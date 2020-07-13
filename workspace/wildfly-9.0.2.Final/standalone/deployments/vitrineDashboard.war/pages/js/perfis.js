$(document).ready(function() {
	
	var metodo = "finAll";
	var JSONData = JSON.stringify(new Object);
	var JSONObj = JSON.parse(JSONData);
	var url = "http://localhost:8080/vitrineService/";
	var genericService = "webresources/entities.perfil/";
	var msg = {erro:1,alerta:2,info:3,sucesso:4,nullNumber:0,nullText:""};
	
	$(showMessage(msg.nullNumber,msg.nullText));
	$(persiste(metodo));
	
	function showMessage(msgNumber, msgText){
		
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
	
	function Adicionar(){
		metodo = "create";
		
		$("#perfilDatatable tbody").append(
			"<tr>"+
			"<td><input type='text'/></td>"+
			"<td><input type='text'/></td>"+
			"<td><button class='btn btn-xs btn-success btnSalvar' type='button'>Salvar</button>"+
			"<button class='btn btn-xs btn-danger btnExcluir' type='button'>Excluir</button></td>"+
			"</tr>");

		$(".btnSalvar").bind("click", Salvar);     
		$(".btnExcluir").bind("click", Excluir);
	};

	function Editar(){
		metodo = "edit";
		var par = $(this).parent().parent(); //tr
		var tdIdPerfil = par.children("td:nth-child(1)");
		var tdPerfil = par.children("td:nth-child(2)");
		var tdBotoes = par.children("td:nth-child(3)");
		
		tdIdPerfil.html("<input type='text' id='txtIdPerfil' value='"+tdIdPerfil.html()+"'/>");
		tdPerfil.html("<input type='text'id='txtPerfil' value='"+tdPerfil.html()+"'/>");
		tdBotoes.html("<button class='btn btn-xs btn-success btnSalvar' type='button'>Salvar</button>");

		$(".btnSalvar").bind("click", Salvar);
		$(".btnEditar").bind("click", Editar);
		$(".btnExcluir").bind("click", Excluir);
	};

	function Salvar(e){
		var par = $(this).parent().parent(); //tr
		var tdIdPerfil = par.children("td:nth-child(1)");
		var tdPerfil = par.children("td:nth-child(2)");
		var tdBotoes = par.children("td:nth-child(3)");
			
		tdIdPerfil.html(tdIdPerfil.children("input[type=text]").val());
		tdPerfil.html(tdPerfil.children("input[type=text]").val());
		tdBotoes.html("<button class='btn btn-xs btn-danger btnExcluir' type='button'>Excluir</button>"+
						"<button class='btn btn-xs btn-warning btnEditar' type='button'>Editar</button>");

		$(".btnEditar").bind("click", Editar);
		$(".btnExcluir").bind("click", Excluir);
		
		JSONObj = new Object;
		JSONObj = {"idPerfil":tdIdPerfil.html(), 
				"perfil":tdPerfil.html()};

		JSONData = JSON.stringify(JSONObj);

		showMessage(msg.nullNumber,msg.nullText);
		persiste(metodo);
	};

	function Excluir(){
		metodo ="remove";
	    var par = $(this).parent().parent(); //tr
	    var tdIdPerfil = par.children("td:nth-child(1)");
		var tdPerfil = par.children("td:nth-child(2)");
		
		JSONObj = new Object;
		JSONObj = {"idPerfil":tdIdPerfil.html(), 
				"perfil":tdPerfil.html()};

		JSONData = JSON.stringify(JSONObj);
		//JSONObj = JSON.parse(JSONData);
		
		showMessage(msg.nullNumber,msg.nullText);
	    persiste(metodo);
	    
		par.remove();
	};
	
	function inicializar(data){
		
		var table ="";
     	$.each(data , function (index, value){
     		table = table + "<tr>"
     		table = table + "<td>"+value.idPerfil+"</td>"
     		table = table + "<td>"+value.perfil+"</td>"
     		table = table + "<td><button class='btn btn-xs btn-danger btnExcluir' type='button'>Excluir</button>"
     		table = table + "<button class='btn btn-xs btn-warning btnEditar' type='button'>Editar</button></td>"
     		table = table + "</tr>"
 		})
 		
    	$("#perfilDatatable tbody").append(table);
     	
     	$(".btnEditar").bind("click", Editar);
    	$(".btnExcluir").bind("click", Excluir);
    
	};
	
	function persiste(metodo){
		
		if( metodo === "edit" )(
			$.ajax({
		    	method: "PUT",
		    	url: url + genericService + JSONObj.idPerfil,
		    	data: JSONData,
		    	dataType: "json",
				contentType: 'application/json; charset=UTF-8'
				})
		    	.fail(function(data) {
		    		showMessage(msg.erro,"Erro na Alteração dos Dados. Por favor reinicie o processo!");
		    	})
		    	.done(function( data ) {
		    		showMessage(msg.sucesso, "Obrigado! Dados Alterados com Sucesso." );
		    	})
		);
		
		if( metodo === "create")(
			$.ajax({
			    method: "POST",
			    url: url + genericService,
			    data: JSONData,
			    dataType: "json",
			    contentType: 'application/json; charset=UTF-8'
			    })
			    .fail(function(data) {
			    	showMessage(msg.erro,"Erro na inclusão dos Dados. Por favor reinicie o processo!");
			    })
			    .done(function( data ) {
			    	showMessage(msg.sucesso, "Obrigado! Dados incluidos com sucesso." );
			    })
			
		);
		
		if( metodo === "remove")(
			$.ajax({
			    method: "DELETE",
			    url: url + genericService + JSONObj.idPerfil,
			    dataType: "json",
			    contentType: 'application/json; charset=UTF-8'
			    })
			    .fail(function(data) {
			    	showMessage(msg.erro,"Erro na exclusão dos Dados. Por favor reinicie o processo!");
			    })
			    .done(function( data ) {
			    	showMessage(msg.sucesso, "Obrigado! Dados excluidos com sucesso." );
			    })
		);
		
		if( metodo === "finAll")(
			$.ajax({
			    method: "GET",
			    url: url + genericService,
			    dataType: "json",
			    contentType: 'application/json; charset=UTF-8'
			    })
			    .fail(function(data) {
			    	showMessage(msg.erro, "Erro ao carregar os dados da tabela de perfil. Por favor reinicie o processo!");
			    })
			    .done(function( data ) {
			    	inicializar(data);
			    	console.log("Dados inicializados com sucesso...");
			    })
		);
	    
	}

	$(".btnEditar").bind("click", Editar);
	$(".btnExcluir").bind("click", Excluir);
	$("#btnAdicionar").bind("click", Adicionar);
	
});