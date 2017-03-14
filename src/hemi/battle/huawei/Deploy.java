package hemi.battle.huawei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import hemi.battle.huawei.Official.Main;

public class Deploy {
	public static void main(String[] args) {
		String graphFilePath = "D:\\Documents\\��Ϊ����\\case_example\\case1.txt";
		String resultFilePath = "D:\\Documents\\��Ϊ����\\result\\test.txt";
		String[] input = { graphFilePath, resultFilePath };
		Main.main(input);
	}

	/**
	 * ����Ҫ��ɵ���� <������ϸ����>
	 * 
	 * @param graphContent
	 *            ������Ϣ�ļ�
	 * @return [����˵��] ��������Ϣ
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public static String[] deployServer(String[] graphContent) {
		/** do your work here **/
		FormatData.format(graphContent);
		int[] index = sortNodeLinkCount(FormatData.costTable);// ����·����������
		int endid, band;
		/* ��ÿһ��Ŀ��ڵ� */
		for (int d = 0; d < FormatData.consumerList.size(); d++) {
			endid = FormatData.consumerList.get(d).getNetNode();
			band = FormatData.consumerList.get(d).getNeedBand();// �������
			/* ��ÿһ����� */
			// �˴��ݶ����̶�Ϊ5��
			for (int i = 0; i < 5; i++) {
				getPaths(FormatData.nodes[index[i]], null, FormatData.nodes[index[i]], FormatData.nodes[endid]);
			}
			// ��·���ڶ࣬����ѡ.
			// ȷ������·����ֵ���룩�������´���table
			// �ͷ��ڴ�
			sers = null;
		}

		return new String[] { "17", "\r\n", "0 8 0 20" };
	}

	/* ��ʱ����·���ڵ��ջ */
	public static Stack<Node> stack = new Stack<Node>();
	/* �洢·���ļ��� */
	public static ArrayList<Object[]> sers = new ArrayList<Object[]>();

	/**
	 * ����·����С�ϴ���
	 * 
	 * @param path 
	 *            ·��
	 * @return ��С����
	 */
	public static int getMinBandOfPath(Object[] path) {
		int min = 999;
		for (int i = 0; i < path.length; i++) {
			if ((int) path[i] < min)
				min = (int) path[i];
		}
		return min;
	}

	/**
	 * 
	 * @param cNode
	 *            currentNode��ǰ����ʼ�ڵ�
	 * @param pNode
	 *            previousNode��ǰ��ʼ�ڵ����һ�ڵ�
	 * @param sNode
	 *            startNode�������ʼ�ڵ�
	 * @param eNode
	 *            endNode�յ�
	 * @return
	 */
	public static boolean getPaths(Node cNode, Node pNode, Node sNode, Node eNode) {
		Node nNode = null;
		/* ������������ж�˵�����ֻ�·��������˳�Ÿ�·������Ѱ·������false */
		if (cNode != null && pNode != null && cNode == pNode)
			return false;

		if (cNode != null) {
			int i = 0;
			/* ��ʼ�ڵ���ջ */
			stack.push(cNode);
			/* �������ʼ�ڵ�����յ㣬˵���ҵ�һ��·�� */
			if (cNode == eNode) {
				/* ת������ӡ�����·��������true */
				if (stack.size() < 5)
					showAndSavePath();
				return true;
			}
			/* �������,����Ѱ· */
			else {
				/*
				 * ���뵱ǰ��ʼ�ڵ�cNode�����ӹ�ϵ�Ľڵ㼯�а�˳������õ�һ���ڵ� ��Ϊ��һ�εݹ�Ѱ·ʱ����ʼ�ڵ�
				 */
				nNode = FormatData.nodes[FormatData.nodes[cNode.getId()].getRelationNodes().get(i)];
				while (nNode != null) {
					/*
					 * ���nNode���������ʼ�ڵ����nNode����cNode����һ�ڵ����nNode�Ѿ���ջ�� �� ˵��������·
					 * ��Ӧ�������뵱ǰ��ʼ�ڵ������ӹ�ϵ�Ľڵ㼯��Ѱ��nNode
					 */
					if (pNode != null && (nNode == sNode || nNode == pNode || isNodeInStack(nNode))) {
						i++;
						if (i >= cNode.getRelationNodes().size())
							nNode = null;
						else
							nNode = FormatData.nodes[FormatData.nodes[cNode.getId()].getRelationNodes().get(i)];
						continue;
					}
					/* ��nNodeΪ�µ���ʼ�ڵ㣬��ǰ��ʼ�ڵ�cNodeΪ��һ�ڵ㣬�ݹ����Ѱ·���� */
					if (getPaths(nNode, cNode, sNode, eNode))/* �ݹ���� */
					{
						/* ����ҵ�һ��·�����򵯳�ջ���ڵ� */
						stack.pop();
					}
					/* ��������cNode�����ӹ�ϵ�Ľڵ㼯�в���nNode */
					i++;
					if (i >= cNode.getRelationNodes().size())
						nNode = null;
					else
						nNode = FormatData.nodes[FormatData.nodes[cNode.getId()].getRelationNodes().get(i)];
				}
				/*
				 * ��������������cNode�����ӹ�ϵ�Ľڵ�� ˵������cNodeΪ��ʼ�ڵ㵽�յ��·���Ѿ�ȫ���ҵ�
				 */
				stack.pop();
				return false;
			}
		} else
			return false;
	}

	/* �жϽڵ��Ƿ���ջ�� */
	public static boolean isNodeInStack(Node node) {
		Iterator<Node> it = stack.iterator();
		while (it.hasNext()) {
			Node node1 = (Node) it.next();
			if (node == node1)
				return true;
		}
		return false;
	}

	/* ��ʱջ�еĽڵ����һ������·����ת������ӡ��� */
	public static void showAndSavePath() {
		Object[] o = stack.toArray();
		for (int i = 0; i < o.length; i++) {
			Node nNode = (Node) o[i];
			if (i < (o.length - 1))
				System.out.print(nNode.getId() + "->");
			else
				System.out.print(nNode.getId());
		}
		sers.add(o); /* ת�� */
		System.out.println("\n");
	}

	/*
	 * ����·�����нڵ㣨��->�٣�
	 */
	public static int[] sortNodeLinkCount(int[][] t) {
		int len = t.length;
		int[] count = new int[len];
		for (int i = 0; i < len; i++) {
			int ct = 0;
			for (int j = 0; j < len; j++) {
				if (t[i][j] != 0) {
					++ct;
				}
			}
			count[i] = ct;
		}
		int[] temp = count.clone();
		return MyBubsort(temp);
	}

	/*
	 * ð�����򣬴�->С����������������ԭλ��
	 */
	private static int[] MyBubsort(int[] a) {
		int i, j, temp = 0;
		int len = a.length;
		int[] out = new int[len];
		for (int t = 0; t < len; t++) {
			out[t] = t;
		}
		for (i = 0; i < len - 1; i++) {
			for (j = 0; j < len - 1 - i; j++) {
				if (a[j] < a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					// swap index too
					temp = out[j];
					out[j] = out[j + 1];
					out[j + 1] = temp;
				}
			}
		}
		return out;
	}

}
