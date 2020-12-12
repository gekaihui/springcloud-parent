package com.gkh.springboot.entity;

import lombok.Data;

import java.util.HashMap;

/**
 * @author gekaihui
 * @date 2020/5/28
 */

@Data
public class SubscribeMessageDto {
    private String touser;
    private String template_id;
    private String page;
    private String miniprogram_state;
    private String lang;
    private HashMap<String, Object> data;
}
