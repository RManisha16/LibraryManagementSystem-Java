import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class IssueBook {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter Book ID to issue: ");
            int bookId = sc.nextInt();

            Connection conn = DBConnection.getConnection();

            // 1. Check if book exists and quantity > 0
            String checkQuery = "SELECT quantity FROM books WHERE book_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int qty = rs.getInt("quantity");
                if (qty > 0) {
                    // 2. Update quantity
                    String updateQuery = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setInt(1, bookId);
                    int rows = updateStmt.executeUpdate();
                    if (rows > 0) {
                        System.out.println("✅ Book issued successfully!");
                    }
                } else {
                    System.out.println("❌ Book is not available (0 quantity).");
                }
            } else {
                System.out.println("❌ Book ID not found.");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
