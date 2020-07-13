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
public class Trace implements Serializable {

	private static final long serialVersionUID = 1L;

    private String situacao;
    private Date dataInclusao;
    private Date dataAlteracao;
    
    public Trace() {
    }
    
    public Trace(String situacao, Date dataInclusao, Date dataAlteracao) {
    	this.situacao = situacao;
    	this.dataInclusao = dataInclusao;
    	this.dataAlteracao = dataAlteracao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (situacao != null ? situacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trace)) {
            return false;
        }
        Trace other = (Trace) object;
        if ((this.situacao == null && other.situacao != null) || (this.situacao != null && !this.situacao.equals(other.situacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rastreabilidade[ situacao=" + situacao + " ]";
    }

}
