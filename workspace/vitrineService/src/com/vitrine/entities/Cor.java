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
public class Cor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idCor;
    private String cor;
    private Collection<Estoque> estoqueCollection;

    public Cor() {
    }

    public Cor(Integer idCor) {
        this.idCor = idCor;
    }

    public Integer getIdCor() {
        return idCor;
    }

    public void setIdCor(Integer idCor) {
        this.idCor = idCor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Collection<Estoque> getEstoqueCollection() {
        return estoqueCollection;
    }

    public void setEstoqueCollection(Collection<Estoque> estoqueCollection) {
        this.estoqueCollection = estoqueCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCor != null ? idCor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cor)) {
            return false;
        }
        Cor other = (Cor) object;
        if ((this.idCor == null && other.idCor != null) || (this.idCor != null && !this.idCor.equals(other.idCor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cor[ idCor=" + idCor + " ]";
    }
    
}
