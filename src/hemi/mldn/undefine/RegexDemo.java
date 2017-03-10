package hemi.mldn.undefine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//	X,�������1��----X?,����0�λ�һ��----X*,0,1,���----X+,����һ�λ���----X{n,m},�������n~m��
//	\d,����----\D,������
//	\w,��ĸ�����»���----\W,����ĸ�����»���
//	\s,���пհ��ַ������У��ո�----\S,�ǿհ��ַ�
public class RegexDemo {
	public static void main(String[] args){
		Regex myregex = new Regex();
//		myregex.UsePattern();
		myregex.UseString();

	}
}

class Regex{
	/**
	 * ʹ��Pattern���Matcher��
	 */
	public void UsePattern(){
		/*
		 * ƥ�����ڸ�ʽ
		 */
		String str ="1983-07-24";
		String pat = "\\d{4}-\\d{2}-\\d{2}";//'\\d'��ʾ'\d'
		Pattern p = Pattern.compile(pat);
		Matcher m = p.matcher(str);
		if(m.matches()){
			System.out.println("���ڸ�ʽ�Ϸ���");
		}else{
			System.out.println("���ڸ�ʽ���Ϸ���");
		}
		/*
		 * �ַ���������滻
		 */
		String str2 = "A1B22C333D4444E55555F6";
		String pat2 = "\\d+";
		Pattern p2 = Pattern.compile(pat2);
		Matcher m2 = p2.matcher(str2);
		String s[] = p2.split(str2);
		System.out.println("�ַ�����֣�");
		for(String ss:s){
			System.out.print(ss+"\t");
		}
		System.out.println();
		System.out.println("�ַ����滻��");
		String newString = m2.replaceAll("_");//�������滻��_
		System.out.println(newString);		
	}
	/**
	 * ʹ��String��
	 */
	public void UseString(){
		/*
		 * ƥ�������ַ"^\\w+(.\\w+)?@(\\w+.)+\\w{2,3}$"
		 */
		String[] email = {"aaa@","aa.b@qq.com","1123@163.com","113fe$@11.com","han. @sohu.com.cn","han.c@sohu.com.cn.cm.cm"};
//		String reg = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";//'\\d'��ʾ'\d'				
		String reg = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
//		String reg = "^\\w+(.\\w+)?@(\\w+.)+\\w{2,3}$";//test
		for(String mail:email){
			//�˴�Ӧ��String��������֧��
			System.out.println(mail+"\t------"+mail.matches(reg));
		}
		System.out.println("�ַ�������");
		String str = "A1B22C333D4444E55555F6";
		String str2 = str.replaceAll("\\d+", "=");
		String s[] = str.split("\\d+");
		for(String ss:s){
			System.out.print(ss+"\t");
		}
		System.out.println();
		System.out.println("�滻"+str2);
		
	}
}
