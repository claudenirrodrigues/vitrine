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
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idPerfil;
    private String perfil;
    private Trace trace;
    private Collection<Usuario> usuarioCollection;

    public Perfil() {
    }

    public Perfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getperfil() {
        return perfil;
    }

    public void setperfil(String perfil) {
        this.perfil = perfil;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    protected Trace getTrace() {
		return trace;
	}

	protected void setTrace(Trace trace) {
		this.trace = trace;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Perfil[ idPerfil=" + idPerfil + " ]";
    }
    
}
