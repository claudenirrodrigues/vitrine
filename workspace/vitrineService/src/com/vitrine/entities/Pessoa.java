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
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idUsuario;
    private Integer idPessoa;
    private String nome;
    private String sobrenome;
    private String nomeExibido;
    private String complemento;
    private String emailPrincipal;
    private String emailSecundario;
    private Integer dddContato;
    private Integer foneContato;
    private Integer dddResidencial;
    private Integer foneResidencial;
    private Integer dddComercial;
    private Integer foneComercial;
    private Integer dddCelular;
    private Integer foneCelular;
    private Integer cpfCnpj;
    private Integer cnpjFilial;
    private Integer cpfCnpjDigito;
    private String newslleter;
    private String cupomPromocional;
    private Date dataInclusao;
    private Date dataAlteracao;
    private Usuario usuario;
    private Collection<Endereco> enderecoCollection;
    private Collection<PessoaFaixaCEP> pessoafaixacepCollection;

    public Pessoa() {
    }

    

    public Pessoa(String idUsuario, int idPessoa) {
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
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}



	public String getNomeExibido() {
		return nomeExibido;
	}



	public void setNomeExibido(String nomeExibido) {
		this.nomeExibido = nomeExibido;
	}



	public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEmailPrincipal() {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal) {
        this.emailPrincipal = emailPrincipal;
    }

    public String getEmailSecundario() {
        return emailSecundario;
    }

    public void setEmailSecundario(String emailSecundario) {
        this.emailSecundario = emailSecundario;
    }

    public Integer getDddContato() {
        return dddContato;
    }

    public void setDddContato(Integer dddContato) {
        this.dddContato = dddContato;
    }

    public Integer getFoneContato() {
        return foneContato;
    }

    public void setFoneContato(Integer foneContato) {
        this.foneContato = foneContato;
    }

    public Integer getDddResidencial() {
        return dddResidencial;
    }

    public void setDddResidencial(Integer dddResidencial) {
        this.dddResidencial = dddResidencial;
    }

    public Integer getFoneResidencial() {
        return foneResidencial;
    }

    public void setFoneResidencial(Integer foneResidencial) {
        this.foneResidencial = foneResidencial;
    }

    public Integer getDddComercial() {
        return dddComercial;
    }

    public void setDddComercial(Integer dddComercial) {
        this.dddComercial = dddComercial;
    }

    public Integer getFoneComercial() {
        return foneComercial;
    }

    public void setFoneComercial(Integer foneComercial) {
        this.foneComercial = foneComercial;
    }

    public Integer getDddCelular() {
        return dddCelular;
    }

    public void setDddCelular(Integer dddCelular) {
        this.dddCelular = dddCelular;
    }

    public Integer getFoneCelular() {
        return foneCelular;
    }

    public void setFoneCelular(Integer foneCelular) {
        this.foneCelular = foneCelular;
    }

    public Integer getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(Integer cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Integer getCnpjFilial() {
        return cnpjFilial;
    }

    public void setCnpjFilial(Integer cnpjFilial) {
        this.cnpjFilial = cnpjFilial;
    }

    public Integer getCpfCnpjDigito() {
        return cpfCnpjDigito;
    }

    public void setCpgCnpjDigito(Integer cpgCnpjDigito) {
        this.cpfCnpjDigito = cpgCnpjDigito;
    }

    public String getNewslleter() {
        return newslleter;
    }

    public void setNewslleter(String newslleter) {
        this.newslleter = newslleter;
    }

    public String getCupomPromocional() {
        return cupomPromocional;
    }

    public void setCupomPromocional(String cupomPromocional) {
        this.cupomPromocional = cupomPromocional;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<Endereco> getEnderecoCollection() {
        return enderecoCollection;
    }

    public void setEnderecoCollection(Collection<Endereco> enderecoCollection) {
        this.enderecoCollection = enderecoCollection;
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
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        hash += (int) idPessoa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
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
        return "entities.Pessoa[ idUsuario=" + idUsuario + ", idPessoa=" + idPessoa + " ]";
    }
    
}
