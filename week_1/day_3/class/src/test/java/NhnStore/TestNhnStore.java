package NhnStore;

import org.example.NhnStore.Buyer;
import org.example.NhnStore.Seller;
import org.example.NhnStore.Store;
import org.example.tip.Sender;

public class TestNhnStore {
    public static void main(String[] args) {
        Store store = new Store();
        Seller seller = new Seller(store);
        Buyer[] buyers = new Buyer[10];
        seller.start();
        for(int i = 0; i < 10; i++){
            String name = String.valueOf(i);
            buyers[i] = new Buyer(name,store);
            buyers[i].start();
        }


    }
}

