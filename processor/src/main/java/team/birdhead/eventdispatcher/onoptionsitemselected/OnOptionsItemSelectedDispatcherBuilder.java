package team.birdhead.eventdispatcher.onoptionsitemselected;

import javax.annotation.processing.ProcessingEnvironment;

import team.birdhead.eventdispatcher.EventDispatcher;
import team.birdhead.eventdispatcher.EventDispatcherBuilder;
import team.birdhead.eventdispatcher.OnOptionsItemSelected;
import team.birdhead.eventdispatcher.annotation.SupportedAnnotation;

@SupportedAnnotation(OnOptionsItemSelected.class)
public class OnOptionsItemSelectedDispatcherBuilder extends EventDispatcherBuilder {

    public OnOptionsItemSelectedDispatcherBuilder(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    @Override
    protected EventDispatcher createEventDispatcher() {
        return new OnOptionsItemSelectedDispatcher(processingEnv);
    }
}
