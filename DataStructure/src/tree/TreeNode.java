package tree;

/**
 * Author Mayiwei
 * Date 2020/4/15
 */
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int value;

    public TreeNode(int value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }

    public void add(TreeNode node){
        if(node == null){
            return;
        }
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }
        if(node.value > this.value){
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrderTraverse() {

        if (this.left != null) {
            this.left.inOrderTraverse();
        }
        System.out.print(this.value +" ");
        if (this.right != null) {
            this.right.inOrderTraverse();
        }
    }
}
