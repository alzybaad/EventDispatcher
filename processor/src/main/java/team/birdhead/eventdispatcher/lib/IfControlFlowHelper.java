package team.birdhead.eventdispatcher.lib;

import com.squareup.javapoet.MethodSpec;

public class IfControlFlowHelper {

    private final MethodSpec.Builder builder;
    private boolean inControlFlow;

    public IfControlFlowHelper(MethodSpec.Builder builder) {
        this.builder = builder;
    }

    public void addControlFlow(String controlFlow, Object... args) {
        if (StringUtils.isEmpty(controlFlow)) {
            return;
        }

        if (!inControlFlow) {
            builder.beginControlFlow("if (" + controlFlow + ")", args);
            inControlFlow = true;
        } else {
            builder.nextControlFlow("else if (" + controlFlow + ")", args);
        }
    }

    public void addStatement(String statement, Object... args) {
        builder.addStatement(statement, args);
    }

    public void addAlternativeStatement(String statement, Object... args) {
        if (inControlFlow) {
            builder.nextControlFlow("else");
            addStatement(statement, args);
        }
    }

    public void endControlFlow() {
        if (inControlFlow) {
            builder.endControlFlow();
            inControlFlow = false;
        }
    }
}
