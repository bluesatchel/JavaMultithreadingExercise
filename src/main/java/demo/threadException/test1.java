package demo.threadException;

public class test1 {
    public static void main(String[] args) {
        //设置线程异常全局的回调接口
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName()+"产生了异常:"+e.getMessage());
            }
        });
        Thread t1=new Thread(() -> System.out.println(10/0));
        t1.start();
    }
}
