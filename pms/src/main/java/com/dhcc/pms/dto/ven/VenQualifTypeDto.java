/**
 * Dto 
 * template by zxx
 */
package com.dhcc.pms.dto.ven;

import com.dhcc.pms.entity.ven.VenQualifType;
import com.dhcc.framework.transmission.dto.BaseDto;

public class VenQualifTypeDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VenQualifType venQualifType;

	
	public VenQualifType getVenQualifType() {
		return venQualifType;
	}

	
	public void setVenQualifType(VenQualifType venQualifType) {
		this.venQualifType = venQualifType;
	}
	
}
