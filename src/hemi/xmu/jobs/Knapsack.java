package hemi.xmu.jobs;


/**
 * 背包问题
 * Created by Vanguard on 2017/4/7.
 */
public class Knapsack {
    private final int MIN = Integer.MIN_VALUE;

    @org.junit.Test
    public void test() {
        int[] w = {2,6,5,4,7};
        int[] v = {5,3,5,4,15};
//        knapsackOptimal(10, w, w);
        completePack(10,w,v);
    }




    /**
     * 01背包-容量压缩
     * 求解将哪些物品装入背包可使价值总和最大（每种物品一件）
     * @param c      包容量
     * @param weight 各物品质量
     * @param value  各物品价值
     */
    public void knapsackOptimal(int c, int[] weight, int[] value) {
        int n = weight.length; //物品数量
        int[] w = new int[n + 1];
        int[] v = new int[n + 1];
        int[][] G = new int[n + 1][c + 1];
        int[] count = new int[c+1];
        for (int i = 1; i < n + 1; i++) {
            w[i] = weight[i - 1];
            v[i] = value[i - 1];
        }

        //初始化values[0...c]=0————解决在不超过背包容量的情况下，最多能获得多少价值
        //原因：如果背包并非必须被装满，那么任何容量的背包都有一个合法解“什么都不装”，这个解的价值为0，所以初始时状态的值也就全部为0了
        int[] values = new int[c + 1];
        //初始化values[0]=0，其它全为负无穷————解决在恰好装满背包的情况下，最多能获得多少价值的问题
        //原因：只有容量为0的背包可以什么物品都不装就能装满，此时价值为0，其它容量背包均无合法的解，属于未定义的状态，应该被赋值为负无穷
//        for (int i = 1; i < values.length; i++) {
//            values[i] = MIN;
//        }

        for (int i = 1; i < n + 1; i++) {
//            for (int t = w[i]; t <= c; t++) { //为完全背包问题，但是打印输出有问题
            for (int t = c; t >= w[i]; t--) {
                if (values[t] < values[t - w[i]] + v[i]) {
                    values[t] = values[t - w[i]] + v[i];
                    G[i][t] = 1;
                    count[t] = countNum(G,w,c,n);
                }
                /**
                 *
                 */
/*                else if(values[t] == values[t - w[i]] + v[i]){
                    G[i][t] = 1;
                    if(count[t]<countNum(G,w,c,n)){
                        count[t]=countNum(G,w,c,n);
                        values[t] = values[t - w[i]] + v[i];
                    }else{
                        G[i][t] = 0;
                    }
                }*/
            }
        }
        System.out.println("最大价值为： " + values[c]);
        System.out.print("装入背包的物品编号为： ");
        /*
            输出顺序:逆序输出物品编号
            注意：这里另外开辟数组G[i][v],标记上一个状态的位置
            G[i][v] = 1:表示物品i放入背包了，上一状态为G[i - 1][v - w[i]]
            G[i][v] = 0:表示物品i没有放入背包，上一状态为G[i - 1][v]
        */
        int i = n;
        int j = c;
        while (i > 0) {
            if (G[i][j] == 1) {
                System.out.print(i + " ");
                j -= w[i];
            }
            i--;
        }
    }

    /**
     * 完全背包问题
     *
     * 物品有无限件可选，求获得的最大价值
     * @param c 背包容量
     * @param weight
     * @param value
     */
    public void completePack(int c, int[] weight, int[] value){
        int n = weight.length; //物品数量
        int[] w = new int[n + 1];
        int[] v = new int[n + 1];
        int[][] G = new int[n + 1][c + 1];
        int[] count = new int[c+1];
        for (int i = 1; i < n + 1; i++) {
            w[i] = weight[i - 1];
            v[i] = value[i - 1];
        }

        //初始化values[0...c]=0————解决在不超过背包容量的情况下，最多能获得多少价值
        //原因：如果背包并非必须被装满，那么任何容量的背包都有一个合法解“什么都不装”，这个解的价值为0，所以初始时状态的值也就全部为0了
        int[] values = new int[c + 1];
        //初始化values[0]=0，其它全为负无穷————解决在恰好装满背包的情况下，最多能获得多少价值的问题
        //原因：只有容量为0的背包可以什么物品都不装就能装满，此时价值为0，其它容量背包均无合法的解，属于未定义的状态，应该被赋值为负无穷
//        for (int i = 1; i < values.length; i++) {
//            values[i] = MIN;
//        }

        for (int i = 1; i < n + 1; i++) {
//            for (int t = w[i]; t <= c; t++) { //为完全背包问题，但是打印输出有问题
            for (int k = 0; k <= c / w[i]; k++) {
                for (int t = c; t >= k * w[i]; t--) {
                    if (values[t] < values[t - k * w[i]] + k * v[i]) {
                        values[t] = values[t - w[i] * k] + v[i] * k;
                        G[i][t] = k;
                    }
                }
            }
        }
        System.out.println("最大价值为： " + values[c]);
        System.out.print("装入背包的物品编号为： ");
        /*
            输出顺序:逆序输出物品编号
            注意：这里另外开辟数组G[i][v],标记上一个状态的位置
            G[i][v] = 1:表示物品i放入背包了，上一状态为G[i - 1][v - w[i]]
            G[i][v] = 0:表示物品i没有放入背包，上一状态为G[i - 1][v]
        */
        int i = n;
        int j = c;
        while (i > 0) {
            if(G[i][j] > 0){
                while (G[i][j] > 0) {
                    System.out.print(i + " ");
                    G[i][j]--;
                }
                j -= w[i];
            }
            i--;
        }
    }
    /**
     * 计算已选择物品数量
     * @param G
     * @param w 各物品质量
     * @param c 背包容量
     * @param n 物品数量
     * @return
     */
    int countNum(int[][] G,int[] w, int c,int n){
        int count = 0 ;
        while (n > 0) {
            if (G[n][c] == 1) {
                count++;
                c -= w[n];
            }
            n--;
        }
        return count;
    }
}
