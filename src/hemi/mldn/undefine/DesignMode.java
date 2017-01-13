package hemi.mldn.undefine;

import java.util.Observable;
import java.util.Observer;

/**
 * 设计模式，包括↓ 工厂模式、代理模式、适配器模式、观察者模式
 */
public class DesignMode {
	public static void main(String args[]) {
		// 工厂模式——过渡端
		System.out.println("-----工厂模式-----");
		Fruit f = null;
		f = Factory.getInstance("apple");
		f.eat();

		// 代理模式——类似Service,只关注业务
		System.out.println("-----代理模式-----");
		Fruit fd = null;
		fd = new Proxy(new Orange());
		fd.eat();

		// 适配器模式
		// 一个接口首先被一个抽象类（称为适配器）先实现，并在此抽象类中实现接口的若干方法（方法体为空），
		// 则以后的子类直接继承此抽象类，就可以有选择地覆写所需要的方法
		System.out.println("-----适配器模式-----");
		Window win = new WindowImpl();
		win.open();
		win.close();

		/*
		 * 观察者模式 
		 * 被观察类发生变动时，观察者都能够观察到 
		 * 需要被观察的类必须继承Observable类，观察者类都要实现Observer接口
		 */
		House h = new House(100000);
		HousePriceObserver hpo1=new HousePriceObserver("观察者A");
		HousePriceObserver hpo2=new HousePriceObserver("观察者B");
		HousePriceObserver hpo3=new HousePriceObserver("观察者C");
		h.addObserver(hpo1);
		h.addObserver(hpo2);
		h.addObserver(hpo3);
		System.out.println(h);//调用House的toString输出房子价格
		h.setPrice(123456);
		System.out.println(h);
	}
}

/*-------------------------------------------
 * 观察者模式
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
		super.setChanged();			//设置变化点
		super.notifyObservers(price);	//通知所有观察者价格改变
		this.price = price;
	}

	public String toString() {
		return "房子价格为：" + this.price;
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
			System.out.println(this.name+"观察到价格更改为："+((Float)arg).floatValue());
		}
	}

}

/*-------------------------------------------
 * 
 */
// 定义一个水果的接口
interface Fruit {
	public void eat();
}

class Apple implements Fruit {
	@Override
	public void eat() {
		System.out.println("吃苹果！");
	}
}

class Orange implements Fruit {
	@Override
	public void eat() {
		System.out.println("吃橘子！");
	}
}

/*
 * 定义工厂类
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
 * 代理模式
 */
class Proxy implements Fruit {
	private Fruit fruit;

	public Proxy(Fruit fruit) {
		this.fruit = fruit;
	}

	public void wash() {
		System.out.println("洗刷刷！");
	}

	@Override
	public void eat() {
		this.wash();
		this.fruit.eat();
	}
}

/*
 * 适配器设计实现
 */
interface Window {
	void open();

	void close();

	void activated();

	void iconified();

	void deicoinfied();
}

// 定义抽象类实现接口，在此类中复写方法，但是所有的方法体为空
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

// 子类继承抽象类，有选择地实现需要的方法
class WindowImpl extends WindowAdapter {
	public void open() {
		System.out.println("窗口打开。");
	}

	public void close() {
		System.out.println("窗口关闭。");
	}
}
