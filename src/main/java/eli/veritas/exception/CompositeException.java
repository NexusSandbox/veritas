package eli.veritas.exception;

import java.util.Collection;
import java.util.Map;
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

    protected static String joiner(final Collection<String> items)
    {
        return String.format(items.stream().collect(Collectors.joining("%n")));
    }
}
