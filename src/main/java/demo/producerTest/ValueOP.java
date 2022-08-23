package demo.producerTest;

public class ValueOP {

    private String value="";

    //定义方法修改value的值
    public void setValue(){
        synchronized (this){
            //如果不是空串就等待
            while (!value.equalsIgnoreCase("")){

                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果value值是空串,就设置value字段的值
            String value=System.currentTimeMillis()+"--"+System.nanoTime();
            System.out.println("set设置的值是:"+value);
            this.value=value;
            this.notifyAll();
        }
    }

    public void getValue(){
        synchronized (this){
            //是空串就等待
            while (value.equalsIgnoreCase("")){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //不是空串,读取字段值
            System.out.println("get的值是:"+value);
            this.value="";
            this.notifyAll();
        }
    }
}
