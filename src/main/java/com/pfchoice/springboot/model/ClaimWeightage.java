package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;



@Component
public class ClaimWeightage implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Integer userClaimId;

	
	private Integer priorityId;

	
	private Integer prvdrId;

	
	private Integer practiceId;

	
	BigInteger claimno;

	
	private Integer claimStatusDetailId;

	
	private Integer maxClaimScore;

	
	private Integer agingPercentage;

	
	private Double maxClaimAgeScore;

	
	private Integer aging;

	
	private Integer agingCond;

	
	private Double maxClaimAgingScore;

	
	private Double actualClaimAgingScore;

	
	private Double charges;

	
	private Integer chargesPercentage;

	
	private Double maxClaimChargesScore;

	
	private Integer chargesCond;

	
	private Integer dvPerct;

	
	private Double actualClaimchargesScore;

	
	private Integer remaingDays;

	
	private Integer filingPercentage;

	
	private Double maxClaimFilingScore;

	
	private Integer filingCond;

	
	private Double actualClaimFilingScore;

	
	private Double actualClaimScore;

	@Temporal(TemporalType.DATE)
	private Date reminder;
	
	
	public Integer getUserClaimId() {
		return userClaimId;
	}

	public void setUserClaimId(Integer userClaimId) {
		this.userClaimId = userClaimId;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getPrvdrId() {
		return prvdrId;
	}

	public void setPrvdrId(Integer prvdrId) {
		this.prvdrId = prvdrId;
	}

	public Integer getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
	}

	public BigInteger getClaimno() {
		return claimno;
	}

	public Integer getClaimStatusDetailId() {
		return claimStatusDetailId;
	}

	public void setClaimStatusDetailId(Integer claimStatusDetailId) {
		this.claimStatusDetailId = claimStatusDetailId;
	}

	public void setActualClaimScore(BigDecimal actualClaimScore) {
		this.actualClaimScore = actualClaimScore.doubleValue();
	}

	public double getActualClaimScore() {
		return actualClaimScore;
	}

	public void setClaimno(BigInteger claimno) {
		this.claimno = claimno;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public Integer getMaxClaimScore() {
		return maxClaimScore;
	}

	public void setMaxClaimScore(Integer maxClaimScore) {
		this.maxClaimScore = maxClaimScore;
	}

	public Integer getAgingPercentage() {
		return agingPercentage;
	}

	public void setAgingPercentage(Integer agingPercentage) {
		this.agingPercentage = agingPercentage;
	}

	public Double getMaxClaimAgeScore() {
		return maxClaimAgeScore;
	}

	public void setMaxClaimAgeScore(BigDecimal maxClaimAgeScore) {
		this.maxClaimAgeScore = maxClaimAgeScore.doubleValue();
	}

	public Integer getAging() {
		return aging;
	}

	public void setAging(Integer aging) {
		this.aging = aging;
	}

	public Integer getAgingCond() {
		return agingCond;
	}

	public void setAgingCond(Integer agingCond) {
		this.agingCond = agingCond;
	}


	public double getActualClaimAgingScore() {
		return actualClaimAgingScore;
	}

	public void setActualClaimAgingScore(double actualClaimAgingScore) {
		this.actualClaimAgingScore = actualClaimAgingScore;
	}

	public Integer getChargesPercentage() {
		return chargesPercentage;
	}

	public void setChargesPercentage(Integer chargesPercentage) {
		this.chargesPercentage = chargesPercentage;
	}


	public Integer getChargesCond() {
		return chargesCond;
	}

	public void setChargesCond(Integer chargesCond) {
		this.chargesCond = chargesCond;
	}

	public Integer getDvPerct() {
		return dvPerct;
	}

	public void setDvPerct(Integer dvPerct) {
		this.dvPerct = dvPerct;
	}

	public double getActualClaimchargesScore() {
		return actualClaimchargesScore;
	}

	public void setActualClaimchargesScore(double actualClaimchargesScore) {
		this.actualClaimchargesScore = actualClaimchargesScore;
	}

	public Integer getRemaingDays() {
		return remaingDays;
	}

	public void setRemaingDays(Integer remaingDays) {
		this.remaingDays = remaingDays;
	}

	public Integer getFilingPercentage() {
		return filingPercentage;
	}

	public void setFilingPercentage(Integer filingPercentage) {
		this.filingPercentage = filingPercentage;
	}


	public double getMaxClaimAgingScore() {
		return maxClaimAgingScore;
	}

	public void setMaxClaimAgingScore(BigDecimal maxClaimAgingScore) {
		this.maxClaimAgingScore = maxClaimAgingScore.doubleValue();
	}

	public double getMaxClaimChargesScore() {
		return maxClaimChargesScore;
	}

	public void setMaxClaimChargesScore(BigDecimal maxClaimChargesScore) {
		this.maxClaimChargesScore = maxClaimChargesScore.doubleValue();
	}

	public double getMaxClaimFilingScore() {
		return maxClaimFilingScore;
	}

	public void setMaxClaimFilingScore(BigDecimal maxClaimFilingScore) {
		this.maxClaimFilingScore = maxClaimFilingScore.doubleValue();
	}

	public Integer getFilingCond() {
		return filingCond;
	}

	public void setFilingCond(Integer filingCond) {
		this.filingCond = filingCond;
	}

	public double getActualClaimFilingScore() {
		return actualClaimFilingScore;
	}

	public void setActualClaimFilingScore(BigDecimal actualClaimFilingScore) {
		this.actualClaimFilingScore = actualClaimFilingScore.doubleValue();
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

}
