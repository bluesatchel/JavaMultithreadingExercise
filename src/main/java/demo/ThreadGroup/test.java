package demo.ThreadGroup;

public class test {
    public static void main(String[] args) {
        //返回当前main线程的线程组
        ThreadGroup mainGroup=Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup);

        //定义线程组,如果不指定所属线程组,则自动归属到当前线程所属的线程组中
        ThreadGroup group1=new ThreadGroup("group1");
        System.out.println(group1);
        System.out.println(group1.getParent()==mainGroup);

        //定义线程组,同时指定父线程组
        ThreadGroup group2=new ThreadGroup(mainGroup,"group2");
        System.out.println(group2.getParent()==mainGroup);

        //创建线程时不指定线程组
        Runnable r=new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        };
        Thread t1=new Thread(r,"t1");
        System.out.println(t1);

        //创建线程时指定线程组
        Thread t2=new Thread(group1,r,"t2");
        System.out.println(t2);
    }
}
