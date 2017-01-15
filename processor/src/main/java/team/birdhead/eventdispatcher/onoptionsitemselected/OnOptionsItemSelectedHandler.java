package team.birdhead.eventdispatcher.onoptionsitemselected;

import java.util.ArrayList;
import java.util.List;

import team.birdhead.eventdispatcher.EventHandler;
import team.birdhead.eventdispatcher.lib.CollectionUtils;
import team.birdhead.eventdispatcher.lib.JavaCodeUtils;

class OnOptionsItemSelectedHandler extends EventHandler {

    private final int[] itemIds;

    OnOptionsItemSelectedHandler(String methodName, Iterable<String> parameterNames, int[] itemIds) {
        super(methodName, parameterNames);

        this.itemIds = itemIds;
    }

    @Override
    protected boolean intersects(EventHandler other) {
        if (!(other instanceof OnOptionsItemSelectedHandler)) {
            return false;
        }

        final OnOptionsItemSelectedHandler handler = (OnOptionsItemSelectedHandler) other;
        return CollectionUtils.isEmpty(itemIds) || CollectionUtils.isEmpty(handler.itemIds) || CollectionUtils.intersects(itemIds, handler.itemIds);
    }

    @Override
    protected String getConditionStatement() {
        return getConditionExpression("item.getItemId()", itemIds);
    }

    @Override
    protected String getStatement(String receiverName) {
        return JavaCodeUtils.returnStatement(JavaCodeUtils.methodCall(receiverName, methodName, parameterNames));
    }

    private String getConditionExpression(String parameterName, int[] values) {
        final List<String> statements = new ArrayList<>();

        for (int value : values) {
            statements.add(JavaCodeUtils.equals(parameterName, String.valueOf(value)));
        }

        return JavaCodeUtils.or(statements);
    }
}
