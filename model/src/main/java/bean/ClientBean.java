/**
 * 
 */
package bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import model.Cliente;
import model.Endereco;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webservice.CEPWebService;
import static java.lang.Long.parseLong;
import dao.DAO;
import bean.ServiceBean;

@Controller("clientBean")
@RequestScoped
@Named
public class ClientBean extends GenericBean implements Serializable {

	private static Cliente cliente = new Cliente();

	private static Class c = cliente.getClass();
	
	private char pessoa;

	@Autowired
	private static DAO<Cliente> clienteDao = new DAO<Cliente>();

	public ClientBean() {
		super(cliente, clienteDao, c, new Cliente());

	}

	@Override
	public void save() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message;
		setEntity(getNewEntity());
		try {
			Cliente cliente = (Cliente) getEntity();
			 saveEndereco(cliente);
			super.save();
			
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cliente cadastrado com sucesso.", " ");
			
			context.addMessage(null, message);
			setNewEntity(new Cliente());
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Desculpe não foi possível cadastrar.", "");
			context.addMessage(null, message);
		}
		
	}

	@Override
	public void update() {

		Cliente cliente = (Cliente) getEntity();
		updateEndereco(cliente);
		DAO<Cliente> cliDao = new DAO<Cliente>();
		cliDao.update(cliente);
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Cliente cadastrado com sucesso.", " ");
		context.addMessage(null, message);
	}

	@SuppressWarnings("unchecked")
	public void saveEndereco(Cliente cliente) {
		Endereco endereco = cliente.getEndereco();
		DAO<Endereco> enderecoDao = new DAO<Endereco>();
		enderecoDao.save(endereco);
	}

	@SuppressWarnings("unchecked")
	public void updateEndereco(Cliente cliente) {
		Endereco endereco = cliente.getEndereco();
		DAO<Endereco> enderecoDao = new DAO<Endereco>();
		enderecoDao.update(endereco);
	}
	
	public void completeEndereco(AjaxBehaviorEvent actionEvent)throws javax.faces.event.AbortProcessingException{
		System.out.println("Complete endereco");
		Cliente cliente = (Cliente) getNewEntity();
		Endereco endereco = cliente.getEndereco();
		endereco.findCep(actionEvent);
	}

	
	
	

}
