package hemi.xmu.learn.multiThread;

import java.util.concurrent.TimeUnit;
/*
 * join()
 */
public class Join {
	public static void main(String[] args) throws Exception {
		Thread previous = Thread.currentThread();
		for(int i=0;i<10;i++){
			Thread thread = new Thread(new testJoin(previous),String.valueOf(i));
			thread.start();
			previous = thread;
		}
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName()+" terminate");
	}
	static class testJoin implements Runnable{
		Thread thread;
		public testJoin(Thread thread){
			this.thread=thread;
		}
		@Override
		public void run() {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" terminate");
		}
		
	}
}
