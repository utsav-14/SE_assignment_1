package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT;

public class MarshallerFactory {

    private MarshallerFactory() {
    }

    public static ArgumentMarshaller getInstance(char elementId, String elementTail) throws ArgsException {
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
                throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
        }
    }
}
