package com.deepazure.visualdata.util;

public interface Observable {

    void register(Observer o);

    void remove(Observer o);

    void notifyOn(Message msg);

    void notifyOn(String msg);

}
