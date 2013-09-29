package engine.data.sort;

/**
 * The {@code Select} class implements Select sort.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.05.18
 */
public class Select extends Sorter{
    @Override
    public <E extends Comparable<?>> void sort(E[] arr){
        if(null!=arr && arr.length>1){
            int i,j,min;
            for(i=0;i<arr.length;i++){
                for(j=i+1,min=i;j<arr.length;j++)
                    if( ((Comparable)arr[j]).compareTo(arr[min])<0)
                        min=j;
                if( ((Comparable)arr[min]).compareTo(arr[i])<0)
                    swap(arr,min,i);
            }
        }
    }
}