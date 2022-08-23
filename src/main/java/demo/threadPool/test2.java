package demo.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class test2 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        //延迟两秒之后执行任务   schedule(Runnable任务,延迟时长,时间单位)
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {

            }
        },2, TimeUnit.SECONDS);

        //以固定的频率执行任务,开启任务的时间是固定的,在三秒后执行任务,以后每隔2秒重新执行一次
        //如果任务执行时长超过了时间间隔,则任务完成后立即开启下个任务
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

            }
        },3,2,TimeUnit.SECONDS);


        //在上次任务结束后,在固定延迟后再次执行该任务

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

            }
            //不管任务耗时多长,总是在任务完成后2秒后再次开启下个任务
        },3,2,TimeUnit.SECONDS);
    }
}
