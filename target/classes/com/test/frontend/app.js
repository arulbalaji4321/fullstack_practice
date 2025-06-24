// Wait until the DOM (HTML page) is fully loaded
document.addEventListener("DOMContentLoaded", function () {
  // Grab form elements and buttons
  const form = document.getElementById("purchaseForm");
  const resultDiv = document.getElementById("result");
  const totalBtn = document.getElementById("totalBtn");

  // 🟩 Handle form submission (Add new Purchase)
  form.addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent page refresh

    // Get product and price values from form
    const product = document.getElementById("product").value;
    const price = parseFloat(document.getElementById("price").value);

    const data = { product: product, price: price };

    // Send POST request to /purchase endpoint
    fetch("http://localhost:7070/purchase", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    })
      .then(function (response) {
        return response.text(); // Expecting plain text response
      })
      .then(function (data) {
        resultDiv.innerText = data; // Show server response
        form.reset(); // Clear form inputs
      })
      .catch(function (error) {
        resultDiv.innerText = "Error: " + error; // Show error if any
      });
  });

  // 🟦 Handle "Show Total Profit" button click (GET from /total)
  totalBtn.addEventListener("click", function () {
    fetch("http://localhost:7070/total")
      .then((response) => response.json()) // Expect JSON response
      .then((data) => {
        // Format and display returned data
        const output = `
Name: ${data.customerName}
Age: ${data.customerAge}
Region: ${data.customerRegion}
Total Profit: ₹${data.totalProfit}
        `;
        resultDiv.innerText = output;
      })
      .catch((error) => {
        resultDiv.innerText = "Error: " + error; // Display fetch error
      });
  });
});
