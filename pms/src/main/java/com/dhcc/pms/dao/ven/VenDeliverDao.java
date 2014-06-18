/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.ven;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.entity.ord.ExeState;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.ord.OrderItm;
import com.dhcc.pms.entity.ven.VenDeliver;
import com.dhcc.pms.entity.ven.VenDeliveritm;

@Repository
public class VenDeliverDao extends HibernatePersistentObjectDAO<VenDeliver> {

	public void buildPagerModelQuery(PagerModel pagerModel,BaseDto dto) {

	}		
	
	/**
	 * 
	* @Title: VenDeliverDao.java
	* @Description: TODO(更具订单生成发货单 )
	* @param orderId
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月18日 下午3:29:01
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public void AccectOrder(Long orderId){
		StringBuilder hqlStr = new StringBuilder();
		
		Order order=super.get(Order.class, orderId);
		
		VenDeliver venDeliver=new VenDeliver();
		venDeliver.setDeliverHopid(order.getHopId());
		venDeliver.setDeliverOrderid(order.getOrderId());
		venDeliver.setDeliverUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		venDeliver.setDeliverPurloc(order.getPurLoc());
		venDeliver.setDeliverRecloc(order.getRecLoc());
		venDeliver.setDeliverVendorid(order.getVendorId());
		venDeliver.setDeliverDestinationid(order.getRecDestination());
		super.saveOrUpdate(venDeliver);
		
		ExeState exeState=new ExeState();
		exeState.setDeliverId(venDeliver.getDeliverId());
		exeState.setExedate(new java.sql.Timestamp(new Date().getTime()));
		exeState.setStateId(Long.valueOf(2));
		exeState.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		super.saveOrUpdate(exeState);
		
		hqlStr.delete(0, hqlStr.length());
		hqlStr.append(" from OrderItm t where t.ordId=?");
		List<OrderItm> orderItms=super.findByHql(hqlStr.toString(), orderId);
		for(OrderItm tmpOrderItm:orderItms){
			VenDeliveritm venDeliveritm=new VenDeliveritm();
			venDeliveritm.setDeliveritmHopincid(tmpOrderItm.getIncId());
			venDeliveritm.setDeliveritmOrderitmid(tmpOrderItm.getOrderitmId());
			venDeliveritm.setDeliveritmParentid(venDeliver.getDeliverId());
			venDeliveritm.setDeliveritmQty(tmpOrderItm.getReqqty());
			venDeliveritm.setDeliveritmUom(tmpOrderItm.getUom());
			venDeliveritm.setDeliveritmRp(tmpOrderItm.getRp());
			super.saveOrUpdate(venDeliveritm);
		}
	}
}
