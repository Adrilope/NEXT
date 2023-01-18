window.onresize = function() {navbar()};

function navbar() {
    if (screen.width <= 1330) {
        document.getElementById("header-uno").classList.replace("col-lg-4", "col-lg-3");
        document.getElementById("header-dos").classList.replace("col-lg-8", "col-lg-9");
    }
    else {
        document.getElementById("header-uno").classList.replace("col-lg-3", "col-lg-4");
        document.getElementById("header-dos").classList.replace("col-lg-9", "col-lg-8");
    }
}




var lupa = document.getElementById("lupa");

function openSearch() {
    document.getElementById("mySearchnav").style.height = "40px";
    document.getElementsByClassName("principal")[0].style.marginTop = "125px";
    lupa.setAttribute("onclick", "closeSearch()");
}

function closeSearch() {
    document.getElementById("mySearchnav").style.height = "0";
    document.getElementsByClassName("principal")[0].style.marginTop= "85px";
    lupa.setAttribute("onclick", "openSearch()");
}