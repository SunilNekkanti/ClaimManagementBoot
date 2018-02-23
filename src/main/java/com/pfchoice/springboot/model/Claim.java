package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 *
 * @author sarath
 */
@Entity
@Table(name = "claims")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Claim extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@Size(min = 2, max = 25, message = "The code must be between {min} and {max} characters long")
	@Column(name = "lookup")
	private String lookup;

	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "prvdr_id", nullable = false, referencedColumnName = "prvdr_id")
	private Provider prvdr;

	
	@Column(name = "claimNo")
	private Long claimNo;

	
	@Temporal(TemporalType.DATE)
	@Column(name = "service_date")
	private Date serviceDate;

	
	@Size(min = 2, max = 100, message = "The code must be between {min} and {max} characters long")
	@Column(name = "patient_name")
	private String patientName;

	
	@Temporal(TemporalType.DATE)
	@Column(name = "dob", nullable = true)
	private Date dob;

	
	@Size(min = 0, max = 25, message = "The code must be between {min} and {max} characters long")
	@Column(name = "patient_phone", nullable = true)
	private String patientPhone;

	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "ins_id", nullable = false, referencedColumnName = "insurance_id")
	private Insurance insId;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "insurance_type_id", nullable = false, referencedColumnName = "id")
	private InsuranceType insuranceType;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_status_detail_id", nullable = false, referencedColumnName = "id")
	private ClaimStatusDetail claimStatusDetailId;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_status_id", nullable = false, referencedColumnName = "id")
	private ClaimStatus claimStatus;

	
	@Column(name = "charges")
	private Double charges;
	
	
	@Column(name = "priority_id", nullable = false)
	private Integer priorityId;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "reminder")
	private Date reminderDate;
	
	
	@Transient
	private List<ProviderInsuranceDetails> prvdrInsDetails = new ArrayList<>();
	
	
	@Transient
	private List<PracticeProviderInsuranceDetails> pracPrvdrInsDetails = new ArrayList<>();
	
	
	@Column(name = "file_id", nullable = false)
	private Integer fileId;
	/**
	 * 
	 */
	public Claim() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public Claim(final Integer id) {
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
	public String getLookup() {
		return lookup;
	}

	/**
	 * @param lookup
	 */
	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	/**
	 * @return
	 */
	public Provider getPrvdr() {
		return prvdr;
	}

	/**
	 * @param prvdr
	 */
	public void setPrvdr(Provider prvdr) {
		this.prvdr = prvdr;
	}

	/**
	 * @return
	 */
	public Long getClaimNo() {
		return claimNo;
	}

	/**
	 * @param claim
	 */
	public void setClaimNo(Long claim) {
		this.claimNo = claim;
	}

	/**
	 * @return
	 */
	public Date getServiceDate() {
		return serviceDate;
	}

	/**
	 * @param serviceDate
	 */
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	/**
	 * @return
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patient_name
	 */
	public void setPatient_name(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return
	 */
	public Insurance getInsId() {
		return insId;
	}

	/**
	 * @param insId
	 */
	public void setInsId(Insurance insId) {
		this.insId = insId;
	}

	/**
	 * @return
	 */
	public ClaimStatusDetail getClaimStatusDetailId() {
		return claimStatusDetailId;
	}

	/**
	 * @param claimStatusDetailId
	 */
	public void setClaimStatusDetailId(ClaimStatusDetail claimStatusDetailId) {
		this.claimStatusDetailId = claimStatusDetailId;
	}

	/**
	 * @return
	 */
	public String getPatientPhone() {
		return patientPhone;
	}

	/**
	 * @return
	 */
	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}

	/**
	 * @param claimStatus
	 */
	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}

	/**
	 * @param patientName
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @param patientPhone
	 */
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	/**
	 * @return
	 */
	public Double getCharges() {
		return charges;
	}

	/**
	 * @param charges
	 */
	public void setCharges(Double charges) {
		this.charges = charges;
	}

	/**
	 * @return
	 */
	public Integer getPriorityId() {
		return priorityId;
	}

	/**
	 * @param priorityId
	 */
	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	/**
	 * @return
	 */
	public Date getReminderDate() {
		return reminderDate;
	}

	/**
	 * @param reminderDate
	 */
	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}

	/**
	 * @return the prvdrInsDetails
	 */
	public List<ProviderInsuranceDetails> getPrvdrInsDetails() {
		return prvdrInsDetails;
	}

	/**
	 * @param prvdrInsDetails the prvdrInsDetails to set
	 */
	public void setPrvdrInsDetails(List<ProviderInsuranceDetails> prvdrInsDetails) {
		this.prvdrInsDetails = prvdrInsDetails;
	}

	/**
	 * @return the pracPrvdrInsDetails
	 */
	public List<PracticeProviderInsuranceDetails> getPracPrvdrInsDetails() {
		return pracPrvdrInsDetails;
	}

	/**
	 * @param pracPrvdrInsDetails the pracPrvdrInsDetails to set
	 */
	public void setPracPrvdrInsDetails(List<PracticeProviderInsuranceDetails> pracPrvdrInsDetails) {
		this.pracPrvdrInsDetails = pracPrvdrInsDetails;
	}

	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the insuranceType
	 */
	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	/**
	 * @param insuranceType the insuranceType to set
	 */
	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Claim)) {
			return false;
		}
		Claim other = (Claim) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.Claim[ id=" + id + " ]";
	}

}
