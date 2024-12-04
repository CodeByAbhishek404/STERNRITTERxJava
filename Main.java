import dao.UserDAO;
import dao.TransactionDAO;
import dao.BudgetDAO;
import dao.DashboardDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        TransactionDAO transactionDAO = new TransactionDAO();
        BudgetDAO budgetDAO = new BudgetDAO();
        DashboardDAO dashboardDAO = new DashboardDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Personal Finance Management System!");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            if (userDAO.createUser(username, password, email)) {
                System.out.println("Sign up successful! Please log in.");
            } else {
                System.out.println("Error during sign up.");
            }
        } else if (choice == 2) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (userDAO.authenticateUser(username, password)) {
                System.out.println("Login successful!");

                while (true) {
                    System.out.println("1. Add Transaction");
                    System.out.println("2. View Transactions");
                    System.out.println("3. Set Budget");
                    System.out.println("4. View Dashboard");
                    System.out.println("5. Exit");
                    int action = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (action == 1) {
                        System.out.print("Enter category ID (1-Income, 2-Expense): ");
                        int categoryId = scanner.nextInt();
                        System.out.print("Enter amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter transaction date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        System.out.print("Enter description: ");
                        String description = scanner.nextLine();

                        if (transactionDAO.addTransaction(1, categoryId, amount, date, description)) {
                            System.out.println("Transaction added successfully!");
                        } else {
                            System.out.println("Error adding transaction.");
                        }
                    } else if (action == 2) {
                        ResultSet rs = transactionDAO.getTransactions(1);
                        try {
                            while (rs.next()) {
                                System.out.println(rs.getString("category_name") + ": " +
                                        rs.getDouble("amount") + " on " +
                                        rs.getDate("transaction_date") + " - " +
                                        rs.getString("description"));
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else if (action == 3) {
                        System.out.print("Enter month (e.g., January): ");
                        String month = scanner.nextLine();
                        System.out.print("Enter year: ");
                        int year = scanner.nextInt();
                        System.out.print("Enter budget amount: ");
                        double budgetAmount = scanner.nextDouble();

                        if (budgetDAO.setBudget(1, month, year, budgetAmount)) {
                            System.out.println("Budget set successfully!");
                        } else {
                            System.out.println("Error setting budget.");
                        }
                    } else if (action == 4) {
                        System.out.print("Enter month (e.g., January): ");
                        String month = scanner.nextLine();
                        System.out.print("Enter year: ");
                        int year = scanner.nextInt();

                        double budget = budgetDAO.getBudget(1, month, year);
                        double expenses = dashboardDAO.getTotalExpenses(1, month, year);

                        System.out.println("Dashboard for " + month + " " + year);
                        System.out.println("Budget: ₹" + budget);
                        System.out.println("Total Expenses: ₹" + expenses);
                        System.out.println("Remaining Budget: ₹" + (budget - expenses));

                        ResultSet summary = dashboardDAO.getTransactionSummary(1, month, year);
                        System.out.println("Category-wise Summary:");
                        try {
                            while (summary.next()) {
                                System.out.println(summary.getString("category_name") + ": ₹" +
                                        summary.getDouble("total"));
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else if (action == 5) {
                        break;
                    }
                }
            } else {
                System.out.println("Invalid username or password.");
            }
        }
    }
}
