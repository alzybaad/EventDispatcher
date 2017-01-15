package team.birdhead.eventdispatcher.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

import team.birdhead.eventdispatcher.exception.InvalidParameterTypesException;
import team.birdhead.eventdispatcher.exception.InvalidReturnTypeException;
import team.birdhead.eventdispatcher.exception.NoThrownTypesException;
import team.birdhead.eventdispatcher.exception.PrivateMethodException;

public class Validator {

    private final Types types;
    private final TypeMirror returnType;
    private final Collection<TypeMirror> parameterTypes;

    public Validator(Types types, TypeMirror returnType, Collection<TypeMirror> parameterTypes) {
        this.types = types;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
    }

    public void execute(ExecutableElement element) {
        validateModifier(element);
        validateReturnType(element);
        validateParameterTypes(element);
        validateThrownTypes(element);
    }

    private void validateModifier(ExecutableElement element) {
        if (element.getModifiers().contains(Modifier.PRIVATE)) {
            throw new PrivateMethodException(element);
        }
    }

    private void validateReturnType(ExecutableElement element) {
        if (!types.isSameType(element.getReturnType(), returnType)) {
            throw new InvalidReturnTypeException(element, types.getNoType(TypeKind.VOID));
        }
    }

    private void validateParameterTypes(ExecutableElement element) {
        final List<TypeMirror> expectedParameterTypes = new ArrayList<>(parameterTypes);
        for (VariableElement parameter : element.getParameters()) {
            final TypeMirror parameterType = parameter.asType();

            int index = -1;
            for (int i = 0; i < expectedParameterTypes.size(); ++i) {
                final TypeMirror expectedParameterType = expectedParameterTypes.get(i);

                if (types.isSameType(expectedParameterType, parameterType)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                throw new InvalidParameterTypesException(element, parameterTypes);
            }

            expectedParameterTypes.remove(index);
        }
    }

    private void validateThrownTypes(ExecutableElement element) {
        if (!element.getThrownTypes().isEmpty()) {
            throw new NoThrownTypesException(element);
        }
    }
}
