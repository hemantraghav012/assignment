/**
 * 
 */
package com.digilytics.javaAssignment.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author hemant
 *
 */
@Entity
@Table(name = "userRole", uniqueConstraints = @UniqueConstraint(columnNames = { "userId","roleId" }))

public class UserRole implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5322028341319745559L;
	private Long id;
	private Users users;	
	private Role role;
	
	
	public UserRole() {
		super();
	}
	public UserRole(Long id, Users users, Role role) {
		super();
		this.id = id;
		this.users = users;
		this.role = role;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	public Role getRole() {
		return role;
	}	
	
	public void setRole(Role role) {
		this.role = role;
	}

	
}

