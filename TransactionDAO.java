package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    public boolean addTransaction(int userId, int categoryId, double amount, String date, String description) {
        String query = "INSERT INTO Transactions (user_id, category_id, amount, transaction_date, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, categoryId);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, date);
            pstmt.setString(5, description);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getTransactions(int userId) {
        String query = "SELECT t.transaction_id, t.amount, t.transaction_date, t.description, c.category_name " +
                       "FROM Transactions t JOIN Categories c ON t.category_id = c.category_id WHERE t.user_id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
