package engine.util;

/**
 * The {@code DoubleDataMember} class holds two things... that's it... really... 
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.19
 * @param T the type of the things that it should hold.
 */
public class DoubleDataMember<T>{
    //the things 
    private T a,b;
    /**
     * Default constructor sets things to null.
     */
    public DoubleDataMember(){
        a=null;b=null;
    }
    /**
     * Second constructor takes things to set the things to.
     * @param _a thing a.
     * @param _b thing b.
     */
    public DoubleDataMember(T _a, T _b){
        a = _a;
        b = _b;
    }
    //getters and setters
    /**
     * Get the value of a.
     * @return ...
     */
    public T getA(){return a;}
    /**
     * Get the value of b.
     * @return ...
     */
    public T getB(){return b;}
    /**
     * Set the value of a.
     * @param _a a
     */
    public void setA(T _a){
        a = _a;
    }
    /**
     * Set the value of b.
     * @param _b b
     */
    public void setB(T _b){
        b = _b;
    }
}