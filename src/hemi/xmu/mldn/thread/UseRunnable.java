package hemi.xmu.mldn.thread;
/**
 * ͨ��ʵ��Runnable�ӿ�ʵ�ֶ��߳�
 * @author Vanguard
 */
public class UseRunnable {
	public static void main(String args[]){
		MyThread2 mt1 = new MyThread2("---�߳�1---");//ʵ����Runnable�������
		MyThread2 mt2 = new MyThread2("===�߳�2===");
		Thread t1 = new Thread(mt1);//ʵ����Thread�����
		Thread t2 = new Thread(mt2);
		t1.start();
		t2.start();
	}
}
//�߳���
class MyThread2 implements Runnable{
	private String name;
	
	public MyThread2(String name){
		this.name=name;
	}
	/*
	 * �߳�����
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(name+"���У�i="+ i);
		}
	}
}