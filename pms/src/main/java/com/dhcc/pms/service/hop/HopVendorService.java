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
	
	/**
	 * 
	* @Title: HopVendorService.java
	* @Description: TODO(批量导入医院供应商)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月12日 下午12:48:24
	* @version V1.0
	 */
	public void exportVendor(HopVendorDto dto);
	
	/**
	 * 
	* @Title: HopVendorService.java
	* @Description: TODO(供应商名称找ID)
	* @param name
	* @return
	* @return:Long 
	* @author zhouxin  
	* @date 2014年6月12日 下午2:08:30
	* @version V1.0
	 */
	public Long findVendorIdByName(String name);
}
