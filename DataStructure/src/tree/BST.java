package tree;

/**
 * Author Mayiwei
 * Date 2020/5/15
 */
public class BST {

    private TreeNode root;

    /**
     * 添加
     *
     * @param node
     */
    public void add(TreeNode node) {
        if (root == null) {
            root = node;
        }
        else{
            root.add(node);
        }
    }

    public void inOrderTraverse() {
        if(root == null) {
            System.out.println("此二叉树为空");
            return;
        }else {
            root.inOrderTraverse();
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.add(new TreeNode(4));
        bst.add(new TreeNode(5));
        bst.add(new TreeNode(6));
        bst.add(new TreeNode(3));


        bst.inOrderTraverse();
    }
}
