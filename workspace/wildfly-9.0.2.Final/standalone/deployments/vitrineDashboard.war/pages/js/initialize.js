$(document).ready(function() {
	var metodo = "initialize";
	var JSONData = JSON.stringify(new Object);
	var JSONObj = JSON.parse(JSONData);
	
	$(persiste(metodo));

	
	function persiste(acao){
		
		msg.clear();
		
		if( acao === "initialize")(
			$.ajax({
			    method: "POST",
			    url: service.URL + service.SECURITY + acao,
			    cache: false,
                crossDomain: true,
                headers : {
                	"Authorization" : localStorage.getItem(header.AUTH_TOKEN),
                	"serviceKey" : localStorage.getItem(header.SERVICE_KEY)
                },
			    dataType: "json",
			    contentType: 'application/json; charset=UTF-8'
			    })
			    .fail(function(data) {
			    	showMessage(msg.erro,"Erro na autenticação do usuário. Por favor reinicie o processo!");
			    })
			    .done(function( data ) {
			    	liberaPerfil(data);
			    })
			    .error(function( data ) {
			    	if(data.redirect){
			    		$(location).attr('href', data.redirect+"?nome="+data.nome);
			    	}else{
			    		$(location).attr('href', service.URL_ERROR);
			    	}
			    	showMessage(msg.erro,"Erro na autenticação do usuário. Por favor reinicie o processo!");
			    })
		);
	}
	
	function liberaPerfil(data){
		var value = $.map(data.perfilCollection, function(obj) {
    			if(obj.perfil.toLowerCase() === perfil.A){
    				return obj;
    			}
    		});
		
		if(value.length === 0){
			value = $.map(data.perfilCollection, function(obj) {
    			if(obj.perfil.toLowerCase() === perfil.R){
    				hideFunctions(perfil.A);
    				return obj;
    			}
    		});
		}
		
		if(value.length === 0){
			var nome ="";
			$.each(data.pessoaCollection, function (index, obj){
				if(obj.nomeExibido != null){
					nome = obj.nomeExibido;	
				}
			})
			
			value = $.map(data.pessoaCollection, function(obj) {return obj.nomeExibido;});
			
			$(location).attr('href', service.URL_SOLICITACAO_ACESSO+"?nome="+nome);
		}
			
		
	}
	
	function hideFunctions(perfil){
		perfil = "."+perfil;
		
		$.each(document.querySelectorAll(perfil), function (index, value){
			$(value).removeAttr("a");
			$(value).hide("fast");
		})
	
	}
	
});