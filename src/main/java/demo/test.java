package demo;

public class test {

    public static void main(String[] args) {
        testThread thread=new testThread();
        thread.setDaemon(true);

        thread.start();


    }

}
