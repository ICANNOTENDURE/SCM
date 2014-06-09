package com.dhcc.pms.entity.hop;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the T_SYS_CTLOC database table.
 * 
 */
@Entity
@Table(name="T_SYS_CTLOC")
@NamedQuery(name="HopCtloc.findAll", query="SELECT h FROM HopCtloc h")
public class HopCtloc implements Serializable {
	private static final long serialVersionUID = 1L;	
	@Id
	@GenericGenerator(name="idGenerator",strategy="sequence",
	parameters={@Parameter(name="sequence",value="SEQUENCE_SYS_CTLOC")})
	@GeneratedValue(generator="idGenerator")
	@Column(name="CTLOC_ID")
	private Long hopCtlocId;

	@Column(name="CTLOC_CODE")
	private String code;

	@Column(name="CTLOC_HISID")
	private Long hisid;

	@Column(name="CTLOC_HOSPID")
	private Long hospid;

	@Column(name="CTLOC_NAME")
	private String name;
	

			
	public HopCtloc() {
	}
	
	public HopCtloc(Long hopCtlocId, String name) {
		super();
		this.hopCtlocId = hopCtlocId;
		this.name = name;
	}



	public Long getHopCtlocId() {
		return hopCtlocId;
	}



	public void setHopCtlocId(Long hopCtlocId) {
		this.hopCtlocId = hopCtlocId;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public Long getHisid() {
		return hisid;
	}



	public void setHisid(Long hisid) {
		this.hisid = hisid;
	}



	public Long getHospid() {
		return hospid;
	}



	public void setHospid(Long hospid) {
		this.hospid = hospid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


 
	

}