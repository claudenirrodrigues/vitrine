$(document).ready(function() {
	var metodo = "authentication";
	var JSONData = JSON.stringify(new Object);
	var JSONObj = JSON.parse(JSONData);
	
	$(persiste(metodo));

	
	function persiste(acao){
		
		msg.clear();
		
		if( acao === "authentication")(
			$.ajax({
			    method: "POST",
			    url: service.URL + service.SECURITY + acao,
			    cache: false,
                crossDomain: true,
                headers: {
                    "state" : $.query.get(header.PARAM_STATE),
                    "code" : $.query.get(header.PARAM_CODE),
                    "serviceKey" : localStorage.getItem(header.SERVICE_KEY)
                },
			    dataType: "json",
			    contentType: 'application/json; charset=UTF-8'
			    })
			    .fail(function(data) {
			    	showMessage(msg.erro,"Erro na autenticação do usuário. Por favor reinicie o processo!");
			    })
			    .done(function( data ) {
			    	localStorage.setItem(header.AUTH_TOKEN, data.authToken);
			    	if(data.home){
			    		$(location).attr('href', data.home);
			    	}
			    	if(data.inputServiceKey){
			    		$(location).attr('href', data.inputServiceKey+"?nome="+data.nome+"&email="+data.email);
			    	}
			    })
			    .error(function( data ) {
			    	$(location).attr('href', service.URL_ERROR);
			    	showMessage(msg.erro,"Erro na autenticação do usuário. Por favor reinicie o processo!");
			    })
		);
	}
	
});