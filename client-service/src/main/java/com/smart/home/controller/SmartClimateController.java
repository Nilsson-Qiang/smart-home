package com.smart.home.controller;

import com.google.protobuf.Descriptors;
import com.smart.home.service.SmartClimateClientService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SmartClimateController {
    @Resource
    SmartClimateClientService smartClimateClientService;

    @GetMapping("/climate/{roomId}/{temperature}")
    public String getClimateStatus(@PathVariable String roomId,@PathVariable Float temperature){
        return smartClimateClientService.getStatus(temperature,roomId);
    }

    @GetMapping("/upload")
    public Map<String, Map<Descriptors.FieldDescriptor,Object>> getUploadResult() throws InterruptedException {
        return smartClimateClientService.getUploadPreferences();
    }
}
