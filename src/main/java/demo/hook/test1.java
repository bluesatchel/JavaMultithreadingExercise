package demo.hook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class test1 {
    public static void main(String[] args) {
        //1.注入Hook线程,在程序退出时删除.lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("JVM退出,会启动柜当前hook线程,在hook线程中删除.lock文件");
                getLockFile().toFile().delete();
            }
        }));
        //2.程序运行时,检查.lock文件是否存在,如果存在,则抛出异常
        if(getLockFile().toFile().exists()){
            throw new RuntimeException("程序已启动");
        }else{
            try {
                getLockFile().toFile().createNewFile();
                System.out.println("程序启动并创建了.lock文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //模拟程序运行
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("程序运行中----");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static Path getLockFile(){
        return Paths.get("","tmp.lock");
    }
}
