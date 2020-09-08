//  Created By Gullian Van Der Walt

// Hide Menu Function
var miniNav = true;
var menuButton = document.getElementById("menuButton");

// Show/Hide Menu
function toggleSideNav() {
  //  Hide Menu
  if (miniNav) {
    console.log("Closing Sidenav");
    // Transition Effect
    document.getElementById("sideNav").style.transition = "0.6s";
    // Shrink side nav width
    document.getElementById("sideNav").style.width = "8%";
    // Transition Effects
    document.getElementById("navLinkDashboard").style.transition = "0.6s";
    document.getElementById("navLinkCustomers").style.transition = "0.6s";
    document.getElementById("navLinkNewApp").style.transition = "0.6s";
    document.getElementById("navLinkEmployees").style.transition = "0.6s";
    document.getElementById("navLinkFinance").style.transition = "0.6s";
    document.getElementById("navLinkJobCard").style.transition = "0.6s";
    document.getElementById("navLinkManageBusiness").style.transition = "0.6s";
    // Hide Link Text
    document.getElementById("navLinkDashboard").style.display = "none";
    document.getElementById("navLinkCustomers").style.display = "none";
    document.getElementById("navLinkNewApp").style.display = "none";
    document.getElementById("navLinkEmployees").style.display = "none";
    document.getElementById("navLinkFinance").style.display = "none";
    document.getElementById("navLinkJobCard").style.display = "none";
    document.getElementById("navLinkManageBusiness").style.display = "none";
    // Set Content Width
    document.getElementById("contentContainer").style.width = "90%";
    document.getElementById("nav-list").style.marginLeft = "0";
    document.getElementById("dashboardLink").style.textAlign = "center";
    document.getElementById("customersLink").style.textAlign = "center";
    document.getElementById("newAppLink").style.textAlign = "center";
    document.getElementById("employeesLink").style.textAlign = "center";
    document.getElementById("financeLink").style.textAlign = "center";
    document.getElementById("jobCardLink").style.textAlign = "center";
    document.getElementById("manageBusinessLink").style.textAlign = "center";
    // Center Icons

    this.miniNav = false;
  } else {
    console.log("Opening Sidenav");
    // Show full width sidenav
    document.getElementById("sideNav").style.transition = "0.6s";
    document.getElementById("sideNav").style.width = "20%";
    document.getElementById("nav-list").style.marginLeft = "25px";
    // Transition Effects
    document.getElementById("navLinkDashboard").style.transition = "0.6s";
    document.getElementById("navLinkCustomers").style.transition = "0.6s";
    document.getElementById("navLinkNewApp").style.transition = "0.6s";
    document.getElementById("navLinkEmployees").style.transition = "0.6s";
    document.getElementById("navLinkFinance").style.transition = "0.6s";
    document.getElementById("navLinkJobCard").style.transition = "0.6s";
    document.getElementById("navLinkManageBusiness").style.transition = "0.6s";
    // Display Link Text
    document.getElementById("navLinkDashboard").style.display = "inline-flex";
    document.getElementById("navLinkCustomers").style.display = "inline-flex";
    document.getElementById("navLinkNewApp").style.display = "inline-flex";
    document.getElementById("navLinkEmployees").style.display = "inline-flex";
    document.getElementById("navLinkFinance").style.display = "inline-flex";
    document.getElementById("navLinkJobCard").style.display = "inline-flex";
    document.getElementById("navLinkManageBusiness").style.display = "inline-flex";
    // Re-align links
    document.getElementById("dashboardLink").style.textAlign = "left";
    document.getElementById("customersLink").style.textAlign = "left";
    document.getElementById("newAppLink").style.textAlign = "left";
    document.getElementById("employeesLink").style.textAlign = "left";
    document.getElementById("financeLink").style.textAlign = "left";
    document.getElementById("jobCardLink").style.textAlign = "left";
    document.getElementById("manageBusinessLink").style.textAlign = "left";

    this.miniNav = true;
  }
}
