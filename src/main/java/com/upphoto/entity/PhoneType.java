package com.upphoto.entity;

import java.io.Serializable;

public class PhoneType implements Serializable {
    private Integer id;
    private String type;
    private String name;


    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }
}
