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
public class Numeracao implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idNumeracao;
    private Integer numeracao;
    private Collection<Estoque> estoqueCollection;

    public Numeracao() {
    }

    public Numeracao(Integer idNumeracao) {
        this.idNumeracao = idNumeracao;
    }

    public Integer getIdNumeracao() {
        return idNumeracao;
    }

    public void setIdNumeracao(Integer idNumeracao) {
        this.idNumeracao = idNumeracao;
    }

    public Integer getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(Integer numeracao) {
        this.numeracao = numeracao;
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
        hash += (idNumeracao != null ? idNumeracao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Numeracao)) {
            return false;
        }
        Numeracao other = (Numeracao) object;
        if ((this.idNumeracao == null && other.idNumeracao != null) || (this.idNumeracao != null && !this.idNumeracao.equals(other.idNumeracao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Numeracao[ idNumeracao=" + idNumeracao + " ]";
    }
    
}
