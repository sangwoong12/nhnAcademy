package ex02;

public class App {
    public static void main(String[] args) {
        PassengerList passengerList = new PassengerList();
        passengerList.getDate().forEach(System.out::println);
    }
}
