package team.birdhead.eventdispatcher.exception;

import java.util.Locale;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import team.birdhead.eventdispatcher.lib.ElementUtils;
import team.birdhead.eventdispatcher.lib.StringUtils;

public class InvalidParameterTypesException extends RuntimeException {

    public InvalidParameterTypesException(ExecutableElement element, Iterable<TypeMirror> expectedTypes) {
        super(createMessage(element, expectedTypes));
    }

    private static String createMessage(ExecutableElement element, Iterable<TypeMirror> expectedTypes) {
        return String.format(Locale.US, "'%s' must declare parameters of type (%s) or its subList", ElementUtils.getName(element), StringUtils.join(",", expectedTypes));
    }


}
