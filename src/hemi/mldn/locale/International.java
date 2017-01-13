package hemi.mldn.locale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class International {
	public static void main(String[] args){
		//表示地区
		Locale zhLoc = new Locale("zh","CN");
		Locale enLoc = new Locale("en","US");
		//找到对应地区的属性文件
		ResourceBundle zhrb = ResourceBundle.getBundle("hemi.mldn.locale.Message",zhLoc);
		ResourceBundle enrb = ResourceBundle.getBundle("hemi.mldn.locale.Message",enLoc);
		//以次读取各个属性文件的内容，通过键值读取，此时的键值名称为info
		String str1 = zhrb.getString("info");
		String str2 = enrb.getString("info");
		
		System.out.println("中文："+MessageFormat.format(str1, "小白水","鱼<。)#)))≦"));
		System.out.println("英文："+MessageFormat.format(str2, "Hemi"));
	}	
}
