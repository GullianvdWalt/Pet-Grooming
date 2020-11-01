/*   © Pet Grooming
     © Gullian Van Der Walt
     Pearson Pretoria ITSP300 - Project 2020  */

//Delete Modal

// // Get the modal
var deleteModal = document.getElementById("employeeDeleteModal");

// Open Delte Modal
$(document).ready(function () {
  $('table #deleteButton').on('click', function (event) {
    event.preventDefault();
    deleteModal.style.display = "block";
    // Delete Button URL
    var href = $(this).attr('href');
    console.log(href);
    $('#confirmEmployeeDelete').attr('href', href);
    $('#employeeDeleteModal').modal();
  });
});

// Close Delete Model
$(document).ready(function () {
  // Close Delete Modal
  $("#employeeCancel").on('click', function (event) {
    deleteModal.style.display = "none";
  });
});

// Function to clear search
function clearSearch() {
  window.location = "[[@{/}]]";
}
