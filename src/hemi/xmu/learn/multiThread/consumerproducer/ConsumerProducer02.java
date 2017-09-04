package hemi.xmu.learn.multiThread.consumerproducer;

import java.util.concurrent.*;

/**
 * use BlockingQueue & ThreadPool
 *
 * Created by Vanguard on 2017/4/14.
 */
public class ConsumerProducer02 {
    public static void main(String[] args){
        BlockingQueue<Task> buffer = new LinkedBlockingQueue<>(Constants.MAX_BUFFER_SIZE);
        ExecutorService es = Executors.newFixedThreadPool(Constants.NUM_CONSUMER + Constants.NUM_PRODUCER);
        for (int i = 1; i <= Constants.NUM_PRODUCER; i++) {
            es.execute(new Producer2(buffer));
        }
        for (int i = 1; i <= Constants.NUM_CONSUMER; i++) {
            es.execute(new Consumer2(buffer));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdownNow();
    }
}

class Consumer2 implements Runnable {
    private BlockingQueue<Task> buffer;
    public Consumer2(BlockingQueue<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                Task task = buffer.take();
                System.out.println("消耗 "+task.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer2 implements Runnable {
    private BlockingQueue<Task> buffer;

    public Producer2(BlockingQueue<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                Task task = new Task();
                buffer.put(task);
                System.out.println("生产 "+task.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


