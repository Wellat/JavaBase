package hemi.mldn.collection;

/**
 * 二叉树
 * 
 * @author Vanguard
 *
 * @param <E>
 */
public class BinaryTree<E extends Comparable<E>> {

	public static void main(String args[]) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		tree.insert(25);
		tree.insert(10);
		tree.insert(29);
		tree.insert(3);
		
		System.out.println("exist 3 ?:"+tree.search(3));
		tree.testsort();
	}

	/*
	 * 二叉查找树的简单实现
	 */

	// 根
	private TreeNode<E> root;

	// 默认构造函数
	public BinaryTree() {
	}

	// 二叉查找树的搜索
	public boolean search(E e) {
		TreeNode<E> current = root;
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				return true;
			}
		}
		return false;
	}
	public void testsort(){
		inorder(root);
	}
	// 二叉查找树的插入
	public boolean insert(E e) {
		// 如果之前是空二叉树 插入的元素就作为根节点
		if (root == null) {
			root = createNewNode(e);
		} else {
			// 否则就从根节点开始遍历 直到找到合适的父节点
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else {
					return false;
				}
			}
			// 插入
			if (e.compareTo(parent.element) < 0) {
				parent.left = createNewNode(e);
			} else {
				parent.right = createNewNode(e);
			}
		}
		return true;
	}

	// 创建新的节点
	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<E>(e);
	}

	// 先序遍历
	protected void preorder(TreeNode<E> root) {
		if (root == null)
			return;
		System.out.println(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}

	// 中序遍历
	public void inorder(TreeNode<E> root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.println(root.element + " ");
		inorder(root.right);
	}

	// 后序遍历
	protected void postorder(TreeNode<E> root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.println(root.element + " ");
	}
}

// 二叉树节点
class TreeNode<E> {
	E element;
	TreeNode<E> left;
	TreeNode<E> right;

	public TreeNode(E e) {
		element = e;
	}
}