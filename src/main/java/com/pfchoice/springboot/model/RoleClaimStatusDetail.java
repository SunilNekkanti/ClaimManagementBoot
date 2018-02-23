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
@Table(name = "role_claim_status_details")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RoleClaimStatusDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
	private Role role;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_status_detail_id", nullable = false, referencedColumnName = "id")
	private ClaimStatusDetail claimStatusDetail;

	/**
	 * 
	 */
	public RoleClaimStatusDetail() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public RoleClaimStatusDetail(final Integer id) {
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
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return
	 */
	public ClaimStatusDetail getClaimStatusDetail() {
		return claimStatusDetail;
	}

	/**
	 * @param claimStatus
	 */
	public void setClaimStatusDetail(ClaimStatusDetail claimStatusDetail) {
		this.claimStatusDetail = claimStatusDetail;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof RoleClaimStatusDetail)) {
			return false;
		}
		RoleClaimStatusDetail other = (RoleClaimStatusDetail) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.RoleClaimStatusDetail[ id=" + id + " ]";
	}

}
