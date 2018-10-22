package Thread11Deadlock;

import java.time.Duration;
import java.time.Instant;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();

        Thread t1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Starting...");
        Instant start = Instant.now();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
        System.out.println("Time elapsed: " + Duration.between(start, Instant.now()).toMillis());
    }
}

