package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
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
	name = "findPracticeProviderInsuranceDetailNativeSQL",
	query = " select this_.* from prac_prvdr_ins_details this_ "+
 " join prac_prvdr_ins_details_prac pracDetails on pracDetails.prac_prvdr_ins_details_id = this_.prac_prvdr_ins_details_id " +
 " left join prac_prvdr_ins_details_prvdr prvdrDetails on prvdrDetails.prac_prvdr_ins_details_id = this_.prac_prvdr_ins_details_id " +
 " left join prac_prvdr_ins_details_ins insDetails on insDetails.prac_prvdr_ins_details_id = this_.prac_prvdr_ins_details_id " +
 "  left join insurance ins2_ on insDetails.ins_id=ins2_.Insurance_Id  "+
 "   join practice prac3_ on pracDetails.prac_id=prac3_.id "+
 "  left  join provider prvdr1_ on prvdrDetails.prvdr_id=prvdr1_.prvdr_Id "+
 "  where this_.active_ind='Y' and case when prvdrDetails.prvdr_Id is not null then  prvdr1_.prvdr_Id= :prvdrId else 1=1 end and  case when insDetails.ins_id is not null then ins2_.Insurance_Id=:insId else 1=1 end  and prac3_.id=:pracId   ",
        resultClass = PracticeProviderInsuranceDetails.class
	),
	@NamedNativeQuery(	
	name = "findPracticeProviderPracticeInsuranceDetailNativeSQL",
	query = " select this_.* from prac_prvdr_ins_details this_ "+
			 " join prac_prvdr_ins_details_prac pracDetails on pracDetails.prac_prvdr_ins_details_id = this_.prac_prvdr_ins_details_id " +
			 " join prac_prvdr_ins_details_ins insDetails on insDetails.prac_prvdr_ins_details_id = this_.prac_prvdr_ins_details_id " +
 " inner join insurance ins2_ on insDetails.ins_id=ins2_.Insurance_Id  "+
 " inner join practice prac3_ on pracDetails.prac_id=prac3_.id "+
 "  where this_.active_ind='Y'  and prac3_.id in (:pracId)  "+
 "order by ins2_.name asc",
        resultClass = PracticeProviderInsuranceDetails.class
	)
})
@Entity(name = "prac_prvdr_ins_details")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PracticeProviderInsuranceDetails extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "prac_prvdr_ins_details_id", nullable = false)
	private Integer id;
	

	
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
	
	
	@Column(name = "QA")
	private String QA ;
	
	
	@Column(name = "register_id")
	private String registerId ;
	
	
	@NotNull(message = "Select clearing house option")
	@Column(name = "is_clearing_house")
	private Character isClearingHouse ;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "prac_prvdr_ins_details_prvdr", joinColumns = {
			@JoinColumn(name = "prac_prvdr_ins_details_id", referencedColumnName = "prac_prvdr_ins_details_id") }, inverseJoinColumns = {
					@JoinColumn(name = "prvdr_id", referencedColumnName = "prvdr_id") })
	private Set<Provider> prvdrs;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "prac_prvdr_ins_details_prac", joinColumns = {
			@JoinColumn(name = "prac_prvdr_ins_details_id", referencedColumnName = "prac_prvdr_ins_details_id") }, inverseJoinColumns = {
					@JoinColumn(name = "prac_id", referencedColumnName = "id") })
	private Set<Practice> pracs;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "prac_prvdr_ins_details_ins", joinColumns = {
			@JoinColumn(name = "prac_prvdr_ins_details_id", referencedColumnName = "prac_prvdr_ins_details_id") }, inverseJoinColumns = {
					@JoinColumn(name = "ins_id", referencedColumnName = "insurance_id") })
	private Set<Insurance> insurances;
	
	
	
	/**
	 * create an object on instantiation
	 */
	public PracticeProviderInsuranceDetails() {
		super();
	}

	/**
	 * @param id
	 */
	public PracticeProviderInsuranceDetails(final Integer id) {
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

	/**
	 * @return the prvdrs
	 */
	public Set<Provider> getPrvdrs() {
		return prvdrs;
	}

	/**
	 * @param prvdrs the prvdrs to set
	 */
	public void setPrvdrs(Set<Provider> prvdrs) {
		this.prvdrs = prvdrs;
	}

	/**
	 * @return the pracs
	 */
	public Set<Practice> getPracs() {
		return pracs;
	}

	/**
	 * @param pracs the pracs to set
	 */
	public void setPracs(Set<Practice> pracs) {
		this.pracs = pracs;
	}

	/**
	 * @return the insurances
	 */
	public Set<Insurance> getInsurances() {
		return insurances;
	}

	/**
	 * @param insurances the insurances to set
	 */
	public void setInsurances(Set<Insurance> insurances) {
		this.insurances = insurances;
	}
	
	/**
	 * @return
	 */
	public String getQA() {
		return QA;
	}

	/**
	 * @param qA
	 */
	public void setQA(String qA) {
		QA = qA;
	}

	/**
	 * @return
	 */
	public String getRegisterId() {
		return registerId;
	}

	/**
	 * @param registerId
	 */
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PracticeProviderInsuranceDetails)) {
			return false;
		}
		PracticeProviderInsuranceDetails other = (PracticeProviderInsuranceDetails) object;
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
