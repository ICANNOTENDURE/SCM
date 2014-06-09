/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.ord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.ord.OrderDto;
import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.hop.HopCtlocDestination;
import com.dhcc.pms.entity.hop.HopInc;
import com.dhcc.pms.entity.hop.Hospital;
import com.dhcc.pms.entity.ord.ExeState;
import com.dhcc.pms.entity.ord.OrdShopping;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.ord.OrderItm;
import com.dhcc.pms.entity.ven.Vendor;
import com.dhcc.pms.entity.vo.ord.ShopCartPicVo;
import com.dhcc.pms.entity.vo.ord.ShopCartVo;

@Repository
public class OrderDao extends HibernatePersistentObjectDAO<Order> {
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	public void buildPagerModelQuery(PagerModel pagerModel,BaseDto dto) {
	
		OrderDto orderDto = (OrderDto) dto;
		Order order = orderDto.getOrder();

		pagerModel.setCountProName(super.getIdName(Order.class));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		
		buildQuery(hqlParamMap, order, hqlStr);
		pagerModel.setQueryHql(hqlStr.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
	}

	/** 
	 * 拼接查询条件的方法  
	 * @param hql
	 * @return
	 */
	private void buildQuery(@SuppressWarnings("rawtypes") Map hqlParamMap,Order order,StringBuilder hqlStr){
		//拼接查询条件
		hqlStr.append(" from Order where 1=1 ");
		//接下来拼接其他查询条件 如下示例代码所示
		//hqlStr.append("WHERE YEAR=:year ");
		//hqlParamMap.put("year", year);
		//hqlStr.append("AND MONTH=:month ");
		//hqlParamMap.put("month", month);
		//hqlStr.append("AND DAY=:day ");
		//hqlParamMap.put("day", day);
	}
		
	public void save(Order order){
	
		super.save(order);
	}
	
	public void delete(Order order){
		
		super.delete(order);
		StringBuilder hqlBuffer = new StringBuilder();
		hqlBuffer.delete(0, hqlBuffer.length());
		hqlBuffer.append(" delete from OrderItm t ");
		hqlBuffer.append(" where t.ordId = ?");
		this.updateByHqlWithFreeParam(hqlBuffer.toString(),order.getOrderId());
		
		
	}
	
	public void update(Order order){
	
		super.update(order);
	}
	
	public Order findById(Order order){

		return super.findById(order.getOrderId());

	}
	
	/**
	 * 
	* @Title: OrderDao.java
	* @Description: TODO(保存购物车)
	* @param ordShopping
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月21日 下午7:22:13
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public void saveShopCart(OrdShopping ordShopping){

		
		ordShopping.setShopUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		hqlStr.append(" from OrdShopping  ");
		//接下来拼接其他查询条件 如下示例代码所示
		hqlStr.append("WHERE shopIncid=:shopIncid ");
		hqlParamMap.put("shopIncid", ordShopping.getShopIncid());
		hqlStr.append("and shopUserid=:shopUserid ");
		hqlParamMap.put("shopUserid", ordShopping.getShopUserid());
		
		List<OrdShopping> ordShoppingList=this.findByHqlWithValuesMap(hqlStr.toString(), hqlParamMap,false);
		if (ordShoppingList.size()>0){
			
			Long qty=ordShopping.getShopQty()+ordShoppingList.get(0).getShopQty();
			ordShopping=ordShoppingList.get(0);
			ordShopping.setShopQty(qty);
		}
		this.saveOrUpdate(ordShopping);
	}
	
	/**
	 * 
	* @Title: OrderDao.java
	* @Description: TODO(显示购物车)
	* @param ordShopping
	* @return
	* @return:List<ShopCartVo> 
	* @author zhouxin  
	* @date 2014年5月23日 下午3:00:19
	* @version V1.0
	 */
	
	public List<ShopCartVo> listShopCart(OrdShopping ordShopping){
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select ");
		hqlBuffer.append("  t1.shop_incid as inc,t1.shop_qty as qty,t2.inc_name as name,t2.inc_rp as rp,t2.inc_uomname as uom ");// ,t2.venQualificationId,t2.expdate,t2.vendorid)																														// ");
		hqlBuffer.append("  from t_ord_shopping t1 left join t_hop_inc t2 on t2.inc_id=t1.shop_incid");
		Long userIdLong=Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId());
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();

		hqlBuffer.append(" where t1.shop_userid=:userIdLong ");
		hqlParamMap.put("userIdLong", userIdLong);
		@SuppressWarnings("unchecked")
		List<ShopCartVo> shopCartVoList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), ShopCartVo.class, hqlParamMap);
		return shopCartVoList; 
	}
	
	/**
	 * 
	* @Title: OrderDao.java
	* @Description: TODO(购物车删除一个商品)
	* @param ordShopping
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月23日 下午2:59:56
	* @version V1.0
	 */
	public void deleteShopCart(OrdShopping ordShopping){
		
		ordShopping.setShopUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		hqlStr.append(" from OrdShopping  ");
		//接下来拼接其他查询条件 如下示例代码所示
		hqlStr.append("WHERE shopIncid=:shopIncid ");
		hqlParamMap.put("shopIncid", ordShopping.getShopIncid());
		hqlStr.append("and shopUserid=:shopUserid ");
		hqlParamMap.put("shopUserid", ordShopping.getShopUserid());
		
		@SuppressWarnings("unchecked")
		List<OrdShopping> ordShoppingList=this.findByHqlWithValuesMap(hqlStr.toString(), hqlParamMap,false);
		if (ordShoppingList.size()>0){
			this.deleteById(OrdShopping.class, ordShoppingList.get(0).getShopId());
		} 
	}
	
  /**
   * 	
  * @Title: OrderDao.java
  * @Description: TODO(查询购物车结算，带图片)
  * @param ordShopping
  * @return
  * @return:List<ShopCartVo> 
  * @author zhouxin  
  * @date 2014年5月23日 下午2:56:56
  * @version V1.0
   */
  public List<ShopCartPicVo> listShopCartPic(OrdShopping ordShopping){
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select ");
		hqlBuffer.append("  t1.shop_id as shop,t1.shop_incid as inc,t1.shop_qty as qty,t2.inc_name as name,t2.inc_rp as rp,t2.inc_uomname as uom,t3.inc_pic_path path ");
		hqlBuffer.append("  from t_ord_shopping t1 left join t_hop_inc t2 on t2.inc_id=t1.shop_incid left join t_hop_inc_pic t3 on t3.inc_pic_incid=t2.inc_id and t3.inc_pic_seq=1");
		hqlBuffer.append("  where 1=1 ");
		Long userIdLong=Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId());
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();

		hqlBuffer.append(" and t1.shop_userid=:userIdLong ");
		hqlParamMap.put("userIdLong", userIdLong);
		@SuppressWarnings("unchecked")
		List<ShopCartPicVo> shopCartVoList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), ShopCartPicVo.class, hqlParamMap);
		return shopCartVoList; 
	}
  
  /**
   * 
  * @Title: OrderDao.java
  * @Description: TODO(修改购物车确认的药品)
  * @param shop
  * @return:void 
  * @author zhouxin  
  * @date 2014年5月27日 上午10:03:41
  * @version V1.0
   */
  public void saveOrdInfo(String shop){
	  	
	    StringBuffer hqlBuffer = new StringBuffer();
	    String stringarray[]=shop.split("\\^");  

	    for(String stemp:stringarray){  
	    	hqlBuffer.delete(0, hqlBuffer.length());
			hqlBuffer.append(" update OrdShopping t ");
			hqlBuffer.append(" set t.shopCheckFlag = 1 ");
			hqlBuffer.append(" where t.shopId = ?");
			this.updateByHqlWithFreeParam(hqlBuffer.toString(),Long.valueOf(stemp));
	    } 

  }
  	/**
  	 * 
  	* @Title: OrderDao.java
  	* @Description: TODO(返回收货地址列表)
  	* @param dto
  	* @return
  	* @return:List<HopCtlocDestination> 
  	* @author zhouxin  
  	* @date 2014年5月27日 下午8:53:13
  	* @version V1.0
  	 */
  @SuppressWarnings("unchecked")
	public List<HopCtlocDestination> findLocDesctionComboList(OrderDto dto){
		StringBuffer hqlBuffer = new StringBuffer();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		hqlBuffer.append(" select new HopCtlocDestination(");
		hqlBuffer.append(" t.hopCtlocDestinationId , ");
		hqlBuffer.append(" t.destination ) ");
		hqlBuffer.append(" from HopCtlocDestination t ");
		if(dto.getLoc()!=null){
			hqlBuffer.append(" where t.ctlocDr = :ctlocDr");
			hqlParamMap.put("ctlocDr", dto.getLoc().toString());
		}
		return (List<HopCtlocDestination>)super.findByHqlWithValuesMap(hqlBuffer.toString(),1,20,hqlParamMap,true);
		
	}
  
  
   /**
    *   
   * @Title: OrderDao.java
   * @Description: TODO(保存订单表，删除购物车表)
   * @param dto
   * @return:void 
   * @author zhouxin  
   * @date 2014年5月27日 下午8:54:14
   * @version V1.0
    */
   @SuppressWarnings("unchecked")
public void saveOrUpdate(OrderDto dto){
	   
	   //保存 order
	   dto.getOrder().setCreateUser(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
	   dto.getOrder().setPlanDate(new Date());
	   super.saveOrUpdate(dto.getOrder());
	   
	   //保存 ordexe
	   ExeState exeState=new ExeState();
	   exeState.setStateId(Long.valueOf(1));
	   exeState.setRemark(dto.getOrder().getRemark());
	   exeState.setUserid(dto.getOrder().getCreateUser());
	   exeState.setOrdId(dto.getOrder().getOrderId());
	   exeState.setExedate(new Date());
	   super.save(exeState);
	   //更新执行表Id
	   Order order=new Order();
	   order=super.findById(dto.getOrder().getOrderId());
	   order.setExeStateId(exeState.getExestateId());
	   super.update(order);
	   
	   
	   StringBuilder hqlStr = new StringBuilder();
	   Map<String,Object> hqlParamMap = new HashMap<String,Object>();
	   hqlStr.append(" from OrdShopping  ");
		//接下来拼接其他查询条件 如下示例代码所示
	   hqlStr.append("where shopUserid=:shopUserid ");
	   hqlParamMap.put("shopUserid", dto.getOrder().getCreateUser());
		
	 
	   List<OrdShopping> ordShoppingList=this.findByHqlWithValuesMap(hqlStr.toString(), hqlParamMap,false);
		
	   for(int i=0;i<ordShoppingList.size();i++){
		   OrderItm orderItm=new OrderItm();
		   orderItm.setOrdId(dto.getOrder().getOrderId());
		   orderItm.setIncId(ordShoppingList.get(i).getShopIncid());
		   orderItm.setReqqty(ordShoppingList.get(i).getShopQty());
		   orderItm.setUom(ordShoppingList.get(i).getShopUom());
		   orderItm.setRp(super.get(HopInc.class, ordShoppingList.get(i).getShopIncid()).getIncRp().longValue());
		   super.save(orderItm);
		   super.deleteById(OrdShopping.class, ordShoppingList.get(i).getShopId());
	   }
   }
   
   /**
    * 
   * @Title: OrderDao.java
   * @Description: TODO(导入的订单保存)
   * @param dto
   * @return:void 
   * @author zhouxin  
   * @date 2014年6月3日 下午3:53:06
   * @version V1.0
    */
   @SuppressWarnings("unchecked")
public void impOrder(OrderDto dto){
	   dto.setOpFlg("0");
	   StringBuffer hqlBuffer = new StringBuffer(); 
	   
	   if(dto.getOrder().getHopId()==null){
		  Long hopId=Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
		  if(hopId!=null){
			  dto.getOrder().setHopId(hopId);
		  }else{
			  //没有医院不能导入
			  dto.setOpFlg("2");
			  dto.setMsg("没有医院不能导入");
			  return;
		  }
	   }else{
		   Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		   hqlBuffer.delete(0, hqlBuffer.length());

		   hqlBuffer.append(" from Hospital t ");
		   hqlBuffer.append(" where t.hospitalHisdr = :hopid");
		   hqlParamMap.put("hopid", dto.getOrder().getHopId());
		   List<Hospital> hospitals=super.findByHqlWithValuesMap(hqlBuffer.toString(),hqlParamMap,true);
		   if(hospitals.size()>0){
			   dto.getOrder().setHopId(hospitals.get(0).getHospitalId());
		   }else{
			   dto.setOpFlg("2");
			   dto.setMsg("医院标示在平台没有维护");
			   return;
		   }
	   }
	   //入库科室
	   if(dto.getOrder().getPurLoc()!=null){
		   Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		   hqlBuffer.delete(0, hqlBuffer.length());

		   hqlBuffer.append(" from HopCtloc t ");
		   hqlBuffer.append(" where t.hisid = :hisid");
		   hqlParamMap.put("hisid", dto.getOrder().getPurLoc());
		   hqlBuffer.append(" and t.hospid = :hopid");
		   hqlParamMap.put("hopid", dto.getOrder().getHopId());
		   List<HopCtloc> ctlocs=super.findByHqlWithValuesMap(hqlBuffer.toString(),hqlParamMap,true);
		   if(ctlocs.size()>0){
			   dto.getOrder().setPurLoc(ctlocs.get(0).getHopCtlocId());
		   }else{
			   //没有入库科室，或者科室在平台没有
			   dto.setOpFlg("5");
			   dto.setMsg("没有入库科室，或者科室在平台没有");
			   return;
		   }
	   }
	   //收货科室
	   if(dto.getOrder().getRecLoc()!=null){
		   Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		   hqlBuffer.delete(0, hqlBuffer.length());

		   hqlBuffer.append(" from HopCtloc t ");
		   hqlBuffer.append(" where t.hisid = :hisid");
		   hqlParamMap.put("hisid", dto.getOrder().getRecLoc());
		   hqlBuffer.append(" and t.hospid = :hopid");
		   hqlParamMap.put("hopid", dto.getOrder().getHopId());
		   List<HopCtloc> ctlocs=super.findByHqlWithValuesMap(hqlBuffer.toString(),hqlParamMap,true);
		   if(ctlocs.size()>0){
			   dto.getOrder().setRecLoc(ctlocs.get(0).getHopCtlocId());
		   }else{
			   //没有入库科室，或者科室在平台没有
			   dto.setOpFlg("5");
			   dto.setMsg("没有接收科室，或者科室在平台没有");
			   //return;
		   }
	   }
	   
	   
	 //供应商
	   if(dto.getOrder().getVendorId()!=null){
		   Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		   hqlBuffer.delete(0, hqlBuffer.length());

		   hqlBuffer.append(" from Vendor t ");
		   hqlBuffer.append(" where t.hisId = :hisid");
		   hqlParamMap.put("hisid", dto.getOrder().getVendorId());
		   List<Vendor> vendors=super.findByHqlWithValuesMap(hqlBuffer.toString(),hqlParamMap,true);
		   if(vendors.size()>0){
			   dto.getOrder().setVendorId(vendors.get(0).getVendorId());
		   }else{
			   //没有入库科室，或者科室在平台没有
			   dto.setOpFlg("6");
			   dto.setMsg("供应商在平台没有维护");
			   return;
		   }
	   }else{
		   dto.setOpFlg("6");
		   dto.setMsg("供应商不能为空");
		   return;
	   }
	   
	   if(dto.getOrder().getOrderNo()==null){
		   //his订单号不能为空
		   dto.setOpFlg("3"); 
		   return;
	   }
	   Order order=dto.getOrder();
	   order.setCreateUser(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
	   order.setPlanDate(new Date());
	   super.save(order);
	   
	   
	   
	   List<OrderItm> orderItms=dto.getOrderItms();
	   for(int i=0;i<orderItms.size();i++){
		   orderItms.get(i).setOrdId(order.getOrderId());
		   Long hisIncIdLong= orderItms.get(i).getIncId();
		   hqlBuffer.delete(0, hqlBuffer.length());
		   Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		   hqlBuffer.append(" from HopInc t ");
		   hqlBuffer.append(" where t.incHospid = :hopid");
		   hqlParamMap.put("hopid", order.getHopId());
		   hqlBuffer.append(" and t.incHissysid = :incHissysid");
		   hqlParamMap.put("incHissysid", hisIncIdLong);
		   List<HopInc> hopIncs=super.findByHqlWithValuesMap(hqlBuffer.toString(),hqlParamMap,true);
		   if(hopIncs.size()>0){
			   orderItms.get(i).setIncId(hopIncs.get(0).getIncId());
		   }else{
			   orderItms.remove(i);
			   if(StringUtils.isNullOrEmpty(dto.getMsg())){
				   dto.setMsg(hisIncIdLong.toString()+":在平台里有没");
			   }else{
				   dto.setMsg(dto.getMsg()+"."+hisIncIdLong.toString()+":在平台里有没");
				   dto.setOpFlg("4");
			   }
		   }
	   }
	   if(orderItms.size()>0){
		  super.batchSaveOrUpdate(orderItms);
	   }
	   dto.setOpFlg("1");
   }
   
   /**
    * 
   * @Title: OrderDao.java
   * @Description: TODO(修改订单明细)
   * @param dto
   * @return:void 
   * @author zhouxin  
   * @date 2014年6月5日 上午9:37:33
   * @version V1.0
    */
   public void saveOrditm(OrderDto dto){
	   
	   super.saveOrUpdate(dto.getOrderItm());
	   dto.setOpFlg("1");
   }
   
   /**
    * 
   * @Title: OrderDao.java
   * @Description: TODO(删除订单明细)
   * @param dto
   * @return:void 
   * @author zhouxin  
   * @date 2014年6月5日 上午9:39:25
   * @version V1.0
    */
   public void deleteOrditm(OrderDto dto){
	   
	   super.deleteById(OrderItm.class, dto.getOrderItm().getOrderitmId());
	   dto.setOpFlg("1");
   }
   
   /**
    * 
   * @Title: OrderDao.java
   * @Description: TODO(医院确定订单完成)
   * @param dto
   * @return:void 
   * @author zhouxin  
   * @date 2014年6月5日 下午8:43:26
   * @version V1.0
    */
   public void complete(OrderDto dto){
	   
	   ExeState exeState=new ExeState();
	   exeState.setStateId(Long.valueOf(1));
	   exeState.setRemark(dto.getOrder().getRemark());
	   exeState.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
	   exeState.setOrdId(dto.getOrder().getOrderId());
	   exeState.setExedate(new Date());
	   super.save(exeState);
	   dto.setOpFlg("1");
   }
}
