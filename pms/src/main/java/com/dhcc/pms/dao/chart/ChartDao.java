package com.dhcc.pms.dao.chart;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.pms.dto.chart.OrdVenDistributionDto;
import com.dhcc.pms.entity.demo.Demo;
import com.dhcc.pms.entity.vo.chart.ChartVO;

/**
 * 
 * @author Administrator
 *
 */
@Repository
public class ChartDao extends HibernatePersistentObjectDAO<Demo> {
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	@Override
	public void buildPagerModelQuery(PagerModel pagerModel, BaseDto dto) {

	}

	
	/**
	 * 
	* @Title: ChartDao.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年9月1日 下午1:32:43
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public void listOrdVenDistribution(OrdVenDistributionDto dto){
		

		StringBuffer hqlBuffer = new StringBuffer();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		hqlBuffer.append("select count(*) as value , ");
		hqlBuffer.append("t.vendor_id,t1.h_name as name ");
		hqlBuffer.append(" from t_ord_order t,t_hop_vendor t1   ");
		hqlBuffer.append(" where  t1.h_venid=t.vendor_id and t.order_serial is not null  group by t.order_serial,t.vendor_id,t1.h_name   ");		
		dto.setChartVOs(jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), ChartVO.class, hqlParamMap, 1, 5, "name"));
	}
	
	/**
	 * 
	* @Title: ChartDao.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @return:void 
	* @author zhouxin  
	* @date 2014年9月1日 下午3:27:29
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public void listOrdReqQty(OrdVenDistributionDto dto){
		StringBuffer hqlBuffer = new StringBuffer();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		hqlBuffer.append("select to_char(t1.plandate,'yyyy-MM')as  name , ");
		hqlBuffer.append(" sum(t.reqqty) as value, ");
		hqlBuffer.append(" sum(t.deliverqty)  as value1 ");
		hqlBuffer.append(" from t_Ord_Orderitm t ,t_ord_order t1     ");
		hqlBuffer.append(" where t1.order_id=t.ord_id     ");
		hqlBuffer.append(" group by to_char(t1.plandate,'yyyy-MM')    ");
		dto.setChartVOs(jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), ChartVO.class, hqlParamMap, 1, 6, "name"));
	
	}
	
}
