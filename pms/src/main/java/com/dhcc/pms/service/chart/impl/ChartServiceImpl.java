/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.chart.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.CommonService;
import com.dhcc.pms.dao.chart.ChartDao;
import com.dhcc.pms.dto.chart.OrdVenDistributionDto;
import com.dhcc.pms.service.chart.ChartService;

@Service("chartService")
public class ChartServiceImpl implements ChartService {

	@Resource
	private ChartDao chartDao;
	@Resource
	private CommonService commonService;
	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.chart.ChartService#listOrdVenDistribution(com.dhcc.pms.dto.chart.OrdVenDistributionDto)
	 */
	@Override
	public void listOrdVenDistribution(OrdVenDistributionDto dto) {
		// TODO Auto-generated method stub
		chartDao.listOrdVenDistribution(dto);
	}
	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.chart.ChartService#listOrdReqQty(com.dhcc.pms.dto.chart.OrdVenDistributionDto)
	 */
	@Override
	public void listOrdReqQty(OrdVenDistributionDto dto) {
		// TODO Auto-generated method stub
		chartDao.listOrdReqQty(dto);
	}

	
}
