package demo.wait;

public class test1 {

    public static void main(String[] args) throws InterruptedException {
        String lock="hello!!!";
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("线程1开始等待"+System.currentTimeMillis());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程1等待结束"+System.currentTimeMillis());

                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                //notify方法也需要在同步代码块中由锁对象调用
                synchronized (lock){
                    System.out.println("线程2开始唤醒"+System.currentTimeMillis());

                    lock.notify();//唤醒在lock锁对象上等待的线程

                    System.out.println("线程2唤醒结束"+System.currentTimeMillis());
                }
            }
        });
        t1.start();
        Thread.sleep(3000);
        t2.start();
    }
}
