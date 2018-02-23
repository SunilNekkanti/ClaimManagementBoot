package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author sarath
 */
@Entity(name = "role")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Role extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id", nullable = false)
	private Integer id;

	
	@NotNull(message = "The role cannot be null")
	@Column(name = "role")
	private String role;

	@OneToOne
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id") })
	private User user;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_priorities", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "priority_id", referencedColumnName = "id") })
	public Set<Priority> priorities;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_claim_status_details", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "claim_status_detail_id", referencedColumnName = "id") })
	public Set<ClaimStatusDetail> claimStatusDetails;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_claim_status", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "claim_status_id", referencedColumnName = "id") })
	public Set<ClaimStatus> claimStatus;

	
	/**
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * @param id
	 */
	public Role(final Integer id) {
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
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return
	 */
	public Set<Priority> getPriorities() {
		return priorities;
	}

	/**
	 * @param priorities
	 */
	public void setPriorities(Set<Priority> priorities) {
		this.priorities = priorities;
	}

	/**
	 * @return
	 */
	public Set<ClaimStatusDetail> getClaimStatusDetails() {
		return claimStatusDetails;
	}

	/**
	 * @param claimStatusDetails
	 */
	public void setClaimStatusDetails(Set<ClaimStatusDetail> claimStatusDetails) {
		this.claimStatusDetails = claimStatusDetails;
	}

	/**
	 * @return
	 */
	public Set<ClaimStatus> getClaimStatus() {
		return claimStatus;
	}

	/**
	 * @param claimStatus
	 */
	public void setClaimStatus(Set<ClaimStatus> claimStatus) {
		this.claimStatus = claimStatus;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Role)) {
			return false;
		}
		Role other = (Role) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.Role[ id=" + id + " ]";
	}

}
