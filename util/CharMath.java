package engine.util;

/**
 * The {@code CharMath} class provides many char operations and calculations to 
 * speed up, improve and aid algorithms.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.18
 */
public final class CharMath{
    /**
     * Convert the passed string into an integer via character arithmetic.
     * @param str string to be converted.
     * @param integer value represented by the passed string.
     */
    static public int parseInt(String str){
        int i=0;
        for(char c:str.toCharArray()){
            i*=10;
            i+=c-'0';
        }
        return i;
    }
}