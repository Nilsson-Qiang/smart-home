package com.smart.home.grpcService;

import generated.grpc.smartClimateService.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@GrpcService
@Slf4j
public class SmartClimateService extends ClimateServiceGrpc.ClimateServiceImplBase {

    @Override
    public void setTemperature(TemperatureRequest request, StreamObserver<TemperatureResponse> responseObserver) {
        float temp = request.getDesiredTemp();
        String room = request.getRoom();
        log.info("get temperature request:{}",request.getDesiredTemp());
        String status = "Set temperature to " + temp + "°C in " + room;
        log.info("status");
        TemperatureResponse temperatureResponse = TemperatureResponse.newBuilder().setStatus(status).build();
        responseObserver.onNext(temperatureResponse);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<TemperaturePreference> uploadTemperaturePreferences(StreamObserver<PreferenceSummary> responseObserver) {
        List<String> prefs = new ArrayList<>();

        return new StreamObserver<TemperaturePreference>() {
            @Override
            public void onNext(TemperaturePreference pref) {
                prefs.add("[" + pref.getTimePeriod() + ": " + pref.getPreferredTemp() + "°C]");
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                String summary = "Received preferences: " + String.join(", ", prefs);
                PreferenceSummary response = PreferenceSummary.newBuilder()
                        .setMessage(summary)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
}
