package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by adarsh.sharma on 11/06/18.
 */
public class ArrayBlockingQueueTest {
    BlockingQueue<Integer> queue;

    public ArrayBlockingQueueTest() {
        queue = new ArrayBlockingQueue<>(2);
        queue.add(1);
        queue.add(2);
    }

    public Integer getItem() throws InterruptedException {
        return queue.take();
    }

    public void returnItem(Integer val) throws InterruptedException {
        queue.put(val);
    }

    public static void main(String[] args) {
        ArrayBlockingQueueTest test = new ArrayBlockingQueueTest();
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(test)).start();
        }
    }

    static class Task implements Runnable {
        ArrayBlockingQueueTest test;

        public Task(ArrayBlockingQueueTest test) {
            this.test = test;
        }

        @Override
        public void run() {
            try {
                Integer item = test.getItem();
                Thread.sleep(3000);
                System.out.println(item);
                test.returnItem(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
