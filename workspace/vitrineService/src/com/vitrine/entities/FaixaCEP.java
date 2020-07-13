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
public class FaixaCEP implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idFaixaCep;
    private Integer cepInicial;
    private Integer cepFinal;
    private Collection<PessoaFaixaCEP> pessoafaixacepCollection;

    public FaixaCEP() {
    }

    public FaixaCEP(Integer idFaixaCep) {
        this.idFaixaCep = idFaixaCep;
    }

    public Integer getIdFaixaCep() {
        return idFaixaCep;
    }

    public void setIdFaixaCep(Integer idFaixaCep) {
        this.idFaixaCep = idFaixaCep;
    }

    public Integer getCepInicial() {
        return cepInicial;
    }

    public void setCepInicial(Integer cepInicial) {
        this.cepInicial = cepInicial;
    }

    public Integer getCepFinal() {
        return cepFinal;
    }

    public void setCepFinal(Integer cepFinal) {
        this.cepFinal = cepFinal;
    }

    public Collection<PessoaFaixaCEP> getPessoafaixacepCollection() {
        return pessoafaixacepCollection;
    }

    public void setPessoafaixacepCollection(Collection<PessoaFaixaCEP> pessoafaixacepCollection) {
        this.pessoafaixacepCollection = pessoafaixacepCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFaixaCep != null ? idFaixaCep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaixaCEP)) {
            return false;
        }
        FaixaCEP other = (FaixaCEP) object;
        if ((this.idFaixaCep == null && other.idFaixaCep != null) || (this.idFaixaCep != null && !this.idFaixaCep.equals(other.idFaixaCep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Faixacep[ idFaixaCep=" + idFaixaCep + " ]";
    }
    
}
