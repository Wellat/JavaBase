package hemi.mldn.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * 字符流
 * 使用字节流更好
 * @author Vanguard
 *
 */
public class CharStream {
	public static void main(String[] args) {
		File file = new File("D:" + File.separator + "filedemo.txt");
		CharStream.WriterDemo(file);
		try {
			CharStream.ReaderDemo(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void WriterDemo(File file) {
		Writer out = null;
		try {
			out = new FileWriter(file, true);// true表示追加内容
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = "writer in by charStream!\n";
		try {
			out.write(str);
			out.flush();//强制性清空缓冲区中的内容
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void ReaderDemo(File file) {
		Reader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		char c[] = new char[(int) file.length()];
		try{
			for(int i=0;i<c.length;i++){
				c[i]=(char) reader.read();
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}		
		System.out.println(new String(c));
	}
}
