package com.zsc.servicedata.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserMark implements Serializable {

    private static final long serialVersionUID = 8885591332531568487L;

    private String markCity;

    private Long userId;
}
