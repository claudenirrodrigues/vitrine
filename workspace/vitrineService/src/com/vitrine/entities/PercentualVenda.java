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
public class PercentualVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    protected PercentualVendaPK percentualVendaPK;
    private Date dataInicio;
    private Date dataFinal;
    private Float percentualVarejo;
    private Float percentualAtacado;
    private Float percentualPromocional;
    private Float percentualConsignado;

    public PercentualVenda() {
    }

    public PercentualVenda(PercentualVendaPK percentualVendaPK) {
        this.percentualVendaPK = percentualVendaPK;
    }

    public PercentualVenda(int idPercentualVenda, int tipoPercentual) {
        this.percentualVendaPK = new PercentualVendaPK(idPercentualVenda, tipoPercentual);
    }

    public PercentualVendaPK getPercentualVendaPK() {
        return percentualVendaPK;
    }

    public void setPercentualVendaPK(PercentualVendaPK percentualVendaPK) {
        this.percentualVendaPK = percentualVendaPK;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Float getPercentualVarejo() {
        return percentualVarejo;
    }

    public void setPercentualVarejo(Float percentualVarejo) {
        this.percentualVarejo = percentualVarejo;
    }

    public Float getPercentualAtacado() {
        return percentualAtacado;
    }

    public void setPercentualAtacado(Float percentualAtacado) {
        this.percentualAtacado = percentualAtacado;
    }

    public Float getPercentualPromocional() {
        return percentualPromocional;
    }

    public void setPercentualPromocional(Float percentualPromocional) {
        this.percentualPromocional = percentualPromocional;
    }

    public Float getPercentualConsignado() {
        return percentualConsignado;
    }

    public void setPercentualConsignado(Float percentualConsignado) {
        this.percentualConsignado = percentualConsignado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (percentualVendaPK != null ? percentualVendaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PercentualVenda)) {
            return false;
        }
        PercentualVenda other = (PercentualVenda) object;
        if ((this.percentualVendaPK == null && other.percentualVendaPK != null) || (this.percentualVendaPK != null && !this.percentualVendaPK.equals(other.percentualVendaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PercentualVenda[ percentualVendaPK=" + percentualVendaPK + " ]";
    }
    
}
