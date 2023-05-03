import java.sql.SQLException;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        try {
            SqlHelper sqlHelper = new SqlHelper("jdbc:mysql://localhost:3306/module06;user=root;password=");

            List<Aircraft> execute = sqlHelper.execute("select * from Aircraft", Aircraft.class);
            execute.forEach(System.out::println);
            int update = sqlHelper.execute("UPDATE Aircraft SET Airline = ? WHERE AircraftNo = ?", "제주공항", 101);
            System.out.println("update = " + update);
            List<Aircraft> execute2 = sqlHelper.execute("select * from Aircraft", Aircraft.class);
            execute2.forEach(System.out::println);
            List<Passenger> passengers = sqlHelper.execute("select * from Passenger", Passenger.class);
            passengers.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}