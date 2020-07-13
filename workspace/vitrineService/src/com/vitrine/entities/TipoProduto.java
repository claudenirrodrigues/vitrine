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
public class TipoProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idTipoProduto;
    private String tipoProduto;
    private Collection<Produto> produtoCollection;

    public TipoProduto() {
    }

    public TipoProduto(Integer idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(Integer idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Collection<Produto> getProdutoCollection() {
        return produtoCollection;
    }

    public void setProdutoCollection(Collection<Produto> produtoCollection) {
        this.produtoCollection = produtoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoProduto != null ? idTipoProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProduto)) {
            return false;
        }
        TipoProduto other = (TipoProduto) object;
        if ((this.idTipoProduto == null && other.idTipoProduto != null) || (this.idTipoProduto != null && !this.idTipoProduto.equals(other.idTipoProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tipoproduto[ idTipoProduto=" + idTipoProduto + " ]";
    }
    
}
