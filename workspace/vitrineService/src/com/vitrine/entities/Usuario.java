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
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

    private Collection<PedidoCompra> pedidocompraCollection;
    private Collection<Pessoa> pessoaCollection;
    private Collection<Carrinho> carrinhoCollection;
    private String idUsuario;
    private String senha;
    private Collection<Perfil> perfilCollection;
    private Trace trace;
    
    /**
     * Atributos utilizados para armazenar dados de acesso e segurança do Usuario
     */
    private String rawResponse;
    private String accessToken;
    private String tokenType;
    private Integer expiresIn;
    private String refreshToken;
    private String scope;
    private String openIdToken;
    private Collection<Autorizacao>  authTokenCollection;
    
    public Usuario() {
    }

    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    public Collection<Perfil> getPerfilCollection() {
        return perfilCollection;
    }

    public void setPerfilCollection(Collection<Perfil> perfilCollection) {
        this.perfilCollection = perfilCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuario[ idUsuario=" + idUsuario + " ]";
    }

    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
    }

    public Collection<PedidoCompra> getPedidocompraCollection() {
        return pedidocompraCollection;
    }

    public void setPedidocompraCollection(Collection<PedidoCompra> pedidocompraCollection) {
        this.pedidocompraCollection = pedidocompraCollection;
    }

	public Trace getTrace() {
		return trace;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	public Collection<Carrinho> getCarrinhoCollection() {
		return carrinhoCollection;
	}

	public void setCarrinhoCollection(Collection<Carrinho> carrinhoCollection) {
		this.carrinhoCollection = carrinhoCollection;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getRawResponse() {
		return rawResponse;
	}

	public void setRawResponse(String rawResponse) {
		this.rawResponse = rawResponse;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getOpenIdToken() {
		return openIdToken;
	}

	public void setOpenIdToken(String openIdToken) {
		this.openIdToken = openIdToken;
	}

	public Collection<Autorizacao> getAuthTokenCollection() {
		return authTokenCollection;
	}

	public void setAuthTokenCollection(Collection<Autorizacao> authTokenCollection) {
		this.authTokenCollection = authTokenCollection;
	}

}
