/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.ord;

import java.util.List;

import com.dhcc.pms.dto.ord.OrderDto;
import com.dhcc.pms.entity.hop.HopCtlocDestination;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.vo.ord.ShopCartPicVo;
import com.dhcc.pms.entity.vo.ord.ShopCartVo;

public interface OrderService {

	public void list(OrderDto dto);
	
	public void save(OrderDto dto);
	
	public void delete(OrderDto dto);
	
	public void update(OrderDto dto);
	
	public Order findById(OrderDto dto);
	
	public void saveShopCart(OrderDto dto);
	
	public List<ShopCartVo> listShopCart(OrderDto dto);
	
	/**
	 * 
	* @Title: OrderService.java
	* @Description: TODO(删除购物车的一个药)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月23日 上午11:34:44
	* @version V1.0
	 */
	public void deleteShopCart(OrderDto dto);
	
	/**
	 * 
	* @Title: OrderService.java
	* @Description: TODO(查询登录人购物车，带图片)
	* @param dto
	* @return
	* @return:List<ShopCartPicVo> 
	* @author zhouxin  
	* @date 2014年5月23日 下午3:01:20
	* @version V1.0
	 */
	public List<ShopCartPicVo> listShopCartPic(OrderDto dto);
	
	
	/**
	 * 
	* @Title: OrderService.java
	* @Description: TODO(确认购物车选择的药品)
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月27日 上午10:09:18
	* @version V1.0
	 */
	public void saveOrdInfo(OrderDto dto);

	/**
	 * 
	* @Title: OrderService.java
	* @Description: TODO(更具科室查询收货地址)
	* @param dto
	* @return
	* @return:List<HopCtlocDestination> 
	* @author zhouxin  
	* @date 2014年5月27日 下午2:59:55
	* @version V1.0
	 */
	public List<HopCtlocDestination> findLocDesctionComboList(OrderDto dto);
	
	/**
	 * 
	* @Title: OrderService.java
	* @Description: TODO(生成订单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月28日 上午8:33:27
	* @version V1.0
	 */
	public void saveOrUpdate(OrderDto dto);
	
	/**
	 * 
	* @Title: OrderService.java
	* @Description: TODO(导入订单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月4日 上午10:06:31
	* @version V1.0
	 */
	public void impOrder(OrderDto dto);
	
	/**
	 * 
	* @Title: OrderService.java
	* @Description: TODO(修改订单明细)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月5日 上午9:38:34
	* @version V1.0
	 */
	public void saveOrditm(OrderDto dto);
	
	/**
	 * 
	* @Title: OrderService.java
	* @Description: TODO(删除订单明细)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月5日 上午9:38:34
	* @version V1.0
	 */
	public void deleteOrditm(OrderDto dto);
	
	/**
	 * 
	* @Title: OrderService.java
	* @Description: TODO(确认订单完成)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月5日 下午8:46:21
	* @version V1.0
	 */
	public void complete(OrderDto dto);
}
