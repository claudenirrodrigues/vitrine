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
public class Tamanho implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idTamanho;
    private String tamanho;
    private Collection<Estoque> estoqueCollection;

    public Tamanho() {
    }

    public Tamanho(Integer idTamanho) {
        this.idTamanho = idTamanho;
    }

    public Integer getIdTamanho() {
        return idTamanho;
    }

    public void setIdTamanho(Integer idTamanho) {
        this.idTamanho = idTamanho;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
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
        hash += (idTamanho != null ? idTamanho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tamanho)) {
            return false;
        }
        Tamanho other = (Tamanho) object;
        if ((this.idTamanho == null && other.idTamanho != null) || (this.idTamanho != null && !this.idTamanho.equals(other.idTamanho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tamanho[ idTamanho=" + idTamanho + " ]";
    }
    
}
