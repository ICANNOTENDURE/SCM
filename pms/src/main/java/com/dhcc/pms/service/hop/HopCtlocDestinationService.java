/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.hop;

import java.util.List;

import com.dhcc.pms.dto.hop.HopCtlocDestinationDto;
import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.hop.HopCtlocDestination;
import com.dhcc.pms.entity.vo.hop.HopDestinationVo;

public interface HopCtlocDestinationService {

	public void list(HopCtlocDestinationDto dto);
	
	public void save(HopCtlocDestinationDto dto);
	
	public void delete(HopCtlocDestinationDto dto);
	
	public void update(HopCtlocDestinationDto dto);
	
	public HopCtlocDestination findById(HopCtlocDestinationDto dto);

	public HopCtloc getCtloc(HopCtlocDestinationDto dto) throws Exception;

	public List<HopDestinationVo> getListInfo(HopCtlocDestinationDto dto);
	

}
