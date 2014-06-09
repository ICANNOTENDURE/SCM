package com.dhcc.pms.entity.ord;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the T_ORD_ORDER database table.
 * 
 */
@Entity
@Table(name="T_ORD_ORDER")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_ORD_ORDER_ORDERID_GENERATOR", sequenceName="SEQUENCE_ORD_ORDER")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_ORD_ORDER_ORDERID_GENERATOR")
	@Column(name="ORDER_ID")
	private Long orderId;

	@Temporal(TemporalType.DATE)
	private Date deliveryDate;
	
	@Column(name="EMFLAG")
	private String emFlag;

	@Column(name="EXESTATE_ID")
	private Long exeStateId;

	@Column(name="HOP_ID")
	private Long hopId;

	@Column(name="ORDER_NO")
	private String orderNo;

	@Temporal(TemporalType.DATE)
	@Column(name="PLANARRDATE")
	private Date planArrDate;

	@Temporal(TemporalType.DATE)
	@Column(name="PLANDATE")
	private Date planDate;
	
	@Column(name="REMARK")
	private String remark;

	@Column(name="VENDOR_ID")
	private Long vendorId;
	
	@Column(name="RECLOC")
	private Long recLoc;
	
	@Column(name="RECDESTINATION")
	private Long recDestination;
	
	@Column(name="CREATEUSER")
	private Long createUser;
	
	@Column(name="PURLOC")
	private Long purLoc;
	
	public Order() {
	}
	
	
	
	
	/**
	 * @return the createUser
	 */
	public Long getCreateUser() {
		return createUser;
	}




	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}




	/**
	 * @return the purLoc
	 */
	public Long getPurLoc() {
		return purLoc;
	}




	/**
	 * @param purLoc the purLoc to set
	 */
	public void setPurLoc(Long purLoc) {
		this.purLoc = purLoc;
	}




	/**
	 * @return the recLoc
	 */
	public Long getRecLoc() {
		return recLoc;
	}




	/**
	 * @param recLoc the recLoc to set
	 */
	public void setRecLoc(Long recLoc) {
		this.recLoc = recLoc;
	}




	/**
	 * @return the recDestination
	 */
	public Long getRecDestination() {
		return recDestination;
	}




	/**
	 * @param recDestination the recDestination to set
	 */
	public void setRecDestination(Long recDestination) {
		this.recDestination = recDestination;
	}




	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the emFlag
	 */
	public String getEmFlag() {
		return emFlag;
	}

	/**
	 * @param emFlag the emFlag to set
	 */
	public void setEmFlag(String emFlag) {
		this.emFlag = emFlag;
	}

	/**
	 * @return the exeStateId
	 */
	public Long getExeStateId() {
		return exeStateId;
	}

	/**
	 * @param exeStateId the exeStateId to set
	 */
	public void setExeStateId(Long exeStateId) {
		this.exeStateId = exeStateId;
	}

	/**
	 * @return the hopId
	 */
	public Long getHopId() {
		return hopId;
	}

	/**
	 * @param hopId the hopId to set
	 */
	public void setHopId(Long hopId) {
		this.hopId = hopId;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the planArrDate
	 */
	public Date getPlanArrDate() {
		return planArrDate;
	}

	/**
	 * @param planArrDate the planArrDate to set
	 */
	public void setPlanArrDate(Date planArrDate) {
		this.planArrDate = planArrDate;
	}

	/**
	 * @return the planDate
	 */
	public Date getPlanDate() {
		return planDate;
	}

	/**
	 * @param planDate the planDate to set
	 */
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the vendorId
	 */
	public Long getVendorId() {
		return vendorId;
	}

	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	

}