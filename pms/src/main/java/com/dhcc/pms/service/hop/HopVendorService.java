/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.hop;

import com.dhcc.pms.dto.hop.HopVendorDto;
import com.dhcc.pms.entity.hop.HopVendor;

public interface HopVendorService {

	public void list(HopVendorDto dto);
	
	public void save(HopVendorDto dto);
	
	public void delete(HopVendorDto dto);
	
	public void update(HopVendorDto dto);
	
	public HopVendor findById(HopVendorDto dto);
	
	/**
	 * 
	* @Title: HopVendorService.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 上午10:07:19
	* @version V1.0
	 */
	public void listHopCon(HopVendorDto dto);
}
