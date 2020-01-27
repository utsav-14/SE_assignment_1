package com.cleancoder.args;

import java.util.Iterator;

public interface ArgumentMarshaller {
    void set(Iterator<String> currentArgument) throws ArgsException;
}
