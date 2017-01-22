package team.birdhead.eventdispatcher.onoptionsitemselected;

import org.junit.Test;

import team.birdhead.eventdispatcher.TestSuite;

public class OnOptionsItemSelectedTestSuite extends TestSuite {

    @Test
    public void testPrivateMethod() {
        assertSource(Sources.PRIVATE_METHOD);
    }

    @Test
    public void testReturnType() {
        assertSource(Sources.RETURN_TYPE);
    }

    @Test
    public void testParameterType() {
        assertSource(Sources.PARAMETER_TYPE);
    }

    @Test
    public void testThrowType() {
        assertSource(Sources.THROWN_TYPE);
    }

    @Test
    public void testDuplicateHandlers1() {
        assertSource(Sources.DUPLICATE_HANDLERS1);
    }

    @Test
    public void testDuplicateHandlers2() {
        assertSource(Sources.DUPLICATE_HANDLERS2);
    }

    @Test
    public void testDuplicateHandlers3() {
        assertSource(Sources.DUPLICATE_HANDLERS3);
    }

    @Test
    public void testParameters1() {
        assertSource(Sources.PARAMETERS1);
    }

    @Test
    public void testParameters2() {
        assertSource(Sources.PARAMETERS2);
    }

    @Test
    public void testItemId1() {
        assertSource(Sources.ITEM_ID1);
    }

    @Test
    public void testItemId2() {
        assertSource(Sources.ITEM_ID2);
    }

    @Test
    public void testMultipleHandlers1() {
        assertSource(Sources.MULTIPLE_HANDLERS1);
    }

    @Test
    public void testMultipleHandlers2() {
        assertSource(Sources.MULTIPLE_HANDLERS2);
    }
}
