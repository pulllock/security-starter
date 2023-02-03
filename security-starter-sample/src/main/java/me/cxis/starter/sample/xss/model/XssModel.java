package me.cxis.starter.sample.xss.model;

import java.io.Serializable;

public class XssModel implements Serializable {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "XssModel{" +
                "key='" + key + '\'' +
                '}';
    }
}
