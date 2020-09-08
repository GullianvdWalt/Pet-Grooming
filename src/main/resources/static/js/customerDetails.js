// Created by Gullian Van Der Walt - 07-09-2020
// Last Updated - 08-09-2020
$(document).ready(function () {
  $("#selectPet").change(function () {
    try {
      var petId = $(this).val();
      console.log(petId);
      var params = { ID: petId };
      $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: '/loadPetDetails',
        data: JSON.stringify(params),
        success: function (result) {
          var petName = '';
          var length = result.length;
          if (result != '') {

            for (var i = 0; i < length; i++) {
              console.log(result[i].id);
              console.log(result[i].name);
            }

            // var jSON_res = JSON.stringify(responseJson);
            // var d_Name = jSON_res.replace(/\"/g, ""); // Remove quotes
            // var text_val = "<input type='text' id ='new_name' name ='new_name' " +
            //   "value=' " + d_Name + " '}/>";
            // $('#textBox').empty().append(text_val);

          }

        },
        error: function (e) {
          alert(e);
        }


      });
    } catch (err) {
      alert("Exception ::-- " + err)
    }
  });

});


