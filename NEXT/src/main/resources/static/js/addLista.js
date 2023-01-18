var mensaje = document.getElementById("ceroListas");

var model;

function setModelo(modelo) {
	model = modelo;
}

function setLista(lista) {
	addProducto(lista, model);
}




function addProducto(lista, modelo) {
    var xhr = new XMLHttpRequest(); 
    var url = "/favoritos/addProducto"; 

    // open a connection 
    xhr.open("POST", url, true); 

    // Set the request header i.e. which type of content you are sending 
    xhr.setRequestHeader("Content-Type", "application/json"); 

    // Create a state change callback 
    xhr.onreadystatechange = function () { 
        if (xhr.readyState === 4 && xhr.status === 200) { 
            // Print received data from server 
            console.log(this.responseText); 
        } 
    }; 

    var data = JSON.stringify({ "listaFavoritosKey": lista, "productoKey" : modelo });

    // Sending data with the request 
    xhr.send(data);
}

