package engine.core;

import engine.data.LinkedList;
import engine.data.Node;
/**
 * {@code ExecutableChain} class is a linked list of executable code.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.07
 */
public class ExecutableChain implements ExecutableStructure
    //GIANT WRAPPER CLASS - WHY JAVA WHY
{
    //Holds the chain of executable code
    private LinkedList chain;
    /**
     * Default constructor instantiates an empty chain.
     */
    public ExecutableChain(){
        chain = new LinkedList();
    }
    /**
     * Second constructor takes an executable or many executables 
     * to add to the new chain.
     * @param execs list of executables to add to this chain.
     */
    public ExecutableChain(Executable... execs){
        chain = new LinkedList((Object[])execs);
    }
    /**
     * Adds the specified executable to the end of the chain.
     * @param exec to be added to the chain.
     */
    public void add(Executable exec){
        chain.add(exec);
    }
    /**
     * Adds the specified executable to the chain at the specified index.
     * @param index where to add the executable.
     * @param exec to be added to the chain.
     */
    public void add(int index, Executable exec){
        chain.add(index,exec);
    }
    /**
     * Adds the list of executables to the end of the chain.
     * @param execs list of executables to be added.
     */
    public void addAll(Executable... execs){
        chain.addAll((Object[])execs);
    }
    /**
     * Adds the list of executables to the chain at the specified index.
     * @param index where to add the list of executables.
     * @param execs list of executables to be added.
     */
    public void addAll(int index, Executable... execs){
        chain.addAll(index,(Object[])execs);
    }
    /**
     * Sets the executable at the specified index to the specified executable.
     * @param index where to replace the executable.
     * @param exec to replace the executable at the given index.
     */
    public void set(int index, Executable exec){
        chain.set(index,exec);
    }
    /**
     * Removes the specified executable from the chain.
     * @param exec to be removed from the chain.
     */
    public void remove(Executable exec){
        chain.remove(exec);
    }
    /**
     * Removes the executable at the specified index from the chain.
     * @param index where to remove the executable from.
     * @return executable at the given index that was removed.
     */
    public Executable remove(int index){
        return (Executable)chain.remove(index);
    }
    /**
     * Removes the item at the end of the chain.
     * @param executable that was at the end of the chain.
     */
    public Executable remove(){
        return (Executable)chain.remove();
    }
    /**
     * Removes the specified range of executables from the chain.
     * @param start index to start the range from.
     * @param length how many executables should be removed.
     * @return an array of the specified length with the removed executables.
     */
    public Executable[] removeRange(int start, int length){
        return (Executable[])chain.removeRange(start,length);
    }
    /**
     * Gets if the chain is empty.
     * @return if the chain is empty.
     */
    public boolean isEmpty(){
        return chain.isEmpty();
    }
    /**
     * Gets the size of the chain.
     * @return the size of the chain.
     */
    public int size(){
        return chain.size();
    }
    /**
     * Clears the chain.
     */
    public void clear(){
        chain.clear();
    }
    /**
     * Returns if the specified executable occurs in the chain.
     * @param exec which executable to look for.
     * @return if the executable occurs in the chain.
     */
    public boolean contains(Executable exec){
        return chain.contains(exec);
    }
    /**
     * Gets the index of the specified executable if it exists.
     * @param exec which executable to find the index of.
     * @return the index of the item or -1.
     */
    public int indexOf(Executable exec){
        return chain.indexOf(exec);
    }
    /**
     * Gets the last index of the specified item.
     * @param exec which executable to find the last index of.
     * @return the last index of the executable or -1.
     */
    public int lastIndexOf(Executable exec){
        return chain.lastIndexOf(exec);
    }
    /**
     * Gets the item at the specified index.
     * @param index where to get the executable from.
     * @return executable at the specified index.
     */
    public Executable get(int index){
        return (Executable)chain.get(index);
    }
    /**
     * Gets the linked list maintained by this class.
     * @return the chain.
     */
    public LinkedList getChain(){
        return chain;
    }
    /**
     * Executes the first item in the chain.
     */
    public void execute(){
        if(!isEmpty())
            ((Executable)chain.getFirst()).execute();
    }
    /**
     * Executes all items in the chain.
     */
    public void executeAll(){
        //finally - the only part of this class that does something useful!
        for(Node node=chain.getFirst();null!=node;node=node.next)
            ((Executable)node.data).execute();
    }
}