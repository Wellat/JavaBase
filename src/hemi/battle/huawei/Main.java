package hemi.battle.huawei;

/**
 * Created by Vanguard on 2017/3/24.
 */


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] cpu = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                cpu[i] = sc.nextInt() / 1024;
                sum += cpu[i];
            }
            int sumhalf = sum / 2;
            int[] time = new int[sum + 1];
            for (int i = 0; i < cpu.length; i++) {
                for (int j = sumhalf; j >= 0; j--) {
                    if (j >= cpu[i])
                        time[j] = Math.max(time[j], time[j - cpu[i]] + cpu[i]);
                }
            }
            System.out.println(Math.max(time[sumhalf], sum - time[sumhalf]) * 1024);
        }
    }
}

/*public class Main {
    public final static int Inf = 65535;
    public static int[][] data = {  {0,2,10,5,3, Inf},
                                    {Inf,0,12,Inf,Inf,10},
                                    {Inf,Inf,0,Inf,7,Inf},
                                    {2,Inf,Inf,0,2,Inf},
                                    {4,Inf,Inf,1,0,Inf},
                                    {3,Inf,1,Inf,2,0}};
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int to = in.nextInt();
        int wu = in.nextInt();
        if(wu!=0){
            for(int i=0;i<6;i++){
                data[wu-1][i]=Inf;
                data[i][wu-1]=Inf;
            }
        }
        getPaths(4,4,4,to);

    }
    public static boolean getPaths(int begin, int last, int source, int end) {
        int temp;
        Stack<Integer> stack = new Stack<>();
        if (begin == 0 && last==0 && source==0)
            return false;

        if (begin != 0) {
            int i = 0;
            stack.push(begin);
            if (begin == end) {
                return true;
            }
            else {
                for(int j=0;j<6;j++){
                }

                stack.pop();
                return false;
            }
        } else
            return false;
    }

}*/


class Mtest {
    public void inputDemo() {
        /**
         *
         */
        Scanner inn = new Scanner(System.in);
        while (inn.hasNextInt()) {//注意while处理多个case
            int a = inn.nextInt();
            int b = inn.nextInt();
            System.out.println(a + b);
        }

        /**
         *
         */
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
        /**
         * 输入为二维矩阵
         */
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
}