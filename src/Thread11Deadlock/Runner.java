package Thread11Deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

//    private Lock lock1 = new ReentrantLock();
//    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while(true) {
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {
                if (gotFirstLock && gotSecondLock) {
                    return;
                }

                if (gotFirstLock) {
                    firstLock.unlock();
                }

                if (gotSecondLock) {
                    secondLock.unlock();
                }
            }
            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {

            acquireLocks(acc1.getLock(), acc2.getLock());

            try {
                acc1.transfer(acc2, random.nextInt(100));
            } finally {
                acc1.getLock().unlock();
                acc2.getLock().unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {

            acquireLocks(acc2.getLock(), acc1.getLock());

            try {
                acc2.transfer(acc1, random.nextInt(100));
            } finally {
                acc2.getLock().unlock();
                acc1.getLock().unlock();
            }
        }
    }

    public void finished() {
        System.out.println("Account 1 balance: " + acc1.getBalance());
        System.out.println("Account 2 balance: " + acc2.getBalance());
        System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
    }
}
