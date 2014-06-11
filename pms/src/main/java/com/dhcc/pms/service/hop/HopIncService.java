/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.hop;

import java.util.List;

import com.dhcc.pms.dto.hop.HopIncDto;
import com.dhcc.pms.entity.hop.HopInc;
import com.dhcc.pms.entity.vo.hop.HopIncVo;

public interface HopIncService {

	public void list(HopIncDto dto);
	
	public void save(HopIncDto dto);
	
	public void delete(HopIncDto dto);
	
	public void update(HopIncDto dto);
	
	public HopInc findById(HopIncDto dto);

	public List<HopIncVo> getIncInfo(HopIncDto dto);

	public void getListInfo(HopIncDto dto);
	
	/**
	 * 
	* @Title: HopIncService.java
	* @Description: TODO(保存导入药品信息)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月10日 下午3:32:22
	* @version V1.0
	 */
	public void saveInc(HopIncDto dto);
	
}
