package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.lang.reflect.Field;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author SarathGandluri
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "provider")
public class Provider extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "prvdr_Id", nullable = false)
	private Integer id;

	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;

	
	@NotNull
	//@Size( min = 10, max =50, message = "The npi must be between {min} and {max} characters long")
	@Column(name = "npi")
	private String npi;

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}



	/*@OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "reference_contacts", joinColumns = {
			@JoinColumn(name = "prvdr_id", referencedColumnName = "prvdr_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "cnt_id", referencedColumnName = "cnt_id", nullable = false, unique = true) })
					
	@Size(min = 10, max = 50, message = "The contact must be between {min} and {max} characters long")*/
	@Column(name = "contact", nullable = false)
	private String contact;


	@Column(name = "facility_address")
	private String facilityAddress;
	

	public String getFacilityAddress() {
		return facilityAddress;
	}

	public void setFacilityAddress(String facilityAddress) {
		this.facilityAddress = facilityAddress;
	}



	@Column(name = "temp")
	private String temp;
	

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}



	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prac_id", nullable = false, referencedColumnName = "id")
	private Practice practice;

	
	public Practice getPractice() {
		return practice;
	}

	public void setPractice(Practice practice) {
		this.practice = practice;
	}

	/**
	 * 
	 */

	@Transient
	private String pracName;
	
	
	public String getPracName() {
		return pracName;
	}

	public void setPracName(String pracName) {
		this.pracName = pracName;
	}



	@Transient
	private Integer taxId;
	
	
	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}



	@Transient
	private String pracNpi;

	
	public String getPracNpi() {
		return pracNpi;
	}

	public void setPracNpi(String pracNpi) {
		this.pracNpi = pracNpi;
	}



	@Transient
	private Long rowCount;
	
	public Long getRowCount() {
		return rowCount;
	}

	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}

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

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/**
	 * @param code
	 *            the code to set
	 */
public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @param id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(this.getClass().getName());
		result.append(" Object {");
		result.append("\n");

		// determine fields declared in this class only (no fields of
		// superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			if ("serialVersionUID".equals(field.getName()))
				continue;
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				// requires access to private field:
				result.append(field.get(this));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			result.append("\n");
		}
		result.append("}");

		return result.toString();
	}

}
