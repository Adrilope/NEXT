function atras() {
    window.history.back();
}

//comprobar si hay scrollbar para fijar el pie abajo
window.onload = () => {
	if(window.innerWidth > document.documentElement.clientWidth) {
		var footer = document.getElementsByClassName("pie")[0];
		footer.style.position = "fixed";
		footer.style.bottom = "0";
	}
}


//cambia el action del formulario de busqueda dependiendo de lo que se ponga en el input
function actionBusqueda() {
	var form = document.getElementsByClassName("busqueda");
	var input = document.getElementsByClassName("inputBusqueda");
	

	if(screen.width < 767) {
		var valor = "/productos/busqueda/" + input[0].value;
		form[0].setAttribute("action", valor);
	} else {
		var valor = "/productos/busqueda/" + input[1].value;
		form[1].setAttribute("action", valor);
	}
}




var menuFiltros = document.getElementsByClassName("filtros")[0];
var transparencia2 = document.getElementById("transparencia-dos");

function openFiltros() {
    menuFiltros.style.width = "100%";
    menuFiltros.style.display = "block";
    transparencia2.style.width= "100%";
}

function closeFiltros() {
    menuFiltros.style.width = "0";
    menuFiltros.style.display = "none";
    transparencia2.style.width = "0%";
}




var toTop = document.getElementById("goToTop");
  
if(toTop != null) {
	toTop.addEventListener("click", function(){
		scrollToTop(200);
	});
	window.onload = function () {displayScroll()};
	window.onscroll = function() {displayScroll()};
}


function scrollToTop(scrollDuration) {
    var scrollStep = -window.scrollY / (scrollDuration / 15),
        scrollInterval = setInterval(function(){
        if ( window.scrollY != 0 ) {
            window.scrollBy( 0, scrollStep );
        }
        else clearInterval(scrollInterval); 
    },15);
}



function displayScroll() {
  if (document.body.scrollTop > 150 || document.documentElement.scrollTop > 150) {
    toTop.style.display = "block";
  } else {
    toTop.style.display = "none";
  }
}




var inputs = document.getElementsByTagName("input")

for (var x=0; x<inputs.length; x++) {
    inputs[x].addEventListener("click" , function() {
        // alert(this.value);
    });
}






var escribir = document.getElementsByClassName("escribir")[0];
var opinion = document.getElementsByClassName("opinion")[0];

function openResena(x) {
    x.setAttribute("onclick", "closeResena(this)");
    if(screen.width > 417) {
        opinion.style.height = "260px";
    }
    else if(screen.width > 368) {
        opinion.style.height = "290px";
    }
    else {
        opinion.style.height = "310px";
    }
}


function closeResena(x) {
    x.setAttribute("onclick", "openResena(this)");
    opinion.style.height = "0";
}





//FUNCIONES Y VARIABLES PARA DATATABLE
var tipos = document.getElementsByClassName("tipoProducto");
var subForms = document.getElementsByClassName("formSubClass");
var form;
var tipoActual;

for (var y=0; y<tipos.length; y++) {
    tipos[y].setAttribute("onclick", 'openForm("' + tipos[y].value + '")'); 
}

function openForm(tipo){
    form = document.getElementById(tipo);
    tipoActual = tipo;
    closeForm(subForms);

    if (tipo === "portatil" || tipo == "sobremesa") {
        form.style.height = "450px";
    }
    
}

function closeForm(forms) {
    for(var x=0; x<forms.length; x++) {
        forms[x].style.height = 0;
    }
}




// funcion que cambia el valor del input para que aparezca el nombre de la foto que se escogio
function changeTittle() {
	let label = document.getElementById("image");
	let input = document.getElementById("input4");
	label.innerHTML = input.value.split('\\')[2];
}




//funcion que permite visualizar la imagen seleccionada antes de editar un producto
function loadFile(event) {
	var img = document.getElementById("output");
	var file = event.target.files[0];

	var reader = new FileReader();
	reader.onload = function(event) {
		img.src= event.target.result;
		
		changeTittle();
	}
	reader.readAsDataURL(file);

}
