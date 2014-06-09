/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.hop.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.PagerModel;

import javax.annotation.Resource;

import com.dhcc.pms.service.hop.HopCtlocDestinationService;
import com.dhcc.pms.dao.hop.HopCtlocDestinationDao;
import com.dhcc.pms.dto.hop.HopCtlocDestinationDto;
import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.hop.HopCtlocDestination;
import com.dhcc.pms.entity.vo.hop.HopDestinationVo;

@Service("hopCtlocDestinationService")
public class HopCtlocDestinationServiceImpl implements HopCtlocDestinationService {

	@Resource
	private HopCtlocDestinationDao hopCtlocDestinationDao;
	@Resource
	private CommonService commonService;

	public void list(HopCtlocDestinationDto dto){
	
		PagerModel pagerModel = dto.getPageModel();
		//调用DAO 拼接查询条件
		hopCtlocDestinationDao.buildPagerModelQuery(pagerModel, dto);
		//调用分页查询方法
		commonService.fillPagerModelData(pagerModel);
	}
	
	public void save(HopCtlocDestinationDto dto){
	
		hopCtlocDestinationDao.save(dto.getHopCtlocDestination());
	}
	
	public void delete(HopCtlocDestinationDto dto){
	
		hopCtlocDestinationDao.delete(dto.getHopCtlocDestination());
	}
	
	public void update(HopCtlocDestinationDto dto){
	
		hopCtlocDestinationDao.update(dto.getHopCtlocDestination());
	}
	
	public HopCtlocDestination findById(HopCtlocDestinationDto dto){
	
		dto.setHopCtlocDestination(hopCtlocDestinationDao.findById(dto.getHopCtlocDestination()));
		return dto.getHopCtlocDestination();
	}

	public HopCtloc getCtloc(HopCtlocDestinationDto dto) throws Exception {
		return hopCtlocDestinationDao.getCtloc(dto);
	}

	public List<HopDestinationVo> getListInfo(HopCtlocDestinationDto dto) {
		// hopCtlocDao.getListInfo(dto.getHopCtlocVos());
		PagerModel pagerModel=dto.getPageModel();
		return hopCtlocDestinationDao.getListInfo(pagerModel,dto.getHopDestinationVos(),dto.getHopCtlocDestination());
	}

	

}
