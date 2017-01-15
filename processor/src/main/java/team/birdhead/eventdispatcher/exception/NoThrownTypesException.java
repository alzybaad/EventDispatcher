package team.birdhead.eventdispatcher.exception;

import java.util.Locale;

import javax.lang.model.element.ExecutableElement;

import team.birdhead.eventdispatcher.lib.ElementUtils;

public class NoThrownTypesException extends RuntimeException {

    public NoThrownTypesException(ExecutableElement element) {
        super(createMessage(element));
    }

    private static String createMessage(ExecutableElement element) {
        return String.format(Locale.US, "'%s' must not have any 'throws'", ElementUtils.getName(element));
    }
}
