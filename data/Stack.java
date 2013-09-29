package engine.data;

/**
 * The {@code Stack} class provides implementation for a stack data structure.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.12.27
 */
public class Stack{
	//Holds the top of the stack
	private Node top;

	/**
	 * Default constructor instantiates an empty stack.
	 */
	public Stack(){
		//clear();
	}
	/**
	 * Second constructor takes an item or a series of items to add to the new stack.
	 * @param items list of items to add to the stack.
	 */
	public Stack(Object... items){
		//clear();
		pushAll(items);
	}

	/**
	 * Adds the specified item to the top of the stack.
	 * @param item to be added to the stack.
	 */
	public void push(Object item){
		top=new Node(item,top);
	}
	/**
	 * Adds the list of items to the stack in the order of the list.
	 * @param items list of items to be added.
	 */
	public void pushAll(Object... items){
		for(Object item:items)
			push(item);
	}

	/**
	 * Removes the item on top of the stack and returns it.
	 * @return the item that was on the top of the stack.
	 */
	public Object pop(){
		if(top==null)throw new UnderflowException("Pop from empty stack");
		Object data=top.data;
		top=top.next;
		return data;
	}
	/**
	 * Reveals the item on the top of the stack without removing it.
	 * @return the item currently on the top of the stack.
	 */
	public Object peek(){
		if(top==null)throw new UnderflowException("Peek into empty stack");
		return top.data;
	}

	/**
	 * Gets if the stack is empty or not.
	 * @return if the list is empty.
	 */
	public boolean isEmpty(){
		return top==null;
	}

	/**
	 * Clears the stack.
	 */
	public void clear(){
		top=null;
	}

	/**
	 * Overrides object's toString.
	 * @return a string representation of this stack.
	 */
	@Override
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("Stack[ Top -> ");
		for(Node node=top;node!=null;node=node.next)
			buffer.append(node.data.toString()+" -> ");
		buffer.append("End ]");
		return buffer.toString();
	}
}