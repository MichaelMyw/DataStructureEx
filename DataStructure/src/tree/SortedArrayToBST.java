package tree;

/**
 * Author Mayiwei
 * Date 2020/5/15
 */
public class SortedArrayToBST {

    /**
     * 有序数组转平衡二叉树
     * @param arr
     * @return
     */
    public TreeNode sortedArrayToBST(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        return getBalancedBST(arr, 0 , arr.length - 1);
    }

    private TreeNode getBalancedBST(int[] arr, int start, int end){
        if(start > end){
            return null;
        }
        TreeNode node = new TreeNode(arr[(start + end)/2]);
        node.left = getBalancedBST(arr,start,(start + end)/2 - 1);
        node.right = getBalancedBST(arr, (start + end)/2 + 1, end);
        return node;
    }


    public static void main(String[] args) {
        SortedArrayToBST s = new SortedArrayToBST();
        TreeNode treeNode = s.sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        treeNode.inOrderTraverse();


    }
}
