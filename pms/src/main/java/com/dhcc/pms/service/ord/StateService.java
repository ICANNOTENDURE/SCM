/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.ord;

import com.dhcc.pms.dto.ord.StateDto;
import com.dhcc.pms.entity.ord.State;

public interface StateService {

	public void list(StateDto dto);
	
	public void save(StateDto dto);
	
	public void delete(StateDto dto);
	
	public void update(StateDto dto);
	
	public State findById(StateDto dto);
	

}
