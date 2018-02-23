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
 * @author sarath
 */
@Entity
@Table(name = "user_claim_status")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserClaimStatus extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "user_claim_status_id", nullable = false)
	private Integer id;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_claim_id", nullable = false, referencedColumnName = "user_claim_id")
	private UserClaim userClaim;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_status_detail_id", nullable = false, referencedColumnName = "id")
	private ClaimStatusDetail claimStatusDetail;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prev_claim_status_detail_id", nullable = false, referencedColumnName = "id")
	private ClaimStatusDetail prevClaimStatusDetail;
	
	/**
	 * 
	 */
	public UserClaimStatus() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public UserClaimStatus(final Integer id) {
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
	public UserClaim getUserClaim() {
		return userClaim;
	}

	/**
	 * @param userClaim
	 */
	public void setUserClaim(UserClaim userClaim) {
		this.userClaim = userClaim;
	}

	/**
	 * @return
	 */
	public ClaimStatusDetail getClaimStatusDetail() {
		return claimStatusDetail;
	}

	/**
	 * @param claimStatusDetail
	 */
	public void setClaimStatusDetail(ClaimStatusDetail claimStatusDetail) {
		this.claimStatusDetail = claimStatusDetail;
	}

	/**
	 * @return
	 */
	public ClaimStatusDetail getPrevClaimStatusDetail() {
		return prevClaimStatusDetail;
	}

	/**
	 * @param prevClaimStatusDetail
	 */
	public void setPrevClaimStatusDetail(ClaimStatusDetail prevClaimStatusDetail) {
		this.prevClaimStatusDetail = prevClaimStatusDetail;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserClaimStatus)) {
			return false;
		}
		UserClaimStatus other = (UserClaimStatus) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.UserClaimStatus[ id=" + id + " ]";
	}

}
