package engine.data.structures;

import engine.util.BitMath;
/**
 * The {@code MutableArray} class implements a mutable array data structure.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.16
 * @param T the type of item to be stored by this array
 */
public class MutableArray<T>{
    //The normal array
    private Object[] arr;
    //The size of the data within the array
    private int size;
    /**
     * Default constructor initializes the array with a capacity of 32.
     */
    public MutableArray(){ this(32); } //this is half the reason for not using arraylist - who starts with 10??? EVER???
    /**
     * Overloaded constructor takes an initial capacity for the array.
     * @param cap initial capacity for the array.
     */
    public MutableArray(int cap){
        if(cap<0)
            throw new IllegalArgumentException("mutable array capacity must be at least 1"); 
        arr = new Object[BitMath.nearestPowerOf2(cap)];
    }
    /**
     * Adds the item to the array at the next available index.
     * @param elem item to add to the array.
     */
    public void add(T elem){
        ensureSize();
        arr[size++]=elem;
    }
    /**
     * Adds the item to the array at the specified index.
     * @param index where to add the item.
     * @param elem item to add to the array.
     */
    public void add(int index, T elem){
        ensureSize();
        if(index<0)
            throw new IllegalArgumentException("negative index: "+index);
        if(index>size)
            index=size;
        //make room
        for(int i=size++;i>index;i--)
            arr[i]=arr[i-1];
        arr[index]=elem;
    }
    /**
     * Sets the item at the spcified index to the specified item effectively replacing the old one.
     * @param index where to set the item.
     * @param elem item to add to the array.
     * @return the item that was replaced.
     */
    public T set(int index, T elem){
        T e = get(index);
        arr[index]=elem;
        return e;
    }
    /**
     * Removes the last item in the array and returns it.
     * @return the item that was removed.
     */
    public T remove(){
        T elem = get(size-1);
        arr[--size]=null; //let gc take over
        return elem;
    }
    /**
     * Removes the item from the array at the specified index.
     * @param index where to remove the item from.
     * @return the item removed from the array.
     */
    public T remove(int index){
        T elem = get(index);
        for(int i=index;i<size-1;i++)
            arr[i]=arr[i+1];
        arr[--size]=null; //let gc take over
        return elem;
    }
    /**
     * Removes the specified item from the list or does nothing if the item does not exist.
     * @param elem the item to be removed from the list.
     * @return if the item was successfully found and removed.
     */
    public boolean remove(T elem){
        int index=indexOf(elem);
        if(index>-1){
            remove(index);
            return true;
        }
        return false;
    }
    /**
     * Removes every occurance of the specified item from the array.
     * @param elem the item to be removed from the list.
     */
    public void removeAll(T elem){
        int index;
        //complicated syntax just sets index via the assignment 
        //before checking boolean value in each iteration
        while( (index=indexOf(elem)) > -1)
            remove(index);
    }
    /**
     * Gets the item at the specified index.
     * @param index where to find the item in the array.
     * @return the item at the specified index.
     */
    @SuppressWarnings("unchecked") //MEGA GRRRRRRRRRRRRRRRR
    public T get(int index){
        checkIndex(index);
        return (T) arr[index];
    }
    /**
     * Returns the first occurance of the specified item or -1 if it does not exist.
     * @param elem the item to search for.
     * @return the index where the item is found or -1 if it is not found.
     */
    public int indexOf(T elem){
        for(int i=0;i<size;i++)
            if(elem.equals(arr[i]))
                return i;
        return -1;
    }
    /**
     * Returns the last occurance of the specified item or -1 if it does not exist.
     * @param elem the item to search for.
     * @return the last index where the item is found or -1 if it is not found.
     */
    public int lastIndexOf(T elem){
        for(int i=size-1;i>=0;i--)
            if(elem.equals(arr[i]))
                return i;
        return -1;
    }
    /**
     * Returns whether the array has the specified item or not.
     * @param elem item to search for.
     * @return if the item was found.
     */
    public boolean contains(T elem){
        return indexOf(elem) > -1; // tada - laziness at its best
    }
    /**
     * Trims the capity of the array to the current size of the array.
     */
    public void trimToSize(){
        Object[] newarr = new Object[BitMath.nearestPowerOf2(size)];
        for(int i=0;i<size;i++)
            newarr[i]=arr[i];
        arr=newarr;
    }
    /**
     * Returns if the array is empty.
     * @return if the array is empty.
     */
    public boolean isEmpty(){
        return size == 0;
    }
    /**
     * Removes every element from the array.
     */
    public void clear(){
        for(int i=0;i<size;i++)
            arr[i]=null;
    }
    /**
     * Gets the size of the array.
     * @return the integer size of the array.
     */
    public int size(){
        return size;
    }
    /**
     * Alias for size.
     * @return the integer size of the array.
     */
    public int length(){
        return size;
    }
    /**
     * Gets the current capacity of the array.
     * @return the current capacity of the array.
     */
    public int capacity(){
        return arr.length;
    }
    /// CONVENIENCE METHODS
    //Ensures the size of the array will never be too big or too small
    private void ensureSize(){
        if(size>arr.length-1){
            Object[] newarr = new Object[arr.length<<1];
            for(int i=0;i<arr.length;i++)
                newarr[i]=arr[i];
            arr=newarr;
        }
    }
    //Checks the range of the index against the size of the array
    private void checkIndex(int index){
        if(index<0)
            throw new ArrayIndexOutOfBoundsException("negative index: "+index);
        if(index>=size)
            throw new ArrayIndexOutOfBoundsException("index too large: "+index);
    }
}