package com.cleancoder.args;

import java.util.*;

import static com.cleancoder.args.ArgsException.ErrorCode.INVALID_ARGUMENT_NAME;
import static com.cleancoder.args.ArgsException.ErrorCode.UNEXPECTED_ARGUMENT;

public class Args {
    private Map<String, ArgumentMarshaller> marshallers;
    private Set<String> argsFound;
    private ListIterator<String> currentArgument;

    public Args(String schema, String[] commandLineArgs) throws ArgsException {
        marshallers = new HashMap<String, ArgumentMarshaller>();
        argsFound = new HashSet<String>();
        parseSchema(schema);
        parseArgumentStrings(Arrays.asList(commandLineArgs));
    }

    private void parseSchema(String schema) throws ArgsException {
        for (String element : schema.split(","))
            if (element.length() > 0)
                parseSchemaElement(element.trim());
    }

    private void parseSchemaElement(String element) throws ArgsException {
        int lastIndex = 1;
        for (int i = 1; i < element.length(); ++i) {
            if (!Character.isLetter(element.charAt(i))) {
                break;
            }
            ++lastIndex;
        }
        String elementId = element.substring(0, lastIndex);
        String elementTail = element.substring(lastIndex);
        validateSchemaElementId(elementId);
        marshallers.put(elementId, MarshallerFactory.getInstance(elementId, elementTail));
    }

    private void validateSchemaElementId(String elementId) throws ArgsException {
        for (int i = 0; i < elementId.length(); ++i) {
            if (!Character.isLetter(elementId.charAt(i)))
                throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
        }
    }

    private void parseArgumentStrings(List<String> argsList) throws ArgsException {
        for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
            String argString = currentArgument.next();
            if (argString.startsWith("-")) {
                parseArgName(argString.substring(1));
            } else {
                currentArgument.previous();
                break;
            }
        }
    }

    private void parseArgName(String argName) throws ArgsException {
        Optional<ArgumentMarshaller> marshallerForArgName = Optional.ofNullable(marshallers.get(argName));
        if (marshallerForArgName.isPresent()) {
            argsFound.add(argName);
            addCurrentArgumentToMarshaller(marshallerForArgName, argName);
        } else {
            throw new ArgsException(UNEXPECTED_ARGUMENT, argName, null);
        }
    }

    private void addCurrentArgumentToMarshaller(Optional<ArgumentMarshaller> marshallerForArgName, String argName) throws  ArgsException{
        try {
            ((ArgumentMarshaller)marshallerForArgName.get()).set(currentArgument);
        }catch (ArgsException ex){
            ex.setErrorArgumentId(argName);
            throw ex;
        }
    }
    public boolean has(String arg) {
        return argsFound.contains(arg);
    }

    public int nextArgument() {
        return currentArgument.nextIndex();
    }

    public boolean getBoolean(String arg) {
        return BooleanArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public String getString(String arg) {
        return StringArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public int getInt(String arg) {
        return IntegerArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public double getDouble(String arg) {
        return DoubleArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public String[] getStringArray(String arg) {
        return StringArrayArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public Map<String, String> getMap(String arg) {
        return MapArgumentMarshaller.getValue(marshallers.get(arg));
    }
}