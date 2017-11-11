package hemi.xmu.learn.multiThread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class Test {
	public static void main(String[] args) throws Exception{
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
		}
		
		String ser = "sd";
		MyThreadA ta = new MyThreadA(ser);
		ta.start();	
		MyThreadA tb = new MyThreadA("sss");
		tb.start();
		Thread.sleep(2000);
		NotifyThread noti = new NotifyThread(ser);
		noti.start();
	}
}

class Service {
	private Object obj;
	public Service(Object obj) {
		this.obj=obj;
	}
	public void testMethod1() {
		try {
			synchronized (obj) {
				System.out.println("into wait method..."+Thread.currentThread().getName());
				obj.wait(4000);
				System.out.println("---out wait method..."+Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}

class MyThreadA extends Thread {
	private Object lock;

	public MyThreadA(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		super.run();
		Service service = new Service(lock);
		service.testMethod1();
	}
}

class MyThreadB extends Thread {
	private Object lock;

	public MyThreadB(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		super.run();
		Service service = new Service(lock);
		service.testMethod1();
	}
}

class NotifyThread extends Thread{
	private Object obj;
	public NotifyThread(Object obj){
		super();
		this.obj=obj;
	}
	@Override
	public void run() {
		super.run();
		synchronized (obj) {
			System.out.println("into notify method..."+Thread.currentThread().getName());
			obj.notifyAll();
			System.out.println("---out notify method..."+Thread.currentThread().getName());
		}
	}
}

