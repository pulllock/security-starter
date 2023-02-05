package me.cxis.starter.sample.crlf.model;

import java.io.Serializable;

public class CrlfModel implements Serializable {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "CrlfModel{" +
                "key='" + key + '\'' +
                '}';
    }
}
