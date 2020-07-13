$(document).ready(function() {
	
	var metodo = "summaryAll";
	var JSONData = JSON.stringify(new Object);
	var JSONObj = JSON.parse(JSONData);
	var genericServiceEstoque = "entities.estoque/";
	
	$(persiste(metodo));
	
	function inicializar(data){
		
		$.each(data , function (index, value){
     		if(value.trace.situacao === "Destaque"){
     			$("#destaques").append(showDestaque(value));
     		}
     	})
     	$(destacarValores("* #valorDestacado"));
 		$(".btnDetalhes").bind("click", detalhes);
	};
	
	function inicializarModal(data){
		
		$("#imgProductLarge").attr('src', data[0].imagem);
		$("#referencia").append("Ref.: " + data[0].produto.idProduto); 
		$("#produto").append(data[0].produto.tipoProduto.tipoProduto +" "+ 
				data[0].produto.tipoSalto.tipoSalto +" "+ 
				data[0].produto.alturaSalto.alturaSalto); 
		$("#descricao").append(data[0].produto.descricao);
		$("#valorVarejo").append(data[0].valorVarejo);
		$("#cor").append(data[0].cor.cor);
		$("#acabamento").append(data[0].produto.acabamento.acabamento);
		$("#detalhe").append(data[0].produto.detalhe.detalhe);
		$("#material").append(data[0].produto.material.material);
	
		$(destacarValores("#valorVarejo"));
		
		var table ="";
     	$.each(data , function (index, value){
     		table = table + "<tr>"
     		table = table + "<td>"+value.numeracao.numeracao+"</td>"
     		table = table + "<td>"+value.quantidadeTotal+"</td>"
     		if(value.quantidadeTotal > 0){
     			table = table + "<td><button class='btn btn-xs btn-success btnReservar' type='button'>Reservar</button></td>"
     		}else{
     			table = table + "<td><button class='btn btn-xs btn-disabled' type='button'>Reservar</button></td>"
     		}
     		
     		table = table + "</tr>"
 		})
 		
    	$("#detalhesModalDataTable tbody").append(table);
     	
     	$(".btnReservar").bind("click", reservar);
    };
    
    function limparModal(){
    	$("#imgProductLarge").attr('src', '');
		$("#referencia").html(''); 
		$("#produto").html('');  
		$("#descricao").html(''); 
		$("#valorVarejo").html(''); 
		$("#cor").html(''); 
		$("#acabamento").html(''); 
		$("#detalhe").html(''); 
		$("#material").html(''); 
    	$("#detalhesModalDataTable tbody tr").remove();
	};
    
    function reservar (){
    	
    };
	
	function showDestaque(value){
		//tira a mascara
		//$('#demo8').maskMoney('unmasked')[0] 
		return   "<div class='col-sm-4'>"
		 		+ 	"<div class='product-image-wrapper'>"
		 		+ 		"<div class='single-products'>"
		 		+ 			"<div class='productinfo text-center'>"
		 		+ 				"<img src='"+value.imagem+"'/>"
		 		+ 				"<h2 id='valorDestacado'>"+value.valorVarejo+"</h2>"
		 		+ 				"<p>"+value.produto.tipoProduto.tipoProduto+"</p>"
		 		+ 				"<p id='idProduto'>Referência - <span id='"+value.idEstoque+"'>"+value.produto.idProduto+"</span></p>"
		 		+ 				"<button type='button' class='btn btn-default add-to-cart btnDetalhes' data-toggle='modal' data-target='#detalhesModal'><i class='fa fa-eye'></i>Detalhes</button>"
		 		+ 			"</div>"
		 		+ 			"<div class='product-overlay'>"
		 		+ 				"<div class='overlay-content'>"
		 		+ 					"<h2 id='valorDestacado'>"+value.valorVarejo+"</h2>"
		 		+ 					"<p>"+value.produto.tipoProduto.tipoProduto+"</p>"
		 		+ 					"<p id='idProduto'>Referência - <span id='"+value.idEstoque+"'>"+value.produto.idProduto+"</span></p>"
		 		+ 					"<button type='button' class='btn btn-default add-to-cart btnDetalhes' data-toggle='modal' data-target='#detalhesModal'><i class='fa fa-eye'></i>Detalhes</button>"
		 		+ 				"</div>"
		 		+ 			"</div>"
		 		+ 		"</div>"
		 		+ 		"<div class='choose' style='display: none;'>"
		 		+ 			"<ul class='nav nav-pills nav-justified'>"
		 		+ 				"<li><a href='#'><i class='fa fa-plus-square'></i>Favorito</a></li>"
		 		+ 				"<li><a href='#'><i class='fa fa-plus-square'></i>Comparar</a></li>"
		 		+ 			"</ul>"
		 		+ 		"</div>"
		 		+ 	"</div>"
		 		+ "</div>"
 		
	};
	
	function detalhes(){
		metodo = "details";
		var parSpan = $(this).parent().children("p")[1].children;
		console.log(parSpan[0].innerHTML);
		console.log(parSpan[0].id);
		
		JSONObj = new Object;
		JSONObj = {"idEstoque":parSpan[0].id, 
				"produto":{
						"idProduto": parSpan[0].innerHTML
					}
				};

		JSONData = JSON.stringify(JSONObj);

		persiste(metodo);
	};
	
	function persiste(acao){
		
		msg.clear();
		
		if( acao === "edit" )(
			$.ajax({
		    	method: "PUT",
		    	url: service.URL + genericServiceEstoque + JSONObj.idEstoque,
		    	headers : {
                	"Authorization" : localStorage.getItem(header.AUTH_TOKEN),
                	"serviceKey" : localStorage.getItem(header.SERVICE_KEY)
                },
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
			    url: service.URL + genericServiceEstoque,
			    headers : {
                	"Authorization" : localStorage.getItem(header.AUTH_TOKEN),
                	"serviceKey" : localStorage.getItem(header.SERVICE_KEY)
                },
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
		
		if( acao === "details")(
				$.ajax({
				    method: "POST",
				    url: service.URL + genericServiceEstoque + acao,
				    headers : {
	                	"Authorization" : localStorage.getItem(header.AUTH_TOKEN),
	                	"serviceKey" : localStorage.getItem(header.SERVICE_KEY)
	                },
				    data: JSONData,
				    dataType: "json",
				    contentType: 'application/json; charset=UTF-8'
				    })
				    .fail(function(data) {
				    	showMessage(msg.erro,"Erro ao consultar detalhes do produto. Por favor reinicie o processo!");
				    })
				    .done(function( data ) {
				    	inicializarModal(data);
				    	showMessage(msg.sucesso, "Detalhes consultados com sucesso." );
				    })
				    .error(function(data) {
				    	$(location).attr('href',service.URL_ERROR);
				    })
				
		);
		
		if( acao === "remove")(
			$.ajax({
			    method: "DELETE",
			    url: service.URL + genericServiceEstoque + JSONObj.idEstoque,
			    headers : {
                	"Authorization" : localStorage.getItem(header.AUTH_TOKEN),
                	"serviceKey" : localStorage.getItem(header.SERVICE_KEY)
                },
			    dataType: "json",
			    contentType: 'application/json; charset=UTF-8'
			    })
			    .fail(function(data) {
			    	showMessage(msg.erro,"Erro na exclusão dos Dados. Por favor reinicie o processo!");
			    })
			    .done(function( data ) {
			    	showMessage(msg.sucesso, "Obrigado! Dados excluidos com sucesso." );
			    })
			    .error(function(data) {
					$(location).attr('href',service.URL_ERROR);
				})
		);
		
		if( acao === "findAll")(
			$.ajax({
			    method: "GET",
			    url: service.URL + genericServiceEstoque,
			    headers : {
                	"Authorization" : localStorage.getItem(header.AUTH_TOKEN),
                	"serviceKey" : localStorage.getItem(header.SERVICE_KEY)
                },
			    dataType: "json",
			    contentType: 'application/json; charset=UTF-8'
			    })
			    .fail(function(data) {
			    	showMessage(msg.erro, "Erro ao carregar os dados da tabela de estoque. Por favor reinicie o processo!");
			    })
			    .done(function( data ) {
			    	inicializar(data);
			    	console.log("Dados inicializados com sucesso..." );
			    })
		);
		
		if( acao === "summaryAll")(
				$.ajax({
				    method: "GET",
				    url: service.URL + genericServiceEstoque + acao,
				    headers : {
	                	"Authorization" : localStorage.getItem(header.AUTH_TOKEN),
	                	"serviceKey" : localStorage.getItem(header.SERVICE_KEY)
	                },
				    dataType: "json",
				    contentType: 'application/json; charset=UTF-8'
				    })
				    .fail(function(data) {
				    	showMessage(msg.erro, "Erro ao carregar os dados da tabela de estoque. Por favor reinicie o processo!");
				    })
				    .done(function( data ) {
				    	inicializar(data);
				    	console.log("Dados inicializados com sucesso..." );
				    })
				    .error(function(data) {
				    	$(location).attr('href',service.URL_ERROR);
				    })
		);
		
	};
	
	$('#detalhesModal').on('hidden.bs.modal', function (e) {
		limparModal();
	})
	
	$(".btnDetalhes").bind("click", detalhes);
	$("#btnSairModal").bind("click", limparModal);
	
});