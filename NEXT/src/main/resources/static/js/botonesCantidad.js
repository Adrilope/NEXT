var cantidad = document.getElementsByClassName("cantidad");
var mas = document.getElementsByClassName("mas");
var menos = document.getElementsByClassName("menos");
var precios = document.getElementsByClassName("precio-unidad");
var preciosFinales = document.getElementsByClassName("precio-final");
var total = document.getElementById("total");

window.onload = evaluarBotones();


//setea los onclick de los botones y comprubea si hay que desabilitar alguno debido a la cantidad de un producto
function evaluarBotones() {
    for(var x=0; x<cantidad.length; x++) {
        var valor = cantidad[x].getAttribute("value");
        mas[x].setAttribute("onclick", "calcularPrecio("+ x +","+ valor +","+ 1 +")");
        menos[x].setAttribute("onclick", "calcularPrecio("+ x +","+ valor +"," + -1 +")");
        
        switch(valor) {
            case "1": 
                menos[x].setAttribute("disabled", "true");
                break;

            case "5": 
                mas[x].setAttribute("disabled", "true");
                break;
            default : 
                mas[x].removeAttribute("disabled");
                menos[x].removeAttribute("disabled");
        }
    }
    var precioFinal = 0;
    for(var z=0; z<preciosFinales.length; z++) {
    	precioFinal += parseInt(preciosFinales[z].innerHTML);
    }
    total.innerHTML = precioFinal;
}



//calcula y cambia los valores de cantidad y precio final de cada producto
function calcularPrecio(x, valorActual, nuevaCantidad) {
    var aux = 0;
    valorActual = valorActual + nuevaCantidad;
    cantidad[x].setAttribute("value", valorActual.toString());
    cantidad[x].innerHTML = valorActual;

    if(precios.length != 0) {	//estamos en el carro
    	preciosFinales[x].innerHTML = parseInt(precios[x].innerHTML) * parseInt(cantidad[x].innerHTML); 
        for(var z=0; z<preciosFinales.length; z++) {
            aux += parseInt(preciosFinales[z].innerHTML);
        }
        total.innerHTML = aux;
    }
    else {	//estamos en el producto
    	var input = document.getElementById("inputCantidad");
    	input.setAttribute("value", valorActual);
    }
    
    evaluarBotones();
}