package engine.data;

/**
 * The {@code RangeException} is thrown when an attempt is made to access an element in 
 * a structure or sub structure that is outside of its bounds.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.12.25
 */
public class RangeException extends DataStructureException{
	public RangeException(String msg){
		super(msg);
	}
	public RangeException(int index){
		super("RangeException at Index: "+index);
	}
}