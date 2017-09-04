package hemi.xmu.mldn.thread;
/**
 * Thread��Ҳ��Runnable�ӿڵ�����
 * �̳�Thread�಻����Դ������ʵ��Runnable�ӿڿ���
 * @author Vanguard
 */
/*
 * һ�����̵߳ĳ��������ͨ��Runnable�ӿ�ʵ�ֵģ�����ζ�����е����Խ�������̹߳���
 * ��ô�����������߳�Ҫ����ͬһ��Դʱ���п��ܳ�����Դ��ͬ������ 
 * �����Դ�����ͬ������������ʹ��ͬ��������ͬ���������ַ�ʽ���
 */
/*
 * ͬ�����Ա�֤��Դ�����������ȷ�ԣ����ǹ���ͬ��Ҳ�������������
 * ��ν��������ָ�����̶߳��ڵȴ��Է�����ɣ�����˳����ͣ�ͣ�
 */
public class Test {
	public static void main(String args[]){
		//�̳�Thread�಻����Դ����
		EThread et1 = new EThread();
		EThread et2 = new EThread();
		et1.start();
		et2.start();
		//ʵ��Runnable�ӿڿ���ʵ����Դ����
		RThread rt = new RThread();
		new Thread(rt,"�Զ����߳�����").start();
		new Thread(rt).start();
	}
}
class EThread extends Thread{
	private int ticket=5;
	public void run(){
		for(int i=0;i<100;i++){
			synchronized(this){//ͬ�������
				if(ticket>0){
					System.out.println("AAA��Ʊ��ticket = " + ticket--);
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
	//ͬ������
	public synchronized void sale(){
		if(ticket>0){
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"��Ʊ��ticket = " + ticket--);
		}
	}
}