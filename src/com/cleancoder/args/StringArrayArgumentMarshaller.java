package com.cleancoder.args;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_STRING;

public class StringArrayArgumentMarshaller implements ArgumentMarshaller {
  private List<String> strings = new ArrayList<String>();

  @Override
  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      strings.add(currentArgument.next());
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }

  public static String[] getValue(ArgumentMarshaller am) {
    if (am != null && am instanceof StringArrayArgumentMarshaller)
      return ((StringArrayArgumentMarshaller) am).strings.toArray(new String[0]);
    else
      return new String[0];
  }
}
