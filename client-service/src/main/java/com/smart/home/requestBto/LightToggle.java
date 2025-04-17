package com.smart.home.requestBto;

import lombok.Data;

@Data
public class LightToggle {
    private String lightId;
    private Boolean turnOn;
}
