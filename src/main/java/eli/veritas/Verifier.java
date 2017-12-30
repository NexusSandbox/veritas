package eli.veritas;

import java.text.MessageFormat;
import java.util.Collection;

import eli.veritas.exception.AssertionException;

/**
 * A utility class that can be used to check or assert a variety of general conditions.
 *
 * @author The Architect
 */
public class Verifier
{
    public static class Collections
    {
        /**
         * {@value #messageFormat_ContainsAllValues}
         */
        private static final String messageFormat_ContainsAllValues  = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected collection <{1}> to contain all values of <{2}>.";
        /**
         * {@value #messageFormat_ContainsAnyValues}
         */
        private static final String messageFormat_ContainsAnyValues  = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected collection <{1}> to contain any values of <{2}>.";
        /**
         * {@value #messageFormat_ContainsNoValue}
         */
        private static final String messageFormat_ContainsNoValue    = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected collection <{1}> to not contain value <{2}>.";
        /**
         * {@value #messageFormat_ContainsNoValues}
         */
        private static final String messageFormat_ContainsNoValues   = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected collection <{1}> to contain no values of <{2}>.";
        /**
         * {@value #messageFormat_ContainsValue}
         */
        private static final String messageFormat_ContainsValue      = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected collection <{1}> to contain value <{2}>.";
        /**
         * {@value #messageFormat_EmptyArray}
         */
        private static final String messageFormat_EmptyArray         = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected array <{1}> to be empty, or null.";
        /**
         * {@value #messageFormat_EmptyCollection}
         */
        private static final String messageFormat_EmptyCollection    = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected collection <{1}> to be empty, or null.";
        /**
         * {@value #messageFormat_NotEmptyArray}
         */
        private static final String messageFormat_NotEmptyArray      = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected array to not be empty, or null.";
        /**
         * {@value #messageFormat_NotEmptyCollection}
         */
        private static final String messageFormat_NotEmptyCollection = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected collection to not be empty, or null.";

        /**
         * Asserts that <VAR>actual</VAR> input {@link #containsAllValues(Collection, Object...) contains all values} of the <VAR>expected</VAR>
         * array, otherwise throws an {@link AssertionException} formatted to include the provided diagnostic <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsAllValues}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An array of expected values to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        @SafeVarargs
        public static <T> void assertContainsAllValues(final String message, final Collection<T> actual, final T ... expected)
        {
            if (!Verifier.Collections.containsAllValues(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsAllValues, message, actual, expected));
            }
        }

        /**
         * Asserts that <VAR>actual</VAR> input {@link #containsAllValues(Object[], Object...) contains all values} of the <VAR>expected</VAR> array,
         * otherwise throws an {@link AssertionException} formatted to include the provided diagnostic <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsAllValues}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An array of expected values to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        @SafeVarargs
        public static <T> void assertContainsAllValues(final String message, final T[] actual, final T ... expected)
        {
            if (!Verifier.Collections.containsAllValues(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsAllValues, message, actual, expected));
            }
        }

        /**
         * Asserts that <VAR>actual</VAR> input {@link #containsAnyValues(Collection, Object...) contains any values} of the <VAR>expected</VAR>
         * array, otherwise throws an {@link AssertionException} formatted to include the provided diagnostic <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsAnyValues}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An array of expected values to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        @SafeVarargs
        public static <T> void assertContainsAnyValues(final String message, final Collection<T> actual, final T ... expected)
        {
            if (!Verifier.Collections.containsAnyValues(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsAnyValues, message, actual, expected));
            }
        }

        /**
         * Asserts that <VAR>actual</VAR> input {@link #containsAnyValues(Object[], Object...) contains any values} of the <VAR>expected</VAR> array,
         * otherwise throws an {@link AssertionException} formatted to include the provided diagnostic <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsAnyValues}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An array of expected values to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        @SafeVarargs
        public static <T> void assertContainsAnyValues(final String message, final T[] actual, final T ... expected)
        {
            if (!Verifier.Collections.containsAnyValues(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsAnyValues, message, actual, expected));
            }
        }

        /**
         * Asserts that <VAR>actual</VAR> input does {@link #containsNoValue(Collection, Object) contain the value} of the <VAR>expected</VAR>,
         * otherwise throws an {@link AssertionException} formatted to include the provided diagnostic <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsNoValue}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertContainsNoValue(final String message, final Collection<T> actual, final T expected)
        {
            if (!Verifier.Collections.containsNoValue(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsNoValue, message, actual, expected));
            }
        }

        /**
         * Asserts that <VAR>actual</VAR> input does {@link #containsNoValue(Object[], Object) contain the value} of the <VAR>expected</VAR>,
         * otherwise throws an {@link AssertionException} formatted to include the provided diagnostic <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsNoValue}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertContainsNoValue(final String message, final T[] actual, final T expected)
        {
            if (!Verifier.Collections.containsNoValue(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsNoValue, message, actual, expected));
            }
        }

        /**
         * Asserts that <VAR>actual</VAR> input does <B>not</B> {@link #containsAnyValues(Collection, Object...) contain any value} of the
         * <VAR>expected</VAR> array, otherwise throws an {@link AssertionException} formatted to include the provided diagnostic
         * <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsNoValues}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An array of expected values to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        @SafeVarargs
        public static <T> void assertContainsNoValues(final String message, final Collection<T> actual, final T ... expected)
        {
            if (!Verifier.Collections.containsNoValues(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsNoValues, message, actual, expected));
            }
        }

        /**
         * Asserts that <VAR>actual</VAR> input does <B>not</B> {@link #containsAnyValues(Object[], Object...) contain any value} of the
         * <VAR>expected</VAR> array, otherwise throws an {@link AssertionException} formatted to include the provided diagnostic
         * <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsNoValues}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An array of expected values to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        @SafeVarargs
        public static <T> void assertContainsNoValues(final String message, final T[] actual, final T ... expected)
        {
            if (!Verifier.Collections.containsNoValues(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsNoValues, message, actual, expected));
            }
        }

        /**
         * Asserts that <VAR>actual</VAR> input {@link #containsValue(Collection, Object) contains the value} of the <VAR>expected</VAR>, otherwise
         * throws an {@link AssertionException} formatted to include the provided diagnostic <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsValue}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertContainsValue(final String message, final Collection<T> actual, final T expected)
        {
            if (!Verifier.Collections.containsValue(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsValue, message, actual, expected));
            }
        }

        /**
         * Asserts that <VAR>actual</VAR> input {@link #containsValue(Object[], Object) contains the value} of the <VAR>expected</VAR>, otherwise
         * throws an {@link AssertionException} formatted to include the provided diagnostic <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_ContainsValue}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertContainsValue(final String message, final T[] actual, final T expected)
        {
            if (!Verifier.Collections.containsValue(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_ContainsValue, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertEmpty(final String message, final Collection<T> actual)
        {
            if (!Verifier.Collections.isEmpty(actual))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_EmptyCollection, message, actual));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        @SafeVarargs
        public static <T> void assertEmpty(final String message, final T ... actual)
        {
            if (!Verifier.Collections.isEmpty(actual))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_EmptyArray, message, actual));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertNotEmpty(final String message, final Collection<T> actual)
        {
            if (!Verifier.Collections.isNotEmpty(actual))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_NotEmptyCollection, message));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        @SafeVarargs
        public static <T> void assertNotEmpty(final String message, final T ... actual)
        {
            if (!Verifier.Collections.isNotEmpty(actual))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Collections.messageFormat_NotEmptyArray, message));
            }
        }

        @SafeVarargs
        public static <T> boolean containsAllValues(final Collection<T> actual, final T ... expected)
        {
            if (actual == null || actual.isEmpty())
            {
                return expected == null || expected.length == 0;
            }
            else if (expected == null || expected.length == 0)
            {
                return true;
            }

            boolean containsAll = true;
            for (int i = 0; i < expected.length && containsAll; i++)
            {
                containsAll = actual.contains(expected[i]);
            }

            return containsAll;
        }

        @SafeVarargs
        public static <T> boolean containsAllValues(final T[] actual, final T ... expected)
        {
            if (actual == null || actual.length == 0)
            {
                return expected == null || expected.length == 0;
            }
            else if (expected == null || expected.length == 0)
            {
                return true;
            }

            boolean containsAll = true;
            for (int i = 0; i < expected.length && containsAll; i++)
            {
                boolean found = false;
                for (final T actualElement : actual)
                {
                    if (Verifier.Equality.isEqual(actualElement, expected[i]))
                    {
                        found = true;
                        break;
                    }
                }
                containsAll = found;
            }

            return containsAll;
        }

        @SafeVarargs
        public static <T> boolean containsAnyValues(final Collection<T> actual, final T ... expected)
        {
            if (actual == null || actual.isEmpty())
            {
                return false;
            }

            boolean containsAny = false;
            for (int i = 0; i < expected.length && !containsAny; i++)
            {
                containsAny = actual.contains(expected[i]);
            }

            return containsAny;
        }

        @SafeVarargs
        public static <T> boolean containsAnyValues(final T[] actual, final T ... expected)
        {
            if (actual == null || actual.length == 0)
            {
                return expected == null || expected.length == 0;
            }
            else if (expected == null || expected.length == 0)
            {
                return true;
            }

            boolean containsAny = false;
            for (int i = 0; i < expected.length && !containsAny; i++)
            {
                for (final T actualElement : actual)
                {
                    if (Verifier.Equality.isEqual(actualElement, expected[i]))
                    {
                        containsAny = true;
                        break;
                    }
                }
            }

            return containsAny;
        }

        public static <T> boolean containsNoValue(final Collection<T> actual, final T expected)
        {
            if (actual == null || actual.isEmpty())
            {
                return true;
            }

            return !actual.contains(expected);
        }

        public static <T> boolean containsNoValue(final T[] actual, final T expected)
        {
            if (actual == null || actual.length == 0)
            {
                return true;
            }

            boolean contains = true;
            for (final T actualElement : actual)
            {
                if (Verifier.Equality.isEqual(actualElement, expected))
                {
                    contains = false;
                    break;
                }
            }

            return contains;
        }

        @SafeVarargs
        public static <T> boolean containsNoValues(final Collection<T> actual, final T ... expected)
        {
            if (actual == null || actual.isEmpty())
            {
                return true;
            }

            boolean containsNone = true;
            for (int i = 0; i < expected.length && containsNone; i++)
            {
                containsNone = !actual.contains(expected[i]);
            }

            return containsNone;
        }

        @SafeVarargs
        public static <T> boolean containsNoValues(final T[] actual, final T ... expected)
        {
            if (actual == null || actual.length == 0)
            {
                return expected != null && expected.length != 0;
            }
            else if (expected == null || expected.length == 0)
            {
                return false;
            }

            boolean containsNone = true;
            for (int i = 0; i < expected.length && containsNone; i++)
            {
                for (final T actualElement : actual)
                {
                    if (Verifier.Equality.isEqual(actualElement, expected[i]))
                    {
                        containsNone = false;
                        break;
                    }
                }
            }

            return containsNone;
        }

        public static <T> boolean containsValue(final Collection<T> actual, final T expected)
        {
            if (actual == null || actual.isEmpty())
            {
                return false;
            }

            return actual.contains(expected);
        }

        public static <T> boolean containsValue(final T[] actual, final T expected)
        {
            if (actual == null || actual.length == 0)
            {
                return false;
            }

            boolean contains = false;
            for (final T actualElement : actual)
            {
                if (Verifier.Equality.isEqual(actualElement, expected))
                {
                    contains = true;
                    break;
                }
            }

            return contains;
        }

        public static <T> boolean isEmpty(final Collection<T> actual)
        {
            return actual == null || actual.isEmpty();
        }

        public static <T> boolean isEmpty(final T ... actual)
        {
            return actual == null || actual.length == 0;
        }

        public static <T> boolean isNotEmpty(final Collection<T> actual)
        {
            return actual != null && !actual.isEmpty();
        }

        public static <T> boolean isNotEmpty(final T ... actual)
        {
            return actual != null && actual.length != 0;
        }

    }

    public static class Equality
    {

        /**
         * {@value #messageFormat_Equal}
         */
        private static final String messageFormat_Equal          = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ≡ Expected[{2}]";
        /**
         * {@value #messageFormat_EqualWithError}
         */
        private static final String messageFormat_EqualWithError = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ± ε[{3}] ≡ Expected[{2}]";

        /**
         * {@value #messageFormat_NotEqual}
         */
        private static final String messageFormat_NotEqual = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ≠ Expected[{2}]";

        /**
         * {@value #messageFormat_NotEqualWithError}
         */
        private static final String messageFormat_NotEqualWithError = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ± ε[{3}] ≠ Expected[{2}]";

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertEqual(final String message, final boolean actual, final boolean expected)
        {
            if (!Verifier.Equality.isEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_Equal, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertEqual(final String message, final char actual, final char expected)
        {
            if (!Verifier.Equality.isEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_Equal, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertEqual(final String message, final Collection<T> actual, final Collection<T> expected)
        {
            if (!Verifier.Equality.isEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_Equal, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertEqual(final String message, final double actual, final double expected, final double epsilon)
        {
            if (!Verifier.Equality.isEqual(actual, expected, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_EqualWithError,
                                                                  message,
                                                                  actual,
                                                                  expected,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertEqual(final String message, final long actual, final long expected)
        {
            if (!Verifier.Equality.isEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_Equal, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @param ignoreCase
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertEqual(final String message, final String actual, final String expected, final boolean ignoreCase)
        {
            if (!Verifier.Equality.isEqual(actual, expected, ignoreCase))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_Equal, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertEqual(final String message, final T actual, final T expected)
        {
            if (!Verifier.Equality.isEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_Equal, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertEqual(final String message, final T[] actual, final T[] expected)
        {
            if (!Verifier.Equality.isEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_Equal, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertNotEqual(final String message, final boolean actual, final boolean expected)
        {
            if (!Verifier.Equality.isNotEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_NotEqual, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertNotEqual(final String message, final char actual, final char expected)
        {
            if (!Verifier.Equality.isNotEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_NotEqual, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertNotEqual(final String message, final Collection<T> actual, final Collection<T> expected)
        {
            if (!Verifier.Equality.isNotEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_NotEqual, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertNotEqual(final String message, final double actual, final double expected, final double epsilon)
        {
            if (!Verifier.Equality.isNotEqual(actual, expected, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_NotEqualWithError,
                                                                  message,
                                                                  actual,
                                                                  expected,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertNotEqual(final String message, final long actual, final long expected)
        {
            if (!Verifier.Equality.isNotEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_NotEqual, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @param ignoreCase
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertNotEqual(final String message, final String actual, final String expected, final boolean ignoreCase)
        {
            if (!Verifier.Equality.isNotEqual(actual, expected, ignoreCase))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_NotEqual, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertNotEqual(final String message, final T actual, final T expected)
        {
            if (!Verifier.Equality.isNotEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_NotEqual, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static <T> void assertNotEqual(final String message, final T[] actual, final T[] expected)
        {
            if (!Verifier.Equality.isNotEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Equality.messageFormat_NotEqual, message, actual, expected));
            }
        }

        public static boolean isEqual(final boolean actual, final boolean expected)
        {
            return expected == actual;
        }

        public static boolean isEqual(final char actual, final char expected)
        {
            return expected == actual;
        }

        public static <T> boolean isEqual(final Collection<T> actual, final Collection<T> expected)
        {
            if (expected == null)
            {
                return actual == null;
            }

            return expected.equals(actual);
        }

        public static boolean isEqual(final double actual, final double expected, final double epsilon)
        {
            return Math.abs(expected - actual) < epsilon;
        }

        public static boolean isEqual(final long actual, final long expected)
        {
            return expected == actual;
        }

        public static boolean isEqual(final String actual, final String expected, final boolean ignoreCase)
        {
            if (expected == null)
            {
                return actual == null;
            }

            if (ignoreCase)
            {
                return expected.equalsIgnoreCase(actual);
            }
            return expected.equals(actual);
        }

        public static <T> boolean isEqual(final T actual, final T expected)
        {
            if (expected == null)
            {
                return expected == actual;
            }

            return expected.equals(actual);
        }

        public static <T> boolean isEqual(final T[] actual, final T[] expected)
        {
            if (expected == null || actual == null)
            {
                return actual == expected;
            }
            if (expected.length != actual.length)
            {
                return false;
            }
            int index = 0;
            for (final T expectedElement : expected)
            {
                if (expectedElement == null)
                {
                    if (expectedElement != actual[index])
                    {
                        return false;
                    }
                }
                else if (!expectedElement.equals(actual[index]))
                {
                    return false;
                }
                index++;
            }

            return true;
        }

        public static boolean isNotEqual(final boolean actual, final boolean expected)
        {
            return expected != actual;
        }

        public static boolean isNotEqual(final char actual, final char expected)
        {
            return expected != actual;
        }

        public static <T> boolean isNotEqual(final Collection<T> actual, final Collection<T> expected)
        {
            if (expected == null)
            {
                return actual != null;
            }

            return !expected.equals(actual);
        }

        public static boolean isNotEqual(final double actual, final double expected, final double epsilon)
        {
            return Math.abs(expected - actual) >= epsilon;
        }

        public static boolean isNotEqual(final long actual, final long expected)
        {
            return expected != actual;
        }

        public static boolean isNotEqual(final String actual, final String expected, final boolean ignoreCase)
        {
            if (expected == null)
            {
                return actual != null;
            }

            if (ignoreCase)
            {
                return !expected.equalsIgnoreCase(actual);
            }
            return !expected.equals(actual);
        }

        public static <T> boolean isNotEqual(final T actual, final T expected)
        {
            if (expected == null)
            {
                return expected != actual;
            }

            return !expected.equals(actual);
        }

        public static <T> boolean isNotEqual(final T[] actual, final T[] expected)
        {
            if (expected == null || actual == null)
            {
                return actual != expected;
            }
            if (expected.length != actual.length)
            {
                return true;
            }
            int index = 0;
            for (final T expectedElement : expected)
            {
                if (expectedElement == null)
                {
                    if (expectedElement != actual[index])
                    {
                        return true;
                    }
                }
                else if (!expectedElement.equals(actual[index]))
                {
                    return true;
                }
                index++;
            }

            return false;
        }

    }

    public static class Inequality
    {

        /**
         * {@value #messageFormat_GreaterThan}
         */
        private static final String messageFormat_GreaterThan = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] > Expected[{2}]";

        /**
         * {@value #messageFormat_GreaterThanOrEqual}
         */
        private static final String messageFormat_GreaterThanOrEqual = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ≥ Expected[{2}]";

        /**
         * {@value #messageFormat_GreaterThanOrEqualWithError}
         */
        private static final String messageFormat_GreaterThanOrEqualWithError = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ± ε[{3}] ≥ Expected[{2}]";

        /**
         * {@value #messageFormat_GreaterThanWithError}
         */
        private static final String messageFormat_GreaterThanWithError = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ± ε[{3}] > Expected[{2}]";

        /**
         * {@value #messageFormat_LessThan}
         */
        private static final String messageFormat_LessThan = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] < Expected[{2}]";

        /**
         * {@value #messageFormat_LessThanOrEqual}
         */
        private static final String messageFormat_LessThanOrEqual = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ≤ Expected{2}";

        /**
         * {@value #messageFormat_LessThanOrEqualWithError}
         */
        private static final String messageFormat_LessThanOrEqualWithError = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ± ε[{3}] ≤ Expected{2}";

        /**
         * {@value #messageFormat_LessThanWithError}
         */
        private static final String messageFormat_LessThanWithError = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ± ε[{3}] < Expected[{2}]";

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertGreaterThan(final String message, final double actual, final double expected, final double epsilon)
        {
            if (!Verifier.Inequality.isGreaterThan(actual, expected, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Inequality.messageFormat_GreaterThanWithError,
                                                                  message,
                                                                  actual,
                                                                  expected,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertGreaterThan(final String message, final long actual, final long expected)
        {
            if (!Verifier.Inequality.isGreaterThan(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Inequality.messageFormat_GreaterThan, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertGreaterThanOrEqual(final String message, final double actual, final double expected, final double epsilon)
        {
            if (!Verifier.Inequality.isGreaterThanOrEqual(actual, expected, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Inequality.messageFormat_GreaterThanOrEqualWithError,
                                                                  message,
                                                                  actual,
                                                                  expected,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertGreaterThanOrEqual(final String message, final long actual, final long expected)
        {
            if (!Verifier.Inequality.isGreaterThanOrEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Inequality.messageFormat_GreaterThanOrEqual, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertLessThan(final String message, final double actual, final double expected, final double epsilon)
        {
            if (!Verifier.Inequality.isLessThan(actual, expected, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Inequality.messageFormat_LessThanWithError,
                                                                  message,
                                                                  actual,
                                                                  expected,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertLessThan(final String message, final long actual, final long expected)
        {
            if (!Verifier.Inequality.isLessThan(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Inequality.messageFormat_LessThan, message, actual, expected));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertLessThanOrEqual(final String message, final double actual, final double expected, final double epsilon)
        {
            if (!Verifier.Inequality.isLessThanOrEqual(actual, expected, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Inequality.messageFormat_LessThanOrEqualWithError,
                                                                  message,
                                                                  actual,
                                                                  expected,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param expected An expected value to compare against.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertLessThanOrEqual(final String message, final long actual, final long expected)
        {
            if (!Verifier.Inequality.isLessThanOrEqual(actual, expected))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Inequality.messageFormat_LessThanOrEqual, message, actual, expected));
            }
        }

        public static boolean isGreaterThan(final double actual, final double expected, final double epsilon)
        {
            return actual + epsilon > expected;
        }

        public static boolean isGreaterThan(final long actual, final long expected)
        {
            return actual > expected;
        }

        public static boolean isGreaterThanOrEqual(final double actual, final double expected, final double epsilon)
        {
            return actual - epsilon >= expected;
        }

        public static boolean isGreaterThanOrEqual(final long actual, final long expected)
        {
            return actual >= expected;
        }

        public static boolean isLessThan(final double actual, final double expected, final double epsilon)
        {
            return actual - epsilon < expected;
        }

        public static boolean isLessThan(final long actual, final long expected)
        {
            return actual < expected;
        }

        public static boolean isLessThanOrEqual(final double actual, final double expected, final double epsilon)
        {
            return actual <= expected + epsilon;
        }

        public static boolean isLessThanOrEqual(final long actual, final long expected)
        {
            return actual <= expected;
        }

    }

    public static class Ranges
    {

        /**
         * {@value #messageFormat_InsideRange}
         */
        private static final String messageFormat_InsideRange = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Lower Bound[{2}] ≤ Actual[{1}] < Upper Bound[{3}]";

        /**
         * {@value #messageFormat_InsideRangeWithError}
         */
        private static final String messageFormat_InsideRangeWithError = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Lower Bound[{2}] ≤ Actual[{1}] ± ε[{4}] < Upper Bound[{3}]";

        /**
         * {@value #messageFormat_OutsideRange}
         */
        private static final String messageFormat_OutsideRange = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] < Lower Bound[{2}] ‖ Actual[{1}] ≥ Upper Bound[{3}]";

        /**
         * {@value #messageFormat_OutsideRangeWithError}
         */
        private static final String messageFormat_OutsideRangeWithError = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Actual[{1}] ± ε[{4}] < Lower Bound[{2}] ‖ Actual[{1}] ± ε[{4}] ≥ Upper Bound[{3}]";

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertInsideRange(final String message, final double actual, final double lowerBound, final double upperBound,
                                             final double epsilon)
        {
            if (!Verifier.Ranges.isInsideRange(actual, lowerBound, upperBound, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_InsideRangeWithError,
                                                                  actual,
                                                                  lowerBound,
                                                                  upperBound,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertInsideRange(final String message, final long actual, final long lowerBound, final long upperBound)
        {
            if (!Verifier.Ranges.isInsideRange(actual, lowerBound, upperBound))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_InsideRange, actual, lowerBound, upperBound));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertInsideRange_Exclusive(final String message, final double actual, final double lowerBound, final double upperBound,
                                                       final double epsilon)
        {
            if (!Verifier.Ranges.isInsideRange_Exclusive(actual, lowerBound, upperBound, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_InsideRangeWithError,
                                                                  actual,
                                                                  lowerBound,
                                                                  upperBound,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertInsideRange_Exclusive(final String message, final long actual, final long lowerBound, final long upperBound)
        {
            if (!Verifier.Ranges.isInsideRange_Exclusive(actual, lowerBound, upperBound))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_InsideRange, actual, lowerBound, upperBound));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertInsideRange_Inclusive(final String message, final double actual, final double lowerBound, final double upperBound,
                                                       final double epsilon)
        {
            if (!Verifier.Ranges.isInsideRange_Inclusive(actual, lowerBound, upperBound, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_InsideRangeWithError,
                                                                  actual,
                                                                  lowerBound,
                                                                  upperBound,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertInsideRange_Inclusive(final String message, final long actual, final long lowerBound, final long upperBound)
        {
            if (!Verifier.Ranges.isInsideRange_Inclusive(actual, lowerBound, upperBound))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_InsideRange, actual, lowerBound, upperBound));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertOutsideRange(final String message, final double actual, final double lowerBound, final double upperBound,
                                              final double epsilon)
        {
            if (!Verifier.Ranges.isOutsideRange(actual, lowerBound, upperBound, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_OutsideRangeWithError,
                                                                  actual,
                                                                  lowerBound,
                                                                  upperBound,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertOutsideRange(final String message, final long actual, final long lowerBound, final long upperBound)
        {
            if (!Verifier.Ranges.isOutsideRange(actual, lowerBound, upperBound))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_OutsideRange, actual, lowerBound, upperBound));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertOutsideRange_Exclusive(final String message, final double actual, final double lowerBound, final double upperBound,
                                                        final double epsilon)
        {
            if (!Verifier.Ranges.isOutsideRange_Exclusive(actual, lowerBound, upperBound, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_OutsideRangeWithError,
                                                                  actual,
                                                                  lowerBound,
                                                                  upperBound,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertOutsideRange_Exclusive(final String message, final long actual, final long lowerBound, final long upperBound)
        {
            if (!Verifier.Ranges.isOutsideRange_Exclusive(actual, lowerBound, upperBound))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_OutsideRange, actual, lowerBound, upperBound));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @param epsilon
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertOutsideRange_Inclusive(final String message, final double actual, final double lowerBound, final double upperBound,
                                                        final double epsilon)
        {
            if (!Verifier.Ranges.isOutsideRange_Inclusive(actual, lowerBound, upperBound, epsilon))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_OutsideRangeWithError,
                                                                  actual,
                                                                  lowerBound,
                                                                  upperBound,
                                                                  epsilon));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @param lowerBound
         * @param upperBound
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertOutsideRange_Inclusive(final String message, final long actual, final long lowerBound, final long upperBound)
        {
            if (!Verifier.Ranges.isOutsideRange_Inclusive(actual, lowerBound, upperBound))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Ranges.messageFormat_OutsideRange, actual, lowerBound, upperBound));
            }
        }

        public static boolean isInsideRange(final double actual, final double lowerBound, final double upperBound, final double epsilon)
        {
            return actual - epsilon >= lowerBound && actual - epsilon < upperBound;
        }

        public static boolean isInsideRange(final long actual, final long lowerBound, final long upperBound)
        {
            return actual >= lowerBound && actual < upperBound;
        }

        public static boolean isInsideRange_Exclusive(final double actual, final double lowerBound, final double upperBound, final double epsilon)
        {
            return actual + epsilon > lowerBound && actual - epsilon < upperBound;
        }

        public static boolean isInsideRange_Exclusive(final long actual, final long lowerBound, final long upperBound)
        {
            return actual > lowerBound && actual < upperBound;
        }

        public static boolean isInsideRange_Inclusive(final double actual, final double lowerBound, final double upperBound, final double epsilon)
        {
            return actual - epsilon >= lowerBound && actual + epsilon <= upperBound;
        }

        public static boolean isInsideRange_Inclusive(final long actual, final long lowerBound, final long upperBound)
        {
            return actual >= lowerBound && actual <= upperBound;
        }

        public static boolean isOutsideRange(final double actual, final double lowerBound, final double upperBound, final double epsilon)
        {
            return actual - epsilon < lowerBound || actual - epsilon >= upperBound;
        }

        public static boolean isOutsideRange(final long actual, final long lowerBound, final long upperBound)
        {
            return actual < lowerBound || actual >= upperBound;
        }

        public static boolean isOutsideRange_Exclusive(final double actual, final double lowerBound, final double upperBound, final double epsilon)
        {
            return actual - epsilon < lowerBound || actual + epsilon > upperBound;
        }

        public static boolean isOutsideRange_Exclusive(final long actual, final long lowerBound, final long upperBound)
        {
            return actual < lowerBound || actual > upperBound;
        }

        public static boolean isOutsideRange_Inclusive(final double actual, final double lowerBound, final double upperBound, final double epsilon)
        {
            return actual + epsilon <= lowerBound || actual - epsilon >= upperBound;
        }

        public static boolean isOutsideRange_Inclusive(final long actual, final long lowerBound, final long upperBound)
        {
            return actual <= lowerBound || actual >= upperBound;
        }

    }

    public static class Strings
    {

        /**
         * {@value #messageFormat_BlankString}
         */
        private static final String messageFormat_BlankString = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected string <{1}> to be blank, empty, or null.";
        /**
         * {@value #messageFormat_EmptyString}
         */
        private static final String messageFormat_EmptyString = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected string <{1}> to be empty, or null.";

        /**
         * {@value #messageFormat_NotBlankString}
         */
        private static final String messageFormat_NotBlankString = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected string <{1}> to not be blank, empty, or null.";

        /**
         * {@value #messageFormat_NotEmptyString}
         */
        private static final String messageFormat_NotEmptyString = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected string to not be empty, or null.";

        /**
         * Asserts that <VAR>actual</VAR> input {@link #isBlank(String) is blank}, otherwise throws an {@link AssertionException} formatted to include
         * the provided diagnostic <VAR>message</VAR>:<br />
         * <BLOCKQUOTE>{@value #messageFormat_BlankString}</BLOCKQUOTE>
         *
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertBlank(final String message, final String actual)
        {
            if (!Verifier.Strings.isBlank(actual))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Strings.messageFormat_BlankString, message, actual));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertEmpty(final String message, final String actual)
        {
            if (!Verifier.Strings.isEmpty(actual))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Strings.messageFormat_EmptyString, message, actual));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertNotBlank(final String message, final String actual)
        {
            if (Verifier.Strings.isBlank(actual))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Strings.messageFormat_NotBlankString, message, actual));
            }
        }

        /**
         * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
         * @param actual The <VAR>actual</VAR> input to assert against the expectations.
         * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
         */
        public static void assertNotEmpty(final String message, final String actual)
        {
            if (Verifier.Strings.isEmpty(actual))
            {
                throw new AssertionException(MessageFormat.format(Verifier.Strings.messageFormat_NotEmptyString, message));
            }
        }

        public static boolean isBlank(final String actual)
        {
            return actual == null || actual.isEmpty() || actual.matches("^\\s+$");
        }

        public static boolean isEmpty(final String actual)
        {
            return actual == null || actual.isEmpty();
        }

        public static boolean isNotBlank(final String actual)
        {
            return actual != null && !actual.isEmpty() && !actual.matches("^\\s+$");
        }

        public static boolean isNotEmpty(final String actual)
        {
            return actual != null && !actual.isEmpty();
        }

    }

    private static final String linefeedTab = "\r\n\t";

    /**
     * {@value #messageFormat_AssertionFailed}
     */
    private static final String messageFormat_AssertionFailed = "Assertion failed: {0}";

    /**
     * {@value #messageFormat_NotNull}
     */
    private static final String messageFormat_NotNull = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected object to be non-null.";

    /**
     * {@value #messageFormat_Null}
     */
    private static final String messageFormat_Null = Verifier.messageFormat_AssertionFailed + Verifier.linefeedTab + "Expected object to be null.";

    /**
     * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
     * @param actual The <VAR>actual</VAR> input to assert against the expectations.
     * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
     */
    public static <T> void assertNotNull(final String message, final T actual)
    {
        if (!Verifier.isNotNull(actual))
        {
            throw new AssertionException(MessageFormat.format(Verifier.messageFormat_NotNull, message));
        }
    }

    /**
     * @param message A textual diagnostic message to display if the input does not match expectations (May be empty, but not null).
     * @param actual The <VAR>actual</VAR> input to assert against the expectations.
     * @throws AssertionException If the <VAR>actual</VAR> input does NOT match the expectations.
     */
    public static <T> void assertNull(final String message, final T actual)
    {
        if (!Verifier.isNull(actual))
        {
            throw new AssertionException(MessageFormat.format(Verifier.messageFormat_Null, message));
        }
    }

    public static <T> boolean isNotNull(final T actual)
    {
        return actual != null;
    }

    public static <T> boolean isNull(final T actual)
    {
        return actual == null;
    }
}