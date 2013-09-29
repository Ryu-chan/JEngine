package engine.core;

/** 
 * {@code Timer} class handles threaded ticking cycles for accurate 
 * intervals between triggers of executable chains and queues.
 * <p>Note:<br/>
 * All code in ichain will execute the number of times per second
 * specified by the period of this timer.  All code in the pchain 
 * will execute every time the timer ticks.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.08
 */
public abstract class Timer implements Runnable{
    /** Number of delays to be had before yielding the thread */
    static private final int DELAY_YIELD = 16;
    /** Number of skips in the timer before continuing */
    static private final int MAX_SKIPS = 5;
    //Thread for execution
    private Thread activated;
    //Flag for if the thread is running or not
    private volatile boolean runflag;
    //Chain of executable code to run in timer
    private volatile ExecutableChain pchain;
    //Chain of executable code to run in case of skipped ticks
    private volatile ExecutableChain ichain;
    //Queue to execute when possible
    private volatile ExecutableQueue equeue; //I know I'm going to regret calling it this
    //Time between ticks
    private volatile long period;
    /**
     * Constructor takes a cycle period.
     * @param period the time between each cycle that the timer will attempt to hit.
     */
    public Timer(long period){
        this.period = period;
        pchain = new ExecutableChain();
        ichain = new ExecutableChain();
        equeue = new ExecutableQueue();
    }
    /**
     * Constructor takes a spent timer to reactivate it.
     * @param timer the used (or unused if preferred) timer to clone.
     */
    public Timer(Timer timer){
        this.period = timer.period;
        this.pchain = timer.pchain;
        this.ichain = timer.ichain;
        this.equeue = timer.equeue;
    }
    /**
     * Activates the timers - this allows the timer to only be started and stopped once.
     */
    public void start(){
        if(null==activated){
            activated = new Thread(this);
            activated.start();
        }
    }
    /**
     * Stops the timer gracefully - the maximum amount of time it may take is equal to the period.
     */
    public void stop(){
        runflag=false;
    }
    /**
     * The run method is used by the thread class to execute asynchronously.
     */
    @Override
    public void run(){
        runflag=true;
        //track time - ensures the period
        long before = System.nanoTime(),
             after,
             sleep,
             overslept = 0L,
             excess = 0L;
        int delays=0,
            skips=0;
        //run something before starting
        pre_execution();
        //GO!
        while(runflag){
            //execute both chains
            getPrimaryChain().executeAll();
            getInsuredChain().executeAll();
            //execute one item in the queue if there is one
            if(!getQueue().isEmpty())
                equeue.dequeue().execute();
            //log time and calculate how long to sleep
            after = System.nanoTime();
            sleep = ( period - (after - before) ) - overslept;
            //if there is time to sleep, otherwise...
            if(sleep > 0){
                try{ Thread.sleep(toMillis(sleep));
                }catch(InterruptedException ignored){} //hit whoever woke me up
                //if alseep to long store how long to make up for it - weird right?
                overslept = (System.nanoTime() - after) - sleep;
            }else{
                //if there was no time to sleep get rid of some extra time to compensate
                //remember sleep is less or equal 0 so minus equals will add time
                excess -= sleep;
                overslept = 0L;
                //sleeping allows other threads (including other timers) to activate 
                //should this thread not sleep for a long time it could cuase other 
                //important threads to never fire - this catches that by saying if 
                //it happens too often that there it no time to sleep - yield the 
                //thread over to another anyway.
                if(++delays >= DELAY_YIELD){
                    Thread.yield();
                    delays = 0;
                }
            }
            //relog before time
            before = System.nanoTime();
            //imprtant for insured chain
            //if the amount of excess time exceeds sleep time let the thread skip a 
            //a couple of primary chain executions to compensate for the insurace chain 
            //this will have the effect of sacrificing one primary execution for every 
            // "max_skips" executions of the insurance chain as can be seen in the samples 
            for(skips=0;
                excess>period && skips<MAX_SKIPS;
                excess-=period,skips++)
                    getInsuredChain().executeAll();
        }
        //run something before ending
        post_execution();
    }
    /**
     * Gets the primary execution chain.
     * @return the primary execution chain.
     */
    public synchronized ExecutableChain getPrimaryChain(){
        return pchain; //?? should this be called primary chain??
    }
    /**
     * Sets the primary execution chain for this timer.
     * @param ec executable chain to make the primary chain.
     *
    public void setPrimaryChain(ExecutableChain ec){
        pchain = ec;
    }*/
    /**
     * Gets the insured chain. The insured chain will execute the number of times specified 
     * by the period even if the machine cannot keep up.  This is useful for game updates so 
     * as not to sacrifice gameplay to a lacking fps caused by system deficiencies.
     * @return the insured chain.
     */
    public synchronized ExecutableChain getInsuredChain(){
        return ichain; 
    }
    /**
     * Sets the insured chain for this timer.
     * @param ec executable chain to make the insured chain.
     *
    public void setInsuredChain(ExecutableChain ec){
        ichain = ec;
    }*/
    /**
     * Gets the executable queue for this timer.
     * @return the executable queue.
     */
    public synchronized ExecutableQueue getQueue(){
        return equeue; //queueueueueueueue
    }
    /**
     * Convenience methods adds to the executable queue.
     * @param e executable to add to the queue.
     */
    public void enqueue(Executable e){
        equeue.enqueue(e); //hahaha thats a mouthful!!!
    }
    
    //Convenience methods
    private long toMillis(long nano){
        return nano/1000000L;
    }
    private long toNano(long millis){
        return millis * 1000000L;
    }
    
    /**
     * Executes before the timer starts.
     */
    public abstract void pre_execution(); //creative right?
    /**
     * Executed after the timer stops.
     */
    public abstract void post_execution();
}
/*
Sample run - 2013.03.08

Period: 1000000ns
Duration: 1000ms
Primary:Insured | 1000:1000
    Accuracy: 0:0

Period: 1000000ns
Duration: 1000ms
Primary:Insured | 1000:1000
    Accuracy: 0:0

Period: 1000000ns
Duration: 1000ms
Primary:Insured | 1001:1001
    Accuracy: -1:-1

Period: 100000ns
Duration: 1000ms
Primary:Insured | 7715:9974
    Accuracy: 2285:26

Period: 100000ns
Duration: 1000ms
Primary:Insured | 7883:9984
    Accuracy: 2117:16

Period: 100000ns
Duration: 1000ms
Primary:Insured | 7806:9980
    Accuracy: 2194:20

Period: 10000ns
Duration: 1000ms
Primary:Insured | 72049:99498
    Accuracy: 27951:502

Period: 10000ns
Duration: 1000ms
Primary:Insured | 74688:99652
    Accuracy: 25312:348

Period: 10000ns
Duration: 1000ms
Primary:Insured | 73539:99558
    Accuracy: 26461:442

*/