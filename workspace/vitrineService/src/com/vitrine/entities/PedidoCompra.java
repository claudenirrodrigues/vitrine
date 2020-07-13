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
public class PedidoCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idPedidoCompra;
    private Collection<Carrinho> carrinhoCollection;
    private Float valorFrete;
    private Float valorCompra;
    private Float valorCustoTotal;
    private Float valorLucroLiquido;
    private Date dataPedido;
    private Date dataFechamento;
    private Integer parcelas;
    private Frete idFrete;
    private Pagamento idPagamento;
    
    
    public PedidoCompra() {
    }

    public PedidoCompra(int idPedidoCompra) {
        this.idPedidoCompra = idPedidoCompra;
    }

    public Integer getIdPedidoCompra() {
        return idPedidoCompra;
    }

    public void setIdPedidoCompra(Integer idPedidoCompra) {
        this.idPedidoCompra = idPedidoCompra;
    }

    public Collection<Carrinho> getCarrinhoCollection() {
        return carrinhoCollection;
    }

    public void setCarrinhoCollection(Collection<Carrinho> carrinhoCollection) {
        this.carrinhoCollection = carrinhoCollection;
    }

    public Float getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Float valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Float getValorCustoTotal() {
        return valorCustoTotal;
    }

    public void setValorCustoTotal(Float valorCustoTotal) {
        this.valorCustoTotal = valorCustoTotal;
    }

    public Float getValorLucroLiquido() {
        return valorLucroLiquido;
    }

    public void setValorLucroLiquido(Float valorLucroLiquido) {
        this.valorLucroLiquido = valorLucroLiquido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Frete getIdFrete() {
        return idFrete;
    }

    public void setIdFrete(Frete idFrete) {
        this.idFrete = idFrete;
    }

    public Pagamento getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Pagamento idPagamento) {
        this.idPagamento = idPagamento;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPedidoCompra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoCompra)) {
            return false;
        }
        PedidoCompra other = (PedidoCompra) object;
        if (this.idPedidoCompra != other.idPedidoCompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pedidocompra[ idPedidoCompra=" + idPedidoCompra + " ]";
    }
    
}
