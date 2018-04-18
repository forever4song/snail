package cn.foreversong.snail.htw.ex02;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-1
 * Time: 上午8:11
 * Description: 服务器
 */
public class HttpServer {

    private boolean shutdown = false;

    /**
     * 应用主进程
     * @param args
     */
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        System.out.println("server start");
        httpServer.await();
        System.out.println("server stop");

    }

    /**
     *  Http服务方法，循环等待请求，给出响应
     */
    public void await(){
        ServerSocket serverSocket = null;
        int port = 8080;
        try{
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        // Loop for the request
        while(!shutdown){
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;

            try {
                socket = serverSocket.accept();
                in = socket.getInputStream();
                out = socket.getOutputStream();
                // Create Request and parse
                Request request = new Request(in);
                request.parse();
                // Create Response
                Response response = new Response(out);
                response.setRequest(request);

                // check if this is request for a servlet or a static sources
                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor servletProcessor = new ServletProcessor();
                    servletProcessor.process(request, response);
                } else {
                    StaticResourceProcessor resourceProcess = new StaticResourceProcessor();
                    resourceProcess.process(request, response);
                }

                // Close the socket
                socket.close();
                //check if the previous URI is a shutdown command
                shutdown = request.getUri().equals(Constants.SHUTDOWN_COMMAND);
            }catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }

    }
}
