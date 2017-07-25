package com.cnjaj.myapplication.dao;

import com.cnjaj.myapplication.dao.inject.Column;
import com.cnjaj.myapplication.dao.inject.Table;

@Table
public class DataBean extends OfflineMessage {
    @Column
    private String stringValue;
    @Column
    private double doubleValue;
    @Column
    private boolean booleanValue;
    @Column
    private float floatValue;
    @Column
    private byte byteValue;
    @Column
    private Wrapper wrapper;

    public Wrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public void setByteValue(byte byteValue) {
        this.byteValue = byteValue;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "stringValue='" + stringValue + '\'' +
                ", doubleValue=" + doubleValue +
                ", booleanValue=" + booleanValue +
                ", floatValue=" + floatValue +
                ", byteValue=" + byteValue + "}" + super.toString();
    }
}
