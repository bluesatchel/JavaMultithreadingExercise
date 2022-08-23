package demo;

public class test1 {
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new MyThread().start();
        }
    }
    static class MyThread extends Thread{
        public static int num;
        public synchronized static void add(){
            for(int i=0;i<1000;i++){
                num++;
            }
            System.out.println("num==="+num);
        }
        @Override
        public void run() {
            add();
        }
    }
}
