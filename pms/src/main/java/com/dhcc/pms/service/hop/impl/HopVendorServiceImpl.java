/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.hop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.PagerModel;
import com.dhcc.pms.dao.hop.HopVendorDao;
import com.dhcc.pms.dto.hop.HopVendorDto;
import com.dhcc.pms.entity.hop.HopVendor;
import com.dhcc.pms.service.hop.HopVendorService;

@Service("hopVendorService")
public class HopVendorServiceImpl implements HopVendorService {

	@Resource
	private HopVendorDao hopVendorDao;
	@Resource
	private CommonService commonService;

	public void list(HopVendorDto dto){
	
		PagerModel pagerModel = dto.getPageModel();
		//调用DAO 拼接查询条件
		hopVendorDao.buildPagerModelQuery(pagerModel, dto);
		//调用分页查询方法
		commonService.fillPagerModelData(pagerModel);
	}
	
	public void save(HopVendorDto dto){
	
		hopVendorDao.save(dto.getHopVendor());
	}
	
	public void delete(HopVendorDto dto){
	
		hopVendorDao.delete(dto.getHopVendor());
	}
	
	public void update(HopVendorDto dto){
	
		hopVendorDao.update(dto.getHopVendor());
	}
	
	public HopVendor findById(HopVendorDto dto){
	
		dto.setHopVendor(hopVendorDao.findById(dto.getHopVendor()));
		return dto.getHopVendor();
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.hop.HopVendorService#listHopCon(com.dhcc.pms.dto.hop.HopVendorDto)
	 */
	@Override
	public void listHopCon(HopVendorDto dto) {
		// TODO Auto-generated method stub
		hopVendorDao.listHopCon(dto);
	}

}
