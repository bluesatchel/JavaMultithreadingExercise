package demo.reentrant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
public class test5 {
    static class TimeLock implements Runnable{
        private static ReentrantLock lock=new ReentrantLock();
        @Override
        public void run() {
            try {
                if(lock.tryLock(3, TimeUnit.SECONDS)){
                    System.out.println(Thread.currentThread().getName()+"获得锁,执行耗时任务");
                    Thread.sleep(4000);

                }else{
                    System.out.println(Thread.currentThread().getName()+"没有获得锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        TimeLock timeLock=new TimeLock();
        Thread t1=new Thread(timeLock);
        Thread t2=new Thread(timeLock);
        t1.start();
        t2.start();
    }
}
