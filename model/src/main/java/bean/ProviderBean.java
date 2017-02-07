package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import model.Endereco;
import model.Fornecedor;

import org.springframework.beans.factory.annotation.Autowired;

import bean.GenericBean;
import dao.DAO;

public class ProviderBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = -91047186223486170L;

	private static Fornecedor fornecedor = new Fornecedor();

	private static Class c = fornecedor.getClass();

	@Autowired
	private static DAO<Fornecedor> fornecedorDao = new DAO<Fornecedor>();

	public ProviderBean() {
		super(fornecedor, fornecedorDao, c, new Fornecedor());

	}

	@Override
	public void save() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message;
		setEntity(getNewEntity());
		try {
			Fornecedor fornecedor = (Fornecedor) getEntity();
			saveEndereco(fornecedor);
			super.save();

			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Fornecedor cadastrado com sucesso.", " ");

			context.addMessage(null, message);
			setNewEntity(new Fornecedor());
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Desculpe não foi possível cadastrar.", "");
			context.addMessage(null, message);
		}

	}

	@Override
	public void update() {

		Fornecedor fornecedor = (Fornecedor) getEntity();
		updateEndereco(fornecedor);
		DAO<Fornecedor> cliDao = new DAO<Fornecedor>();
		cliDao.update(fornecedor);
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Fornecedor cadastrado com sucesso.", " ");
		context.addMessage(null, message);
	}

	@SuppressWarnings("unchecked")
	public void saveEndereco(Fornecedor fornecedor) {
		Endereco endereco = fornecedor.getEndereco();
		DAO<Endereco> enderecoDao = new DAO<Endereco>();
		enderecoDao.save(endereco);
	}

	@SuppressWarnings("unchecked")
	public void updateEndereco(Fornecedor fornecedor) {
		Endereco endereco = fornecedor.getEndereco();
		DAO<Endereco> enderecoDao = new DAO<Endereco>();
		enderecoDao.update(endereco);
	}

	public void completeEndereco(AjaxBehaviorEvent actionEvent)
			throws javax.faces.event.AbortProcessingException {
		System.out.println("Complete endereco");
		Fornecedor fornecedor = (Fornecedor) getNewEntity();
		Endereco endereco = fornecedor.getEndereco();
		endereco.findCep(actionEvent);
	}
}
