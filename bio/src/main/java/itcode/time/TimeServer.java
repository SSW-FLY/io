package itcode.time;

import itcode.handler.TimeServerHandler;
import itcode.handler.TimeServerHandlerExecutePool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author imp
 * @date 2020/12/24
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket server = new ServerSocket(port)) {
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,1000);
            for (; ; ) {
                Socket socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
