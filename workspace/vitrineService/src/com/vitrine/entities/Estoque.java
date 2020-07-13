/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Familia
 */
public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idEstoque;
    private Integer quantidadeEntrada;
    private Integer quantidadeSaida;
    private Integer quantidadeTotal;
    private Date dataEntrada;
    private Date dataSaidaTotal;
    private Float valorCusto;
    private Float valorVarejo;
    private Float valorAtacado;
    private Float valorPromocional;
    private Float valorConsignado;
    private Date dataInicioPromocao;
    private Date dataFinalPromocao;
    private String imagem;
    private Cor cor;
    private Numeracao numeracao;
    private Produto produto;
    private Tamanho tamanho;
    private Collection<Carrinho> carrinhoCollection;
    private Trace trace;

    public Estoque() {
    }

    public Estoque(Produto produto, Cor cor, Tamanho tamanho, Numeracao numeracao, int idEstoque) {
        this.produto = produto;
        this.cor = cor;
        this.tamanho = tamanho;
        this.numeracao = numeracao;
        this.idEstoque = idEstoque;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Integer getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(Integer quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Integer quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaidaTotal() {
        return dataSaidaTotal;
    }

    public void setDataSaidaTotal(Date dataSaidaTotal) {
        this.dataSaidaTotal = dataSaidaTotal;
    }

    public Float getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(Float valorCusto) {
        this.valorCusto = valorCusto;
    }

    public Float getValorVarejo() {
        return valorVarejo;
    }

    public void setValorVarejo(Float valorVarejo) {
        this.valorVarejo = valorVarejo;
    }

    public Float getValorAtacado() {
        return valorAtacado;
    }

    public void setValorAtacado(Float valorAtacado) {
        this.valorAtacado = valorAtacado;
    }

    public Float getValorPromocional() {
        return valorPromocional;
    }

    public void setValorPromocional(Float valorPromocional) {
        this.valorPromocional = valorPromocional;
    }

    public Float getValorConsignado() {
        return valorConsignado;
    }

    public void setValorConsignado(Float valorConsignado) {
        this.valorConsignado = valorConsignado;
    }

    public Date getDataInicioPromocao() {
        return dataInicioPromocao;
    }

    public void setDataInicioPromocao(Date dataInicioPromocao) {
        this.dataInicioPromocao = dataInicioPromocao;
    }

    public Date getDataFinalPromocao() {
        return dataFinalPromocao;
    }

    public void setDataFinalPromocao(Date dataFinalPromocao) {
        this.dataFinalPromocao = dataFinalPromocao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Numeracao getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(Numeracao numeracao) {
        this.numeracao = numeracao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Collection<Carrinho> getCarrinhoCollection() {
        return carrinhoCollection;
    }

    public void setCarrinhoCollection(Collection<Carrinho> carrinhoCollection) {
        this.carrinhoCollection = carrinhoCollection;
    }

    public Trace getTrace() {
		return trace;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (int) (produto.getIdProduto() == null ? 0 : produto.getIdProduto());
        hash += (int) (cor.getIdCor() == null ? 0 : cor.getIdCor()); 
        hash += (int) (tamanho.getIdTamanho() == null ? 0 : tamanho.getIdTamanho()); 
        hash += (int) (numeracao.getIdNumeracao() == null ? 0 : numeracao.getIdNumeracao()); 
        hash += (int) idEstoque;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estoque)) {
            return false;
        }
        Estoque other = (Estoque) object;
        if (this.produto != other.produto) {
            return false;
        }
        if (this.cor != other.cor) {
            return false;
        }
        if (this.tamanho != other.tamanho) {
            return false;
        }
        if (this.numeracao != other.numeracao) {
            return false;
        }
        if (this.idEstoque != other.idEstoque) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Estoque[ produto=" + produto.toString() + ", cor=" + cor.toString() + ", tamanho=" + tamanho.toString() + ", numeracao=" + numeracao.toString() + ", idEstoque=" + idEstoque + " ]";
    }
    
}
