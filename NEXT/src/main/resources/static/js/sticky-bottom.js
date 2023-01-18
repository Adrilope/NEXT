var filtros = document.getElementById("filters");
var productos = document.getElementsByClassName("productos")[0];
var filtrosBottom = filtros.offsetTop + filtros.offsetHeight;
var altoFiltos = filtros.clientHeight;
 
window.onresize = function() {resize()};
resize();



function resize() {
	if(screen.width > 767) {
		window.onscroll = function() {sticky()};
	}
	else {
		//quitamos el evento para que si pasamos de un ancho de tablet a un ancho de movil no se ejecute el evento oncroll prestablecido
		window.onscroll = "";
	}
}



function sticky() {
	var posicion = productos.getBoundingClientRect();
	var anchoFiltros = filtros.clientWidth.toString();
	
	if(posicion.bottom < filtrosBottom) {
		filtros.classList.remove("position-fixed");
		
		var top = productos.clientHeight - altoFiltos;
		filtros.style = "position: absolute!important; width:"+ anchoFiltros +"px; top:" + top.toString() + "px;";
	}	
	else {
		filtros.classList.add("position-fixed");
		filtros.style = "";
	}
}