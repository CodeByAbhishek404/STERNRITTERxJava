// Form Validation
function validateForm(formId) {
    const form = document.getElementById(formId);
    let isValid = true;

    form.querySelectorAll("input").forEach((input) => {
        if (input.required && input.value.trim() === "") {
            input.classList.add("is-invalid");
            isValid = false;
        } else {
            input.classList.remove("is-invalid");
        }
    });

    return isValid;
}

// Clear Form
function clearForm(formId) {
    const form = document.getElementById(formId);
    form.reset();
}

// Toast Notification
function showToast(message, type = "success") {
    const toast = document.createElement("div");
    toast.className = `toast toast-${type}`;
    toast.innerText = message;

    document.body.appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 3000);
}
