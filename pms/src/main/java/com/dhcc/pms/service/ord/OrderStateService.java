/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.ord;

import java.util.List;

import com.dhcc.pms.dto.ord.OrderStateDto;
import com.dhcc.pms.entity.ord.State;
import com.dhcc.pms.entity.vo.ord.OrderExeStateVo;
import com.dhcc.pms.entity.vo.ord.OrderStateAndroidVo;

public interface OrderStateService {
	
	/**
	 * 
	* @Title: OrderStateService.java
	* @Description: TODO(查询订单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月28日 上午9:51:51
	* @version V1.0
	 */
	public void listOrderState(OrderStateDto dto);
	

	/**
	 * 
	* @Title: OrderStateService.java
	* @Description: TODO(查询订单历史状态)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月28日 下午5:54:09
	* @version V1.0
	 */
	public List<OrderExeStateVo> listOrderExeState(OrderStateDto dto);
	
	
	/**
	 * 
	* @Title: OrderStateService.java
	* @Description: TODO(查询订单明细)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月29日 上午9:35:01
	* @version V1.0
	 */
	public void listOrderItm(OrderStateDto dto);
	
	/**
	 * 
	* @Title: OrderStateService.java
	* @Description: TODO(返回状态列表)
	* @return
	* @return:List<State> 
	* @author zhouxin  
	* @date 2014年5月29日 下午2:42:12
	* @version V1.0
	 */
	public  List<State> getComboList();


	/**
	 * @param dto
	 * @return
	 */
	public List<OrderStateAndroidVo> listOrderStateAndroid(OrderStateDto dto);
}
