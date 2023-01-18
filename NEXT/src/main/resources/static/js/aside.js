var menu = document.getElementById("menu");
var transparencia = document.getElementById("transparencia");
function openNav() {
    if (screen.width < 650) {
        menu.style.width= "55%";
    }
    else {
        menu.style.width= "40%";
    }
    
    menu.style.display = "flex";
    transparencia.style.width= "100%";
}

function closeNav() {
    menu.style.width = "0%";
    menu.style.display = "none";
    transparencia.style.width = "0%";
}