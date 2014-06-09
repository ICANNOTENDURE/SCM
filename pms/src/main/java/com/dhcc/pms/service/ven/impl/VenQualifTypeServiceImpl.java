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

import com.dhcc.pms.service.ven.VenQualifTypeService;
import com.dhcc.pms.dao.ven.VenQualifTypeDao;
import com.dhcc.pms.dto.ven.VenQualifTypeDto;
import com.dhcc.pms.entity.ven.VenQualifType;

@Service("venQualifTypeService")
public class VenQualifTypeServiceImpl implements VenQualifTypeService {

	@Resource
	private VenQualifTypeDao venQualifTypeDao;
	@Resource
	private CommonService commonService;

	public void list(VenQualifTypeDto dto){
	
		PagerModel pagerModel = dto.getPageModel();
		//调用DAO 拼接查询条件
		venQualifTypeDao.buildPagerModelQuery(pagerModel, dto);
		//调用分页查询方法
		commonService.fillPagerModelData(pagerModel);
	}
	
	public void save(VenQualifTypeDto dto){
	
		venQualifTypeDao.save(dto.getVenQualifType());
	}
	
	public void delete(VenQualifTypeDto dto){
	
		venQualifTypeDao.delete(dto.getVenQualifType());
	}
	
	public void update(VenQualifTypeDto dto){
	
		venQualifTypeDao.update(dto.getVenQualifType());
	}
	
	public VenQualifType findById(VenQualifTypeDto dto){
	
		dto.setVenQualifType(venQualifTypeDao.findById(dto.getVenQualifType()));
		return dto.getVenQualifType();
	}

}
