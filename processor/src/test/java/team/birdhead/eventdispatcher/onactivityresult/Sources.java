package team.birdhead.eventdispatcher.onactivityresult;

import java.util.Locale;

import team.birdhead.eventdispatcher.Source;
import team.birdhead.eventdispatcher.exception.DuplicateHandlersException;
import team.birdhead.eventdispatcher.exception.InvalidParameterTypesException;
import team.birdhead.eventdispatcher.exception.InvalidReturnTypeException;
import team.birdhead.eventdispatcher.exception.NoThrownTypesException;
import team.birdhead.eventdispatcher.exception.PrivateMethodException;

class Sources {

    static abstract class BaseSource extends Source {

        @Override
        protected String name() {
            return "team.birdhead.eventdispatcher.onactivityresult.test.Test";
        }
    }

    static final Source PRIVATE_METHOD = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return PrivateMethodException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "'%s#onActivityResult' must not be private", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    private void onActivityResult() {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source RETURN_TYPE = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return InvalidReturnTypeException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "'%s#onActivityResult' must specify return type '%s', not '%s'", name(), "void", "boolean");
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    boolean onActivityResult() {",
                    "        return false;",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETER_TYPE1 = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return InvalidParameterTypesException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "'%s#onActivityResult' must declare parameters of type (android.content.Intent,int,int) or its subList", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult(long a) {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETER_TYPE2 = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return InvalidParameterTypesException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "'%s#onActivityResult' must declare parameters of type (android.content.Intent,int,int) or its subList", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult(int a1, int a2, int a3) {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source THROWN_TYPE = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return NoThrownTypesException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "'%s#onActivityResult' must not have any 'throws'", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult() throws Exception {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source DUPLICATE_HANDLERS1 = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return DuplicateHandlersException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "conditions of '%1$s#onActivityResult1' and '%1$s#onActivityResult2' are overlapped", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(requestCode=1)",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(requestCode=1)",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source DUPLICATE_HANDLERS2 = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return DuplicateHandlersException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "conditions of '%1$s#onActivityResult1' and '%1$s#onActivityResult2' are overlapped", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(resultCode=1)",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(resultCode=1)",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source DUPLICATE_HANDLERS3 = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return DuplicateHandlersException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "conditions of '%1$s#onActivityResult1' and '%1$s#onActivityResult2' are overlapped", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(requestCode={1,2})",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(requestCode=1)",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source DUPLICATE_HANDLERS4 = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return DuplicateHandlersException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "conditions of '%1$s#onActivityResult1' and '%1$s#onActivityResult2' are overlapped", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(resultCode={1,2})",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(resultCode=1)",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source DUPLICATE_HANDLERS5 = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return DuplicateHandlersException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "conditions of '%1$s#onActivityResult1' and '%1$s#onActivityResult2' are overlapped", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(requestCode=1)",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source DUPLICATE_HANDLERS6 = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return DuplicateHandlersException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "conditions of '%1$s#onActivityResult1' and '%1$s#onActivityResult2' are overlapped", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(resultCode=1)",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETERS1 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        target.onActivityResult();",
                    "        return true;",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETERS2 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "import android.content.Intent;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult(int resultCode) {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        target.onActivityResult(resultCode);",
                    "        return true;",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETERS3 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "import android.content.Intent;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult(Intent data) {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        target.onActivityResult(data);",
                    "        return true;",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETERS4 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "import android.content.Intent;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult(int requestCode, int resultCode) {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        target.onActivityResult(requestCode, resultCode);",
                    "        return true;",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETERS5 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "import android.content.Intent;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult(int resultCode, Intent data) {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        target.onActivityResult(resultCode, data);",
                    "        return true;",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETERS6 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "import android.content.Intent;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult(int requestCode, int resultCode, Intent data) {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        target.onActivityResult(requestCode, resultCode, data);",
                    "        return true;",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETERS7 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "import android.content.Intent;",
                    "public class Test {",
                    "    @OnActivityResult",
                    "    void onActivityResult(Intent data, int resultCode, int requestCode) {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        target.onActivityResult(data, requestCode, resultCode);",
                    "        return true;",
                    "    }",
                    "}"
            };
        }
    };

    static final Source REQUEST_CODE1 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(requestCode=1)",
                    "    void onActivityResult() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((requestCode == 1)) {",
                    "            target.onActivityResult();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source REQUEST_CODE2 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(requestCode={1,2})",
                    "    void onActivityResult() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((requestCode == 1 || requestCode == 2)) {",
                    "            target.onActivityResult();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source RESULT_CODE1 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(resultCode=1)",
                    "    void onActivityResult() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((resultCode == 1)) {",
                    "            target.onActivityResult();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source RESULT_CODE2 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(resultCode={1,2})",
                    "    void onActivityResult() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((resultCode == 1 || resultCode == 2)) {",
                    "            target.onActivityResult();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source REQUEST_CODE_AND_RESULT_CODE1 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(requestCode=1,resultCode=2)",
                    "    void onActivityResult() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((requestCode == 1) && (resultCode == 2) {",
                    "            target.onActivityResult();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source REQUEST_CODE_AND_RESULT_CODE2 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(requestCode={1,2},resultCode={3,4})",
                    "    void onActivityResult() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((requestCode == 1 || requestCode == 2) && (resultCode == 3 || resultCode == 4)) {",
                    "            target.onActivityResult();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source MULTIPLE_HANDLERS1 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(requestCode=1)",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(requestCode=2)",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((requestCode == 1)) {",
                    "            target.onActivityResult1();",
                    "            return true;",
                    "        } else if ((requestCode == 2)) {",
                    "            target.onActivityResult2();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source MULTIPLE_HANDLERS2 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(resultCode=1)",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(resultCode=2)",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((resultCode == 1)) {",
                    "            target.onActivityResult1();",
                    "            return true;",
                    "        } else if ((resultCode == 2)) {",
                    "            target.onActivityResult2();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source MULTIPLE_HANDLERS3 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(requestCode=1,resultCode=2)",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(requestCode=2,resultCode=3)",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((requestCode == 1) && (resultCode == 2)) {",
                    "            target.onActivityResult1();",
                    "            return true;",
                    "        } else if ((requestCode == 2) && (resultCode == 3)) {",
                    "            target.onActivityResult2();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source MULTIPLE_HANDLERS4 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import team.birdhead.eventdispatcher.OnActivityResult;",
                    "public class Test {",
                    "    @OnActivityResult(requestCode={1,2},resultCode={1,2})",
                    "    void onActivityResult1() {",
                    "    }",
                    "    @OnActivityResult(requestCode={1,2},resultCode={3,4})",
                    "    void onActivityResult2() {",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onactivityresult.test;",
                    "import android.content.Intent;",
                    "class TestEventDispatcher {",
                    "    static boolean onActivityResult(Test target, int requestCode, int resultCode, Intent data) {",
                    "        if ((requestCode == 1 || requestCode == 2) && (resultCode == 1 || resultCode == 2)) {",
                    "            target.onActivityResult1();",
                    "            return true;",
                    "        } else if ((requestCode == 1 || requestCode == 2) && (resultCode == 3 || resultCode == 4)) {",
                    "            target.onActivityResult2();",
                    "            return true;",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };
}
