package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetDAO {

    public boolean setBudget(int userId, String month, int year, double amount) {
        String query = "INSERT INTO Budgets (user_id, month, year, budget_amount) VALUES (?, ?, ?, ?) " +
                       "ON DUPLICATE KEY UPDATE budget_amount = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, month);
            pstmt.setInt(3, year);
            pstmt.setDouble(4, amount);
            pstmt.setDouble(5, amount);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getBudget(int userId, String month, int year) {
        String query = "SELECT budget_amount FROM Budgets WHERE user_id = ? AND month = ? AND year = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, month);
            pstmt.setInt(3, year);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("budget_amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
