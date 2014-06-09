/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.hop;

import java.util.List;

import com.dhcc.pms.dto.hop.HopIncPicDto;
import com.dhcc.pms.entity.hop.HopIncPic;
import com.dhcc.pms.entity.vo.hop.HopIncPicVo;

public interface HopIncPicService {

	public void list(HopIncPicDto dto);
	
	public void save(HopIncPicDto dto);
	
	public void delete(HopIncPicDto dto);
	
	public void update(HopIncPicDto dto);
	
	public HopIncPic findById(HopIncPicDto dto);

	public List<HopIncPicVo> getListInfo(HopIncPicDto dto);



}
