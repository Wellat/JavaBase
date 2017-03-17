package hemi.battle.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

import hemi.battle.huawei.Official.Main;

public class Deploy_exh {
	public static void main(String[] args) {
		String graphFilePath = "D:\\Documents\\华为软赛\\case_example\\case0.txt";
		String resultFilePath = "D:\\Documents\\华为软赛\\result\\test.txt";
		String[] input = { graphFilePath, resultFilePath };
		Main.main(input);
	}

	/* 临时保存路径节点的栈 */
	public static Stack<Node> stack;
	/* 存储初选路径集合 */
	public static ArrayList<Object[]> sers = new ArrayList<Object[]>();
	/* 转储路径数 */
	public static final int TRAIN = 20;

	public static final int STACKSIZE = 7;
	/* 保存最终路径 */
	public static ArrayList<Object[]> result = new ArrayList<Object[]>();

	public static int ecost[] = new int[100];

	public static int useband[] = new int[100];

	public static int costIndex = 0;

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
		int endid, needband;
		//对每一个目标节点 
		for (int d = 0; d < FormatData.consumerList.size(); d++) {// FormatData.consumerList.size()
			stack = new Stack<Node>();
			endid = FormatData.consumerList.get(d).getNetNode();
			needband = FormatData.consumerList.get(d).getNeedBand();
			// band = FormatData.consumerList.get(d).getNeedBand();// 所需带宽
			//对每一个起点 
			// 此处暂定起点固定为3个
			for (int i = 0; i < 6; i++) {
				getPaths(FormatData.nodes[index[i]], null, FormatData.nodes[index[i]], FormatData.nodes[endid]);
			}
			// 链路数众多，需优选.
			// 确定最终路径（值代入），并更新带宽table
			selectBestPath(sers, needband);
		}
		// 打印结果
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
	 * 确定路径函数
	 * 
	 * @param pathList
	 *            待选路径集合
	 * @param needband
	 *            客户所需带宽
	 */
	private static void selectBestPath(ArrayList<Object[]> pathList, int needband) {
		int count = 0;
		ArrayList<Object[]> pathtemp = new ArrayList<Object[]>();
		// 转储路径
		for (int i = 0; i < pathList.size() && count < TRAIN; i++) {
			int min = getMinLenOfPaths(pathList);
			pathtemp.add(pathList.get(min));
			count++;
			pathList.remove(min);
		}

		int cband = 0;
		// 分别算cost
		for (int j = 0; j < pathtemp.size(); j++) {
			Object[] path = pathtemp.get(j);
			cband = getMinBandOfPath(path);
			// 若本条路径带宽满足需求——直接算最佳，否则拆分
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
	 * 计算花费
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
	 * 计算路径集合的最短路径长度
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
	 * 返回路径最小上带宽
	 * 
	 * @param path
	 *            路径
	 * @return 最小带宽
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
				if (stack.size() < STACKSIZE)
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

	/* 打印结果 */
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
