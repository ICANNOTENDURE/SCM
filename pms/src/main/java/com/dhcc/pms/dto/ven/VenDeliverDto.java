/**
 * Dto 
 * template by zxx
 */
package com.dhcc.pms.dto.ven;

import com.dhcc.pms.entity.ven.VenDeliver;
import com.dhcc.framework.transmission.dto.BaseDto;

public class VenDeliverDto extends BaseDto {

	private VenDeliver venDeliver;

	
	public VenDeliver getVenDeliver() {
		return venDeliver;
	}

	
	public void setVenDeliver(VenDeliver venDeliver) {
		this.venDeliver = venDeliver;
	}
	
}
