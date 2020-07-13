$(document).ready(function() {
	
	var metodo = "findAll";
	var JSONData = JSON.stringify(new Object);
	var JSONObj = JSON.parse(JSONData);
	var url = "http://localhost:8080/vitrineService/";
	var genericServiceUsuario = "webresources/entities.usuario/";
	var genericServicePerfil = "webresources/entities.perfil/";
	var complement	= "perfil/";
	var msg = {
			erro:1,
			alerta:2,
			info:3,
			sucesso:4,
			nullNumber:0,
			nullText:"", 
			clear: function(){
				showMessage(msg.nullNumber, msg.nullText);
			}
	};
	
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
	
	function adicionar(){
		metodo = "create";
		
		$("#usuarioDataTable tbody").append(
			"<tr>"+
			"<td><input type='text'/></td>"+
			"<td><input type='text'/></td>"+
			"<td><select class='form-control'>"+
            	"<option value='A' selected>Ativo</option>"+
            	"<option value='I'>Inativo</option>"+
            	"</select></td>"+
			"<td>"+dateFormat(new Date(),"dd/mm/yyyy")+"</td>"+
			"<td>"+dateFormat(new Date(),"dd/mm/yyyy")+"</td>"+
			"<td><button class='btn btn-xs btn-info btnGerenciarPerfil' type='button'>Perfil</button></td>"+
			"<td><button class='btn btn-xs btn-success btnSalvar' type='button'>Salvar</button>"+
			"<button class='btn btn-xs btn-warning btnCancelar' type='button'>Cancelar</button></td>"+
			"</tr>");

		$(".btnSalvar").bind("click", salvar);     
		$(".btnCancelar").bind("click", cancelar);
		$("#btnAdicionar").unbind("click", adicionar);
		$("#btnAdicionar").addClass("disabled");
		$(".btnGerenciarPerfil").unbind("click", gerenciarPerfil);
		$(".btnGerenciarPerfil").addClass("disabled");
		$(".btnEditar").unbind("click", editar);
		$(".btnEditar").addClass("disabled");
		$(".btnExcluir").unbind("click", excluir);
		$(".btnExcluir").addClass("disabled");
		
	};
		
	function cancelar(){
		
		if(metodo === "create"){
			var par = $(this).parent().parent(); //tr
			par.remove();
			msg.clear();
			showMessage(msg.sucesso, "Obrigado! Inclusão de dados cancelada com sucesso." );
		}
		if(metodo === "edit"){
			var par = $(this).parent().parent().parent(); //tbody?
			par.html("");
			metodo = "findAll";
			persiste(metodo);
			showMessage(msg.sucesso, "Obrigado! Alteração de dados cancelada com sucesso." );
		}
		
		$("#btnAdicionar").bind("click", adicionar);
		$("#btnAdicionar").removeClass("disabled");
		$(".btnGerenciarPerfil").bind("click", gerenciarPerfil);
		$(".btnGerenciarPerfil").removeClass("disabled");
		$(".btnEditar").bind("click", editar);
		$(".btnEditar").removeClass("disabled");
		$(".btnExcluir").bind("click", excluir);
		$(".btnExcluir").removeClass("disabled");
	};

	function editar(){
		metodo = "edit";
		var par = $(this).parent().parent(); //tr
		var tdIdUsuario = par.children("td:nth-child(1)");
		var tdSenha = par.children("td:nth-child(2)");
		var tdSituacao = par.children("td:nth-child(3)");
		var tdDataInclusao = par.children("td:nth-child(4)");
		var tdDataAlteracao = par.children("td:nth-child(5)");
		var tdBotoesPerfil = par.children("td:nth-child(6)");
		var tdBotoes = par.children("td:nth-child(7)");
		var situacaoSelected = tdSituacao.html();
		
		tdIdUsuario.html("<input type='text' id='txtIdUsuario' value='"+tdIdUsuario.html()+"'/>");
		tdSenha.html("<input type='text'id='txtSenha' value='"+tdSenha.html()+"'/>");
		tdSituacao.html("<td><select class='form-control'>"+
						"<option value='A'>Ativo</option>"+
						"<option value='I'>Inativo</option>"+
    					"</select></td>");
		
		$('option[value='+situacaoSelected.substring(0,1)+']',tdSituacao).prop('selected', true);
		
		tdDataInclusao.html("<input type='text'id='txtDataInclusao' value='"+tdDataInclusao.html()+"'/>");
		tdDataAlteracao.html("<input type='text'id='txtDataAlteracao' value='"+tdDataAlteracao.html()+"'/>");
		
		tdBotoesPerfil.html("<button class='btn btn-xs btn-info btnGerenciarPerfil' type='button'>Perfil</button>");
		tdBotoes.html("<button class='btn btn-xs btn-success btnSalvar' type='button'>Salvar</button>"+
					"<button class='btn btn-xs btn-warning btnCancelar' type='button'>Cancelar</button>");

		$(".btnSalvar").bind("click", salvar);
		$(".btnCancelar").bind("click", cancelar);
		$("#btnAdicionar").unbind("click", adicionar);
		$("#btnAdicionar").addClass("disabled");
		$(".btnGerenciarPerfil").unbind("click", gerenciarPerfil);
		$(".btnGerenciarPerfil").addClass("disabled");
		$(".btnEditar").unbind("click", editar);
		$(".btnEditar").addClass("disabled");
		$(".btnExcluir").unbind("click", excluir);
		$(".btnExcluir").addClass("disabled");
	};
	
	function salvar(){
		var par = $(this).parent().parent(); //tr
		var tdIdUsuario = par.children("td:nth-child(1)");
		var tdSenha = par.children("td:nth-child(2)");
		var tdSituacao = par.children("td:nth-child(3)");
		var tdDataInclusao = par.children("td:nth-child(4)");
		var tdDataAlteracao = par.children("td:nth-child(5)");
		var tdBotoesPerfil = par.children("td:nth-child(6)");
		var tdBotoes = par.children("td:nth-child(7)");
		
		tdIdUsuario.html($('input',tdIdUsuario).val());
		tdSenha.html($('input',tdSenha).val());
		tdSituacao.html($('option:selected',tdSituacao).text());
		tdDataInclusao.html($('input',tdDataInclusao).val());
		tdDataAlteracao.html($('input',tdDataAlteracao).val());
		tdBotoesPerfil.html("<button class='btn btn-xs btn-info btnGerenciarPerfil' type='button' data-toggle='modal' data-target='#perfilModal'>Perfil</button>");

		tdBotoes.html("<button class='btn btn-xs btn-danger btnExcluir' type='button'>Excluir</button>"+
						"<button class='btn btn-xs btn-warning btnEditar' type='button'>Editar</button>");

		$(".btnEditar").bind("click", editar);
		$(".btnEditar").removeClass("disabled");
		$(".btnExcluir").bind("click", excluir);
		$(".btnExcluir").removeClass("disabled");
		$("#btnAdicionar").bind("click",adicionar);
		$("#btnAdicionar").removeClass("disabled");
		$(".btnGerenciarPerfil").bind("click", gerenciarPerfil);
		$(".btnGerenciarPerfil").removeClass("disabled");
		
		JSONObj = new Object;
		JSONObj = {"idUsuario":tdIdUsuario.html(), 
				"senha":tdSenha.html(),
				"situacao":tdSituacao.html().substring(0,1),
				"dataInclusao":dateFormat(Date().valueOf(tdDataInclusao.html()),"yyyy-mm-dd"),
				"dataAlteracao":dateFormat(Date().valueOf(tdDataAlteracao.html()),"yyyy-mm-dd"),
				};

		JSONData = JSON.stringify(JSONObj);

		persiste(metodo);
	};

	function excluir(){
		metodo ="remove";
		var par = $(this).parent().parent(); //tr
		var tdIdUsuario = par.children("td:nth-child(1)");
		var tdSenha = par.children("td:nth-child(2)");
		var tdSituacao = par.children("td:nth-child(3)");
		var tdDataInclusao = par.children("td:nth-child(4)");
		var tdDataAlteracao = par.children("td:nth-child(5)");
		var tdBotoesPerfil = par.children("td:nth-child(6)");
		var tdBotoes = par.children("td:nth-child(7)");
		
		JSONObj = new Object;
		JSONObj = {"idUsuario":tdIdUsuario.html(), 
				"senha":tdSenha.html(),
				"situacao":tdSituacao.html(),
				"dataInclusao":dateFormat(Date().valueOf(tdDataInclusao.html()),"yyyy-MM-dd"),
				"dataAlteracao":dateFormat(Date().valueOf(tdDataAlteracao.html()),"yyyy-MM-dd"),
				};

		JSONData = JSON.stringify(JSONObj);
		
		persiste(metodo);
	    
		par.remove();
	};
	
	function gerenciarPerfil(){
		metodo = "findPerfil";
		var par = $(this).parent().parent(); //tr
		var tdIdUsuario = par.children("td:nth-child(1)");
		
		JSONObj = new Object;
		JSONObj = {"idUsuario":tdIdUsuario.html(), 
				"senha":"",
				"situacao":"",
				"dataInclusao":"",
				"dataAlteracao":"",
				};

		JSONData = JSON.stringify(JSONObj);
		
		$(persiste(metodo));
	};
	
	function consultarPerfis(){
		metodo = "findAllPerfil";
		
		$("#btnAdicionarPerfil").unbind("click", consultarPerfis);
		$("#btnAdicionarPerfil").addClass("disabled");
		$(".btnEditarPerfil").unbind("click", editarPerfil);
		$(".btnEditarPerfil").addClass("disabled");
		$(".btnExcluirPerfil").unbind("click", excluirPerfil);
		$(".btnExcluirPerfil").addClass("disabled");
		
		$(persiste(metodo));
	};

	function adicionarPerfil(data){
		metodo = "createPerfil";
		var options="";
		
		$.each(data , function (index, value){
			options = options + "<option value='"+ value.idPerfil +"'>"+ value.perfil +"</option>"
     	})
		
     	$("#perfilModalDataTable tbody").append(
			"<tr>"+
			"<td>"+JSONObj.idUsuario+"</td>"+
			"<td><select class='form-control'>"+options+"</select></td>"+
			"<td><button class='btn btn-xs btn-success btnSalvarPerfil' type='button'>Salvar</button>"+
			"<button class='btn btn-xs btn-warning btnCancelar' type='button'>Cancelar</button></td>"+
			"</tr>");

		$(".btnSalvarPerfil").bind("click", salvarPerfil);     
		$(".btnCancelar").bind("click", cancelar);
	};
	
	function salvarPerfil(){
		var par = $(this).parent().parent(); //tr
		var tdIdUsuario = par.children("td:nth-child(1)");
		var tdPerfil = par.children("td:nth-child(2)");
		var tdBotoes = par.children("td:nth-child(3)");
		
		var idPerfil = $('option:selected',tdPerfil).val();
		var perfil = $('option:selected',tdPerfil).text();
		
		tdPerfil.html(perfil);
		tdBotoes.html("<button class='btn btn-xs btn-danger btnExcluirPerfil' type='button'>Excluir</button>"+
						"<button class='btn btn-xs btn-warning btnEditarPerfil' type='button'>Editar</button>");

		$(".btnEditarPerfil").bind("click", editarPerfil);
		$(".btnEditarPerfil").removeClass("disabled");
		$(".btnExcluirPerfil").bind("click", excluirPerfil);
		$(".btnExcluirPerfil").removeClass("disabled");
		$("#btnAdicionarPerfil").bind("click", consultarPerfis);
		$("#btnAdicionarPerfil").removeClass("disabled");
		
		JSONObj = new Object;
		JSONObj = {"idUsuario":tdIdUsuario.html(), 
				"senha":"",
				"situacao":"",
				"dataInclusao":"",
				"dataAlteracao":"",
				"perfilCollection":[ 
					{
						"idPerfil": idPerfil,
						"perfil": perfil,
						"usuarioCollection": null
					}]
				};

		JSONData = JSON.stringify(JSONObj);

		persiste(metodo);
	};
		
	function excluirPerfil(){
		metodo ="removePerfil";
		var par = $(this).parent().parent(); //tr
		var tdIdUsuario = par.children("td:nth-child(1)");
		var tdPerfil = par.children("td:nth-child(2)");
		var tdBotoes = par.children("td:nth-child(3)");
		
		JSONObj = new Object;
		JSONObj = {"idUsuario":tdIdUsuario.html(), 
				"senha":"",
				"situacao":"",
				"dataInclusao":"",
				"dataAlteracao":"",
				"perfilCollection":{
					"idPerfil":$('option:selected',tdPerfil).val(),
					"perfil":$('option:selected',tdPerfil).text(),
					}
				};

		JSONData = JSON.stringify(JSONObj);
		
		persiste(metodo);
	    
		par.remove();
	};

	function editarPerfil(){
		metodo = "editPerfil";
		var par = $(this).parent().parent(); //tr
		var tdIdUsuario = par.children("td:nth-child(1)");
		var tdPerfil = par.children("td:nth-child(2)");
		var tdBotoes = par.children("td:nth-child(3)");
		
		var situacaoSelected = tdSituacao.html();
		
		tdIdUsuario.html("<input type='text' id='txtIdUsuario' value='"+tdIdUsuario.html()+"'/>");
		tdSituacao.html("<td><select class='form-control'>"+
						"<option value='A'>Ativo</option>"+
						"<option value='I'>Inativo</option>"+
    					"</select></td>");
		
		$('option[value='+situacaoSelected.substring(0,1)+']',tdSituacao).prop('selected', true);
		
		tdDataInclusao.html("<input type='text'id='txtDataInclusao' value='"+tdDataInclusao.html()+"'/>");
		tdDataAlteracao.html("<input type='text'id='txtDataAlteracao' value='"+tdDataAlteracao.html()+"'/>");
		
		tdBotoesPerfil.html("<button class='btn btn-xs btn-info btnGerenciarPerfil' type='button'>Perfil</button>");
		tdBotoes.html("<button class='btn btn-xs btn-success btnSalvar' type='button'>Salvar</button>"+
					"<button class='btn btn-xs btn-warning btnCancelar' type='button'>Cancelar</button>");

		$(".btnSalvar").bind("click", salvar);
		$(".btnCancelar").bind("click", cancelar);
		$("#btnAdicionar").unbind("click", adicionar);
		$("#btnAdicionar").addClass("disabled");
		$(".btnGerenciarPerfil").unbind("click", gerenciarPerfil);
		$(".btnGerenciarPerfil").addClass("disabled");
		$(".btnEditar").unbind("click", editar);
		$(".btnEditar").addClass("disabled");
		$(".btnExcluir").unbind("click", excluir);
		$(".btnExcluir").addClass("disabled");
	};

	function inicializar(data){
		
		var table ="";
     	$.each(data , function (index, value){
     		var situacao;
     		if(value.situacao === "A"){
     			situacao = "Ativo";
     		}else{
     			situacao = "Inativo";
     		}
     		table = table + "<tr>"
     		table = table + "<td>"+value.idUsuario+"</td>"
     		table = table + "<td>"+value.senha+"</td>"
     		table = table + "<td>"+situacao+"</td>"
     		table = table + "<td>"+dateFormat(value.dataInclusao,"dd/mm/yyyy")+"</td>"
     		table = table + "<td>"+dateFormat(value.dataAlteracao,"dd/mm/yyyy")+"</td>"
     		table = table + "<td><button class='btn btn-xs btn-info btnGerenciarPerfil' type='button' data-toggle='modal' data-target='#perfilModal'>Perfil</button></td>"
     		table = table + "<td><button class='btn btn-xs btn-danger btnExcluir' type='button'>Excluir</button>"
     		table = table + "<button class='btn btn-xs btn-warning btnEditar' type='button'>Editar</button></td>"
     		table = table + "</tr>"
 		})
 		
    	$("#usuarioDataTable tbody").append(table);
     	
     	$(".btnEditar").bind("click", editar);
    	$(".btnExcluir").bind("click", excluir);
    	$(".btnGerenciarPerfil").bind("click", gerenciarPerfil);
	};
	
	function sairModal(){
		$("#perfilModalDataTable tbody tr").remove();
	};
	
	function inicializarModal(data){
		
		var table ="";
     	$.each(data.perfilCollection , function (index, value){
     		table = table + "<tr>"
     		table = table + "<td>"+data.idUsuario+"</td>"
     		table = table + "<td>"+value.perfil+"</td>"
     		table = table + "<td><button class='btn btn-xs btn-danger btnExcluirPerfil' type='button'>Excluir</button>"
     		table = table + "<button class='btn btn-xs btn-warning btnEditarPerfil' type='button'>Editar</button></td>"
     		table = table + "</tr>"
 		})
 		
    	$("#perfilModalDataTable tbody").append(table);
     	
     	$(".btnEditarPerfil").bind("click", editarPerfil);
    	$(".btnExcluirPerfil").bind("click", excluirPerfil);
    	$("#btnAdicionarPerfil").bind("click", consultarPerfis);
		$("#btnAdicionarPerfil").removeClass("disabled");
    
	};
	
	function persiste(acao){
		
		msg.clear();
		
		if( acao === "edit" )(
			$.ajax({
		    	method: "PUT",
		    	url: url + genericServiceUsuario + JSONObj.idUsuario,
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
		
		if( acao === "create")(
			$.ajax({
			    method: "POST",
			    url: url + genericServiceUsuario,
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
		
		if( acao === "remove")(
			$.ajax({
			    method: "DELETE",
			    url: url + genericServiceUsuario + JSONObj.idUsuario,
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
		
		if( acao === "editPerfil" )(
				$.ajax({
			    	method: "PUT",
			    	url: url + genericServiceUsuario + complement + JSONObj.idUsuario,
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
		
		if( acao === "createPerfil")(
				$.ajax({
				    method: "POST",
				    url: url + genericServiceUsuario + complement,
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
		
		if( acao === "removePerfil")(
				$.ajax({
				    method: "DELETE",
				    url: url + genericServiceUsuario + complement + JSONObj.idUsuario,
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
			
		if( acao === "findAll")(
			$.ajax({
			    method: "GET",
			    url: url + genericServiceUsuario,
			    dataType: "json",
			    contentType: 'application/json; charset=UTF-8'
			    })
			    .fail(function(data) {
			    	showMessage(msg.erro, "Erro ao carregar os dados da tabela de usuário. Por favor reinicie o processo!");
			    })
			    .done(function( data ) {
			    	inicializar(data);
			    	console.log("Dados inicializados com sucesso..." );
			    })
		);
		
		if( acao === "findPerfil")(
				$.ajax({
				    method: "GET",
				    url: url + genericServiceUsuario + complement + JSONObj.idUsuario,
				    dataType: "json",
				    contentType: 'application/json; charset=UTF-8'
				    })
				    .fail(function(data) {
				    	showMessage(msg.erro, "Erro ao carregar os dados da tabela de perfil. Por favor reinicie o processo!");
				    })
				    .done(function( data ) {
				    	inicializarModal(data);
				    	console.log("Dados inicializados com sucesso..." );
				    })
		);
		
		if( acao === "findAllPerfil")(
				$.ajax({
				    method: "GET",
				    url: url + genericServicePerfil,
				    dataType: "json",
				    contentType: 'application/json; charset=UTF-8'
				    })
				    .fail(function(data) {
				    	showMessage(msg.erro, "Erro ao carregar os dados da tabela de perfil. Por favor reinicie o processo!");
				    })
				    .done(function( data ) {
				    	adicionarPerfil(data);
				    	console.log("Dados inicializados com sucesso..." );
				    })
		);
	    
	}

	$(".btnEditar").bind("click", editar);
	$(".btnExcluir").bind("click", excluir);
	$("#btnAdicionar").bind("click", adicionar);
	$(".btnGerenciarPerfil").bind("click", gerenciarPerfil);
	$(".btnEditarPerfil").bind("click", editarPerfil);
	$(".btnExcluirPerfil").bind("click", excluirPerfil);
	$("#btnAdicionarPerfil").bind("click", consultarPerfis);
	$("#btnSairModal").bind("click", sairModal);
	$("#perfilModal").bind("hidden.bs.modal", sairModal);
	
});