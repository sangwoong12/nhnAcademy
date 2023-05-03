public class Aircraft {
    int AircraftNo;
    String KindOfAircraft;
    String Airline;

    public Aircraft() {

    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "AircraftNo=" + AircraftNo +
                ", KindOfAircraft='" + KindOfAircraft + '\'' +
                ", Airline='" + Airline + '\'' +
                '}';
    }
}
