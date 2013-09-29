package engine.data.sort;

/**
 * The {@code Shell} class implements Shell sort.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.05.18
 */
public class Shell extends Sorter{
    static public final int THRESHOLD = 3;
    @Override
    public <E extends Comparable<?>> void sort(E[] arr){
        if(null!=arr && arr.length>1){
            for(int incr=arr.length/2;incr>=THRESHOLD;incr=incr/2+1)
                for(int start=0;start<incr;start++)
                    insertIncr(arr,start,incr);
            insertIncr(arr,0,1);
        }
    }
    @SuppressWarnings({"unchecked"})
    <E extends Comparable<?>> void insertIncr(E[] arr,int start,int incr){
        int i,j;
        for(i=incr+start;i<arr.length-start;i+=incr)
            for(j=i;
                j>start && ((Comparable)arr[j+start]).compareTo(arr[j+start-incr])<0;
                j-=incr)
                    swap(arr,j,j-incr);
    }
}