package demo.ReadWriteLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class test2 {
    static class Service{
        //定义读写锁
        ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        //定义方法读取数据
        public void read(){
            try {
                readWriteLock.readLock().lock();
                System.out.println(Thread.currentThread().getName()+"获得读锁"+System.currentTimeMillis());

                TimeUnit.SECONDS.sleep(3);//模拟读取数据耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }

        }
        public void write(){
            try {
                readWriteLock.writeLock().lock();
                System.out.println(Thread.currentThread().getName()+"获得写锁"+System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);//模拟读取数据耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }
    public static void main(String[] args) {
        Service service=new Service();
            new Thread(() -> service.write()).start();
            new Thread(() -> service.read()).start();
    }
}
