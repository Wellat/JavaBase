package hemi.mldn.undefine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//	X,必须出现1次----X?,出现0次或一次----X*,0,1,多次----X+,出现一次或多次----X{n,m},必须出现n~m次
//	\d,数字----\D,非数字
//	\w,字母数字下划线----\W,非字母数字下划线
//	\s,所有空白字符（换行，空格）----\S,非空白字符
public class RegexDemo {
	public static void main(String[] args){
		Regex myregex = new Regex();
//		myregex.UsePattern();
		myregex.UseString();

	}
}

class Regex{
	/**
	 * 使用Pattern类和Matcher类
	 */
	public void UsePattern(){
		/*
		 * 匹配日期格式
		 */
		String str ="1983-07-24";
		String pat = "\\d{4}-\\d{2}-\\d{2}";//'\\d'表示'\d'
		Pattern p = Pattern.compile(pat);
		Matcher m = p.matcher(str);
		if(m.matches()){
			System.out.println("日期格式合法！");
		}else{
			System.out.println("日期格式不合法！");
		}
		/*
		 * 字符串拆分与替换
		 */
		String str2 = "A1B22C333D4444E55555F6";
		String pat2 = "\\d+";
		Pattern p2 = Pattern.compile(pat2);
		Matcher m2 = p2.matcher(str2);
		String s[] = p2.split(str2);
		System.out.println("字符串拆分：");
		for(String ss:s){
			System.out.print(ss+"\t");
		}
		System.out.println();
		System.out.println("字符串替换：");
		String newString = m2.replaceAll("_");//将数字替换成_
		System.out.println(newString);
	}
	/**
	 * 使用String类
	 */
	public void UseString(){
		/*
		 * 匹配邮箱地址"^\\w+(.\\w+)?@(\\w+.)+\\w{2,3}$"
		 */
		String[] email = {"aaa@","aa.b@qq.com","1123@163.com","113fe$@11.com","han. @sohu.com.cn","han.c@sohu.com.cn.cm.cm"};
//		String reg = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";//'\\d'表示'\d'
		String reg = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
//		String reg = "^\\w+(.\\w+)?@(\\w+.)+\\w{2,3}$";//test
		for(String mail:email){
			//此处应用String类对正则的支持
			System.out.println(mail+"\t------"+mail.matches(reg));
		}
		System.out.println("字符串操作");
		String str = "A1B22C333D4444E55555F6";
		String str2 = str.replaceAll("\\d+", "=");
		String s[] = str.split("\\d+");
		for(String ss:s){
			System.out.print(ss+"\t");
		}
		System.out.println();
		System.out.println("替换"+str2);

	}
}
