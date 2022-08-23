package demo.reentrant;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class test6 {

    static class IntLock implements Runnable {
        private static ReentrantLock lock1 = new ReentrantLock();
        private static ReentrantLock lock2 = new ReentrantLock();
        private int lockNum;

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            if (lockNum % 2 == 0) {

                while (true) {
                    try {
                        if (lock1.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "获得锁1,还想获得锁2");
                            Thread.sleep(new Random().nextInt(100));
                            try {
                                if (lock2.tryLock()) {
                                    System.out.println(Thread.currentThread().getName() + "同时获得锁1与锁2");
                                    return;//结束run方法
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (lock2.isHeldByCurrentThread()) {
                                    lock2.unlock();
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock1.isHeldByCurrentThread()) {
                            lock1.unlock();
                        }
                    }
                }
            } else {
                while (true) {
                    try {
                        if (lock2.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "获得锁2,还想获得锁1");
                            Thread.sleep(new Random().nextInt(100));
                            try {
                                if (lock1.tryLock()) {
                                    System.out.println(Thread.currentThread().getName() + "同时获得锁2与锁1");
                                    return;//结束run方法
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (lock1.isHeldByCurrentThread()) {
                                    lock1.unlock();
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock2.isHeldByCurrentThread()) {
                            lock2.unlock();
                        }
                    }
                }

            }
        }
    }
    public static void main(String[] args) {
        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(10);
        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);
        t1.start();
        t2.start();
        //运行后,使用trylock()尝试获得锁,不会傻傻的等待,
        //通过循环不停的再次尝试,如果等待的时间足够长,线程总是或获得想要的资源
    }
}
