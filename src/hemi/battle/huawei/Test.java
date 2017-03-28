package hemi.battle.huawei;


import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static int sum = 0;

    public static void main(String[] args) {
        boolean flag = true;
        int temp;


        ArrayList<Integer> value = new ArrayList<>();
        while (flag)
            change();
        Scanner input = new Scanner(System.in);
        while (flag) {
            if(input.hasNextInt()){
                temp = input.nextInt();
                if (temp == 0) {
                    calculate2(value);
                    return;
                } else {
                    value.add(temp);
                }
            }
        }
        System.out.println("The input values is: " + value.toString());
        if (value.size() > 10 || value.size() < 1)
            return;
        for (int i = 0; i < value.size(); i++) {
            System.out.println(calculate1(value.get(i)));
            sum = 0;
        }
    }
    public static void change() {
        String temp ;
        Scanner input = new Scanner(System.in);
        temp = input.next();
        temp = temp.substring(2);
        System.out.println(Integer.valueOf(temp,16));
    }

    public static int calculate1(int n) {
        if (n == 2)
            return 1;
        if (n < 2)
            return 0;
        sum = sum + calculate1(n / 3 + n % 3);
        return (sum + n / 3);
    }

    public static void calculate2(ArrayList<Integer> arr) {
        int result[] = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            int n = arr.get(i);
            while (n > 1) {
                if (n == 2) {
                    result[i] = result[i] + 1;
                    break;
                } else {
                    result[i] = result[i] + n / 3;
                    n = n / 3 + n % 3;
                }
            }
            System.out.println(result[i]);
        }
    }
}
