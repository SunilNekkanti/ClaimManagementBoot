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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 *
 * @author Mohanasundharam
 */
@Entity
@Table(name = "weightage")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Weightage extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	
	@Column(name = "condition_exp", nullable = false)
	private Integer conditionExp;

	
	@Column(name = "percentage", nullable = false)
	private Integer percentage;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weightage_type_id", nullable = false, referencedColumnName = "id")
	private WeightageType wtageType;

	/**
	 * 
	 */
	public Weightage() {
		super();

	}

	/**
	 * @param id
	 */
	public Weightage(final Integer id) {
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
	 * @return
	 */
	public Integer getConditionExp() {
		return conditionExp;
	}

	/**
	 * @param conditionExp
	 */
	public void setConditionExp(Integer conditionExp) {
		this.conditionExp = conditionExp;
	}

	/**
	 * @return
	 */
	public Integer getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage
	 */
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public WeightageType getWtageType() {
		return wtageType;
	}

	/**
	 * @param wtageType
	 */
	public void setWtageType(WeightageType wtageType) {
		this.wtageType = wtageType;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Weightage)) {
			return false;
		}
		Weightage other = (Weightage) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.springboot.model.Weightage[ id=" + id + " ]";
	}

}
