package com.smart.home.service;

import com.google.protobuf.Descriptors;
import com.smart.home.db.DBUtil;
import generated.grpc.smartSecurityService.*;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class SmartSecurityClientService {

    @Resource
    SecurityServiceGrpc.SecurityServiceStub securityServiceStub;

    public List<Map<Descriptors.FieldDescriptor, Object>> getMotionAlert(String areaId) throws InterruptedException {
        final MotionRequest motionRequest = MotionRequest.newBuilder().setAreaId(areaId).build();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
        securityServiceStub.streamMotionAlerts(motionRequest, new StreamObserver<MotionAlert>() {
            @Override
            public void onNext(MotionAlert gradeResponse) {
                response.add(gradeResponse.getAllFields());
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
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyList();
    }

    public List<Map<Descriptors.FieldDescriptor, Object>> getCameraFeed() throws InterruptedException, UnsupportedEncodingException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
        StreamObserver<CameraFrame> responseObserver =  securityServiceStub.streamCameraFeed(new StreamObserver<CameraAcknowledgement>() {
            @Override
            public void onNext(CameraAcknowledgement cameraAcknowledgementResponse) {
                response.add(cameraAcknowledgementResponse.getAllFields());
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
        DBUtil.getCameraFrames()
                .forEach(cameraFrame -> responseObserver.onNext(CameraFrame.newBuilder().setCameraId(cameraFrame.getCameraId()).build()));
        responseObserver.onCompleted();
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyList();
    }


}
