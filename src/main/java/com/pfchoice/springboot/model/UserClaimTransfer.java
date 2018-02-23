package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;



@Component
public class UserClaimTransfer implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Integer userId;

	
	private Integer claimId;

	
	private Integer allocTypeId;

	
	private Integer agingCond;

	
	private Integer filingCond;

	
	private Integer chargesCond;

	
	private Date assignedDate;

	private Integer maxClaimScore;
	
	private Integer counter;
	
	
	private double claimScore;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getClaimId() {
		return claimId;
	}

	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}

	public Integer getAllocTypeId() {
		return allocTypeId;
	}

	public void setAllocTypeId(Integer allocTypeId) {
		this.allocTypeId = allocTypeId;
	}

	public Integer getAgingCond() {
		return agingCond;
	}

	public void setAgingCond(Integer agingCond) {
		this.agingCond = agingCond;
	}

	public Integer getFilingCond() {
		return filingCond;
	}

	public void setFilingCond(Integer filingCond) {
		this.filingCond = filingCond;
	}

	public Integer getChargesCond() {
		return chargesCond;
	}

	public void setChargesCond(Integer chargesCond) {
		this.chargesCond = chargesCond;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Integer getMaxClaimScore() {
		return maxClaimScore;
	}

	public void setMaxClaimScore(Integer maxClaimScore) {
		this.maxClaimScore = maxClaimScore;
	}

	public double getClaimScore() {
		return claimScore;
	}

	public void setClaimScore(double claimScore) {
		this.claimScore = claimScore;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

}
