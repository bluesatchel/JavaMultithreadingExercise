package demo.reentrant;

public class test {
    public synchronized void sm1(){
        System.out.println("同步方法1");
        //线程执行sm1()方法,默认this作为锁对象,在sm1()方法中调用了sm2()方法
        //sm2()同步方法默认的锁对象也是this对象,
        //要执行sm2()必须先获得this锁对象,当前this对象被当前线程持有,
        //可以再次获得this对象,这就是锁的重入性,假设锁不可重入的话,可能会造成死锁
        sm2();

    }
    private synchronized void sm2(){
        System.out.println("同步方法2");
        sm3();
    }

    private synchronized void sm3(){

        System.out.println("同步方法3");

    }




    public static void main(String[] args) {



    }

}
