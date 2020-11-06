package com.deepazure.visualdata.util;

import java.io.IOException;

public class Cmder {

    private static Cmder instance;

    private static Runtime runtime;

    private Cmder() {
        runtime = Runtime.getRuntime();
    }

    public static Cmder getInstance() {
        if (instance == null) {
            instance = new Cmder();
        }
        return instance;
    }

    public void exec(String cmd) {
        try {
            runtime.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exec(String[] cmds) {
        try {
            runtime.exec(cmds);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
