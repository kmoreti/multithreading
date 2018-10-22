package Thread12Semaphores;

import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws InterruptedException {
//        Semaphore semaphore = new Semaphore(1);
//
//        semaphore.release();
//        semaphore.acquire();
//
//        System.out.println("Available permits: " + semaphore.availablePermits());


        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executor.submit(() -> Connection.getInstance().connect());
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS );
    }
}
