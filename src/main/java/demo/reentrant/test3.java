package demo.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test3 extends Thread {
   static class SubThread extends Thread{
      //这里定义为static的Lock对象的原因就是为了保证每个实例对象拿到的都是同一把锁
      private static Lock lock=new ReentrantLock();
      public static int num=0;
      @Override
      public void run() {
         for(int i=0;i<10000;i++){
            try{
               lock.lock();
               lock.lock();
               num++;
            }finally {
               lock.unlock();
               lock.unlock();
            }
         }
      }
   }
   public static void main(String[] args) throws InterruptedException {
      SubThread t1=new SubThread();
      SubThread t2=new SubThread();
      t1.start();
      t2.start();
      //使用join让main线程等待其执行
      t1.join();
      t2.join();
      System.out.println(SubThread.num);
   }
}
