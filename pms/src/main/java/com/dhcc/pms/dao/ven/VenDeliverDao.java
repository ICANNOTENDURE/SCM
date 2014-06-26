/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.ven;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.dhcc.pms.dao.hop.HopIncDao;
import com.dhcc.pms.dao.ord.OrderStateDao;
import com.dhcc.pms.dto.ven.VenDeliverDto;
import com.dhcc.pms.entity.hop.HopVendor;
import com.dhcc.pms.entity.ord.ExeState;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.ord.OrderItm;
import com.dhcc.pms.entity.ven.VenDeliver;
import com.dhcc.pms.entity.ven.VenDeliveritm;
import com.dhcc.pms.entity.vo.ven.DeliverItmVo;
import com.dhcc.pms.entity.vo.ven.DeliverVo;

@Repository
public class VenDeliverDao extends HibernatePersistentObjectDAO<VenDeliver> {
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	@Resource
	private HopIncDao hopIncDao;
	
	@Resource
	private OrderStateDao orderStateDao;
	
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
	public void AccectOrder(VenDeliverDto dto){
		StringBuilder hqlStr = new StringBuilder();
		
		Order order=super.get(Order.class, dto.getVenDeliver().getDeliverOrderid());
		VenDeliver venDeliver=new VenDeliver();
		venDeliver.setDeliverHopid(order.getHopId());
		venDeliver.setDeliverOrderid(order.getOrderId());
		venDeliver.setDeliverUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		venDeliver.setDeliverPurloc(order.getPurLoc());
		venDeliver.setDeliverRecloc(order.getRecLoc());
		venDeliver.setHopVendorId(order.getVendorId()); //医院供应商
		venDeliver.setDeliverVendorid(super.get(HopVendor.class, order.getVendorId()).getHopVenId()); //供应商
		venDeliver.setDeliverDestinationid(order.getRecDestination());
		venDeliver.setDeliverAccpecctDate(new java.sql.Timestamp(new Date().getTime()));
		super.saveOrUpdate(venDeliver);
		//保存发货主表t_ven_deliver
		
		ExeState exeState=new ExeState();
		exeState.setDeliverId(venDeliver.getDeliverId());
		exeState.setExedate(new java.sql.Timestamp(new Date().getTime()));
		exeState.setStateId(Long.valueOf(2));
		exeState.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		super.saveOrUpdate(exeState);
		//保存订单执行表t_ord_exestate
		
		venDeliver.setDeliverExestateid(exeState.getExestateId());
		super.saveOrUpdate(venDeliver);
		dto.setVenDeliver(venDeliver);
		//更新发货主表
		
		hqlStr.delete(0, hqlStr.length());
		hqlStr.append(" from OrderItm t where t.ordId=?");
		List<OrderItm> orderItms=super.findByHql(hqlStr.toString(), dto.getVenDeliver().getDeliverOrderid());
		for(OrderItm tmpOrderItm:orderItms){
			if((tmpOrderItm.getFlag()==null)||(tmpOrderItm.getFlag().toString().equals("1"))){
				VenDeliveritm venDeliveritm=new VenDeliveritm();
				venDeliveritm.setDeliveritmHopincid(tmpOrderItm.getIncId());
				venDeliveritm.setDeliveritmOrderitmid(tmpOrderItm.getOrderitmId());
				venDeliveritm.setDeliveritmParentid(venDeliver.getDeliverId());
				if(tmpOrderItm.getDeliverqty()==null){
					venDeliveritm.setDeliveritmQty(tmpOrderItm.getReqqty());
				}else{
					venDeliveritm.setDeliveritmQty(tmpOrderItm.getReqqty().floatValue()-tmpOrderItm.getDeliverqty().floatValue());
				}
				venDeliveritm.setDeliveritmUom(tmpOrderItm.getUom());
				venDeliveritm.setDeliveritmRp(tmpOrderItm.getRp());
				venDeliveritm.setDeliveritmVenincid(hopIncDao.getVenIncByHopInc(tmpOrderItm.getIncId()));
				super.saveOrUpdate(venDeliveritm);
			}
		}
		//保存发货明细表
	}
	
	
	/**
	 * 
	* @Title: VenDeliverDao.java
	* @Description: TODO(查询发货单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 上午9:52:04
	* @version V1.0
	 */
	public void listDeliver(VenDeliverDto dto){
		
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.DELIVER_ROWID as deliverid,  ");
		hqlBuffer.append("t1.DELIVER_ACCPECTDATE as deliveraccpectdate,  ");
		hqlBuffer.append("t1.DELIVER_DATE as deliverdate,  ");
		hqlBuffer.append("t3.state_name as statedesc,  ");
		hqlBuffer.append("t4.hospital_name as hopname,  ");
		hqlBuffer.append("t1.DELIVER_REMARK as remark,  ");
		hqlBuffer.append("t5.order_no as hisno,  ");
		hqlBuffer.append("t7.realname as deliveraccpectuser,  ");
		hqlBuffer.append("t9.realname as deliveruser,  ");
		hqlBuffer.append("t10.ctloc_name as purloc,  ");
		hqlBuffer.append("t11.ctloc_name as recloc,  ");
		hqlBuffer.append("t12.ctlocdes_destination as destination  ");
		hqlBuffer.append("from  t_ven_deliver t1 ");
		hqlBuffer.append("left join t_ord_exestate t2 on t1.deliver_exestateid=t2.exestate_id ");
		hqlBuffer.append("left join t_ord_state t3 on t3.state_seq=t2.state_id ");
		hqlBuffer.append("left join t_sys_hospital t4 on t4.hospital_id=t1.deliver_hopid ");
		hqlBuffer.append("left join t_ord_order t5 on t5.order_id=t1.deliver_orderid ");
		hqlBuffer.append("left join t_sys_normal_account t6 on t6.account_id=t1.deliver_userid ");
		hqlBuffer.append("left join t_sys_normal_user t7 on t7.faccount_id=t6.account_id ");
		hqlBuffer.append("left join t_sys_normal_account t8 on t8.account_id=t1.deliver_accpuserid ");
		hqlBuffer.append("left join t_sys_normal_user t9 on t9.faccount_id=t8.account_id ");
		hqlBuffer.append("left join t_sys_ctloc t10 on t10.ctloc_id=t1.deliver_purloc ");
		hqlBuffer.append("left join t_sys_ctloc t11 on t11.ctloc_id=t1.deliver_recloc ");
		hqlBuffer.append("left join t_sys_ctloc_destination t12 on t12.ctlocdes_id=t1.deliver_destinationid ");
		//hqlBuffer.append("left join T_HOP_VENDOR t13 on t13.H_VENID=t1.DELIVER_VENDORID ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        
		if(dto.getStDate()!=null){
			hqlBuffer.append("and t1.DELIVER_DATE>=:Stdate ");
			hqlParamMap.put("Stdate", dto.getStDate());
		}
		if(dto.getEdDate()!=null){
			hqlBuffer.append("and t1.DELIVER_DATE<=:Eddate ");
			hqlParamMap.put("Eddate", dto.getEdDate());
		}
		if(dto.getAccpStDate()!=null){
			hqlBuffer.append("and t1.DELIVER_ACCPECTDATE>=:ReqStDate ");
			hqlParamMap.put("ReqStDate", dto.getAccpStDate());
		}
		if(dto.getAccpEdDate()!=null){
			hqlBuffer.append("and t1.DELIVER_ACCPECTDATE<=:ReqEdDate ");
			hqlParamMap.put("ReqEdDate", dto.getAccpEdDate());
		}
		if(dto.getHopId()!=null){
			hqlBuffer.append("and t1.DELIVER_HOPID=:hopId ");
			hqlParamMap.put("hopId", dto.getHopId());
		}
		if(!StringUtils.isNullOrEmpty(dto.getEmFlag())){
			hqlBuffer.append("and t5.emflag=:emflag ");
			hqlParamMap.put("emflag", dto.getEmFlag());
		}
		if(dto.getPurLocId()!=null){
			hqlBuffer.append("and t1.DELIVER_PURLOC=:PurLocId ");
			hqlParamMap.put("PurLocId", dto.getPurLocId());
		}
		if(dto.getState()!=null){
			hqlBuffer.append("and t3.STATE_SEQ=:State ");
			hqlParamMap.put("State", dto.getState());
		}
		
		
		
		Long type=WebContextHolder.getContext().getVisit().getUserInfo().getUserType();
		if(type==2){
			hqlBuffer.append("and t1.DELIVER_VENDORID=:venId ");
			hqlParamMap.put("venId", WebContextHolder.getContext().getVisit().getUserInfo().getVendorIdLong());
		}
		
		
		dto.getPageModel().setQueryHql(hqlBuffer.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		jdbcTemplateWrapper.fillPagerModelData(dto.getPageModel(), DeliverVo.class, "DELIVER_ROWID");
	}
	
	
	/**
	 * 
	* @Title: VenDeliverDao.java
	* @Description: TODO(查询发货单明细)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午3:59:03
	* @version V1.0
	 */
	public void listDeliverItm(VenDeliverDto dto){
		
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.DELIVERITM_ID as deliveritmid,  ");
		hqlBuffer.append("t1.deliveritm_hopincid as hopincid,  ");
		hqlBuffer.append("t2.VEN_INC_CODE as venincncode,  ");
		hqlBuffer.append("t2.VEN_INC_NAME as venincname,  ");
		hqlBuffer.append("t3.INC_NAME as hopincname,  ");
		hqlBuffer.append("t2.VEN_INC_UOMNAME as uom,  ");
		hqlBuffer.append("t1.DELIVERITM_QTY as deliverqty,  ");
		hqlBuffer.append("t5.REQQTY as orderqty,  ");
		hqlBuffer.append("t5.DELIVERQTY as sendedqty,  ");
		hqlBuffer.append("t1.DELIVERITM_BATNO as batno,  ");
		hqlBuffer.append("t1.DELIVERITM_INVNOE as invno,  ");
		hqlBuffer.append("t1.DELIVERITM_EXPDATE as expdate,  ");
		hqlBuffer.append("t1.DELIVERITM_RP as rp,  ");
		hqlBuffer.append("t1.DELIVERITM_RPAMT as rpamt,  ");
		hqlBuffer.append("t4.NAME as manf  ");
		hqlBuffer.append("from t_ven_deliveritm t1 ");
		
		hqlBuffer.append("left join t_hop_inc t3 on t1.deliveritm_hopincid=t3.inc_id ");
		hqlBuffer.append("left join t_hop_manf t4 on t4.id=t3.inc_manfid ");
		hqlBuffer.append("left join t_ord_orderitm t5 on t5.orderitm_id=t1.deliveritm_orderitmid ");
		hqlBuffer.append("left join t_ven_hop_inc t6 on t1.deliveritm_hopincid=t6.hop_inc_id ");
		hqlBuffer.append("left join t_ven_inc t2 on t2.ven_inc_rowid=t6.ven_inc_id ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        
		if(dto.getVenDeliver()==null){
			dto.getPageModel().setPageDataJson(null);
			dto.getPageModel().setTotals(0);
			return;
		}
		if(dto.getVenDeliver()!=null){
			if(dto.getVenDeliver().getDeliverId()!=null){
				hqlBuffer.append("and t1.DELIVERITM_PARENTID=:parentid ");
				hqlParamMap.put("parentid", dto.getVenDeliver().getDeliverId());
			}
		}
		
		hqlBuffer.append(" order by t3.INC_NAME");
		dto.getPageModel().setQueryHql(hqlBuffer.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		jdbcTemplateWrapper.fillPagerModelData(dto.getPageModel(), DeliverItmVo.class, "DELIVERITM_ID");
	}
	
	/**
	 * 
	* @Title: VenDeliverDao.java
	* @Description: TODO(保存更新发货表明细)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午4:56:39
	* @version V1.0
	 */
	public void saveDeliverItm(VenDeliverDto dto){
		VenDeliveritm venDeliveritm=new VenDeliveritm();
		if(dto.getVenDeliveritm().getDeliveritmId()!=null){
			venDeliveritm=super.get(VenDeliveritm.class, dto.getVenDeliveritm().getDeliveritmId());
		}else{
			venDeliveritm.setDeliveritmParentid(dto.getVenDeliveritm().getDeliveritmParentid());
			venDeliveritm.setDeliveritmHopincid(dto.getVenDeliveritm().getDeliveritmHopincid());
			venDeliveritm.setDeliveritmVenincid(hopIncDao.getVenIncByHopInc(dto.getVenDeliveritm().getDeliveritmHopincid()));
		}
		venDeliveritm.setDeliveritmQty(dto.getVenDeliveritm().getDeliveritmQty());
		venDeliveritm.setDeliveritmBatno(dto.getVenDeliveritm().getDeliveritmBatno());
		venDeliveritm.setDeliveritmExpdate(dto.getVenDeliveritm().getDeliveritmExpdate());
		venDeliveritm.setDeliveritmInvnoe(dto.getVenDeliveritm().getDeliveritmInvnoe());
		venDeliveritm.setDeliveritmRp(dto.getVenDeliveritm().getDeliveritmRp());
		super.saveOrUpdate(venDeliveritm);
		dto.setVenDeliveritm(venDeliveritm);
	}
	
	@SuppressWarnings("unchecked")
	public List<VenDeliveritm> getDeliveritms(Long deliverId){
		StringBuffer hqlBuffer = new StringBuffer();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		hqlBuffer.append(" from VenDeliveritm h");
		hqlBuffer.append(" where 1=1 ");
		hqlBuffer.append(" and h.deliveritmParentid = :deliverId ");
		hqlParamMap.put("deliverId",deliverId);
		return (List<VenDeliveritm>)this.findByHqlWithValuesMap(hqlBuffer.toString(),hqlParamMap,false);
	
		
	}
	
	/**
	 * 
	* @Title: VenDeliverDao.java
	* @Description: TODO(导入发票,按发货单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月20日 下午2:28:19
	* @version V1.0
	 */
	public void impInv(VenDeliverDto dto){
		List<VenDeliveritm> venDeliveritms =this.getDeliveritms(dto.getVenDeliver().getDeliverId());
		
		for(VenDeliveritm tmpVenDeliveritm:venDeliveritms){
			int j=0;;
			for(int i=0;i<dto.getVenDeliveritms().size();i++){
				String tmpString=tmpVenDeliveritm.getDeliveritmHopincid().toString();
				VenDeliveritm tmpsVenDeliveritm=dto.getVenDeliveritms().get(i);
				String tmpsString=tmpsVenDeliveritm.getDeliveritmHopincid().toString();
				if(tmpString.equals(tmpsString)){
					if(!StringUtils.isNullOrEmpty(tmpsVenDeliveritm.getDeliveritmBatno())){
						tmpVenDeliveritm.setDeliveritmBatno(tmpsVenDeliveritm.getDeliveritmBatno());
					}
					if(tmpsVenDeliveritm.getDeliveritmExpdate()!=null){
						tmpVenDeliveritm.setDeliveritmExpdate(tmpsVenDeliveritm.getDeliveritmExpdate());
					}
					if(!StringUtils.isNullOrEmpty(tmpsVenDeliveritm.getDeliveritmInvnoe())){
						tmpVenDeliveritm.setDeliveritmInvnoe(tmpsVenDeliveritm.getDeliveritmInvnoe());
					}
					if(tmpsVenDeliveritm.getDeliveritmQty()!=null){
						tmpVenDeliveritm.setDeliveritmQty(tmpsVenDeliveritm.getDeliveritmQty());
					}
					if(tmpsVenDeliveritm.getDeliveritmRp()!=null){
						tmpVenDeliveritm.setDeliveritmRp(tmpsVenDeliveritm.getDeliveritmRp());
					}
					j=i;
					
					super.save(tmpVenDeliveritm);
				}
			}
			if(j!=0){
				dto.getVenDeliveritms().remove(j);
				j=0;
			}
		}
		if(dto.getVenDeliveritms().size()>0){
			for(VenDeliveritm tmpssVenDeliveritm:dto.getVenDeliveritms()){
				for(VenDeliveritm tmpsssVenDeliveritm:venDeliveritms){
					String tmpString=tmpssVenDeliveritm.getDeliveritmHopincid().toString();
					String tmpsString=tmpsssVenDeliveritm.getDeliveritmHopincid().toString();
					if(tmpString.equals(tmpsString)){
						tmpssVenDeliveritm.setDeliveritmOrderitmid(tmpsssVenDeliveritm.getDeliveritmOrderitmid());
						tmpssVenDeliveritm.setDeliveritmParentid(dto.getVenDeliver().getDeliverId());
						super.save(tmpssVenDeliveritm);
					}
				}
			}
		}
		
	}
	
	/**
	 * 
	* @Title: VenDeliverDao.java
	* @Description: TODO(更据订单号导入发货单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月20日 下午10:46:44
	* @version V1.0
	 */
	public void impByOrder(VenDeliverDto dto){
		
		Iterator<String> it = dto.getOrderMap().keySet().iterator();
		q1:
		while(it.hasNext()){
			String key = (String) it.next();
			List<Order> orders=orderStateDao.getOrderByNo(key);
			
			Order order=new Order();
			if(orders.size()==1){
				order=orders.get(0);
			}
			if(orders.size()==0){
				dto.setOpFlg("2");
				dto.setMsg(key+"订单号在his里没有");
				break q1;
			}
			if(orders.size()>1){
				dto.setOpFlg("2");
				dto.setMsg(key+"订单号在his里重复");
				break q1;
			}
			
			/*********************保存发货表关联订单执行表t_ord_exestate和发货表t_ven_delivery  start***********************/
			VenDeliver venDeliver=new VenDeliver();
			venDeliver.setDeliverHopid(order.getHopId());
			venDeliver.setDeliverOrderid(order.getOrderId());
			venDeliver.setDeliverUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
			venDeliver.setDeliverPurloc(order.getPurLoc());
			venDeliver.setDeliverRecloc(order.getRecLoc());
			venDeliver.setHopVendorId(order.getVendorId()); //医院供应商
			venDeliver.setDeliverVendorid(super.get(HopVendor.class, order.getVendorId()).getHopVenId()); //供应商
			venDeliver.setDeliverDestinationid(order.getRecDestination());
			venDeliver.setDeliverAccpecctDate(new java.sql.Timestamp(new Date().getTime()));
			super.saveOrUpdate(venDeliver);
			ExeState exeState=new ExeState();
			exeState.setDeliverId(venDeliver.getDeliverId());
			exeState.setExedate(new java.sql.Timestamp(new Date().getTime()));
			exeState.setStateId(Long.valueOf(2));
			exeState.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
			super.saveOrUpdate(exeState);
			venDeliver.setDeliverExestateid(exeState.getExestateId());
			super.saveOrUpdate(venDeliver);
			/*************保存发货表关联订单执行表t_ord_exestate和发货表t_ven_delivery  end**********************/
			ExeState exeState2=new ExeState();
			exeState2.setExedate(new java.sql.Timestamp(new Date().getTime()));
			exeState2.setOrdId(order.getOrderId());
			exeState2.setStateId(Long.valueOf(2));
			exeState2.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
			super.saveOrUpdate(exeState2);
			order.setExeStateId(exeState2.getExestateId());
			super.saveOrUpdate(venDeliver);
			//保存订单表关联订单执行表t_ord_exestate和订单表t_ord_order
			
			
			
			//保存发货子表 ，一条订单会有多个批次的发货记录
			List<VenDeliveritm> venDeliveritms = dto.getOrderMap().get(key);
			List<OrderItm> orderItms=orderStateDao.getOrderItms(order.getOrderId());
			for(OrderItm tmpOrderItm:orderItms){
				int j=0;
				for(int i=0;i<venDeliveritms.size();i++){
					if(tmpOrderItm.getIncId().toString().equals(venDeliveritms.get(i).getDeliveritmHopincid().toString())){
						VenDeliveritm tmpVenDeliveritm=venDeliveritms.get(i);
						tmpVenDeliveritm.setDeliveritmOrderitmid(tmpOrderItm.getOrderitmId());
						tmpVenDeliveritm.setDeliveritmParentid(venDeliver.getDeliverId());
						super.saveOrUpdate(tmpVenDeliveritm);
						j=1;
					}
				}
				if(j!=0){
					dto.getOrderMap().get(key).remove(j);
					j=0;
				}
			}
			//一个药多批次
			if(dto.getOrderMap().get(key).size()>0){
				for(VenDeliveritm venDeliveritm:dto.getOrderMap().get(key)){
					for(OrderItm tmpOrderItm:orderItms){
						if(tmpOrderItm.getIncId().toString().equals(venDeliveritm.getDeliveritmHopincid().toString())){
							venDeliveritm.setDeliveritmOrderitmid(tmpOrderItm.getOrderitmId());
							venDeliveritm.setDeliveritmParentid(venDeliver.getDeliverId());
							super.saveOrUpdate(venDeliveritm);
						}
					}
				}
				
			}
		}
	}
	
	
	/**
	 * 
	* @Title: VenDeliverDao.java
	* @Description: TODO(供应商发货)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月24日 上午11:03:31
	* @version V1.0
	 */
	public void deliver(VenDeliverDto dto){
		
		ExeState exeState=new ExeState();
		exeState.setExedate(new java.sql.Timestamp(new Date().getTime()));
		exeState.setDeliverId(dto.getVenDeliver().getDeliverId());
		exeState.setStateId(Long.valueOf(5));  //发货
		exeState.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		super.saveOrUpdate(exeState);
		Long deliverId=dto.getVenDeliver().getDeliverId();
		VenDeliver venDeliver=super.get(VenDeliver.class, deliverId);
		venDeliver.setDeliverDate(new java.sql.Timestamp(new Date().getTime()));
		venDeliver.setDeliverUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		venDeliver.setDeliverExestateid(exeState.getExestateId());
		super.saveOrUpdate(venDeliver);
		
		
		ExeState exeState2=new ExeState();
		exeState2.setExedate(new java.sql.Timestamp(new Date().getTime()));
		exeState2.setOrdId(venDeliver.getDeliverOrderid());
		exeState2.setStateId(Long.valueOf(5));  //发货
		exeState2.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		super.saveOrUpdate(exeState2);
		Order order=super.get(Order.class, venDeliver.getDeliverOrderid());
		order.setExeStateId(exeState2.getExestateId());
		
		
		
		
		List<VenDeliveritm> venDeliveritms =this.getDeliveritms(dto.getVenDeliver().getDeliverId());
		for(VenDeliveritm tmpVenDeliveritm:venDeliveritms){
			OrderItm orderItm=super.get(OrderItm.class, tmpVenDeliveritm.getDeliveritmOrderitmid());
			if(orderItm.getDeliverqty()==null){
				orderItm.setDeliverqty(0f);
			}
			if(tmpVenDeliveritm.getDeliveritmQty()==null){
				tmpVenDeliveritm.setDeliveritmQty(0f);
			}
			Float deliverqty=orderItm.getDeliverqty().floatValue()+tmpVenDeliveritm.getDeliveritmQty().floatValue();
			//logger.info("deliverqty:"+deliverqty);
			//logger.info("deliverqty1:"+orderItm.getDeliverqty().floatValue());
			//logger.info("deliverqty2:"+tmpVenDeliveritm.getDeliveritmQty().floatValue());
			orderItm.setDeliverqty(deliverqty);
			orderItm.setFlag(Long.valueOf("1"));
			if((orderItm.getDeliverqty().floatValue()-orderItm.getReqqty().floatValue())>=0){
				orderItm.setFlag(Long.valueOf("2"));
			}
			super.saveOrUpdate(orderItm);
		}
		
		List<OrderItm> orderItms=orderStateDao.getOrderItms(order.getOrderId());
		int orderFlag=0;
		for(OrderItm tmpOrderItm:orderItms){
			if(tmpOrderItm.getFlag()!=null){
				if(!tmpOrderItm.getFlag().toString().equals("2")){
					orderFlag=1;
				}
			}else{
				orderFlag=1;
			}
		}
		order.setOrdFlag(Long.valueOf("1"));
		if(orderFlag==0){
			order.setOrdFlag(Long.valueOf("2"));
		}
		super.saveOrUpdate(order);
	}
	
	
	/**
	 * 
	* @Title: VenDeliverDao.java
	* @Description: TODO(删除发货单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月24日 下午4:14:11
	* @version V1.0
	 */
	public void delete(VenDeliverDto dto){
		//VenDeliver venDeliver=super.get(VenDeliver.class, dto.getVenDeliver().getDeliverId(),LockMode.WRITE); //(VenDeliver.class, dto.getVenDeliver().getDeliverId());;
		//以收货
		VenDeliver venDeliver=super.get(VenDeliver.class, dto.getVenDeliver().getDeliverId());
		if(super.get(ExeState.class, venDeliver.getDeliverExestateid()).getStateId().toString().equals("6")){
			
			dto.setOpFlg("2");
			return;
		}
		Order order=super.get(Order.class, venDeliver.getDeliverOrderid());
		super.delete(venDeliver);
		StringBuilder hqlBuffer = new StringBuilder();
		hqlBuffer.delete(0, hqlBuffer.length());
		hqlBuffer.append(" delete from VenDeliveritm t ");
		hqlBuffer.append(" where t.deliveritmParentid = ?");
		this.updateByHqlWithFreeParam(hqlBuffer.toString(),dto.getVenDeliver().getDeliverId());
		
		hqlBuffer.delete(0, hqlBuffer.length());
		hqlBuffer.append(" delete from ExeState t ");
		hqlBuffer.append(" where t.deliverId = ?");
		this.updateByHqlWithFreeParam(hqlBuffer.toString(),dto.getVenDeliver().getDeliverId());
		
		
		ExeState exeState=new ExeState();
		exeState.setExedate(new java.sql.Timestamp(new Date().getTime()));
		exeState.setOrdId(order.getOrderId());
		exeState.setStateId(Long.valueOf(1));  //新建
		exeState.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		exeState.setRemark("删除发货单");
		super.saveOrUpdate(exeState);
		
	
		order.setExeStateId(exeState.getExestateId());
		super.saveOrUpdate(order);
		
		
		
		List<VenDeliveritm> venDeliveritms =this.getDeliveritms(dto.getVenDeliver().getDeliverId());
		for(VenDeliveritm tmpVenDeliveritm:venDeliveritms){
			OrderItm orderItm=super.get(OrderItm.class, tmpVenDeliveritm.getDeliveritmOrderitmid());
			if(orderItm.getDeliverqty()==null){
				orderItm.setDeliverqty(0f);
			}
			if(tmpVenDeliveritm.getDeliveritmQty()==null){
				tmpVenDeliveritm.setDeliveritmQty(0f);
			}
			Float deliverqty=orderItm.getDeliverqty().floatValue()-tmpVenDeliveritm.getDeliveritmQty().floatValue();
			orderItm.setDeliverqty(deliverqty);
			orderItm.setFlag(Long.valueOf("1"));
			super.saveOrUpdate(orderItm);
		}
	}
	
	
	/**
	 * 
	* @Title: VenDeliverDao.java
	* @Description: TODO(取消发货状态)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月24日 下午4:52:00
	* @version V1.0
	 */
	public void cancelComplete(VenDeliverDto dto){
		VenDeliver venDeliver=super.get(VenDeliver.class, dto.getVenDeliver().getDeliverId());
		Order order=super.get(Order.class, venDeliver.getDeliverOrderid());
		//以收货
		if(!super.get(ExeState.class, venDeliver.getDeliverExestateid()).getStateId().toString().equals("5")){
			
			dto.setOpFlg("2");
			return;
		}
		ExeState exeState=new ExeState();
		exeState.setExedate(new java.sql.Timestamp(new Date().getTime()));
		exeState.setDeliverId(dto.getVenDeliver().getDeliverId());
		exeState.setStateId(Long.valueOf(2));  //接收
		exeState.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		exeState.setRemark("取消发货状态");
		super.saveOrUpdate(exeState);
		
		venDeliver.setDeliverExestateid(exeState.getExestateId());
		super.saveOrUpdate(venDeliver);
		
		
		ExeState exeState2=new ExeState();
		exeState2.setExedate(new java.sql.Timestamp(new Date().getTime()));
		exeState2.setOrdId(order.getOrderId());
		exeState2.setStateId(Long.valueOf(2));  //接收
		exeState2.setUserid(Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId()));
		exeState2.setRemark("取消发货状态");
		super.saveOrUpdate(exeState2);
		
		order.setExeStateId(exeState2.getExestateId());
		super.saveOrUpdate(order);
		
		
		List<VenDeliveritm> venDeliveritms =this.getDeliveritms(dto.getVenDeliver().getDeliverId());
		for(VenDeliveritm tmpVenDeliveritm:venDeliveritms){
			OrderItm orderItm=super.get(OrderItm.class, tmpVenDeliveritm.getDeliveritmOrderitmid());
			if(orderItm.getDeliverqty()==null){
				orderItm.setDeliverqty(0f);
			}
			if(tmpVenDeliveritm.getDeliveritmQty()==null){
				tmpVenDeliveritm.setDeliveritmQty(0f);
			}
			Float deliverqty=orderItm.getDeliverqty().floatValue()-tmpVenDeliveritm.getDeliveritmQty().floatValue();
			orderItm.setDeliverqty(deliverqty);
			orderItm.setFlag(Long.valueOf("1"));
			super.saveOrUpdate(orderItm);
		}
		
	}
	
	
	
	
	
}
