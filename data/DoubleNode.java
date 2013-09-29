package engine.data;

/**
 * The {@code DoubleNode} class is used as a base for many data structures and objects.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.12.28
 */
class DoubleNode{
	/** Data held in this node */
	Object data;
	/** Reference to another node */
	DoubleNode next,
	/** Reference to another node */
				prev;
	/**
	 * Constructs an object with the specified data and no next or previous node.
	 * @param _data data that the node should hold.
	 */
	public DoubleNode(Object _data){
		data = _data;
		next = null;
	}
	/**
	 * Constructs an object with the specified data and next node and no previous node.
	 * @param _data data that the node should hold.
	 * @param _next node that this node should hold.
	 */
	public DoubleNode(Object _data, DoubleNode _next){
		data = _data;
		next = _next;
	}
	/**
	 * Constructs an object with the specified data, next node, and previous node.
	 * @param _data data that the node should hold.
	 * @param _next node that this node should hold.
	 * @param _prev node that this node should hold.
	 */
	public DoubleNode(Object _data, DoubleNode _next, DoubleNode _prev){
		data = _data;
		next = _next;
		prev = _prev;
	}
}