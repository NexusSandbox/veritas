package eli.veritas.exception;

/**
 * A generalized exception when validation fails. Thrown whenever a specified expectation is not met.
 *
 * @author The Architect
 */
public class AssertionException extends RuntimeException
{
    private static final long serialVersionUID = 5804100742879174356L;

    public AssertionException(final String message)
    {
        super(message);
    }

    public AssertionException(final Exception ex)
    {
        super(ex);
    }

    public AssertionException(final String message, final Exception ex)
    {
        super(message, ex);
    }
}
