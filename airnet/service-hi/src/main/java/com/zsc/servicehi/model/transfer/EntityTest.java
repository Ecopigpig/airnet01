package com.zsc.servicehi.model.transfer;

import com.zsc.servicehi.model.weather.Weather24Hours;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EntityTest implements Serializable {
    private String key;
    private List<Weather24Hours> list;
}
