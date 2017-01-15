package team.birdhead.eventdispatcher.onactivityresult;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import team.birdhead.eventdispatcher.EventDispatcher;
import team.birdhead.eventdispatcher.OnActivityResult;
import team.birdhead.eventdispatcher.lib.IfControlFlowHelper;
import team.birdhead.eventdispatcher.lib.Validator;

class OnActivityResultDispatcher extends EventDispatcher<OnActivityResultHandler> {

    private static final String PARAMETER_TARGET = "target";
    private static final String PARAMETER_REQUEST_CODE = "requestCode";
    private static final String PARAMETER_RESULT_CODE = "resultCode";
    private static final String PARAMETER_DATA = "data";

    OnActivityResultDispatcher(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    @Override
    protected Map<String, TypeMirror> createExpectedParameters(ProcessingEnvironment processingEnv) {
        final Elements elements = processingEnv.getElementUtils();
        final Types types = processingEnv.getTypeUtils();
        final Map<String, TypeMirror> expectedParameters = new LinkedHashMap<>();
        expectedParameters.put(PARAMETER_DATA, elements.getTypeElement("android.content.Intent").asType());
        expectedParameters.put(PARAMETER_RESULT_CODE, types.getPrimitiveType(TypeKind.INT));
        expectedParameters.put(PARAMETER_REQUEST_CODE, types.getPrimitiveType(TypeKind.INT));
        return expectedParameters;
    }

    @Override
    protected Validator createValidator(ProcessingEnvironment processingEnv, Map<String, TypeMirror> expectedParameters) {
        final Types types = processingEnv.getTypeUtils();
        return new Validator(types, types.getNoType(TypeKind.VOID), expectedParameters.values());
    }

    @Override
    protected OnActivityResultHandler createEventHandler(ExecutableElement element) {
        final String methodName = getMethodName(element);
        final Collection<String> parameterNames = getParameterNames(element);
        final OnActivityResult annotation = element.getAnnotation(OnActivityResult.class);
        return new OnActivityResultHandler(methodName, parameterNames, annotation.requestCode(), annotation.resultCode());
    }

    @Override
    public MethodSpec createMethod(TypeMirror target, Map<String, TypeMirror> expectedParameters) {
        final MethodSpec.Builder builder = MethodSpec.methodBuilder("onActivityResult")
                .addModifiers(Modifier.STATIC)
                .addParameter(TypeName.get(target), PARAMETER_TARGET)
                .addParameter(TypeName.get(expectedParameters.get(PARAMETER_REQUEST_CODE)), PARAMETER_REQUEST_CODE)
                .addParameter(TypeName.get(expectedParameters.get(PARAMETER_RESULT_CODE)), PARAMETER_RESULT_CODE)
                .addParameter(TypeName.get(expectedParameters.get(PARAMETER_DATA)), PARAMETER_DATA)
                .returns(boolean.class);

        final IfControlFlowHelper helper = new IfControlFlowHelper(builder);
        for (OnActivityResultHandler handler : handlers) {
            helper.addControlFlow(handler.getConditionStatement());
            helper.addStatement(handler.getStatement(PARAMETER_TARGET));
            helper.addStatement("return true");
        }

        helper.addAlternativeStatement("return false");
        helper.endControlFlow();

        return builder.build();
    }
}
