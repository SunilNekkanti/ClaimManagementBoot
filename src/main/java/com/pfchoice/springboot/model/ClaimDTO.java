package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;



@Component

public class ClaimDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer claimId;

	private Integer cnt;

	
	private String lookup;

	
	private Long claimno;
	
	
	private String practice;

	
	private String provider;

	
	@Temporal(TemporalType.DATE)
	Date serviceDate;
	
	
	@Temporal(TemporalType.DATE)
	Date claimDate;

	
	private String patient;

	
	@Temporal(TemporalType.DATE)
	Date dob;
	
	
	private String patientPhone;

	
	private Double charges;

	
	private String insurance;
	
	
	private String insuranceType;
	
	
	private String statuses;
	
	
	private String userName;

	
	private String priority;
	
	
	private Integer allocCount;
	
	
	private Integer workedCount;
	
	
	@Temporal(TemporalType.DATE)
	Date reminder;
	
	
	@Temporal(TemporalType.DATE)
	Date followupDate;
	
	
	private String followupDetails;

	public Integer getClaimId() {
		return claimId;
	}

	public Integer getAllocCount() {
		return allocCount;
	}

	public void setAllocCount(Integer allocCount) {
		this.allocCount = allocCount;
	}

	public Integer getWorkedCount() {
		return workedCount;
	}

	public void setWorkedCount(Integer workedCount) {
		this.workedCount = workedCount;
	}

	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public Long getClaimno() {
		return claimno;
	}

	public void setClaimno(BigInteger claimno) {
		this.claimno = claimno.longValue();
	}
	
	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	
	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public String getStatuses() {
		return statuses;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	
	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getFollowupDetails() {
		return followupDetails;
	}

	public void setFollowupDetails(String followupDetails) {
		this.followupDetails = followupDetails;
	}

	/**
	 * @return the followupDate
	 */
	public Date getFollowupDate() {
		return followupDate;
	}

	/**
	 * @param followupDate the followupDate to set
	 */
	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}
	
}
