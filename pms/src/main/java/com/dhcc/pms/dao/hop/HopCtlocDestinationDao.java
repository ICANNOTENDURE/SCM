/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.hop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import  com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.hibernate.dao.CommonDao;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.hop.HopCtlocDestination;
import com.dhcc.pms.entity.vo.hop.HopDestinationVo;
import com.dhcc.pms.dto.hop.HopCtlocDestinationDto;

@Repository
public class HopCtlocDestinationDao extends HibernatePersistentObjectDAO<HopCtlocDestination> {

	@Resource
	private CommonDao commonDao;
	public void buildPagerModelQuery(PagerModel pagerModel,BaseDto dto) {
	
		HopCtlocDestinationDto hopCtlocDestinationDto = (HopCtlocDestinationDto) dto;
		HopCtlocDestination hopCtlocDestination = hopCtlocDestinationDto.getHopCtlocDestination();

		pagerModel.setCountProName(super.getIdName(HopCtlocDestination.class));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		
		buildQuery(hqlParamMap, hopCtlocDestination, hqlStr);
		pagerModel.setQueryHql(hqlStr.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
	}

	/** 
	 * 拼接查询条件的方法  
	 * @param hql
	 * @return
	 */
	private void buildQuery(Map<String, Object> hqlParamMap,HopCtlocDestination hopCtlocDestination,StringBuilder hqlStr){
		//拼接查询条件
		hqlStr.append(" from HopCtlocDestination h");
		//拼接查询条件		
		if (hopCtlocDestination!=null) {
			hqlStr.append(" where 1=1 ");
			String ctlocId =hopCtlocDestination.getCtlocDr();
			String destinatonStr =hopCtlocDestination.getDestination();	
			String contactStr=hopCtlocDestination.getContact();
			if(!StringUtils.isNullOrEmpty(destinatonStr)){
				hqlStr.append(" AND h.destination like:destinatonStr ");
				hqlParamMap.put("destinatonStr","%"+destinatonStr+"%");
			}
			if(ctlocId!=null){
				hqlStr.append(" AND h.ctlocDr like:ctlocId ");
				hqlParamMap.put("ctlocId","%"+ctlocId+"%");
			}
			if(!StringUtils.isNullOrEmpty(contactStr)){
				hqlStr.append(" AND h.contact like:contactStr ");
				hqlParamMap.put("contactStr","%"+contactStr+"%");
			}
		}
	}
		
	public void save(HopCtlocDestination hopCtlocDestination){
	
		super.save(hopCtlocDestination);
	}
	
	public void delete(HopCtlocDestination hopCtlocDestination){
		
		super.delete(hopCtlocDestination);
	}
	
	public void update(HopCtlocDestination hopCtlocDestination){
	
		super.update(hopCtlocDestination);
	}
	
	public HopCtlocDestination findById(HopCtlocDestination hopCtlocDestination){

		return super.findById(hopCtlocDestination.getHopCtlocDestinationId());

	}

	/**
	 * @param dto
	 * @return 
	 */	
	public HopCtloc getCtloc(HopCtlocDestinationDto dto) throws Exception{
		
		StringBuffer hql = new StringBuffer();
		hql.append(" from ");
		hql.append(" HopCtoloc h ");
		hql.append(" where h.hopCtlocId = :hopctlocDr ");		
		
		HopCtloc hopCtloc=(HopCtloc) this.findByHql(hql.toString());
		//dto.getHopCtlocDestination().setTSysCtloc(hopCtloc);
		return hopCtloc;
		
	}

	/**
	 * @param hopDestinationVos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HopDestinationVo> getListInfo(PagerModel pagerModel,
			List<HopDestinationVo> hopDestinationVos ,HopCtlocDestination hopCtlocDestination) {
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select new com.dhcc.pms.entity.vo.hop.HopDestinationVo(");
		hqlBuffer.append(" h.hopCtlocDestinationId, ");
		hqlBuffer.append(" h.destination, ");
		hqlBuffer.append(" h.contact, ");
		hqlBuffer.append(" h.tel, ");
		hqlBuffer.append(" h.ctlocDr, ");
		hqlBuffer.append(" hc.name) ");
		hqlBuffer.append(" from HopCtlocDestination h , HopCtloc hc ");
		hqlBuffer.append(" where h.ctlocDr=hc.hopCtlocId");
		if (hopCtlocDestination!=null) {			
			String ctlocId =hopCtlocDestination.getCtlocDr();
			String destinatonStr =hopCtlocDestination.getDestination();	
			String contactStr=hopCtlocDestination.getContact();
			if(!StringUtils.isNullOrEmpty(destinatonStr)){
				hqlBuffer.append(" AND h.destination like:destinatonStr ");
				hqlParamMap.put("destinatonStr","%"+destinatonStr+"%");
			}
			if(ctlocId!=null){
				hqlBuffer.append(" AND h.ctlocDr like:ctlocId ");
				hqlParamMap.put("ctlocId","%"+ctlocId+"%");
			}
			if(!StringUtils.isNullOrEmpty(contactStr)){
				hqlBuffer.append(" AND h.contact like:contactStr ");
				hqlParamMap.put("contactStr","%"+contactStr+"%");
			}
		}
		
		pagerModel.setCountProName("CTLOCDES_ID");
		pagerModel.setQueryHql(hqlBuffer.toString());
		int totalRows = pagerModel.getTotals();
		if (totalRows == 0) {
			totalRows = commonDao.getResultCountWithValuesMap(
					pagerModel.getQueryHql(), pagerModel.getHqlParamMap(),
					pagerModel.getCountProName(), false).intValue();
		}
		if (totalRows == 0) {
			pagerModel.setPageData(new ArrayList<Object>(1));
			return null;
		}
		pagerModel.setTotals(totalRows);
		//return (List<HopDestinationVo>)this.findByHql(hqlBuffer.toString());
		return (List<HopDestinationVo>)findByHqlWithValuesMap(hqlBuffer.toString(),pagerModel.getPageNo(),pagerModel.getPageSize(), hqlParamMap, true);
		
	}
		
}
