package hemi.xmu.mldn.undefine;

import java.util.Observable;
import java.util.Observer;

/**
 * ���ģʽ�������� ����ģʽ������ģʽ��������ģʽ���۲���ģʽ
 */
public class DesignMode {
	public static void main(String args[]) {
		// ����ģʽ�������ɶ�
		System.out.println("-----����ģʽ-----");
		Fruit f = null;
		f = Factory.getInstance("apple");
		f.eat();

		// ����ģʽ��������Service,ֻ��עҵ��
		System.out.println("-----����ģʽ-----");
		Fruit fd = null;
		fd = new Proxy(new Orange());
		fd.eat();

		// ������ģʽ
		// һ���ӿ����ȱ�һ�������ࣨ��Ϊ����������ʵ�֣����ڴ˳�������ʵ�ֽӿڵ����ɷ�����������Ϊ�գ���
		// ���Ժ������ֱ�Ӽ̳д˳����࣬�Ϳ�����ѡ��ظ�д����Ҫ�ķ���
		System.out.println("-----������ģʽ-----");
		Window win = new WindowImpl();
		win.open();
		win.close();

		/*
		 * �۲���ģʽ 
		 * ���۲��෢���䶯ʱ���۲��߶��ܹ��۲쵽 
		 * ��Ҫ���۲�������̳�Observable�࣬�۲����඼Ҫʵ��Observer�ӿ�
		 */
		House h = new House(100000);
		HousePriceObserver hpo1=new HousePriceObserver("�۲���A");
		HousePriceObserver hpo2=new HousePriceObserver("�۲���B");
		HousePriceObserver hpo3=new HousePriceObserver("�۲���C");
		h.addObserver(hpo1);
		h.addObserver(hpo2);
		h.addObserver(hpo3);
		System.out.println(h);//����House��toString������Ӽ۸�
		h.setPrice(123456);
		System.out.println(h);
	}
}

/*-------------------------------------------
 * �۲���ģʽ
 */
class House extends Observable {
	private float price;

	public House(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		super.setChanged();			//���ñ仯��
		super.notifyObservers(price);	//֪ͨ���й۲��߼۸�ı�
		this.price = price;
	}

	public String toString() {
		return "���Ӽ۸�Ϊ��" + this.price;
	}
}

class HousePriceObserver implements Observer {
	private String name;

	public HousePriceObserver(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Float){
			System.out.println(this.name+"�۲쵽�۸����Ϊ��"+((Float)arg).floatValue());
		}
	}

}

/*-------------------------------------------
 * 
 */
// ����һ��ˮ���Ľӿ�
interface Fruit {
	public void eat();
}

class Apple implements Fruit {
	@Override
	public void eat() {
		System.out.println("��ƻ����");
	}
}

class Orange implements Fruit {
	@Override
	public void eat() {
		System.out.println("�����ӣ�");
	}
}

/*
 * ���幤����
 */
class Factory {
	public static Fruit getInstance(String className) {
		Fruit f = null;
		if ("apple".equals(className)) {
			f = new Apple();
		}
		if ("orange".equals(className)) {
			f = new Orange();
		}
		return f;
	}
}

/*
 * ����ģʽ
 */
class Proxy implements Fruit {
	private Fruit fruit;

	public Proxy(Fruit fruit) {
		this.fruit = fruit;
	}

	public void wash() {
		System.out.println("ϴˢˢ��");
	}

	@Override
	public void eat() {
		this.wash();
		this.fruit.eat();
	}
}

/*
 * ���������ʵ��
 */
interface Window {
	void open();

	void close();

	void activated();

	void iconified();

	void deicoinfied();
}

// ���������ʵ�ֽӿڣ��ڴ����и�д�������������еķ�����Ϊ��
abstract class WindowAdapter implements Window {
	public void open() {
	}

	public void close() {
	}

	public void activated() {
	}

	public void iconified() {
	}

	public void deicoinfied() {
	}
}

// ����̳г����࣬��ѡ���ʵ����Ҫ�ķ���
class WindowImpl extends WindowAdapter {
	public void open() {
		System.out.println("���ڴ򿪡�");
	}

	public void close() {
		System.out.println("���ڹرա�");
	}
}
