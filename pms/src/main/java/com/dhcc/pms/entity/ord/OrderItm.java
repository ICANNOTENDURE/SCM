package com.dhcc.pms.entity.ord;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the T_ORD_ORDERITM database table.
 * 
 */
@Entity
@Table(name="T_ORD_ORDERITM")
public class OrderItm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_ORD_ORDERITM_ORDERITMID_GENERATOR", sequenceName="SEQUENCE_ORD_ORDERITM")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_ORD_ORDERITM_ORDERITMID_GENERATOR")
	@Column(name="ORDERITM_ID")
	private Long orderitmId;
	
	@Column(name="DELIVERQTY")
	private Float deliverqty;

	@Column(name="INC_ID")
	private Long incId;

	@Column(name="ORD_ID")
	private Long ordId;
	
	@Column(name="RECDESTINATION")
	private Long recdestination;
	
	@Column(name="RECLOC")
	private Long recloc;
	
	@Column(name="REQQTY")
	private Float reqqty;
	
	@Column(name="RP")
	private Float rp;
	
	@Column(name="UOM")
	private String uom;
	
	@Column(name="flag")
	private String flag;
	
	public OrderItm() {
	}
	
	
	
	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}



	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}



	public Long getOrderitmId() {
		return this.orderitmId;
	}

	public void setOrderitmId(Long orderitmId) {
		this.orderitmId = orderitmId;
	}

	public Float getDeliverqty() {
		return this.deliverqty;
	}

	public void setDeliverqty(Float deliverqty) {
		this.deliverqty = deliverqty;
	}

	public Long getIncId() {
		return this.incId;
	}

	public void setIncId(Long incId) {
		this.incId = incId;
	}

	public Long getOrdId() {
		return this.ordId;
	}

	public void setOrdId(Long ordId) {
		this.ordId = ordId;
	}

	public Long getRecdestination() {
		return this.recdestination;
	}

	public void setRecdestination(Long recdestination) {
		this.recdestination = recdestination;
	}

	public Long getRecloc() {
		return this.recloc;
	}

	public void setRecloc(Long recloc) {
		this.recloc = recloc;
	}

	public Float getReqqty() {
		return this.reqqty;
	}

	public void setReqqty(Float reqqty) {
		this.reqqty = reqqty;
	}

	public Float getRp() {
		return this.rp;
	}

	public void setRp(Float rp) {
		this.rp = rp;
	}

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

}