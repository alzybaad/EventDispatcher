package team.birdhead.eventdispatcher;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import team.birdhead.eventdispatcher.annotation.SupportedAnnotation;

public abstract class EventDispatcherBuilder {

    protected final ProcessingEnvironment processingEnv;

    protected EventDispatcherBuilder(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    Class<? extends Annotation> getSupportedAnnotationType() {
        final SupportedAnnotation annotation = getClass().getAnnotation(SupportedAnnotation.class);
        if (annotation == null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "No SupportedAnnotation annotation found on " + this.getClass().getName());
            return null;
        }

        return annotation.value();
    }

    Map<Name, EventDispatcher> createEventDispatchers(Set<? extends Element> elements) {
        final Map<Name, EventDispatcher> dispatchers = new LinkedHashMap<>();

        for (Element element : elements) {
            final Annotation annotation = element.getAnnotation(getSupportedAnnotationType());
            if (annotation == null) {
                continue;
            }

            final Name name = ((TypeElement) element.getEnclosingElement()).getQualifiedName();
            if (!dispatchers.containsKey(name)) {
                dispatchers.put(name, createEventDispatcher());
            }

            dispatchers.get(name).addElement((ExecutableElement) element);
        }

        return dispatchers;
    }

    protected abstract EventDispatcher createEventDispatcher();
}
