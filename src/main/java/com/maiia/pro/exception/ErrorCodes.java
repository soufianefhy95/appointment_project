package com.maiia.pro.exception;

public enum ErrorCodes {

    PRACTITIONER_NOT_FOUND(1000),
    PATIENT_NOT_FOUND(1001),

    AVAILABILITY_NOT_FOUND(1002);

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
