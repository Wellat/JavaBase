package hemi.subject.jobs;

/**
 * Created by Vanguard on 2017/3/24.
 * 自己练习的排序算法
 */
public class Sort {
    /*public static void main(String[] args){
        int arr[] = {1,0,2,4,9,-5,44,13,5,7,7,0};

        insertSort(arr);
        System.out.println("排序后：");
        for(int a:arr)
            System.out.print(a+" ");

    }*/

    /*
    快速排序
     */
    public static void quickSort(int[] a){
        quickSort(a,0,a.length-1);
    }
    private static void quickSort(int[] a,int low ,int high){
        int i,j,choosen;
        if(low>high)
            return;
        i=low;j=high;
        choosen=a[j];
        while (i<j){
            while(i<j && a[i]<=choosen)
                i++;
            if(i<j)
                a[j--]=a[i];
            while(i<j && a[j]>=choosen)
                j--;
            if(i<j)
                a[i++]=a[j];
        }
        a[j]=choosen;
        quickSort(a,low,i-1);
        quickSort(a,i+1,high);
    }

    /*
    插入排序
     */
    public static void insertSort(int[] a){
        for(int i=1;i<a.length;i++){
            Integer temp = a[i];
            int j=i;
            for(;j>0 && temp.compareTo(a[j-1])<0;j--)
                a[j]=a[j-1];
            a[j]=temp;
        }
    }
    /*
    冒泡排序
     */
    public static void bubSort(int[] a){
        int temp;
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-1;j++){
                if(a[j]>a[j+1]){
                    swap(a,j,j+1);
                }
            }
        }
    }

    private static void swap(int arr[],int a,int b){
        int temp = arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }
}
