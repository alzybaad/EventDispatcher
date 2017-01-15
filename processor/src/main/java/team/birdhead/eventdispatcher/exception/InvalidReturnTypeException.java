package team.birdhead.eventdispatcher.exception;

import java.util.Locale;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import team.birdhead.eventdispatcher.lib.ElementUtils;

public class InvalidReturnTypeException extends RuntimeException {

    public InvalidReturnTypeException(ExecutableElement element, TypeMirror expectedType) {
        super(createMessage(element, expectedType));
    }

    private static String createMessage(ExecutableElement element, TypeMirror expectedType) {
        return String.format(Locale.US, "'%s' must specify return type '%s', not '%s'", ElementUtils.getName(element), expectedType, element.getReturnType());
    }
}
