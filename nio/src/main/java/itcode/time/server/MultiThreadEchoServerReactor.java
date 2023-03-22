package itcode.time.server;

import itcode.handler.MultiThreadEchoHandler;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadEchoServerReactor {
    ServerSocketChannel serverSocketChannel;

    AtomicInteger next = new AtomicInteger();

    Selector[] selectors = new Selector[2];

    SubReactor[] subReactors = null;

    MultiThreadEchoServerReactor() throws Exception {
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8080);
        serverSocketChannel.socket().bind(address);
        serverSocketChannel.configureBlocking(false);
        SelectionKey sk = serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptorHandler());
        SubReactor subReactor1 = new SubReactor(selectors[0]);
        SubReactor subReactor2 = new SubReactor(selectors[1]);
        subReactors = new SubReactor[]{subReactor1, subReactor2};
    }

    private void startService() {
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }


    static class SubReactor implements Runnable {
        final Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    for (SelectionKey sk : keys) {
                        dispatch(sk);
                    }
                    keys.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void dispatch(SelectionKey sk) {
            Runnable attachment = (Runnable) sk.attachment();
            if (attachment != null) {
                attachment.run();
            }
        }
    }


    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel accept = serverSocketChannel.accept();
                if (accept != null) {
                    new MultiThreadEchoHandler(selectors[1], accept);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MultiThreadEchoServerReactor server = new MultiThreadEchoServerReactor();
        server.startService();
    }
}
