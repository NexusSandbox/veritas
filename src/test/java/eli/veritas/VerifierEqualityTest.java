package eli.veritas;

import eli.veritas.exception.CompositeExceptionTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

/**
 * Verifies workflows through the {@link Verifier} for generic equality checks.
 */
public class VerifierEqualityTest
{
    private static final long TEST_VALUE1 = 123l;
    private static final long TEST_VALUE2 = 234l;

    @Test public void testFailureWithValue_ifEqualBoolean()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1",
                                                                                                                                 true,
                                                                                                                                 false,
                                                                                                                                 TEST_VALUE1)
                                                                                                                        .ifEqual("testField2",
                                                                                                                                 false,
                                                                                                                                 true,
                                                                                                                                 TEST_VALUE2)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[true] ≡ Expected[false].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[false] ≡ Expected[true]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        expectedValues.add(TEST_VALUE2);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifEqualCollection()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("xxx");
        final List<String> expectedCollection1 = new LinkedList<>();
        expectedCollection1.add("yyy");
        final List<String> expectedCollection2 = new LinkedList<>();
        expectedCollection2.add("xxx");
        expectedCollection2.add("yyy");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1",
                                                                                                                                 testCollection,
                                                                                                                                 expectedCollection1,
                                                                                                                                 TEST_VALUE1)
                                                                                                                        .ifEqual("testField2",
                                                                                                                                 testCollection,
                                                                                                                                 expectedCollection2,
                                                                                                                                 TEST_VALUE2)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[[xxx]] ≡ Expected[[yyy]].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[[xxx]] ≡ Expected[[xxx, yyy]]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        expectedValues.add(TEST_VALUE2);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifEqualInstant()
    {
        final Instant future = Instant.MAX;
        final Instant past = Instant.MIN;
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqualDate("testField1",
                                                                                                                                     future,
                                                                                                                                     past,
                                                                                                                                     1,
                                                                                                                                     TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tActual[+1000000000-12-31T23:59:59.999999999Z] ± ε[1 s] ≡ Expected[-1000000000-01-01T00:00:00Z].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifEqualLocalDate()
    {
        final LocalDateTime future = LocalDateTime.MAX;
        final LocalDateTime past = LocalDateTime.MIN;
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqualDate("testField1",
                                                                                                                                     future,
                                                                                                                                     past,
                                                                                                                                     1,
                                                                                                                                     TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tActual[+999999999-12-31T23:59:59.999999999] ± ε[1 s] ≡ Expected[-999999999-01-01T00:00].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifEqualNumeric()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1",
                                                                                                                                 1,
                                                                                                                                 2,
                                                                                                                                 TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[1] ≡ Expected[2].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifEqualNumericWithError()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqualWithError("testField1",
                                                                                                                                          1.5,
                                                                                                                                          2.5,
                                                                                                                                          .5d,
                                                                                                                                          TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[1.5] ± ε[0.5] ≡ Expected[2.5].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifEqualString()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1",
                                                                                                                                 "x",
                                                                                                                                 "X",
                                                                                                                                 true,
                                                                                                                                 TEST_VALUE1)
                                                                                                                        .ifEqual("testField2",
                                                                                                                                 "y",
                                                                                                                                 "x",
                                                                                                                                 false,
                                                                                                                                 TEST_VALUE2)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[\"x\"] ≡ Expected[\"X\"].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[\"y\"] ≡ Expected[\"x\"]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        expectedValues.add(TEST_VALUE2);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNotEqualBoolean()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1",
                                                                                                                                    true,
                                                                                                                                    true,
                                                                                                                                    TEST_VALUE1)
                                                                                                                        .ifNotEqual("testField2",
                                                                                                                                    false,
                                                                                                                                    false,
                                                                                                                                    TEST_VALUE2)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[true] ≠ Expected[true].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[false] ≠ Expected[false]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        expectedValues.add(TEST_VALUE2);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNotEqualCollection()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("xxx");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("xxx");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1",
                                                                                                                                    testCollection,
                                                                                                                                    expectedCollection,
                                                                                                                                    TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[[xxx]] ≠ Expected[[xxx]].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNotEqualInstant()
    {
        final Instant future = Instant.MAX;
        final Instant past = Instant.MAX.minus(1, ChronoUnit.SECONDS);
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqualDate("testField1",
                                                                                                                                        future,
                                                                                                                                        past,
                                                                                                                                        5,
                                                                                                                                        TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tActual[+1000000000-12-31T23:59:59.999999999Z] ± ε[5 s] ≠ Expected[+1000000000-12-31T23:59:58.999999999Z].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNotEqualLocalDate()
    {
        final LocalDateTime future = LocalDateTime.MAX;
        final LocalDateTime past = LocalDateTime.MAX.minus(1, ChronoUnit.SECONDS);
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqualDate("testField1",
                                                                                                                                        future,
                                                                                                                                        past,
                                                                                                                                        5,
                                                                                                                                        TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tActual[+999999999-12-31T23:59:59.999999999] ± ε[5 s] ≠ Expected[+999999999-12-31T23:59:58.999999999].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNotEqualNumeric()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1",
                                                                                                                                    1,
                                                                                                                                    1,
                                                                                                                                    TEST_VALUE1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[1] ≠ Expected[1].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNotEqualNumericWithError()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqualWithError(
                                                                            "testField1",
                                                                            1.5,
                                                                            1.25,
                                                                            .5d,
                                                                            TEST_VALUE1).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[1.5] ± ε[0.5] ≠ Expected[1.25].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailureWithValue_ifNotEqualString()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1",
                                                                                                                                    "x",
                                                                                                                                    "x",
                                                                                                                                    true,
                                                                                                                                    TEST_VALUE1)
                                                                                                                        .ifNotEqual("testField2",
                                                                                                                                    "x",
                                                                                                                                    "X",
                                                                                                                                    false,
                                                                                                                                    TEST_VALUE2)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[\"x\"] ≠ Expected[\"x\"].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[\"x\"] ≠ Expected[\"X\"]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        expectedValues.add(TEST_VALUE1);
        expectedValues.add(TEST_VALUE2);
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifEqualBoolean()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1",
                                                                                                                                 true,
                                                                                                                                 false)
                                                                                                                        .ifEqual("testField2",
                                                                                                                                 false,
                                                                                                                                 true)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[true] ≡ Expected[false].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[false] ≡ Expected[true]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifEqualCollection()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("xxx");
        final List<String> expectedCollection1 = new LinkedList<>();
        expectedCollection1.add("yyy");
        final List<String> expectedCollection2 = new LinkedList<>();
        expectedCollection2.add("xxx");
        expectedCollection2.add("yyy");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1",
                                                                                                                                 testCollection,
                                                                                                                                 expectedCollection1)
                                                                                                                        .ifEqual("testField2",
                                                                                                                                 testCollection,
                                                                                                                                 expectedCollection2)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[[xxx]] ≡ Expected[[yyy]].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[[xxx]] ≡ Expected[[xxx, yyy]]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifEqualInstant()
    {
        final Instant future = Instant.MAX;
        final Instant past = Instant.MIN;
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqualDate("testField1",
                                                                                                                                     future,
                                                                                                                                     past,
                                                                                                                                     1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tActual[+1000000000-12-31T23:59:59.999999999Z] ± ε[1 s] ≡ Expected[-1000000000-01-01T00:00:00Z].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifEqualLocalDate()
    {
        final LocalDateTime future = LocalDateTime.MAX;
        final LocalDateTime past = LocalDateTime.MIN;
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqualDate("testField1",
                                                                                                                                     future,
                                                                                                                                     past,
                                                                                                                                     1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tActual[+999999999-12-31T23:59:59.999999999] ± ε[1 s] ≡ Expected[-999999999-01-01T00:00].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifEqualNumeric()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1", 1, 2)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[1] ≡ Expected[2].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifEqualNumericWithError()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqualWithError("testField1",
                                                                                                                                          1.5,
                                                                                                                                          2.5,
                                                                                                                                          .5d)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[1.5] ± ε[0.5] ≡ Expected[2.5].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifEqualString()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1",
                                                                                                                                 "x",
                                                                                                                                 "X",
                                                                                                                                 true)
                                                                                                                        .ifEqual("testField2",
                                                                                                                                 "y",
                                                                                                                                 "x",
                                                                                                                                 false)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[\"x\"] ≡ Expected[\"X\"].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[\"y\"] ≡ Expected[\"x\"]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotEqualBoolean()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1",
                                                                                                                                    true,
                                                                                                                                    true)
                                                                                                                        .ifNotEqual("testField2",
                                                                                                                                    false,
                                                                                                                                    false)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[true] ≠ Expected[true].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[false] ≠ Expected[false]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotEqualCollection()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("xxx");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("xxx");
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1",
                                                                                                                                    testCollection,
                                                                                                                                    expectedCollection)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[[xxx]] ≠ Expected[[xxx]].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotEqualInstant()
    {
        final Instant future = Instant.MAX;
        final Instant past = Instant.MAX.minus(1, ChronoUnit.SECONDS);
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqualDate("testField1",
                                                                                                                                        future,
                                                                                                                                        past,
                                                                                                                                        5)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tActual[+1000000000-12-31T23:59:59.999999999Z] ± ε[5 s] ≠ Expected[+1000000000-12-31T23:59:58.999999999Z].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotEqualLocalDate()
    {
        final LocalDateTime future = LocalDateTime.MAX;
        final LocalDateTime past = LocalDateTime.MAX.minus(1, ChronoUnit.SECONDS);
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqualDate("testField1",
                                                                                                                                        future,
                                                                                                                                        past,
                                                                                                                                        1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(
                "Assertion failed for field: \"VerifierTest#testField1\";\tActual[+999999999-12-31T23:59:59.999999999] ± ε[1 s] ≠ Expected[+999999999-12-31T23:59:58.999999999].",
                ex.getMessage(),
                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotEqualNumeric()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1",
                                                                                                                                    1,
                                                                                                                                    1)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[1] ≠ Expected[1].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotEqualNumericWithError()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqualWithError(
                                                                            "testField1",
                                                                            1.5,
                                                                            1.25,
                                                                            .5d).throwing(CompositeExceptionTester::new));

        Assertions.assertEquals("Assertion failed for field: \"VerifierTest#testField1\";\tActual[1.5] ± ε[0.5] ≠ Expected[1.25].",
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testFailure_ifNotEqualString()
    {
        final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
                                                                    () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1",
                                                                                                                                    "x",
                                                                                                                                    "x",
                                                                                                                                    true)
                                                                                                                        .ifNotEqual("testField2",
                                                                                                                                    "x",
                                                                                                                                    "X",
                                                                                                                                    false)
                                                                                                                        .throwing(
                                                                                                                                CompositeExceptionTester::new));

        Assertions.assertEquals(String.format("Assertion failed for field: \"VerifierTest#testField1\";\tActual[\"x\"] ≠ Expected[\"x\"].%n"
                                              + "Assertion failed for field: \"VerifierTest#testField2\";\tActual[\"x\"] ≠ Expected[\"X\"]."),
                                ex.getMessage(),
                                "Unexpected exception message");
        final List<Long> expectedValues = new LinkedList<>();
        Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
    }

    @Test public void testSuccess_ifEqualBoolean()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1", true, true)
                                                      .ifEqual("testField2", false, false)
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifEqualCollection()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("xxx");
        final List<String> expectedCollection = new LinkedList<>();
        expectedCollection.add("xxx");
        Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1", testCollection, expectedCollection)
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifEqualInstant()
    {
        final Instant future = Instant.MAX;
        final Instant past = Instant.MAX.minus(1, ChronoUnit.SECONDS);
        Verifier.<Long>forChecking(VerifierTest.class).ifEqualDate("testField1", future, past, 5).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifEqualLocalDate()
    {
        final LocalDateTime future = LocalDateTime.MAX;
        final LocalDateTime past = LocalDateTime.MAX.minus(1, ChronoUnit.SECONDS);
        Verifier.<Long>forChecking(VerifierTest.class).ifEqualDate("testField1", future, past, 5).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifEqualNumeric()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1", 1, 1).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifEqualNumericWithError()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifEqualWithError("testField1", 1.5, 1.75, .5d).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifEqualString()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifEqual("testField1", "x", "x", true)
                                                      .ifEqual("testField2", "x", "X", false)
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNotEqualBoolean()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1", true, false)
                                                      .ifNotEqual("testField2", false, true)
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNotEqualCollection()
    {
        final List<String> testCollection = new LinkedList<>();
        testCollection.add("xxx");
        final List<String> expectedCollection1 = new LinkedList<>();
        expectedCollection1.add("yyy");
        final List<String> expectedCollection2 = new LinkedList<>();
        expectedCollection2.add("xxx");
        expectedCollection2.add("yyy");
        Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1", testCollection, expectedCollection1)
                                                      .ifNotEqual("testField2", testCollection, expectedCollection2)
                                                      .throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNotEqualInstant()
    {
        final Instant future = Instant.MAX;
        final Instant past = Instant.MIN;
        Verifier.<Long>forChecking(VerifierTest.class).ifNotEqualDate("testField1", future, past, 5).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNotEqualLocalDate()
    {
        final LocalDateTime future = LocalDateTime.MAX;
        final LocalDateTime past = LocalDateTime.MIN;
        Verifier.<Long>forChecking(VerifierTest.class).ifNotEqualDate("testField1", future, past, 5).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNotEqualNumeric()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1", 1, 2).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNotEqualNumericWithError()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifNotEqualWithError("testField1", 1.5, 2.25, .5d).throwing(CompositeExceptionTester::new);
    }

    @Test public void testSuccess_ifNotEqualString()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifNotEqual("testField1", "x", "X", true)
                                                      .ifNotEqual("testField2", "x", "y", false)
                                                      .throwing(CompositeExceptionTester::new);
    }
}
