package com.dhcc.pms.entity.vo.ord;

import java.util.Date;



public class OrderExeStateVo implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String statedesc;
	
	private Date exedate;
	
	private String exeuser;
	
	private String remark;

	/**
	 * @return the statedesc
	 */
	public String getStatedesc() {
		return statedesc;
	}

	/**
	 * @param statedesc the statedesc to set
	 */
	public void setStatedesc(String statedesc) {
		this.statedesc = statedesc;
	}

	/**
	 * @return the exedate
	 */
	public Date getExedate() {
		return exedate;
	}

	/**
	 * @param exedate the exedate to set
	 */
	public void setExedate(Date exedate) {
		this.exedate = exedate;
	}

	/**
	 * @return the exeuser
	 */
	public String getExeuser() {
		return exeuser;
	}

	/**
	 * @param exeuser the exeuser to set
	 */
	public void setExeuser(String exeuser) {
		this.exeuser = exeuser;
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
	
	
	
}
