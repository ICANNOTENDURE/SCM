package com.dhcc.pms.entity.vo.ws;

import java.util.Date;
import java.util.List;

import com.dhcc.framework.util.StringUtils;




public class OrderWebVo implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderno;
	
	private Long orderid;
	
	private String hopname;
	
	private String purloc;
	
	private String recloc;
	
	private String desction;
	
	private String remark;
	
	private String emflag;
	
	private Date deliverdate;
	
	private List<OrderItmWebVo> orderItmWSVos;
	
	private String operateresuslt;
	
	private String operateContent;
	
	

	/**
	 * @return the operateresuslt
	 */
	public String getOperateresuslt() {
		return operateresuslt;
	}

	/**
	 * @param operateresuslt the operateresuslt to set
	 */
	public void setOperateresuslt(String operateresuslt) {
		this.operateresuslt = operateresuslt;
	}

	/**
	 * @return the operateContent
	 */
	public String getOperateContent() {
		return operateContent;
	}

	/**
	 * @param operateContent the operateContent to set
	 */
	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	/**
	 * @return the deliverdate
	 */
	public Date getDeliverdate() {
		return deliverdate;
	}

	/**
	 * @param deliverdate the deliverdate to set
	 */
	public void setDeliverdate(Date deliverdate) {
		this.deliverdate = deliverdate;
	}

	/**
	 * @return the orderItmWSVos
	 */
	public List<OrderItmWebVo> getOrderItmWSVos() {
		return orderItmWSVos;
	}

	/**
	 * @param orderItmWSVos the orderItmWSVos to set
	 */
	public void setOrderItmWSVos(List<OrderItmWebVo> orderItmWSVos) {
		this.orderItmWSVos = orderItmWSVos;
	}

	/**
	 * @return the orderno
	 */
	public String getOrderno() {
		return orderno;
	}

	/**
	 * @param orderno the orderno to set
	 */
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	/**
	 * @return the orderid
	 */
	public Long getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the hopname
	 */
	public String getHopname() {
		return hopname;
	}

	/**
	 * @param hopname the hopname to set
	 */
	public void setHopname(String hopname) {
		this.hopname = hopname;
	}

	/**
	 * @return the purloc
	 */
	public String getPurloc() {
		return purloc;
	}

	/**
	 * @param purloc the purloc to set
	 */
	public void setPurloc(String purloc) {
		this.purloc = purloc;
	}

	/**
	 * @return the recloc
	 */
	public String getRecloc() {
		return recloc;
	}

	/**
	 * @param recloc the recloc to set
	 */
	public void setRecloc(String recloc) {
		this.recloc = recloc;
	}

	/**
	 * @return the desction
	 */
	public String getDesction() {
			return desction;
	}

	/**
	 * @param desction the desction to set
	 */
	public void setDesction(String desction) {
		this.desction = desction;
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
	 * @return the emflag
	 */
	public String getEmflag() {
		if(!StringUtils.isNullOrEmpty(emflag)){
			if(emflag.equals("checked")){
				emflag="Y";
			}
		}
		return emflag;
	}

	/**
	 * @param emflag the emflag to set
	 */
	public void setEmflag(String emflag) {
		this.emflag = emflag;
	}

	/**
	 * 
	 */
	public OrderWebVo() {
	}
	
	
	
}
