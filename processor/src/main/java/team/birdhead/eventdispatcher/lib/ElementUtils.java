package team.birdhead.eventdispatcher.lib;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SuppressWarnings("WeakerAccess")
public final class ElementUtils {

    private ElementUtils() {
    }

    public static String getName(ExecutableElement element) {
        return getName(getTypeName((TypeElement) element.getEnclosingElement()), getMethodName(element));
    }

    public static String getName(String className, String methodName) {
        return className + "#" + methodName;
    }

    public static String getTypeName(ExecutableElement element) {
        return getTypeName((TypeElement) element.getEnclosingElement());
    }

    public static String getTypeName(TypeElement element) {
        return element.getQualifiedName().toString();
    }

    public static String getMethodName(ExecutableElement element) {
        return element.getSimpleName().toString();
    }
}
