package hemi.battle.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hemi.battle.huawei.Official.Main;

public class Deploy {
	/** ------------- test main ------------- **/
	public static void main(String[] args) {
		String graphFilePath = "D:\\Documents\\华为软赛\\case_example\\case1.txt";
		String resultFilePath = "D:\\Documents\\华为软赛\\result\\test.txt";
		String[] input = { graphFilePath, resultFilePath };
		Main.main(input);
	}

	/** -------------static data begin------------- **/
	public static int SumOfNetNode = 0;
	public static int SumOfLink = 0;
	public static int SumOfConsumer = 0;
	public static int CostOfPerServer = 0;

	public static List<Consumer> consumerList = new ArrayList<Consumer>();

	public static int[][] bandTable;
	public static int[][] costTable;

	public static Node[] nodes;

	// public static Map<Integer,Integer> target;
	/* */
	public static int[] serverIndex;

	public static Container[] ppcontain;

	/** -------------static data end------------- **/

	public static String[] deployServer(String[] graphContent) {

		format(graphContent);
		ppcontain = new Container[SumOfNetNode];
		creatContainer();
		serverIndex = sortByContainerCount();

		for (int i = 0; i < SumOfConsumer; i++) {
			// for each container/possible server
			Container container = ppcontain[serverIndex[i]];
			for (int j = 0; j < 20; j++) {
				
				int par[] = container.getParent();
				if (par[j] == -1)
					break;
				int des[] = container.getDestination();
				ArrayList<Integer> path = new ArrayList<Integer>();
				if (par[j] == des[j]) {
					// use this link,update bandTable
					path.add(serverIndex[i]);
					path.add(des[j]);
					
				} else {
					path.add(serverIndex[i]);
					path.add(par[j]);
					path.add(des[j]);
				}
				getMinBandOfPath(path)
				int deltBand = bandTable[serverIndex[i]][des[j]];
				Consumer consumer = findConsumerByNode(des[j]);
				if (deltBand > consumer.getNeedBand()) {
					deltBand = consumer.getNeedBand();
				}
				updateBandTable(path, deltBand);
				nodes[des[j]].setFlag(0);
				nodes[serverIndex[i]].setFlag(1);
				consumer.setLeftBand(leftBand);
			}

		}

		return null;//final return.
	}

	private static Consumer findConsumerByNode(int netNode) {
		for (int i = 0; i < SumOfConsumer; i++) {
			if (netNode == consumerList.get(i).getNetNode()) {
				return consumerList.get(i);
			}
		}
		return null;
	}

	private static void updateBandTable(ArrayList<Integer> path, int delt) {
		for (int i = 0; i < path.size() - 1; i++) {
			bandTable[path.get(i)][path.get(i + 1)] -= delt;
		}
	}

	public static int getMinBandOfPath(ArrayList<Integer> path) {
		int len = path.size();
		int bands[] = new int[len - 1];
		for (int i = 0; i < len - 1; i++) {
			bands[i] = bandTable[path.get(i)][path.get(i+1)];
		}
		Arrays.sort(bands);
		return bands[0];
	}

	private static int[] sortByContainerCount() {
		int[] temp = new int[SumOfNetNode];
		for (int i = 0; i < SumOfNetNode; i++) {
			if (ppcontain[i] != null) {
				temp[i] = ppcontain[i].getCount();
			}
		}
		return myBubsort(temp);
	}

	/**
	 * BubbleSort
	 * 
	 * @param a
	 *            Array
	 * @return origin index
	 */
	private static int[] myBubsort(int[] a) {
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

	/**
	 * count for the secondary node
	 */
	private static void creatContainer() {
		/* relation node of direct node in nodeFirst */
		for (int i = 0; i < SumOfConsumer; i++) {
			int destid = consumerList.get(i).getNetNode();
			Node destNode = nodes[destid];
			for (int j = 0; j < destNode.getRelationNodes().size(); j++) {
				int childid = destNode.getRelationNodes().get(j);
				int des[] = creatArray(20);
				int par[] = creatArray(20);
				Container container = new Container();
				if (ppcontain[childid] != null) {
					container.setCount(ppcontain[childid].getCount() + 1);
					des = ppcontain[childid].getDestination();
					par = ppcontain[childid].getParent();
					for (int t = 0; t < des.length; t++) {
						if (des[t] == -1) {
							des[t] = destid;
							par[t] = destid;
							container.setDestination(des);
							container.setParent(par);
							break;
						}
					}
				} else {
					container.setCount(1);
					par[0] = destid;
					container.setParent(par);
					des[0] = destid;
					container.setDestination(des);
				}
				ppcontain[childid] = container;
			}
		}
		/* relation node of direct node in nodeSecond */
		for (int i = 0; i < SumOfConsumer; i++) {
			int destid = consumerList.get(i).getNetNode();
			Node destNode = nodes[destid];
			for (int j = 0; j < destNode.getRelationNodes().size(); j++) {
				int parentid = destNode.getRelationNodes().get(j);
				Node parentNode = nodes[parentid];
				for (int m = 0; m < parentNode.getRelationNodes().size(); m++) {
					int des[] = creatArray(20);
					int par[] = creatArray(20);
					int childid = parentNode.getRelationNodes().get(m);
					Container container = new Container();
					if (ppcontain[childid] != null) {
						container.setCount(ppcontain[childid].getCount() + 1);
						des = ppcontain[childid].getDestination();
						par = ppcontain[childid].getParent();
						for (int t = 0; t < des.length; t++) {
							if (des[t] == -1) {
								des[t] = destid;
								par[t] = parentid;
								container.setDestination(des);
								container.setParent(par);
								break;
							}
						}
					} else {
						container.setCount(1);
						par[0] = parentid;
						container.setParent(par);
						des[0] = destid;
						container.setDestination(des);
					}
					ppcontain[childid] = container;
				}
			}
		}
	}

	private static int[] creatArray(int t) {
		int[] arr = new int[t];
		for (int i = 0; i < t; i++) {
			arr[i] = -1;
		}
		return arr;
	}

	public static void format(String[] graphContent) {
		String[] data = graphContent;
		for (int c = 0; c < data.length; c++) {
			String[] line = data[c].split(" ");
			if (c == 0) {
				SumOfNetNode = Integer.valueOf(line[0]);
				SumOfLink = Integer.valueOf(line[1]);
				SumOfConsumer = Integer.valueOf(line[2]);
				bandTable = new int[SumOfNetNode][SumOfNetNode];
				costTable = new int[SumOfNetNode][SumOfNetNode];
			} else if (c == 2) {
				CostOfPerServer = Integer.valueOf(line[0]);
			} else if (c > 3 && c < (4 + SumOfLink)) {
				int i = Integer.valueOf(line[0]);
				int j = Integer.valueOf(line[1]);
				bandTable[j][i] = bandTable[i][j] = Integer.valueOf(line[2]);
				costTable[j][i] = costTable[i][j] = Integer.valueOf(line[3]);
			} else if (c > (4 + SumOfLink)) {
				Consumer consumer = new Consumer();
				consumer.setId(Integer.valueOf(line[0]));
				consumer.setNetNode(Integer.valueOf(line[1]));
				consumer.setNeedBand(Integer.valueOf(line[2]));
				consumerList.add(consumer);
			}
		}
		nodes = new Node[SumOfNetNode];
		for (int i = 0; i < SumOfNetNode; i++) {
			nodes[i] = new Node();
			nodes[i].setId(i);
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int j = 0; j < SumOfNetNode; j++) {
				if (bandTable[i][j] != 0) {
					list.add(j);
				}
			}
			nodes[i].setRelationNodes(list);
			list = null;
		}
		// mark the server node.
		for (int i = 0; i < SumOfConsumer; i++) {
			nodes[consumerList.get(i).getNetNode()].setFlag(1);
		}
	}

	public static String[] outputDemo(String[] graphContent) {
		String[] content = new String[graphContent.length];

		List<String> list = new ArrayList<String>();
		for (int i = 1; i < graphContent.length; i++) {

			if (graphContent[i].contains(" ") && graphContent[i].split(" ").length == 3) {
				String[] array = graphContent[i].split(" ");
				String content1 = array[0];
				String content2 = array[1];
				String content3 = array[2];

				list.add(content2 + " " + content1 + " " + content3);
			}
		}
		content[0] = String.valueOf(list.size());
		content[1] = "";

		for (int i = 0; i < list.size(); i++) {
			content[i + 2] = list.get(i);
		}
		return content;
	}

}
