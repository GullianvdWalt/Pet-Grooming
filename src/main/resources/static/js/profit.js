/*   © Pet Grooming
     © Gullian Van Der Walt
     Pearson Pretoria ITSP300 - Project 2020  */

// Select Date

// // Get the modal
var selectDate = document.getElementById("selectDateModal");

// Open Delte Modal
$(document).ready(function () {
  $('#selectDate').on('click', function (event) {
    event.preventDefault();
    selectDate.style.display = "block";
  });
});

// Close Delete Model
$(document).ready(function () {
  // Close Delete Modal
  $("#selectDateCancel").on('click', function (event) {
    selectDate.style.display = "none";
  });
});
