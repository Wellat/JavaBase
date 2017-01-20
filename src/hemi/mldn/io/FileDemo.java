package hemi.mldn.io;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作
 * File类只是针对文件本身进行操作
 * @author Vanguard
 *
 */
public class FileDemo {
	public static void main(String[] args){
		File f = new File("D:\\filedemo.txt");//【注意】此处路径分隔符建议用 File.separator 代替，可适用不同操作系统
		if(f.exists()){
			f.delete();//删除文件
		}else{
			try {
				f.createNewFile();//创建新文件
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File f2 = new File("D:"+File.separator+"filedemo_javaTest");
		f2.mkdir();//创建一个文件夹
		
		//打印文件夹下的所有文件
		File f3 = new File("D:"+File.separator+"Documents"+File.separator+"GitHub"+File.separator+"MLaction"+File.separator+"Ch09_ReTree");
		FileDemo.print(f3);
	}
	public static void print(File file){
		if(file!=null){
			if(file.isDirectory()){
				File f[] = file.listFiles();
				if(f!=null){
					for(int i=0;i<f.length;i++){
						print(f[i]);
					}
				}
			}else{
				System.out.println(file);
			}
		}
	}
}

