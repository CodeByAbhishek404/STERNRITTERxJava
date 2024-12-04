Wealth Smith - Personal Finance Management System
Welcome to Wealth Smith, a comprehensive personal finance management system designed to help you effectively manage, monitor, and plan your finances. This system offers a user-friendly interface and a rich set of features to simplify tracking expenses, managing budgets, generating reports, and achieving financial goals.
# Personal Finance Management System

This is a Java-based project for managing personal finances, including tracking income, expenses, setting budgets, and viewing financial dashboards.

## Project Overview

- **Technologies Used**:
  - **Java**: Backend programming language.
  - **MySQL**: Database for persistent storage.
  - **Bootstrap**: For frontend styling (if applicable).
- **Features**:
  - User authentication (sign up and log in).
  - Add and view income/expense transactions.
  - Set monthly budgets.
  - Dashboard for financial insights.

---

## IDE and JDK Setup

### **1. Integrated Development Environment (IDE)**

- **Recommended IDE**: IntelliJ IDEA Community Edition or Eclipse IDE.
- **Steps to Install IntelliJ IDEA**:
  1. Download IntelliJ IDEA Community Edition from [JetBrains](https://www.jetbrains.com/idea/).
  2. Install the IDE on your machine by following the setup wizard.
  3. Open the project folder in IntelliJ IDEA.
  4. Configure the JDK (as shown below).

---

### **2. Java Development Kit (JDK)**

- **JDK Version**: JDK 17 or later is recommended.
- **Steps to Install JDK**:
  1. Download the JDK from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/).
  2. Install the JDK by following the setup wizard.
  3. Configure the `JAVA_HOME` environment variable:
     - **Windows**:
       - Go to **System Properties** > **Environment Variables**.
       - Add a new variable:
         - Name: `JAVA_HOME`
         - Value: Path to your JDK installation (e.g., `C:\Program Files\Java\jdk-17`).
       - Update the `Path` variable to include `%JAVA_HOME%\bin`.
     - **Mac/Linux**:
       - Add the following to your `~/.bashrc` or `~/.zshrc` file:
         ```bash
         export JAVA_HOME=/path/to/jdk
         export PATH=$JAVA_HOME/bin:$PATH
         ```
  4. Verify the installation:
     - Open a terminal and type:
       ```bash
       java -version
       ```
       You should see the installed JDK version.

---

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/PersonalFinanceManagement.git
