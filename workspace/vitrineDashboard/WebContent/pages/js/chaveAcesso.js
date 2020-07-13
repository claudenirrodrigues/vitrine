$(document).ready(function() {
	var metodo = $.query.get(service.ACTION);
	var JSONData = JSON.stringify(new Object);
	var JSONObj = JSON.parse(JSONData);

	$(persiste(metodo));

	function validarChave() {
		if(!$("#chaveAcesso").val() || $("#chaveAcesso").length === 0){
			$("#formChaveAcesso").addClass("has-error");
			showMessage(msg.erro, "Por favor informe a chave enviada!");
			return;
		}
		localStorage.setItem(header.SERVICE_KEY, $("#chaveAcesso").val());
		metodo = "authorization";
		persiste(metodo);
	};


	function persiste(acao) {

		msg.clear();
		
		if (acao === "authorization")
			($.ajax({
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
				showMessage(msg.erro, "Erro na validação da chave de acesso. Por favor reinicie o processo!");
			})
			.done(function(data) {
				if(data.authToken){
					localStorage.setItem(header.AUTH_TOKEN, data.authToken);
				}
				$(location).attr('href',data.home);
				
			})
			.error(function(data) {
				$(location).attr('href',service.URL_ERROR);
				showMessage(msg.erro,
						"Erro na validação da chave de acesso. Por favor reinicie o processo!");
			}));


	}

	$("#nome").append($.query.get("nome"));
	$("#email").append($.query.get("email"));
	$("#btnChaveAcesso").bind("click", validarChave);

});