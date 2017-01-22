package team.birdhead.eventdispatcher.onactivityresult;

import org.junit.Test;

import team.birdhead.eventdispatcher.TestSuite;

public class OnActivityResultTestSuite extends TestSuite {

    @Test
    public void testPrivateMethod() {
        assertSource(Sources.PRIVATE_METHOD);
    }

    @Test
    public void testReturnType() {
        assertSource(Sources.RETURN_TYPE);
    }

    @Test
    public void testParameterType1() {
        assertSource(Sources.PARAMETER_TYPE1);
    }

    @Test
    public void testParameterType2() {
        assertSource(Sources.PARAMETER_TYPE2);
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
    public void testDuplicateHandlers4() {
        assertSource(Sources.DUPLICATE_HANDLERS4);
    }

    @Test
    public void testDuplicateHandlers5() {
        assertSource(Sources.DUPLICATE_HANDLERS5);
    }

    @Test
    public void testDuplicateHandlers6() {
        assertSource(Sources.DUPLICATE_HANDLERS6);
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
    public void testParameters3() {
        assertSource(Sources.PARAMETERS3);
    }

    @Test
    public void testParameters4() {
        assertSource(Sources.PARAMETERS4);
    }

    @Test
    public void testParameters5() {
        assertSource(Sources.PARAMETERS5);
    }

    @Test
    public void testParameters6() {
        assertSource(Sources.PARAMETERS6);
    }

    @Test
    public void testParameters7() {
        assertSource(Sources.PARAMETERS7);
    }

    @Test
    public void testRequestCode1() {
        assertSource(Sources.REQUEST_CODE1);
    }

    @Test
    public void testRequestCode2() {
        assertSource(Sources.REQUEST_CODE2);
    }

    @Test
    public void testResultCode1() {
        assertSource(Sources.RESULT_CODE1);
    }

    @Test
    public void testResultCode2() {
        assertSource(Sources.RESULT_CODE2);
    }

    @Test
    public void testRequestCodeAndResultCode1() {
        assertSource(Sources.REQUEST_CODE_AND_RESULT_CODE1);
    }

    @Test
    public void testRequestCodeAndResultCode2() {
        assertSource(Sources.REQUEST_CODE_AND_RESULT_CODE2);
    }

    @Test
    public void testMultipleHandlers1() {
        assertSource(Sources.MULTIPLE_HANDLERS1);
    }

    @Test
    public void testMultipleHandlers2() {
        assertSource(Sources.MULTIPLE_HANDLERS2);
    }

    @Test
    public void testMultipleHandlers3() {
        assertSource(Sources.MULTIPLE_HANDLERS3);
    }

    @Test
    public void testMultipleHandlers4() {
        assertSource(Sources.MULTIPLE_HANDLERS4);
    }
}
