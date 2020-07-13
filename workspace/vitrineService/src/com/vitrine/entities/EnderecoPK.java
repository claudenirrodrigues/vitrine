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
public class EnderecoPK implements Serializable {

    private int idEndereco;
    private String idUsuario;
    private int idPessoa;

    public EnderecoPK() {
    }

    public EnderecoPK(int idEndereco, String idUsuario, int idPessoa) {
        this.idEndereco = idEndereco;
        this.idUsuario = idUsuario;
        this.idPessoa = idPessoa;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEndereco;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        hash += (int) idPessoa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnderecoPK)) {
            return false;
        }
        EnderecoPK other = (EnderecoPK) object;
        if (this.idEndereco != other.idEndereco) {
            return false;
        }
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        if (this.idPessoa != other.idPessoa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EnderecoPK[ idEndereco=" + idEndereco + ", idUsuario=" + idUsuario + ", idPessoa=" + idPessoa + " ]";
    }
    
}
