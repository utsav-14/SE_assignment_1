package com.cleancoder.args;

import java.util.Iterator;

public class BooleanArgumentMarshaller implements ArgumentMarshaller {
    private Boolean loggingEnabled = false;

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        loggingEnabled = true;
    }

    public static boolean getValue(ArgumentMarshaller am) {
        if (am instanceof BooleanArgumentMarshaller)
            return ((BooleanArgumentMarshaller) am).loggingEnabled;
        else
            return false;
    }
}
