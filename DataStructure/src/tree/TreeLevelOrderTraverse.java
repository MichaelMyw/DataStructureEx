package tree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 * Author Mayiwei
 * Date 2020/5/16
 */
public class TreeLevelOrderTraverse {

    public static List<List<Integer>> treeLevelOrderTraverse(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        //使用队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        //先把根节点加进去
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            //队列大小，即每一层的数量
            int size = queue.size();
            //把当前层的节点出队列，并记录，再将下一层的结点入队列
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.value);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(treeLevelOrderTraverse(root));


    }
}
