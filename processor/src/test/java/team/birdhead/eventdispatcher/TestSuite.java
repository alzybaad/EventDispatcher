package team.birdhead.eventdispatcher;

import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourceSubjectFactory;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TestSuite {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    protected void assertSource(Source source) {
        if (source.exception() != null) {
            expectedException.expect(RuntimeException.class);
            expectedException.expectCause(new CauseMatcher(source.exception(), source.message()));
        }

        Truth.assert_()
                .about(JavaSourceSubjectFactory.javaSource())
                .that(JavaFileObjects.forSourceLines(source.name(), source.actual()))
                .processedWith(new EventDispatcherProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(JavaFileObjects.forSourceLines(source.name() + "EventDispatcher", source.expect()));
    }

    private class CauseMatcher extends TypeSafeMatcher<Throwable> {

        private final Class<? extends Throwable> type;
        private final String message;

        CauseMatcher(Class<? extends Throwable> type, String message) {
            this.type = type;
            this.message = message;
        }

        @Override
        protected boolean matchesSafely(Throwable item) {
            return item.getClass().isAssignableFrom(type) && item.getMessage().contains(message);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("expects type ").appendValue(type)
                    .appendText(" and a message ").appendValue(message);
        }
    }
}
