package ex02;

public class App {
    public static void main(String[] args) {
        PassengerList passengerList = new PassengerList();
        passengerList.getDate().forEach(System.out::println);
        a();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void a(){
        PassengerList passengerList = new PassengerList();
        passengerList.getDate().forEach(System.out::println);
    }
}
