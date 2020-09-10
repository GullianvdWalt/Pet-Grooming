// Created by Gullian Van Der Walt - 07-09-2020
// Last Updated - 10-09-2020

// $("#selectPet").change(function () {
//   try {
//     var petId = $(this).find(":selected").val();
//     console.log(petId);
//     var param = { petID: petId };
//     console.log(param);
//     $.ajax({
//       type: 'POST',
//       url: '/customers/update/pet',
//       data: param,
//       success: function (result) {
//         console.log(success);

//       },
//       error: function (e) {
//         alert(e);
//       }

//       // var d_Name = jSON_res.replace(/\"/g, ""); // Remove quotes
//       // var text_val = "<input type='text' id ='new_name' name ='new_name' " +
//       //   "value=' " + d_Name + " '}/>";
//       // $('#textBox').empty().append(text_val);

//     });

//   } catch (err) {
//     alert("Exception ::-- " + err)
//   }
// });

// Get Update Model
var petEditModal = document.getElementById("petEditModal");
// Get button that opens the modal
var openPetEdit = document.getElementById("editPet");
// Get Delete Modals
var petDeleteModal = document.getElementById("petDeleteModal");
// Get Delete Modals
var petOwnerDeleteModal = document.getElementById("petOwnerDeleteModal");
// Get Pet Owner Id
var petOwnerId = document.getElementById("pet_owner_id");
// Get edit pet modal close button
var closeEditPet = document.getElementById("closePetEdit");

$(document).ready(function () {
  // Edit Modal
  $('table #editPet').on('click', function (event) {
    // Prevent submission
    event.preventDefault();
    // Display Modal
    petEditModal.style.display = "block";
    window.location.hash = '#petEditModal';

    // Current Button URL
    var href = $(this).attr('href');
    // Get Values from inputs
    $.get(href, function (pet, status) {
      $('#petId').val(pet.id);
      $('#petOwnerId').val(pet.pet_owner_id);
      $('#petName').val(pet.pet_name);
      $('#petBreed').val(pet.pet_breed);
      if ($('#petGender').length < 2) {
        $('#petGender').val(pet.pet_gender);
      } else {
        alert("Pet Gender must be M or F");
      }
      $('#petSize').val(pet.pet_size);
      $('#petNotes').val(pet.pet_notes);
    });
    $('#petEditModal').modal();

  });

});

$(document).ready(function () {
  // Close Edit Modal
  $('#closePetEdit').on('click', function (event) {
    petEditModal.style.display = "none";
  });
});
// Delete Pet Modal
$(document).ready(function () {
  $('table #deletePet').on('click', function (event) {
    event.preventDefault();
    petDeleteModal.style.display = "block";
    // Delete Button URL
    var href = $(this).attr('href');
    console.log(href);
    $('#confirmPetDelete').attr('href', href);
    $('#petDeleteModal').modal();
  });
});
// Delete Pet Owner Modal
$(document).ready(function () {
  $('#deletePetOwner').on('click', function (event) {
    event.preventDefault();
    petOwnerDeleteModal.style.display = "block";
    // Delete Button URL
    var href = $(this).attr('href');
    console.log(href);
    $('#confirmPetOwnerDelete').attr('href', href);
    $('#petOwnerDeleteModal').modal();
  });
});
// Close Pet Delete Model
$(document).ready(function () {
  // Close Delete Modal
  $("#petCancel").on('click', function (event) {
    petDeleteModal.style.display = "none";
  });
});

// Close Pet Owner Delete Model
$(document).ready(function () {
  // Close Delete Modal
  $("#petOwnerCancel").on('click', function (event) {
    petOwnerDeleteModal.style.display = "none";
  });

});
