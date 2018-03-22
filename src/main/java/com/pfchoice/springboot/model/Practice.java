package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 *
 * @author sarath
 */
@Entity
@Table(name = "practice")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Practice extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@Size(min = 2, max = 50, message = "The code must be between {min} and {max} characters long")
	@Column(name = "name")
	private String name;

	
	@Size(min = 2, max = 10, message = "The code must be between {min} and {max} characters long")
	@Column(name = "short_name")
	private String shortName;
	
	
	@Column(name = "NPI")
	private String npi;
	
	
	@Column(name = "tax_id")
	private Integer taxId;
	
	
	@Size( max = 100, message = "The code must be between {min} and {max} characters long")
	@Column(name = "clearing_house_url")
	private String clearingHouseURL;
	
	
	@Size( max = 25, message = "The code must be between {min} and {max} characters long")
	@Column(name = "url_usn")
	private String userName;
	
	
	
	@Size( max = 100, message = "The code must be between {min} and {max} characters long")
	@Column(name = "url_pwd")
	private String password;
	 
    @JsonIgnore
    @Fetch(FetchMode.SELECT)
	@BatchSize(size = 25)
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_practices", joinColumns = {
			@JoinColumn(name = "prac_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id") }) 
	private  Set<User> users = new HashSet<>(); 
 
   @Fetch(FetchMode.SELECT)
	@BatchSize(size = 25)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "practice")
	private Set<Provider> prvdrs = new HashSet<>();

	
   /**
	 * 
	 */
	public Practice() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public Practice(final Integer id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return
	 */
	public String getNpi() {
		return npi;
	}

	/**
	 * @param npi
	 */
	public void setNpi(String npi) {
		this.npi = npi;
	}

	/**
	 * @return
	 */
	public Integer getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId
	 */
	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	/**
	 * @return
	 */
	public String getClearingHouseURL() {
		return clearingHouseURL;
	}

	/**
	 * @param clearingHouseURL
	 */
	public void setClearingHouseURL(String clearingHouseURL) {
		this.clearingHouseURL = clearingHouseURL;
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


/*	public Set<User> getUsers() {
		return users;
	}*/

	/*public void setUsers(Set<User> users) {
		this.users = users;
	}*/

	/**
	 * @return
	 */
	public Set<Provider> getPrvdrs() {
		return prvdrs;
	}

	/**
	 * @param prvdrs
	 */
	public void setPrvdrs(Set<Provider> prvdrs) {
		this.prvdrs = prvdrs;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Practice)) {
			return false;
		}
		Practice other = (Practice) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.springboot.model.Practice[ id=" + id + " ]";
	}





}
