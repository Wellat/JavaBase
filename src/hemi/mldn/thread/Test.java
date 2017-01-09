package hemi.mldn.thread;
/**
 * Thread类也是Runnable接口的子类
 * 继承Thread类不能资源共享，而实现Runnable接口可以
 * @author Vanguard
 */
/*
 * 一个多线程的程序如果是通过Runnable接口实现的，则意味着类中的属性将被多个线程共享，
 * 那么这样如果多个线程要操作同一资源时就有可能出现资源的同步问题 
 * 解决资源共享的同步操作，可以使用同步代码块和同步方法两种方式完成
 */
/*
 * 同步可以保证资源共享操作的正确性，但是过多同步也会产生死锁问题
 * 所谓死锁就是指两个线程都在等待对方先完成，造成了程序的停滞，
 */
public class Test {
	public static void main(String args[]){
		//继承Thread类不能资源共享
		EThread et1 = new EThread();
		EThread et2 = new EThread();
		et1.start();
		et2.start();
		//实现Runnable接口可以实现资源共享
		RThread rt = new RThread();
		new Thread(rt,"自定义线程名称").start();
		new Thread(rt).start();
	}
}
class EThread extends Thread{
	private int ticket=5;
	public void run(){
		for(int i=0;i<100;i++){
			synchronized(this){//同步代码块
				if(ticket>0){
					System.out.println("AAA卖票：ticket = " + ticket--);
				}
			}
		}
	}
}

class RThread implements Runnable{
	private int ticket=5;
	public void run(){
		for(int i=0;i<100;i++){
			this.sale();
		}
	}
	//同步方法
	public synchronized void sale(){
		if(ticket>0){
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"卖票：ticket = " + ticket--);
		}
	}
}