package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author sarath
 */
@Entity(name = "user")
public class User extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id", nullable = false)
	private Integer id;

	@NotEmpty
	@Column(name = "username")
	private String username;

	@NotEmpty
	@Column(name = "password")
	private String password;

	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 25)
	@OneToMany(cascade =  CascadeType.ALL,fetch = FetchType.LAZY)  //EAGER
	@JoinTable(name = "user_practices", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "prac_id", referencedColumnName = "id") })
	public Set<Practice> practices;

	
	@OneToOne( cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	public Role role;

	@Column(name = "effective_year")
	private Integer effectiveYear;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 */
	public User(final Integer id) {
		super();
		this.id = id;
	}

	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public  Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the effectiveYear
	 */
	public Integer getEffectiveYear() {
		return effectiveYear;
	}

	/**
	 * @param effectiveYear
	 *            the effectiveYear to set
	 */
	public void setEffectiveYear(Integer effectiveYear) {
		this.effectiveYear = effectiveYear;
	}

	/**
	 * @return
	 */
	public Set<Practice> getPractices() {
		return practices;
	}

	/**
	 * @param practices
	 */
	public void setPractices(Set<Practice> practices) {
		this.practices = practices;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.springboot.model.User[ id=" + id + "]";
	}

}
