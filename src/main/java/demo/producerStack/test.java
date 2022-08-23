package demo.producerStack;



public class test {
    public static void main(String[] args) {

        MyStack stack=new MyStack();

        ProducerThread p=new ProducerThread(stack);
        ConsumerThread c=new ConsumerThread(stack);

        p.start();
        c.start();
    }
}
