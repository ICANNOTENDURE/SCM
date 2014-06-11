/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.manf;

import java.util.List;

import com.dhcc.pms.dto.manf.HopManfDto;
import com.dhcc.pms.entity.manf.HopManf;

public interface HopManfService {

	public void list(HopManfDto dto);
	
	public void save(HopManfDto dto);
	
	public void delete(HopManfDto dto);
	
	public void update(HopManfDto dto);
	
	public HopManf findById(HopManfDto dto);

	public List<HopManf> getManfInfo(HopManfDto dto);
	
	/**
	 * 
	* @Title: HopManfService.java
	* @Description: TODO(更具产地描述查找产地)
	* @param name
	* @return
	* @return:Long 
	* @author zhouxin  
	* @date 2014年6月10日 下午3:19:30
	* @version V1.0
	 */
	public Long getIdByName(String name);

}
