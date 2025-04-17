package com.smart.home.config;

import generated.grpc.smartClimateService.ClimateServiceGrpc;
import generated.grpc.smartLightingService.LightingServiceGrpc;
import generated.grpc.smartSecurityService.SecurityServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class ServerConfig {
    @Bean
    ClimateServiceGrpc.ClimateServiceBlockingStub climateServiceBlockingStub(GrpcChannelFactory factory){
        return ClimateServiceGrpc.newBlockingStub(factory.createChannel("service1"));
    }
    @Bean
    ClimateServiceGrpc.ClimateServiceStub climateServiceStub(GrpcChannelFactory channelFactory){
        return ClimateServiceGrpc.newStub(channelFactory.createChannel("service1"));
    }

    @Bean
    LightingServiceGrpc.LightingServiceBlockingStub lightingServiceBlockingStub(GrpcChannelFactory channelFactory){
        return LightingServiceGrpc.newBlockingStub(channelFactory.createChannel("service1"));
    }

    @Bean
    SecurityServiceGrpc.SecurityServiceStub securityServiceStub(GrpcChannelFactory channelFactory){
        return SecurityServiceGrpc.newStub(channelFactory.createChannel("service1"));
    }
}
