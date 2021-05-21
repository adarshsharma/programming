package practice.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ExecutorServiceTest {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    BlockingQueue<Runnable> tasks = new ArrayBlockingQueue<>(20);
    // ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 30L, TimeUnit.SECONDS,
    //     tasks);

    IntStream.rangeClosed(0, 10)
        .boxed().forEach(i ->
    {
      Task task = new Task(i, executorService);
      // System.out.println("Random: " + threadPoolExecutor.getCorePoolSize());
      // executorService.submit(task);
      executorService.execute(task);
    });
  }

  static class Task implements Runnable {

    int id;
    private ExecutorService executorService;

    public Task(int id, ExecutorService threadPoolExecutor) {
      this.id = id;
      this.executorService = threadPoolExecutor;
    }

    @Override
    public void run() {
      // System.out.println("Before: " + executorService.getPoolSize());
      System.out.println("threadId: " + Thread.currentThread().getId() + " Task id: " + id);
      throw new RuntimeException();
    }
  }

}
