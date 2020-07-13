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

public class AlturaSalto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idAlturaSalto;
    private String alturaSalto;
    private Collection<Produto> produtoCollection;

    public AlturaSalto() {
    }

    public AlturaSalto(Integer idAlturaSalto) {
        this.idAlturaSalto = idAlturaSalto;
    }

    public Integer getIdAlturaSalto() {
        return idAlturaSalto;
    }

    public void setIdAlturaSalto(Integer idAlturaSalto) {
        this.idAlturaSalto = idAlturaSalto;
    }

    public String getAlturaSalto() {
        return alturaSalto;
    }

    public void setAlturaSalto(String alturaSalto) {
        this.alturaSalto = alturaSalto;
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
        hash += (idAlturaSalto != null ? idAlturaSalto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlturaSalto)) {
            return false;
        }
        AlturaSalto other = (AlturaSalto) object;
        if ((this.idAlturaSalto == null && other.idAlturaSalto != null) || (this.idAlturaSalto != null && !this.idAlturaSalto.equals(other.idAlturaSalto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AlturaSalto[ idAlturaSalto=" + idAlturaSalto + " ]";
    }
    
}
