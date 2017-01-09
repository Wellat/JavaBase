package hemi.mldn.thread;
/**
 * 通过实现Runnable接口实现多线程
 * @author Vanguard
 */
public class UseRunnable {
	public static void main(String args[]){
		MyThread2 mt1 = new MyThread2("---线程1---");//实例化Runnable子类对象
		MyThread2 mt2 = new MyThread2("===线程2===");
		Thread t1 = new Thread(mt1);//实例化Thread类对象
		Thread t2 = new Thread(mt2);
		t1.start();
		t2.start();
	}
}
//线程类
class MyThread2 implements Runnable{
	private String name;
	public MyThread2(String name){
		this.name=name;
	}
	/*
	 * 线程主体
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(name+"运行，i="+i);
		}
	}
}