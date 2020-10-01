// Created By Gullian Van Der Walt

//Delete Modal

// // Get the modal
var deleteModal = document.getElementById("employeeDeleteModal");

// // Get the button that opens the modal
// var openDeleteModal = document.getElementById("delete-button");

// //Get the close modal button
// var closeDeleteModal = document.getElementById("employeeCancel");

// $(document).ready(function () {
//   // When the user clicks the button, open the modal
//   openDeleteModal.onclick = function () {
//     deleteModal.style.display = "block";
//   }
// });

// $(document).ready(function () {
//   closeDeleteModal.onclick = function () {
//     deleteModal.style.display = "none";
//   }
// });


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
