package engine.data;

/**
 * The {@code RingStructure} class provides implementation for a 
 * ring structure data object.
 * @author Christopher Kelley
 * @version 1.0
 * @sicne 2012.12.28
 */
 public class RingStructure{
 	//Holds the current node in the buffer
 	private DoubleNode current;

 	/**
 	 * Default constructor instantiates an empty buffer.
 	 */
 	public RingStructure(){
 		//clear();
 	}
 	/**
 	 * Second constructor takes an item or a series of items to add to the new buffer.
 	 * @param items list of items to add to the buffer.
 	 */
 	public RingStructure(Object... items){
 		//clear();
 		addAll(items);
 	}

 	/**
 	 * Adds the specified item after the current item.
 	 * @param item to be added to the buffer.
 	 */
 	public void add(Object item){
 		addAfter(item);
 	}
 	/**
 	 * Adds the specified item after the current item.
 	 * @param item to be added to the buffer.
 	 */
 	public void addAfter(Object item){
 		if(current==null){
 			current=new DoubleNode(item);
 			current.prev=current.next=current;
 		}else{
 			current.next=new DoubleNode(item,current.next,current);
 		}
 	}
 	/**
 	 * Adds the specified item before the current item.
 	 * @param item to be added to the buffer.
 	 */
 	public void addBefore(Object item){
 		if(current==null){
 			current=new DoubleNode(item);
 			current.prev=current.next=current;
 		}else{
 			current.prev=new DoubleNode(item,current,current.prev);
 		}
 	}
 	/**
 	 * Adds the list of items after the current node.
 	 * @param items list of items to be added to the buffer.
 	 */
 	public void addAll(Object... items){
 		for(Object item:items){
 			add(item);
 		}
 	}

 	/**
 	 * Removes the current node from the buffer and returns its contents.<P>
 	 * This has the effect of moving forward as well.
 	 * @return contents of the previous current node.
 	 */
 	public Object remove(){
 		return removeForward();
 	}
 	/**
 	 * Removes the current node from the buffer and moves forward.
 	 * @return contents of the previous current node.
 	 */
 	public Object removeForward(){
 		if(current==null)throw new UnderflowException("Remove by forward from empty buffer");
 		Object data;
 		if(current==current.next){
 			data=current.data;
 			current=null;
 		}else{
	 		current.next.prev=current.prev;
	 		current.prev.next=current.next;
	 		data=current.data;
	 		current=current.next;
	 	}
 		return data;
 	}
 	/**
 	 * Removes the current node from the buffer and moves backward.
 	 * @return contents of the previous current node.
 	 */
 	public Object removeBackward(){
 		if(current==null)throw new UnderflowException("Remove by backward from empty buffer");
 		Object data;
 		if(current==current.next){
 			data=current.data;
 			current=null;
 		}else{
	 		current.next.prev=current.prev;
	 		current.prev.next=current.next;
	 		data=current.data;
	 		current=current.prev;
	 	}
 		return data;
 	}

 	/**
 	 * Gets if the buffer is empty or not.
 	 * @return if the list is empty.
 	 */
 	public boolean isEmtpy(){
 		return current==null;
 	}

 	/**
	 * Clears the buffer.
	 */
 	public void clear(){
 		current=null;
	}

	/**
	 * Searches the array for the given item and returns how far away it is in the forward direction.
	 * @return the number of places forward away from the current node the search item is, -1 if not in the list.
	 */
	public int search(Object item){
		return searchForward(item);
	}
	/**
	 * Searches the array for the given item and returns how far away it is in the forward direction.
	 * @return the number of places forward away from the current node the search item is, -1 if not in the list.
	 */
	public int searchForward(Object item){
		int cnt=-1;
		for(DoubleNode node=current.next;node!=current;node=node.next,cnt++)
			if(item.equals(node.data))
				break;
		return cnt;
	}
	/**
	 * Searches the array for the given item and returns how far away it is in the backward direction.
	 * @return the number of places backward away from the current node the search item is, -1 if not in the list.
	 */
	public int searchBackward(Object item){
		int cnt=-1;
		for(DoubleNode node=current.prev;node!=current;node=node.prev,cnt++)
			if(item.equals(node.data))
				break;
		return cnt;
	}

	/**
	 * Moves the buffer forward one step.
	 */
	public void forward(){
		current=current.next;
	}
	/**
	 * Moves the buffer backward one step.
	 */
	public void backward(){
		current=current.prev;
	}
	/**
	 * Returns the current item.
	 * @return data of the current place in the buffer.
	 */
	public Object get(){
		if(current==null)throw new UnderflowException("Get from an empty buffer");
		return current.data;
	}

	/**
	 * Overrides object's toString
	 * @return a string representation of this list.
	 */
	@Override
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("RingBuffer[ Current -> ");
		buffer.append(current.data.toString());
		for(DoubleNode node=current.next;node!=current;node=node.next)
			buffer.append(node.data.toString()+" -> ");
		buffer.append("Current ]");
		return buffer.toString();
	}
}