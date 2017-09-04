package hemi.xmu.mldn.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 * ���ڲ�����
 * ��Ҫʹ��Date,Calendar,SimpleDateFormat
 * @author Vanguard
 *
 */
public class DateDemo {
	public static void main(String[] args){		
		/*
		 * Date
		 */
		System.out.println("*********Date***********");
		Date date = new Date();//ʵ����Date�����
		System.out.println("��ǰ���ڣ�"+date);		//Out:��ǰ���ڣ�Wed Jan 11 15:04:06 CST 2017		
		
		/*
		 * Calendar
		 */
		System.out.println("*********Calendar***********");
		Calendar calendar = Calendar.getInstance();
		System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
		System.out.println("MONTH: " + (calendar.get(Calendar.MONTH) + 1));
		System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
		System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
		System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));		
		
		/*
		 * DateFormat
		 */
		System.out.println("*********DateFormat***********");
		//ȡ������ʱ�䣬�������ڵ���ʾ���
		DateFormat df1 = DateFormat.getDateInstance(DateFormat.YEAR_FIELD,new Locale("zh","CN"));
		//ȡ������ʱ�䣬�������ڵ���ʾ��ʽ��ʱ�����ʾ��ʽ
		DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.YEAR_FIELD,DateFormat.ERA_FIELD,new Locale("zh","CN"));
		System.out.println("Date:"+df1.format(date));		//Out:Date:2017��1��11��		
		System.out.println("DateTime:"+df2.format(date));	//Out:DateTime:2017��1��11�� ����03ʱ04��06�� CST		
		
		/*
		 * SimpleDateFormat
		 */
		System.out.println("*********SimpleDateFormat***********");
		String pat1 = "yyyy-MM-dd HH:mm:ss";
		String pat2 = "yyyy��MM��dd�� HHʱmm��ss��";
		SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);
		SimpleDateFormat sdf2 = new SimpleDateFormat(pat2);
		System.out.println(sdf1.format(date));
		System.out.println(sdf2.format(date));		
		
		/*
		 * ������������֮����Ϊ����
		 */
		try {
			System.out.println(DateTest.getDistanceDays("2017-01-01", "2017-10-11"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
class DateTest{
	public static long getDistanceDays(String str1,String str2) throws Exception{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date one;
		Date two;
		long days=0,diff=0;
		try{
			one = df.parse(str1);
			two = df.parse(str2);
			long time1=one.getTime();
			long time2=two.getTime();
			diff=Math.abs(time1-time2);
			days=diff/(1000*60*60*24);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return days;
	}
}


