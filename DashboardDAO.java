package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO {

    public ResultSet getTransactionSummary(int userId, String month, int year) {
        String query = "SELECT c.category_name, SUM(t.amount) AS total " +
                       "FROM Transactions t JOIN Categories c ON t.category_id = c.category_id " +
                       "WHERE t.user_id = ? AND MONTH(t.transaction_date) = ? AND YEAR(t.transaction_date) = ? " +
                       "GROUP BY c.category_name";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            pstmt.setString(2, month);
            pstmt.setInt(3, year);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getTotalExpenses(int userId, String month, int year) {
        String query = "SELECT SUM(amount) AS total_expenses " +
                       "FROM Transactions WHERE user_id = ? AND MONTH(transaction_date) = ? AND YEAR(transaction_date) = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, month);
            pstmt.setInt(3, year);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_expenses");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
