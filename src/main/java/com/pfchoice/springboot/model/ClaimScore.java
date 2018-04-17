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
@Table(name = "claim_score")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ClaimScore extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "claim_score_id", nullable = false)
	private Integer id;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_id", nullable = false, referencedColumnName = "id")
	private Claim claim;

	
	@Column(name = "score")
	private Integer score;

	
	@Temporal(TemporalType.DATE)
	@Column(name = "assigned_date")
	private Date assignedDate;

	/**
	 * 
	 */
	public ClaimScore() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public ClaimScore(final Integer id) {
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
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score
	 */
	public void setScore(Integer score) {
		this.score = score;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ClaimScore)) {
			return false;
		}
		ClaimScore other = (ClaimScore) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.springboot.model.ClaimScore[ id=" + id + " ]";
	}

}
