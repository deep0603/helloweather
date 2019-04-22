package com.example.fuck.helloweather.db;

import org.litepal.crud.LitePalSupport;

public class WeatherId extends LitePalSupport {
    private String name;
    private String cityCode;

    public String getName() {
        return name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
