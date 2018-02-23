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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 *
 * @author sarath
 */
@Entity
@Table(name = "allocation")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Allocation extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "priority_id", nullable = false, referencedColumnName = "id")
	private Priority priority;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "allocation_level_id", nullable = false, referencedColumnName = "id")
	private AllocationLevel allocLevel;

	
	@NotNull(message = "Enter the percentage")
	@Column(name = "percentage", nullable = false)
	private Integer percentage;

	/**
	 * 
	 */
	public Allocation() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public Allocation(final Integer id) {
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
	public Priority getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * @return
	 */
	public AllocationLevel getAllocLevel() {
		return allocLevel;
	}

	/**
	 * @param allocLevel
	 */
	public void setAllocLevel(AllocationLevel allocLevel) {
		this.allocLevel = allocLevel;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Allocation)) {
			return false;
		}
		Allocation other = (Allocation) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.Allocation[ id=" + id + " ]";
	}

}
