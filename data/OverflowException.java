package engine.data;

/**
 * The {@code OverflowException} is thrown when an element is added to a structure or 
 * sub structure that goes over that strcutures bounds.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.12.25
 */
public class OverflowException extends DataStructureException{
	public OverflowException(String msg){
		super(msg);
	}
}