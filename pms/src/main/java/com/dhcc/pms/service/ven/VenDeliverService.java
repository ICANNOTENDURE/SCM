/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.ven;

import com.dhcc.pms.dto.ven.VenDeliverDto;
import com.dhcc.pms.entity.ven.VenDeliver;

public interface VenDeliverService {

	public void list(VenDeliverDto dto);
	
	public void save(VenDeliverDto dto);
	
	public void delete(VenDeliverDto dto);
	
	public void update(VenDeliverDto dto);
	
	public VenDeliver findById(VenDeliverDto dto);
	

}
