package com.smart.home.service;

import generated.grpc.smartLightingService.*;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class SmartLightService extends LightingServiceGrpc.LightingServiceImplBase {
    @Override
    public void toggleLight(LightRequest request, StreamObserver<LightResponse> responseObserver) {
        super.toggleLight(request, responseObserver);
    }

    @Override
    public void setSchedule(ScheduleRequest request, StreamObserver<ScheduleResponse> responseObserver) {
        super.setSchedule(request, responseObserver);
    }
}
