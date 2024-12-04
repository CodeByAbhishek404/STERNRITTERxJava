// Generate Report
document.getElementById("reportForm").addEventListener("submit", (e) => {
    e.preventDefault();

    const month = document.getElementById("reportMonth").value;
    const year = document.getElementById("reportYear").value;

    // Replace with your backend API logic
    console.log(`Generating report for: ${month}/${year}`);
    updateChart();
    showToast("Report generated!");
});

// Update Chart
function updateChart() {
    // Mock Data
    const categories = ["Rent", "Groceries", "Utilities"];
    const values = [1200, 800, 500];

    const ctx = document.getElementById("reportChart").getContext("2d");
    new Chart(ctx, {
        type: "pie",
        data: {
            labels: categories,
            datasets: [
                {
                    data: values,
                    backgroundColor: ["#ffd700", "#f76c6c", "#5fba7d"],
                },
            ],
        },
        options: {
            responsive: true,
        },
    });
}
