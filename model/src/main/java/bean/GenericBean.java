/**
 * 
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import model.Cliente;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Property;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.Long.parseLong;
import dao.DAO;

@ViewScoped
public class GenericBean<T> {

	private T entity;

	private T newEntity;

	public T getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(T newEntity) {
		this.newEntity = newEntity;
	}

	@Autowired
	private DAO<T> dao;

	private ArrayList<T> list = new ArrayList<T>();
	private ArrayList<T> filteredlist = new ArrayList<T>();
	private ArrayList<T> listField = new ArrayList<T>();

	private Long key;

	private boolean result;

	private Class c;

	private Long criteria;

	public GenericBean(T entity, DAO<T> dao, Class c, T newEntity) {
		super();
		this.entity = entity;
		this.dao = dao;
		this.result = false;
		this.c = c;
		this.newEntity = newEntity;

	}

	public void save() {
		
		dao.save(entity);
		/*this.newEntity = null;*/
	}
	
	
	
	public void resetNewEntity() {
		this.newEntity = null;
	}

	public void update() {
		dao.update(entity);
	}

	public void delete() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			setResult(dao.delete(entity));
			System.out.print("Cadastrado excluído ");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cadastro", "excluído.");
			context.addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Desculpe", "não foi possível excluir.");
			context.addMessage(null, message);
		}

	}

	/*public void reset() {
		RequestContext.getCurrentInstance().reset("form:panel");
	}*/

	public void findAll() {
		DetachedCriteria query = DetachedCriteria.forClass(c);
		list = (ArrayList<T>) dao.searchModels(query);
		
	}

	public void findById() {
		String id = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");
		setCriteria(parseLong(id));
		DetachedCriteria query = DetachedCriteria.forClass(c);
		query.add(Restrictions.eq("id", criteria));
		this.entity = dao.searchModel(query);
	}

	public ArrayList<T> findField(String field) {
		System.out.println("Find field");
		DetachedCriteria query = DetachedCriteria.forClass(c);
		query.setProjection(Projections.distinct(Projections.property(field)));
		// listField =(ArrayList<T>) dao.searchModels(query);
		return (ArrayList<T>) dao.searchModels(query);

	}

	public void newEntity() {

	}

	// *************** Getters and Setters ****************************

	public T getEntity() {

		return entity;
	}

	public DAO<T> getDao() {
		return dao;
	}

	public ArrayList<T> getList() {
		
		findAll();
		return list;
	}

	public Long getKey() {
		return key;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public void setDao(DAO<T> dao) {
		this.dao = dao;
	}

	public void setList(ArrayList<T> list) {
		this.list = list;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public void setListField(ArrayList<T> listField) {
		this.listField = listField;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Long getCriteria() {
		return criteria;
	}

	public void setCriteria(Long criteria) {
		this.criteria = criteria;
	}

	public ArrayList<T> getFilteredlist() {

		return filteredlist;
	}

	public void setFilteredlist(ArrayList<T> filteredlist) {
		this.filteredlist = filteredlist;
	}

}
