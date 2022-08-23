package demo.reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test2 {
    //定义显示锁
    static Lock lock=new ReentrantLock();

    public static void sm1(){
        try{
            lock.lock();//获得锁
            System.out.println(Thread.currentThread().getName()+"---method 1---"+System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"---method 1---"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
    }
    public static void sm2(){
        try{
            lock.lock();//获得锁
            System.out.println(Thread.currentThread().getName()+"---method 2---"+System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"---method 2---"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
    }
    public static void main(String[] args) {
        Runnable r1=new Runnable() {
            @Override
            public void run() {
                sm1();
            }
        };
        Runnable r2=new Runnable() {
            @Override
            public void run() {
                sm2();
            }
        };
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r2).start();
        new Thread(r2).start();
    }
}
