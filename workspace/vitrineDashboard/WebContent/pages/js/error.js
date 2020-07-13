$(document).ready(function() {
	$.getScript('js/constants.js');
	var metodo = "authentication";
	var JSONData = JSON.stringify(new Object);
	var JSONObj = JSON.parse(JSONData);
	
	
	function persiste(acao){
		
		msg.clear();
		
		if( acao === "authentication")(
			$.ajax({
			    method: "POST",
			    url: service.URL + service.SECURITY + acao,
			    data: JSONData,
			    dataType: "json",
			    contentType: 'application/json; charset=UTF-8'
			    })
			    .fail(function(data) {
			    	showMessage(msg.erro,"Erro na autenticação do usuário. Por favor reinicie o processo!");
			    })
			    .done(function( data ) {
			    	$(location).attr('href', data.home);
			    	//showMessage(msg.sucesso, "Obrigado! login incluidos com sucesso." );
			    })
			    .error(function( data ) {
			    	$(location).attr('href', service.URL_ERROR);
			    	showMessage(msg.erro,"Erro na autenticação do usuário. Por favor reinicie o processo!");
			    })
		);
	}
	
});