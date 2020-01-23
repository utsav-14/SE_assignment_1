package com.cleancoder.args;

public class ArgsMain {

  private static final String SCHEMA = "l,p#,d*";
  private static final Character LOGGING_FLAG = 'l';
  private static final Character PORT_NUMBER_FLAG = 'p';
  private static final Character DIRECTORY_NAME_FLAG = 'd';

  public static void main(String[] args) {
    try {
      CommandParameters parameters = extractArguments(args);
      executeApplication(parameters);
    } catch (ArgsException e) {
      System.out.printf("Argument error: %s\n", e.errorMessage());
    }
  }

  private static CommandParameters extractArguments(final String[] commandLineArgs) throws ArgsException {
    Args arg = new Args(SCHEMA, commandLineArgs);
    CommandParameters parameters = new CommandParameters();
    parameters.setLoggingEnabled(arg.getBoolean(LOGGING_FLAG));
    parameters.setPortNumber(arg.getInt(PORT_NUMBER_FLAG));
    parameters.setDirectoryName(arg.getString(DIRECTORY_NAME_FLAG));
    return  parameters;
  }

  private static void executeApplication(CommandParameters parameters) {
    System.out.printf("Logging enabled:%s\nPort number:%d\nDirectory name:%s\n",parameters.isLoggingEnabled(),
            parameters.getPortNumber(),
            parameters.getDirectoryName());
  }
}