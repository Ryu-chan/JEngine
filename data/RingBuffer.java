package engine.data;

/**
 * The {@code RingBuffer} class models the data type needed for the 
 * Karplus-Strong algorithm.
 * 
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.10.14
 */
public class RingBuffer{
    private int first, size;
    private Object[] ring;
    /**
     * Takes one parameter as the capacity for this ring buffer.
     * @param cap capacity of the buffer; length of the ring.
     */
    public RingBuffer(int cap){
        ring = new Object[cap];
    }
    /**
     * Accessor method.
     * @return the number of items currently in the buffer.
     */
    public int size(){
        return size;
    }
    /**
     * Check method.
     * @return if the buffer is empty.
     */
    public boolean isEmpty(){
        return size()==0;
    }
    /**
     * Check method.
     * @return is the buffer is full.
     */
    public boolean isFull(){
        return size()==ring.length;
    }
    /**
     * Add an item to the buffer.
     * @param val value to queue in the buffer
     */
    public void enqueue(Object val){
        if(isFull())
            throw new OverflowException("ring buffer is full");
        size++;
        ring[(first+size-1)%ring.length]=val;
    }
    /**
     * Remove and return an item from the front of the buffer.
     * @return item from the front of the buffer.
     */
    public Object dequeue(){
        if(isEmpty())
            throw new UnderflowException("ring buffer is empty");
        size--;
        int temp = first;
        first=(first+1)%ring.length;
        return ring[temp];
    }
    /**
     * Peek into the front of the buffer.
     * @return item from the front of the buffer.
     */
    public Object peek(){
        if(isEmpty())
            throw new UnderflowException("ring buffer is empty");
        return ring[first];
    }
    /**
     * @return a string representation of this class.
     */
    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("RingBuffer=[{");
        for(Object val:ring){
            buffer.append(val).append(", ");
        }
        buffer.append("}]");
        return buffer.toString();
    }
}