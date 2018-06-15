package eli.veritas.exception;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A generalized exception when validation fails. Thrown whenever a specified expectation is not met.
 *
 * @author The Architect
 */
public class CompositeException extends RuntimeException
{
    public CompositeException(final Collection<String> items)
    {
        super(joiner(items));
    }

    public CompositeException(final Map<String, Exception> items)
    {
        this(items.keySet());
        items.values().forEach(ex -> addSuppressed(ex));
    }

    public CompositeException merger(final CompositeException ex)
    {
        final List<String> messages = new LinkedList<>();
        messages.add(getMessage());
        messages.add(ex.getMessage());

        return new CompositeException(messages);
    }

    public CompositeException mergerWithExceptions(final CompositeException ex)
    {
        final Map<String, Exception> messages = new LinkedHashMap<>(2);
        messages.put(getMessage(), this);
        messages.put(ex.getMessage(), ex);

        return new CompositeException(messages);
    }

    protected static String joiner(final Collection<String> items)
    {
        return String.format(items.stream().collect(Collectors.joining("%n")));
    }
}
