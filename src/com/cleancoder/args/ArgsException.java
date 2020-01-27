package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.OK;

public class ArgsException extends Exception {
    static final long serialVersionUID = 1L;
    private String errorArgumentId = null;
    private String errorParameter = null;
    private ErrorCode errorCode = OK;

    public ArgsException() {}

    public ArgsException(String message) {super(message);}

    public ArgsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ArgsException(ErrorCode errorCode, String errorParameter) {
        this.errorCode = errorCode;
        this.errorParameter = errorParameter;
    }

    public ArgsException(ErrorCode errorCode, String errorArgumentId, String errorParameter) {
        this.errorCode = errorCode;
        this.errorParameter = errorParameter;
        this.errorArgumentId = errorArgumentId;
    }

    public String getErrorArgumentId() {
        return errorArgumentId;
    }

    public void setErrorArgumentId(String errorArgumentId) {
        this.errorArgumentId = errorArgumentId;
    }

    public String getErrorParameter() {
        return errorParameter;
    }

    public void setErrorParameter(String errorParameter) {
        this.errorParameter = errorParameter;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String errorMessage() {
        switch (errorCode) {
            case OK:
                return "TILT: Should not get here.";
            case UNEXPECTED_ARGUMENT:
                return String.format("Argument -%s unexpected.", errorArgumentId);
            case MISSING_STRING:
                return String.format("Could not find string parameter for -%s.", errorArgumentId);
            case INVALID_INTEGER:
                return String.format("Argument -%s expects an integer but was '%s'.", errorArgumentId, errorParameter);
            case MISSING_INTEGER:
                return String.format("Could not find integer parameter for -%s.", errorArgumentId);
            case INVALID_DOUBLE:
                return String.format("Argument -%s expects a double but was '%s'.", errorArgumentId, errorParameter);
            case MISSING_DOUBLE:
                return String.format("Could not find double parameter for -%s.", errorArgumentId);
            case INVALID_ARGUMENT_NAME:
                return String.format("'%s' is not a valid argument name.", errorArgumentId);
            case INVALID_ARGUMENT_FORMAT:
                return String.format("'%s' is not a valid argument format.", errorParameter);
            case MISSING_MAP:
                return String.format("Could not find map string for -%s.", errorArgumentId);
            case MALFORMED_MAP:
                return String.format("Map string for -%s is not of form k1:v1,k2:v2...", errorArgumentId);
        }
        return "";
    }

    public enum ErrorCode {
        OK, INVALID_ARGUMENT_FORMAT, UNEXPECTED_ARGUMENT, INVALID_ARGUMENT_NAME,
        MISSING_STRING,
        MISSING_INTEGER, INVALID_INTEGER,
        MISSING_DOUBLE, MALFORMED_MAP, MISSING_MAP, INVALID_DOUBLE}
}
