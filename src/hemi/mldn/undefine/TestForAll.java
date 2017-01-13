package hemi.mldn.undefine;

import java.io.IOException;

public class TestForAll {
	public static void main(String args[]) {
		String str = "LXH:98|MLDN:90|LI:100";
		String[] resultl = str.split("\\|");
		for(String result:resultl){
			System.out.println(result.replaceAll(":", "\t"));
		}		
	}
}

class sys{
	public void systest(){
		Runtime run = Runtime.getRuntime();
		Process pro = null;
		System.out.println("freeMemory:" + run.freeMemory());
		System.out.println("maxMemory():" + run.maxMemory());
		try {
			pro = run.exec("notepad.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pro.destroy();
	}
}