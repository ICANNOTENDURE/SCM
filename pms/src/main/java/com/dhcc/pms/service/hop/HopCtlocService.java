/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.hop;

import java.util.List;

import com.dhcc.pms.dto.hop.HopCtlocDto;
import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.vo.hop.HopCtlocVo;

public interface HopCtlocService {

	public void list(HopCtlocDto dto);
	
	public void save(HopCtlocDto dto);
	
	public void delete(HopCtlocDto dto);
	
	public void update(HopCtlocDto dto);
	
	public HopCtloc findById(HopCtlocDto dto);

	/**
	 * @param dto
	 */
	public List<HopCtloc> getCtlocInfo(HopCtlocDto dto);

	/**
	 * @param dto
	 * @return
	 */
	public List<HopCtlocVo> getListInfo(HopCtlocDto dto);
	
	

}
