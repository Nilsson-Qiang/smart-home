package com.smart.home.service;

import com.google.protobuf.Descriptors;
import com.smart.home.db.DBUtil;
import generated.grpc.smartClimateService.ClimateServiceGrpc;
import generated.grpc.smartClimateService.PreferenceSummary;
import generated.grpc.smartClimateService.TemperaturePreference;
import generated.grpc.smartClimateService.TemperatureRequest;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class SmartClimateClientService {
    @Resource
    ClimateServiceGrpc.ClimateServiceBlockingStub climateServiceBlockingStub;

    @Resource
    ClimateServiceGrpc.ClimateServiceStub climateServiceStub;

    public String getStatus(Float temperature,String roomId){
        TemperatureRequest temperatureRequest = TemperatureRequest.newBuilder().setDesiredTemp(temperature).setRoom(roomId).build();
        return climateServiceBlockingStub.setTemperature(temperatureRequest).getStatus();
    }

    public Map<String, Map<Descriptors.FieldDescriptor,Object>> getUploadPreferences() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Map<String, Map<Descriptors.FieldDescriptor, Object>> response = new HashMap<>();
        StreamObserver<TemperaturePreference> responseObserver= climateServiceStub.uploadTemperaturePreferences(new StreamObserver<PreferenceSummary>() {
            @Override
            public void onNext(PreferenceSummary preferenceSummary) {
                response.put("message",preferenceSummary.getAllFields());
            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });

        DBUtil.getPreferences().forEach(responseObserver::onNext);
        responseObserver.onCompleted();
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyMap();
    }
}
