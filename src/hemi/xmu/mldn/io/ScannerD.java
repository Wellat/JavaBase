package hemi.xmu.mldn.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Scanner��
 * @author Vanguard
 *
 */
public class ScannerD {
	public static void main(String[] args){
//		Scanner scan = new Scanner(System.in);//�Ӽ��̽�������
/*		scan.useDelimiter("\n");//�޸��������ݵķָ�����Ĭ��Ϊ�ո�
		System.out.println("�������ݣ�");
		String str = scan.next();
		System.out.println("����Ϊ��"+str);*/
		
/*		System.out.println("����������");
		if(scan.hasNextInt()){
			int i = scan.nextInt();
			System.out.println("zhengshu:"+i);
		}else{
			System.out.println("����Ĳ���������");
		}*/
		
//		System.out.println("����Ϊ��"+str);
//		System.out.println("����������");
//		if(scan.hasNextInt()){
//			int i = scan.nextInt();
//			System.out.println("zhengshu:"+i);
//		}else{
//			System.out.println("����Ĳ���������");
//		}
		
		/*		System.out.println("�������ڣ�yyyy-mm-dd��:");
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
		
		System.out.println("ϵͳ���룺"+System.getProperty("file.encoding"));
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
