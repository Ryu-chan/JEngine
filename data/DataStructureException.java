package engine.data;

/**
 * The {@code DataStructureException} is the parent class to 
 * several common exceptions in data sructures.<br/>
 * See: {@see UnderflowException}, {@see OverflowException}, {@see RangeException}.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.12.25
 */
public class DataStructureException extends RuntimeException{
	public DataStructureException(String msg){
		super(msg);
	}
}