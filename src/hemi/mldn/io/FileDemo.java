package hemi.mldn.io;

import java.io.File;
import java.io.IOException;

/**
 * �ļ�����
 * File��ֻ������ļ�������в���
 * @author Vanguard
 *
 */
public class FileDemo {
	public static void main(String[] args){
		File f = new File("D:\\filedemo.txt");//��ע�⡿�˴�·���ָ��������� File.separator ���棬�����ò�ͬ����ϵͳ
		if(f.exists()){
			f.delete();//ɾ���ļ�
		}else{
			try {
				f.createNewFile();//�������ļ�
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File f2 = new File("D:"+File.separator+"filedemo_javaTest");
		f2.mkdir();//����һ���ļ���
		
		//��ӡ�ļ����µ������ļ�
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

