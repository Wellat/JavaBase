package hemi.learn.multiThread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		//获取正在运行的线程信息
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for(ThreadInfo threadInfo:threadInfos){
			System.out.println("["+threadInfo.getThreadId()+"] "+threadInfo.getThreadName());
		}
		
		Thread thread = new Thread(new Test.DaemonRunner(),"DaemonRunner");
		thread.setDaemon(true);
		thread.start();
		Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
          public void run() {
          System.out.println("JVM Exit!");
          }
        });
	}
	static class DaemonRunner implements Runnable{

		@Override
		public void run() {
			try{
				System.out.println("Thread Running...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("Thread Running...End！");
			}finally{
				System.out.println("DaemonThread finally run.");
			}
		}
		
	}
}
