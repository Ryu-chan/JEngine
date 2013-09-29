package engine.data.sort;

/**
 * The {@code Sorter} psuedo-interface provides insurance of a 
 * sort method.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.05.18
 */
public abstract class Sorter{
     abstract <E extends Comparable<?>> void sort(E[] arr);
     
     <E extends Comparable<?>> void swap(E[] arr, int i, int j){
         E tmp = arr[i];
         arr[i]= arr[j];
         arr[j]= tmp;
     }
}
