package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import model.Endereco;
import model.Endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webservice.CEPWebService;
import dao.DAO;


@Controller("enderecoBean")
@RequestScoped
@Named
public class EnderecoBean extends GenericBean implements Serializable {

	private static Endereco endereco = new Endereco();

	private static Class c = endereco.getClass();

	@Autowired
	private static DAO<Endereco> EnderecoDao = new DAO<Endereco>();

	public EnderecoBean() {
		super(endereco, EnderecoDao, c, new Endereco());

	}

	@Override
	public void save() {
		setEntity(getNewEntity());
		endereco.setVerificador();
		super.save();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Endereco  cadastrado com sucesso.", "");
		context.addMessage(null, message);

	}
	public void update(){
	
		super.update();
	}
	
	
}
	
