/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.ven;

import com.dhcc.pms.dto.ven.VenDeliverDto;


public interface VenDeliverService {
	/**
	 * 
	* @Title: VenDeliverService.java
	* @Description: TODO(根据订单生成发货单)
	* @param orderId
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月18日 下午4:26:10
	* @version V1.0
	 */
	public void AccectOrder(Long orderId);
	
	/**
	 * 
	* @Title: VenDeliverService.java
	* @Description: TODO(查询发货单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 上午11:42:07
	* @version V1.0
	 */
	public void listDeliver(VenDeliverDto dto);
	
	/**
	 * 
	* @Title: VenDeliverService.java
	* @Description: TODO(查询发货单明细)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午4:16:27
	* @version V1.0
	 */
	public void listDeliverItm(VenDeliverDto dto);
	
	/**
	 * 
	* @Title: VenDeliverService.java
	* @Description: TODO(保存发货明细)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午5:02:30
	* @version V1.0
	 */
	public void saveDeliverItm(VenDeliverDto dto);
}
