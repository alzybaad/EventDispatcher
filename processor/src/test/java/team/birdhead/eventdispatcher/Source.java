package team.birdhead.eventdispatcher;

public abstract class Source {

    protected abstract String name();

    protected abstract String[] actual();

    protected String[] expect() {
        return new String[0];
    }

    protected Class<? extends Throwable> exception() {
        return null;
    }

    protected String message() {
        return null;
    }
}
