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
public class PessoaPK implements Serializable {

    private String idUsuario;
    private Integer idPessoa;

    public PessoaPK() {
    }

    public PessoaPK(String idUsuario, Integer idPessoa) {
        this.idUsuario = idUsuario;
        this.idPessoa = idPessoa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    
    
}
