package com.aimprosoft.mtkachenko.cam.common.util;

/**
 * @author Mikhail Tkachenko
 */
public class CamLogger {

    private String name;

    public static CamLogger getCamLogger(String name) {
        return new CamLogger(name);
    }


    public static CamLogger getCamLogger(Class clazz) {
        return getCamLogger(clazz.getName());
    }


    private CamLogger(String name) {
        this.name = name;
    }


    public void trace(Object o) {
        String message = "[" + name + "]: " + o.toString();
        System.out.println(message);
    }


    public void error(Object o) {
        String message = "[" + name + "]: " + o.toString();
        System.err.println(message);
    }


    public void error(Exception e) {
        error(e.getMessage());
    }


}
