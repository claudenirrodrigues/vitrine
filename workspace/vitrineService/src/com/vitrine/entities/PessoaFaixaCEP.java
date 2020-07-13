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
public class PessoaFaixaCEP implements Serializable {

    private static final long serialVersionUID = 1L;
    protected PessoaFaixaCEPPK pessoafaixacepPK;
    private Date dataInclusao;
    private Date dataAlteracao;
    private FaixaCEP faixacep;
    private Pessoa pessoa;

    public PessoaFaixaCEP() {
    }

    public PessoaFaixaCEP(PessoaFaixaCEPPK pessoafaixacepPK) {
        this.pessoafaixacepPK = pessoafaixacepPK;
    }

    public PessoaFaixaCEP(String idUsuario, int idPessoa, int idFaixaCep) {
        this.pessoafaixacepPK = new PessoaFaixaCEPPK(idUsuario, idPessoa, idFaixaCep);
    }

    public PessoaFaixaCEPPK getPessoafaixacepPK() {
        return pessoafaixacepPK;
    }

    public void setPessoafaixacepPK(PessoaFaixaCEPPK pessoafaixacepPK) {
        this.pessoafaixacepPK = pessoafaixacepPK;
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

    public FaixaCEP getFaixacep() {
        return faixacep;
    }

    public void setFaixacep(FaixaCEP faixacep) {
        this.faixacep = faixacep;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoafaixacepPK != null ? pessoafaixacepPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaFaixaCEP)) {
            return false;
        }
        PessoaFaixaCEP other = (PessoaFaixaCEP) object;
        if ((this.pessoafaixacepPK == null && other.pessoafaixacepPK != null) || (this.pessoafaixacepPK != null && !this.pessoafaixacepPK.equals(other.pessoafaixacepPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pessoafaixacep[ pessoafaixacepPK=" + pessoafaixacepPK + " ]";
    }
    
}
