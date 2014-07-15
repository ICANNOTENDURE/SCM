/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.ven.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.PagerModel;
import com.dhcc.pms.dao.hop.HopIncDao;
import com.dhcc.pms.dao.ven.VenIncDao;
import com.dhcc.pms.dto.ven.VenIncDto;
import com.dhcc.pms.entity.ven.VenInc;
import com.dhcc.pms.service.ven.VenIncService;

@Service("venIncService")
public class VenIncServiceImpl implements VenIncService {

	@Resource
	private VenIncDao venIncDao;
	@Resource
	private HopIncDao hopIncDao;
	@Resource
	private CommonService commonService;

	public void list(VenIncDto dto){
	
		PagerModel pagerModel = dto.getPageModel();
		//调用DAO 拼接查询条件
		venIncDao.buildPagerModelQuery(pagerModel, dto);
		//调用分页查询方法
		commonService.fillPagerModelData(pagerModel);
	}
	
	public void save(VenIncDto dto){
	
		venIncDao.save(dto.getVenInc());
	}
	
	public void delete(VenIncDto dto){
	
		venIncDao.delete(dto.getVenInc());
	}
	
	public void update(VenIncDto dto){
	
		venIncDao.update(dto.getVenInc());
	}
	
	public VenInc findById(VenIncDto dto){
	
		dto.setVenInc(venIncDao.findById(dto.getVenInc()));
		return dto.getVenInc();
	}


	public List<VenInc> getIncInfo(VenIncDto dto) {
		
		return venIncDao.getIncInfo(dto.getVenInc());
	}

	
	public void getListInfo(VenIncDto dto) {
		//
		PagerModel pagerModel = dto.getPageModel();
		venIncDao.getListInfo(pagerModel,dto.getShowVenIncVos(),dto.getVenInc());
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenIncService#listContrantInc(com.dhcc.pms.dto.ven.VenIncContranstDto)
	 */
	@Override
	public void listContrantInc(VenIncDto dto) {
		// TODO Auto-generated method stub
		venIncDao.listContrantInc(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenIncService#saveContranst(com.dhcc.pms.dto.ven.VenIncDto)
	 */
	@Override
	public void saveContranst(VenIncDto dto) {
		// TODO Auto-generated method stub
		venIncDao.saveContranst(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenIncService#listVenContranst(com.dhcc.pms.dto.ven.VenIncDto)
	 */
	@Override
	public void listVenContranst(VenIncDto dto) {
		// TODO Auto-generated method stub
		venIncDao.listVenContranst(dto);
		
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenIncService#exportVenInc(com.dhcc.pms.dto.ven.VenIncDto)
	 */
	@Override
	public void exportVenInc(VenIncDto dto) {
		// TODO Auto-generated method stub
		venIncDao.exportVenInc(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenIncService#getHopIncByVenIncCode(java.lang.String)
	 */
	@Override
	public Long getHopIncByVenIncCode(String code) {
		// TODO Auto-generated method stub
		Long venincidLong=hopIncDao.getVenIncByCode(code);
		if(venincidLong==null){
			return null;
		}
		return hopIncDao.getHopIncByVenInc(venincidLong);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenIncService#getVenIncByCode(java.lang.String, java.lang.Long)
	 */
	@Override
	public Long getVenIncByCode(String code, Long venId) {
		// TODO Auto-generated method stub
		return venIncDao.getVenIncByCode(code,venId);
	}

}
