/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Familia
 */
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idProduto;
    private String descricao;
    private Float peso;
    private Float altura;
    private Float largura;
    private String profundidade;
    private Collection<Estoque> estoqueCollection;
    private Acabamento acabamento;
    private Categoria categoria;
    private Detalhe detalhe;
    private TipoProduto tipoProduto;
    private AlturaSalto alturaSalto;
    private Material material;
    private TipoSalto tipoSalto;
    
    public Produto() {
    }

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getLargura() {
        return largura;
    }

    public void setLargura(Float largura) {
        this.largura = largura;
    }

    public String getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(String profundidade) {
        this.profundidade = profundidade;
    }

    public Collection<Estoque> getEstoqueCollection() {
        return estoqueCollection;
    }

    public void setEstoqueCollection(Collection<Estoque> estoqueCollection) {
        this.estoqueCollection = estoqueCollection;
    }

    public Acabamento getAcabamento() {
        return acabamento;
    }

    public void setAcabamento(Acabamento acabamento) {
        this.acabamento = acabamento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Detalhe getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(Detalhe detalhe) {
        this.detalhe = detalhe;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public AlturaSalto getAlturaSalto() {
		return alturaSalto;
	}

	public void setAlturaSalto(AlturaSalto alturaSalto) {
		this.alturaSalto = alturaSalto;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public TipoSalto getTipoSalto() {
		return tipoSalto;
	}

	public void setTipoSalto(TipoSalto tipoSalto) {
		this.tipoSalto = tipoSalto;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduto != null ? idProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProduto == null && other.idProduto != null) || (this.idProduto != null && !this.idProduto.equals(other.idProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produto[ idProduto=" + idProduto + " ]";
    }
    
}
