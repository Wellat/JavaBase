package hemi.mldn.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 序列化操作
 * @author Vanguard
 *
 */
public class SerializableD {
	public static void main(String[] args) throws Exception{
		File f = new File("D:"+File.separator+"filedemoser.txt");
		ser(f);
		dser(f);
	}
	public static void ser(File file) throws Exception{
		ObjectOutputStream oos = null;
		OutputStream out = new FileOutputStream(file);//文件输出流
		oos = new ObjectOutputStream(out);
		oos.writeObject(new Person("张三","30","123456"));
		oos.close();
	}
	public static void dser(File file) throws Exception{
		ObjectInputStream ois = null;
		InputStream input = new FileInputStream(file);
		ois = new ObjectInputStream(input);
		Object obj = ois.readObject();
		ois.close();
		System.out.println(obj);
	}
}
class Person implements Serializable{
	private static final long serialVersionUID = 1219784322318073250L;
	private String name;
	private String age;	
	private transient String password;//transient关键字不被序列化
	
	public Person(String name, String age, String password) {
		super();
		this.name = name;
		this.age = age;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", password=" + password + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}