/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.ven;

import java.util.List;

import com.dhcc.pms.dto.ven.VenIncDto;
import com.dhcc.pms.entity.ven.VenInc;

public interface VenIncService {

	public void list(VenIncDto dto);
	
	public void save(VenIncDto dto);
	
	public void delete(VenIncDto dto);
	
	public void update(VenIncDto dto);
	
	public VenInc findById(VenIncDto dto);

	public List<VenInc> getIncInfo(VenIncDto dto);

	public void getListInfo(VenIncDto dto);
	
	/**
	 * 
	* @Title: VenIncService.java
	* @Description: TODO(医院药品对照查询)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月30日 上午9:02:26
	* @version V1.0
	 */
	public void listContrantInc(VenIncDto dto);
	
	/**
	 * 
	* @Title: VenIncService.java
	* @Description: TODO(保存药品对照)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月30日 下午5:41:58
	* @version V1.0
	 */
	public void saveContranst(VenIncDto dto);
}
