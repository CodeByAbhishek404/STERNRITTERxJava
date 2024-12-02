import java.sql.*;
import java.util.Scanner;

public class WealthSmithDB {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/WealthSmith";
    private static final String DB_USER = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "yourpassword"; // Replace with your MySQL password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the Wealth Smith database successfully!");

            Scanner scanner = new Scanner(System.in);

            // Menu-driven program
            while (true) {
                System.out.println("\n=== Wealth Smith Menu ===");
                System.out.println("1. Add New User");
                System.out.println("2. View Transactions for a User");
                System.out.println("3. Log User Action");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (choice) {
                    case 1 -> addNewUser(connection, scanner);
                    case 2 -> viewTransactions(connection, scanner);
                    case 3 -> logUserAction(connection, scanner);
                    case 4 -> {
                        System.out.println("Exiting the application. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    // Add a new user
    private static void addNewUser(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String query = "INSERT INTO Users (FullName, Email, PasswordHash) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, fullName);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, hashPassword(password));

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User added successfully.");
                } else {
                    System.out.println("Failed to add user.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    // View transactions for a user
    private static void viewTransactions(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter user ID: ");
            int userId = scanner.nextInt();

            String query = "SELECT * FROM Transactions WHERE UserID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("\n=== Transactions ===");
                    while (resultSet.next()) {
                        System.out.printf("TransactionID: %d, Amount: %.2f, Type: %s, Category: %s, Description: %s, Date: %s%n",
                                resultSet.getInt("TransactionID"),
                                resultSet.getDouble("Amount"),
                                resultSet.getString("Type"),
                                resultSet.getString("Category"),
                                resultSet.getString("Description"),
                                resultSet.getDate("TransactionDate"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving transactions: " + e.getMessage());
        }
    }

    // Log user actions
    private static void logUserAction(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter user ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            System.out.print("Enter action description: ");
            String action = scanner.nextLine();

            String query = "INSERT INTO UserLogs (UserID, Action) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, action);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User action logged successfully.");
                } else {
                    System.out.println("Failed to log user action.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error logging user action: " + e.getMessage());
        }
    }

    // Simulate password hashing
    private static String hashPassword(String password) {
        return Integer.toHexString(password.hashCode()); // Replace with a proper hashing algorithm in production
    }
}
