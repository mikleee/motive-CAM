package com.aimprosoft.mtkachenko.cam.common.model;

/**
 * @author Mikhail Tkachenko
 */
public class Constraint {


    private String name;
    private String value;


    public Constraint(String name, String condition, String message) {
        this.name = name;
        this.value = makeConstraint(condition, message);
    }


    private String makeConstraint(String condition, String message) {
        return "<as:constraint condition=\"" + condition + "\" action=\"printMessage(" + message + ")\" />";
    }


    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

}
