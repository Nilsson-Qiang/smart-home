package com.smart.home.service;

import generated.grpc.smartLightingService.LightRequest;
import generated.grpc.smartLightingService.LightingServiceGrpc;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class SmartLightClientService {

    @Resource
    LightingServiceGrpc.LightingServiceBlockingStub lightingServiceStub;


    public String toggleLight(String lightId,boolean turnOn){
        LightRequest lightRequest = LightRequest.newBuilder().setLightId(lightId).setTurnOn(turnOn).build();
        return lightingServiceStub.toggleLight(lightRequest).getStatus();
    }


}
