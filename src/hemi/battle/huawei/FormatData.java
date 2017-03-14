package hemi.battle.huawei;

import java.util.ArrayList;
import java.util.List;

public class FormatData {
	public static int SumOfNetNode = 0;
	public static int SumOfLink = 0;
	public static int SumOfConsumer = 0;
	public static int CostOfPerServer = 0;

	public static List<Consumer> consumerList = new ArrayList<Consumer>();

	public static int[][] bandTable;
	public static int[][] costTable;

	public static Node[] nodes;

	public static void format(String[] graphContent) {
		String[] data = graphContent.clone();
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
				if(bandTable[i][j]!=0){
					list.add(j);
				}
			}
			nodes[i].setRelationNodes(list);
			list = null;
		}
	}

}
