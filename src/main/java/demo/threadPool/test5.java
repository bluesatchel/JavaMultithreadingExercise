package demo.threadPool;

import java.sql.Time;
import java.util.concurrent.*;

public class test5 {
    static class TraceThreadPoolExecutor extends ThreadPoolExecutor{
        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }
        //定义方法,对执行的任务进行包装,接收两个参数,一个参数接收要执行的任务,另一个参数是Exception异常
        public Runnable wrap(Runnable task,Exception exception){
                return new Runnable() {
                    @Override
                    public void run() {
                        try {
                            task.run();
                        } catch (Exception e) {
                            exception.printStackTrace();
                            throw e;
                        }
                    }
                };
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task,new Exception("自定义跟踪异常")));
        }
        public static void main(String[] args) {
            TraceThreadPoolExecutor traceThreadPoolExecutor = new TraceThreadPoolExecutor(2,5,0, TimeUnit.SECONDS,new SynchronousQueue<>());
            traceThreadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(0/0);
                }
            });
        }
    }
}
