package com.cleancoder.args;

public class MarshallerFactory {

    private MarshallerFactory() {
    }

    public static ArgumentMarshaller getInstance(String elementId, String elementTail) throws ArgsException {
        switch (elementTail) {
            case "":
                return new BooleanArgumentMarshaller();
            case "#":
                return new IntegerArgumentMarshaller();
            case "*":
                return new StringArgumentMarshaller();
            case "##":
                return new DoubleArgumentMarshaller();
            case "[*]":
                return new StringArrayArgumentMarshaller();
            case "&":
                return new MapArgumentMarshaller();
            default:
                throw new ArgsException(ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT, elementId, elementTail);
        }
    }
}
