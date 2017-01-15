package team.birdhead.eventdispatcher;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import team.birdhead.eventdispatcher.onactivityresult.OnActivityResultDispatcherBuilder;
import team.birdhead.eventdispatcher.onoptionsitemselected.OnOptionsItemSelectedDispatcherBuilder;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class EventDispatcherProcessor extends AbstractProcessor {

    private EventDispatcherBuilder[] builders;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        builders = new EventDispatcherBuilder[] {
                new OnActivityResultDispatcherBuilder(processingEnv),
                new OnOptionsItemSelectedDispatcherBuilder(processingEnv)
        };
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> supportedAnnotationTypes = new LinkedHashSet<>();

        for (EventDispatcherBuilder builder : builders) {
            supportedAnnotationTypes.add(builder.getSupportedAnnotationType().getName());
        }

        return supportedAnnotationTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        generateJavaFiles(createEventDispatchers(roundEnv));
        return true;
    }

    private Map<Name, Set<EventDispatcher>> createEventDispatchers(RoundEnvironment roundEnv) {
        final Map<Name, Set<EventDispatcher>> dispatchers = new LinkedHashMap<>();

        for (EventDispatcherBuilder builder : builders) {
            final Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(builder.getSupportedAnnotationType());
            final Map<Name, ? extends EventDispatcher> dispatcher = builder.createEventDispatchers(elements);
            for (Map.Entry<Name, ? extends EventDispatcher> entry : dispatcher.entrySet()) {
                final Name name = entry.getKey();
                if (!dispatchers.containsKey(name)) {
                    dispatchers.put(name, new LinkedHashSet<>());
                }

                dispatchers.get(name).add(entry.getValue());
            }
        }

        return dispatchers;
    }

    private void generateJavaFiles(Map<Name, Set<EventDispatcher>> dispatchers) {
        for (Map.Entry<Name, Set<EventDispatcher>> entry : dispatchers.entrySet()) {
            final Name name = entry.getKey();
            final TypeElement element = processingEnv.getElementUtils().getTypeElement(name);
            final ClassName className = ClassName.get(element);
            final TypeMirror target = element.asType();

            final TypeSpec.Builder builder = TypeSpec.classBuilder(className.simpleName() + "EventDispatcher");
            for (EventDispatcher dispatcher : entry.getValue()) {
                builder.addMethod(dispatcher.createMethod(target));
            }

            try {
                JavaFile.builder(className.packageName(), builder.build()).build().writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
