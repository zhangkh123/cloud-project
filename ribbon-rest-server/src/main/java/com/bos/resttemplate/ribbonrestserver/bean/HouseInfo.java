package com.bos.resttemplate.ribbonrestserver.bean;

import java.io.Serializable;

public class HouseInfo implements Serializable {
    private static final Long serialVersionUID=1L;
    private String provice;
    private String city;
    private String zone;
    private String address;

    public HouseInfo() {
    }

    public HouseInfo(String provice, String city, String zone, String address) {
        this.provice = provice;
        this.city = city;
        this.zone = zone;
        this.address = address;
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
