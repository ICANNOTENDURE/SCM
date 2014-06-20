/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.ven;

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
import com.dhcc.pms.dao.hop.HopIncDao;
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
		//更新发货主表
		
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
			venDeliveritm.setDeliveritmVenincid(hopIncDao.getVenIncByHopInc(tmpOrderItm.getIncId()));
			super.saveOrUpdate(venDeliveritm);
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
		
		VenDeliveritm venDeliveritm=super.get(VenDeliveritm.class, dto.getVenDeliveritm().getDeliveritmId());
		venDeliveritm.setDeliveritmQty(dto.getVenDeliveritm().getDeliveritmQty());
		venDeliveritm.setDeliveritmBatno(dto.getVenDeliveritm().getDeliveritmBatno());
		venDeliveritm.setDeliveritmExpdate(dto.getVenDeliveritm().getDeliveritmExpdate());
		venDeliveritm.setDeliveritmInvnoe(dto.getVenDeliveritm().getDeliveritmInvnoe());
		venDeliveritm.setDeliveritmRp(dto.getVenDeliveritm().getDeliveritmRp());
		super.saveOrUpdate(venDeliveritm);
	}
}
