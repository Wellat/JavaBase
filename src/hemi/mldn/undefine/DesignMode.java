package hemi.mldn.undefine;
/*
 * 设计模式，包括↓
 * 工厂模式、代理模式、适配器模式
 */
public class DesignMode {
	public static void main(String args[]){
		//工厂模式——过渡端
		System.out.println("-----工厂模式-----");
		Fruit f = null;
		f = Factory.getInstance("apple");
		f.eat();
		
		//代理模式——类似Service,只关注业务
		System.out.println("-----代理模式-----");
		Fruit fd =null;
		fd = new Proxy(new Orange());
		fd.eat();
		
		//适配器模式
		//一个接口首先被一个抽象类（称为适配器）先实现，并在此抽象类中实现接口的若干方法（方法体为空），
		//则以后的子类直接继承此抽象类，就可以有选择地覆写所需要的方法
		System.out.println("-----适配器模式-----");
		Window win = new WindowImpl();
		win.open();
		win.close();
	}
}

//定义一个水果的接口
interface Fruit{
	public void eat();
}

class Apple implements Fruit{
	@Override
	public void eat() {
		System.out.println("吃苹果！");
	}	
}

class Orange implements Fruit{
	@Override
	public void eat() {
		System.out.println("吃橘子！");
	}
}

/*
 * 定义工厂类
 */
class Factory{
	public static Fruit getInstance(String className){
		Fruit f = null;
		if("apple".equals(className)){
			f = new Apple();
		}
		if("orange".equals(className)){
			f = new Orange();
		}
		return f;
	}
}

/*
 * 代理模式
 */
class Proxy implements Fruit{
	private Fruit fruit;
	public Proxy(Fruit fruit){
		this.fruit=fruit;
	}
	public void wash(){
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
interface Window{
	void open();
	void close();
	void activated();
	void iconified();
	void deicoinfied();
}
//定义抽象类实现接口，在此类中复写方法，但是所有的方法体为空
abstract class WindowAdapter implements Window{
	public void open(){}
	public void close(){}
	public void activated(){}
	public void iconified(){}
	public void deicoinfied(){}
}
//子类继承抽象类，有选择地实现需要的方法
class WindowImpl extends WindowAdapter{
	public void open(){
		System.out.println("窗口打开。");
	}
	public void close(){
		System.out.println("窗口关闭。");
	}
}



