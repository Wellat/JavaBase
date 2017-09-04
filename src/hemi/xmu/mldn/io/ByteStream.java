package hemi.xmu.mldn.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 字节流
 * 使用字节流更好,Scanner类读文件的方式也不错
 * @author Vanguard
 * 因为所有的文件在硬盘或在传输时都是以字节的方式进行的，包括图片等都是按字节的方式存储的，而字符是只有在内存中才会形成，所以在开发中，字节流使用较为广泛
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
	 * 文件复制操作
	 * @param source
	 * @param target
	 * 采用边读边写的方式
	 */
	public static void Copy(File source,File target){
		if(!source.exists()){
			System.out.println("源文件不存在！");
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
		System.out.println("复制完成！");
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
		System.out.println("内容为："+ new String(b));

	}
	public static void FileOutput(File f){
		OutputStream out = null;	//准备好一个输出流对象
		try {
			out = new FileOutputStream(f,true);//此处true表示文件末尾追加内容
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str = "\nYour text.输入文本。";
		byte b[] = str.getBytes();//只能输出byte数组，所以将字符串变为byte数组
		try {
			out.write(b);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
