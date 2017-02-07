package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="login")
public class User {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "id")
	private Long id;

	private String username;

	private String password;

	private String email;

	@Transient
	private String passwordConfirmation;

	 @Column(name = "data")
	private Date datalogin = new Date();

	@Transient
	private static String logged;

	@Transient
	private boolean isLogged;

	public static boolean isLogged() {

		return logged != null;
	}

	// *************** Getters and Setters ****************************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;

	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		System.out.println("setPassword" + password);
		this.password = password;
	}

	public Date getDatalogin() {
		return datalogin;
	}

	public void setDatalogin(Date datalogin) {
		this.datalogin = datalogin;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static void setLogged(String logged) {
		User.logged = "logged";

	}

}