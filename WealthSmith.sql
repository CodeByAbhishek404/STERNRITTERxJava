-- Create Database
CREATE DATABASE WealthSmith;

-- Use the newly created database
USE WealthSmith;

-- Users Table: Stores user information
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PasswordHash VARCHAR(255) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Transactions Table: Stores income and expense records
CREATE TABLE Transactions (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    Type ENUM('Income', 'Expense') NOT NULL,
    Category VARCHAR(50) NOT NULL,
    Description TEXT,
    TransactionDate DATE NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

-- Budgets Table: Stores user budgets for various categories
CREATE TABLE Budgets (
    BudgetID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Category VARCHAR(50) NOT NULL,
    BudgetAmount DECIMAL(10, 2) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

-- Reports Table: Stores summary reports for users
CREATE TABLE Reports (
    ReportID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    ReportType ENUM('Monthly', 'Quarterly', 'Yearly') NOT NULL,
    Period VARCHAR(20) NOT NULL,
    TotalIncome DECIMAL(10, 2) NOT NULL,
    TotalExpense DECIMAL(10, 2) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

-- UserLogs Table: Tracks user logins and actions
CREATE TABLE UserLogs (
    LogID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Action VARCHAR(100) NOT NULL,
    ActionTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

-- Insert sample data into Users
INSERT INTO Users (FullName, Email, PasswordHash) VALUES
('John Doe', 'john.doe@example.com', 'hashedpassword1'),
('Jane Smith', 'jane.smith@example.com', 'hashedpassword2');

-- Insert sample data into Transactions
INSERT INTO Transactions (UserID, Amount, Type, Category, Description, TransactionDate) VALUES
(1, 1000.00, 'Income', 'Salary', 'Monthly salary', '2024-11-01'),
(1, 200.00, 'Expense', 'Food', 'Groceries', '2024-11-02'),
(2, 1500.00, 'Income', 'Freelancing', 'Freelance project', '2024-11-03');

-- Insert sample data into Budgets
INSERT INTO Budgets (UserID, Category, BudgetAmount) VALUES
(1, 'Food', 500.00),
(2, 'Travel', 1000.00);

-- Insert sample data into Reports
INSERT INTO Reports (UserID, ReportType, Period, TotalIncome, TotalExpense) VALUES
(1, 'Monthly', 'November 2024', 1000.00, 200.00),
(2, 'Monthly', 'November 2024', 1500.00, 0.00);

-- Insert sample data into UserLogs
INSERT INTO UserLogs (UserID, Action) VALUES
(1, 'Logged In'),
(2, 'Logged In');
