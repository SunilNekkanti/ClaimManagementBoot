package com.pfchoice.springboot.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 *
 * @author Mohanasundharam
 */
@Entity
@Table(name = "insurance")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Insurance extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Insurance_Id", nullable = false)
	private Integer id;

	
	@Size(min = 2, max = 50, message = "The name must be between {min} and {max} characters long")
	@Column(name = "name", nullable = false)
	private String name;

	
	@Column(name = "filing_limit", nullable = false)
	private Integer filingLimit;

	
	/**
	 * 
	 */
	public Insurance() {
		super();
	}

	/**
	 * @param id
	 */
	public Insurance(final Integer id) {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public Integer getFilingLimit() {
		return filingLimit;
	}

	/**
	 * @param filingLimit
	 */
	public void setFilingLimit(Integer filingLimit) {
		this.filingLimit = filingLimit;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Insurance)) {
			return false;
		}
		Insurance other = (Insurance) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.Insurance[ id=" + id + " ]";
	}

}
