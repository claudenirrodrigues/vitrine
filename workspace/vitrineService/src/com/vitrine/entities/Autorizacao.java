package com.vitrine.entities;

public class Autorizacao {
	
	private static final long serialVersionUID = 1L;
	private String idUsuario;
    private String authToken;
    private String serviceKey;
    private Trace trace;
	
    
    
    public Autorizacao() {
	
    }

	public Autorizacao(String idUsuario, String authToken, String serviceKey) {
		super();
		this.idUsuario = idUsuario;
		this.authToken = authToken;
		this.serviceKey = serviceKey;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	public Trace getTrace() {
		return trace;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        hash += (authToken != null ? authToken.hashCode() : 0);
        hash += (serviceKey != null ? serviceKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autorizacao)) {
            return false;
        }
        Autorizacao other = (Autorizacao) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        if ((this.authToken == null && other.authToken != null) || (this.authToken != null && !this.authToken.equals(other.authToken))) {
            return false;
        }
        if ((this.serviceKey == null && other.serviceKey != null) || (this.serviceKey != null && !this.serviceKey.equals(other.serviceKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Autorizacao[ idUsuario=" + idUsuario + ", authToken=" + authToken + ", serviceKey=" + serviceKey + " ]";
    }
    
    
    

}
