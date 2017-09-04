package hemi.xmu.mldn.collection;

public class LinearList {
	public static void main(String args[]) {
		Node<String> head = null;
		Node<String> tail = null;
		head = new Node<String>("nodedata1");
		tail = head;

		// ����׷��һ���ڵ�
		tail.next = new Node<String>("nodedata2");
		tail = tail.next;

		// ˳���������
		Node<String> current = head;
		while (current != null) {
			System.out.println(current.item);
			current = current.next;
		}
		LinearList.printListRev(head);

	}

	// �����������
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
