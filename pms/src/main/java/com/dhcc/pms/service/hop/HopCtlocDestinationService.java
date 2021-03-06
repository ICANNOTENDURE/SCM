/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.hop;

import com.dhcc.pms.dto.hop.HopCtlocDestinationDto;
import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.hop.HopCtlocDestination;

public interface HopCtlocDestinationService {

	public void list(HopCtlocDestinationDto dto);
	
	public void save(HopCtlocDestinationDto dto);
	
	public void delete(HopCtlocDestinationDto dto);
	
	public void update(HopCtlocDestinationDto dto);
	
	public HopCtlocDestination findById(HopCtlocDestinationDto dto);

	public HopCtloc getCtloc(HopCtlocDestinationDto dto) throws Exception;

	public void getListInfo(HopCtlocDestinationDto dto);
	
	public HopCtlocDestination getDesctionByCode(String code,Long hopId);

}
