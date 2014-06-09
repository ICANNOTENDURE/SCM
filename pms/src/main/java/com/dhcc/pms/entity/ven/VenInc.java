package com.dhcc.pms.entity.ven;

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
 * The persistent class for the T_VEN_INC database table.
 * 
 */
@Entity
@Table(name="T_VEN_INC")
@NamedQuery(name="VenInc.findAll", query="SELECT v FROM VenInc v")
public class VenInc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="idGenerator",strategy="sequence",
	parameters={@Parameter(name="sequence",value="SEQUENCE_VEN_INC")})
	@GeneratedValue(generator="idGenerator")	
	@Column(name="VEN_INC_ID", unique=true, nullable=false, precision=22)
	private Long venIncId;

	@Column(name="VEN_INC_BUOMCODE", length=50)
	private String venIncBuomcode;

	@Column(name="VEN_INC_BUOMNAME", length=50)
	private String venIncBuomname;

	@Column(name="VEN_INC_CODE", length=50)
	private String venIncCode;

	@Column(name="VEN_INC_FAC", precision=22)
	private BigDecimal venIncFac;
	
	@Column(name="VEN_INC_PRICE", precision=22)
	private BigDecimal venIncPrice;
	
	@Column(name="VEN_INC_NAME", length=50)
	private String venIncName;

	@Column(name="VEN_INC_UOMCODE", length=50)
	private String venIncUomcode;

	@Column(name="VEN_INC_UOMNAME", length=50)
	private String venIncUomname;
	
	@Column(name="VEN_INC_MANFID", precision=22)
	private BigDecimal venIncManfid;
	
	@Column(name="VEN_INC_VENID", precision=22)
	private BigDecimal venIncVenid;

	@Column(name="VEN_INC_VENSYSID", precision=22)
	private BigDecimal venIncVensysid;

	

	public VenInc() {
	}

	public VenInc(Long venIncId,  String venIncCode, String venIncName,
			 String venIncUomname, String venIncBuomname, BigDecimal venIncManfid,
			BigDecimal venIncVenid, BigDecimal venIncVensysid) {
		super();
		this.venIncId = venIncId;		
		this.venIncCode = venIncCode;
		this.venIncName = venIncName;
		this.venIncUomname = venIncUomname;
		this.venIncBuomname = venIncBuomname;
		this.venIncManfid = venIncManfid;
		this.venIncVenid = venIncVenid;
		this.venIncVensysid = venIncVensysid;
	}

	public Long getVenIncId() {
		return venIncId;
	}


	public void setVenIncId(Long venIncId) {
		this.venIncId = venIncId;
	}


	public String getVenIncBuomcode() {
		return this.venIncBuomcode;
	}

	public void setVenIncBuomcode(String venIncBuomcode) {
		this.venIncBuomcode = venIncBuomcode;
	}

	public String getVenIncBuomname() {
		return this.venIncBuomname;
	}

	public void setVenIncBuomname(String venIncBuomname) {
		this.venIncBuomname = venIncBuomname;
	}

	public String getVenIncCode() {
		return this.venIncCode;
	}

	public void setVenIncCode(String venIncCode) {
		this.venIncCode = venIncCode;
	}

	public BigDecimal getVenIncFac() {
		return this.venIncFac;
	}

	public void setVenIncFac(BigDecimal venIncFac) {
		this.venIncFac = venIncFac;
	}	

	public BigDecimal getVenIncPrice() {
		return venIncPrice;
	}

	public void setVenIncPrice(BigDecimal venIncPrice) {
		this.venIncPrice = venIncPrice;
	}

	public String getVenIncName() {
		return this.venIncName;
	}

	public void setVenIncName(String venIncName) {
		this.venIncName = venIncName;
	}

	public String getVenIncUomcode() {
		return this.venIncUomcode;
	}

	public void setVenIncUomcode(String venIncUomcode) {
		this.venIncUomcode = venIncUomcode;
	}

	public String getVenIncUomname() {
		return this.venIncUomname;
	}

	public void setVenIncUomname(String venIncUomname) {
		this.venIncUomname = venIncUomname;
	}

	public BigDecimal getVenIncVensysid() {
		return this.venIncVensysid;
	}

	public void setVenIncVensysid(BigDecimal venIncVensysid) {
		this.venIncVensysid = venIncVensysid;
	}

	
	public BigDecimal getVenIncManfid() {
		return venIncManfid;
	}

	public void setVenIncManfid(BigDecimal venIncManfid) {
		this.venIncManfid = venIncManfid;
	}

	public BigDecimal getVenIncVenid() {
		return venIncVenid;
	}

	public void setVenIncVenid(BigDecimal venIncVenid) {
		this.venIncVenid = venIncVenid;
	}

	
}