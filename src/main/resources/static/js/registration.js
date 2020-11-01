/*   © Pet Grooming
     © Gullian Van Der Walt
     Pearson Pretoria ITSP300 - Project 2020  */
$(document).ready(function () {

  // Confirm that passwords match
  var password = document.getElementById("password");
  var confirmPassword = document.getElementById("confirmPassword");

  function validiatePassword() {
    if (password.value != confirmPassword.value) {
      confirmPassword.setCustomValidity("Passwords doesn't match!")
    } else {
      confirmPassword.setCustomValidity('');
    }
  }
  // Check if user changes password
  password.onchange = validiatePassword;
  confirmPassword.onchange = validiatePassword;
});

