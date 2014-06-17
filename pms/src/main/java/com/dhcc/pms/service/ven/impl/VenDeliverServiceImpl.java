/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.ven.impl;

import org.springframework.stereotype.Service;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.app.service.CommonServiceImpl;
import com.dhcc.framework.common.PagerModel;
import javax.annotation.Resource;

import com.dhcc.pms.service.ven.VenDeliverService;
import com.dhcc.pms.dao.ven.VenDeliverDao;
import com.dhcc.pms.dto.ven.VenDeliverDto;
import com.dhcc.pms.entity.ven.VenDeliver;

@Service("venDeliverService")
public class VenDeliverServiceImpl implements VenDeliverService {

	@Resource
	private VenDeliverDao venDeliverDao;
	@Resource
	private CommonService commonService;

	public void list(VenDeliverDto dto){
	
		PagerModel pagerModel = dto.getPageModel();
		//调用DAO 拼接查询条件
		venDeliverDao.buildPagerModelQuery(pagerModel, dto);
		//调用分页查询方法
		commonService.fillPagerModelData(pagerModel);
	}
	
	public void save(VenDeliverDto dto){
	
		venDeliverDao.save(dto.getVenDeliver());
	}
	
	public void delete(VenDeliverDto dto){
	
		venDeliverDao.delete(dto.getVenDeliver());
	}
	
	public void update(VenDeliverDto dto){
	
		venDeliverDao.update(dto.getVenDeliver());
	}
	
	public VenDeliver findById(VenDeliverDto dto){
	
		dto.setVenDeliver(venDeliverDao.findById(dto.getVenDeliver()));
		return dto.getVenDeliver();
	}

}
