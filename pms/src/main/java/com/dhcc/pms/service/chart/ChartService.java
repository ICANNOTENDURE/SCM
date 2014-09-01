/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.chart;

import com.dhcc.pms.dto.chart.OrdVenDistributionDto;


public interface ChartService {

	/**
	 * 
	* @Title: ChartService.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年9月1日 下午1:59:40
	* @version V1.0
	 */
	public void listOrdVenDistribution(OrdVenDistributionDto dto);
	
	
	/**
	 * 
	* @Title: ChartService.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年9月1日 下午3:34:48
	* @version V1.0
	 */
	public void listOrdReqQty(OrdVenDistributionDto dto);

}
