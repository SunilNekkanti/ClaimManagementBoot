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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 *
 * @author sarath
 */

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "getClaimStatusDetailNativeSQL",
	query = " select  *,group_concat(role) from claim_status_detail csd "+
			" join role_claim_status_details  rcsd    on csd.id = rcsd.claim_status_detail_id "+
			" join role r on r.id = rcsd.role_id "+
			" group by csd.id "+
			"  order by csd.id",
        resultClass = ClaimStatusDetail.class
	),
})

@Entity
@Table(name = "claim_status_detail")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ClaimStatusDetail extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@Size(min = 2, max = 25, message = "The code must be between {min} and {max} characters long")
	@Column(name = "description")
	private String description;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_status_id", referencedColumnName = "id")
	private ClaimStatus claimStatusId;

	/**
	 * 
	 */
	public ClaimStatusDetail() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public ClaimStatusDetail(final Integer id) {
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
	 * @return the name
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public ClaimStatus getClaimStatusId() {
		return claimStatusId;
	}

	/**
	 * @param claimStatusId
	 */
	public void setClaimStatusId(ClaimStatus claimStatusId) {
		this.claimStatusId = claimStatusId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ClaimStatusDetail)) {
			return false;
		}
		ClaimStatusDetail other = (ClaimStatusDetail) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.springboot.model.ClaimStatusDetail[ id=" + id + " ]";
	}

}
