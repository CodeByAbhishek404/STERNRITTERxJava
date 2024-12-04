// Login Form Submission
document.getElementById("loginForm").addEventListener("submit", (e) => {
    e.preventDefault();

    if (validateForm("loginForm")) {
        const username = document.getElementById("loginUsername").value;
        const password = document.getElementById("loginPassword").value;

        // Replace with your backend API logic
        console.log(`Logging in with username: ${username}, password: ${password}`);
        showToast("Logged in successfully!");
    }
});

// Signup Form Submission
document.getElementById("signupForm").addEventListener("submit", (e) => {
    e.preventDefault();

    if (validateForm("signupForm")) {
        const username = document.getElementById("signupUsername").value;
        const password = document.getElementById("signupPassword").value;
        const email = document.getElementById("signupEmail").value;

        // Replace with your backend API logic
        console.log(`Signing up with username: ${username}, email: ${email}`);
        showToast("Signup successful!");
        clearForm("signupForm");
    }
});
