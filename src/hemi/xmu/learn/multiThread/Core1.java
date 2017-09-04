package hemi.xmu.learn.multiThread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Hemi on 2017/5/26.
 */
public class Core1 {
    public static void main(String args[]) throws InterruptedException {
        HasSelfPrivateNum num = new HasSelfPrivateNum();
//        HasSelfPrivateNum num2 = new HasSelfPrivateNum();


        ObjectService service = new ObjectService("te");

//        ThreadB threadB = new ThreadB(service);
//        threadB.start();
//        threadB.setName("sub1");


        LockService lockService = new LockService();
        ThreadA threadA = new ThreadA(lockService);
        threadA.setName("A");
        threadA.start();

/*        ThreadB threadB = new ThreadB(lockService);
        threadB.setName("B");
        threadB.start();*/

/*        Thread.sleep(2000);
        lockService.signal();*/

        System.out.println("main end...");
    }
}

class LockService{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    public void await(){
        try {
            lock.lock();
            System.out.println("into await, "+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            condition1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("unlocked  , "+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
        }
    }
    public void signal(){
        lock.lock();
        System.out.println("signalAll时间为 "+System.currentTimeMillis());
        condition2.signalAll();
        lock.unlock();
    }

    public void method1(){
        try{
            lock.lock();
            System.out.println("1method begin, "+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
//            condition2.await();
            Thread.sleep(50);
            method2();
            System.out.println("1method   end, "+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void method2(){
        try{
            lock.lock();
            System.out.println("2method begin, "+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            Thread.sleep(50);
            System.out.println("2method   end, "+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class ValueObject{
    public static List list = new ArrayList<Integer>();
}


class MyThread extends Thread {
    private int count =5;
    /*
    public MyThread(String name){
        super();
        this.setName(name);
    }
    */
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin ");
            Thread.sleep(3000);
            System.out.println("run end");
            /*for (int i = 0; i < 20000; i++) {
                if (this.interrupted()) {
                    System.out.println("线程停止。");
                    throw new InterruptedException();
                }
                System.out.println("i=" + (i + 1));
            }
            System.out.println("for外的语句，");*/
        }catch (InterruptedException e){
            System.out.println("线程内的异常");
            e.printStackTrace();
        }

        /*while (count>0){
            count--;
            System.out.println(this.currentThread().getName()+"----"+count);

        }*/

        /*try {
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("run=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}

class ThreadA extends Thread {
    private LockService obj ;
    public ThreadA(LockService obj){
        this.obj=obj;
    }
    @Override
    public void run() {
        obj.method1();
    }
}

class ThreadB extends Thread {
    private LockService obj ;
    public ThreadB(LockService obj){
        this.obj=obj;
    }
    @Override
    public void run() {
        obj.method1();
    }
}

class HasSelfPrivateNum {

    private int num=0;
    public synchronized void add(String username) {
        try{
            System.out.println("begin add() threadName="+Thread.currentThread().getName());
            if(username.equals("a")){
                Thread.sleep(2000);
                num=100;
                System.out.println("a set over!");
                test();
            }else {
                num=200;
                System.out.println("b set over!");
            }
            System.out.println(username+" num="+num);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public synchronized void test(){
        System.out.println("test method");
    }
}

class ObjectService{
    public ObjectService(String lock){
        this.lock=lock;
    }

    private String anyString = new String();//实例变量

    synchronized static public void serviceAstatic(){
        try{
            System.out.println("A begin time = "+System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("A end time = "+System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void serviceB(){
        synchronized (ObjectService.class){

            System.out.println("B begin time = "+System.currentTimeMillis());
            System.out.println("B end time = "+System.currentTimeMillis());
        }
    }
    public void otherService(){
        System.out.println("--------------run otherService..."+System.currentTimeMillis());
    }

    public void blockString(){
        try{
            synchronized (anyString){
                System.out.println(Thread.currentThread().getName()+" in "+System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+" out "+System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private String lock;

    public void add(){
        synchronized (lock){
            ValueObject.list.add(1);
            lock.notifyAll();
        }
    }

    public void subtract(){
        try {
            synchronized (lock){
                while (ValueObject.list.size()==0){
                    System.out.println("wait begin ThreadName = "+Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait end ThreadName = "+Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                System.out.println("list size = "+ValueObject.list.size());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


