function create() {

	document.getElementById("status").innerHTML = '<span class="dica">Aguarde...</span>';
	
	var idPerfil = document.getElementById("idPerfil").value;
	var perfil = document.getElementById("perfil").value;
	
	var JSONObj = {"idPerfil":idPerfil, "perfil":perfil};
	var JSONData = JSON.stringify(JSONObj);

	var xhttp = new XMLHttpRequest();
	
	var urlServico = "http://localhost:8080/vitrineService/webresources/entities.perfil/";
	
	/*
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			var resposta = JSON.parse(xhttp.responseText);
			console.log("status: " + resposta.status);
			console.log("mensagem: " + resposta.mensagem);
			console.log("token: " + resposta.token);
			
			if (typeof(Storage) !== "undefined" && typeof(localStorage) !== "undefined") {
			    localStorage.setItem("token", resposta.token);
			    console.log("localStorage token: " + localStorage.getItem("token"));
			    document.getElementById("status").innerHTML = '<span class="dica">'+resposta.token+'</span>';
			} else {
				console.log("Sorry, your browser does not support Web Storage...");
			}
			
		} else if (xhttp.status != 200) {
			//console.log('Erro: ' + xhttp.readyState + ' | ' + xhttp.status);
			console.log(xhttp.responseText);
			document.getElementById("status").innerHTML = '<span class="dica">HTTP status: ' + xhttp.status + '</span>';
		}
	};
	*/
	
	xhttp.open("POST", urlServico, true);
	//xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.setRequestHeader("Accept", "application/json");
	//xhttp.setRequestHeader("Authorization", localStorage.getItem("token"));
	xhttp.setRequestHeader("Content-Type", "application/json");	
	
	console.log('JSONData: ' + JSONData);
	
	xhttp.send(JSONData);
	
}