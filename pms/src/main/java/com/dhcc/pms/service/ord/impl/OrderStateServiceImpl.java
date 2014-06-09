/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.ord.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.CommonService;
import com.dhcc.pms.dao.ord.OrderStateDao;
import com.dhcc.pms.dto.ord.OrderStateDto;
import com.dhcc.pms.entity.ord.State;
import com.dhcc.pms.entity.vo.ord.OrderExeStateVo;
import com.dhcc.pms.service.ord.OrderStateService;

@Service("orderStateService")
public class OrderStateServiceImpl implements OrderStateService {

	@Resource
	private OrderStateDao orderStateDao;
	@Resource
	private CommonService commonService;



	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ord.OrderStateService#listOrderState(com.dhcc.pms.dto.ord.OrderStateDto)
	 */
	@Override
	public void listOrderState(OrderStateDto dto) {
		// TODO Auto-generated method stub
		orderStateDao.listOrderState(dto);
	}



	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ord.OrderStateService#listOrderExeState(com.dhcc.pms.dto.ord.OrderStateDto)
	 */
	@Override
	public List<OrderExeStateVo> listOrderExeState(OrderStateDto dto) {
		// TODO Auto-generated method stub
		return orderStateDao.listOrderExeState(dto);
	}



	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ord.OrderStateService#listOrderItm(com.dhcc.pms.dto.ord.OrderStateDto)
	 */
	@Override
	public void listOrderItm(OrderStateDto dto) {
		// TODO Auto-generated method stub
		orderStateDao.listOrderItm(dto);
	}



	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ord.OrderStateService#getComboList()
	 */
	@Override
	public List<State> getComboList() {
		// TODO Auto-generated method stub
		return orderStateDao.getComboList();
	}


}
