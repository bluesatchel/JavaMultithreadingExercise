package demo.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//使用condition设计生产者消费者设计模式中两个线程交替打印
public class test8 {
    static class MyService{
        private Lock lock=new ReentrantLock();
        private Condition condition=lock.newCondition();
        private boolean flag=true;
        private void printOne(){
            try {
                lock.lock();
                while (flag){
                    System.out.println(Thread.currentThread().getName()+"waiting...");
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName()+"--------------");
                flag=true;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        private void printTwo(){
            try {
                lock.lock();
                while (!flag){
                    System.out.println(Thread.currentThread().getName()+"waiting...");
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName()+"===============");
                flag=false;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        MyService myService=new MyService();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<100;i++){
                        myService.printOne();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<100;i++){
                        myService.printTwo();
                    }
                }
            }).start();
        }
    }
}
