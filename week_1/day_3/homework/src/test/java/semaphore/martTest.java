package semaphore;

import org.example.semaphore.Buyer;
import org.example.semaphore.Seller;
import org.example.semaphore.Store;

import java.util.Random;

public class martTest {
    public static void main(String[] args) {
        Random random = new Random();
        String[] items = {"고등어","문어","꽁치","오징어"};
        Store store = new Store("고등어","문어","꽁치","오징어");
        Seller[] sellers = new Seller[10];
        //생산자 매번 똑같은 물품 들여온다고 가정
        for(int i = 0; i < 10; i++){
            sellers[i] = new Seller(store,items[random.nextInt(items.length-1)],random.nextInt(5)+1);
            sellers[i].start();
        }
        //소비자 랜덤 물품을 랜덤하게 사러오는 10명 있다고 가정
        Buyer[] buyers = new Buyer[10];
        for(int i = 0; i < 10; i++){
            String name = String.valueOf(i);
            buyers[i] = new Buyer(name,store,items[random.nextInt(items.length-1)],random.nextInt(5)+1);
            buyers[i].start();
        }

    }
}
