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
@Table(name = "user_claims")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserClaim extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "user_claim_id")
	private Integer id;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_id", nullable = false, referencedColumnName = "id")
	private Claim claim;

	
	@Temporal(TemporalType.DATE)
	@Column(name = "assigned_date")
	private Date assignedDate;

	
	@Column(name = "aging_cond")
	private Integer agingCond;

	
	@Column(name = "filing_cond")
	private Integer filingCond;

	
	@Column(name = "charges_cond")
	private Integer chargesCond;

	
	@Column(name = "weightage")
	private Double claimScore;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alloc_type_id", nullable = false, referencedColumnName = "id")
	private AllocationType allocType;

	/**
	 * 
	 */
	public UserClaim() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public UserClaim(final Integer id) {
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
	public Claim getClaim() {
		return claim;
	}

	/**
	 * @param claim
	 */
	public void setClaim(Claim claim) {
		this.claim = claim;
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
	public Integer getAgingCond() {
		return agingCond;
	}

	/**
	 * @param agingCond
	 */
	public void setAgingCond(Integer agingCond) {
		this.agingCond = agingCond;
	}

	/**
	 * @return
	 */
	public Integer getFilingCond() {
		return filingCond;
	}

	/**
	 * @param filingCond
	 */
	public void setFilingCond(Integer filingCond) {
		this.filingCond = filingCond;
	}

	/**
	 * @return
	 */
	public Integer getChargesCond() {
		return chargesCond;
	}

	/**
	 * @param chargesCond
	 */
	public void setChargesCond(Integer chargesCond) {
		this.chargesCond = chargesCond;
	}

	/**
	 * @return
	 */
	public Double getClaimScore() {
		return claimScore;
	}

	/**
	 * @param claimScore
	 */
	public void setClaimScore(Double claimScore) {
		this.claimScore = claimScore;
	}

	/**
	 * @return
	 */
	public AllocationType getAllocType() {
		return allocType;
	}

	/**
	 * @param allocType
	 */
	public void setAllocType(AllocationType allocType) {
		this.allocType = allocType;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserClaim)) {
			return false;
		}
		UserClaim other = (UserClaim) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.UserClaim[ id=" + id + " ]";
	}

}
