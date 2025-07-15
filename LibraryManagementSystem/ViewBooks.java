import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewBooks {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM books";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\nBooks in Library:\n");
            System.out.format("+------+-----------------------------+------------------------+----------+\n");
            System.out.format("| ID   | Title                       | Author                 | Quantity |\n");
            System.out.format("+------+-----------------------------+------------------------+----------+\n");

            boolean found = false;
            while (rs.next()) {
                found = true;
                int id = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int qty = rs.getInt("quantity");

                System.out.format("| %-4d | %-27s | %-22s | %-8d |\n", id, title, author, qty);
            }

            System.out.format("+------+-----------------------------+------------------------+----------+\n");
            if (!found) {
                System.out.println("No books found.");
            conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
