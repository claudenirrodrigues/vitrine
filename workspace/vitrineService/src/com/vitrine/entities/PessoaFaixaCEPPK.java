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
public class PessoaFaixaCEPPK implements Serializable {

    private String idUsuario;
    private int idPessoa;
    private int idFaixaCep;

    public PessoaFaixaCEPPK() {
    }

    public PessoaFaixaCEPPK(String idUsuario, int idPessoa, int idFaixaCep) {
        this.idUsuario = idUsuario;
        this.idPessoa = idPessoa;
        this.idFaixaCep = idFaixaCep;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdFaixaCep() {
        return idFaixaCep;
    }

    public void setIdFaixaCep(int idFaixaCep) {
        this.idFaixaCep = idFaixaCep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        hash += (int) idPessoa;
        hash += (int) idFaixaCep;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaFaixaCEPPK)) {
            return false;
        }
        PessoaFaixaCEPPK other = (PessoaFaixaCEPPK) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        if (this.idPessoa != other.idPessoa) {
            return false;
        }
        if (this.idFaixaCep != other.idFaixaCep) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PessoafaixacepPK[ idUsuario=" + idUsuario + ", idPessoa=" + idPessoa + ", idFaixaCep=" + idFaixaCep + " ]";
    }
    
}
