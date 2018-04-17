package com.pfchoice.springboot.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 *
 * @author sarath
 */
@Entity
@Table(name = "lu_weightage_type")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WeightageType extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@Size(min = 2, max = 50, message = "The description must be between {min} and {max} characters long")
	@Column(name = "description")
	private String description;

	
	@NotNull(message = "Enter the weightage percent")
	@Column(name = "weightage_percent", nullable = false)
	private Integer weightagePercent;

	/**
	 * 
	 */
	public WeightageType() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public WeightageType(final Integer id) {
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
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public Integer getWeightagePercent() {
		return weightagePercent;
	}

	/**
	 * @param weightagePercent
	 */
	public void setWeightagePercent(Integer weightagePercent) {
		this.weightagePercent = weightagePercent;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof WeightageType)) {
			return false;
		}
		WeightageType other = (WeightageType) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.springboot.model.WeightageType[ id=" + id + " ]";
	}

}
