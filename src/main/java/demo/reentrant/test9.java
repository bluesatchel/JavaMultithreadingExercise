package demo.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test9 {

    static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();

                        System.out.println(Thread.currentThread().getName()+"获得了锁对象");
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
        for (int i = 0; i < 4; i++) {
            new Thread(runnable).start();
        }
    }
}
