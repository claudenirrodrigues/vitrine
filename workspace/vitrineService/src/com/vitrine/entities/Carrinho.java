/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Familia
 */
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idUsuario;
    private Integer itemCarrinho;
    private Estoque estoque;
    private Integer quantidade;
    private Date dataFechamento;
    private Trace trace;

    public Carrinho() {
    }

    public Carrinho(String idUsuario, Integer itemCarrinho) {
        this.idUsuario = idUsuario;
        this.itemCarrinho = itemCarrinho;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getItemCarrinho() {
        return itemCarrinho;
    }

    public void setItemCarrinho(Integer itemCarrinho) {
        this.itemCarrinho = itemCarrinho;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    
    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Trace getTrace() {
		return trace;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        hash += (int) itemCarrinho;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrinho)) {
            return false;
        }
        Carrinho other = (Carrinho) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        if (this.itemCarrinho != other.itemCarrinho) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Carrinho[ idUsuario=" + idUsuario + ", itemCarrinho=" + itemCarrinho + " ]";
    }
    
}
