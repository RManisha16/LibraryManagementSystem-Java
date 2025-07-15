import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ReturnBook {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter Book ID to return: ");
            int bookId = sc.nextInt();

            Connection conn = DBConnection.getConnection();
            String query = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, bookId);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Book returned successfully!");
            } else {
                System.out.println("❌ Book ID not found.");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
