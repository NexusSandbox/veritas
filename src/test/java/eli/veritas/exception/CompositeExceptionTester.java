package eli.veritas.exception;

import java.util.Collection;

/**
 * Tester {@link CompositeException} for validating various {@link Verifier} workflows.
 */
public class CompositeExceptionTester extends CompositeException
{
    private Collection<Long> values;

    public CompositeExceptionTester(final Collection<String> messages, final Collection<Long> values)
    {
        super(messages);
        this.values = values;
    }

    public Collection<Long> getValues()
    {
        return values;
    }
}
