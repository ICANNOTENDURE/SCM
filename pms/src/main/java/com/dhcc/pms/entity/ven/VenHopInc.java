package com.dhcc.pms.entity.ven;

import java.io.Serializable;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


/**
 * The persistent class for the T_VEN_INC database table.
 * 
 */
@Entity
@Table(name="T_VEN_HOP_INC")
@IdClass(VenHopIncId.class)
public class VenHopInc implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	private Long hopIncId;
	private Long venIncId;

	
	
	@Id
	@AttributeOverrides({
		@AttributeOverride(name="hopIncId",
	    column=@Column(name="HOP_INC_ID")),
	    @AttributeOverride(name="venIncId",
	    column=@Column(name="VEN_INC_ID"))
	})
	
	/**
	 * @return the hopIncId
	 */
	@Column(name = "HOP_INC_ID")
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
	@Column(name = "VEN_INC_ID")
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
	 * @param hopIncId
	 * @param venIncId
	 */
	public VenHopInc(Long hopIncId, Long venIncId) {
		super();
		this.hopIncId = hopIncId;
		this.venIncId = venIncId;
	}



	/**
	 * 
	 */
	public VenHopInc() {
		super();
	}

}