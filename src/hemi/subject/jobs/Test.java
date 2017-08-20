package hemi.subject.jobs;

import hemi.battle.huawei.Node;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test{
    private static final int MIN = Integer.MIN_VALUE;

    @org.junit.Test
    public void testree(){
        BinaryNode n1 = new BinaryNode(7);
        BinaryNode n2 = new BinaryNode(5);
        BinaryNode n3 = new BinaryNode(6);
        BinaryNode n4 = new BinaryNode(9);
        BinaryNode n5 = new BinaryNode(8);
        BinaryNode n6 = new BinaryNode(2);
        n1.left=n2;
        n1.right=n4;
        n2.right=n3;
        n2.left = n6;
        n4.left=n5;
        postOrder(n1);
        System.out.println();
        iterativePostorder3(n1);
    }
    public void postOrder(BinaryNode p){
        if(p==null) return;
        if(p!=null){
            postOrder(p.left);
            postOrder(p.right);
            System.out.print(p.element+" ");
        }
    }
    protected static void iterativePostorder3(BinaryNode p) {
        Stack<BinaryNode> stack = new Stack<>();
        BinaryNode node = p, prev = p;
        while (node != null || stack.size() > 0) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (stack.size() > 0) {
                BinaryNode temp = stack.peek().right;
                if (temp == null || temp == prev) {
                    node = stack.pop();
                    System.out.print(node.element+" ");
                    prev = node;
                    node = null;
                } else {
                    node = temp;
                }
            }

        }
    }
    @org.junit.Test
    public void testRex() throws Exception {

    }

    //求边长
    private double length(Integer[] a,Integer[] b){
        int sum = 0;
        for(int i=0;i<a.length;i++){
            sum += Math.pow(a[i]-b[i],2);
        }
        return Math.sqrt(sum);
    }
    //求面积
    private double area(double a,double b,double c){
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

//    @org.junit.Test
    public void test() {
/*        int sum = 0;
        int[] vi = {88242, 313, 1991, 4207, 2483, 1763, 224, 16, 582, 22943, 28632, 47682, 378, 90, 88, 43, 117, 19, 8};
        int n = vi.length;
        for (int i = 1; i < n; i++) {
            sum += vi[i];
        }*/
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] vi = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                vi[i] = sc.nextInt();
                sum += vi[i];
            }


            int[] dp0 = new int[sum * 2 + 1], dp1 = new int[sum * 2 + 1];
            for (int i = 0; i < dp0.length; i++)
                dp0[i] = -1;

            dp0[sum] = 0;
            // 对每个物品
            for (int i = 1; i <= n; i++) {
                int v = vi[i - 1];
                for (int j = 0; j < 2 * sum + 1; j++) {
                    dp1[j] = dp0[j];
                    if (j - v >= 0 && dp0[j - v] != -1) {
                        dp1[j] = Math.max(dp0[j - v], dp1[j]);
                    }
                    if (j + v <= 2 * sum && dp0[j + v] != -1) {
                        dp1[j] = Math.max(dp0[j + v] + v, dp1[j]);
                    }
                }
                int[] temp = dp1;
                dp1 = dp0;
                dp0 = temp;
            }
            if (dp0[sum] == 0)
                System.out.println(-1);
            else
                System.out.println(dp0[sum]);
        }
    }

    @org.junit.Test
    public void test2() {
        long begin = System.currentTimeMillis();
//        int[] a = {2, 8, 3, 4, 7, 5, 6,0,1,3,2, 1, 9};
//        findMaxN(a, 5);
        String str = "北京市(朝阳区)(西城区)(海淀区)";
        Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);
        if(m.find()) {
            System.out.println(m.group());
        }

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            PreparedStatement ps = con.prepareStatement("select * from test where id = ?");
            ps.setInt(1,25);
            ResultSet rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                    con = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - begin));
    }

    private void findMaxN(int[] arr, int num) {
        int temp;
        for (int i = 0; i < num; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        for (int i = arr.length - 1; i > arr.length - num - 1; i--)
            System.out.println(arr[i] + " ");
    }
}