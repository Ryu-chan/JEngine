package engine.util;

/**
 * The {@code BitMath} class provides many bitwise operations and calculations to 
 * speed up, improve and aid mathematical algorithms.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.18
 */
public final class BitMath{
    /**
     * Calculates the nearset upper bounded power of two for the specified integer.
     * @param i integer to round.
     * @return the closest upper bounding power of two.
     */
    static public int nearestPowerOf2(int i){
        if(i==0) return 1;
        i--;
        i |= i >> 1;    i |= i >> 2;
        i |= i >> 4;    i |= i >> 8;
        i |= i >> 16;
        i++;
        return i;
        //algorithm provided by bithacks
    }
    /**
     * Divide and conquer - find number of leading zeros.
     * @param i integer to get number of leading zeros for.
     * @return the number of leading zeros in the integer.
     */
    static public int leadingZeros(int i) {
        if(i==0) return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        return n;
        //algorithm found in java integer class
    }
    /**
     * Returns if the two passed integers have opposite signs.
     * @param a first integer.
     * @param b second integer.
     * @return true iff a and b have opposite signs.
     */
    static public boolean oppositeSigns(int a, int b){
        return (a^b)<0;
    }
    /**
     * Calculates if the integer is a power of 2.
     * @param i integer to calculate.
     * @return true iff i is a power of 2.
     */
    static public boolean isPowerOf2(int i){
        return (i & (i-1) ) == 0;
    }
}