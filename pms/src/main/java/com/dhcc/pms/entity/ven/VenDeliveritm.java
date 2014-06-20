package com.dhcc.pms.entity.ven;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the T_VEN_DELIVERITM database table.
 * 
 */
@Entity
@Table(name="T_VEN_DELIVERITM")
public class VenDeliveritm implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long deliveritmId;
	private String deliveritmBatno;
	private Date deliveritmExpdate;
	private Long deliveritmHopincid;
	private String deliveritmInvnoe;
	private Long deliveritmOrderitmid;
	private Long deliveritmParentid;
	private Float deliveritmQty;
	private Float deliveritmRp;
	private Float deliveritmRpamt;
	private String deliveritmUom;
	private Long deliveritmVenincid;

	public VenDeliveritm() {
	}


	@Id
	@SequenceGenerator(name="T_VEN_DELIVERITM_DELIVERITMID_GENERATOR", sequenceName="SEQUENCE_VEN_DELIVERITM")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_VEN_DELIVERITM_DELIVERITMID_GENERATOR")
	@Column(name="DELIVERITM_ID")
	public Long getDeliveritmId() {
		return this.deliveritmId;
	}

	public void setDeliveritmId(Long deliveritmId) {
		this.deliveritmId = deliveritmId;
	}


	@Column(name="DELIVERITM_BATNO")
	public String getDeliveritmBatno() {
		return this.deliveritmBatno;
	}

	public void setDeliveritmBatno(String deliveritmBatno) {
		this.deliveritmBatno = deliveritmBatno;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="DELIVERITM_EXPDATE")
	public Date getDeliveritmExpdate() {
		return this.deliveritmExpdate;
	}

	public void setDeliveritmExpdate(Date deliveritmExpdate) {
		this.deliveritmExpdate = deliveritmExpdate;
	}


	@Column(name="DELIVERITM_HOPINCID")
	public Long getDeliveritmHopincid() {
		return this.deliveritmHopincid;
	}

	public void setDeliveritmHopincid(Long deliveritmHopincid) {
		this.deliveritmHopincid = deliveritmHopincid;
	}


	@Column(name="DELIVERITM_INVNOE")
	public String getDeliveritmInvnoe() {
		return this.deliveritmInvnoe;
	}

	public void setDeliveritmInvnoe(String deliveritmInvnoe) {
		this.deliveritmInvnoe = deliveritmInvnoe;
	}


	@Column(name="DELIVERITM_ORDERITMID")
	public Long getDeliveritmOrderitmid() {
		return this.deliveritmOrderitmid;
	}

	public void setDeliveritmOrderitmid(Long deliveritmOrderitmid) {
		this.deliveritmOrderitmid = deliveritmOrderitmid;
	}


	@Column(name="DELIVERITM_PARENTID")
	public Long getDeliveritmParentid() {
		return this.deliveritmParentid;
	}

	public void setDeliveritmParentid(Long deliveritmParentid) {
		this.deliveritmParentid = deliveritmParentid;
	}


	@Column(name="DELIVERITM_QTY")
	public Float getDeliveritmQty() {
		return this.deliveritmQty;
	}

	public void setDeliveritmQty(Float deliveritmQty) {
		this.deliveritmQty = deliveritmQty;
	}


	@Column(name="DELIVERITM_RP")
	public Float getDeliveritmRp() {
		return this.deliveritmRp;
	}

	public void setDeliveritmRp(Float deliveritmRp) {
		this.deliveritmRp = deliveritmRp;
	}


	@Column(name="DELIVERITM_RPAMT")
	public Float getDeliveritmRpamt() {
		return this.deliveritmRpamt;
	}

	public void setDeliveritmRpamt(Float deliveritmRpamt) {
		this.deliveritmRpamt = deliveritmRpamt;
	}


	@Column(name="DELIVERITM_UOM")
	public String getDeliveritmUom() {
		return this.deliveritmUom;
	}

	public void setDeliveritmUom(String deliveritmUom) {
		this.deliveritmUom = deliveritmUom;
	}


	@Column(name="DELIVERITM_VENINCID")
	public Long getDeliveritmVenincid() {
		return this.deliveritmVenincid;
	}

	public void setDeliveritmVenincid(Long deliveritmVenincid) {
		this.deliveritmVenincid = deliveritmVenincid;
	}

}