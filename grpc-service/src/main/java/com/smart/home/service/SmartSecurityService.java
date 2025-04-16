package com.smart.home.service;

import generated.grpc.smartSecurityService.*;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class SmartSecurityService extends SecurityServiceGrpc.SecurityServiceImplBase {
    @Override
    public void lockDoor(DoorRequest request, StreamObserver<DoorResponse> responseObserver) {
        super.lockDoor(request, responseObserver);
    }

    @Override
    public void streamMotionAlerts(MotionRequest request, StreamObserver<MotionAlert> responseObserver) {
        super.streamMotionAlerts(request, responseObserver);
    }

    @Override
    public StreamObserver<CameraFrame> streamCameraFeed(StreamObserver<CameraAcknowledgement> responseObserver) {
        return super.streamCameraFeed(responseObserver);
    }
}
