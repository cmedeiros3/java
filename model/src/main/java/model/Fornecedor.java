package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fornecedor {

	@Id
	 @GeneratedValue
	 private Long id;

	 private String nome;

	 private String cnpj;

	 private String telefone;

	 private String celular;

	 @ManyToOne(cascade=CascadeType.ALL)
	 private Endereco endereco = new Endereco();
	 
	 private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	 
	 
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null) {
	        return false;
	    }
	    if (!(obj instanceof Fornecedor)) {
	        return false;
	    }
	   Fornecedor other = (Fornecedor) obj;
	    if (id == null) {
	        if (other.id != null) {
	            return false;
	        }
	    } else if (!id.equals(other.id)) {
	        return false;
	    }
	    return true;
	}
	 
}
