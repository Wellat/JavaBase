package hemi.xmu.mldn.undefine;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberFormat_h {
	public static void main(String[] args) {
		/*
		 * NumberFormat��
		 */
		NumberFormat nf = null; // ����һ��NumberFormat����
		nf = NumberFormat.getInstance(); // �õ�Ĭ�ϵ����ָ�ʽ����ʾ
		System.out.println("��ʽ��֮������֣�" + nf.format(10000000));// ��ʽ��֮������֣�10,000,000
		System.out.println("��ʽ��֮������֣�" + nf.format(1000.345));// ��ʽ��֮������֣�1,000.345

		/*
		 * DecimalFormat��
		 */
		FormatDemo demo = new FormatDemo(); // ��ʽ���������
		demo.Formath("###,###.###", 1116222.34567);
		demo.Formath("000,000.000", 11222.34567);
		demo.Formath("###,###.###��", 111222.34567);
		demo.Formath("000,000.000��", 11222.34567);
		demo.Formath("##.###%", 0.345678);
		demo.Formath("00.###%", 0.0345678);
		demo.Formath("###.###\u2030", 0.345678);
	}
}

class FormatDemo {
	public void Formath(String pattern, double value) {
		DecimalFormat df = null; // ����һ��DecimalFormat��Ķ���
		df = new DecimalFormat(pattern); // ʵ�������󣬴���ģ��
		String str = df.format(value); // ��ʽ������
		System.out.println("ʹ��" + pattern + "��ʽ������" + value + "��" + str);
	}
}