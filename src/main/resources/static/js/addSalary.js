// Created By Gullian Van Der Walt

// Function To Get Total Days
function getTotalDays(startDate, endDate) {
    const date1 = new Date(Date.parse(startDate));
    const date2 = new Date(Date.parse(endDate));

    // Convert To Date
    let diff = 0;
    if (date1 && date2) {
        // Milisecond per day = = 1000 * 60 * 60 * 24 = 86400000
        diff = Math.floor((date2.getTime() - date1.getTime()) / 86400000);
    }
    return diff;
}

// Set Total Days for Inputs
function setTotalDays() {
    // Get Inputs for total dayss
    var startDate = document.getElementById("salDateStart").value;
    var endDate = document.getElementById("salDateEnd").value;
    // Get Total Days
    var diff = getTotalDays(startDate, endDate);

    document.getElementById("numDays").value = diff;

    return diff;
}


// ############## Overtime ################

// Get Button
var calcOverrTimeBtn = document.getElementById("calcOvertimeBtn");


calcOverrTimeBtn.addEventListener("click", function (event) {

    // Prevent Form Submission
    event.preventDefault();

    var overtimeHours = document.getElementById("overtimeHours").value;
    var overtimeRate = document.getElementById("overtimeRate").value;

    // Validate
    if (overtimeHours <= 0) {
        alert("Please enter total overtime hours");
        document.getElementById("overtimeHours").style.backgroundColor = "red";
    } else if (overtimeRate <= 0) {
        alert("Please enter overtime rate");
        document.getElementById("overtimeRate").style.backgroundColor = "red";
    } else {
        // Calculate Overtime Total
        var overtimeTotal = overtimeHours * overtimeRate;
        // Set Overtime Total Input
        document.getElementById("overtimeTotal").value = overtimeTotal;
    }

});


// ############## Transport #################

// Get Button
var calcTransportBtn = document.getElementById("calcTransportBtn");
calcTransportBtn.addEventListener("click", function (event) {

    // Prevent Form Submission
    event.preventDefault();

    // Get Inputs
    var amountPerDay = document.getElementById("amountPerDay").value;
    var numDays = document.getElementById("numDays").value;
    // Validate
    if (amountPerDay <= 0) {
        alert("Please enter transport amount per day");
        document.getElementById("amountPerDay").style.backgroundColor = "red";
    } else if (numDays <= 0) {
        alert("Please enter number of days");
        document.getElementById("numDays").style.backgroundColor = "red"
    } else {
        // Calculate Overtime Total
        var transPortTotal = amountPerDay * numDays;
        // Set Overtime Total Input
        document.getElementById("transportTotal").value = transPortTotal;
    }
});

// ############# Bonus ################

// Get Button
var calcBonusBtn = document.getElementById("calcBonusBtn");

calcBonusBtn.addEventListener("click", function (event) {

    // Prevent Form Submission
    event.preventDefault();

    // Get Input Values

    // Number of dogs
    var numLDog = document.getElementById("numLDog").value;
    var numMDog = document.getElementById("numMDog").value;
    var numSDog = document.getElementById("numSDog").value;
    // Dog Rates
    var rLDog = document.getElementById("lDogRate").value;
    var rMDog = document.getElementById("mDogRate").value;
    var rSDog = document.getElementById("sDogRate").value;
    // Set Bonus ammounts
    document.getElementById("amountSDog").value = (numSDog * rSDog);
    document.getElementById("amountMDog").value = (numMDog * rMDog);
    document.getElementById("amountLDog").value = (numLDog * rLDog);
    // Calculate Bonus Total
    var bonusTotal = (numLDog * rLDog) + (numMDog * rMDog) + (numSDog * rSDog);
    // Set Input
    document.getElementById("bonusTotal").value = bonusTotal;
});

// ########### Total ############

// Get Button
var calcSalaryTotals = document.getElementById("calcSalaryTotals");

calcSalaryTotals.addEventListener("click", function (event) {

    // Prevent Form Submission
    event.preventDefault();
    // Get Inputs
    var totalWorkDays = document.getElementById("numDays").value;
    var wage = document.getElementById("basicWage").value;
    var overtimeTotal = document.getElementById("overtimeTotal").value;
    var deductionTotal = document.getElementById("deductionTotal").value;
    var bonusTotal = document.getElementById("bonusTotal").value;

    var subtotal = (totalWorkDays * wage) + Number(overtimeTotal);
    //var subtotalWOvertime = subtotal + overtimeTotal;
    var totalWBonus = Number(subtotal) + Number(bonusTotal);
    var totalAftrDeductions = Number(totalWBonus) - Number(deductionTotal);
    var grandTotal = totalAftrDeductions;

    // Set Total
    document.getElementById("subtotal").value = subtotal;
    document.getElementById("totalInclBonus").value = totalWBonus;
    document.getElementById("totalAfDeductions").value = totalAftrDeductions;
    document.getElementById("grandTotal").value = grandTotal;
});

// Clear Button

// Get Button
var clearBtn = document.getElementById("totalClearBtn");

clearBtn.addEventListener("click", function (event) {

    // Prevent Form Submission
    event.preventDefault();
    // Set Total
    document.getElementById("subtotal").value = null;
    document.getElementById("totalInclBonus").value = null;
    document.getElementById("totalAfDeductions").value = null;
    document.getElementById("grandTotal").value = null;
});




