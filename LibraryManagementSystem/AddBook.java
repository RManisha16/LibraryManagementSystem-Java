import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class AddBook {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Book Author: ");
            String author = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            Connection conn = DBConnection.getConnection();  // using the connection class

            String query = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, title);
            pst.setString(2, author);
            pst.setInt(3, quantity);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Book added successfully!");
            } else {
                System.out.println("Failed to add book.");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
