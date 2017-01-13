package hemi.mldn.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
/**
 * 日期操作类
 * 主要使用Date,Calendar,SimpleDateFormat
 * @author Vanguard
 *
 */
public class DateDemo {
	public static void main(String[] args){		
		/*
		 * Date
		 */
		System.out.println("*********Date***********");
		Date date = new Date();//实例化Date类对象
		System.out.println("当前日期："+date);		//Out:当前日期：Wed Jan 11 15:04:06 CST 2017		
		
		/*
		 * Calendar
		 */
		System.out.println("*********Calendar***********");
		Calendar calendar = null;
		calendar = new GregorianCalendar();
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
		//取得日期时间，设置日期的显示风格
		DateFormat df1 = DateFormat.getDateInstance(DateFormat.YEAR_FIELD,new Locale("zh","CN"));
		//取得日期时间，设置日期的显示格式，时间的显示格式
		DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.YEAR_FIELD,DateFormat.ERA_FIELD,new Locale("zh","CN"));
		System.out.println("Date:"+df1.format(date));		//Out:Date:2017年1月11日		
		System.out.println("DateTime:"+df2.format(date));	//Out:DateTime:2017年1月11日 下午03时04分06秒 CST		
		
		/*
		 * SimpleDateFormat
		 */
		System.out.println("*********SimpleDateFormat***********");
		String pat1 = "yyyy-MM-dd HH:mm:ss";
		String pat2 = "yyyy年MM月dd日 HH时mm分ss秒";
		SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);
		SimpleDateFormat sdf2 = new SimpleDateFormat(pat2);
		System.out.println(sdf1.format(date));
		System.out.println(sdf2.format(date));		
		
	}
}
