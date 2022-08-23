package demo.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test1 {
    public static void main(String[] args) {
        //创建有5个线程大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //向线程池中提交18个任务
        for (int i = 0; i < 18; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId()+"号任务正在执行任务,开始时间:"+System.currentTimeMillis());

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
