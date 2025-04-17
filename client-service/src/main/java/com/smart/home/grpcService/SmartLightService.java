package com.smart.home.grpcService;

import generated.grpc.smartLightingService.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class SmartLightService extends LightingServiceGrpc.LightingServiceImplBase {
    @Override
    public void toggleLight(LightRequest request, StreamObserver<LightResponse> responseObserver) {
        String lightId = request.getLightId();
        boolean turnOn = request.getTurnOn();

        String status = turnOn ? "Light " + lightId + " turned ON" : "Light " + lightId + " turned OFF";
        log.info(status);

        LightResponse response = LightResponse.newBuilder()
                .setStatus(status)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
