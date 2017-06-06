package hemi.subject.jobs.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Vanguard on 2017/4/14.
 */
public class ConsumerProducer01 {
    public static void main(String[] args){
        List<Task> buffer = new ArrayList<>(Constants.MAX_BUFFER_SIZE);
        for (int i = 1; i <= Constants.NUM_PRODUCER; i++) {
            new Thread(new Producer(buffer)).start();
        }
        for (int i = 1; i <= Constants.NUM_CONSUMER; i++) {
            new Thread(new Consumer(buffer)).start();
        }
    }
}

class Consumer implements Runnable {
    private List<Task> buffer;
    public Consumer(List<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                System.out.println(Thread.currentThread().getName()+" get lock-----consumer");
                while (buffer.isEmpty()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task task = buffer.remove(0);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                buffer.notifyAll();
                System.out.println("完成 " + task.toString());
                System.out.println(Thread.currentThread().getName()+" out lock-----consumer");
            }
        }
    }
}

class Producer implements Runnable {
    private List<Task> buffer;

    public Producer(List<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                System.out.println(Thread.currentThread().getName()+" get lock-----producer");
                while (buffer.size() >= Constants.MAX_BUFFER_SIZE) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task task = new Task();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                buffer.add(task);
                buffer.notifyAll();
                System.out.println("建立 " + task.toString());
                System.out.println(Thread.currentThread().getName()+" out lock-----consumer");
            }
        }
    }
}

class Task {
    private String id;

    public Task() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                '}';
    }
}
