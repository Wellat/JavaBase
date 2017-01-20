package hemi.mldn.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 完成从键盘输入两个数据，进行加法计算
 * 
 * @author Vanguard
 *
 */
public class AdditionDemo {
	public static void main(String[] args) {
		InputData id = new InputData();
		int i =id.getInt("请输入第一个数：", "请输入整数！");
		int j=id.getInt("请输入第二个数：", "请输入整数！");
		System.out.println(i+"+"+j+"="+(i+j));
		
		Date date = id.getDate("输入日期：", "日期格式为（yyyy-mm-dd）");
		System.out.println(date);
	}
}
/*
 * 输入数据类
 * 此类可被Scanner类替代
 */
class InputData {
	private BufferedReader buf = null;//BufferedReader用于从缓冲区中读取内容，所有的输入字节数据都将放在缓冲区中，

	public InputData() {
		this.buf = new BufferedReader(new InputStreamReader(System.in));
	}

	public String getString(String info) {
		String temp = null;
		System.out.println(info);
		try {
			temp = buf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int getInt(String info, String err) {
		int temp = 0;
		String str = null;
		boolean flag = true;
		while (flag) {
			str = this.getString(info);
			if (str.matches("^\\d+$")) {
				temp = Integer.parseInt(str);
				flag = false;
			} else {
				System.out.println(err);
			}
		}
		return temp;
	}

	public float getFloat(String info, String err) {
		float temp = 0;
		String str = null;
		boolean flag = true;
		while (flag) {
			str = this.getString(info);
			if (str.matches("^\\d+.?\\d+$")) {
				temp = Float.parseFloat(str);
				flag = false;
			} else {
				System.out.println(err);
			}
		}
		return temp;
	}

	public Date getDate(String info, String err) {
		Date temp = null;
		String str = null;
		boolean flag = true;
		while (flag) {
			str = this.getString(info);
			if (str.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					temp = sdf.parse(str);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				flag = false;
			} else {
				System.out.println(err);
			}
		}
		return temp;
	}
}
