package com.dhcc.pms.entity.ord;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the T_ORD_EXESTATE database table.
 * 
 */
@Entity
@Table(name="T_ORD_EXESTATE")
public class ExeState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_ORD_EXESTATE_EXESTATEID_GENERATOR", sequenceName="SEQUENCE_ORD_EXESTATE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_ORD_EXESTATE_EXESTATEID_GENERATOR")
	@Column(name="EXESTATE_ID")
	private Long exestateId;

	@Temporal(TemporalType.DATE)
	private Date exedate;

	@Column(name="ORD_ID")
	private Long ordId;
	
	@Column(name="REMARK")
	private String remark;

	@Column(name="STATE_ID")
	private Long stateId;
	
	@Column(name="TEL")
	private String tel;
	
	@Column(name="USER_ID")
	private Long userid;

	public ExeState() {
	}

	public Long getExestateId() {
		return this.exestateId;
	}

	public void setExestateId(Long exestateId) {
		this.exestateId = exestateId;
	}

	public Date getExedate() {
		return this.exedate;
	}

	public void setExedate(Date exedate) {
		this.exedate = exedate;
	}

	public Long getOrdId() {
		return this.ordId;
	}

	public void setOrdId(Long ordId) {
		this.ordId = ordId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getStateId() {
		return this.stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

}