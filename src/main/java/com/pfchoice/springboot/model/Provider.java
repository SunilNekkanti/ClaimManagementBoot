package com.pfchoice.springboot.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Mohanasundharam
 */
@Entity
@Table(name = "provider")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Provider extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "prvdr_Id", unique = true, nullable = false)
	private Integer id;

	
	@Column(name = "code")
	private String code;

	
	@NotNull
	@Size( min =5, max = 100, message = "The name must be between {min} and {max} characters long")
	@Column(name = "name")
	private String name;


	
	@NotNull
	@Size( min = 10, max =50, message = "The npi must be between {min} and {max} characters long")
	@Column(name = "npi")
	private String npi;
	
	
	@Column(name = "contact")
	private String contact;
	
	
	@Column(name = "facility_address")
	private String facilityAddress;
	
	
	@Column(name = "temp")
	private String temp;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prac_id", nullable = false, referencedColumnName = "id")
	private Practice practice;

	
	@Transient
	private String pracName;
	
	
	@Transient
	private Integer taxId;
	
	
	@Transient
	private String pracNpi;

	
	@Transient
	private Long rowCount;

	/**
	 * 
	 */
	public Provider() {
		super();

	}

	/**
	 * @param id
	 */
	public Provider(final Integer id) {
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
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
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}
	
	
	/**
	 * @return
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return
	 */
	public String getFacilityAddress() {
		return facilityAddress;
	}

	/**
	 * @param facilityAddress
	 */
	public void setFacilityAddress(String facilityAddress) {
		this.facilityAddress = facilityAddress;
	}

	/**
	 * @return
	 */
	public String getTemp() {
		return temp;
	}

	/**
	 * @param temp
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}

	/**
	 * @return
	 */
	public Practice getPractice() {
		return practice;
	}

	/**
	 * @param practice
	 */
	public void setPractice(Practice practice) {
		this.practice = practice;
	}

	/**
	 * @return
	 */
	public String getPracName() {
		return pracName;
	}

	/**
	 * @param pracName
	 */
	public void setPracName(String pracName) {
		this.pracName = pracName;
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
	public String getPracNpi() {
		return pracNpi;
	}

	/**
	 * @param pracNpi
	 */
	public void setPracNpi(String pracNpi) {
		this.pracNpi = pracNpi;
	}

	/**
	 * @return
	 */
	public Long getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount
	 */
	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Provider)) {
			return false;
		}
		Provider other = (Provider) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.Provider[ id=" + id + " ]";
	}

}
