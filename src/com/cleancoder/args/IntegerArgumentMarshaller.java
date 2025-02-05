package com.cleancoder.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.INVALID_INTEGER;
import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_INTEGER;

public class IntegerArgumentMarshaller implements ArgumentMarshaller {
    private Integer portNumber = 0;

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            portNumber = Integer.parseInt(parameter);
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_INTEGER);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_INTEGER, parameter);
        }
    }

    public static int getValue(ArgumentMarshaller am) {
        if (am instanceof IntegerArgumentMarshaller)
            return ((IntegerArgumentMarshaller) am).portNumber;
        else
            return 0;
    }
}
