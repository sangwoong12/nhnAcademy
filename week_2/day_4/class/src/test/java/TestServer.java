import org.example.Server;

public class TestServer {
    public static void main(String[] args) {
        Server server = new Server(1234);
        server.start();
    }
}
