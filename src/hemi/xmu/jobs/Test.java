package hemi.xmu.jobs;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
//    HashMap

}
class Clock{
    public static void timer(Date date) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(System.currentTimeMillis()==date.getTime()){

                }
                System.out.println("-------设定要指定任务--------");
            }
        }, 500, 1000);
    }

}

class Singleton{
    private Singleton(){}
    private volatile static Singleton instance = null;
    public static Singleton getInstance(){
        if(null == instance){
            synchronized (Singleton.class){
                if(null==instance){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

class Singleton2{
    private Singleton2(){}
    private static class SingletonHolder{
        private static final Singleton2 instance = new Singleton2();
    }
    public static Singleton2 getInstance(){
        return SingletonHolder.instance;
    }
}

enum Color{
    RED("hong",1),BLACK("hei",2);

    private String name;
    private int index;

    Color(String name,int index){
        this.index=index;
        this.name=name;
    }


}