package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 *
 * @author sarath
 */
@Entity
@Table(name = "user_allocation_limit")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserAllocationLimit extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;

	
	@Temporal(TemporalType.DATE)
	@Column(name = "assigned_date")
	private Date assignedDate;

	
	@Column(name = "aging_max_count")
	private Integer agingMaxCount;

	
	@Column(name = "filing_max_count")
	private Integer filingMaxCount;

	
	@Column(name = "charges_max_count")
	private Integer chargesMaxCount;

	/**
	 * 
	 */
	public UserAllocationLimit() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public UserAllocationLimit(final Integer id) {
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
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return
	 */
	public Date getAssignedDate() {
		return assignedDate;
	}

	/**
	 * @param assignedDate
	 */
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	/**
	 * @return
	 */
	public Integer getAgingMaxCount() {
		return agingMaxCount;
	}

	/**
	 * @param agingMaxCount
	 */
	public void setAgingMaxCount(Integer agingMaxCount) {
		this.agingMaxCount = agingMaxCount;
	}

	/**
	 * @return
	 */
	public Integer getFilingMaxCount() {
		return filingMaxCount;
	}

	/**
	 * @param filingMaxCount
	 */
	public void setFilingMaxCount(Integer filingMaxCount) {
		this.filingMaxCount = filingMaxCount;
	}

	/**
	 * @return
	 */
	public Integer getChargesMaxCount() {
		return chargesMaxCount;
	}

	/**
	 * @param chargesMaxCount
	 */
	public void setChargesMaxCount(Integer chargesMaxCount) {
		this.chargesMaxCount = chargesMaxCount;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserAllocationLimit)) {
			return false;
		}
		UserAllocationLimit other = (UserAllocationLimit) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.springboot.model.UserAllocationLimit[ id=" + id + " ]";
	}

}
