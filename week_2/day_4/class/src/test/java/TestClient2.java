import org.example.Client;

import java.io.IOException;

public class TestClient2 {
    public static void main(String[] args) {
        try {
            Client client = new Client(1234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
