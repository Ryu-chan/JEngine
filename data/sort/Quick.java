package engine.data.sort;

/**
 * The {@code Quick} class implements Quick sort.
 * It is suggested to override the findPivot method.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.05.18
 */
public class Quick extends Sorter{
    @Override
    public <E extends Comparable<?>> void sort(E[] arr){
        if(null!=arr && arr.length>1){
            quick(arr,0,arr.length);
        }
    }
    private <E extends Comparable<?>> void quick(E[]arr,int start,int length){
        if(arr.length>1 && arr.length>start){
            int p = partition(arr,start,length);
            quick(arr,start,p);
            quick(arr,start+p+1,
        }
    }
}