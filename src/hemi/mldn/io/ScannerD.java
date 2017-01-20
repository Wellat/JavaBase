package hemi.mldn.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Scanner类
 * @author Vanguard
 *
 */
public class ScannerD {
	public static void main(String[] args){
//		Scanner scan = new Scanner(System.in);//从键盘接收数据
/*		scan.useDelimiter("\n");//修改输入数据的分隔符（默认为空格）
		System.out.println("输入数据：");
		String str = scan.next();
		System.out.println("数据为："+str);*/
		
/*		System.out.println("输入整数：");
		if(scan.hasNextInt()){
			int i = scan.nextInt();
			System.out.println("zhengshu:"+i);
		}else{
			System.out.println("输入的不是整数！");
		}*/
		
//		System.out.println("数据为："+str);
//		System.out.println("输入整数：");
//		if(scan.hasNextInt()){
//			int i = scan.nextInt();
//			System.out.println("zhengshu:"+i);
//		}else{
//			System.out.println("输入的不是整数！");
//		}
		
		/*		System.out.println("输入日期（yyyy-mm-dd）:");
		String str2 = null;
		Date date = null;
		if(scan.hasNext("^\\d{4}-\\d{2}-\\d{2}$")){
			str2=scan.next("^\\d{4}-\\d{2}-\\d{2}$");
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(str2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("not date!");
		}
		System.out.println(date);*/
		
		System.out.println("系统编码："+System.getProperty("file.encoding"));
		ScannerD.ScannerFile();
		
	}
	public static void ScannerFile(){
		File f = new File("D:\\filedemo.txt");
		Scanner scan = null;
		try {
			scan = new Scanner(f);
			scan.useDelimiter("\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuffer str = new StringBuffer();
		while(scan.hasNext()){
			str.append(scan.next()).append("\n");
		}
		System.out.println(str);
	}
}
