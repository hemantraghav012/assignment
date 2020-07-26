/**
 * 
 */
package com.digilytics.javaAssignment.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hemant
 *
 */

@Entity
@Table(name = "users")
public class Users implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3942240782948912419L;
	
	private Integer id;
	@Email(message = "{user.email.invalid}")
	@NotEmpty(message = "Please enter email")	  
	private String emailId;
	private String name;
	private String password;
	private Set<UserRole> userRole = new HashSet<>(0);
	/**
	 * 
	 */
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param emailId
	 * @param name
	 * @param password
	 * @param userRole
	 */
	public Users(Integer id, String emailId, String name, String password, Set<UserRole> userRole) {
		this.id = id;
		this.emailId = emailId;
		this.name = name;
		this.password = password;
		this.userRole = userRole;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId",unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="emailId",unique = true,nullable = false)
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "users")
	public Set<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	
	
	
	

}
