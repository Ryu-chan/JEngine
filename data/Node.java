package engine.data;

/**
 * The {@code Node} class is used as a base for many data structures.
 * <br/>See: {@see LinkedList}, {@see Stack}, {@see Queue}
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.12.24
 */
public class Node{
	/** Data held in this node */
	public Object data;
	/** Reference to another node */
	public Node next;
	/**
	 * Constructs an object with the specified data and no next node.
	 * @param _data data that the node should hold.
	 */
	public Node(Object _data){
		data = _data;
		next = null;
	}
	/**
	 * Constructs an object with the specified data and next node.
	 * @param _data data that the node should hold.
	 * @param _next node that this node should hold.
	 */
	public Node(Object _data, Node _next){
		data = _data;
		next = _next;
	}
}