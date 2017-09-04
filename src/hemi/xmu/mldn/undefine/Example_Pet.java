package hemi.xmu.mldn.undefine;

public class Example_Pet {
	public static void main(String args[]){
		PetShop ps = new PetShop(3);
		ps.add(new Cat("��è","��ɫ",2));
		ps.add(new Cat("��è","��ɫ",4));
		ps.add(new Cat("�ڻ�è","��ɫ",3));
		ps.add(new Cat("��è","��ɫ",3));
		ps.add(new Cat("è","��ɫ",3));
		ps.search("��");
	}
}

class PetShop {
	private Pet[] pets;
	private int index;

	public PetShop(int len) {
		if (len > 0) {
			this.pets = new Pet[len];
		} else {
			this.pets = new Pet[1];
		}
	}

	public boolean add(Pet pet) {
		if (this.index < this.pets.length) {
			this.pets[this.index]=pet;
			this.index++;
			return true;
		}else{
			System.out.println( "index out������ʧ�ܣ�[name=" + pet.getName() + ", color=" + pet.getColor() + ", age=" + pet.getAge() + "]");
			return false;
		}
	}
	protected Pet[] search(String keyword){		
		if(this.pets.length>0){
			for(Pet p:this.pets){
				if(p.getName().indexOf(keyword) != -1
						|| p.getColor().indexOf(keyword) != -1){
					System.out.println( "[name=" + p.getName() + ", color=" + p.getColor() + ", age=" + p.getAge() + "]");
				}
			}
		}
		return null;
	}
}

interface Pet {
	public String getName();

	public String getColor();

	public int getAge();
}

class Dog implements Pet {
	private String name;
	private String color;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Dog(String name, String color, int age) {
		super();
		this.name = name;
		this.color = color;
		this.age = age;
	}	
}

class Cat implements Pet {
	private String name;
	private String color;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Cat(String name, String color, int age) {
		super();
		this.name = name;
		this.color = color;
		this.age = age;
	}

}
