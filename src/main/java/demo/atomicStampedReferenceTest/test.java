package demo.atomicStampedReferenceTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class test {


    private static AtomicStampedReference<String> stampedReference=new AtomicStampedReference<>("abc",0);

    public static void main(String[] args) throws InterruptedException {

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                stampedReference.compareAndSet("abc","def",stampedReference.getStamp(),stampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"----"+stampedReference.getReference());
                stampedReference.compareAndSet("def","abc",stampedReference.getStamp(),stampedReference.getStamp()+1);
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp=stampedReference.getStamp();
                //先获得了版本号,但是sleep期间上面的线程导致版本号+1了
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //此处版本号不一致,所以并没有做出修改
                System.out.println(stampedReference.compareAndSet("abc","hello",stamp,stamp+1));
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(stampedReference.getReference());
    }

}
