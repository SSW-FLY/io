package itcode.handler;


import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoHandler implements Runnable {
    final SocketChannel channel;

    final SelectionKey sk;

    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    static final int RECEIVING = 0, SENDING = 1;

    int state = RECEIVING;

    static ExecutorService pool = Executors.newFixedThreadPool(4);

    public MultiThreadEchoHandler(Selector selector, SocketChannel c) throws Exception {
        this.channel = c;
        c.configureBlocking(false);
        sk = channel.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        pool.submit(this::asyncRun);
    }

    public synchronized void asyncRun() {
        try {
            if (state == SENDING) {
                channel.write(byteBuffer);
                byteBuffer.clear();
                sk.interestOps(SelectionKey.OP_READ);
                state = RECEIVING;
            } else if (state == RECEIVING) {
                int length = 0;
                while ((length = channel.read(byteBuffer)) > 0) {
                    System.out.println(new String(byteBuffer.array(), 0, length));
                }
                byteBuffer.flip();
                sk.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
