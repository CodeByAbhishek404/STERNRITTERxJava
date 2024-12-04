// Add Transaction
document.getElementById("transactionForm").addEventListener("submit", (e) => {
    e.preventDefault();

    if (validateForm("transactionForm")) {
        const category = document.getElementById("transactionCategory").value;
        const amount = document.getElementById("transactionAmount").value;
        const date = document.getElementById("transactionDate").value;
        const description = document.getElementById("transactionDescription").value;

        // Replace with your backend API logic
        console.log(`Adding transaction: ${category}, ₹${amount}, ${date}, ${description}`);
        showToast("Transaction added successfully!");
        clearForm("transactionForm");
    }
});

// Filter Transactions
document.getElementById("filterForm").addEventListener("submit", (e) => {
    e.preventDefault();

    const filterMonth = document.getElementById("filterMonth").value;
    const filterYear = document.getElementById("filterYear").value;

    // Replace with your backend API logic
    console.log(`Filtering transactions for: ${filterMonth}/${filterYear}`);
    showToast("Transactions filtered!");
});
