package engine.data;

/**
 * The {@code OrderedLinkedList} class provides implementation for a linked list data structure.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.12.28
 */
public class OrderedLinkedList{
	//Holds the first node reference for this list.
	private Node first;
	//Holds the size of this list for ease and efficiency.
	private int size;

	/**
	 * Default constructor instantiates an empty list.
	 */
	public OrderedLinkedList(){
		//clear();
	}
	/**
	 * Second constructor takes an item or a series of items to add to the new list.
	 * @param items list of items to add to this list
	 */
	public OrderedLinkedList(Comparable... items){
		//clear();
		addAll(items);
	}
	
	/**
	 * Adds the specified item to the end of this list.
	 * @param item to be added to the list.
	 */
	public void add(Comparable item){
		//Exceptions thrown in pass-on
		Node place=this.placeOf(item);
		if(place==null)
			this.addFirst(item);
		else
			this.add(place,item);
	}
	/**
	 * Adds the list of items to the end of this list.
	 * @param items list of items to be added.
	 */
	public void addAll(Comparable... items){
		//Exceptions thrown in pass-on.
		for(Comparable item:items)
			add(item);
	}

	/**
	 * Removes the specified item from the list.
	 * @param item to be removed from the list.
	 */
	public void remove(Comparable item){
		if(size==0)throw new UnderflowException("Remove by item from empty list");
		if(item.equals(first.data))
			this.removeFirst();
		else
			this.remove(this.findBefore(item));
	}
	/**
	 * Removes the item at the specified index from the list.
	 * @param index where to remove the item from.
	 * @return object at the given index that was removed.
	 */
	public Comparable remove(int index){
		if(size==0)throw new UnderflowException("Remove by index from empty list");
		this.checkRange(index);
		if(index==0)
			return this.removeFirst();
		return this.remove(this.findBefore(index));
	}
	/**
	 * Removes the item at the end of the list.
	 * @return object that was previously at the end of the list.
	 */
	public Comparable remove(){
		//Exceptions thrown in pass-on.
		return remove(size-1);
	}
	/**
	 * Removes the specified range of items from the list.
	 * @param start index to start the range from.
	 * @param length how many items should be removed.
	 * @return an array of the specified length with the removed items in it.
	 */
	public Comparable[] removeRange(int start, int length){
		this.checkRange(start+length-1);
		Comparable[] list=new Comparable[length];
		for(int cnt=0;cnt<length;cnt++)
			list[cnt]=remove(start);
		return list;
	}

	/**
	 * Gets if the list is empty or not.
	 * @return if the list is empty.
	 */
	public boolean isEmpty(){
		return size==0;
	}
	/**
	 * Gets the size of the list.
	 * @return the size of the list.
	 */
	public int size(){
		return size;
	}

	/**
	 * Clears the list.
	 */
	public void clear(){
		first=null;
		size=0;
	}

	/**
	 * Returns if the specified item occurs in the list.
	 * @param item which item to look for.
	 * @return if the item occurs in the list.
	 */
	public boolean contains(Comparable item){
		return indexOf(item)!=-1;
	}

	/**
	 * Gets the index of the specified item if it exists.
	 * @param item which item to find the index of.
	 * @return the index of the item or -1.
	 */
	public int indexOf(Comparable item){
		Node node=first;
		for(int index=0;node!=null;node=node.next,index++)
			if(item.equals(node.data))return index;
		return -1;
	}
	/**
	 * Gets the last index of the specified item.
	 * @param item which item to find the last index of.
	 * @return the last index of the item or -1.
	 */
	public int lastIndexOf(Comparable item){
		int index=-1;
		Node node=first;
		for(int cnt=0;node!=null;node=node.next,cnt++)
			if(item.equals(node.data))index=cnt;
		return index;
	}
	/**
	 * Gets the item at the specified index.
	 * @param index where to get the item from.
	 * @return object at the specified index.
	 */
	public Comparable get(int index){
		this.checkRange(index);
		return (Comparable)this.find(index).data;
	}
	
	/**
	 * Gets a sublist from this list at the specified range.
	 * @param start index to start the range from.
	 * @param length how many items should be taken in the sublist.
	 * @return linked list with the specified sub range.
	 */
	public OrderedLinkedList subList(int start, int length){
		this.checkRange(start+length-1);
		OrderedLinkedList list=new OrderedLinkedList();
		Node f=this.find(start);
		for(int cnt=0;cnt<length;cnt++,f=f.next)
			list.add((Comparable)f.data);
		return list;
	}
	/** 
	 * Gets an array form of this list.
	 * @return array of all elements in this list.
	 */
	public Comparable[] toArray(){
		Comparable[] arr = new Comparable[size];
		int cnt=0;
		for(Node node=first;node!=null;node=node.next,cnt++)
			arr[cnt]=(Comparable)node.data;
		return arr;
	}

	/**
	 * Overrides object's toString
	 * @return a string representation of this list.
	 */
	@Override
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("OrderedLinkedList[ Start -> ");
		for(Node node=first;node!=null;node=node.next)
			buffer.append(node.data.toString()+" -> ");
		buffer.append("End ]");
		return buffer.toString();
	}


	//==================== HELPERS ====================

	//Convenience method - adds the item to the list after the specified node
	private void add(Node prev, Comparable item){
		prev.next=new Node(item,prev.next);
		size++;
	}
	//Convenience method - adds the item to the beginning of the list
	private void addFirst(Comparable item){
		first=new Node(item,first);
		size++;
	}
	//Convenience method - removes the node after and returns what it contained.
	private Comparable remove(Node prev){
		Comparable data=(Comparable)prev.next.data;
		prev.next=prev.next.next;
		size--;
		return data;
	}
	//Convenience method - removes the first node from the list.
	private Comparable removeFirst(){
		Comparable data=(Comparable)first.data;
		first=first.next;
		size--;
		return data;
	}


	//================== CONVENIENCE ==================

	//Convenience method - finds the place of an item and returns its previous node
	private Node placeOf(Comparable obj){
		Node last=null;
		for(Node node=first;node!=null;node=node.next){
			if(obj.compareTo((Comparable)node.data)<0)
				break;
			last=node;
		}
		return last;
	}
	//Convenience method - finds the node in the list with the passed object
	private Node find(Comparable obj){
		return findBefore(obj).next;
	}
	//Convenience method - finds the node before the one specified
	private Node findBefore(Comparable obj){
		for(Node node=first;node.next!=null;node=node.next)
			if(obj.equals(node.next.data))return node;
		return null;
	}
	//Convenience method - finds the node at the passed index
	private Node find(int index){
		return this.findBefore(index+1);
	}
	//Convenience method - finds the node before the passed index
	private Node findBefore(int index){
		Node node=first;
		for(int cnt=0;cnt<index-1;node=node.next,cnt++);
		return node;
	}
	//Convenience method - checks the range of the list against the passed index
	private void checkRange(int index){
		if(index<0||index>size-1)
			throw new RangeException(index);
	}
}