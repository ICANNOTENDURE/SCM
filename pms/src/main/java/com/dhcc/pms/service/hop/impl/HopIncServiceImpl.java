/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.hop.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.PagerModel;
import com.dhcc.pms.dao.hop.HopIncDao;
import com.dhcc.pms.dto.hop.HopIncDto;
import com.dhcc.pms.entity.hop.HopInc;
import com.dhcc.pms.entity.vo.hop.HopIncVo;
import com.dhcc.pms.service.hop.HopIncService;

@Service("hopIncService")
public class HopIncServiceImpl implements HopIncService {

	@Resource
	private HopIncDao hopIncDao;
	@Resource
	private CommonService commonService;

	public void list(HopIncDto dto){
	
		PagerModel pagerModel = dto.getPageModel();
		//调用DAO 拼接查询条件
		hopIncDao.buildPagerModelQuery(pagerModel, dto);
		//调用分页查询方法
		commonService.fillPagerModelData(pagerModel);
	}
	
	public void save(HopIncDto dto){
	
		hopIncDao.save(dto.getHopInc());
	}
	
	public void delete(HopIncDto dto){
	
		hopIncDao.delete(dto.getHopInc());
	}
	
	public void update(HopIncDto dto){
	
		hopIncDao.update(dto.getHopInc());
	}
	
	public HopInc findById(HopIncDto dto){
	
		dto.setHopInc(hopIncDao.findById(dto.getHopInc()));
		return dto.getHopInc();
	}

	
	public List<HopIncVo> getIncInfo(HopIncDto dto) {
		return hopIncDao.getIncInfo(dto.getHopIncVos());
	}

	
	public void getListInfo(HopIncDto dto) {
		// 
		hopIncDao.getListInfo(dto);
	}

}
