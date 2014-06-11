/**
 * Dto 
 * template by zxx
 */
package com.dhcc.pms.dto.hop;

import com.dhcc.pms.entity.hop.HopVendor;
import com.dhcc.framework.transmission.dto.BaseDto;

public class HopVendorDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HopVendor hopVendor;

	private String flag;
	
	
	
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


	public HopVendor getHopVendor() {
		return hopVendor;
	}

	
	public void setHopVendor(HopVendor hopVendor) {
		this.hopVendor = hopVendor;
	}
	
}
