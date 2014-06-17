/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.ven;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Repository;
import  com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.pms.entity.ven.VenDeliver;
import com.dhcc.pms.dto.ven.VenDeliverDto;

@Repository
public class VenDeliverDao extends HibernatePersistentObjectDAO<VenDeliver> {

	public void buildPagerModelQuery(PagerModel pagerModel,BaseDto dto) {
	
		VenDeliverDto venDeliverDto = (VenDeliverDto) dto;
		VenDeliver venDeliver = venDeliverDto.getVenDeliver();

		pagerModel.setCountProName(super.getIdName(VenDeliver.class));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		
		buildQuery(hqlParamMap, venDeliver, hqlStr);
		pagerModel.setQueryHql(hqlStr.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
	}

	/** 
	 * 拼接查询条件的方法  
	 * @param hql
	 * @return
	 */
	private void buildQuery(Map hqlParamMap,VenDeliver venDeliver,StringBuilder hqlStr){
		//拼接查询条件
		hqlStr.append(" from VenDeliver where 1=1 ");
		//接下来拼接其他查询条件 如下示例代码所示
		//hqlStr.append("WHERE YEAR=:year ");
		//hqlParamMap.put("year", year);
		//hqlStr.append("AND MONTH=:month ");
		//hqlParamMap.put("month", month);
		//hqlStr.append("AND DAY=:day ");
		//hqlParamMap.put("day", day);
	}
		
	public void save(VenDeliver venDeliver){
	
		super.save(venDeliver);
	}
	
	public void delete(VenDeliver venDeliver){
		
		super.delete(venDeliver);
	}
	
	public void update(VenDeliver venDeliver){
	
		super.update(venDeliver);
	}
	
	public VenDeliver findById(VenDeliver venDeliver){

		return super.findById(venDeliver.getDeliverId());

	} 
}
