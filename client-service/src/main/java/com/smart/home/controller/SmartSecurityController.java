package com.smart.home.controller;

import com.google.protobuf.Descriptors;
import com.smart.home.service.SmartSecurityClientService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SmartSecurityController {

   @Resource
   SmartSecurityClientService smartSecurityClientService;
   @GetMapping("/security/{areaId}")
    public List<Map<Descriptors.FieldDescriptor, Object>> getMotion(@PathVariable String areaId) throws InterruptedException {
        return smartSecurityClientService.getMotionAlert(areaId);
    }
    @GetMapping("/feed")
    public List<Map<Descriptors.FieldDescriptor, Object>> getFeed() throws UnsupportedEncodingException, InterruptedException {
       return smartSecurityClientService.getCameraFeed();
    }

}
