package com.vitrine.entities.dao;

import java.util.Collection;
import java.util.List;

import com.vitrine.entities.Estoque;



public interface EntityManager<T> {
	
	/**SOMENTE METODOS ABSTRATOS GENERICOS NESTA INTERFACE**/
	public void executeCreate(T entity);
	public T executeRetrieve(T entity);
	public void executeUpdate(T entity);
	public void executeDelete(T entity);
	public void executeCreateOrUpdade(T entity);
	public List<T> executeRetrieveAll(T entity);
	public void executeCreateRelation(T entity, Collection<?> relations, String instruction);
	public Collection<?> executeRetrieveRelation(T entity, Object relation, String instruction);
	public List<T> executeCustomRetrieveAll(T entity, String instruction);
	public T executeCustomRetrieve(T entity, String instruction);
	public void executeCustom(T entity, String instruction, String msgErro);
	
	/**ENUM CONSTANTES**/
	public enum EnumInstruction {
		
		CREATE(1,"create"),
		RETRIEVE(2,"retrieve"),
		UPDATE(3,"update"),
		DELETE(4,"delete"),
		RETRIEVE_ALL(5,"retrieveAll"),
		CREATE_PERFIL(6,"createPerfil"),
		RETRIEVE_PERFIS(7,"retrievePerfis"),
		RETRIEVE_PESSOAS(8,"retrievePessoas"), 
		RETRIEVE_SUMMARY_ALL(9,"retrieveSummaryAll"),
		RETRIEVE_DETAILS(10,"retrieveDetails"),
		RETRIEVE_MAX(11,"retrieveMax"), 
		LOGOUT(12,"logout"), 
		CREATE_UPDATE(13,"createOrUpade");
		
		private int index;
		private String instruction;
		
		private EnumInstruction(int index, String instruction) {
			this.index = index;
			this.instruction = instruction;
		}

		public int getIndex() {
			return index;
		}

		public String getInstruction() {
			return instruction;
		}
		
	}
	
	/**
	* BLOCO - PROCEDURE MAIN - INICIO
	* 
	* Unica Procedure utilizada para execução
	* 
	* Toda Procedure de Entidade deverá ser incluida no corpo da Procedure
	* EntityManagerProcedure, na criação ou exlusão de novos dominios os parametros
	* deverão ser alterados na string de execução. 
	*/
	public enum EnumEntityManager{
		
		PROCEDURE("{call EntityManagerProcedure");
				/**
				 * Passou a ser informado de forma dinamica no construtor da classe {@link AbstractJDBCFacade}
				 */
				//(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"//30 POSIÇÕES
				//+"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"//+30 POSIÇÕES
				//+"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"//+30 POSIÇÕES
				//+ "?,?,?,?,?,?)}");//+6 POSIÇÕES
		
		private String call;

		EnumEntityManager(String call) {
			this.call = call;
		}

		public String getCall() {
			return call;
		}
		
		
	}
	
	/**
	* BLOCO - PROCEDURE MAIN - FIM
	*/

	
	/**
	 * BLOCO - PROCEDURE ENTITIES - INICIO Identifica a Procedure de cada Entidade
	 *
	 * USUARIO(EnumEntityManager.PROCEDURE.getCall(),"UsuarioProcedure");
	 *
	 * BLOCO - PROCEDURE ENTITIES - FIM
	 * 
	 * private String name; 
	 * 
	 * EnumXXX(String call, String name) {
		this.call = call;
		this.name = name;
		}
	 * 
	 * protected String getName() {
			return name;
		}
	 */

	public enum EnumTypeNull {

		NULL(null, "Null"),
		INTEGER(null, Integer.class.getName()),
		STRING(null, String.class.getName()),
		FLOAT(null, Float.class.getName()),
		DATE(null, java.sql.Date.class.getName()),
		DATE_UTIL(null, java.util.Date.class.getName()),
		COLLECTION(null, Collection.class.getName()),
		INT(null,int.class.getName()),
		LONG(null,long.class.getName());
		
		private Object value;
		private String name;
		
		EnumTypeNull(Object value, String name) {
			this.value = value;
			this.name = name;
		}

		public Object getNull() {
			return value;
		}

		public String getName() {
			return name;
		}
			
	}

	public enum EnumDomains {

		/**
		 * BLOCO - DOMINIOS - INICIO
		 * TODOS OS DOMINIOS EXISTENTES NO BANDO DE DADOS DEVERAM CONSTAR NESTA LISTA
		 * 
		 * IMPORTANTE!!! AO ADICIONAR OU REMOVER DOMINIOS DESTA LISTA É NECESSÁRIO ALTERAR 
		 * {@link EnumEntityManager.PROCEDURE}
		 */
		ENTITY("entity", 1, EnumTypeNull.NULL, false),
		INSTRUCTION("instruction", 2, EnumTypeNull.NULL, false),
		ACABAMENTO("acabamento", 3, EnumTypeNull.NULL, false),
		ALTURA("altura", 4, EnumTypeNull.NULL, false),
		ALTURA_SALTO("alturaSalto", 5, EnumTypeNull.NULL, false),
		BAIRRO("bairro", 6, EnumTypeNull.NULL, false),
		CATEGORIA("categoria", 7, EnumTypeNull.NULL, false),
		CEP("cep", 8, EnumTypeNull.NULL, false),
		CEP_FINAL("cepFinal", 9, EnumTypeNull.NULL, false),
		CEP_INICIAL("cepInicial", 10, EnumTypeNull.NULL, false),
		CIDADE("cidade", 11, EnumTypeNull.NULL, false),
		CNPJ_FILIAL("cnpjFilial", 12, EnumTypeNull.NULL, false),
		COMPLEMENTO("complemento", 13, EnumTypeNull.NULL, false),
		COR("cor", 14, EnumTypeNull.NULL, false),
		CPF_CNPJ("cpfCnpj", 15, EnumTypeNull.NULL, false),
		CPF_CNPJ_DIGITO("cpfCnpjDigito", 16, EnumTypeNull.NULL, false),
		CUPOM_PROMOCIONAL("cupomPromocional", 17, EnumTypeNull.NULL, true),
		DATA_ALTERACAO("dataAlteracao", 18, EnumTypeNull.NULL, false),
		DATA_ENTRADA("dataEntrada", 19, EnumTypeNull.NULL, false),
		DATA_FECHAMENTO("dataFechamento", 20, EnumTypeNull.NULL, false),
		DATA_FINAL("dataFinal", 21, EnumTypeNull.NULL, false),
		DATA_FINAL_PROMOCAO("dataFinalPromocao", 22, EnumTypeNull.NULL, true),
		DATA_INCLUSAO("dataInclusao", 23, EnumTypeNull.NULL, false),
		DATA_INICIO("dataInicio", 24, EnumTypeNull.NULL, false),
		DATA_INICIO_PROMOCAO("dataInicioPromocao", 25, EnumTypeNull.NULL, false),
		DATA_PEDIDO("dataPedido", 26, EnumTypeNull.NULL, false),
		DATA_SAIDA_TOTAL("dataSaidaTotal", 27, EnumTypeNull.NULL, false),
		DDD_CELULAR("dddCelular", 28, EnumTypeNull.NULL, false),
		DDD_COMERCIAL("dddComercial", 29, EnumTypeNull.NULL, false),
		DDD_CONTATO("dddContato", 30, EnumTypeNull.NULL, false),
		DDD_RESIDENCIAL("dddResidencial", 31, EnumTypeNull.NULL, false),
		DESCRICAO("descricao", 32, EnumTypeNull.NULL, false),
		DETALHE("detalhe", 33, EnumTypeNull.NULL, false),
		EMAIL_PRINCIPAL("emailPrincipal", 34, EnumTypeNull.NULL, false),
		EMAIL_SECUNDARIO("emailSecundario", 35, EnumTypeNull.NULL, false),
		ESTADO("estado", 36, EnumTypeNull.NULL, false),
		FONE_CELULAR("foneCelular", 37, EnumTypeNull.NULL, false),
		FONE_COMERCIAL("foneComercial", 38, EnumTypeNull.NULL, false),
		FONE_CONTATO("foneContato", 39, EnumTypeNull.NULL, false),
		FONE_RESIDENCIAL("foneResidencial", 40, EnumTypeNull.NULL, false),
		FRETE("frete", 41, EnumTypeNull.NULL, false),
		ID_ACABAMENTO("idAcabamento", 42, EnumTypeNull.NULL, false),
		ID_ALTURA_SALTO("idAlturaSalto", 43, EnumTypeNull.NULL, false),
		ID_CATEGORIA("idCategoria", 44, EnumTypeNull.NULL, false),
		ID_COR("idCor", 45, EnumTypeNull.NULL, false),
		ID_DETALHE("idDetalhe", 46, EnumTypeNull.NULL, false),
		ID_ENDERECO("idEndereco", 47, EnumTypeNull.NULL, false),
		ID_ESTOQUE("idEstoque", 48, EnumTypeNull.NULL, false),
		ID_FAIXACEP("idFaixaCep", 49, EnumTypeNull.NULL, false),
		ID_FRETE("idFrete", 50, EnumTypeNull.NULL, false),
		ID_MATERIAL("idMaterial", 51, EnumTypeNull.NULL, false),
		ID_NUMERACAO("idNumeracao", 52, EnumTypeNull.NULL, false),
		ID_PAGAMENTO("idPagamento", 53, EnumTypeNull.NULL, false),
		ID_PEDIDO_COMPRA("idPedidoCompra", 54, EnumTypeNull.NULL, false),
		ID_PERCENTUAL_VENDA("idPercentualVenda", 55, EnumTypeNull.NULL, false),
		ID_PERFIL("idPerfil", 56, EnumTypeNull.NULL, false),
		ID_PESSOA("idPessoa", 57, EnumTypeNull.NULL, false),
		ID_PRODUTO("idProduto", 58, EnumTypeNull.NULL, false),
		ID_TAMANHO("idTamanho", 59, EnumTypeNull.NULL, false),
		ID_TIPO_PRODUTO("idTipoProduto", 60, EnumTypeNull.NULL, false),
		ID_TIPO_SALTO("idTipoSalto", 61, EnumTypeNull.NULL, false),
		ID_USUARIO("idUsuario", 62, EnumTypeNull.NULL, false),
		IMAGEM("imagem", 63, EnumTypeNull.NULL, false),
		ITEM_CARRINHO("itemCarrinho", 64, EnumTypeNull.NULL, false),
		LARGURA("largura", 65, EnumTypeNull.NULL, false),
		LOGRADOURO("logradouro", 66, EnumTypeNull.NULL, false),
		MATERIAL("material", 67, EnumTypeNull.NULL, false),
		NEWSLLETER("newslleter", 68, EnumTypeNull.NULL, false),
		NOME("nome", 69, EnumTypeNull.NULL, false),
		NOME_EXIBIDO("nomeExibido", 70, EnumTypeNull.NULL, false),
		NUMERACAO("numeracao", 71, EnumTypeNull.NULL, false),
		NUMERO("numero", 72, EnumTypeNull.NULL, false),
		PAGAMENTO("pagamento", 73, EnumTypeNull.NULL, false),
		PARCELAS("parcelas", 74, EnumTypeNull.NULL, false),
		PERCENTUAL_CONSIGNADO("percentuaConsignado", 75, EnumTypeNull.NULL, false),
		PERCENTUAL_ATACADO("percentualAtacado", 76, EnumTypeNull.NULL, false),
		PERCENTUAL_PROMOCIONAL("percentualPromocional", 77, EnumTypeNull.NULL, false),
		PERCENTUAL_VAREJO("percentualVarejo", 78, EnumTypeNull.NULL, false),
		PERFIL("perfil", 79, EnumTypeNull.NULL, false),
		PESO("peso", 80, EnumTypeNull.NULL, false),
		PROFUNDIDADE("profundidade", 81, EnumTypeNull.NULL, true),
		QUANTIDADE("quantidade", 82, EnumTypeNull.NULL, false),
		QUANTIDADE_ENTRADA("quantidadeEntrada", 83, EnumTypeNull.NULL, false),
		QUANTIDADE_SAIDA("quantidadeSaida", 84, EnumTypeNull.NULL, false),
		QUANTIDADE_TOTAL("quantidadeTotal", 85, EnumTypeNull.NULL, false),
		SENHA("senha", 86, EnumTypeNull.NULL, false),
		SITUACAO("situacao", 87, EnumTypeNull.NULL, false),
		SOBRENOME("sobrenome", 88, EnumTypeNull.NULL, false),
		TAMANHO("tamanho", 89, EnumTypeNull.NULL, false),
		TAXA("taxa", 90, EnumTypeNull.NULL, false),
		TIPO_PERCENTUAL("tipoPercentual", 91, EnumTypeNull.NULL, false),
		TIPO_PRODUTO("tipoProduto", 92, EnumTypeNull.NULL, false),
		TIPO_SALTO("tipoSalto", 93, EnumTypeNull.NULL, false),
		VALOR_ATACADO("valorAtacado", 94, EnumTypeNull.NULL, false),
		VALOR_COMPRA_TOTAL("valorCompraTotal", 95, EnumTypeNull.NULL, false),
		VALOR_CONSIGNADO("valorConsignado", 96, EnumTypeNull.NULL, false),
		VALOR_CUSTO("valorCusto", 97, EnumTypeNull.NULL, false),
		VALOR_CUSTO_TOTAL("valorCustoTotal", 98, EnumTypeNull.NULL, false),
		VALOR_FRETE_TOTAL("valorFreteTotal", 99, EnumTypeNull.NULL, false),
		VALOR_LUCRO_TOTAL("valorLucroTotal", 100, EnumTypeNull.NULL, false),
		VALOR_PROMOCIONAL("valorPromocional", 101, EnumTypeNull.NULL, false),
		VALOR_VAREJO("valorVarejo", 102, EnumTypeNull.NULL, false),
		VALOR_COMISSAO("valorComissao", 103, EnumTypeNull.NULL, false),
		VALOR_COMISSAO_TOTAL("valorComissaoTotal", 104, EnumTypeNull.NULL, false),
		VALOR_COMPRA("valorCompra", 105, EnumTypeNull.NULL, false),
		VALOR_FRETE("valorFrete", 106, EnumTypeNull.NULL, false),
		VALOR_LUCRO("valorLucro", 107, EnumTypeNull.NULL, false),
		PERCENTUAL_COMISSAO("percentualComissao", 108, EnumTypeNull.NULL, false),
		PERCENTUAL_DESCONTO("percentualDesconto", 109, EnumTypeNull.NULL, false),
		VALOR_DESCONTO("valorDesconto", 110, EnumTypeNull.NULL, false),
		VALOR_DESCONTO_TOTAL("valorDescontoTotal", 111, EnumTypeNull.NULL, false),
		RAW_RESPONSE("rawResponse",112, EnumTypeNull.NULL, false),
	    ACCESS_TOKEN("accessToken",113, EnumTypeNull.NULL, false),
	    TOKEN_TYPE("tokenType",114, EnumTypeNull.NULL, false),
	    EXPIRE_IN("expiresIn",115, EnumTypeNull.NULL, false),
	    REFRESH_TOKEN("refreshToken",116, EnumTypeNull.NULL, false),
	    SCOPE("scope",117, EnumTypeNull.NULL, false),
	    OPEN_ID_TOKEN("openIdToken",118, EnumTypeNull.NULL, false),
	    AUTH_TOKEN("authToken",119, EnumTypeNull.NULL, false),
	    SERVICE_KEY("serviceKey", 120, EnumTypeNull.NULL, false);


		/**
		* BLOCO - DOMINIOS - FIM 
		*/
		
		private int parameterIndex;
		private String parameterName;
		private EnumTypeNull typeNull;
		private String call;
		private boolean isTrace;
		
		EnumDomains(String parameterName, int parameterIndex, EnumTypeNull typetNull, boolean isTrace) {
			this.parameterName = parameterName;
			this.parameterIndex = parameterIndex;
			this.typeNull = typetNull;
			this.isTrace = isTrace;
		}

		public int getParameterIndex() {
			return this.parameterIndex;
		}

		public String getParameterName() {
			return this.parameterName;
		}

		public EnumTypeNull getTypeNull() {
			return typeNull;
		}

		public String getCall() {
			return this.call;
		}
		
		protected boolean isTrace() {
			return isTrace;
		}

	}

	

	

	
}
