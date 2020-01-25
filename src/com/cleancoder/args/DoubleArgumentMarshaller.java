package com.cleancoder.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.INVALID_DOUBLE;
import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_DOUBLE;

public class DoubleArgumentMarshaller implements ArgumentMarshaller {
  private Double doubleValue = null;

  @Override
  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      doubleValue = Double.parseDouble(parameter);
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_DOUBLE);
    } catch (NumberFormatException e) {
      throw new ArgsException(INVALID_DOUBLE, parameter);
    }
  }

  public static double getValue(ArgumentMarshaller am) {
    if (am instanceof DoubleArgumentMarshaller)
      return ((DoubleArgumentMarshaller) am).doubleValue;
    else
      return 0.0;
  }
}
