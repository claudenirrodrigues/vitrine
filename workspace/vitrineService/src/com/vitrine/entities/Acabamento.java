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

public class Acabamento implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idAcabamento;
    private String acabamento;
    private Collection<Produto> produtoCollection;

    public Acabamento() {
    }

    public Acabamento(Integer idAcabamento) {
        this.idAcabamento = idAcabamento;
    }

    public Integer getIdAcabamento() {
        return idAcabamento;
    }

    public void setIdAcabamento(Integer idAcabamento) {
        this.idAcabamento = idAcabamento;
    }

    public String getAcabamento() {
        return acabamento;
    }

    public void setAcabamento(String acabamento) {
        this.acabamento = acabamento;
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
        hash += (idAcabamento != null ? idAcabamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acabamento)) {
            return false;
        }
        Acabamento other = (Acabamento) object;
        if ((this.idAcabamento == null && other.idAcabamento != null) || (this.idAcabamento != null && !this.idAcabamento.equals(other.idAcabamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Acabamento[ idAcabamento=" + idAcabamento + " ]";
    }
    
}
