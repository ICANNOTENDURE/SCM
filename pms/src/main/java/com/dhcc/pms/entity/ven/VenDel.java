package com.dhcc.pms.entity.ven;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the T_VEN_DELIVER database table.
 * 
 */
@Entity
@Table(name="T_VEN_DELV")
public class VenDel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="delv_id", unique=true, nullable=false)
	private String delvId;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DELV_DATE")
	private Date delvDate; 
	
	@Column(name="DELV_VENDORID")
	private Long delvVendorid;

	/**
	 * @return the delvId
	 */
	public String getDelvId() {
		return delvId;
	}

	/**
	 * @param delvId the delvId to set
	 */
	public void setDelvId(String delvId) {
		this.delvId = delvId;
	}

	/**
	 * @return the delvDate
	 */
	public Date getDelvDate() {
		return delvDate;
	}

	/**
	 * @param delvDate the delvDate to set
	 */
	public void setDelvDate(Date delvDate) {
		this.delvDate = delvDate;
	}

	/**
	 * @return the delvVendorid
	 */
	public Long getDelvVendorid() {
		return delvVendorid;
	}

	/**
	 * @param delvVendorid the delvVendorid to set
	 */
	public void setDelvVendorid(Long delvVendorid) {
		this.delvVendorid = delvVendorid;
	}
	
	
}