package hemi.xmu.jobs;

import org.junit.Test;

/**
 * Created by Vanguard on 2017/3/24.
 * 自己练习的排序算法
 */
public class Sort {

    @Test
    public void main() {
        int arr[] = {0, 0, 2, 4, 9, -5, 44, 13, 5, 7, 7, 1};
        Integer arr2[] = {1, 0, 2, 4, 9, -5};
        long begin = System.currentTimeMillis();

        heapSort(arr);

        System.out.println("排序后：");
        for (int a : arr)
            System.out.print(a + " ");
        System.out.println();

        long end = System.currentTimeMillis();
        System.out.println("用时："+(end-begin)+" ms");
    }


    /*
    归并排序
     */
    private static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] temp = (T[]) new Comparable[a.length];
        mergeSort(a, temp, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>>
    void mergeSort(T[] a, T[] temp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, temp, left, center);
            mergeSort(a, temp, center + 1, right);
            merge(a, temp, left, center + 1, right);
        }
    }

    private static <T extends Comparable<? super T>>
    void merge(T[] a, T[] temp, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0)
                temp[tempPos++] = a[leftPos++];
            else
                temp[tempPos++] = a[rightPos++];
        }
        while (leftPos <= leftEnd)
            temp[tempPos++] = a[leftPos++];
        while (rightPos <= rightEnd)
            temp[tempPos++] = a[rightPos++];
        for (int i = 0; i < numElements; i++, rightEnd--)
            a[rightEnd] = temp[rightEnd];
    }

    /*
    堆排序
     */
    public void heapSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            buildMaxHeap(arr,arr.length-1-i);
            swap(arr,0,arr.length-1-i);
        }
    }
    //建大顶堆
    private void buildMaxHeap(int[] data, int lastIndex) {
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            int parent = i;//父节点
            while (2 * parent + 1 <= lastIndex) {
                int biggerIndex = 2 * parent + 1;//记录两子节点值较大的节点的下标
                if (biggerIndex < lastIndex) {
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        biggerIndex++;
                    }
                }
                if (data[parent] < data[biggerIndex]) {
                    swap(data, parent, biggerIndex);
                    parent = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    /*
    选择排序
     */
    private static <T extends Comparable<? super T>> void selectSort(T[] a) {
        int minIndex;
        T temp;
        for (int i = 0; i < a.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[minIndex]) < 0)
                    minIndex = j;
            }
            if (minIndex != i) {
                temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }

    /*
    插入排序
     */
    private static <T extends Comparable<? super T>> void insertSort(T[] a) {
        for (int i = 1; i < a.length; i++) {
            T tmp = a[i];
            int j = i;
            for (; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    /*
    希尔排序
     */
    private static <T extends Comparable<? super T>> void shellSort(T[] a) {
        for (int gap = a.length / 2; gap > 0; gap = gap == 2 ? 1 : (int) (gap / 2.2)) {
            for (int i = gap; i < a.length; i++) {
                T tmp = a[i];
                int j = i;
                for (; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
                    a[j] = a[j - gap];
                a[j] = tmp;
            }
        }
    }

    /*
    快速排序
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] a, int low, int high) {
        int i, j;
        T choosen;
        if (low >= high)
            return;
        i = low;
        j = high;
        choosen = a[j];
        while (i < j) {
            while (i < j && a[i].compareTo(choosen) <= 0)
                i++;
            if (i < j)
                a[j--] = a[i];
            while (i < j && a[j].compareTo(choosen) >= 0)
                j--;
            if (i < j)
                a[i++] = a[j];
        }
        a[j] = choosen;
        quickSort(a, low, i - 1);
        quickSort(a, i + 1, high);
    }

    /*
    冒泡排序
     */
    public static <T extends Comparable<? super T>> void bubSort(T[] a) {
        T temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    private static void swap(int arr[], int a, int b) {
        if(a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
