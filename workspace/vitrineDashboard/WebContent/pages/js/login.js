$(document).ready(function() {
	// $.getScript('js/constants.js');
	var metodo = $.query.get(service.ACTION);
	var JSONData = JSON.stringify(new Object);
	var JSONObj = JSON.parse(JSONData);

	$(persiste(metodo));

	function loginWhithGoogle() {
		metodo = "authorization";
		persiste(metodo);
		// showMessage(msg.sucesso, "Obrigado! Login efetuado
		// com sucesso." );
	}
	;

	function loginWhithFacebook() {
		metodo = "authorization";
		persiste(metodo);
		// showMessage(msg.sucesso, "Obrigado! Login efetuado
		// com sucesso." );
	}
	;

	function loginWhithIstagram() {
		metodo = "authorization";
		persiste(metodo);
		// showMessage(msg.sucesso, "Obrigado! Login efetuado
		// com sucesso." );
	}
	;

	function loginWhithTwitter() {
		metodo = "authorization";
		persiste(metodo);
		// showMessage(msg.sucesso, "Obrigado! Login efetuado
		// com sucesso." );
	}
	;

	function persiste(acao) {

		msg.clear();

		if (acao === "authorization")(
				$.ajax({
					method : "POST",
					url : service.URL + service.SECURITY + acao,
					data : JSONData,
					headers : {
	                	"Authorization" : localStorage.getItem(header.AUTH_TOKEN),
	                	"serviceKey" : localStorage.getItem(header.SERVICE_KEY)
	                },
	                dataType : "json",
					contentType : 'application/json; charset=UTF-8'
				})
				.fail(function(data) {
					showMessage(msg.erro, "Erro na autorização de acesso. Por favor reinicie o processo!");
				})
				.done(function(data) {
					console.info(data.message);
					if(data.authToken){
						localStorage.setItem(header.AUTH_TOKEN, data.authToken);
					}
					if(data.home){
			    		$(location).attr('href', data.home);
			    	}
					if(data.authorization){
						$(location).attr('href',data.authorization);
					}
			    })
				.error(function(data) {
					$(location).attr('href',service.URL_ERROR);
					showMessage(msg.erro,"Erro na autorização de acesso. Por favor reinicie o processo!");
				}));

		
		if (acao === "logout")(
				$.ajax({
					method : "POST",
					url : service.URL + service.SECURITY + acao,
					cache : false,
					crossDomain : true,
					headers : {
						"Authorization" : localStorage.getItem(header.AUTH_TOKEN),
						"serviceKey" : localStorage.getItem(header.SERVICE_KEY)
	                },
	                dataType : "json",
					contentType : 'application/json; charset=UTF-8'
				})
				.fail(function(data) {
					showMessage(msg.erro,"Erro ao encerrar a sessão do usuário. Por favor reinicie o processo!");
				})
				.done(function(data) {
					localStorage.removeItem(header.AUTH_TOKEN);
					$(location).attr('href',data.logout);
				})
				.error(function(data) {
					$(location).attr('href', service.URL_ERROR);
					showMessage(msg.erro, "Erro ao encerrar a sessão do usuário. Por favor reinicie o processo!");
				}));

	}

	
	$("#btnGoogle").bind("click", loginWhithGoogle);
	$("#btnFacebook").bind("click", loginWhithFacebook);
	$("#btnInstagram").bind("click", loginWhithIstagram);
	$("#btnTwitter").bind("click", loginWhithTwitter);

});