package team.birdhead.eventdispatcher.exception;

import java.util.Locale;

import team.birdhead.eventdispatcher.lib.ElementUtils;

public class DuplicateHandlersException extends RuntimeException {

    public DuplicateHandlersException(String typeName, String methodName1, String methodName2) {
        super(createMessage(typeName, methodName1, methodName2));
    }

    private static String createMessage(String typeName, String methodName1, String methodName2) {
        return String.format(Locale.US, "conditions of '%s' and '%s' are overlapped", ElementUtils.getName(typeName, methodName1), ElementUtils.getName(typeName, methodName2));
    }
}
