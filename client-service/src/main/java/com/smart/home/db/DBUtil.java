package com.smart.home.db;

import com.google.protobuf.ByteString;
import generated.grpc.smartClimateService.TemperaturePreference;
import generated.grpc.smartSecurityService.CameraFrame;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    public static List<TemperaturePreference> getPreferences(){
        ArrayList<TemperaturePreference> list = new ArrayList<>();
        list.add(TemperaturePreference.newBuilder().setPreferredTemp(Float.parseFloat("3.24")).setTimePeriod("morning").build());
        list.add(TemperaturePreference.newBuilder().setPreferredTemp(Float.parseFloat("3.24")).setTimePeriod("lunch").build());
        list.add(TemperaturePreference.newBuilder().setPreferredTemp(Float.parseFloat("3.24")).setTimePeriod("evening").build());
        return list;
    }

    public static List<CameraFrame> getCameraFrames() throws UnsupportedEncodingException {
        ArrayList<CameraFrame> list = new ArrayList<>();
        list.add(CameraFrame.newBuilder().setCameraId("1").setImageData(ByteString.copyFrom("this is a test1".getBytes())).build());
        list.add(CameraFrame.newBuilder().setCameraId("2").setImageData(ByteString.copyFrom("this is a test2".getBytes())).build());
        list.add(CameraFrame.newBuilder().setCameraId("3").setImageData(ByteString.copyFrom("this is a test2".getBytes())).build());
        return list;
    }

    //
}
