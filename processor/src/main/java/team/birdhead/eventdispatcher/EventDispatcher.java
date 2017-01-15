package team.birdhead.eventdispatcher;

import com.squareup.javapoet.MethodSpec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

import team.birdhead.eventdispatcher.exception.DuplicateHandlersException;
import team.birdhead.eventdispatcher.lib.ElementUtils;
import team.birdhead.eventdispatcher.lib.Validator;

public abstract class EventDispatcher<T extends EventHandler> {

    protected final ProcessingEnvironment processingEnv;
    protected final Set<T> handlers;
    protected final Map<String, TypeMirror> expectedParameters;
    private final Validator validator;

    protected EventDispatcher(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        this.handlers = new LinkedHashSet<>();
        this.expectedParameters = createExpectedParameters(processingEnv);
        this.validator = createValidator(processingEnv, this.expectedParameters);
    }

    protected abstract Map<String, TypeMirror> createExpectedParameters(ProcessingEnvironment processingEnv);
    protected abstract Validator createValidator(ProcessingEnvironment processingEnv, Map<String, TypeMirror> expectedParameters);

    void addElement(ExecutableElement element) {
        validator.execute(element);

        final T target = createEventHandler(element);

        for (T handler : handlers) {
            if (target.intersects(handler)) {
                throw new DuplicateHandlersException(ElementUtils.getTypeName(element), handler.getMethodName(), target.getMethodName());
            }
        }

        handlers.add(target);
    }

    protected abstract T createEventHandler(ExecutableElement element);

    MethodSpec createMethod(TypeMirror target) {
        if (handlers.isEmpty()) {
            return null;
        }

        return createMethod(target, expectedParameters);
    }

    protected abstract MethodSpec createMethod(TypeMirror target, Map<String, TypeMirror> expectedParameters);

    protected String getMethodName(ExecutableElement element) {
        return ElementUtils.getMethodName(element);
    }

    protected Collection<String> getParameterNames(ExecutableElement element) {
        final Types types = processingEnv.getTypeUtils();
        final List<String> parameterNames = new ArrayList<>();
        final LinkedHashMap<String, TypeMirror> expectedParameters = new LinkedHashMap<>(this.expectedParameters);

        for (int i = element.getParameters().size() - 1; i >= 0; --i) {
            final TypeMirror parameterType = element.getParameters().get(i).asType();

            for (String key : expectedParameters.keySet()) {
                final TypeMirror expectedParameterType = expectedParameters.get(key);
                if (types.isSameType(parameterType, expectedParameterType)) {
                    parameterNames.add(key);
                    expectedParameters.remove(key);
                    break;
                }
            }
        }

        Collections.reverse(parameterNames);

        return parameterNames;
    }
}
