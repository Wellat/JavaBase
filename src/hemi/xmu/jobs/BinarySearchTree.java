package hemi.xmu.jobs;

import org.junit.Test;

/**
 * 二叉查找树（无重复节点）
 * Created by Vanguard on 2017/4/5.
 */
public class BinarySearchTree {
    @Test
    public void test(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(6);
        tree.insert(5);
        tree.insert(1);
        tree.remove(5);
    }


    protected BinaryNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int x) {
        root = insert(x, root);
    }

    public void remove(int x) {
        root = remove(x, root);
    }

    public void removeMin() {
        root = removeMin(root);
    }

    public void removeMax() {
        root = removeMax(root);
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private BinaryNode insert(int x, BinaryNode t) {
        if (t == null) {
            t = new BinaryNode(x);
        } else if (x - t.element > 0) {
            t.right = insert(x, t.right);
        } else if (x - t.element < 0) {
            t.left = insert(x, t.left);
        } else {
            System.out.println("Element Duplicated.");
        }
        return t;
    }

    private BinaryNode remove(int x, BinaryNode t) {
        if (t == null) {
            System.out.println("Tree is NULL.");
            return null;
        } else if (x - t.element > 0) {
            t.right = remove(x, t.right);
        } else if (x - t.element < 0) {
            t.left = remove(x, t.left);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = removeMin(t.right);
        } else {
            t = (t.left == null) ? t.right : t.left;
        }
        return t;
    }

    private BinaryNode findMin(BinaryNode t) {
        if (t != null)
            while (t.left != null)
                t = t.left;
        return t;
    }

    private BinaryNode removeMin(BinaryNode t) {
        if (t == null) {
            System.out.println("Tree is NULL.");
            return null;
        } else if (t.left != null) {
            t.left = removeMin(t.left);
            return t;
        } else
            return t.right;
    }

    private BinaryNode removeMax(BinaryNode t) {
        if (t == null) {
            return null;
        } else if (t.right != null) {
            t.right = removeMax(t.right);
            return t;
        } else
            return t.left;
    }

}

class BinaryNode {
    BinaryNode(int element) {
        this.element = element;
        left = right = null;
    }

    int element;
    BinaryNode left;
    BinaryNode right;
}
