package com.deepazure.visualdata.util;

public interface Observer {

    void update(Message msg);

    void update(String msg);

}
