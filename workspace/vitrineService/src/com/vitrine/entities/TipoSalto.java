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

public class TipoSalto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idTipoSalto;
    private String tipoSalto;
    private Collection<Produto> produtoCollection;

    public TipoSalto() {
    }

    public TipoSalto(Integer idTipoSalto) {
        this.idTipoSalto = idTipoSalto;
    }

    public Integer getIdTipoSalto() {
        return idTipoSalto;
    }

    public void setIdTipoSalto(Integer idAcabamento) {
        this.idTipoSalto = idAcabamento;
    }

    public String getTipoSalto() {
        return tipoSalto;
    }

    public void setTipoSalto(String tipoSalto) {
        this.tipoSalto = tipoSalto;
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
        hash += (idTipoSalto != null ? idTipoSalto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSalto)) {
            return false;
        }
        TipoSalto other = (TipoSalto) object;
        if ((this.idTipoSalto == null && other.idTipoSalto != null) || (this.idTipoSalto != null && !this.idTipoSalto.equals(other.idTipoSalto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoSalto[ idTipoSalto=" + idTipoSalto + " ]";
    }
    
}
