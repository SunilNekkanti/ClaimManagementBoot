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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Mohanasundharam
 */
@Entity
@Table(name = "mapping_insurance")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MappingInsurance extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "mapping_ins_id", nullable = false)
	private Integer id;

	
	@Size(min = 2, max = 50, message = "The name must be between {min} and {max} characters long")
	@Column(name = "name", nullable = false)
	private String name;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ins_id", nullable = false, referencedColumnName = "Insurance_Id")
	private Insurance insId;

	/**
	 * 
	 */
	public MappingInsurance() {
		super();
	}

	/**
	 * @param id
	 */
	public MappingInsurance(final Integer id) {
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
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	public Insurance getInsId() {
		return insId;
	}

	public void setInsId(Insurance insId) {
		this.insId = insId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MappingInsurance)) {
			return false;
		}
		MappingInsurance other = (MappingInsurance) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.MappingInsurance[ id=" + id + " ]";
	}

}
