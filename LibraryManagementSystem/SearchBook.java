import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchBook {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter book title to search: ");
            String title = sc.nextLine();

            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM books WHERE title LIKE ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, "%" + title + "%");

            ResultSet rs = pst.executeQuery();

            System.out.println("\nBooks in Library:\n");
            System.out.format("+------+-----------------------------+------------------------+----------+\n");
            System.out.format("| ID   | Title                       | Author                 | Quantity |\n");
            System.out.format("+------+-----------------------------+------------------------+----------+\n");

            boolean found = false;
            while (rs.next()) {
                found = true;
                int id = rs.getInt("book_id");
                String t = rs.getString("title");
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
