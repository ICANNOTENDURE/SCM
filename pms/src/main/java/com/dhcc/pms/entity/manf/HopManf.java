package com.dhcc.pms.entity.manf;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the T_HOP_MANF database table.
 * 
 */
@Entity
@Table(name="T_HOP_MANF")
@NamedQuery(name="HopManf.findAll", query="SELECT h FROM HopManf h")
public class HopManf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="idGenerator",strategy="sequence",
	parameters={@Parameter(name="sequence",value="SEQUENCE_HOP_MANF")})
	@GeneratedValue(generator="idGenerator")
	@Column(name="ID",unique=true, nullable=false, precision=22)
	private Long hopManfId;

	@Column(name="CODE")
	private String manfCode;

	@Column(name="HISID")
	private Long manfHisid;

	@Column(name="NAME")
	private String manfName;
	

	

	
	public HopManf() {
	}

	
	public HopManf(Long hopManfId, String manfName) {
		super();
		this.hopManfId = hopManfId;
		this.manfName = manfName;
	}


	public Long getHopManfId() {
		return hopManfId;
	}

	public void setHopManfId(Long hopManfId) {
		this.hopManfId = hopManfId;
	}

	public String getManfCode() {
		return manfCode;
	}

	public void setManfCode(String manfCode) {
		this.manfCode = manfCode;
	}

	public Long getManfHisid() {
		return manfHisid;
	}

	public void setManfHisid(Long manfHisid) {
		this.manfHisid = manfHisid;
	}

	public String getManfName() {
		return manfName;
	}

	public void setManfName(String manfName) {
		this.manfName = manfName;
	}
	



	


}