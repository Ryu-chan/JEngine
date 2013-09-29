package engine.data.sort;

public class Tester{
    static public void main(String[]args){
        Comparable[] arr = new Comparable[6];
        arr[0]=3;
        arr[1]=4;
        arr[2]=5;
        arr[3]=3;
        arr[4]=2;
        arr[5]=8;
        new Select().sort(arr);
        for(int i=0;i<6;i++)
         System.out.println(arr[i]);
    }
}