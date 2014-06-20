/**
 * Dto 
 * template by zxx
 */
package com.dhcc.pms.dto.ven;

import java.sql.Timestamp;
import java.util.Date;

import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.pms.entity.ven.VenDeliver;
import com.dhcc.pms.entity.ven.VenDeliveritm;

public class VenDeliverDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VenDeliver venDeliver;
	private VenDeliveritm venDeliveritm;
	private Date stDate;
	private Date edDate;
	private Date accpStDate;
	private Date accpEdDate;
	private Long hopId;
	private Long purLocId;
	private String emFlag;
	private Long state;
	
	
	
	
	/**
	 * @return the venDeliveritm
	 */
	public VenDeliveritm getVenDeliveritm() {
		return venDeliveritm;
	}


	/**
	 * @param venDeliveritm the venDeliveritm to set
	 */
	public void setVenDeliveritm(VenDeliveritm venDeliveritm) {
		this.venDeliveritm = venDeliveritm;
	}


	/**
	 * @return the state
	 */
	public Long getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(Long state) {
		this.state = state;
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
	 * @return the purLocId
	 */
	public Long getPurLocId() {
		return purLocId;
	}


	/**
	 * @param purLocId the purLocId to set
	 */
	public void setPurLocId(Long purLocId) {
		this.purLocId = purLocId;
	}


	/**
	 * @return the venDeliver
	 */
	public VenDeliver getVenDeliver() {
		return venDeliver;
	}


	/**
	 * @param venDeliver the venDeliver to set
	 */
	public void setVenDeliver(VenDeliver venDeliver) {
		this.venDeliver = venDeliver;
	}


	/**
	 * @return the stDate
	 */
	public Date getStDate() {
		return stDate;
	}


	/**
	 * @param stDate the stDate to set
	 */
	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}


	/**
	 * @return the edDate
	 */
	public Date getEdDate() {
		return edDate;
	}


	/**
	 * @param edDate the edDate to set
	 */
	public void setEdDate(Date edDate) {
		this.edDate = edDate;
	}


	/**
	 * @return the accpStDate
	 */
	public Date getAccpStDate() {
		return accpStDate;
	}


	/**
	 * @param accpStDate the accpStDate to set
	 */
	public void setAccpStDate(Date accpStDate) {
		this.accpStDate = accpStDate;
	}


	/**
	 * @return the accpEdDate
	 */
	public Date getAccpEdDate() {
		return accpEdDate;
	}


	/**
	 * @param accpEdDate the accpEdDate to set
	 */
	public void setAccpEdDate(Date accpEdDate) {
		this.accpEdDate = accpEdDate;
	}

	
		
}
