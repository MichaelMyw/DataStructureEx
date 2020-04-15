package search;

/**
 * Author Mayiwei
 * Date 2020/4/15
 */
public class BinarySearch {


    /**
     * 二分查找递归
     * @param arr 数组
     * @param left 数组左下标0
     * @param right 数组右下标 arr.length - 1
     * @param target 查找的值
     * @return 数组下标 没有就返回 -1
     */
    public static int binarySearchByRecurion(int[] arr,int left,int right,int target){
        if(left > right){
            return -1;
        }
        int mid = (left + right)/2;
        //如果目标比中间的数大，则在右半边递归查找，反之在左半边递归查找
        if(target > arr[mid]){
            return binarySearchByRecurion(arr,mid + 1,right,target);
        }else if(target < arr[mid]){
            return binarySearchByRecurion(arr,left,mid - 1,target);
        }else {
            return mid;
        }
    }

    /**
     * 二分查找非递归
     * @param arr 数组
     * @param target 查找的值
     * @return 数组下标 没有就返回 -1
     */
    public static int binarySearchNonRecursion(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = (left + right)/2;
            if(target == arr[mid]){
                return mid;
            }else if(target > arr[mid]){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        System.out.println(binarySearchByRecurion(arr,0,arr.length - 1,9));
        System.out.println(binarySearchNonRecursion(arr,9));
    }
}
