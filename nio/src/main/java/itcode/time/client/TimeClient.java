package itcode.time.client;

/**
 * @author imp
 * @date 2020/12/25
 */
public class TimeClient {

    public static void main(String[] args) {
        new Thread(new TimeClientHandle("127.0.0.1",8080),"TimeClient-001");
    }
}
