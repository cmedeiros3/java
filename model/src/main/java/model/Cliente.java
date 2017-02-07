/**
 * 
 */

package model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
Entity for Client User Case 
*/


@Entity
public class Cliente {


 private Long id;

 private String nome;

 private String rg;

 private String cpf;
 
 private String cnpj;



private String telefone;

 private String celular;


 
 private Endereco endereco = new Endereco();
 
 private String email;

 // *************** Getters and Setters ****************************
 
 @Id
 @GeneratedValue
 public Long getId() {
     return id;
 }
 
 @ManyToOne(cascade=CascadeType.ALL)
 public Endereco getEndereco() {
	
     return endereco;
 }
 
 public String getNome() {
     return nome;
 }



   public String getRg() {
	return rg;
}

   public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

public void setRg(String rg) {
	this.rg = rg;
}



public String getCpf() {
	return cpf;
}



public void setCpf(String cpf) {
	this.cpf = cpf;
}





 public String getTelefone() {
     return telefone;
 }

 public String getCelular() {
     return celular;
 }




 public void setNome(String nome) {
     this.nome = nome;
 }


 

   public void setEndereco(Endereco endereco) {
       this.endereco = endereco;
      
   }

 public void setTelefone(String telefone) {
     this.telefone = telefone;
 }

 public void setCelular(String celular) {
     this.celular = celular;
 }



 public void setId(Long id) {
     this.id = id;
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
    if (!(obj instanceof Cliente)) {
        return false;
    }
   Cliente other = (Cliente) obj;
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
