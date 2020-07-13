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
public class Detalhe implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idDetalhe;
    private String detalhe;
    private Collection<Produto> produtoCollection;

    public Detalhe() {
    }

    public Detalhe(Integer idDetalhe) {
        this.idDetalhe = idDetalhe;
    }

    public Integer getIdDetalhe() {
        return idDetalhe;
    }

    public void setIdDetalhe(Integer idDetalhe) {
        this.idDetalhe = idDetalhe;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
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
        hash += (idDetalhe != null ? idDetalhe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalhe)) {
            return false;
        }
        Detalhe other = (Detalhe) object;
        if ((this.idDetalhe == null && other.idDetalhe != null) || (this.idDetalhe != null && !this.idDetalhe.equals(other.idDetalhe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Detalhe[ idDetalhe=" + idDetalhe + " ]";
    }
    
}
