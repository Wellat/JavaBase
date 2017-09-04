package hemi.xmu.mldn.locale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class International {
	public static void main(String[] args){
		//��ʾ����
		Locale zhLoc = new Locale("zh","CN");
		Locale enLoc = new Locale("en","US");
		//�ҵ���Ӧ�����������ļ�
		ResourceBundle zhrb = ResourceBundle.getBundle("hemi.xmu.mldn.locale.Message",zhLoc);
		ResourceBundle enrb = ResourceBundle.getBundle("hemi.xmu.mldn.locale.Message",enLoc);
		//�Դζ�ȡ���������ļ������ݣ�ͨ����ֵ��ȡ����ʱ�ļ�ֵ����Ϊinfo
		String str1 = zhrb.getString("info");
		String str2 = enrb.getString("info");
		
		System.out.println("���ģ�"+MessageFormat.format(str1, "С��ˮ","��<��)#)))�Q"));
		System.out.println("Ӣ�ģ�"+MessageFormat.format(str2, "Hemi"));
	}	
}
