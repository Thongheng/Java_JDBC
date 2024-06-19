import java.sql.*;

public class Main {
    Connection con;
    ResultSet rs;
    Statement stmt;
    String url = "jdbc:mysql://localhost:3307/Product";
    String username = "root";
    String password = "root";

    public void getConnection() {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully Connected to the Database");
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
             throw new RuntimeException(e);
        }
    }


    public void showProduct() {
        getConnection();
        try {
            stmt = con.createStatement();
            String query = "SELECT * FROM products";
            rs = stmt.executeQuery(query);

            String format = "| %-5s | %-30s | %-20s | %-15s |%n";
            System.out.println("+-------+--------------------------------+----------------------+-----------------+");
            System.out.printf(format, "ID", "Name", "Price per Unit", "Active for Sell");
            System.out.println("+-------+--------------------------------+----------------------+-----------------+");

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price_per_unit");
                boolean active_for_sell = rs.getBoolean("active_for_sell");
                System.out.printf(format, id, name, price ,active_for_sell);

            }

            System.out.println("+-------+--------------------------------+----------------------+-----------------+");
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.showProduct();
    }
}