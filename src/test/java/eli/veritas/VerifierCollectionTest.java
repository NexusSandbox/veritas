package eli.veritas;

import eli.veritas.exception.CompositeExceptionTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Verifies workflows through the {@link Verifier} for generic {@link Collection} checks.
 */
public class VerifierCollectionTest
{
    private static final long TEST_VALUE1 = 123l;

    @Test public void testFailureWithValue_ifContainsAllValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("z");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("x");
        expectedCollection.add("y");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifContainsAllValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            expectedCollection,
                                                                            TEST_VALUE1).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[z]] to contain all values of collection[[x, y]].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifContainsAnyValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("z");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("x");
        expectedCollection.add("y");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifContainsAnyValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            expectedCollection,
                                                                            TEST_VALUE1).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[z]] to contain any values of collection[[x, y]].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifContainsNoValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("z");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("z");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifContainsNoValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            expectedCollection,
                                                                            TEST_VALUE1).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[z]] to contain no values of collection[[z]].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifEmpty()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEmpty("testField1",
                                                                                                                                 testCollection,
                                                                                                                                 TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[]] to be empty, or null.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifMatchesAllValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        testCollection.add("y");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifMatchesAllValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            s -> s.equals("x"),
                                                                            TEST_VALUE1).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[x, y]] to match all values.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifMatchesAnyValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        testCollection.add("y");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifMatchesAnyValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            s -> s.equals("z"),
                                                                            TEST_VALUE1).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[x, y]] to match any values.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifMatchesNoValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifMatchesNoValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            s -> s.equals("x"),
                                                                            TEST_VALUE1).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[x]] to match no values.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNotEmpty()
    {
        final List<String> testCollection = new LinkedList<>();
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEmpty("testField1",
                                                                                                                                    testCollection,
                                                                                                                                    TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection to not be empty, or null.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifContainsAllValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("z");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("x");
        expectedCollection.add("y");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifContainsAllValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            expectedCollection).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[z]] to contain all values of collection[[x, y]].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifContainsAnyValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("z");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("x");
        expectedCollection.add("y");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifContainsAnyValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            expectedCollection).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[z]] to contain any values of collection[[x, y]].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifContainsNoValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("z");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("z");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifContainsNoValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            expectedCollection).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[z]] to contain no values of collection[[z]].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifEmpty()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEmpty("testField1",
                                                                                                                                 testCollection)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[]] to be empty, or null.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifMatchesAllValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        testCollection.add("y");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifMatchesAllValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            s -> s.equals("x")).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[x, y]] to match all values.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifMatchesAnyValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        testCollection.add("y");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifMatchesAnyValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            s -> s.equals("z")).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[x, y]] to match any values.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifMatchesNoValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifMatchesNoValues(
                                                                            "testField1",
                                                                            testCollection,
                                                                            s -> s.equals("x")).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection[[x]] to match no values.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotEmpty()
    {
        final List<String> testCollection = new LinkedList<>();
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEmpty("testField1",
                                                                                                                                    testCollection)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tExpected collection to not be empty, or null.",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testSuccess_ifContainsAllValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        testCollection.add("y");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("x");
        expectedCollection.add("y");
        Verifier.<Long>forChecking(VerifierTest.class).ifContainsAllValues("testField1", testCollection, expectedCollection)
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifContainsAnyValues()
    {
        final List<String> testCollection1 = new LinkedList<>();
        testCollection1.add("x");
        final List<String> testCollection2 = new LinkedList<>();
        testCollection2.add("y");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("x");
        expectedCollection.add("y");
        Verifier.<Long>forChecking(VerifierTest.class).ifContainsAnyValues("testField1", testCollection1, expectedCollection)
                                                      .ifContainsAnyValues("testField2", testCollection2, expectedCollection)
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifContainsNoValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("y");
        expectedCollection.add("z");
        Verifier.<Long>forChecking(VerifierTest.class).ifContainsNoValues("testField1", testCollection, expectedCollection)
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifEmpty()
    {
        final List<String> testCollection = new LinkedList<>();
        Verifier.<Long>forChecking(VerifierTest.class).ifEmpty("testField1", testCollection).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifMatchesAllValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        testCollection.add("y");
        Verifier.<Long>forChecking(VerifierTest.class).ifMatchesAllValues("testField1", testCollection, s -> s.equals("x") || s.equals("y"))
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifMatchesAnyValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        testCollection.add("y");
        Verifier.<Long>forChecking(VerifierTest.class).ifMatchesAnyValues("testField1", testCollection, s -> s.equals("x"))
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifMatchesNoValues()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("x");
        testCollection.add("y");
        Verifier.<Long>forChecking(VerifierTest.class).ifMatchesNoValues("testField1", testCollection, s -> s.equals("z"))
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNotEmpty()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("");
        Verifier.<Long>forChecking(VerifierTest.class).ifNotEmpty("testField1", testCollection).throwing(CompositeExceptionTester::new);
    }
}
