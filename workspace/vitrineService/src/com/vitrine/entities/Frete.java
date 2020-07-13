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
public class Frete implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idFrete;
    private String frete;
    private Collection<PedidoCompra> pedidocompraCollection;

    public Frete() {
    }

    public Frete(Integer idFrete) {
        this.idFrete = idFrete;
    }

    public Integer getIdFrete() {
        return idFrete;
    }

    public void setIdFrete(Integer idFrete) {
        this.idFrete = idFrete;
    }

    public String getFrete() {
        return frete;
    }

    public void setFrete(String frete) {
        this.frete = frete;
    }

    public Collection<PedidoCompra> getPedidocompraCollection() {
        return pedidocompraCollection;
    }

    public void setPedidocompraCollection(Collection<PedidoCompra> pedidocompraCollection) {
        this.pedidocompraCollection = pedidocompraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFrete != null ? idFrete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frete)) {
            return false;
        }
        Frete other = (Frete) object;
        if ((this.idFrete == null && other.idFrete != null) || (this.idFrete != null && !this.idFrete.equals(other.idFrete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Frete[ idFrete=" + idFrete + " ]";
    }
    
}
