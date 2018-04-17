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
 * @author sarath
 */
@Entity
@Table(name = "lu_priority")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Priority extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@NotNull @Min(1) @Max(10000)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@Size(min = 2, max = 15, message = "The code must be between {min} and {max} characters long")
	@Column(name = "code")
	private String code;

	
	@Size(min = 2, max = 50, message = "The description must be between {min} and {max} characters long")
	@Column(name = "description")
	private String description;

	
	/**
	 * 
	 */
	public Priority() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public Priority(final Integer id) {
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
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Priority)) {
			return false;
		}
		Priority other = (Priority) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.springboot.model[ id=" + id + " ]";
	}

}
