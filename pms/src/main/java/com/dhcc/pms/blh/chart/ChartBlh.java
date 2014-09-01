package com.dhcc.pms.blh.chart;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.chart.OrdVenDistributionDto;
import com.dhcc.pms.entity.vo.chart.ChartVO;
import com.dhcc.pms.service.chart.ChartService;

@Component
public class ChartBlh extends AbstractBaseBlh {

	
	private static Log logger = LogFactory.getLog(ChartBlh.class);
	
	@Resource
	private ChartService chartService;
	
	@Resource
	CommonService commonService;

	 

	
	/**
	 * 
	* @Title: ChartBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年9月1日 下午2:04:26
	* @version V1.0
	 */
	public void listOrdVenDistribution(BusinessRequest res){
		OrdVenDistributionDto dto=super.getDto(OrdVenDistributionDto.class, res);
		
		try {
			chartService.listOrdVenDistribution(dto);
			for(ChartVO chartVo:dto.getChartVOs()){
				dto.getNames().add(chartVo.getName());
			}
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		
	}
	
	
	/**
	 * 
	* @Title: ChartBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年9月1日 下午3:35:51
	* @version V1.0
	 */
			
	public void listOrdReqQty(BusinessRequest res){
		OrdVenDistributionDto dto=super.getDto(OrdVenDistributionDto.class, res);
		dto.setMaxQty("0");
		dto.setMinQty("0");
		try {
			chartService.listOrdReqQty(dto);
			for(ChartVO chartVo:dto.getChartVOs()){
				dto.getNames().add(chartVo.getName());
				dto.getValues().add(chartVo.getValue());
				dto.getValue1s().add(chartVo.getValue1());
				
				int tmpMax=Integer.valueOf(dto.getMaxQty()).intValue();
				int cur=Integer.valueOf(chartVo.getValue()).intValue();
				if(cur>tmpMax){
					dto.setMaxQty(chartVo.getValue());
					dto.setMaxQtyMonth(chartVo.getName());
				}
				int tmpMin=Integer.valueOf(dto.getMinQty()).intValue();
				int curMin=Integer.valueOf(chartVo.getValue()).intValue();
				if(curMin<tmpMin){
					dto.setMinQty(chartVo.getValue());
					dto.setMinQtyMonth(chartVo.getName());
				}
			}
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		
	}
}
	
	
