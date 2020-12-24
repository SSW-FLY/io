package itcode.time;

import itcode.handler.TimeServerHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author imp
 * @date 2020/12/24
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        try (ServerSocket server = new ServerSocket(port)) {
            for (; ; ) {
                Socket socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
