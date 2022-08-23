package demo.pipeStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class test {
    public static void main(String[] args) throws IOException {
        PipedInputStream inputStream=new PipedInputStream();
        PipedOutputStream outputStream=new PipedOutputStream();
        //在输入管道流与输出管道流之间建立连接
        inputStream.connect(outputStream);

        //创建新线程向管道流中写入数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                writeData(outputStream);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readData(inputStream);
            }
        }).start();
    }
    public static void writeData(PipedOutputStream out){
            try {
                for(int i=0;i<100;i++){
                    String data=""+i;
                    out.write(data.getBytes());//把字节数组写入到输出管道流中
                }
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public static void readData(PipedInputStream in){
        byte[] bytes=new byte[1024];
        //从管道输入字节流中读取字节保存到字节数组中
        try {
            int len=in.read(bytes);
            while (len!=-1){
                //把bytes数组中从0开始读到的len个字节转换为字符串打印
                System.out.println(new String(bytes,0,len));
                len=in.read(bytes);//继续向下读
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
