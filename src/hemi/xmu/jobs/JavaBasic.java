package hemi.xmu.jobs;

import java.util.Calendar;

/**
 * Created by Hemi on 2017/5/3.
 */
public class JavaBasic {
    public static void main(String args[]) {
        System.out.println(reverse("abcd"));


    }


    public static void basic() throws Exception{

        //将UTF-8编码的字符串转换为GB2312编码的字符串
        String s1 = "你好啊";
        String s2 = new String(s1.getBytes("UTF-8"),"GB2312");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,1);
        cal.getTime();
    }

    /**
     * 字符串反转
     * @param originStr
     * @return
     */
    public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1)
            return originStr;
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

}
class Annoyance extends Exception {}
class Sneeze extends Annoyance {}