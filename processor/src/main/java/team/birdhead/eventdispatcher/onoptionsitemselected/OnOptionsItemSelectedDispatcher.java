package team.birdhead.eventdispatcher.onoptionsitemselected;

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
import team.birdhead.eventdispatcher.OnOptionsItemSelected;
import team.birdhead.eventdispatcher.lib.IfControlFlowHelper;
import team.birdhead.eventdispatcher.lib.Validator;

class OnOptionsItemSelectedDispatcher extends EventDispatcher<OnOptionsItemSelectedHandler> {

    private static final String PARAMETER_TARGET = "target";
    private static final String PARAMETER_MENU_ITEM = "item";

    OnOptionsItemSelectedDispatcher(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    @Override
    protected Map<String, TypeMirror> createExpectedParameters(ProcessingEnvironment processingEnv) {
        final Elements elements = processingEnv.getElementUtils();
        final Map<String, TypeMirror> expectedParameters = new LinkedHashMap<>();
        expectedParameters.put(PARAMETER_MENU_ITEM, elements.getTypeElement("android.view.MenuItem").asType());
        return expectedParameters;
    }

    @Override
    protected Validator createValidator(ProcessingEnvironment processingEnv, Map<String, TypeMirror> expectedParameters) {
        final Types types = processingEnv.getTypeUtils();
        return new Validator(types, types.getPrimitiveType(TypeKind.BOOLEAN), expectedParameters.values());
    }

    @Override
    protected OnOptionsItemSelectedHandler createEventHandler(ExecutableElement element) {
        final String methodName = getMethodName(element);
        final Collection<String> parameterNames = getParameterNames(element);
        final OnOptionsItemSelected annotation = element.getAnnotation(OnOptionsItemSelected.class);
        return new OnOptionsItemSelectedHandler(methodName, parameterNames, annotation.value());
    }

    @Override
    public MethodSpec createMethod(TypeMirror target, Map<String, TypeMirror> expectedParameters) {
        final MethodSpec.Builder builder = MethodSpec.methodBuilder("onOptionsItemSelected")
                .addModifiers(Modifier.STATIC)
                .addParameter(TypeName.get(target), PARAMETER_TARGET)
                .addParameter(TypeName.get(expectedParameters.get(PARAMETER_MENU_ITEM)), PARAMETER_MENU_ITEM)
                .returns(boolean.class);

        final IfControlFlowHelper helper = new IfControlFlowHelper(builder);
        for (OnOptionsItemSelectedHandler handler : handlers) {
            helper.addControlFlow(handler.getConditionStatement());
            helper.addStatement(handler.getStatement(PARAMETER_TARGET));
        }

        helper.addAlternativeStatement("return false");
        helper.endControlFlow();

        return builder.build();
    }
}
