package hemi.dataStructure;



public class Test {
    @org.junit.Test
    public void main() {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(0);
        queue.dequeue();
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);

    }

}

class ArrayQueue {
    private int front;
    private int back ;
    private int currentSize;
    private Object[] theArray;
    private static final int DEFAULT_CAPACITY = 5;

    public ArrayQueue() {
        theArray = new Object[DEFAULT_CAPACITY];
        front =0;
        back=-1;
        currentSize =0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public Object dequeue() {
        if(isEmpty())
            return -999;
        currentSize--;
        Object oldValue = theArray[front];
        front = increment(front);
        return oldValue;
    }

    public void enqueue(int in) {
        if(currentSize == theArray.length)
            doubleQueue();
        back=increment(back);
        theArray[back]=in;
        currentSize++;
    }

    private int increment(int x) {
        if (x == theArray.length - 1) {
            x = 0;
        } else {
            ++x;
        }
        return x;
    }

    private void doubleQueue() {
        Object[] newArray = new Object[theArray.length*2];
        for(int i=0;i<currentSize;i++,front = increment(front))
            newArray[i] = theArray[front];
        theArray = newArray;
        front = 0;
        back = currentSize -1;
    }
}


class MyException extends Exception {
    public MyException(String msg) {
        super(msg);
    }
}
