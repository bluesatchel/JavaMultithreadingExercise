package demo.wait;

public class test3 {
    private static final Object LOCK=new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK){
                    try {
                        System.out.println("等待开始");
                        LOCK.wait();
                        System.out.println("等待结束");
                    } catch (InterruptedException e) {
                        System.out.println("wait被中断了");
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t1.interrupt();
    }
}
