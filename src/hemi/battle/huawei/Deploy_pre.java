package hemi.battle.huawei;

import java.util.ArrayList;

import hemi.battle.huawei.Official.Main;

public class Deploy_pre {
	public static void main(String[] args) {
		String graphFilePath = "D:\\Documents\\��Ϊ����\\case_example\\case0.txt";
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
		FormatData.format(graphContent);
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(String.valueOf(FormatData.consumerList.size()));
		lines.add("");
		for (int d = 0; d < FormatData.consumerList.size(); d++) {
			int nid = FormatData.consumerList.get(d).getNetNode();
			int cid = FormatData.consumerList.get(d).getId();
			int nband = FormatData.consumerList.get(d).getNeedBand();
			lines.add(String.valueOf(nid+" "+cid+" "+nband));
		}
		return lines.toArray(new String[lines.size()]);
		
	}
}
