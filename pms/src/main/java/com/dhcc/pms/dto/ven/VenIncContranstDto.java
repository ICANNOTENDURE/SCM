/**
 * Dto 
 * template by zxx
 */
package com.dhcc.pms.dto.ven;

import com.dhcc.framework.transmission.dto.BaseDto;

public class VenIncContranstDto extends BaseDto {
	
	private static final long serialVersionUID = 1L;

	
	private String incName;
	
	private Long hopId;
	
	private Long venId;

	/**
	 * @return the incName
	 */
	public String getIncName() {
		return incName;
	}

	/**
	 * @param incName the incName to set
	 */
	public void setIncName(String incName) {
		this.incName = incName;
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
	 * @return the venId
	 */
	public Long getVenId() {
		return venId;
	}

	/**
	 * @param venId the venId to set
	 */
	public void setVenId(Long venId) {
		this.venId = venId;
	}
	
	
	
}
