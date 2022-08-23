package demo.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class test4 {
    static class MyTask implements Runnable{
        private String name;
        public MyTask(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(name+"任务正在被线程"+Thread.currentThread().getId()+" 执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        //定义扩展线程池,可以定义线程池类继承ThreadPoolExecutor方法,在子类中重写beforeExecute()/afterExecute()方法
        //也可以直接使用ThreadPoolExecutor的内部类
        ExecutorService executorService=new ThreadPoolExecutor(5,5,0, TimeUnit.SECONDS,new LinkedBlockingDeque<>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t.getId()+"线程准备执行任务"+((MyTask)r).name);
            }
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(((MyTask)r).name+"任务执行完毕");
            }
            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        //创建5个任务添加到线程池中执行
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyTask("task--"+i));
        }
        //关闭线程池
        executorService.shutdown();//关闭线程池仅仅是说线程池不再接收新的任务,线程池中已接收的任务正常执行完毕
    }
}
