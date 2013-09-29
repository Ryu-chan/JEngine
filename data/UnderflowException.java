package engine.data;

/**
 * The {@code UnderflowException} is thrown when an element is removed from an
 * empty structure or structure component.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.12.25
 */
public class UnderflowException extends DataStructureException{
	public UnderflowException(String msg){
		super(msg);
	}
}