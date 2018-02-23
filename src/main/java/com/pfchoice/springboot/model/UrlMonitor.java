package com.pfchoice.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 *
 * @author Mohanasundharam
 */

@Entity
@Table(name = "url_monitor")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UrlMonitor extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "url_monitor_id", nullable = false)
	private Integer id;

	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prac_prvdr_ins_details_id", nullable = false, referencedColumnName = "prac_prvdr_ins_details_id")
	private PracticeProviderInsuranceDetails pracPrvdrInsDetails;
	
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "login_date")
	private Date loginDate;
	
	
	@Column(name = "login_count")
	private Integer loginCount;
	

	/**
	 * 
	 */
	public UrlMonitor() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public UrlMonitor(final Integer id) {
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
	public PracticeProviderInsuranceDetails getPracPrvdrInsDetails() {
		return pracPrvdrInsDetails;
	}

	/**
	 * @param pracPrvdrInsDetails
	 */
	public void setPracPrvdrInsDetails(PracticeProviderInsuranceDetails pracPrvdrInsDetails) {
		this.pracPrvdrInsDetails = pracPrvdrInsDetails;
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
	public Integer getLoginCount() {
		return loginCount;
	}

	/**
	 * @param loginCount
	 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * @return
	 */
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Override
	public String toString() {
		return "com.infocus.core.entity.UserInsuranceLoginMonitor[ id=" + id + " ]";
	}

}
