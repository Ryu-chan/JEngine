package engine.data;

/**
 * The {@code Queue} class provides implementation for a queue data structure.
 * <P>Earlier Versions:<br/><ul>
 * <li>1.0 : 2012.12.27 - commentation fixes and added features.
 * </ul>
 * 
 * @author Christopher Kelley
 * @version 1.1
 * @since 2012.03.08
 */
public class Queue{
	//Holds the beginning of the queue
	private Node front,
	//Holds the end of the queue for ease of access
				 back;

	/**
	 * Default constructor instantiates an empty queue.
	 */
	public Queue(){
		//clear();
	}
	/**
	 * Second constructor takes an item or a series of items to add to the queue.
	 * @param items list of items to add to the queue.
	 */
	public Queue(Object... items){
		//clear();
		enqueueAll(items);
	}

	/**
	 * Adds the specified item to the queue.
	 * @param item to be added to the queue.
	 */
	public void enqueue(Object item){
		if(front==null){
			front=back=new Node(item);
		}else if(front==back){
			front.next=back=new Node(item);
		}else{
			back=back.next=new Node(item);
		}
	}
	/**
	 * Adds the list of items to the queue in the order of the list.
	 * @param items list of items to be added.
	 */
	public void enqueueAll(Object... items){
		for(Object item:items)
			enqueue(item);
	}

	/**
	 * Removes the item at the beginning of the queue and returns it.
	 * @return the item that was at the beginning of the queue.
	 */
	public Object dequeue(){
		if(front==null)throw new UnderflowException("Dequeue from empty queue");
		Object data=front.data;
		front=front.next;
		return data;
	}

	/**
	 * Reveals the item at the beginning of the queue without removing it.
	 * @return the item currently on the top of the queue.
	 */
	public Object peek(){
		if(front==null)throw new UnderflowException("Peek into empty queue");
		return front.data;
	}

	/**
	 * Gets if the queue is empty or not.
	 * @return if the queue is empty.
	 */
	public boolean isEmpty(){
		return front==null;
	}

	/**
	 * Clears the queue.
	 */
	public void clear(){
		front=back=null;
	}

	/**
	 * Overrides object's toString.
	 * @return a string representation of this queue.
	 */
	@Override
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("Queue[ Front -> ");
		for(Node node=front;node!=null;node=node.next)
			buffer.append(node.data.toString()+" -> ");
		buffer.append("Back ]");
		return buffer.toString();
	}
}