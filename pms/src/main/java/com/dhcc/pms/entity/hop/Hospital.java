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
 * The persistent class for the T_SYS_HOSPITAL database table.
 * 
 */
@Entity
@Table(name="T_SYS_HOSPITAL")
@NamedQuery(name="Hospital.findAll", query="SELECT h FROM Hospital h")
public class Hospital implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GenericGenerator(name="idGenerator",strategy="sequence",
	parameters={@Parameter(name="sequence",value="SEQUENCE_SYS_HOSPITAL")})
	@GeneratedValue(generator="idGenerator")	
	@Column(name="HOSPITAL_ID", unique=true, nullable=false, precision=22)
	private Long hospitalId;

	@Column(name="HOSPITAL_CODE", length=50)
	private String hospitalCode;

	@Column(name="HOSPITAL_HISDR", precision=22)
	private Long hospitalHisdr;

	@Column(name="HOSPITAL_NAME", length=50)
	private String hospitalName;


	
	public Hospital() {
	}

	
	public Hospital(Long hospitalId, String hospitalName) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
	}


	public Long getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalCode() {
		return this.hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public Long getHospitalHisdr() {
		return this.hospitalHisdr;
	}

	public void setHospitalHisdr(Long hospitalHisdr) {
		this.hospitalHisdr = hospitalHisdr;
	}

	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}




	




}