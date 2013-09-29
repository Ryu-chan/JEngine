package engine.core;

import engine.data.Queue;
/**
 * {@code ExecutableChain} class is a queue of executable code.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.08
 */
public class ExecutableQueue implements ExecutableStructure{
    //Holds the queue of executable code
    private Queue queue;
    /**
     * Default constructor instantiates an empty quq. <- how did this typo even happen _-_,
     */
    public ExecutableQueue(){
        queue = new Queue();
    }
    /**
     * Second constructor takes an executable or many executables 
     * to add to the new queue.
     * @param execs list of executables to add to this queue.
     */
    public ExecutableQueue(Executable... execs){
        queue = new Queue((Object[])execs); //grrrrrrrrrrrrrrrrrrrrrrrrrr
    }
    /**
     * Adds the specified executable to the queue.
     * @param exec to be added to the queue.
     */
    public void enqueue(Executable exec){
        queue.enqueue(exec);
    }
    /**
     * Adds the list of executables to the queue in the order of the list.
     * @param execs list of executables to be added.
     */
    public void enqueueAll(Executable... execs){
        queue.enqueue((Object[])execs); // also grrrrrrrrrrrrrrrrrrrrrrrrrr
    }
    /**
     * Removes the executable at the beginning of the queue without 
     * executing it and returns it.
     * @return the executable at the beginning of the queue.
     */
    public Executable dequeue(){
        return (Executable)queue.dequeue();
    }
    /**
     * Reveals the executable at the beginning of the queue without doing 
     * anything with it.
     * @return the executable currently at the beginning of the queue.
     */
    public Executable peek(){
        return (Executable)queue.peek();
    }
    /**
     * Gets if the queue is empty.
     * @return if the queue empty.
     */
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    /**
     * Clears the queue.
     */
    public void clear(){
        queue.clear();
    }
    /**
     * Executes the first executable in the queue and removes it.
     */
    public void execute(){
        if(!isEmpty())
            ((Executable)queue.dequeue()).execute();
    }
    /**
     * Executes every executable in the queue and removes them.
     */
    public void executeAll(){
        while(!isEmpty())
            ((Executable)queue.dequeue()).execute();
    }
}