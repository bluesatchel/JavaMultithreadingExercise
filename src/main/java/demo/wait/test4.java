package demo.wait;

import java.util.ArrayList;
import java.util.List;

public class test4 {
    static List list=new ArrayList<>();
    public static void subtract(){
        synchronized (list){
            while(list.size()==0){
                try {
                    System.out.println(Thread.currentThread().getName()+"开始等待");
                    list.wait();
                    System.out.println(Thread.currentThread().getName()+"结束等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              }
            Object o = list.remove(0);
            System.out.println(Thread.currentThread().getName()+"从集合中取了"+o+",,,剩余数量为"+list.size());

        }
    }
    public static void add(){
        synchronized (list){

            list.add("datum");
            System.out.println(Thread.currentThread().getName()+"添加了一条数据");
            list.notifyAll();
        }
    }
    static class SubtractThread extends Thread{
        @Override
        public void run() {
            subtract();
        }
    }
    static class AddThread extends Thread{

        @Override
        public void run() {
            add();
        }
    }
    public static void main(String[] args) throws InterruptedException {


        SubtractThread subtractThread1=new SubtractThread();
        SubtractThread subtractThread2=new SubtractThread();
        subtractThread1.setName("subtractThread1");
        subtractThread2.setName("subtractThread2");
        AddThread addThread1=new AddThread();
        addThread1.setName("addThread1");

        //开启两个取数据的线程,再开启添加数据的线程
        subtractThread1.start();
        subtractThread2.start();
        addThread1.start();


    }


}

