package com.cleancoder.args;

public class ArgsMain {

    private static final String SCHEMA = "logging,port#,directory*";
    private static final String LOGGING_FLAG = "logging";
    private static final String PORT_NUMBER_FLAG = "port";
    private static final String DIRECTORY_NAME_FLAG = "directory";

    public static void main(String[] args) {
        try {
            extractArgsAndExecuteApplication(args);
        } catch (ArgsException e) {
            System.out.printf("Argument error: %s\n", e.errorMessage());
        }
    }

    private static void extractArgsAndExecuteApplication(String[] commandLineArgs) throws ArgsException {
        CommandParameters parameters = extractArguments(commandLineArgs);
        executeApplication(parameters);
    }

    private static CommandParameters extractArguments(final String[] commandLineArgs) throws ArgsException {
        Args arg = new Args(SCHEMA, commandLineArgs);
        CommandParameters parameters = new CommandParameters();
        parameters.setLoggingEnabled(arg.getBoolean(LOGGING_FLAG));
        parameters.setPortNumber(arg.getInt(PORT_NUMBER_FLAG));
        parameters.setDirectoryName(arg.getString(DIRECTORY_NAME_FLAG));
        return parameters;
    }

    private static void executeApplication(CommandParameters parameters) {
        System.out.printf("Logging enabled:%s\nPort number:%d\nDirectory name:%s\n", parameters.isLoggingEnabled(),
                parameters.getPortNumber(),
                parameters.getDirectoryName());
    }
}