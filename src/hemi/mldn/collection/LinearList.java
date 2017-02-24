package hemi.mldn.collection;

public class LinearList {
	public static void main(String args[]) {
		Node<String> head = null;
		Node<String> tail = null;
		head = new Node<String>("nodedata1");
		tail = head;

		// 链表追加一个节点
		tail.next = new Node<String>("nodedata2");
		tail = tail.next;

		// 顺序遍历链表
		Node<String> current = head;
		while (current != null) {
			System.out.println(current.item);
			current = current.next;
		}
		LinearList.printListRev(head);

	}

	// 倒序遍历链表
	static void printListRev(Node<String> head) {
		if (head != null) {
			printListRev(head.next);
			System.out.println(head.item);
		}
	}
}

class Node<E> {
	E item;
	Node<E> next;

	Node(E element) {
		this.item = element;
		this.next = null;
	}
	// Node<E> head = null;
}
