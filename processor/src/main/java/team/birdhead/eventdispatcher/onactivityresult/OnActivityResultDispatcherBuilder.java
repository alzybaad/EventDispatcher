package team.birdhead.eventdispatcher.onactivityresult;

import javax.annotation.processing.ProcessingEnvironment;

import team.birdhead.eventdispatcher.EventDispatcher;
import team.birdhead.eventdispatcher.EventDispatcherBuilder;
import team.birdhead.eventdispatcher.OnActivityResult;
import team.birdhead.eventdispatcher.annotation.SupportedAnnotation;

@SupportedAnnotation(OnActivityResult.class)
public class OnActivityResultDispatcherBuilder extends EventDispatcherBuilder {

    public OnActivityResultDispatcherBuilder(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    @Override
    protected EventDispatcher createEventDispatcher() {
        return new OnActivityResultDispatcher(processingEnv);
    }
}
