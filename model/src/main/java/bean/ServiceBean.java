package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.stereotype.Controller;

import webservice.CEPWebService;

@Controller("ServiceBean")
@RequestScoped
@Named
public class ServiceBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3485786345343999101L;
	private String cep=null;
	private String estado;
	private String cidade;
	private String bairro;
	private String logradouro;
	private int numero;
	private String complemento;
	
	public void encontraCEP(){
		System.out.println("Encontra cep "+getCep());
		CEPWebService cepWS = new CEPWebService(getCep());
		
		if(cepWS.getResultado()==1){
			setLogradouro(cepWS.getLogradouro());
			setEstado(cepWS.getEstado());
			setCidade(cepWS.getCidade());
			setBairro(cepWS.getBairro());
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Servidor não está respondendo","Servidor não está respondendo"));
		}
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
