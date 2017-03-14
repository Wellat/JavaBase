package hemi.battle.huawei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import hemi.battle.huawei.Official.Main;

public class Deploy {
	public static void main(String[] args) {
		String graphFilePath = "D:\\Documents\\华为软赛\\case_example\\case1.txt";
		String resultFilePath = "D:\\Documents\\华为软赛\\result\\test.txt";
		String[] input = { graphFilePath, resultFilePath };
		Main.main(input);
	}

	/**
	 * 你需要完成的入口 <功能详细描述>
	 * 
	 * @param graphContent
	 *            用例信息文件
	 * @return [参数说明] 输出结果信息
	 * @see [类、类#方法、类#成员]
	 */
	public static String[] deployServer(String[] graphContent) {
		/** do your work here **/
		FormatData.format(graphContent);
		int[] index = sortNodeLinkCount(FormatData.costTable);// 按链路数降序排列
		int endid, band;
		/* 对每一个目标节点 */
		for (int d = 0; d < FormatData.consumerList.size(); d++) {
			endid = FormatData.consumerList.get(d).getNetNode();
			band = FormatData.consumerList.get(d).getNeedBand();// 所需带宽
			/* 对每一个起点 */
			// 此处暂定起点固定为5个
			for (int i = 0; i < 5; i++) {
				getPaths(FormatData.nodes[index[i]], null, FormatData.nodes[index[i]], FormatData.nodes[endid]);
			}
			// 链路数众多，需优选.
			// 确定最终路径（值代入），并更新带宽table
			// 释放内存
			sers = null;
		}

		return new String[] { "17", "\r\n", "0 8 0 20" };
	}

	/* 临时保存路径节点的栈 */
	public static Stack<Node> stack = new Stack<Node>();
	/* 存储路径的集合 */
	public static ArrayList<Object[]> sers = new ArrayList<Object[]>();

	/**
	 * 返回路径最小上带宽
	 * 
	 * @param path 
	 *            路径
	 * @return 最小带宽
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
	 *            currentNode当前的起始节点
	 * @param pNode
	 *            previousNode当前起始节点的上一节点
	 * @param sNode
	 *            startNode最初的起始节点
	 * @param eNode
	 *            endNode终点
	 * @return
	 */
	public static boolean getPaths(Node cNode, Node pNode, Node sNode, Node eNode) {
		Node nNode = null;
		/* 如果符合条件判断说明出现环路，不能再顺着该路径继续寻路，返回false */
		if (cNode != null && pNode != null && cNode == pNode)
			return false;

		if (cNode != null) {
			int i = 0;
			/* 起始节点入栈 */
			stack.push(cNode);
			/* 如果该起始节点就是终点，说明找到一条路径 */
			if (cNode == eNode) {
				/* 转储并打印输出该路径，返回true */
				if (stack.size() < 5)
					showAndSavePath();
				return true;
			}
			/* 如果不是,继续寻路 */
			else {
				/*
				 * 从与当前起始节点cNode有连接关系的节点集中按顺序遍历得到一个节点 作为下一次递归寻路时的起始节点
				 */
				nNode = FormatData.nodes[FormatData.nodes[cNode.getId()].getRelationNodes().get(i)];
				while (nNode != null) {
					/*
					 * 如果nNode是最初的起始节点或者nNode就是cNode的上一节点或者nNode已经在栈中 ， 说明产生环路
					 * ，应重新在与当前起始节点有连接关系的节点集中寻找nNode
					 */
					if (pNode != null && (nNode == sNode || nNode == pNode || isNodeInStack(nNode))) {
						i++;
						if (i >= cNode.getRelationNodes().size())
							nNode = null;
						else
							nNode = FormatData.nodes[FormatData.nodes[cNode.getId()].getRelationNodes().get(i)];
						continue;
					}
					/* 以nNode为新的起始节点，当前起始节点cNode为上一节点，递归调用寻路方法 */
					if (getPaths(nNode, cNode, sNode, eNode))/* 递归调用 */
					{
						/* 如果找到一条路径，则弹出栈顶节点 */
						stack.pop();
					}
					/* 继续在与cNode有连接关系的节点集中测试nNode */
					i++;
					if (i >= cNode.getRelationNodes().size())
						nNode = null;
					else
						nNode = FormatData.nodes[FormatData.nodes[cNode.getId()].getRelationNodes().get(i)];
				}
				/*
				 * 当遍历完所有与cNode有连接关系的节点后， 说明在以cNode为起始节点到终点的路径已经全部找到
				 */
				stack.pop();
				return false;
			}
		} else
			return false;
	}

	/* 判断节点是否在栈中 */
	public static boolean isNodeInStack(Node node) {
		Iterator<Node> it = stack.iterator();
		while (it.hasNext()) {
			Node node1 = (Node) it.next();
			if (node == node1)
				return true;
		}
		return false;
	}

	/* 此时栈中的节点组成一条所求路径，转储并打印输出 */
	public static void showAndSavePath() {
		Object[] o = stack.toArray();
		for (int i = 0; i < o.length; i++) {
			Node nNode = (Node) o[i];
			if (i < (o.length - 1))
				System.out.print(nNode.getId() + "->");
			else
				System.out.print(nNode.getId());
		}
		sers.add(o); /* 转储 */
		System.out.println("\n");
	}

	/*
	 * 按链路数排列节点（多->少）
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
	 * 冒泡排序，大->小，返回排序后数组的原位置
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
