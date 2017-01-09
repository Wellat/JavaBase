package hemi.mldn.undefine;
/*
 * ���ģʽ��������
 * ����ģʽ������ģʽ��������ģʽ
 */
public class DesignMode {
	public static void main(String args[]){
		//����ģʽ�������ɶ�
		System.out.println("-----����ģʽ-----");
		Fruit f = null;
		f = Factory.getInstance("apple");
		f.eat();
		
		//����ģʽ��������Service,ֻ��עҵ��
		System.out.println("-----����ģʽ-----");
		Fruit fd =null;
		fd = new Proxy(new Orange());
		fd.eat();
		
		//������ģʽ
		//һ���ӿ����ȱ�һ�������ࣨ��Ϊ����������ʵ�֣����ڴ˳�������ʵ�ֽӿڵ����ɷ�����������Ϊ�գ���
		//���Ժ������ֱ�Ӽ̳д˳����࣬�Ϳ�����ѡ��ظ�д����Ҫ�ķ���
		System.out.println("-----������ģʽ-----");
		Window win = new WindowImpl();
		win.open();
		win.close();
	}
}

//����һ��ˮ���Ľӿ�
interface Fruit{
	public void eat();
}

class Apple implements Fruit{
	@Override
	public void eat() {
		System.out.println("��ƻ����");
	}	
}

class Orange implements Fruit{
	@Override
	public void eat() {
		System.out.println("�����ӣ�");
	}
}

/*
 * ���幤����
 */
class Factory{
	public static Fruit getInstance(String className){
		Fruit f = null;
		if("apple".equals(className)){
			f = new Apple();
		}
		if("orange".equals(className)){
			f = new Orange();
		}
		return f;
	}
}

/*
 * ����ģʽ
 */
class Proxy implements Fruit{
	private Fruit fruit;
	public Proxy(Fruit fruit){
		this.fruit=fruit;
	}
	public void wash(){
		System.out.println("ϴˢˢ��");
	}
	@Override
	public void eat() {
		this.wash();
		this.fruit.eat();
	}	
}

/*
 * ���������ʵ��
 */
interface Window{
	void open();
	void close();
	void activated();
	void iconified();
	void deicoinfied();
}
//���������ʵ�ֽӿڣ��ڴ����и�д�������������еķ�����Ϊ��
abstract class WindowAdapter implements Window{
	public void open(){}
	public void close(){}
	public void activated(){}
	public void iconified(){}
	public void deicoinfied(){}
}
//����̳г����࣬��ѡ���ʵ����Ҫ�ķ���
class WindowImpl extends WindowAdapter{
	public void open(){
		System.out.println("���ڴ򿪡�");
	}
	public void close(){
		System.out.println("���ڹرա�");
	}
}



