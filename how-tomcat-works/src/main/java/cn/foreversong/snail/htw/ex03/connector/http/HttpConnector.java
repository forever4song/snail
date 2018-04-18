package cn.foreversong.snail.htw.ex03.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/3
 * Time: 12:33
 * Description: 连接器
 */
public class HttpConnector implements Runnable{
    boolean stopped;    // 应用进程停止标志
    private String scheme = "http"; // 模型

    public String getScheme() {
        return scheme;
    }

    /**
     * 线程方法，监听端口，接受请求，调用处理类进行处理
     */
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try{
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stopped){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }
            // Hand this socket off to an HttpProcessor
            HttpProcessor httpProcessor = new HttpProcessor(this);
            httpProcessor.process(socket);

        }
    }

    /**
     * start方法，创建线程并运行连接器程序
     */
    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
}