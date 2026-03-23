import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HardwareRepository {

    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\jeffr\\Downloads\\Task4\\src\\Task4.db";

    public List<Hardware> getAllHardware() {
        List<Hardware> hardwareList = new ArrayList<>();

        String[] possibleTables = {
                "\"HARDWARE MASTERLIST\"",
                "Hardware_Masterlist",
                "hardware_masterlist"
        };

        for (String table : possibleTables) {
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM " + table)) {
                
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String brand = rs.getString("Brand");
                    int spec = rs.getInt("Spec");
                    String type = rs.getString("Type");

                   if (type.equalsIgnoreCase("Laptop")) {
                        hardwareList.add(new Laptop(id, brand, spec));
                    } else if (type.equalsIgnoreCase("Phone")) {
                        hardwareList.add(new Phone(id, brand, spec));
                    }
                } return hardwareList;

            } catch (SQLException e) {}
        }

        System.out.println("Error: Could not connect to database.");
        return hardwareList;
    }
}
