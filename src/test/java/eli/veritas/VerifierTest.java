package eli.veritas;

import eli.veritas.exception.CompositeException;
import org.junit.jupiter.api.Test;

import java.util.Collection;

/**
 * Verifies workflows through the {@link Verifier}.
 */
public class VerifierTest
{
    @Test public void test()
    {
        Verifier.<Long>forChecking(VerifierTest.class).ifBlank("testField", "", 123l).throwing(CompositeExceptionTester::new);
    }

    private class CompositeExceptionTester extends CompositeException
    {
        private Collection<Long> values;

        CompositeExceptionTester(final Collection<String> messages, final Collection<Long> values)
        {
            super(messages);
            this.values = values;
        }
    }
}
