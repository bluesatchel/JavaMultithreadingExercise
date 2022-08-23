package demo.wait;

import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main(String[] args) throws InterruptedException {
        List<String> list=new ArrayList<>();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list){
                    if(list.size()!=5){
                        System.out.println("线程1开始等待"+System.currentTimeMillis());
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("线程1被唤醒"+System.currentTimeMillis());
                    }
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list){
                    for (int i=0;i<10;i++){
                        list.add("string"+i);
                        System.out.println("t2添加了第"+i+"个数据");
                        if(list.size()==5){
                            list.notify();
                            System.out.println("线程2已结发起唤醒通知");
                        }
                    }
                }
            }
        });
        t1.start();
        Thread.sleep(500);//保证线程1先执行
        t2.start();
    }
}
