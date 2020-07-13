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
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    protected EnderecoPK enderecoPK;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Integer cep;
    private String cidade;
    private String estado;
    private String situacao;
    private Date dataInclusao;
    private Date dataAlteracao;
    private Pessoa pessoa;

    public Endereco() {
    }

    public Endereco(EnderecoPK enderecoPK) {
        this.enderecoPK = enderecoPK;
    }

    public Endereco(int idEndereco, String idUsuario, int idPessoa) {
        this.enderecoPK = new EnderecoPK(idEndereco, idUsuario, idPessoa);
    }

    public EnderecoPK getEnderecoPK() {
        return enderecoPK;
    }

    public void setEnderecoPK(EnderecoPK enderecoPK) {
        this.enderecoPK = enderecoPK;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enderecoPK != null ? enderecoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.enderecoPK == null && other.enderecoPK != null) || (this.enderecoPK != null && !this.enderecoPK.equals(other.enderecoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Endereco[ enderecoPK=" + enderecoPK + " ]";
    }
    
}
