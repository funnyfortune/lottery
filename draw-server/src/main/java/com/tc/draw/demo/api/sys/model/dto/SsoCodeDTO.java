package com.tc.draw.demo.api.sys.model.dto;

import lombok.Data;

@Data
public class SsoCodeDTO {

    private String appId;

    private String sign;

    private String loginName;

    private long timestamp;

}
