package hemi.xmu.mldn.thread;

/**
 * �̡߳��������ߡ������ߵ��Ͱ��� �漰���̡߳�ͬ�����ȴ�������
 * 
 * @author Vanguard
 *
 */
public class Example_thread {
	static final int ITER = 20;

	public static void main(String args[]) {
		Info info = new Info();
		Producer pro = new Producer(info);
		Consumer con = new Consumer(info);
		new Thread(pro).start();
		new Thread(con).start();
	}
}

class Consumer implements Runnable {
	private Info info = null;

	public Consumer(Info info) {
		this.info = info;
	}

	@Override
	public void run() {
		for (int i = 1; i < Example_thread.ITER; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.info.get();
		}
	}

}

class Producer implements Runnable {
	private Info info = null;

	public Producer(Info info) {
		this.info = info;
	}

	@Override
	public void run() {
		boolean flag = true;
		for (int i = 1; i < Example_thread.ITER; i++) {
			if (flag) {
				this.info.set("Hemi", "С��ˮ");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				flag = false;
			} else {
				this.info.set("mldn", "ħ�ֿƼ�");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				flag = true;
			}
		}
	}

}

class Info {
	private String name = "wechart";
	private String content = "΢��С����";
	private boolean flag = false;

	// Ϊ��������ͬ��,��ӵȴ��뻽��
	public synchronized void set(String name, String content) {
		if (!flag) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.setName(name);
		this.setContent(content);
		flag = false;
		super.notify();
	}

	public synchronized void get() {
		if (flag) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(this.getName() + "-->" + this.getContent());
		flag = true;
		super.notify();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
