package demo.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test7 {
    //定义锁
    static Lock lock=new ReentrantLock();
    //获得Condition对象
    static Condition condition=lock.newCondition();

    static class SubThread extends Thread{
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("method lock");
                condition.await();//等待
                System.out.println("method await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("method unlock");
            }


        }
    }
    public static void main(String[] args) {

    }
}
