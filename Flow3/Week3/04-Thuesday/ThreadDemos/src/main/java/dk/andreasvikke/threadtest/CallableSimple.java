package dk.andreasvikke.threadtest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author andreas
 */
public class CallableSimple {
    private static ExecutorService executor = Executors.newCachedThreadPool();
    
    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            executor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
    }
}
