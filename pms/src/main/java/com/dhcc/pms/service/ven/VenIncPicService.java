/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.ven;

import java.util.List;

import com.dhcc.pms.dto.ven.VenIncPicDto;
import com.dhcc.pms.entity.ven.VenIncPic;
import com.dhcc.pms.entity.vo.ven.VenIncPicVo;

public interface VenIncPicService {

	public void list(VenIncPicDto dto);
	
	public void save(VenIncPicDto dto);
	
	public void delete(VenIncPicDto dto);
	
	public void update(VenIncPicDto dto);
	
	public VenIncPic findById(VenIncPicDto dto);

	public List<VenIncPicVo> getListInfo(VenIncPicDto dto);
	

}
