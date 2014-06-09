package com.dhcc.pms.dao.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.pms.dto.demo.DemoDto;
import com.dhcc.pms.entity.demo.Demo;

/**
 * <p>标题：</p>
 * <p>业务描述：</p>
 * <p>公司：东华软件股份公司</p>
 * <p>版权：dhcc2013</p>
 * @author 吴杰
 * @date 2013年12月9日
 * @version 
 */
@Repository
public class DemoDao extends HibernatePersistentObjectDAO<Demo> {

	@Override
	public void buildPagerModelQuery(PagerModel pagerModel, BaseDto dto) {
		//把dto转化为需要的DemoDto,得到Demo对象
		DemoDto demoDto=(DemoDto)dto;
		Demo demo=demoDto.getDemo();
		//用来统计总记录数的属性，实体类的属性名,与entity中的相同
		pagerModel.setCountProName("demoId");
		
		StringBuilder hqlStr=new StringBuilder();
		Map<String, Object>hqlParamMap=new HashMap<String, Object>();
		
		buildQuery(hqlParamMap,demo,hqlStr);
		pagerModel.setQueryHql(hqlStr.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
		
	}

	
	private void buildQuery(Map hqlParamMap, Demo demo,
			StringBuilder hqlStr) {

		hqlStr.append(" from Demo d ");
		if (demo!=null) {
			hqlStr.append(" where 1=1 ");
			String demoName = demo.getDemoName();
			Integer demoAge = demo.getDemoAge();
			if(!StringUtils.isNullOrEmpty(demoName)){
				hqlStr.append(" AND d.demoName like:demoName ");
				hqlParamMap.put("demoName","%"+demoName+"%");
			}
			if( demoAge != null){
				hqlStr.append(" AND d.demoAge=:demoAge");
				hqlParamMap.put("demoAge", demoAge);
			}
		}
	}
	

	public void save(Demo demo){
			
		super.saveOrUpdate(demo);
	}
	
	public void delete(Demo demo){
		
		super.delete(demo);
	}
	
	public void update(Demo demo){
	
		super.update(demo);
	}
	
	public Demo findById(Demo demo){
		
		 return super.findById(demo.getDemoId());
	}

	
}
