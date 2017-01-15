package team.birdhead.eventdispatcher;

public abstract class EventHandler {

    protected final String methodName;
    protected final Iterable<String> parameterNames;

    protected EventHandler(String methodName, Iterable<String> parameterNames) {
        this.methodName = methodName;
        this.parameterNames = parameterNames;
    }

    protected abstract boolean intersects(EventHandler other);

    String getMethodName() {
        return methodName;
    }

    protected abstract String getConditionStatement();

    protected abstract String getStatement(String receiverName);
}
