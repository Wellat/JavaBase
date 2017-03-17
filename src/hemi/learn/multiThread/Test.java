package hemi.learn.multiThread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class Test {
	public static void main(String[] args) {
		// 获取正在运行的线程信息
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
		}

		/*
		 * Thread thread = new Thread(new Test.DaemonRunner(), "DaemonRunner");
		 * thread.setDaemon(true); thread.start();
		 * Runtime.getRuntime().addShutdownHook(new Thread() {
		 * 
		 * @Override public void run() { System.out.println("JVM Exit!"); } });
		 */
		HasSelfPrivateNum pn1 = new HasSelfPrivateNum();
		HasSelfPrivateNum pn2 = new HasSelfPrivateNum();
		ThreadA athread = new ThreadA(pn1);
		athread.start();
		ThreadB bthread = new ThreadB(pn1);
		bthread.start();
	}

	static class DaemonRunner implements Runnable {

		@Override
		public void run() {
			try {
				System.out.println("Thread Running...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("Thread Running...End！");
			} finally {
				System.out.println("DaemonThread finally run.");
			}
		}
	}

	static class HasSelfPrivateNum {
		private int num = 0;
		private volatile int test = 3;
		public void ohterMethod(){
			System.out.println("-------------other method.");
		}
		public synchronized void addI(String name) {
			// int num = 0;
			
			try {
				test = 666;
				if (name.equals("a")) {
					num = 100;
					System.out.println("a set over.");
					Thread.sleep(2000);
				} else {
					num = 200;
					System.out.println("b set over.");
				}
				System.out.println(name + ",num= " + num+",test = "+test);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class ThreadA extends Thread {
		private HasSelfPrivateNum pn;

		public ThreadA(HasSelfPrivateNum pn) {
			super();
			this.pn = pn;
		}

		@Override
		public void run() {
			super.run();
			pn.addI("a");
		}
	}

	static class ThreadB extends Thread {
		private HasSelfPrivateNum pn;

		public ThreadB(HasSelfPrivateNum pn) {
			super();
			this.pn = pn;
		}

		@Override
		public void run() {
			super.run();
			pn.ohterMethod();
		}
	}
}
