/*   © Pet Grooming
     © Gullian Van Der Walt
     Pearson Pretoria ITSP300 - Project 2020  */


// Delete Pet Modal

var deleteAppModal = document.getElementById("appDeleteModal");

$(document).ready(function () {
  $('table #deleteAppointment').on('click', function (event) {
    event.preventDefault();
    deleteAppModal.style.display = "block";
    // Delete Button URL
    var href = $(this).attr('href');
    console.log(href);
    $('#confirmAppDelete').attr('href', href);
    $('#deleteAppModal').modal();
  });
});
// Close Delete Modal
$(document).ready(function () {
  $('#appCancel').on('click', function (event) {
    deleteAppModal.style.display = "none";
  });
});

