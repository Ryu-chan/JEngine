package engine.data.sort;

/**
 * The {@code Insert} class implements Insert sort.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.05.18
 */
public class Insert extends Sorter{
    @Override
    public <E extends Comparable<?>> void sort(E[] arr){
        if(null!=arr && arr.length>1){
            int i,j;
            for(i=1;i<arr.length;i++)
                for(j=i;
                    j>0 && ((Comparable)arr[j]).compareTo(arr[j-1])<0;
                    j--)
                        swap(arr,j,j-1);
        }
    }
}