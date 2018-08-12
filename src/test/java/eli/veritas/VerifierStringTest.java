package eli.veritas;

import eli.veritas.exception.CompositeExceptionTester;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Verifies workflows through the {@link Verifier} for generic {@link String} checks.
 */
public class VerifierStringTest {

  private static final long TEST_VALUE1 = 123l;
  private static final long TEST_VALUE2 = 234l;
  private static final long TEST_VALUE3 = 234l;

  @Test
  public void testFailureWithValue_ifBlank() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifBlank("testField1",
            "xxx",
            TEST_VALUE1)
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"xxx\"] to be blank, empty, or null.",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    expectedValues.add(TEST_VALUE1);
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailureWithValue_ifEmpty() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifEmpty("testField1",
            "xxx",
            TEST_VALUE1)
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"xxx\"] to be empty, or null.",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    expectedValues.add(TEST_VALUE1);
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailureWithValue_ifMatches() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifMatches("testField1",
            "1984-Jan-23",
            "\\d{4}-\\d{2}-\\d{2}",
            TEST_VALUE1)
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"1984-Jan-23\"] to match pattern[\"\\d{4}-\\d{2}-\\d{2}\"].",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    expectedValues.add(TEST_VALUE1);
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailureWithValue_ifNotBlank() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotBlank("testField1",
            null,
            TEST_VALUE1)
            .ifNotBlank("testField2",
                "",
                TEST_VALUE2)
            .ifNotBlank("testField3",
                " ",
                TEST_VALUE3)
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(String.format(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string to not be blank, empty, or null.%n"
            + "Assertion failed for field: \"VerifierTest#testField2\";\tExpected string to not be blank, empty, or null.%n"
            + "Assertion failed for field: \"VerifierTest#testField3\";\tExpected string to not be blank, empty, or null."),
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    expectedValues.add(TEST_VALUE1);
    expectedValues.add(TEST_VALUE2);
    expectedValues.add(TEST_VALUE3);
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailureWithValue_ifNotEmpty() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEmpty("testField1",
            (String) null,
            TEST_VALUE1)
            .ifNotEmpty("testField2",
                "",
                TEST_VALUE2)
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(String.format(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string to not be empty, or null.%n"
            + "Assertion failed for field: \"VerifierTest#testField2\";\tExpected string to not be empty, or null."),
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    expectedValues.add(TEST_VALUE1);
    expectedValues.add(TEST_VALUE2);
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailureWithValue_ifNotMatches() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotMatches("testField1",
            "1984-01-23",
            "\\d{4}-\\d{2}-\\d{2}",
            TEST_VALUE1)
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"1984-01-23\"] to not match pattern[\"\\d{4}-\\d{2}-\\d{2}\"].",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    expectedValues.add(TEST_VALUE1);
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailureWithValue_ifNotWithinMaxLength() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotWithinMaxLength(
            "testField1",
            "x",
            1,
            TEST_VALUE1).throwing(CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"x\"] length[1] to exceed length[1].",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    expectedValues.add(TEST_VALUE1);
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailureWithValue_ifWithinMaxLength() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifWithinMaxLength(
            "testField1",
            "xx",
            1,
            TEST_VALUE1).throwing(CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"xx\"] length[2] to be within length[1].",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    expectedValues.add(TEST_VALUE1);
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailure_ifBlank() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifBlank("testField1", "xxx")
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"xxx\"] to be blank, empty, or null.",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailure_ifEmpty() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifEmpty("testField1", "xxx")
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"xxx\"] to be empty, or null.",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailure_ifMatches() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifMatches("testField1",
            "1984-Jan-23",
            "\\d{4}-\\d{2}-\\d{2}")
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"1984-Jan-23\"] to match pattern[\"\\d{4}-\\d{2}-\\d{2}\"].",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailure_ifNotBlank() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotBlank("testField1",
            null)
            .ifNotBlank("testField2", "")
            .ifNotBlank("testField3", " ")
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(String.format(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string to not be blank, empty, or null.%n"
            + "Assertion failed for field: \"VerifierTest#testField2\";\tExpected string to not be blank, empty, or null.%n"
            + "Assertion failed for field: \"VerifierTest#testField3\";\tExpected string to not be blank, empty, or null."),
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailure_ifNotEmpty() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotEmpty("testField1",
            (String) null)
            .ifNotEmpty("testField2", "")
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(String.format(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string to not be empty, or null.%n"
            + "Assertion failed for field: \"VerifierTest#testField2\";\tExpected string to not be empty, or null."),
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailure_ifNotMatches() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotMatches("testField1",
            "1984-01-23",
            "\\d{4}-\\d{2}-\\d{2}")
            .throwing(
                CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"1984-01-23\"] to not match pattern[\"\\d{4}-\\d{2}-\\d{2}\"].",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailure_ifNotWithinMaxLength() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifNotWithinMaxLength(
            "testField1",
            "x",
            1).throwing(CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"x\"] length[1] to exceed length[1].",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testFailure_ifWithinMaxLength() {
    final CompositeExceptionTester ex = Assertions.assertThrows(CompositeExceptionTester.class,
        () -> Verifier.<Long>forChecking(VerifierTest.class).ifWithinMaxLength(
            "testField1",
            "xx",
            1).throwing(CompositeExceptionTester::new));

    Assertions.assertEquals(
        "Assertion failed for field: \"VerifierTest#testField1\";\tExpected string[\"xx\"] length[2] to be within length[1].",
        ex.getMessage(),
        "Unexpected exception message");
    final List<Long> expectedValues = new LinkedList<>();
    Assertions.assertEquals(expectedValues, ex.getValues(), "Unexpected exception values");
  }

  @Test
  public void testSuccess_ifBlank() {
    Verifier.<Long>forChecking(VerifierTest.class).ifBlank("testField1", null)
        .ifBlank("testField1", "")
        .ifBlank("testField1", " ")
        .ifBlank("testField1", "\r\n")
        .throwing(CompositeExceptionTester::new);
  }

  @Test
  public void testSuccess_ifEmpty() {
    Verifier.<Long>forChecking(VerifierTest.class).ifEmpty("testField1", (String) null)
        .ifEmpty("testField1", "")
        .throwing(CompositeExceptionTester::new);
  }

  @Test
  public void testSuccess_ifMatches() {
    Verifier.<Long>forChecking(VerifierTest.class)
        .ifMatches("testField1", "1984-01-23", "\\d{4}-\\d{2}-\\d{2}")
        .throwing(CompositeExceptionTester::new);
  }

  @Test
  public void testSuccess_ifNotBlank() {
    Verifier.<Long>forChecking(VerifierTest.class).ifNotBlank("testField1", "x")
        .throwing(CompositeExceptionTester::new);
  }

  @Test
  public void testSuccess_ifNotEmpty() {
    Verifier.<Long>forChecking(VerifierTest.class).ifNotEmpty("testField1", "x")
        .ifNotEmpty("testField1", " ")
        .ifNotEmpty("testField1", "\r\n")
        .throwing(CompositeExceptionTester::new);
  }

  @Test
  public void testSuccess_ifNotMatches() {
    Verifier.<Long>forChecking(VerifierTest.class)
        .ifNotMatches("testField1", "1984-Jan-23", "\\d{4}-\\d{2}-\\d{2}")
        .throwing(CompositeExceptionTester::new);
  }

  @Test
  public void testSuccess_ifNotWithinMaxLength() {
    Verifier.<Long>forChecking(VerifierTest.class).ifNotWithinMaxLength("testField1", "xx", 1)
        .throwing(CompositeExceptionTester::new);
  }

  @Test
  public void testSuccess_ifWithinMaxLength() {
    Verifier.<Long>forChecking(VerifierTest.class).ifWithinMaxLength("testField1", "x", 1)
        .throwing(CompositeExceptionTester::new);
  }
}
