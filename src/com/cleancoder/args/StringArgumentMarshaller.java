package com.cleancoder.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_STRING;

public class StringArgumentMarshaller implements ArgumentMarshaller {
    private String directoryName = null;

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        try {
            directoryName = currentArgument.next();
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_STRING);
        }
    }

    public static String getValue(ArgumentMarshaller am) {
        if (am instanceof StringArgumentMarshaller)
            return ((StringArgumentMarshaller) am).directoryName;
        else
            return "";
    }
}
