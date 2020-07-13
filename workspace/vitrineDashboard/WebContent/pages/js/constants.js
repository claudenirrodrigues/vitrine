const DOMAIN_ORIGIN = document.URL.slice(0,document.URL.indexOf("/vitrineDashboard"));

const service = {
	URL : DOMAIN_ORIGIN + "/vitrineService/webresources/",
	URL_ERROR : DOMAIN_ORIGIN + "/vitrineDashboard/pages/login.html",
	URL_SOLICITACAO_ACESSO : DOMAIN_ORIGIN + "/vitrineDashboard/pages/solicitacaoAcesso.html",
	SECURITY : "security/",
	ACTION: "action",
	UNAUTHORIZED : 401,
	INTERNAL_SERVER_ERROR : 500
};

const perfil = {
		A : "administrador",
		R : "representante",
		C : "cliente"
};

const header = {
		PARAM_STATE : "state",
		PARAM_CODE : "code",
		AUTH_TOKEN : "authToken",
		X_FORWARDED_FOR : "X-Forwarded-For",
		SERVICE_KEY : "serviceKey"
};

var msg = {
	erro : 1,
	alerta : 2,
	info : 3,
	sucesso : 4,
	nullNumber : 0,
	nullText : "",
	clear : function() {
		showMessage(msg.nullNumber, msg.nullText);
	}
};

function showMessage(msgNumber, msgText) {

	msgText = "&nbsp;&nbsp;" + msgText;
	switch (msgNumber) {
	case msg.erro:
		$("#msgErro span").append(msgText);
		$("#msgErro").show();
		break;
	case msg.alerta:
		$("#msgAlerta span").append(msgText);
		$("#msgAlerta").show();
		break;
	case msg.info:
		$("#msgInfo span").append(msgText);
		$("#msgInfo").show();
		break;
	case msg.sucesso:
		$("#msgSucesso span").append(msgText);
		$("#msgSucesso").show();
		break;
	default:
		$("#msgErro span").empty();
		$("#msgAlerta span").empty();
		$("#msgInfo span").empty();
		$("#msgSucesso span").empty();
		$("#msgErro").hide();
		$("#msgAlerta").hide();
		$("#msgInfo").hide();
		$("#msgSucesso").hide();
	}

};

/*
function getYourIP() {
	$.getJSON("https://api.ipify.org?format=jsonp&callback=?",
		function(json) {
        	console.log('got ip: ', json.ip);
        	localStorage.setItem(header.X_FORWARDED_FOR, json.ip);
        	$("#yourIP span").append(json.ip);
		}
	);
};

$(getYourIP());
*/
	