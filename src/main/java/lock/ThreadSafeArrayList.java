package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadSafeArrayList {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private final Map<Integer, Integer> list = new ConcurrentHashMap<>();

    public void add(Integer k, Integer v) {
        writeLock.lock();
        try {
            Thread.sleep(5000);
            list.put(k, v);
        } catch (InterruptedException e) {
            System.out.println("interrupted exception write");
        } finally {
            writeLock.unlock();
        }
    }

    public Integer get(int i) {
        readLock.lock();
        try {
//            Thread.sleep(1000);
            return list.get(i);
//        } catch (InterruptedException e) {
//            System.out.println("interrupted exception read");
//            return list.get(i);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadSafeArrayList threadSafeArrayList = new ThreadSafeArrayList();
        threadSafeArrayList.add(1, 1);

        for (int i = 0; i < 3; i++) {
            new Thread(new ReadRunnable(threadSafeArrayList, 0)).start();
        }

        new Thread(new WriteRunnable(threadSafeArrayList, 0)).start();

        for (int i = 0; i < 3; i++) {
            new Thread(new ReadRunnable(threadSafeArrayList, 0)).start();
        }
    }

    static class ReadRunnable implements Runnable {
        private ThreadSafeArrayList threadSafeArrayList;
        private long wait;

        public ReadRunnable(ThreadSafeArrayList threadSafeArrayList, long wait) {
            this.threadSafeArrayList = threadSafeArrayList;
            this.wait = wait;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(wait);
                System.out.println(threadSafeArrayList.get(1));
            } catch (InterruptedException e) {
                System.out.println("interrupted exception");
            }
        }
    }

    static class WriteRunnable implements Runnable {
        private ThreadSafeArrayList threadSafeArrayList;
        private long wait;

        public WriteRunnable(ThreadSafeArrayList threadSafeArrayList, long wait) {
            this.threadSafeArrayList = threadSafeArrayList;
            this.wait = wait;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(wait);
                System.out.println("start adding");
                threadSafeArrayList.add(1, 2);
                Thread.sleep(4000);
                System.out.println("end adding");
            } catch (InterruptedException e) {
                System.out.println("interrupted exception");
            }
        }
    }
}
