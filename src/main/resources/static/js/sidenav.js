// Hide Menu Function
var mini = false;
var menuButton = document.getElementById("menuButton");
function toggleSideNav() {
  if (mini) {
    console.log("Closing Sidenav");
    document.getElementsByTagName("nav").style.width = "20%";
    document.getElementsByClassName("content-container").style.marginLeft = "85px";
    this.mini = false;
  } else {
    console.log("Opening Sidenav");
    document.getElementsByTagName("nav").style.width = "85px";
    document.getElementsByClassName("content-container").style.marginLeft = "250px";
    this.mini = true;
  }
}
