package com.zsc.servicedata.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserEntity implements Serializable {
    public String username;
    public String userpassword;
    public  int age;
    public  String sex;
}
