package hemi.battle.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

import hemi.battle.huawei.Official.Main;

public class Deploy_exh {
	public static void main(String[] args) {
		String graphFilePath = "D:\\Documents\\��Ϊ����\\case_example\\case0.txt";
		String resultFilePath = "D:\\Documents\\��Ϊ����\\result\\test.txt";
		String[] input = { graphFilePath, resultFilePath };
		Main.main(input);
	}

	/* ��ʱ����·���ڵ��ջ */
	public static Stack<Node> stack;
	/* �洢��ѡ·������ */
	public static ArrayList<Object[]> sers = new ArrayList<Object[]>();
	/* ת��·���� */
	public static final int TRAIN = 20;

	public static final int STACKSIZE = 7;
	/* ��������·�� */
	public static ArrayList<Object[]> result = new ArrayList<Object[]>();

	public static int ecost[] = new int[100];

	public static int useband[] = new int[100];

	public static int costIndex = 0;

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
		int endid, needband;
		//��ÿһ��Ŀ��ڵ� 
		for (int d = 0; d < FormatData.consumerList.size(); d++) {// FormatData.consumerList.size()
			stack = new Stack<Node>();
			endid = FormatData.consumerList.get(d).getNetNode();
			needband = FormatData.consumerList.get(d).getNeedBand();
			// band = FormatData.consumerList.get(d).getNeedBand();// �������
			//��ÿһ����� 
			// �˴��ݶ����̶�Ϊ3��
			for (int i = 0; i < 6; i++) {
				getPaths(FormatData.nodes[index[i]], null, FormatData.nodes[index[i]], FormatData.nodes[endid]);
			}
			// ��·���ڶ࣬����ѡ.
			// ȷ������·����ֵ���룩�������´���table
			selectBestPath(sers, needband);
		}
		// ��ӡ���
		showResult();
		int sumCost = 0;
		for(int i=0;i<ecost.length;i++){
			sumCost += ecost[i];
		}
		sumCost = sumCost + (3*FormatData.CostOfPerServer);
		System.out.println("sumCost: "+sumCost);
		return new String[] { "17", "\r\n", "0 8 0 20" };
	}

	/**
	 * ȷ��·������
	 * 
	 * @param pathList
	 *            ��ѡ·������
	 * @param needband
	 *            �ͻ��������
	 */
	private static void selectBestPath(ArrayList<Object[]> pathList, int needband) {
		int count = 0;
		ArrayList<Object[]> pathtemp = new ArrayList<Object[]>();
		// ת��·��
		for (int i = 0; i < pathList.size() && count < TRAIN; i++) {
			int min = getMinLenOfPaths(pathList);
			pathtemp.add(pathList.get(min));
			count++;
			pathList.remove(min);
		}

		int cband = 0;
		// �ֱ���cost
		for (int j = 0; j < pathtemp.size(); j++) {
			Object[] path = pathtemp.get(j);
			cband = getMinBandOfPath(path);
			// ������·�������������󡪡�ֱ������ѣ�������
			if (cband >= needband) {
				ecost[costIndex] = calculateCost(path, needband);
				result.add(path);
				updateBandTable(path, needband);
				useband[costIndex] = needband;
				costIndex++;
				return;
			} else {
				if (cband > 0) {
					ecost[costIndex] = calculateCost(path, cband);
					result.add(path);
					updateBandTable(path, cband);
					needband -= cband;
					useband[costIndex] = cband;
					costIndex++;
				}
				continue;
			}
		}
	}

	private static void updateBandTable(Object[] obj, int delt) {
		int[] path = pathToArray(obj);
		for (int i = 0; i < path.length - 1; i++) {
			FormatData.bandTable[path[i]][path[i + 1]] -= delt;
//			FormatData.bandTable[path[i + 1]][path[i]] = FormatData.bandTable[path[i]][path[i + 1]];
		}
	}

	/**
	 * ���㻨��
	 * 
	 * @param obj
	 * @param eachband
	 * @return
	 */
	private static int calculateCost(Object[] obj, int eachband) {
		int[] path = pathToArray(obj);
		int sum = 0;
		for (int i = 0; i < path.length - 1; i++) {
			sum += eachband * FormatData.costTable[path[i]][path[i + 1]];
		}
		return sum;
	}

	private static int[] pathToArray(Object[] obj) {
		int len = obj.length;
		int arr[] = new int[len];
		for (int i = 0; i < len; i++) {
			Node cnode = (Node) obj[i];
			arr[i] = cnode.getId();
		}
		return arr;
	}

	/**
	 * ����·�����ϵ����·������
	 * 
	 * @param pathList
	 * @return
	 */
	private static int getMinLenOfPaths(ArrayList<Object[]> pathList) {
		int minlen = 15, minindex = 0;
		for (int i = 0; i < pathList.size(); i++) {
			if (minlen >= pathList.get(i).length) {
				minlen = pathList.get(i).length;
				minindex = i;
			}
		}
		return minindex;
	}

	/**
	 * ����·����С�ϴ���
	 * 
	 * @param path
	 *            ·��
	 * @return ��С����
	 */
	public static int getMinBandOfPath(Object[] path) {
		int index[] = pathToArray(path);
		int len = index.length;
		int bands[] = new int[len - 1];
		for (int i = 0; i < len - 1; i++) {
			bands[i] = FormatData.bandTable[index[i]][index[i + 1]];
		}
		Arrays.sort(bands);
		return bands[0];
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
				if (stack.size() < STACKSIZE)
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

	/* ��ӡ��� */
	public static void showResult() {
		for (int i = 0; i < result.size(); i++) {
			Object[] path = result.get(i);
			System.out.print("Cost: " + ecost[i] + "; UseBand: " + useband[i] + "; ");
			int[] arr = pathToArray(path);
			for (int j = 0; j < arr.length; j++) {
				if (j < (path.length - 1))
					System.out.print(arr[j] + "->");
				else
					System.out.print(arr[j]);
			}
			System.out.println();
		}
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
