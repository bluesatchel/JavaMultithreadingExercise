package demo.reentrant;


import javax.print.attribute.standard.Severity;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test4 {
    static class Server{
        private Lock lock=new ReentrantLock();//定义锁对象
        public void serviceMethod(){
            try {
                //lock.lock();//获得锁,即使调用了线程的interrupt()方法,该线程也没有真正的中断
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName()+"--- begin lock");
                //执行一段耗时的操作
                for (int i=0;i<Integer.MAX_VALUE;i++){

                    new StringBuilder();
                }
                System.out.println(Thread.currentThread().getName()+"--- end lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();//释放锁
                System.out.println(Thread.currentThread().getName()+"*** 释放锁");
            }
        }
        public static void main(String[] args) throws InterruptedException {
            Server s =new Server();
            Runnable r=new Runnable() {
                @Override
                public void run() {
                    s.serviceMethod();
                }
            };
            Thread t1=new Thread(r);
            t1.start();
            Thread.sleep(200);
            Thread t2=new Thread(r);
            t2.start();
            Thread.sleep(200);
            t2.interrupt();
        }
    }
}
