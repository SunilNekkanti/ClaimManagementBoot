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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 *
 * @author sarath
 */

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "findProviderInsuranceDetailNativeSQL",
	query = " select this_.* from provider_insurance_details this_ "+
 " left join insurance ins2_ on this_.ins_id=ins2_.Insurance_Id  "+
 " left join practice prac3_ on this_.prac_id=prac3_.id "+
 "  left join provider prvdr1_ on this_.prvdr_id=prvdr1_.prvdr_Id "+
 "  where this_.active_ind='Y' and case when this_.prvdr_Id is not null then  prvdr1_.prvdr_Id= :prvdrId else 1=1 end and  "+
 "                              case when this_.prac_id is not null then   prac3_.id=:pracId else 1=1 end and "+
 "                              case when this_.ins_id is not null then ins2_.Insurance_Id=:insId else 1=1 end ",
        resultClass = ProviderInsuranceDetails.class
	),
	@NamedNativeQuery(	
	name = "findProviderPracticeInsuranceDetailNativeSQL",
	query = " select this_.* from provider_insurance_details this_ "+
 " inner join insurance ins2_ on this_.ins_id=ins2_.Insurance_Id  "+
 " inner join practice prac3_ on this_.prac_id=prac3_.id "+
 "  where this_.active_ind='Y' and "+
 "                              case when this_.prac_id is not null then prac3_.id in (:pracId) else 1=1 end "+
 "order by ins2_.name asc",
        resultClass = ProviderInsuranceDetails.class
	)
})
@Entity(name = "provider_insurance_details")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProviderInsuranceDetails extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "prvdr_ins_details_id", nullable = false)
	private Integer id;
	
 	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition="integer", name = "prvdr_id", nullable = true,referencedColumnName = "prvdr_id")
	private Provider prvdr;
 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(columnDefinition="integer",name = "ins_id", nullable = true, referencedColumnName = "insurance_id")
	private Insurance ins;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prac_id", nullable = true, referencedColumnName = "id")
	private Practice prac;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition="integer", name = "role_id",   nullable = true, referencedColumnName = "id")
	private Role role;
	
	
	@URL
	@Column(name = "url")
	private String url ;
	
	
	@Column(name = "url_usn")
	@NotEmpty
	private String urlUserName ;
	
	
	@Column(name = "url_pwd")
	@NotEmpty
	private String urlPassword ;
	
	
	@NotNull(message = "Select password active option")
	@Column(name = "url_pwd_active")
	private Character urlPasswordActive ;
	
	
	@NotNull(message = "Select clearing house option")
	@Column(name = "is_clearing_house")
	private Character isClearingHouse ;
	
	
	/**
	 * create an object on instantiation
	 */
	public ProviderInsuranceDetails() {
		super();
	}

	/**
	 * @param id
	 */
	public ProviderInsuranceDetails(final Integer id) {
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
	 * @return the ins
	 */
	public Insurance getIns() {
		return ins;
	}

	/**
	 * @param ins
	 *            the ins to set
	 */
	public void setIns(final Insurance ins) {
		this.ins = ins;
	}

	/**
	 * @return the prvdr
	 */
	public Provider getPrvdr() {
		return prvdr;
	}

	/**
	 * @param prvdr
	 *            the prvdr to set
	 */
	public void setPrvdr(final Provider prvdr) {
		this.prvdr = prvdr;
	}

	/**
	 * @return the prac
	 */
	public Practice getPrac() {
		return prac;
	}

	/**
	 * @param prac the prac to set
	 */
	public void setPrac(Practice prac) {
		this.prac = prac;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the urlUserName
	 */
	public String getUrlUserName() {
		return urlUserName;
	}

	/**
	 * @param urlUserName the urlUserName to set
	 */
	public void setUrlUserName(String urlUserName) {
		this.urlUserName = urlUserName;
	}

	/**
	 * @return the urlPassword
	 */
	public String getUrlPassword() {
		return urlPassword;
	}

	/**
	 * @param urlPassword the urlPassword to set
	 */
	public void setUrlPassword(String urlPassword) {
		this.urlPassword = urlPassword;
	}
	
	/**
	 * @return
	 */
	public Character getUrlPasswordActive() {
		return urlPasswordActive;
	}

	/**
	 * @param urlPasswordActive
	 */
	public void setUrlPasswordActive(Character urlPasswordActive) {
		this.urlPasswordActive = urlPasswordActive;
	}
	
	/**
	 * @return
	 */
	public Character getIsClearingHouse() {
		return isClearingHouse;
	}

	/**
	 * @param isClearingHouse
	 */
	public void setIsClearingHouse(Character isClearingHouse) {
		this.isClearingHouse = isClearingHouse;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProviderInsuranceDetails)) {
			return false;
		}
		ProviderInsuranceDetails other = (ProviderInsuranceDetails) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.ProviderInsuranceDetails[ id=" + id+"]";
	}

}
