/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Familia
 */
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idPagamento;
    private Date dataAlteracao;
    private String pagamento;
    private Float taxa;
    private Collection<PedidoCompra> pedidocompraCollection;

    public Pagamento() {
    }

    public Pagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public Float getTaxa() {
        return taxa;
    }

    public void setTaxa(Float taxa) {
        this.taxa = taxa;
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
        hash += (idPagamento != null ? idPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.idPagamento == null && other.idPagamento != null) || (this.idPagamento != null && !this.idPagamento.equals(other.idPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pagamento[ idPagamento=" + idPagamento + " ]";
    }
    
}
