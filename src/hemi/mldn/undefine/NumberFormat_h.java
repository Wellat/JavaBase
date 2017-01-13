package hemi.mldn.undefine;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberFormat_h {
	public static void main(String[] args) {
		/*
		 * NumberFormat类
		 */
		NumberFormat nf = null; // 声明一个NumberFormat对象
		nf = NumberFormat.getInstance(); // 得到默认的数字格式化显示
		System.out.println("格式化之后的数字：" + nf.format(10000000));// 格式化之后的数字：10,000,000
		System.out.println("格式化之后的数字：" + nf.format(1000.345));// 格式化之后的数字：1,000.345

		/*
		 * DecimalFormat类
		 */
		FormatDemo demo = new FormatDemo(); // 格式化对象的类
		demo.Formath("###,###.###", 1116222.34567);
		demo.Formath("000,000.000", 11222.34567);
		demo.Formath("###,###.###￥", 111222.34567);
		demo.Formath("000,000.000￥", 11222.34567);
		demo.Formath("##.###%", 0.345678);
		demo.Formath("00.###%", 0.0345678);
		demo.Formath("###.###\u2030", 0.345678);
	}
}

class FormatDemo {
	public void Formath(String pattern, double value) {
		DecimalFormat df = null; // 声明一个DecimalFormat类的对象
		df = new DecimalFormat(pattern); // 实例化对象，传入模板
		String str = df.format(value); // 格式化数字
		System.out.println("使用" + pattern + "格式化数字" + value + "：" + str);
	}
}