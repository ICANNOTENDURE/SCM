package com.dhcc.pms.entity.hop;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The persistent class for the T_SYS_CTLOC_DESTINATION database table.
 * 
 */
@Entity
@Table(name="T_SYS_CTLOC_DESTINATION")
public class HopCtlocDestination implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GenericGenerator(name="idGenerator",strategy="sequence",
	parameters={@Parameter(name="sequence",value="SEQUENCE_SYS_CTLOC_DESTINATION")})
	@GeneratedValue(generator="idGenerator")
	@Column(name="CTLOCDES_ID")	
	private Long hopCtlocDestinationId;

	@Column(name="CTLOCDES_CONTACT")
	private String contact;

	@Column(name="CTLOCDES_DESTINATION")
	private String destination;

	@Column(name="CTLOCDES_TEL")
	private String tel;
	
	@Column(name="CTLOCDES_CTLOCDR")
	private String ctlocDr;

	

	public HopCtlocDestination() {
	}

	
	
	
	
	
	/**
	 * @param hopCtlocDestinationId
	 * @param destination
	 */
	public HopCtlocDestination(Long hopCtlocDestinationId, String destination) {
		super();
		this.hopCtlocDestinationId = hopCtlocDestinationId;
		this.destination = destination;
	}






	public Long getHopCtlocDestinationId() {
		return hopCtlocDestinationId;
	}


	public void setHopCtlocDestinationId(Long hopCtlocDestinationId) {
		this.hopCtlocDestinationId = hopCtlocDestinationId;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCtlocDr() {
		return ctlocDr;
	}


	public void setCtlocDr(String ctlocDr) {
		this.ctlocDr = ctlocDr;
	}
	
	

}