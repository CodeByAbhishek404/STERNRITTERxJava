// Navigation
document.querySelectorAll(".nav-item").forEach((item) => {
    item.addEventListener("click", () => {
        document.querySelectorAll(".nav-item").forEach((i) => i.classList.remove("active"));
        item.classList.add("active");
    });
});

// Fetch Summary Data
function fetchSummary() {
    // Replace with your backend API logic
    const summary = {
        budget: 5000,
        expenses: 3000,
        remaining: 2000,
    };

    document.getElementById("budgetAmount").innerText = `₹${summary.budget}`;
    document.getElementById("expensesAmount").innerText = `₹${summary.expenses}`;
    document.getElementById("remainingAmount").innerText = `₹${summary.remaining}`;
}

document.addEventListener("DOMContentLoaded", fetchSummary);
