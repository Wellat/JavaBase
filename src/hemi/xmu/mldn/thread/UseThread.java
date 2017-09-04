package hemi.xmu.mldn.thread;
/*
 * ͨ���̳�Threadʵ�ֶ��߳�
 */
public class UseThread {
	public static void main(String args[]){
		MyThread mt1 = new MyThread("---�߳�1---");
		MyThread mt2 = new MyThread("===�߳�2===");
		mt1.start();
		mt2.start();
	}
}

//�߳���
class MyThread extends Thread{
	private String name;
	public MyThread(String name){
		this.name=name;
	}
	/*
	 * �߳�����
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(name+"���У�i="+i);
		}
	}
}