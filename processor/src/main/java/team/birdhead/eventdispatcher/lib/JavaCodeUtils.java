package team.birdhead.eventdispatcher.lib;

public final class JavaCodeUtils {

    private JavaCodeUtils() {
    }

    public static String equals(String left, String right) {
        return left + " == " + right;
    }

    public static String and(Iterable<String> expressions) {
        return StringUtils.join(" && ", expressions);
    }

    public static String or(Iterable<String> expressions) {
        return StringUtils.join(" || ", expressions);
    }

    public static String methodCall(String receiver, String method, Iterable<String> parameters) {
        return receiver + "." + method + "(" + StringUtils.join(", ", parameters) + ")";
    }

    public static String returnStatement(String statement) {
        return "return " + statement;
    }

    public static String putParentheses(String expression) {
        return StringUtils.isEmpty(expression) ? "" : "(" + expression + ")";
    }
}
