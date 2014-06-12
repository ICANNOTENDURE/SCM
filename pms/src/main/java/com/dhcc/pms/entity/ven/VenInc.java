package com.dhcc.pms.entity.ven;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the T_VEN_INC database table.
 * 
 */
@Entity
@Table(name="T_VEN_INC")
public class VenInc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="idGenerator",strategy="sequence",
	parameters={@Parameter(name="sequence",value="SEQUENCE_VEN_INC")})
	@GeneratedValue(generator="idGenerator")	
	@Column(name="VEN_INC_ROWID", unique=true, nullable=false)
	private Long venIncId;

	@Column(name="VEN_INC_BUOMCODE")
	private String venIncBuomcode;

	@Column(name="VEN_INC_BUOMNAME")
	private String venIncBuomname;

	@Column(name="VEN_INC_CODE")
	private String venIncCode;

	@Column(name="VEN_INC_FAC")
	private  Long venIncFac;
	
	@Column(name="VEN_INC_PRICE")
	private Long venIncPrice;
	
	@Column(name="VEN_INC_NAME")
	private String venIncName;

	@Column(name="VEN_INC_UOMCODE")
	private String venIncUomcode;

	@Column(name="VEN_INC_UOMNAME")
	private String venIncUomname;
	
	@Column(name="VEN_INC_MANFID")
	private Long venIncManfid;
	
	@Column(name="VEN_INC_VENID")
	private Long venIncVenid;

	@Column(name="VEN_INC_VENSYSID")
	private String venIncVensysid;
	
	@Column(name="VEN_INC_SPEC")
	private String venIncSpec;
	
	@Column(name="VEN_INC_CAT")
	private String venIncCat;
	
	@Column(name="VEN_INC_ALIAS")
	private String venIncAlias;
	
	@Column(name="VEN_INC_Sp")
	private Long venIncSp;
	
	public VenInc() {
	}

	public VenInc(Long venIncId,  String venIncCode, String venIncName,
			 String venIncUomname, String venIncBuomname, Long venIncManfid,
			Long venIncVenid, String venIncVensysid) {
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

	/**
	 * @return the venIncPrice
	 */
	public Long getVenIncPrice() {
		return venIncPrice;
	}

	/**
	 * @param venIncPrice the venIncPrice to set
	 */
	public void setVenIncPrice(Long venIncPrice) {
		this.venIncPrice = venIncPrice;
	}

	/**
	 * @return the venIncName
	 */
	public String getVenIncName() {
		return venIncName;
	}

	/**
	 * @param venIncName the venIncName to set
	 */
	public void setVenIncName(String venIncName) {
		this.venIncName = venIncName;
	}

	/**
	 * @return the venIncUomcode
	 */
	public String getVenIncUomcode() {
		return venIncUomcode;
	}

	/**
	 * @param venIncUomcode the venIncUomcode to set
	 */
	public void setVenIncUomcode(String venIncUomcode) {
		this.venIncUomcode = venIncUomcode;
	}

	/**
	 * @return the venIncUomname
	 */
	public String getVenIncUomname() {
		return venIncUomname;
	}

	/**
	 * @param venIncUomname the venIncUomname to set
	 */
	public void setVenIncUomname(String venIncUomname) {
		this.venIncUomname = venIncUomname;
	}

	/**
	 * @return the venIncManfid
	 */
	public Long getVenIncManfid() {
		return venIncManfid;
	}

	/**
	 * @param venIncManfid the venIncManfid to set
	 */
	public void setVenIncManfid(Long venIncManfid) {
		this.venIncManfid = venIncManfid;
	}

	/**
	 * @return the venIncVenid
	 */
	public Long getVenIncVenid() {
		return venIncVenid;
	}

	/**
	 * @param venIncVenid the venIncVenid to set
	 */
	public void setVenIncVenid(Long venIncVenid) {
		this.venIncVenid = venIncVenid;
	}

	/**
	 * @return the venIncVensysid
	 */
	public String getVenIncVensysid() {
		return venIncVensysid;
	}

	/**
	 * @param venIncVensysid the venIncVensysid to set
	 */
	public void setVenIncVensysid(String venIncVensysid) {
		this.venIncVensysid = venIncVensysid;
	}

	/**
	 * @return the venIncSpec
	 */
	public String getVenIncSpec() {
		return venIncSpec;
	}

	/**
	 * @param venIncSpec the venIncSpec to set
	 */
	public void setVenIncSpec(String venIncSpec) {
		this.venIncSpec = venIncSpec;
	}

	/**
	 * @return the venIncCat
	 */
	public String getVenIncCat() {
		return venIncCat;
	}

	/**
	 * @param venIncCat the venIncCat to set
	 */
	public void setVenIncCat(String venIncCat) {
		this.venIncCat = venIncCat;
	}

	/**
	 * @return the venIncAlias
	 */
	public String getVenIncAlias() {
		return venIncAlias;
	}

	/**
	 * @param venIncAlias the venIncAlias to set
	 */
	public void setVenIncAlias(String venIncAlias) {
		this.venIncAlias = venIncAlias;
	}

	/**
	 * @return the venIncSp
	 */
	public Long getVenIncSp() {
		return venIncSp;
	}

	/**
	 * @param venIncSp the venIncSp to set
	 */
	public void setVenIncSp(Long venIncSp) {
		this.venIncSp = venIncSp;
	}

	

	
}