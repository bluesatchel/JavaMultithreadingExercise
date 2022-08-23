package demo.ThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test1 {
    static ThreadLocal<SimpleDateFormat> threadLocal=new ThreadLocal<>();
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    //定义Runnable接口实现类
    static class ParseDate implements Runnable{
        private int i=0;
        public ParseDate(int i){
            this.i=i;
        }
        @Override
        public void run() {
            try {
                String text="2099年11月23日 08:39:"+i%60;
                if(threadLocal.get()==null){
                    threadLocal.set(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"));
                }
                Date date=threadLocal.get().parse(text);
                System.out.println(i+"--"+date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(new ParseDate(i)).start();
        }
    }
}
