package hemi.xmu.jobs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 关于二叉树算法合集
 * Created by Hemi on 2017/10/2.
 */
public class BinaryTree {
    public static void main(String[] args) {
        System.out.println(getMaxDepth(TestData.getTreeNode()));
        System.out.println(getMinDepth(TestData.getTreeNode()));
    }

    /**
     * 二叉树最大深度
     */
    public static int getMaxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = getMaxDepth(root.left);
        int right = getMaxDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    /**
     * 二叉树最小深度
     */
    public static int getMinDepth(TreeNode root) {
        if (root == null) return 0;
        if(root.left==null){
            return getMinDepth(root.right);
        }
        if(root.right==null){
            return getMinDepth(root.left);
        }
        int left = getMinDepth(root.left);
        int right = getMinDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }




    /**
     * //TODO 有问题
     * 深度优先搜索 dfs
     */
    public void dfs(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode cur = null;
        while (stack.size()>0){
            cur = stack.pop();
            System.out.print(cur.val+"-");
            if(cur.right!= null) stack.push(cur.right);
            if(cur.left!=null) stack.push(cur.left);
        }
    }

    /**
     * 广度优先遍历二叉树，层序遍历 bfs
     */
    public void bfs(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode cur = null;
        queue.offer(root);
        while (!queue.isEmpty()){
            cur = queue.poll();
            System.out.print(cur.val+" ");
            if(cur.left!=null) queue.offer(cur.left);
            if(cur.right!=null) queue.offer(cur.right);
        }
    }

    /**
     * 重建二叉树
     *
     * @param pre
     * @param in
     * @return
     */
    /*
    输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
    假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn)
            return null;
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
            }
        }
        return root;
    }

    /**
     * 二叉树的镜像
     *
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null)
            return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null) {
            Mirror(root.left);
        }
        if (root.right != null) {
            Mirror(root.right);
        }
    }


    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        boolean result = false;
        if (root1.val == root2.val) {
            result = hasSubtree(root1, root2);
        }
        if (!result) {
            result = hasSubtree(root1.left, root2);
        }
        if (!result) {
            result = hasSubtree(root1.right, root2);
        }
        return result;
    }
    private boolean hasSubtree(TreeNode parent, TreeNode child) {
        if (parent == null && child != null) return false;
        if (child == null) return true;
        if (parent.val != child.val) return false;
        return hasSubtree(parent.left, child.left) && hasSubtree(parent.right, child.right);
    }

    /**
     * 二叉树的遍历
     */
    /*
    递归
     */
    // 先序遍历
    public void preOrder(BinaryNode node) {
        if (node == null) return;
        System.out.println(node.element);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历
    public void inOrder(BinaryNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.element);
        inOrder(node.right);
    }

    // 后序遍历
    public void postOrder(BinaryNode node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.element);
    }

    /*
    非递归
     */
    // 先序遍历
    public void preOrder2(BinaryNode node) {
        Stack<BinaryNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.element);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    // 中序遍历
    public void inOrder2(BinaryNode node) {
        Stack<BinaryNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println(node.element);
                node = node.right;
            }
        }
    }

    // 后序遍历
    public void postOrder2(BinaryNode p) {
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        BinaryNode node = p, prev = p;
        while (node != null || stack.size() > 0) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (stack.size() > 0) {
                BinaryNode temp = stack.peek().right;
                if (temp == null || temp == prev) {
                    node = stack.pop();
                    System.out.println(node.element);
                    prev = node;
                    node = null;
                } else {
                    node = temp;
                }
            }
        }
    }

}
