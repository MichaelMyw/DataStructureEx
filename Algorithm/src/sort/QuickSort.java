package sort;

import java.util.Arrays;

/**
 * Author Mayiwei
 * Date 2020/4/15
 */
public class QuickSort {

    /**
     * 快速排序
     * @param arr 数组
     * @param left 数组左下标
     * @param right 数组右下标
     */
    public static void quickSort(int[] arr,int left,int right){
        if(left > right){
            return;
        }
        //定义两个指针i,j，一开始指在最左和最右
        int i = left;
        int j = right;
        //定义一个基准pivot, 一开始的值为arr[0]
        int pivot = arr[left];
        //移动指针i和j
        while(i != j){
            //先把j向左移动，选出比基准小的元素退出循环,没有就一直向左移动
            while(arr[j] > pivot && i < j){
                j--;
            }
            //先把i向又移动，选出比基准大的元素退出循环,没有就一直向右移动
            while(arr[i] <= pivot && i < j){
                i++;
            }
            //把选出的元素做交换
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        //最后再把基准点和指针重合点做交换
        arr[left] = arr[i];
        arr[i] = pivot;

        //分别对左半边和右半边递归快排
        quickSort(arr,left,i - 1);
        quickSort(arr,i + 1, right);
    }


    public static void main(String[] args) {
        int[] arr = {44,55,34,66,12,45,89,123,9};
        System.out.println(Arrays.toString(arr));
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
