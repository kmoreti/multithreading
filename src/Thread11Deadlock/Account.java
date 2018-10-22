package Thread11Deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private Lock lock;
    private int balance = 10000;

    public Account() {
        lock = new ReentrantLock();
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }

    public void transfer(Account acc, int amount) {
        this.withdraw(amount);
        acc.deposit(amount);
    }
}
