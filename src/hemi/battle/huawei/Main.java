package hemi.battle.huawei;

/**
 * Created by Vanguard on 2017/3/24.
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        String[] ans = GrayCode.getGray(3);
        for(String anss:ans){
            System.out.println(anss+" ");
        }
        int[] input = {1,2,3,2,2};
        GrayCode.getValue(input,5);
        /*Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int sum = 0;
            int[] cpu = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                cpu[i] = sc.nextInt() / 1024;
                sum += cpu[i];
            }

            int time[] = new int[sum / 2 + 1];
            for (int i = 1; i < n + 1; i++) {
                for (int t = sum / 2; t >= cpu[i]; t--) {
                    time[t] = Math.max(time[t], time[t - cpu[i]] + cpu[i]);
                }
            }
            System.out.println(Math.max(time[sum / 2], sum - time[sum / 2]) * 1024);
        }*/
    }

    public static class GrayCode {
        public static String[] getGray(int n) {
            int count = (int) Math.pow(2, n);
            String[] ans = new String[count];

            if (n == 1) {
                ans[0] = "0";
                ans[1] = "1";
                return ans;
            }
            String[] temp = new String[n - 1];

            for (int i = 0; i < temp.length; i++) {
                ans[i]="0"+temp[i];
                ans[ans.length-1-i]="1"+temp[i];
            }
            return ans;
        }

        public static int getValue(int[] gifts,int n){
            if(n<=0)
                return 0;
            Map<Integer,Integer> map = new HashMap<>();
            for(int i=0;i<gifts.length;i++){
                int re = map.put(gifts[i],1);
                if(re!=0){
                    map.put(gifts[i],re+1);
                }
            }
            return 0;
        }

    }
}

/*class ZeroOnePack {
    public static void main(String[] args) {
        int N;  //背包数目
        int[] weight; //单个物品重量
        int[] value; //单个物品价值
        int[][] values; //存放价值
        int maxValue;  //最大价值
        Scanner in = new Scanner(System.in);
        maxValue = in.nextInt();
        N = in.nextInt();
        weight = new int[N + 1];
        value = new int[N + 1];
        values = new int[N + 1][maxValue + 1];
        for (int i = 1; i <= N; i++) {
            weight[i] = in.nextInt();
            value[i] = in.nextInt();
        }

        for (int i = 0; i <= N; i++)
            values[i][0] = 0;
        for (int i = 0; i <= maxValue; i++)
            values[0][i] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= maxValue; j++) {
                if (weight[i] > j) {
                    values[i][j] = values[i - 1][j];
                } else {
                    values[i][j] = Math.max(values[i - 1][j - weight[i]] + value[i], values[i - 1][j]);
                }
            }
        }

        //反向找出选出的背包
        int j = maxValue;
        for (int i = N; i > 0; i--) {
            if (values[i][j] > values[i - 1][j]) {
                System.out.print(i + "  ");
                j = j - value[i];
                if (j < 0) break;
            }
        }

        in.close();

    }
}*/

/*
class Mtest {
    public void inputDemo() {
        */
/**
 * 输入为二维矩阵
 *//*

        Scanner inn = new Scanner(System.in);
        while (inn.hasNextInt()) {//注意while处理多个case
            int a = inn.nextInt();
            int b = inn.nextInt();
            System.out.println(a + b);
        }

        */
/**
 *
 *//*

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {//注意while处理多个case
            int ans = 0;
            String a = in.nextLine();//读取当前行的数据
            String[] value = a.split(" ");

            for (String s : value) {
                ans += Integer.valueOf(s);
            }
            System.out.println(ans);
        }
        */
/**
 * 输入为二维矩阵
 *//*

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0, x;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x = sc.nextInt();
                ans += x;
            }
        }

    }
}*/
