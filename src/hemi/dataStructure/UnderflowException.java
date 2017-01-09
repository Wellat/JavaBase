package hemi.dataStructure;

/**
 * Exception class for access in empty containers
 * such as stacks, queues, and priority queues.
 * @author Mark Allen Weiss
 */
public class UnderflowException extends RuntimeException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2275614551822341338L;

	/**
     * Construct this exception object.
     * @param message the error message.
     */
    public UnderflowException( String message )
    {
        super( message );
    }
}
