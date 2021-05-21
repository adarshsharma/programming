package futures;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by adarsh.sharma on 20/01/18.
 */
public class Futures {
    static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
        test2();
        System.out.println("done");
    }

    private static void test2() throws InterruptedException {
//        Executor executor = Executors.newFixedThreadPool(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<String> completionService =
                new ExecutorCompletionService<>(executor);

        Long start = System.currentTimeMillis();
        System.out.println(start);
        completionService.submit(callable("task1", 5));
        completionService.submit(callable("task2", 1));
        completionService.submit(callable("task3", 2));

        int received = 0;
        boolean errors = false;

        while(received < 3 && !errors) {
            Future<String> resultFuture = completionService.take(); //blocks if none available
            try {
                String result = resultFuture.get();
                System.out.println(System.currentTimeMillis() - start);
                System.out.println(result);
                received ++;
            }
            catch(Exception e) {
                errors = true;
            }
        }
        executor.shutdown();
    }

    private static void test1() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 5),
                callable("task2", 1),
                callable("task3", 3));

        String result = executor.invokeAny(callables);
        System.out.println(result);
    }
}
