package demo.producerStack;



public class ProducerThread extends Thread{
    private MyStack stack;

    public ProducerThread(MyStack stack){
        this.stack=stack;
    }

    @Override
    public void run() {
        while (true){
            stack.push();
        }
    }
}
