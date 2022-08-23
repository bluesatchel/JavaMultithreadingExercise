package demo.producerStack;

import java.util.ArrayList;
import java.util.List;
//定义类模拟栈
public class MyStack {
    private List list= new ArrayList();
    private static final int MAX=1;
    public synchronized void push(){
        //当栈已满,就等待\
        while (list.size()>=MAX){
            System.out.println(Thread.currentThread().getName()+"开始等待");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String data="data---"+Math.random();
        System.out.println(Thread.currentThread().getName()+"添加了数据"+data);
        list.add(data);
        this.notifyAll();
    }
    public synchronized void pop(){
        while (list.size()==0){
            try {
                System.out.println(Thread.currentThread().getName()+"开始等待");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"出栈数据:  "+list.remove(0));
        this.notifyAll();
    }
}
