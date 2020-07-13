/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities;

import java.io.Serializable;

/**
 *
 * @author Familia
 */
public class PercentualVendaPK implements Serializable {

    private int idPercentualVenda;
    private int tipoPercentual;

    public PercentualVendaPK() {
    }

    public PercentualVendaPK(int idPercentualVenda, int tipoPercentual) {
        this.idPercentualVenda = idPercentualVenda;
        this.tipoPercentual = tipoPercentual;
    }

    public int getIdPercentualVenda() {
        return idPercentualVenda;
    }

    public void setIdPercentualVenda(int idPercentualVenda) {
        this.idPercentualVenda = idPercentualVenda;
    }

    public int getTipoPercentual() {
        return tipoPercentual;
    }

    public void setTipoPercentual(int tipoPercentual) {
        this.tipoPercentual = tipoPercentual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPercentualVenda;
        hash += (int) tipoPercentual;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PercentualVendaPK)) {
            return false;
        }
        PercentualVendaPK other = (PercentualVendaPK) object;
        if (this.idPercentualVenda != other.idPercentualVenda) {
            return false;
        }
        if (this.tipoPercentual != other.tipoPercentual) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PercentualVendaPK[ idPercentualVenda=" + idPercentualVenda + ", tipoPercentual=" + tipoPercentual + " ]";
    }
    
}
