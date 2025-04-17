package com.smart.home.controller;

import com.smart.home.requestBto.LightToggle;
import com.smart.home.service.SmartLightClientService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SmartLightController {

    @Resource
    SmartLightClientService lightClientService;

    @GetMapping("/light")
    public String getToggleLight(@RequestBody LightToggle lightToggle){
        return lightClientService.toggleLight(lightToggle.getLightId(),lightToggle.getTurnOn());
    }
}
