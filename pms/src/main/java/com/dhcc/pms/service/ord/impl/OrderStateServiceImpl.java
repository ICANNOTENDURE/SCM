/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.ord.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.CommonService;
import com.dhcc.pms.dao.ord.OrderStateDao;
import com.dhcc.pms.dto.ord.OrderStateDto;
import com.dhcc.pms.entity.ord.State;
import com.dhcc.pms.entity.vo.ord.OrderExeStateVo;
import com.dhcc.pms.entity.vo.ws.OrderItmWebVo;
import com.dhcc.pms.entity.vo.ws.OrderWebVo;
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



	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ord.OrderStateService#listOrderWS(com.dhcc.pms.dto.ord.OrderStateDto)
	 */
	@Override
	public void listOrderWS(OrderStateDto dto) {
		// TODO Auto-generated method stub
		orderStateDao.listOrderWS(dto);
		for(OrderWebVo orderWSVo:(List<OrderWebVo>)dto.getOrderWSVos()){
			if(orderWSVo.getRecloc().contains("西院")){
				orderWSVo.setHopname(orderWSVo.getHopname()+"(西院)");
			}
			orderStateDao.listOrderItmWS(orderWSVo);
			List<OrderItmWebVo> orderItmWebVos=orderWSVo.getOrderItmWSVos();
			Map<String, OrderItmWebVo> map=new HashMap<String,OrderItmWebVo>();
			for(OrderItmWebVo orderItmWebVo:orderItmWebVos){
				if(map.containsKey(orderItmWebVo.getOrderitmid().toString())){
					OrderItmWebVo orderItmWebVotmp=map.get(orderItmWebVo.getOrderitmid().toString());
					map.get(orderItmWebVo.getOrderitmid().toString()).setVeninccode(orderItmWebVo.getVeninccode()+","+orderItmWebVotmp.getVeninccode());
				}else{
					map.put(orderItmWebVo.getOrderitmid().toString(), orderItmWebVo);
				}
			}
			orderWSVo.getOrderItmWSVos().removeAll(orderWSVo.getOrderItmWSVos());
			for(Map.Entry<String, OrderItmWebVo> entry: map.entrySet()) {
				orderItmWebVos.add(entry.getValue());
			}
			map=null;
		}
	}	
	/*	
	 * @see com.dhcc.pms.service.ord.OrderStateService#listOrderStateAndroid(com.dhcc.pms.dto.ord.OrderStateDto)
	 */
//	@Override
//	public List<OrderStateAndroidVo> listOrderStateAndroid(OrderStateDto dto) {
//		
//		return orderStateDao.listOrderStateAndroid(dto);
//	}


}
