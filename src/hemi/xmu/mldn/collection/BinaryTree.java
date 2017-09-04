package hemi.xmu.mldn.collection;

/**
 * ������
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
	 * ����������ļ�ʵ��
	 */

	// ��
	private TreeNode<E> root;

	// Ĭ�Ϲ��캯��
	public BinaryTree() {
	}

	// ���������������
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
	// ����������Ĳ���
	public boolean insert(E e) {
		// ���֮ǰ�ǿն����� �����Ԫ�ؾ���Ϊ���ڵ�
		if (root == null) {
			root = createNewNode(e);
		} else {
			// ����ʹӸ��ڵ㿪ʼ���� ֱ���ҵ����ʵĸ��ڵ�
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
			// ����
			if (e.compareTo(parent.element) < 0) {
				parent.left = createNewNode(e);
			} else {
				parent.right = createNewNode(e);
			}
		}
		return true;
	}

	// �����µĽڵ�
	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<E>(e);
	}

	// �������
	protected void preorder(TreeNode<E> root) {
		if (root == null)
			return;
		System.out.println(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}

	// �������
	public void inorder(TreeNode<E> root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.println(root.element + " ");
		inorder(root.right);
	}

	// �������
	protected void postorder(TreeNode<E> root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.println(root.element + " ");
	}
}

// �������ڵ�
class TreeNode<E> {
	E element;
	TreeNode<E> left;
	TreeNode<E> right;

	public TreeNode(E e) {
		element = e;
	}
}