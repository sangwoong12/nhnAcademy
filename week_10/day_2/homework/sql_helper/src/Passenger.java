public class Passenger {
    int PassengerNo;
    String PassengerName;
    int Grade;
    int Age;

    public Passenger() {

    }

    @Override
    public String toString() {
        return "Passenger{" +
                "PassengerNo=" + PassengerNo +
                ", PassengerName='" + PassengerName + '\'' +
                ", Grade=" + Grade +
                ", Age=" + Age +
                '}';
    }
}
