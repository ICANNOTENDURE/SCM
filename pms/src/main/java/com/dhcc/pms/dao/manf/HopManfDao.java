/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.manf;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import  com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.pms.entity.hop.Hospital;
import com.dhcc.pms.entity.manf.HopManf;
import com.dhcc.pms.dto.manf.HopManfDto;

@Repository
public class HopManfDao extends HibernatePersistentObjectDAO<HopManf> {

	public void buildPagerModelQuery(PagerModel pagerModel,BaseDto dto) {
	
		HopManfDto hopManfDto = (HopManfDto) dto;
		HopManf hopManf = hopManfDto.getHopManf();

		pagerModel.setCountProName(super.getIdName(HopManf.class));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		
		buildQuery(hqlParamMap, hopManf, hqlStr);
		pagerModel.setQueryHql(hqlStr.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
	}

	/** 
	 * 拼接查询条件的方法  
	 * @param hql
	 * @return
	 */
	private void buildQuery(Map hqlParamMap,HopManf hopManf,StringBuilder hqlStr){
		//拼接查询条件
		hqlStr.append(" from HopManf h ");
		//拼接查询条件		
		if (hopManf!=null) {
			hqlStr.append(" where 1=1 ");
			String manfCode =hopManf.getManfCode();
			String manfName =hopManf.getManfName();
			BigDecimal manfHisDr=hopManf.getManfHisid();
			if(!StringUtils.isNullOrEmpty(manfCode)){
				hqlStr.append(" AND h.manfCode like:manfCode ");
				hqlParamMap.put("manfCode","%"+manfCode+"%");
			}
			if(!StringUtils.isNullOrEmpty(manfName)){
				hqlStr.append(" AND h.manfName like:manfName ");
				hqlParamMap.put("manfName","%"+manfName+"%");
			}
			if(manfHisDr!=null){
				hqlStr.append(" AND h.manfHisid =:manfHisDr ");
				hqlParamMap.put("manfHisDr",manfHisDr);
			}
			
		}
	}
		
	public void save(HopManf hopManf){
	
		super.save(hopManf);
	}
	
	public void delete(HopManf hopManf){
		
		super.delete(hopManf);
	}
	
	public void update(HopManf hopManf){
	
		super.update(hopManf);
	}
	
	public HopManf findById(HopManf hopManf){

		return super.findById(hopManf.getHopManfId());

	}

	/**
	 * @param hopManf
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HopManf> getManfInfo(HopManf hopManf) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select new HopManf(");
		hqlBuffer.append(" h.hopManfId, ");
		hqlBuffer.append(" h.manfName) ");
		hqlBuffer.append(" from HopManf h");
		
		return (List<HopManf>)this.findByHql(hqlBuffer.toString());
	} 
}
