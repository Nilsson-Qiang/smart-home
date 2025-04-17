package com.smart.home.grpcService;

import generated.grpc.smartSecurityService.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class SmartSecurityService extends SecurityServiceGrpc.SecurityServiceImplBase {


    @Override
    public void streamMotionAlerts(MotionRequest request, StreamObserver<MotionAlert> responseObserver) {
        String areaId = request.getAreaId();

        // 模拟3条服务器推送的报警
        for (int i = 1; i <= 3; i++) {
            MotionAlert alert = MotionAlert.newBuilder()
                    .setAlertMessage("Motion detected in area " + areaId + " [" + i + "]")
                    .setTimestamp(java.time.LocalTime.now().toString())
                    .build();

            responseObserver.onNext(alert);
            try {
                Thread.sleep(1000); // 模拟每秒推送一条
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<CameraFrame> streamCameraFeed(StreamObserver<CameraAcknowledgement> responseObserver) {
        return new StreamObserver<CameraFrame>() {
            @Override
            public void onNext(CameraFrame frame) {
                log.info("Received frame from camera: " + frame.getCameraId());

                CameraAcknowledgement ack = CameraAcknowledgement.newBuilder()
                        .setStatus("Frame received from " + frame.getCameraId())
                        .build();
                responseObserver.onNext(ack);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
               log.info("Camera feed stream completed.");
                responseObserver.onCompleted();
            }
        };
    }
}
