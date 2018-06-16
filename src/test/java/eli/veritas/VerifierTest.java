package eli.veritas;

import eli.veritas.exception.CompositeExceptionTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Verifies workflows through the {@link Verifier} for generic existence checks.
 */
public class VerifierTest
{
    private static final long TEST_VALUE1 = 123l;
    private static final long TEST_VALUE2 = 234l;

    @Test public void testFailureWithValue_ifNotNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotNull("testField",
                                                                                                                                   null,
                                                                                                                                   TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField\";\tExpected object to be non-null.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNull("testField",
                                                                                                                                "",
                                                                                                                                TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField\";\tExpected object to be null.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifOrNotNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifOrNotNull("testField1",
                                                                                                                                     null,
                                                                                                                                     "testField2",
                                                                                                                                     null,
                                                                                                                                     TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for fields: \"VerifierTest#testField1\" and \"VerifierTest#testField2\";\tExpected either object to be non-null.",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifOrNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifOrNull("testField1",
                                                                                                                                  "",
                                                                                                                                  "testField2",
                                                                                                                                  "",
                                                                                                                                  TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for fields: \"VerifierTest#testField1\" and \"VerifierTest#testField2\";\tExpected either object to be null.",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNotXorNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotXorNull("testField1",
                                                                                                                                      null,
                                                                                                                                      "testField2",
                                                                                                                                      "",
                                                                                                                                      TEST_VALUE1)
                                                                                                                        .ifNotXorNull("testField3",
                                                                                                                                      "",
                                                                                                                                      "testField4",
                                                                                                                                      null,
                                                                                                                                      TEST_VALUE2)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format(
                "Assertion failed for fields: \"VerifierTest#testField1\" and \"VerifierTest#testField2\";\tExpected either both objects to be null, or neither object to be null.%n"
                + "Assertion failed for fields: \"VerifierTest#testField3\" and \"VerifierTest#testField4\";\tExpected either both objects to be null, or neither object to be null."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        expectedValues.add(TEST_VALUE2);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifXorNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifXorNull("testField1",
                                                                                                                                   "",
                                                                                                                                   "testField2",
                                                                                                                                   "",
                                                                                                                                   TEST_VALUE1)
                                                                                                                        .ifXorNull("testField3",
                                                                                                                                   null,
                                                                                                                                   "testField4",
                                                                                                                                   null,
                                                                                                                                   TEST_VALUE2)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format(
                "Assertion failed for fields: \"VerifierTest#testField1\" and \"VerifierTest#testField2\";\tExpected exactly 1 object to be null, but not both or neither.%n"
                + "Assertion failed for fields: \"VerifierTest#testField3\" and \"VerifierTest#testField4\";\tExpected exactly 1 object to be null, but not both or neither."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        expectedValues.add(TEST_VALUE2);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotNull("testField", null)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField\";\tExpected object to be non-null.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNull("testField", "")
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField\";\tExpected object to be null.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifOrNotNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifOrNotNull("testField1",
                                                                                                                                     null,
                                                                                                                                     "testField2",
                                                                                                                                     null)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for fields: \"VerifierTest#testField1\" and \"VerifierTest#testField2\";\tExpected either object to be non-null.",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifOrNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifOrNull("testField1",
                                                                                                                                  "",
                                                                                                                                  "testField2",
                                                                                                                                  "")
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for fields: \"VerifierTest#testField1\" and \"VerifierTest#testField2\";\tExpected either object to be null.",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotXorNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotXorNull("testField1",
                                                                                                                                      null,
                                                                                                                                      "testField2",
                                                                                                                                      "")
                                                                                                                        .ifNotXorNull("testField3",
                                                                                                                                      "",
                                                                                                                                      "testField4",
                                                                                                                                      null)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format(
                "Assertion failed for fields: \"VerifierTest#testField1\" and \"VerifierTest#testField2\";\tExpected either both objects to be null, or neither object to be null.%n"
                + "Assertion failed for fields: \"VerifierTest#testField3\" and \"VerifierTest#testField4\";\tExpected either both objects to be null, or neither object to be null."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifXorNull()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifXorNull("testField1",
                                                                                                                                   "",
                                                                                                                                   "testField2",
                                                                                                                                   "")
                                                                                                                        .ifXorNull("testField3",
                                                                                                                                   null,
                                                                                                                                   "testField4",
                                                                                                                                   null)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format(
                "Assertion failed for fields: \"VerifierTest#testField1\" and \"VerifierTest#testField2\";\tExpected exactly 1 object to be null, but not both or neither.%n"
                + "Assertion failed for fields: \"VerifierTest#testField3\" and \"VerifierTest#testField4\";\tExpected exactly 1 object to be null, but not both or neither."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testSuccess_ifNotNull()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifNotNull("testField", "").throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNull()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifNull("testField", null).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifOrNotNull()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifOrNotNull("testField1", "", "testField2", "")
                                                      .ifOrNotNull("testField3", null, "testField4", "")
                                                      .ifOrNotNull("testField5", "", "testField6", null)
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifOrNull()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifOrNull("testField1", null, "testField2", null)
                                                      .ifOrNull("testField3", "", "testField4", null)
                                                      .ifOrNull("testField5", null, "testField6", "")
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNotXorNull()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifNotXorNull("testField1", null, "testField2", null)
                                                      .ifNotXorNull("testField3", "", "testField4", "")
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifXorNull()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifXorNull("testField1", "", "testField2", null)
                                                      .ifXorNull("testField3", null, "testField4", "")
                                                      .throwing(CompositeExceptionTester::new);
    }
}
