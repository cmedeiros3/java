/**
 * 
 */

package model;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import webservice.CEPWebService;

/**
* 
*/

@Entity
public class Endereco {

	@Id
	@GeneratedValue
	private Long id;

	private String rua;

	private int numero;

	private String cep = null;

	private String cidade;

	private String estado;

	private String bairro;

	private String complemento;

	private int verificador;

	// *************** Getters and Setters ****************************

	public Long getId() {
		return id;
	}

	public String getRua() {
		return rua;
	}

	public int getNumero() {
		return numero;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public void findCep(AjaxBehaviorEvent actionEvent)
			throws javax.faces.event.AbortProcessingException {

		String idInput = actionEvent.getComponent().getId();
		if (idInput.equals("cep")) {

			System.out.println("Encontra cep " + this.getCep());
			CEPWebService cepWS = new CEPWebService(this.getCep());

			if (cepWS.getResultado() == 1) {
				System.out.println("resultado cep");
				System.out.println(cepWS.getCidade());
				System.out.println(cepWS.getBairro());
				System.out.println(cepWS.getLogradouro());
				System.out.println(cepWS.getEstado());

				this.setRua(cepWS.getLogradouro());
				this.setEstado(cepWS.getEstado());
				this.setCidade(cepWS.getCidade());
				this.setBairro(cepWS.getBairro());
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Servidor não está respondendo",
								"Servidor não está respondendo"));
			}
		}
	}

	public int getVerificador() {
		return verificador;
	}

	public void setVerificador() {
		String aux=String.valueOf(this.cep);
		aux.concat(String.valueOf(this.numero));
		this.verificador = Integer.parseInt(aux);
	}

}
