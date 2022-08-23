package demo.ReadWriteLock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class test1 {
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
    }
    public static void main(String[] args) {
        Service service=new Service();
        for (int i=0;i<6;i++){
            new Thread(() -> service.read()).start();
        }
    }
}
