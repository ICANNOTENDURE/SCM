package com.dhcc.pms.entity.ven;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the T_VEN_INC database table.
 * 
 */
@Entity
@Table(name="T_VEN_HOP_INC")
public class VenHopInc implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="ven_hop_inc_ID", unique=true, nullable=false)
	private String venHopIncId;
	
	@Column(name="HOP_INC_ID")
	private Long hopIncId;
	
	@Column(name="VEN_INC_ID")
	private Long venIncId;

	@Column(name="VEN_INC_FAC")
	private Long venIncFac;

	/**
	 * @return the venHopIncId
	 */
	public String getVenHopIncId() {
		return venHopIncId;
	}

	/**
	 * @param venHopIncId the venHopIncId to set
	 */
	public void setVenHopIncId(String venHopIncId) {
		this.venHopIncId = venHopIncId;
	}

	/**
	 * @return the hopIncId
	 */
	public Long getHopIncId() {
		return hopIncId;
	}

	/**
	 * @param hopIncId the hopIncId to set
	 */
	public void setHopIncId(Long hopIncId) {
		this.hopIncId = hopIncId;
	}

	/**
	 * @return the venIncId
	 */
	public Long getVenIncId() {
		return venIncId;
	}

	/**
	 * @param venIncId the venIncId to set
	 */
	public void setVenIncId(Long venIncId) {
		this.venIncId = venIncId;
	}

	/**
	 * @return the venIncFac
	 */
	public Long getVenIncFac() {
		return venIncFac;
	}

	/**
	 * @param venIncFac the venIncFac to set
	 */
	public void setVenIncFac(Long venIncFac) {
		this.venIncFac = venIncFac;
	}
	
	
	
	
}