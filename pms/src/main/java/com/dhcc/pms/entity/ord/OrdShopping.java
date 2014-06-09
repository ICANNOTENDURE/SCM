package com.dhcc.pms.entity.ord;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the T_ORD_SHOPPING database table.
 * 
 */
@Entity
@Table(name="T_ORD_SHOPPING")
public class OrdShopping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_ORD_SHOPPING_SHOPID_GENERATOR", sequenceName="SEQUENCE_ORD_SHOPPING")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_ORD_SHOPPING_SHOPID_GENERATOR")
	@Column(name="SHOP_ID")
	private Long shopId;

	@Column(name="SHOP_INCID")
	private Long shopIncid;

	@Column(name="SHOP_QTY")
	private Long shopQty;

	@Column(name="SHOP_UOM")
	private String shopUom;

	@Column(name="SHOP_USERID")
	private Long shopUserid;
	
	@Column(name="SHOP_CHECKFLAG")
	private Long shopCheckFlag;
	
	
	
	
	/**
	 * @param shopId
	 * @param shopIncid
	 * @param shopQty
	 * @param shopUom
	 * @param shopUserid
	 * @param shopCheckFlag
	 */
	public OrdShopping(Long shopId, Long shopIncid, Long shopQty,
			String shopUom, Long shopUserid, Long shopCheckFlag) {
		super();
		this.shopId = shopId;
		this.shopIncid = shopIncid;
		this.shopQty = shopQty;
		this.shopUom = shopUom;
		this.shopUserid = shopUserid;
		this.shopCheckFlag = shopCheckFlag;
	}

	/**
	 * @return the shopCheckFlag
	 */
	public Long getShopCheckFlag() {
		return shopCheckFlag;
	}

	/**
	 * @param shopCheckFlag the shopCheckFlag to set
	 */
	public void setShopCheckFlag(Long shopCheckFlag) {
		this.shopCheckFlag = shopCheckFlag;
	}

	public OrdShopping() {
	}

	public Long getShopId() {
		return this.shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getShopIncid() {
		return this.shopIncid;
	}

	public void setShopIncid(Long shopIncid) {
		this.shopIncid = shopIncid;
	}

	public Long getShopQty() {
		return this.shopQty;
	}

	public void setShopQty(Long shopQty) {
		this.shopQty = shopQty;
	}

	public String getShopUom() {
		return this.shopUom;
	}

	public void setShopUom(String shopUom) {
		this.shopUom = shopUom;
	}

	public Long getShopUserid() {
		return this.shopUserid;
	}

	public void setShopUserid(Long shopUserid) {
		this.shopUserid = shopUserid;
	}

}