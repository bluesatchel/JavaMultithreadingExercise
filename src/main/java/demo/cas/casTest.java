package demo.cas;

public class casTest {

    public static void main(String[] args) {
        final CASCounter casCounter=new CASCounter();

        for(int i=0;i<100;i++){
            new Thread(() -> System.out.println(casCounter.incrementAndGet())).start();
        }
    }
}
class CASCounter{

    volatile private long value;
    public long getValue() {
        return value;
    }
    //定义一个Compare and swap方法
    private boolean cas(long expectedValue,long newValue){
        synchronized (this){
            if(value==expectedValue){
                value=newValue;
                return true;
            }else{
                return false;
            }
        }

    }
    public long incrementAndGet(){
        long oldValue;
        long newValue;
        do{
            oldValue=value;
            newValue=oldValue+1;

        }while (!cas(oldValue,newValue));
        return value;
    }
}
