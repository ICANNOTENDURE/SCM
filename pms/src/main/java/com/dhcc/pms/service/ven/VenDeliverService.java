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
	
	/**
	 * 
	* @Title: VenDeliverService.java
	* @Description: TODO(导入发票)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月20日 下午2:53:34
	* @version V1.0
	 */
	public void impInv(VenDeliverDto dto);
	
	/**
	 * 
	* @Title: VenDeliverService.java
	* @Description: TODO(导入发票，按照订单号)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月23日 下午1:18:22
	* @version V1.0
	 */
	public void impByOrder(VenDeliverDto dto);
	
	
	/**
	 * 
	* @Title: VenDeliverService.java
	* @Description: TODO(供应商发货)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月24日 上午11:43:13
	* @version V1.0
	 */
	public void deliver(VenDeliverDto dto);
	
	/**
	 * 
	* @Title: VenDeliverService.java
	* @Description: TODO(删除发货单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月24日 下午4:51:00
	* @version V1.0
	 */
	public void delete(VenDeliverDto dto);
	
	/**
	 * 
	* @Title: VenDeliverService.java
	* @Description: TODO(取消发货状态)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月24日 下午4:56:25
	* @version V1.0
	 */
	public void cancelComplete(VenDeliverDto dto);
}
