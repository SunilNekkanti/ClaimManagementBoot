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
@Table(name = "user_score")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserScore extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
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

	
	@Column(name = "score")
	private Double claimScore;

	/**
	 * 
	 */
	public UserScore() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public UserScore(final Integer id) {
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

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Integer getAgingCond() {
		return agingCond;
	}

	public void setAgingCond(Integer agingCond) {
		this.agingCond = agingCond;
	}

	public Integer getFilingCond() {
		return filingCond;
	}

	public void setFilingCond(Integer filingCond) {
		this.filingCond = filingCond;
	}

	public Integer getChargesCond() {
		return chargesCond;
	}

	public void setChargesCond(Integer chargesCond) {
		this.chargesCond = chargesCond;
	}

	public Double getClaimScore() {
		return claimScore;
	}

	public void setClaimScore(Double claimScore) {
		this.claimScore = claimScore;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserScore)) {
			return false;
		}
		UserScore other = (UserScore) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.springboot.model.UserScore[ id=" + id + " ]";
	}

}
