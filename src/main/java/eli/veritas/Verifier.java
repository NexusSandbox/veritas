package eli.veritas;

import eli.veritas.exception.CompositeException;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * A utility class that can be used to check or assert a variety of general conditions.
 *
 * @author The Architect
 */
public class Verifier
{
    private static final String tabSpacer                   = ";\t";
    private static final String formatAssertionFailedField  = "Assertion failed for field: \"{0}#{1}\"";
    private static final String formatAssertionFailedFields = "Assertion failed for fields: \"{0}#{1}\" and \"{0}#{2}\"";

    private static final String            formatNotNull = formatAssertionFailedField + tabSpacer + "Expected object to be non-null.";
    private static final String            formatNull    = formatAssertionFailedField + tabSpacer + "Expected object to be null.";
    private static final Predicate<Object> isNull        = actual -> actual == null;

    private static final String                      formatXorNull    = formatAssertionFailedFields
                                                                        + tabSpacer
                                                                        + "Expected exactly 1 object to be null.";
    private static final String                      formatXorNotNull = formatAssertionFailedFields
                                                                        + tabSpacer
                                                                        + "Expected exactly 1 object to be non-null.";
    private static final BiPredicate<Object, Object> isXorNull        = (actual1, actual2) -> actual1 == null ^ actual2 == null;

    private static final String                      formatOrNull = formatAssertionFailedFields + tabSpacer + "Expected either object to be null.";
    private static final BiPredicate<Object, Object> isOrNull     = (actual1, actual2) -> actual1 == null || actual2 == null;

    private static final String                      formatOrNotNull = formatAssertionFailedFields
                                                                       + tabSpacer
                                                                       + "Expected either object to be non-null.";
    private static final BiPredicate<Object, Object> isOrNotNull     = (actual1, actual2) -> actual1 != null || actual2 != null;

    /**
     * @param clazz The class to validate. (Cannot be null)
     *
     * @return A new non-null {@link Checker} instance for constructing a {@link CompositeException} to validate a single {@link Class}.
     */
    public static Checker forChecking(final Class<?> clazz)
    {
        return new Checker(clazz);
    }

    public static class Checker
    {
        private final String       className;
        private final List<String> messages = new LinkedList<>();

        private Checker(final Class<?> clazz)
        {
            this.className = clazz.getSimpleName();
        }

        /**
         * Asserts that <var>actual</var> input {@link Strings#isBlank is blank}, i.e. is either null, empty, or contains only whitespace.
         * <blockquote>{@value Strings#formatBlank}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifBlank(final String fieldLabel, final String actual)
        {
            if (Verifier.Strings.isBlank.negate().test(actual))
            {
                messages.add(MessageFormat.format(Strings.formatBlank, className, fieldLabel));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Strings#isBlank is not blank}, i.e. is neither null, empty, nor contains only whitespace.
         * <blockquote>{@value Strings#formatNotBlank}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotBlank(final String fieldLabel, final String actual)
        {
            if (Verifier.Strings.isBlank.negate().test(actual))
            {
                messages.add(MessageFormat.format(Strings.formatNotBlank, className, fieldLabel));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Strings#isEmpty is empty}, i.e. is either null, or empty.
         * <blockquote>{@value Strings#formatEmpty}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifEmpty(final String fieldLabel, final String actual)
        {
            if (Verifier.Strings.isEmpty.negate().test(actual))
            {
                messages.add(MessageFormat.format(Strings.formatEmpty, className, fieldLabel));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Strings#isEmpty is not blank}, i.e. is neither null, nor empty.
         * <blockquote>{@value Strings#formatNotEmpty}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotEmpty(final String fieldLabel, final String actual)
        {
            if (Verifier.Strings.isEmpty.test(actual))
            {
                messages.add(MessageFormat.format(Strings.formatNotEmpty, className, fieldLabel));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link #isNull is null}.
         * <blockquote>{@value #formatNull}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifNull(final String fieldLabel, final T actual)

        {
            if (Verifier.isNull.negate().test(actual))
            {
                messages.add(MessageFormat.format(formatNull, className, fieldLabel));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link #isNull is not null}.
         * <blockquote>{@value #formatNotNull}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifNotNull(final String fieldLabel, final T actual)
        {
            if (Verifier.isNull.test(actual))
            {
                messages.add(MessageFormat.format(formatNotNull, className, fieldLabel));
            }

            return this;
        }

        /**
         * Asserts that either <var>actual1</var> input or <var>actual2</var> input are {@link #isXorNull null} but not both.
         * <blockquote>{@value #formatXorNull}</blockquote>
         *
         * @param field1Label The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual1     The <var>actual1</var> input to assert against the expectations.
         * @param field2Label The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual2     The <var>actual2</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public <T, U> Checker ifXorNull(final String field1Label, final T actual1, final String field2Label, final U actual2)
        {
            if (Verifier.isXorNull.negate().test(actual1, actual2))
            {
                messages.add(MessageFormat.format(formatXorNull, className, field1Label, field2Label));
            }

            return this;
        }

        /**
         * Asserts that either <var>actual1</var> input and <var>actual2</var> input {@link #isXorNull are null} or neither are null.
         * <blockquote>{@value #formatXorNotNull}</blockquote>
         *
         * @param field1Label The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual1     The <var>actual1</var> input to assert against the expectations.
         * @param field2Label The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual2     The <var>actual2</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public <T, U> Checker ifXorNotNull(final String field1Label, final T actual1, final String field2Label, final U actual2)
        {
            if (Verifier.isXorNull.test(actual1, actual2))
            {
                messages.add(MessageFormat.format(formatXorNotNull, className, field1Label, field2Label));
            }

            return this;
        }

        /**
         * Asserts that either <var>actual1</var> input or <var>actual2</var> input {@link #isOrNull are null}.
         * <blockquote>{@value #formatOrNull}</blockquote>
         *
         * @param field1Label The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual1     The <var>actual1</var> input to assert against the expectations.
         * @param field2Label The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual2     The <var>actual2</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public <T, U> Checker ifOrNull(final String field1Label, final T actual1, final String field2Label, final U actual2)
        {
            if (Verifier.isOrNull.negate().test(actual1, actual2))
            {
                messages.add(MessageFormat.format(formatOrNull, className, field1Label, field2Label));
            }

            return this;
        }

        /**
         * Asserts that neither <var>actual1</var> input nor <var>actual2</var> input {@link #isOrNotNull are null}.
         * <blockquote>{@value #formatOrNotNull}</blockquote>
         *
         * @param field1Label The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual1     The <var>actual1</var> input to assert against the expectations.
         * @param field2Label The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual2     The <var>actual2</var> input to assert against the expectations.
         *
         * @return This non-null {@link Checker}.
         */
        public <T, U> Checker ifOrNotNull(final String field1Label, final T actual1, final String field2Label, final U actual2)
        {
            if (Verifier.isOrNotNull.negate().test(actual1, actual2))
            {
                messages.add(MessageFormat.format(formatOrNotNull, className, field1Label, field2Label));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Collections#isEmpty is empty}.
         * <blockquote>{@value Collections#formatEmptyCollection}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param <T>        The type of {@link Collection} to check.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifEmpty(final String fieldLabel, final Collection<T> actual)
        {
            if (Verifier.Collections.isEmpty.negate().test(actual))
            {
                messages.add(MessageFormat.format(Collections.formatEmptyCollection, className, fieldLabel, actual));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Collections#isEmpty is not empty}.
         * <blockquote>{@value Collections#formatNotEmptyCollection}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param <T>        The type of {@link Collection} to check.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifNotEmpty(final String fieldLabel, final Collection<T> actual)
        {
            if (Verifier.Collections.isEmpty.test(actual))
            {
                messages.add(MessageFormat.format(Collections.formatNotEmptyCollection, className, fieldLabel));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Collections#containsAllValues(Collection, Collection) contains all values} of the
         * <var>expected</var> {@link Collection}.
         * <blockquote>{@value Collections#formatContainsAllValues}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> values to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifContainsAllValues(final String fieldLabel, final Collection<T> actual, final Collection<T> expected)
        {
            if (!Verifier.Collections.containsAllValues(actual, expected))
            {
                messages.add(MessageFormat.format(Collections.formatContainsAllValues, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Collections#containsAnyValues(Collection, Collection) contains any values} of the
         * <var>expected</var> {@link Collection}.
         * <blockquote>{@value Collections#formatContainsAnyValues}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> values to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifContainsAnyValues(final String fieldLabel, final Collection<T> actual, final Collection<T> expected)
        {
            if (!Verifier.Collections.containsAnyValues(actual, expected))
            {
                messages.add(MessageFormat.format(Collections.formatContainsAnyValues, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Collections#containsNoValues(Collection, Collection) contains no values} of the
         * <var>expected</var> {@link Collection}.
         * <blockquote>{@value Collections#formatContainsNoValues}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> values to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifContainsNoValues(final String fieldLabel, final Collection<T> actual, final Collection<T> expected)
        {
            if (!Verifier.Collections.containsNoValues(actual, expected))
            {
                messages.add(MessageFormat.format(Collections.formatContainsNoValues, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Collections#matchesAllValues(Collection, Predicate) matches all values} of the
         * <var>expected</var> {@link Predicate lambda}.
         * <blockquote>{@value Collections#formatMatchesAllValues}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param matcher    The <var>expected</var> matcher to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifMatchesAllValues(final String fieldLabel, final Collection<T> actual, final Predicate<T> matcher)
        {
            if (!Verifier.Collections.matchesAllValues(actual, matcher))
            {
                messages.add(MessageFormat.format(Collections.formatMatchesAllValues, className, fieldLabel, actual, matcher));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Collections#matchesAnyValues(Collection, Predicate) matches any values} of the
         * <var>expected</var> {@link Predicate lambda}.
         * <blockquote>{@value Collections#formatMatchesAnyValues}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param matcher    The <var>expected</var> matcher to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifMatchesAnyValues(final String fieldLabel, final Collection<T> actual, final Predicate<T> matcher)
        {
            if (!Verifier.Collections.matchesAnyValues(actual, matcher))
            {
                messages.add(MessageFormat.format(Collections.formatMatchesAnyValues, className, fieldLabel, actual, matcher));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Collections#matchesNoValues(Collection, Predicate) matches no values} of the
         * <var>expected</var> {@link Predicate lambda}.
         * <blockquote>{@value Collections#formatMatchesNoValues}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param matcher    The <var>expected</var> matcher to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifMatchesNoValues(final String fieldLabel, final Collection<T> actual, final Predicate<T> matcher)
        {
            if (!Verifier.Collections.matchesNoValues(actual, matcher))
            {
                messages.add(MessageFormat.format(Collections.formatMatchesNoValues, className, fieldLabel, actual, matcher));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualBoolean is equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifEqual(final String fieldLabel, final boolean actual, final boolean expected)
        {
            if (Equality.isEqualBoolean.negate().test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualBoolean is not equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatNotEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotEqual(final String fieldLabel, final boolean actual, final boolean expected)
        {
            if (Equality.isEqualBoolean.test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatNotEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualCharacter is equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifEqual(final String fieldLabel, final char actual, final char expected)
        {
            if (Equality.isEqualCharacter.negate().test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualCharacter is not equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatNotEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotEqual(final String fieldLabel, final char actual, final char expected)
        {
            if (Equality.isEqualCharacter.test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatNotEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualInteger is equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifEqual(final String fieldLabel, final int actual, final int expected)
        {
            if (Equality.isEqualInteger.negate().test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualInteger is not equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatNotEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotEqual(final String fieldLabel, final int actual, final int expected)
        {
            if (Equality.isEqualInteger.test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatNotEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualShort is equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifEqual(final String fieldLabel, final short actual, final short expected)
        {
            if (Equality.isEqualShort.negate().test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualShort is not equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatNotEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotEqual(final String fieldLabel, final short actual, final short expected)
        {
            if (Equality.isEqualShort.test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatNotEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualLong is equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifEqual(final String fieldLabel, final long actual, final long expected)
        {
            if (Equality.isEqualLong.negate().test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualLong is not equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatNotEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotEqual(final String fieldLabel, final long actual, final long expected)
        {
            if (Equality.isEqualLong.test(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatNotEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualDouble is equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatEqualWithError}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifEqual(final String fieldLabel, final double actual, final double expected, final double epsilon)
        {
            if (Equality.isEqualDouble(actual, expected, epsilon))
            {
                messages.add(MessageFormat.format(Equality.formatEqualWithError, className, fieldLabel, actual, expected, epsilon));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualDouble is not equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatNotEqualWithError}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotEqual(final String fieldLabel, final double actual, final double expected, final double epsilon)
        {
            if (Equality.isEqualDouble(actual, expected, epsilon))
            {
                messages.add(MessageFormat.format(Equality.formatNotEqualWithError, className, fieldLabel, actual, expected, epsilon));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualStringCaseSensitive is equal to} the <var>expected</var> input if
         * <var>caseSensitive</var> is true, otherwise asserts that <var>actual</var> input {@link Equality#isEqualStringCaseInsensitive is equal to}
         * the <var>expected</var> input.
         * <blockquote>{@value Equality#formatEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifEqual(final String fieldLabel, final String actual, final String expected, final boolean caseSensitive)
        {
            if ((caseSensitive && Equality.isEqualStringCaseSensitive.negate().test(actual, expected)) || (!caseSensitive
                                                                                                           && Equality.isEqualStringCaseInsensitive.negate()
                                                                                                                                                   .test(actual,
                                                                                                                                                         expected)))
            {
                messages.add(MessageFormat.format(Equality.formatEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualStringCaseSensitive is not equal to} the <var>expected</var> input if
         * <var>caseSensitive</var> is true, otherwise asserts that <var>actual</var> input {@link Equality#isEqualStringCaseInsensitive is not equal
         * to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatNotEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotEqual(final String fieldLabel, final String actual, final String expected, final boolean caseSensitive)
        {
            if ((caseSensitive && Equality.isEqualStringCaseSensitive.test(actual, expected)) || (!caseSensitive
                                                                                                  && Equality.isEqualStringCaseInsensitive.test(actual,
                                                                                                                                                expected)))
            {
                messages.add(MessageFormat.format(Equality.formatNotEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualCollection is equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifEqual(final String fieldLabel, final Collection<T> actual, final Collection<T> expected)
        {
            if (!Equality.isEqualCollection(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualLong is not equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatNotEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifNotEqual(final String fieldLabel, final Collection<T> actual, final Collection<T> expected)
        {
            if (Equality.isEqualCollection(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatNotEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualCollection is equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifEqual(final String fieldLabel, final T actual, final T expected)
        {
            if (!Equality.isEqualObject(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Equality#isEqualLong is not equal to} the <var>expected</var> input.
         * <blockquote>{@value Equality#formatNotEqual}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param expected   The <var>expected</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public <T> Checker ifNotEqual(final String fieldLabel, final T actual, final T expected)
        {
            if (Equality.isEqualObject(actual, expected))
            {
                messages.add(MessageFormat.format(Equality.formatNotEqual, className, fieldLabel, actual, expected));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Strings#isWithinMaxLength is within max character length} of the <var>maxLength</var> input.
         * <blockquote>{@value Strings#formatIsWithinMaxLength}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param maxLength  The <var>maxLength</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifWithinMaxLength(final String fieldLabel, final String actual, final int maxLength)
        {
            if (Strings.isWithinMaxLength.negate().test(actual, maxLength))
            {
                messages.add(MessageFormat.format(Strings.formatIsWithinMaxLength,
                                                  className,
                                                  fieldLabel,
                                                  actual != null ? actual.length() : 0,
                                                  maxLength));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Strings#isWithinMaxLength is not within max character length} of the <var>maxLength</var>
         * input.
         * <blockquote>{@value Strings#formatIsNotWithinMaxLength}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param maxLength  The <var>maxLength</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotWithinMaxLength(final String fieldLabel, final String actual, final int maxLength)
        {
            if (Strings.isWithinMaxLength.test(actual, maxLength))
            {
                messages.add(MessageFormat.format(Strings.formatIsNotWithinMaxLength,
                                                  className,
                                                  fieldLabel,
                                                  actual != null ? actual.length() : 0,
                                                  maxLength));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Strings#matches matches} the regular expression <var>pattern</var> input.
         * <blockquote>{@value Strings#formatMatches}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param pattern    The regular expression <var>pattern</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifMatches(final String fieldLabel, final String actual, final String pattern)
        {
            if (Strings.matches.negate().test(actual, pattern))
            {
                messages.add(MessageFormat.format(Strings.formatMatches, className, fieldLabel, actual, pattern));
            }

            return this;
        }

        /**
         * Asserts that <var>actual</var> input {@link Strings#matches does not match} the regular expression <var>pattern</var> input.
         * <blockquote>{@value Strings#formatNotMatches}</blockquote>
         *
         * @param fieldLabel The field's name to display as part of the diagnostic message. (Cannot be blank)
         * @param actual     The <var>actual</var> input to assert against the expectations.
         * @param pattern    The regular expression <var>pattern</var> value to compare against.
         *
         * @return This non-null {@link Checker}.
         */
        public Checker ifNotMatches(final String fieldLabel, final String actual, final String pattern)
        {
            if (Strings.matches.test(actual, pattern))
            {
                messages.add(MessageFormat.format(Strings.formatNotMatches, className, fieldLabel, actual, pattern));
            }

            return this;
        }

        /**
         * Assembles the collection of any messages accumulated by the {@link Verifier}. If any of the checked values failed, this will construct a
         * new {@link CompositeException} with the {@link Collection} of failed diagnostic messages.
         *
         * @param exceptionConstructor An {@link Function exception constructor} that consumes a {@link Collection} of {@link String} values, and
         *                             returns a newly initialized {@link CompositeException}. (Cannot be null)
         * @param <AE>                 A {@link CompositeException} to throw.
         */
        public <AE extends CompositeException> void throwing(final Function<Collection<String>, AE> exceptionConstructor)
        {
            if (!messages.isEmpty())
            {
                throw exceptionConstructor.apply(messages);
            }
        }
    }

    private static class Collections
    {
        private static final String formatContainsAllValues  = Verifier.formatAssertionFailedField
                                                               + Verifier.tabSpacer
                                                               + "Expected collection[{2}] to contain all values of collection[{3}].";
        private static final String formatContainsAnyValues  = Verifier.formatAssertionFailedField
                                                               + Verifier.tabSpacer
                                                               + "Expected collection[{2}] to contain any values of collection[{3}].";
        private static final String formatContainsNoValues   = Verifier.formatAssertionFailedField
                                                               + Verifier.tabSpacer
                                                               + "Expected collection[{2}] to contain no values of collection[{3}].";
        private static final String formatMatchesAllValues   = Verifier.formatAssertionFailedField
                                                               + Verifier.tabSpacer
                                                               + "Expected collection[{2}] to match all values of collection[{3}].";
        private static final String formatMatchesAnyValues   = Verifier.formatAssertionFailedField
                                                               + Verifier.tabSpacer
                                                               + "Expected collection[{2}] to match any values of collection[{3}].";
        private static final String formatMatchesNoValues    = Verifier.formatAssertionFailedField
                                                               + Verifier.tabSpacer
                                                               + "Expected collection[{2}] to matches no values of collection[{3}].";
        private static final String formatEmptyCollection    = Verifier.formatAssertionFailedField
                                                               + Verifier.tabSpacer
                                                               + "Expected collection[{2}] to be empty, or null.";
        private static final String formatNotEmptyCollection = Verifier.formatAssertionFailedField
                                                               + Verifier.tabSpacer
                                                               + "Expected collection to not be empty, or null.";

        private static final Predicate<Collection<?>> isEmpty = actual -> actual == null || actual.isEmpty();

        private static <T> boolean containsAllValues(final Collection<T> actual, final Collection<T> expected)
        {
            if (isEmpty.test(actual))
                return isEmpty.test(expected);
            else if (isEmpty.test(expected))
                return true;
            return actual.containsAll(expected);
        }

        private static <T> boolean containsAnyValues(final Collection<T> actual, final Collection<T> expected)
        {
            if (isEmpty.test(actual))
                return isEmpty.test(expected);
            else if (isEmpty.test(expected))
                return true;
            return actual.stream().anyMatch(v -> expected.contains(v));
        }

        private static <T> boolean containsNoValues(final Collection<T> actual, final Collection<T> expected)
        {
            return !containsAnyValues(actual, expected);
        }

        private static <T> boolean matchesAllValues(final Collection<T> actual, final Predicate<T> matcher)
        {
            if (isEmpty.test(actual))
                return true;
            else if (matcher == null)
                return true;
            return actual.stream().allMatch(matcher);
        }

        private static <T> boolean matchesAnyValues(final Collection<T> actual, final Predicate<T> matcher)
        {
            if (isEmpty.test(actual))
                return true;
            else if (matcher == null)
                return true;
            return actual.stream().anyMatch(matcher);
        }

        private static <T> boolean matchesNoValues(final Collection<T> actual, final Predicate<T> matcher)
        {
            return !matchesAnyValues(actual, matcher);
        }
    }

    private static class Equality
    {
        private static final String formatEqual    = Verifier.formatAssertionFailedField + Verifier.tabSpacer + "Actual[{2}] ≡ Expected[{3}]";
        private static final String formatNotEqual = Verifier.formatAssertionFailedField + Verifier.tabSpacer + "Actual[{2}] ≠ Expected[{3}]";

        private static final BiPredicate<Boolean, Boolean>     isEqualBoolean               = (actual, expected) -> actual == expected;
        private static final BiPredicate<Character, Character> isEqualCharacter             = (actual, expected) -> actual == expected;
        private static final BiPredicate<Integer, Integer>     isEqualInteger               = (actual, expected) -> {
            if (expected == null)
                return actual == null;
            return expected.equals(actual);
        };
        private static final BiPredicate<Long, Long>           isEqualLong                  = (actual, expected) -> {
            if (expected == null)
                return actual == null;
            return expected.equals(actual);
        };
        private static final BiPredicate<Short, Short>         isEqualShort                 = (actual, expected) -> {
            if (expected == null)
                return actual == null;
            return expected.equals(actual);
        };
        private static final BiPredicate<String, String> isEqualStringCaseSensitive   = (actual, expected) -> {
            if (expected == null)
                return actual == null;
            return expected.equals(actual);
        };
        private static final BiPredicate<String, String> isEqualStringCaseInsensitive = (actual, expected) -> {
            if (expected == null)
                return actual == null;
            return expected.equalsIgnoreCase(actual);
        };
        private static final String                      formatEqualWithError         = Verifier.formatAssertionFailedField
                                                                                        + Verifier.tabSpacer
                                                                                        + "Actual[{2}] ± ε[{4}] ≡ Expected[{3}]";
        private static final String                      formatNotEqualWithError      = Verifier.formatAssertionFailedField
                                                                                        + Verifier.tabSpacer
                                                                                        + "Actual[{2}] ± ε[{4}] ≠ Expected[{3}]";

        private static <T> boolean isEqualCollection(final Collection<T> actual, final Collection<T> expected)
        {
            if (expected == null)
                return actual == null;
            return expected.equals(actual);
        }

        private static <T> boolean isEqualObject(final T actual, final T expected)
        {
            if (expected == null)
                return actual == null;
            return expected.equals(actual);
        }

        private static boolean isEqualDouble(final double actual, final double expected, final double epsilon)
        {
            return Math.abs(expected - actual) < epsilon;
        }
    }

    private static class Inequality
    {
        public static final  BiPredicate<Short, Short>     isGreaterThanShort                = (actual, expected) -> actual > expected;
        public static final  BiPredicate<Short, Short>     isGreaterThanOrEqualShort         = (actual, expected) -> actual >= expected;
        public static final  BiPredicate<Short, Short>     isLessThanShort                   = (actual, expected) -> actual < expected;
        public static final  BiPredicate<Short, Short>     isLessThanOrEqualShort            = (actual, expected) -> actual <= expected;
        public static final  BiPredicate<Integer, Integer> isGreaterThanInteger              = (actual, expected) -> actual > expected;
        public static final  BiPredicate<Integer, Integer> isGreaterThanOrEqualInteger       = (actual, expected) -> actual >= expected;
        public static final  BiPredicate<Integer, Integer> isLessThanInteger                 = (actual, expected) -> actual < expected;
        public static final  BiPredicate<Integer, Integer> isLessThanOrEqualInteger          = (actual, expected) -> actual <= expected;
        public static final  BiPredicate<Long, Long>       isGreaterThanLong                 = (actual, expected) -> actual > expected;
        public static final  BiPredicate<Long, Long>       isGreaterThanOrEqualLong          = (actual, expected) -> actual >= expected;
        public static final  BiPredicate<Long, Long>       isLessThanLong                    = (actual, expected) -> actual < expected;
        public static final  BiPredicate<Long, Long>       isLessThanOrEqualLong             = (actual, expected) -> actual <= expected;
        private static final String                        formatGreaterThan                 = Verifier.formatAssertionFailedField
                                                                                               + Verifier.tabSpacer
                                                                                               + "Actual[{1}] > Expected[{2}]";
        private static final String                        formatGreaterThanWithError        = Verifier.formatAssertionFailedField
                                                                                               + Verifier.tabSpacer
                                                                                               + "Actual[{1}] ± ε[{3}] > Expected[{2}]";
        private static final String                        formatGreaterThanOrEqual          = Verifier.formatAssertionFailedField
                                                                                               + Verifier.tabSpacer
                                                                                               + "Actual[{1}] ≥ Expected[{2}]";
        private static final String                        formatGreaterThanOrEqualWithError = Verifier.formatAssertionFailedField
                                                                                               + Verifier.tabSpacer
                                                                                               + "Actual[{1}] ± ε[{3}] ≥ Expected[{2}]";
        private static final String                        formatLessThan                    = Verifier.formatAssertionFailedField
                                                                                               + Verifier.tabSpacer
                                                                                               + "Actual[{1}] < Expected[{2}]";
        private static final String                        formatLessThanWithError           = Verifier.formatAssertionFailedField
                                                                                               + Verifier.tabSpacer
                                                                                               + "Actual[{1}] ± ε[{3}] < Expected[{2}]";
        private static final String                        formatLessThanOrEqual             = Verifier.formatAssertionFailedField
                                                                                               + Verifier.tabSpacer
                                                                                               + "Actual[{1}] ≤ Expected{2}";
        private static final String                        formatLessThanOrEqualWithError    = Verifier.formatAssertionFailedField
                                                                                               + Verifier.tabSpacer
                                                                                               + "Actual[{1}] ± ε[{3}] ≤ Expected{2}";

        public static boolean isGreaterThanWithError(final float actual, final float bound, final float epsilon)
        {
            return actual - epsilon > bound;
        }

        public static boolean isGreaterThanOrEqualWithError(final float actual, final float bound, final float epsilon)
        {
            return actual + epsilon >= bound;
        }

        public static boolean isLessThanWithError(final float actual, final float bound, final float epsilon)
        {
            return actual + epsilon < bound;
        }

        public static boolean isLessThanOrEqualWithError(final float actual, final float bound, final float epsilon)
        {
            return actual - epsilon <= bound;
        }

        public static boolean isGreaterThanWithError(final double actual, final double bound, final double epsilon)
        {
            return actual - epsilon > bound;
        }

        public static boolean isGreaterThanOrEqualError(final double actual, final double bound, final double epsilon)
        {
            return actual + epsilon >= bound;
        }

        public static boolean isLessThanWithError(final double actual, final double bound, final double epsilon)
        {
            return actual + epsilon < bound;
        }

        public static boolean isLessThanOrEqualWithError(final double actual, final double bound, final double epsilon)
        {
            return actual - epsilon <= bound;
        }
    }

    private static class Ranges
    {
        private static final String formatInsideRange                     = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Lower Bound[{2}] ≤ Actual[{1}] < Upper Bound[{3}]";
        private static final String formatInsideRangeWithError            = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Lower Bound[{2}] ≤ Actual[{1}] ± ε[{4}] < Upper Bound[{3}]";
        private static final String formatInsideRange_Exclusive           = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Lower Bound[{2}] < Actual[{1}] < Upper Bound[{3}]";
        private static final String formatInsideRangeWithError_Exclusive  = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Lower Bound[{2}] < Actual[{1}] ± ε[{4}] < Upper Bound[{3}]";
        private static final String formatInsideRange_Inclusive           = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Lower Bound[{2}] ≤ Actual[{1}] ≤ Upper Bound[{3}]";
        private static final String formatInsideRangeWithError_Inclusive  = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Lower Bound[{2}] ≤ Actual[{1}] ± ε[{4}] ≤ Upper Bound[{3}]";
        private static final String formatOutsideRange                    = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Actual[{1}] < Lower Bound[{2}] ‖ Actual[{1}] ≥ Upper Bound[{3}]";
        private static final String formatOutsideRangeWithError           = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Actual[{1}] ± ε[{4}] < Lower Bound[{2}] ‖ Actual[{1}] ± ε[{4}] ≥ Upper Bound[{3}]";
        private static final String formatOutsideRange_Exclusive          = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Actual[{1}] < Lower Bound[{2}] ‖ Actual[{1}] > Upper Bound[{3}]";
        private static final String formatOutsideRangeWithError_Exclusive = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Actual[{1}] ± ε[{4}] < Lower Bound[{2}] ‖ Actual[{1}] ± ε[{4}] > Upper Bound[{3}]";
        private static final String formatOutsideRange_Inclusive          = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Actual[{1}] ≤ Lower Bound[{2}] ‖ Actual[{1}] ≥ Upper Bound[{3}]";
        private static final String formatOutsideRangeWithError_Inclusive = Verifier.formatAssertionFailedField
                                                                            + Verifier.tabSpacer
                                                                            + "Actual[{1}] ± ε[{4}] ≤ Lower Bound[{2}] ‖ Actual[{1}] ± ε[{4}] ≥ Upper Bound[{3}]";
    }

    private static class Strings
    {
        private static final Pattern patternBlank   = Pattern.compile("^\\s$", Pattern.DOTALL);
        private static final String  formatBlank    = Verifier.formatAssertionFailedField
                                                      + Verifier.tabSpacer + "Expected string[\"{2}\"] to be blank, empty, or null.";
        private static final String  formatNotBlank = Verifier.formatAssertionFailedField
                                                      + Verifier.tabSpacer
                                                      + "Expected string to not be blank, empty, or null.";

        private static final Predicate<String> isBlank = actual -> actual == null || patternBlank.matcher(actual).matches();

        private static final String formatEmpty    = Verifier.formatAssertionFailedField
                                                     + Verifier.tabSpacer
                                                     + "Expected string[\"{2}\"] to be empty, or null.";
        private static final String formatNotEmpty = Verifier.formatAssertionFailedField
                                                     + Verifier.tabSpacer
                                                     + "Expected string to not be empty, or null.";

        private static final Predicate<String> isEmpty = actual -> actual == null || actual.isEmpty();

        private static final String formatIsWithinMaxLength    = Verifier.formatAssertionFailedField
                                                                 + Verifier.tabSpacer
                                                                 + "Expected string length[{2}] to be within length[{3}].";
        private static final String formatIsNotWithinMaxLength = Verifier.formatAssertionFailedField
                                                                 + Verifier.tabSpacer
                                                                 + "Expected string length[{2}] to exceed length[{3}].";

        private static final BiPredicate<String, Integer> isWithinMaxLength = (actual, maxLength) -> {
            if (actual == null)
                return true;
            return actual.length() <= maxLength;
        };

        private static final String formatMatches    = Verifier.formatAssertionFailedField
                                                       + Verifier.tabSpacer
                                                       + "Expected string[\"{2}\"] to match pattern[\"{3}\"]";
        private static final String formatNotMatches = Verifier.formatAssertionFailedField
                                                       + Verifier.tabSpacer
                                                       + "Expected string[\"{2}\"] to not match pattern[\"{3}\"]";

        private static final BiPredicate<String, String> matches = (actual, pattern) -> {
            if (actual == null)
                return false;
            return actual.matches(pattern);
        };
    }
}