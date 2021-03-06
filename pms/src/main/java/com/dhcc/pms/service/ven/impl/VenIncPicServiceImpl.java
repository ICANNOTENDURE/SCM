/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.ven.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.PagerModel;

import javax.annotation.Resource;

import com.dhcc.pms.service.ven.VenIncPicService;
import com.dhcc.pms.dao.ven.VenIncPicDao;
import com.dhcc.pms.dto.ven.VenIncPicDto;
import com.dhcc.pms.entity.ven.VenIncPic;
import com.dhcc.pms.entity.vo.ven.VenIncPicVo;

@Service("venIncPicService")
public class VenIncPicServiceImpl implements VenIncPicService {

	@Resource
	private VenIncPicDao venIncPicDao;
	@Resource
	private CommonService commonService;

	public void list(VenIncPicDto dto){
	
		PagerModel pagerModel = dto.getPageModel();
		//调用DAO 拼接查询条件
		venIncPicDao.buildPagerModelQuery(pagerModel, dto);
		//调用分页查询方法
		commonService.fillPagerModelData(pagerModel);
	}
	
	public void save(VenIncPicDto dto){
	
		venIncPicDao.save(dto.getVenIncPic());
	}
	
	public void delete(VenIncPicDto dto){
	
		venIncPicDao.delete(dto.getVenIncPic());
	}
	
	public void update(VenIncPicDto dto){
	
		venIncPicDao.update(dto.getVenIncPic());
	}
	
	public VenIncPic findById(VenIncPicDto dto){
	
		dto.setVenIncPic(venIncPicDao.findById(dto.getVenIncPic()));
		return dto.getVenIncPic();
	}
	
	public List<VenIncPicVo> getListInfo(VenIncPicDto dto) {
		// 
		PagerModel pagerModel=dto.getPageModel();
		return venIncPicDao.getListInfo(pagerModel,dto.getVenIncPicVos(),dto.getVenIncPic());
	}

}
