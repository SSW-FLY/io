package itcode.time.server;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServerReactor implements Runnable {


    Selector selector;

    ServerSocketChannel serverSocketChannel;


    EchoServerReactor() throws Exception {

    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()){
                    SelectionKey next = it.next();
                    next.attach("ssss");
                    dispatch(next);
                }
                keys.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void dispatch(SelectionKey key) {

    }
}
