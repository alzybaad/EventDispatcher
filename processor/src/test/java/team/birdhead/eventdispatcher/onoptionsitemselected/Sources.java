package team.birdhead.eventdispatcher.onoptionsitemselected;

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
            return "team.birdhead.eventdispatcher.onoptionsitemselected.test.Test";
        }
    }

    static final Source PRIVATE_METHOD = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return PrivateMethodException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "'%s#onOptionsItemSelected' must not be private", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected",
                    "    private boolean onOptionsItemSelected() {",
                    "        return true;",
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
            return String.format(Locale.US, "'%s#onOptionsItemSelected' must specify return type '%s', not '%s'", name(), "boolean", "void");
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected",
                    "    void onOptionsItemSelected() {",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETER_TYPE = new BaseSource() {

        @Override
        protected Class<? extends Throwable> exception() {
            return InvalidParameterTypesException.class;
        }

        @Override
        protected String message() {
            return String.format(Locale.US, "'%s#onOptionsItemSelected' must declare parameters of type (android.view.MenuItem) or its subList", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected",
                    "    boolean onOptionsItemSelected(long a) {",
                    "        return true;",
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
            return String.format(Locale.US, "'%s#onOptionsItemSelected' must not have any 'throws'", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected",
                    "    boolean onOptionsItemSelected() throws Exception {",
                    "        return true;",
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
            return String.format(Locale.US, "conditions of '%1$s#onOptionsItemSelected1' and '%1$s#onOptionsItemSelected2' are overlapped", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected(1)",
                    "    boolean onOptionsItemSelected1() {",
                    "        return true;",
                    "    }",
                    "    @OnOptionsItemSelected(1)",
                    "    boolean onOptionsItemSelected2() {",
                    "        return true;",
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
            return String.format(Locale.US, "conditions of '%1$s#onOptionsItemSelected1' and '%1$s#onOptionsItemSelected2' are overlapped", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected({1,2})",
                    "    boolean onOptionsItemSelected1() {",
                    "        return true;",
                    "    }",
                    "    @OnOptionsItemSelected(1)",
                    "    boolean onOptionsItemSelected2() {",
                    "        return true;",
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
            return String.format(Locale.US, "conditions of '%1$s#onOptionsItemSelected1' and '%1$s#onOptionsItemSelected2' are overlapped", name());
        }

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected",
                    "    boolean onOptionsItemSelected1() {",
                    "        return true;",
                    "    }",
                    "    @OnOptionsItemSelected(1)",
                    "    boolean onOptionsItemSelected2() {",
                    "        return true;",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETERS1 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected",
                    "    boolean onOptionsItemSelected() {",
                    "        return true;",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import android.view.MenuItem;",
                    "class TestEventDispatcher {",
                    "    static boolean onOptionsItemSelected(Test target, MenuItem item) {",
                    "        return target.onOptionsItemSelected();",
                    "    }",
                    "}"
            };
        }
    };

    static final Source PARAMETERS2 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "import android.view.MenuItem;",
                    "public class Test {",
                    "    @OnOptionsItemSelected",
                    "    boolean onOptionsItemSelected(MenuItem item) {",
                    "        return true;",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import android.view.MenuItem;",
                    "class TestEventDispatcher {",
                    "    static boolean onOptionsItemSelected(Test target, MenuItem item) {",
                    "        return target.onOptionsItemSelected(item);",
                    "    }",
                    "}"
            };
        }
    };

    static final Source ITEM_ID1 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected(1)",
                    "    boolean onOptionsItemSelected() {",
                    "        return true;",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import android.view.MenuItem;",
                    "class TestEventDispatcher {",
                    "    static boolean onOptionsItemSelected(Test target, MenuItem item) {",
                    "        if (item.getItemId() == 1) {",
                    "            return target.onOptionsItemSelected();",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };

    static final Source ITEM_ID2 = new BaseSource() {

        @Override
        protected String[] actual() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected({1,2})",
                    "    boolean onOptionsItemSelected() {",
                    "        return true;",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import android.view.MenuItem;",
                    "class TestEventDispatcher {",
                    "    static boolean onOptionsItemSelected(Test target, MenuItem item) {",
                    "        if (item.getItemId() == 1 || item.getItemId() == 2) {",
                    "            return target.onOptionsItemSelected();",
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
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected(1)",
                    "    boolean onOptionsItemSelected1() {",
                    "        return true;",
                    "    }",
                    "    @OnOptionsItemSelected(2)",
                    "    boolean onOptionsItemSelected2() {",
                    "        return true;",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import android.view.MenuItem;",
                    "class TestEventDispatcher {",
                    "    static boolean onOptionsItemSelected(Test target, MenuItem item) {",
                    "        if (item.getItemId() == 1) {",
                    "            return target.onOptionsItemSelected1();",
                    "        } else if (item.getItemId() == 2) {",
                    "            return target.onOptionsItemSelected2();",
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
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import team.birdhead.eventdispatcher.OnOptionsItemSelected;",
                    "public class Test {",
                    "    @OnOptionsItemSelected({1,2})",
                    "    boolean onOptionsItemSelected1() {",
                    "        return true;",
                    "    }",
                    "    @OnOptionsItemSelected({3,4})",
                    "    boolean onOptionsItemSelected2() {",
                    "        return true;",
                    "    }",
                    "}"
            };
        }

        @Override
        protected String[] expect() {
            return new String[] {
                    "package team.birdhead.eventdispatcher.onoptionsitemselected.test;",
                    "import android.view.MenuItem;",
                    "class TestEventDispatcher {",
                    "    static boolean onOptionsItemSelected(Test target, MenuItem item) {",
                    "        if (item.getItemId() == 1 || item.getItemId() == 2) {",
                    "            return target.onOptionsItemSelected1();",
                    "        } else if (item.getItemId() == 3 || item.getItemId() == 4) {",
                    "            return target.onOptionsItemSelected2();",
                    "        } else {",
                    "            return false;",
                    "        }",
                    "    }",
                    "}"
            };
        }
    };
}
