package team.birdhead.eventdispatcher.onactivityresult;

import java.util.ArrayList;
import java.util.List;

import team.birdhead.eventdispatcher.EventHandler;
import team.birdhead.eventdispatcher.lib.CollectionUtils;
import team.birdhead.eventdispatcher.lib.JavaCodeUtils;
import team.birdhead.eventdispatcher.lib.StringUtils;

class OnActivityResultHandler extends EventHandler {

    private final int[] requestCodes;
    private final int[] resultCodes;

    OnActivityResultHandler(String methodName, Iterable<String> parameterNames, int[] requestCodes, int[] resultCodes) {
        super(methodName, parameterNames);

        this.requestCodes = requestCodes;
        this.resultCodes = resultCodes;
    }

    @Override
    protected boolean intersects(EventHandler other) {
        if (!(other instanceof OnActivityResultHandler)) {
            return false;
        }

        final OnActivityResultHandler handler = (OnActivityResultHandler) other;
        return (CollectionUtils.isEmpty(requestCodes) || CollectionUtils.isEmpty(handler.requestCodes) || CollectionUtils.intersects(requestCodes, handler.requestCodes))
                && (CollectionUtils.isEmpty(resultCodes) || CollectionUtils.isEmpty(handler.resultCodes) || CollectionUtils.intersects(resultCodes, handler.resultCodes));
    }

    @Override
    protected String getConditionStatement() {
        final List<String> expressions = new ArrayList<>();
        final String requestCodeExpression = getConditionExpression("requestCode", requestCodes);
        final String resultCodeExpression = getConditionExpression("resultCode", resultCodes);

        if (!StringUtils.isEmpty(requestCodeExpression)) {
            expressions.add(requestCodeExpression);
        }

        if (!StringUtils.isEmpty(resultCodeExpression)) {
            expressions.add(resultCodeExpression);
        }

        return JavaCodeUtils.and(expressions);
    }

    @Override
    protected String getStatement(String receiverName) {
        return JavaCodeUtils.methodCall(receiverName, methodName, parameterNames);
    }

    private String getConditionExpression(String parameterName, int[] values) {
        final List<String> statements = new ArrayList<>();

        for (int value : values) {
            statements.add(JavaCodeUtils.equals(parameterName, String.valueOf(value)));
        }

        return JavaCodeUtils.putParentheses(JavaCodeUtils.or(statements));
    }
}
