package hemi.mldn.thread;
/*
 * 通过继承Thread实现多线程
 */
public class UseThread {
	public static void main(String args[]){
		MyThread mt1 = new MyThread("---线程1---");
		MyThread mt2 = new MyThread("===线程2===");
		mt1.start();
		mt2.start();
	}
}

//线程类
class MyThread extends Thread{
	private String name;
	public MyThread(String name){
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