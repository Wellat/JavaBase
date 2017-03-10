package hemi.battle.huawei;

public class Deploy
{
    /**
     * 你需要完成的入口
     * <功能详细描述>
     * @param graphContent 用例信息文件
     * @return [参数说明] 输出结果信息
     * @see [类、类#方法、类#成员]
     */
    public static String[] deployServer(String[] graphContent)
    {
        /**do your work here**/		
		FormatData.format(graphContent);
		int[] index = sortNodeLinkCount(FormatData.costTable);
        return new String[]{"17","\r\n","0 8 0 20"};
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
		for(int t=0;t<len;t++){
			out[t]=t;
		}
		for (i = 0; i < len - 1; i++) {
			for (j = 0; j < len - 1 - i; j++) {
				if (a[j] < a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					//swap index too
					temp = out[j];
					out[j]=out[j+1];
					out[j+1]=temp;
				}
			}
		}
		return out;
	}

}
