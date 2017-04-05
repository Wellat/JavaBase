package hemi.battle.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import hemi.battle.huawei.Official.Main;

public class Deploy {
	/** ------------- test main ------------- **/
	public static void main(String[] args) {
//		String graphFilePath = "D:\\Documents\\CraftCodeHUAWEI\\case_example\\case2.txt";
		String graphFilePath = "D:\\Documents\\CraftCodeHUAWEI\\case_example\\first\\case2.txt";

		String resultFilePath = "D:\\Documents\\CraftCodeHUAWEI\\result\\test.txt";
		String[] input = { graphFilePath, resultFilePath };
		Main.main(input);
	}

	/** -------------static data begin------------- **/
	public static int SumOfNetNode = 0;
	public static int SumOfLink = 0;
	public static int SumOfConsumer = 0;
	public static int CostOfPerServer = 0;
	public static final int MAXPROCESSBAND = 200;
	public static final int LINKLEN = 150;
	public static List<Consumer> consumerList = new ArrayList<Consumer>();

	public static int[][] bandTable;
	public static int[][] costTable;

	public static int[][] bandTableBackup;
	public static Node[] nodes;

	public static ArrayList<Integer[]> resultPath;
	public static ArrayList<Integer[]> resultPathBest = new ArrayList<Integer[]>();

	public static int maxCost = 0;
	/* */
	public static int[] serverIndex;

	public static Container[] ppcontain;

	/** -------------static data end------------- **/

	public static String[] deployServer(String[] graphContent) {

		format(graphContent);
		ppcontain = new Container[SumOfNetNode];
		creatContainer();
		serverIndex = sortByContainerCount();
		bandTableBackup = bandTable.clone();
		maxCost = SumOfConsumer * CostOfPerServer;
		/** ===========TEST BEGIN=========== **/
//		for (int i = 0; i < 2; i++) {
//			Container con = ppcontain[serverIndex[i]];
//			int[] par = con.getParent();
//			int[] des = con.getDestination();
//			String[] contents = new String[10];
//			String line = new String();
//			for (int j = 0; par[j] != -1; j++) {
//				line = "==================" + serverIndex[i] + "-->" + par[j] + "-->" + des[j] + "\n"
//						+ bandTable[serverIndex[i]][par[j]] + " " + bandTable[par[j]][des[j]] + " need="
//						+ findConsumerByNode(des[j]).getNeedBand() + "\n" + costTable[serverIndex[i]][par[j]] + " "
//						+ costTable[par[j]][des[j]];
//				contents[j] = line;
//			}
//			for (String lin : contents) {
//				 System.out.println(lin);
//			}
//		}
		//
		/** ===========TEST END=========== **/
		
		for (int i = 0; i < consumerList.size(); i++) {
			Consumer consumer = consumerList.get(i);
			ArrayList<Integer> path = new ArrayList<Integer>();
			path.add(consumer.getNetNode());
			path.add(consumer.getId());
			path.add(consumer.getNeedBand());
			resultPathBest.add(pathToArray(path));
		}

		for (int times = 6; times < SumOfConsumer; times++) {
			// 初始化
			int cost = 0;
			bandTable = bandTableBackup.clone();
			resultPath = new ArrayList<Integer[]>();
			for (int i = 0; i < consumerList.size(); i++) {
				Consumer consumer = consumerList.get(i);
				consumer.setFlag(1);
			}

			/** assign alone first **/
			buildDirectPath(times);
			
			/** build path **/
			buildRemotePath(times);
			
			
			
			/** ===========TEST BEGIN=========== **/
			
//			for (int i = 0; i < resultPath.size(); i++) {
//				Integer[] path = resultPath.get(i);
//				for (int j = 0; j < path.length; j++) {
//					if (j < (path.length - 3))
//						System.out.print(path[j] + "->");
//					else
//						System.out.print(path[j] + " ");
//				}
//				System.out.println();
//			}
			/** ===========TEST END=========== **/
			
			selectPath(0);
			
			
//			System.out.println("-------------------------------------------");
			/** ===========TEST BEGIN=========== **/
//			for (int i = 0; i < resultPath.size(); i++) {
//				Integer[] path = resultPath.get(i);
//				for (int j = 0; j < path.length; j++) {
//					if (j < (path.length - 3))
//						System.out.print(path[j] + "->");
//					else
//						System.out.print(path[j] + " ");
//				}
//				System.out.println();
//			}
			/** ===========TEST END=========== **/
			
			
			for (int g = 0; g < SumOfConsumer; g++) {
				if (consumerList.get(g).getLeftBand()>0) {
					consumerList.get(g).setFlag(1);
					int des = consumerList.get(g).getNetNode();
					for(int i=0;i<resultPath.size();i++){
						Integer[] path = resultPath.get(i);
						if(path[path.length-3]==des){
							resultPath.remove(i);
							i--;
						}
					}
				}
			}
			
			for (int k = 0; k < SumOfConsumer; k++) {
				if (consumerList.get(k).isServer()) {
					ArrayList<Integer> newPath = new ArrayList<Integer>();
					newPath.add(consumerList.get(k).getNetNode());
					newPath.add(consumerList.get(k).getId());
					newPath.add(consumerList.get(k).getNeedBand());
					resultPath.add(pathToArray(newPath));
				}
			}

			for(int k=0;k<resultPath.size();k++){
				Integer[] path = resultPath.get(k);
				if(path[path.length-1]==0){
					resultPath.remove(k);
					k--;
				}
			}
			
			/** ===========TEST BEGIN=========== **/
//			System.out.println("========================================");
//			for (int i = 0; i < resultPath.size(); i++) {
//				Integer[] path = resultPath.get(i);
//				for (int j = 0; j < path.length; j++) {
//					if (j < (path.length - 3))
//						System.out.print(path[j] + "->");
//					else
//						System.out.print(path[j] + " ");
//				}
//				System.out.println();
//			}
			/** ===========TEST END=========== **/
			
			cost = calculateCost(times);
			if (cost < maxCost) {
				resultPathBest = (ArrayList<Integer[]>) resultPath.clone();
				maxCost = cost;
			}
		}

		System.out.println("minCost= " + maxCost);
		showResult();
		resultPath = null;
		
		return returnAns();
	}

	private static void selectPath(int begin) {
		for (int f = begin; f < resultPath.size(); f++) {
			Integer[] pathf = resultPath.get(f);
			int desnode = pathf[pathf.length - 3];
			Consumer consumer = findConsumerByNode(desnode);
			int minPathCost = calculatePathCost(pathf);
			int minPathCostIndex = f;
			int flag=0;
					
			if (consumer.getLeftBand() == 0){
				
				continue;
			}
			if(pathf[pathf.length-1]>0)
				continue;
			for (int s = f + 1; s < resultPath.size(); s++) {
				Integer[] paths = resultPath.get(s);
				if (desnode == paths[paths.length - 3]) {
					if(paths[paths.length-1]>0)
						continue;
					if (minPathCost > calculatePathCost(paths) && getMinBandOfPath(paths)>0) {
						minPathCost = calculatePathCost(paths);
						minPathCostIndex = s;
						flag = 1;
					} 
				}
			}
			
			Integer[] path = resultPath.get(minPathCostIndex);
			int deltBand = getMinBandOfPath(resultPath.get(minPathCostIndex));
			if (deltBand >= consumer.getLeftBand()) {
				deltBand = consumer.getLeftBand();
			}
			if (deltBand != 0) {
				if(CostOfPerServer <= calculatePathCost(path) * consumer.getNeedBand()){
					consumer.setFlag(1);
					f--;
					resultPath.remove(minPathCostIndex);
					continue;
				}
				updateBandTable(resultPath.get(minPathCostIndex), deltBand);
				consumer.setLeftBand(deltBand);
				path[path.length-1] = deltBand;
				consumer.setFlag(0);
			}
			if (consumer.getLeftBand() > 0 && flag == 1){
				f--;
			}
		}
		
	}

	/** ----------------------------------------------------------------------- **/
	private static int calculatePathCost(Integer[] path) {
		int sum = 0;
		for (int i = 0; i < path.length - 3; i++) {
			sum += costTable[path[i]][path[i + 1]];
		}
		return sum;
	}

	public static String[] returnAns() {
		String[] content = new String[resultPathBest.size() + 2];

		content[0] = String.valueOf(resultPathBest.size());
		content[1] = "";

		for (int i = 0; i < resultPathBest.size(); i++) {
			Integer[] pa = resultPathBest.get(i);
			String line = new String();
			for (int j = 0; j < pa.length - 1; j++) {
				line += pa[j] + " ";
			}
			line += pa[pa.length - 1];
			content[i + 2] = line;
		}
		return content;
	}

	private static int calculateCost(int times) {
		HashSet<Integer> set = new HashSet<Integer>();
		int cost = 0;
		for (int r = 0; r < resultPath.size(); r++) {
			Integer[] path = resultPath.get(r);
			if (path.length > 3) {
				for (int z = 0; z < path.length - 3; z++) {
					cost += costTable[path[z]][path[z + 1]] * path[path.length - 1];
				}
			}
			set.add(path[0]);
		}
		cost += CostOfPerServer * set.size();
		System.out.println(times+"; Server Sum= " + set.size()+"   Cost= " + cost);
		return cost;
	}

	private static void buildRemotePath(int times) {
		for (int i = 0; i < times; i++) {
			// for each container/possible server
			int server = serverIndex[i];
			Container container = ppcontain[server];
			int des[] = container.getDestination();
			// make path completion
			for (int j = 0; j < LINKLEN; j++) {
				if (des[j] == -1)
					break;
				Consumer consumer = findConsumerByNode(des[j]);
				if (consumer.getNeedBand() > MAXPROCESSBAND)
					continue;
				
//				while (consumer.getLeftBand() != 0) {

				HashSet<Integer> newnodes = findCommonNode(server, consumer.getNetNode());
				int d = 0, flag = 0,e=0;
				
				while (newnodes.size()==0 && d < nodes[consumer.getNetNode()].getRelationNodes().size()) {
					if (nodes[consumer.getNetNode()].getRelationNodes().get(d) == server) {
						//DirectPath?
						d++;
						continue;
					}
					newnodes = findCommonNode(server, nodes[consumer.getNetNode()].getRelationNodes().get(d++));
					flag = 1;
				}
				while (newnodes.size()==0 && e < nodes[server].getRelationNodes().size()) {
					if (nodes[server].getRelationNodes().get(e) == des[j]) {
						e++;
						continue;
					}
					newnodes = findCommonNode(nodes[server].getRelationNodes().get(e++), des[j]);
					flag = 2;
				}
				if (newnodes.size()==0) {
//						updateResultPathAndBandTable(consumer.getNetNode());

					continue;
				}
				
				for(Integer newnode:newnodes){
					ArrayList<Integer> newPath = new ArrayList<Integer>();	
					newPath.add(server);
//                    switch (flag){
//                        case 0:
//                            newPath.add(newnode);
//                        case 1:
//                            newPath.add(newnode);
//                            newPath.add(nodes[consumer.getNetNode()].getRelationNodes().get(--d));
//                        case 2:
//                            newPath.add(nodes[server].getRelationNodes().get(--e));
//                            newPath.add(newnode);
//
//                    }


					if (flag == 0) {
						newPath.add(newnode);
					} else if(flag==1 && d>0){
						newPath.add(newnode);
						newPath.add(nodes[consumer.getNetNode()].getRelationNodes().get(--d));
					} else if(flag==2 && e>0){
						newPath.add(nodes[server].getRelationNodes().get(--e));
						newPath.add(newnode);
					}
					newPath.add(des[j]);
					newPath.add(findConsumerByNode(des[j]).getId());
					newPath.add(0);
					resultPath.add(pathToArray(newPath));
				}
				
			}
		}
		
	}

	public static void showResult() {
		for (int i = 0; i < resultPathBest.size(); i++) {
			Integer[] path = resultPathBest.get(i);
			for (int j = 0; j < path.length; j++) {
				if (j < (path.length - 3))
					System.out.print(path[j] + "->");
				else
					System.out.print(path[j] + " ");
			}
			System.out.println();
		}
	}

	private static void updateResultPathAndBandTable(int nodeId) {
		for (int i = 0; i < resultPath.size(); i++) {
			Integer[] path = resultPath.get(i);
			if (path[path.length - 3] == nodeId) {
				updateBandTable(path, (-1) * path[path.length - 1]);
				resultPath.remove(i);
				i--;
			}
		}
		findConsumerByNode(nodeId).setFlag(1);
	}

	private static HashSet<Integer> findCommonNode(int begin, int end) {
		Node nodebegin = nodes[begin];
		Node nodeend = nodes[end];
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nodebegin.getRelationNodes().size(); i++) {
			ArrayList<Integer> begins = nodebegin.getRelationNodes();
			for (int j = 0; j < nodeend.getRelationNodes().size(); j++) {
				ArrayList<Integer> ends = nodeend.getRelationNodes();
				if (begins.get(i) == ends.get(j)) {
//					if (bandTable[begin][begins.get(i)] == 0 || bandTable[begins.get(i)][end] == 0)
//						continue;
					set.add(begins.get(i));
				}
			}
		}
		return set;
	}

	public static void buildDirectPath(int serverSum) {
		for (int i = 0; i < serverSum; i++) {
			int serverid = serverIndex[i];
			Container container = ppcontain[serverid];
			int par[] = container.getParent();
			int des[] = container.getDestination();
			for (int j = 0; j < LINKLEN; j++) {
				if (par[j] == -1)
					break;
				Consumer consumer = findConsumerByNode(des[j]);
				if (consumer.getNeedBand() > MAXPROCESSBAND ) {
					continue;
				}
				ArrayList<Integer> path = new ArrayList<Integer>();
				if (par[j] == des[j]) {
					path.add(serverid);
					path.add(des[j]);
				} else {
					path.add(serverid);
					path.add(par[j]);
					path.add(des[j]);
				}
				path.add(findConsumerByNode(des[j]).getId());
				path.add(0);
				resultPath.add(pathToArray(path));
			}
		}
	}

	private static Integer[] pathToArray(ArrayList<Integer> obj) {
		int len = obj.size();
		Integer arr[] = new Integer[len];
		for (int i = 0; i < len; i++) {
			arr[i] = obj.get(i);
		}
		return arr;
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
		for (int i = 0; i < path.size() - 3; i++) {
			bandTable[path.get(i)][path.get(i + 1)] -= delt;
		}
	}

	private static void updateBandTable(Integer[] path, int delt) {
		for (int i = 0; i < path.length - 3; i++) {
			bandTable[path[i]][path[i + 1]] -= delt;
		}
	}

	public static int getMinBandOfPath(Integer[] path) {
		int len = path.length;
		int bands[] = new int[len - 3];
		for (int i = 0; i < len - 3; i++) {
			bands[i] = bandTable[path[i]][path[i + 1]];
		}
		Arrays.sort(bands);
		return bands[0];
	}

	public static int getMinBandOfPath(ArrayList<Integer> path) {
		int len = path.size();
		int bands[] = new int[len - 3];
		for (int i = 0; i < len - 3; i++) {
			bands[i] = bandTable[path.get(i)][path.get(i + 1)];
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
				int des[] = creatArray(LINKLEN);
				int par[] = creatArray(LINKLEN);
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
					int des[] = creatArray(LINKLEN);
					int par[] = creatArray(LINKLEN);
					int childid = parentNode.getRelationNodes().get(m);
					Container container = new Container();
					if (ppcontain[childid] != null) {
						container.setCount(ppcontain[childid].getCount() + 1);
						des = ppcontain[childid].getDestination();
						par = ppcontain[childid].getParent();
						for (int a = 0; a < des.length; a++) {
							if (des[a] == -1) {
								des[a] = destid;
								par[a] = parentid;
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
