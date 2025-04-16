package com.smart.home.service;

import generated.grpc.smartClimateService.*;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class SmartClimateService extends ClimateServiceGrpc.ClimateServiceImplBase {

    @Override
    public void setTemperature(TemperatureRequest request, StreamObserver<TemperatureResponse> responseObserver) {
        super.setTemperature(request, responseObserver);
    }

    @Override
    public StreamObserver<TemperaturePreference> uploadTemperaturePreferences(StreamObserver<PreferenceSummary> responseObserver) {
        return super.uploadTemperaturePreferences(responseObserver);
    }
}
