package demo;

public class testThread extends Thread{
    @Override
    public void run() {
        super.run();

        while(true){

            System.out.println("守护线程运行中!!!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
