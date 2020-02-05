package com.zsc.servicedata.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EntityTest implements Serializable {
    private String key;
    private List<Weather24Hours> list;
}
