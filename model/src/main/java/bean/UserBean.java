/**
 * 
 */
package bean;

import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Cliente;
import model.User;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sun.mail.util.MailSSLSocketFactory;

import converter.PasswordConverter;
import static java.lang.Long.parseLong;
import dao.DAO;
import dao.UserDao;

@Controller
@ViewScoped
public class UserBean extends GenericBean implements Serializable {

	static final long serialVersionUID = -8780407253943723401L;

	private static User user = new User();

	private static Class c = user.getClass();

	private String username;

	private String password;

	@Autowired
	private static DAO<User> userDao = new DAO<User>();

	public UserBean() {
		super(user, userDao, c, new User());
		System.out.println("user "+user);
		System.out.println("userDAO "+userDao);
		

	}

	public String login() {

		System.out.println("Login");
		FacesContext context = FacesContext.getCurrentInstance();
		UserDao userdao = new UserDao();
		System.out.println("password login"+this.password);
		 encryptLogin();
		 System.out.println("password login"+this.password);
		if (userdao.searchLogin(this.username, this.password)) {
			// .g getDao()).searchLogin(this.username, this.password)) {
			User.setLogged("logged");
			return "/pages/index/index?faces-redirect=true&includeViewParams=true";

		} else {
			FacesMessage message = new FacesMessage("Usuario/senha invalida!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);

		}
		return null;
	}

	public String logout() {

		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		User entidade = (User) getEntity();
		entidade.setUsername(null);
		return "/pages/login/Login?faces-redirect=true";
	}
	
	
	

	public boolean alreadyRegistered() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message;
		User user = (User) getEntity();
		boolean validator = false;
		UserDao userdao = new UserDao();
		if (userdao.usernameValidator(user.getUsername())) {
			validator = true;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Usuário já cadastrado", "");
			context.addMessage(null, message);
		}
		if (userdao.emailValidator(user.getEmail())) {
			validator = true;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Email já cadastrado", "");
			context.addMessage(null, message);
		}

		return validator;

	}
	
	public void encrypt(){
		User user=(User) getEntity();
		user.setPassword(PasswordConverter.md5(user.getPassword()));
	
	}
	
	public void encryptLogin(){
		this.password=PasswordConverter.md5(this.password);
	}

	@Override
	public void save() {
		 
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message;
		
		setEntity(getNewEntity());
	
		
		try {
			User user = (User) getEntity();
			encrypt();	
			if (alreadyRegistered() == false) {
				super.save();
				String username = user.getUsername();
				System.out
						.print("Cadastrado com sucesso " + user.getUsername());
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuário cadastrado com sucesso.", "");
				context.addMessage(null, message);
				setNewEntity(new User());
				 sendEmail(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Desculpe não foi possível cadastrar.", "");
			context.addMessage(null, message);
		}

	}

	public void remember() {

	}

	public void sendEmail(User user) {
		final String from = "innovare.solucoes.ti@gmail.com";
		final String pass="*sD7epn0qscn*";
		Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true"); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.socketFactory.port", "25");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");

        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });

        mailSession.setDebug(true); // Enable the debug mode

        Message msg = new MimeMessage( mailSession );

        //--[ Set the FROM, TO, DATE and SUBJECT fields
        try {
            msg.setFrom( new InternetAddress( from ) );
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(user.getEmail()) );
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //msg.setSentDate(new Date());
        try {
            msg.setSubject( "Bem-vindo ao Sistema Innovare!" );
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //--[ Create the body of the mail
        try {
        	msg.setContent(
					"<h1>Seu cadastro no sistema Innovare foi recebido com sucesso!</h1>",
					"text/html");
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //--[ Ask the Transport class to send our mail message
        try {
            Transport.send( msg );
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }
		
		
		
		
		/*
		System.out.println("email"+user.getEmail());
		String host = "localhost";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			}
		  });
		try {
			 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user.getEmail()));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}		
*/		
	/*	Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "8085");
		
		 properties.setProperty("mail.transport.protocol", "smtp");
		 properties.setProperty("mail.host", "smtp.gmail.com");
		 
		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					user.getEmail()));
			message.setSubject("Bem-vindo ao Sistema Innovare!");
			message.setContent(
					"<h1>Seu cadastro no sistema Innovare foi recebido com sucesso!</h1>",
					"text/html");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
*/
	// *************** Getters and Setters ****************************

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {

		this.password = password;
	}

}
