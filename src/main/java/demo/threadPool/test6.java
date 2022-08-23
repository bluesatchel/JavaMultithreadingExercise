package demo.threadPool;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class test6 {
    static class CountTask extends RecursiveTask<Long>{
        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }
        private static final int THRESHOLD=10000;//阈值为10000
        private static final int TASKNUM=100;
        private long start;//计算数列的起始值
        private long end;//计算数列的结束值
        //重写RecursiveTask类的compute()方法
        @Override
        protected Long compute() {
            long sum=0;//保存计算的结果
            //判断任务是否需要继续分解,如果当前数列end与start范围的数超过阈值THRESHOLD,就需要继续分解
            if(end-start<THRESHOLD){
                //小于阈值就直接计算
                for (long i=start;i<=end;i++){
                    sum+=i;
                }
            }else{//数列范围超过阈值,需要继续分解
                //约定每次分解成100个小任务,计算每个任务的计算量
                long step=(end-start)/TASKNUM;
                ArrayList<CountTask> subTaskList=new ArrayList<>();
                long pos=start;//每个任务的起始位置
                for (int i = 0; i < TASKNUM; i++) {
                    long lastOne=pos+step;//每个任务的结束位置
                    if(lastOne>end){//调整最后一个任务的结束位置
                        lastOne=end;
                    }
                    //创建子任务
                    CountTask task=new CountTask(pos,lastOne);
                    //把任务添加到集合中
                    subTaskList.add(task);
                    //使用fork()提交子任务
                    task.fork();
                    //调整下个任务的起始位置
                    pos+=step+1;
                }
                //等待所有的子任务结束后,合并计算结果
                for (CountTask task:subTaskList){
                    sum+=task.join();
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        //创建ForkJoinPool线程池
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        //创建一个大任务
        CountTask task=new CountTask(0,2000000);
        //把大任务提交给线程池
        ForkJoinTask<Long> result=forkJoinPool.submit(task);
        try {
            Long res=result.get();//调用任务的get()方法返回结果
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
