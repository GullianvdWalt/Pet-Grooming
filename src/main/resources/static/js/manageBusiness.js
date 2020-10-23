// Created by Gullian Van Der Walt - 05-10-2020

// Get Business Details Edit Model
var businessDetailsEditModal = document.getElementById("businessDetailsEditModal");
// Get button that opens the modal
var openBusiness = document.getElementById("businessEditBtn");
// Get Business Details Edit Modal Cancel Button
var cancelBusiness = document.getElementById("businessCancel");

// Get Service Add Modal
var serviceDeleteModal = document.getElementById("serviceDeleteModal");

// Get Salary Details Edit Modal
var salaryEditModal = document.getElementById("salaryEditModal");
// Get button that opens the modal
var openSalaryEdit = document.getElementById("salaryEditBtn");
// Get Salary Details Edit Modal Cancel Button
var cancelSalaryEdit = document.getElementById("salaryEditCancel");


// Business Details Edit Modal
$(document).ready(function () {

  $('#businessEditBtn').on('click', function (event) {
    // Prevent submission
    event.preventDefault();
    // Display the modal
    businessDetailsEditModal.style.display = "block";
    window.location.hash = '#businessDetailsEditModal';

    // Get URL
    var href = $(this).attr('href');
    $.get(href, function (business_details, status) {
      // Get Values
      $('#business_details_id').val(business_details.id);
      $('#business_name').val(business_details.business_name);
      $('#business_cell').val(business_details.business_cell);
      $('#business_email').val(business_details.business_email);
      $('#business_address').val(business_details.business_address);
      $('#appointment_length').val(business_details.appointment_length);
      $('#total_work_hours').val(business_details.total_work_hours);
      $('#work_hours_start').val(business_details.work_hours_start);
      $('#work_hours_end').val(business_details.work_hours_end);
      $('#logoInput').val(business_details.ImagePath);
    });
    businessDetailsEditModal.modal();

  });
});
// Close Edit Modal
$('#businessCancel').on('click', function (event) {
  event.preventDefault();
  businessDetailsEditModal.style.display = "none";
});

// Service Delete Modal
$(document).ready(function () {
  $('.delete-button').on('click', function (event) {
    event.preventDefault();
    serviceDeleteModal.style.display = "block";
    // Delete Button URL
    var href = $(this).attr('href');
    $('#confirmServiceDelete').attr('href', href);
    $('#serviceDeleteModal').modal();
  });
});

// Close Delete Model
$(document).ready(function () {
  // Close Delete Modal
  $(".service-cancel").on('click', function (event) {
    deleteModal.style.display = "none";
  });
});



// Salary Details Edit Modal
$(document).ready(function () {
  $('#salaryEditBtn').on('click', function (event) {
    // Prevent submission
    event.preventDefault();
    // Display the modal
    salaryEditModal.style.display = "block";
    window.location.hash = '#salaryEditModal';

    // Get URL
    var href = $(this).attr('href');
    $.get(href, function (salary_details, status) {
      // Get Values
      $('#salaryDetailsId').val(salary_details.id);
      $('#wage').val(salary_details.wage);
      $('#bonusLarge').val(salary_details.bonus_large);
      $('#bonusMedium').val(salary_details.bonus_medium);
      $('#bonusSmall').val(salary_details.bonus_small);
      $('#overtime').val(salary_details.overtime);
    });
    salaryEditModal.modal();
  });
});
// Close Edit Modal
$('#salaryEditCancel').on('click', function (event) {
  event.preventDefault();
  salaryEditModal.style.display = "none";
});

