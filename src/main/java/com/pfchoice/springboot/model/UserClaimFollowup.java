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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 *
 * @author sarath
 */
@Entity(name = "user_claim_followup")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserClaimFollowup extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "user_claim_followup_id", nullable = false)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_id", nullable = false, referencedColumnName = "id")
	private Claim claim;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "followup_type_id", nullable = false, referencedColumnName = "id")
	private FollowupType followupType;

	
	@Column(name = "followup_details")
	private String followupDetails;

	
	
	/**
	 * 
	 */
	public UserClaimFollowup() {
		super();
	}

	/**
	 * @param id
	 */
	public UserClaimFollowup(final Integer id) {
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
	public FollowupType getFollowupType() {
		return followupType;
	}

	/**
	 * @param followupType
	 */
	public void setFollowupType(FollowupType followupType) {
		this.followupType = followupType;
	}

	/**
	 * @return
	 */
	public String getFollowupDetails() {
		return followupDetails;
	}

	/**
	 * @param followupDetails
	 */
	public void setFollowupDetails(String followupDetails) {
		this.followupDetails = followupDetails;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserClaimFollowup)) {
			return false;
		}
		UserClaimFollowup other = (UserClaimFollowup) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.UserClaimFollowup[ id=" + id + " ]";
	}

}
