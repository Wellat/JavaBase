package hemi.mldn.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * �ֽ���
 * ʹ���ֽ�������,Scanner����ļ��ķ�ʽҲ����
 * @author Vanguard
 * ��Ϊ���е��ļ���Ӳ�̻��ڴ���ʱ�������ֽڵķ�ʽ���еģ�����ͼƬ�ȶ��ǰ��ֽڵķ�ʽ�洢�ģ����ַ���ֻ�����ڴ��вŻ��γɣ������ڿ����У��ֽ���ʹ�ý�Ϊ�㷺
 */
public class ByteStream {
	public static void main(String[] args){
		File file = new File("D:"+File.separator+"filedemo.txt");
		File file2 = new File("D:"+File.separator+"filedemoCopy.txt");
//		ByteStream.FileOutput(file);
//		ByteStream.FileInput(file);
		ByteStream.Copy(file, file2);
	}
	
	/**
	 * �ļ����Ʋ���
	 * @param source
	 * @param target
	 * ���ñ߶���д�ķ�ʽ
	 */
	public static void Copy(File source,File target){
		if(!source.exists()){
			System.out.println("Դ�ļ������ڣ�");
			System.exit(1);
		}
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(target);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte b[] = new byte[(int) source.length()];
		try {
			for(int i=0;i<b.length;i++){
				b[i]=(byte) input.read();
				output.write(b[i]);
			}
			input.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("������ɣ�");
	}
	
	public static void FileInput(File f){
		InputStream input = null;
		try {
			input = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte b[] = new byte[(int) f.length()];
		try {
			for(int i=0;i<b.length;i++){			
				b[i]=(byte) input.read();			
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("����Ϊ��"+ new String(b));
		
	}
	public static void FileOutput(File f){
		OutputStream out = null;	//׼����һ�����������
		try {
			out = new FileOutputStream(f,true);//�˴�true��ʾ�ļ�ĩβ׷������
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str = "\nYour text.�����ı���";
		byte b[] = str.getBytes();//ֻ�����byte���飬���Խ��ַ�����Ϊbyte����
		try {
			out.write(b);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
