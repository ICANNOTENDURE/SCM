/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.ord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.hibernate.dao.CommonDao;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.ord.OrderStateDto;
import com.dhcc.pms.entity.hop.HopVendor;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.ord.OrderItm;
import com.dhcc.pms.entity.ord.State;
import com.dhcc.pms.entity.vo.ord.OrderExeStateVo;
import com.dhcc.pms.entity.vo.ord.OrderItmVo;
import com.dhcc.pms.entity.vo.ord.OrderStateVo;
import com.dhcc.pms.entity.vo.ws.OrderItmWebVo;
import com.dhcc.pms.entity.vo.ws.OrderWebVo;

@Repository
public class OrderStateDao extends HibernatePersistentObjectDAO<Order> {

	@Resource
	private CommonDao commonDao;
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	public void buildPagerModelQuery(PagerModel pagerModel, BaseDto dto) {


	}


  
    /**
     * 
    * @Title: OrdStateDao.java
    * @Description: TODO(查询所有订单)
    * @param ordShopping
    * @return
    * @return:List<ShopCartVo> 
    * @author zhouxin  
    * @date 2014年5月28日 上午9:34:43
    * @version V1.0
     */
	public void listOrderState(OrderStateDto dto) {

		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.remark remark, ");
		hqlBuffer.append("t1.RECDESTINATION destinationid, ");
		hqlBuffer.append("t1.order_no hisno, ");
		hqlBuffer.append("t1.vendor_id vendorid, ");
		hqlBuffer.append("t1.recloc reclocid, ");
		hqlBuffer.append("t1.purloc purlocid, ");
		hqlBuffer.append("t3.state_name statedesc, ");
		hqlBuffer.append("t1.order_id orderid, ");
		hqlBuffer.append("t1.emflag as emflag, ");
		hqlBuffer.append("t4.ctloc_name as recloc, ");
		hqlBuffer.append("t5.ctloc_name as purloc, ");
		hqlBuffer.append("t6.ctlocdes_destination as destination, ");
		hqlBuffer.append("t7.name exeuser, ");
		hqlBuffer.append("t8.realname as realname, ");
		hqlBuffer.append("t1.deliverydate as deliverydate, ");
		hqlBuffer.append("t2.exedate as exedate, ");
		hqlBuffer.append("t10.HOSPITAL_NAME as hopname, ");
		hqlBuffer.append("t1.ORDER_SERIAL as serialno, ");
		hqlBuffer.append("t9.H_NAME as vendor ");
		hqlBuffer.append("from t_ord_order t1  ");
		hqlBuffer.append("left join t_ord_exestate t2 on t1.exestate_id=t2.exestate_id  ");
		hqlBuffer.append("left join t_ord_state t3 on t2.state_id=t3.state_seq ");
		hqlBuffer.append("left join t_sys_ctloc t4 on t4.ctloc_id=t1.recloc ");
		hqlBuffer.append("left join t_sys_ctloc t5 on t5.ctloc_id=t1.purloc ");
		hqlBuffer.append("left join t_sys_ctloc_destination t6 on t6.ctlocdes_id=t1.recdestination ");
		hqlBuffer.append("left join t_sys_normal_account t7 on  t7.account_id=t1.createuser ");
		hqlBuffer.append("left join t_sys_normal_user t8 on  t8.user_id=t7.user_id ");
		hqlBuffer.append("left join T_HOP_VENDOR t9 on t9.H_VENID=t1.vendor_id ");
		hqlBuffer.append("left join T_SYS_HOSPITAL t10 on t10.HOSPITAL_ID=t1.HOP_ID ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		

		if(!StringUtils.isNullOrEmpty(dto.getEmflag())){
			if(dto.getEmflag().equals("checked")){
				hqlBuffer.append("and t1.emflag=:emflag ");
				hqlParamMap.put("emflag", dto.getEmflag());
			}
		}
	
		if(dto.getStdate()!=null){
			hqlBuffer.append("and t1.plandate>=:Stdate ");
			hqlParamMap.put("Stdate", dto.getStdate());
		}
		if(dto.getEddate()!=null){
			hqlBuffer.append("and t1.plandate<=:Eddate ");
			hqlParamMap.put("Eddate", dto.getEddate());
		}
		if(dto.getReqStDate()!=null){
			hqlBuffer.append("and t1.deliverydate>=:ReqStDate ");
			hqlParamMap.put("ReqStDate", dto.getReqStDate());
		}
		if(dto.getReqEdDate()!=null){
			hqlBuffer.append("and t1.deliverydate<=:ReqEdDate ");
			hqlParamMap.put("ReqEdDate", dto.getReqEdDate());
		}
		
		if(dto.getState()!=null){
			if(dto.getState().toString().equals("0")){
				hqlBuffer.append("and t2.state_id is null ");
			}else{ 
				hqlBuffer.append("and t2.state_id=:State ");
				hqlParamMap.put("State", dto.getState());
			}
		}
		
		if(dto.getVendor()!=null){
			hqlBuffer.append("and t1.vendor_id=:Vendor ");
			hqlParamMap.put("Vendor", dto.getVendor());
		}
		
		if(dto.getPurloc()!=null){
			hqlBuffer.append("and t1.purloc=:purloc ");
			hqlParamMap.put("purloc", dto.getPurloc());
		}
		
		if(dto.getRecLoc()!=null){
			hqlBuffer.append("and t1.recloc=:recloc ");
			hqlParamMap.put("recloc", dto.getRecLoc());
		}
		if((dto.getCmpFlag()!=null)&&(dto.getCmpFlag().toString().equals("1"))){
			hqlBuffer.append("and t1.ORD_FLAG=:cmpflag ");
			hqlParamMap.put("cmpflag", dto.getCmpFlag());
		}
		if(dto.getHopId()!=null){
			hqlBuffer.append("and t1.HOP_ID=:hopid ");
			hqlParamMap.put("hopid", dto.getHopId());
		}
		
		Long userType=WebContextHolder.getContext().getVisit().getUserInfo().getUserType();
		if(userType==null){
			return;
		}
		//医院
		if(userType==1){
			hqlBuffer.append("and t1.HOP_ID=:tmphop ");
			hqlParamMap.put("tmphop", WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
		}
		//供应商
		if(userType==2){
			hqlBuffer.append("and t9.H_VENDORID=:tmpVendor ");
			hqlParamMap.put("tmpVendor", WebContextHolder.getContext().getVisit().getUserInfo().getVendorIdLong());
		}
		
		hqlBuffer.append(" order by order_id desc ");
		if(dto.getPageModel()==null){
			PagerModel pageModel=new PagerModel();
			pageModel.setPageNo(1);
			pageModel.setPageSize(10);
			dto.setPageModel(pageModel);
		}
		dto.getPageModel().setQueryHql(hqlBuffer.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		jdbcTemplateWrapper.fillPagerModelData(dto.getPageModel(), OrderStateVo.class, "order_id");
	}
	
	
	/**
	 * 
	* @Title: OrderStateDao.java
	* @Description: TODO(查询一个订单历史)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月28日 下午5:38:25
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public List<OrderExeStateVo> listOrderExeState(OrderStateDto dto) {

		StringBuffer hqlBuffer = new StringBuffer();

		hqlBuffer.append("select t2.state_name as statedesc, ");
		hqlBuffer.append("t1.exedate as exedate, ");
		hqlBuffer.append("t1.remark as remark, ");
		hqlBuffer.append("t4.realName as exeuser, ");
		hqlBuffer.append("t4.tel as tel ");
		hqlBuffer.append("from t_ord_exestate t1  ");
		hqlBuffer.append("left join t_ord_state t2 on t2.state_seq=t1.state_id ");
		hqlBuffer.append("left join t_sys_normal_account t3 on  t3.account_id=t1.user_id ");
		hqlBuffer.append("left join t_sys_normal_user t4 on  t4.user_id=t3.user_id ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if(dto.getExeState()!=null){
			if (dto.getExeState().getOrdId()!=null){
				hqlBuffer.append("and t1.ord_id=:ord ");
				hqlParamMap.put("ord", dto.getExeState().getOrdId());
			}
			if (dto.getExeState().getDeliverId()!=null){
				hqlBuffer.append("and t1.DELIVER_ID=:deliverId ");
				hqlParamMap.put("deliverId", dto.getExeState().getDeliverId());
			}
		}

		
		hqlBuffer.append("order by t1.exestate_id  ");
		

		return jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), OrderExeStateVo.class, hqlParamMap);
	}
	
	
	/**
	 * 
	* @Title: OrderStateDao.java
	* @Description: TODO(查询订单明细)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月29日 上午9:22:16
	* @version V1.0
	 */
	public void listOrderItm(OrderStateDto dto){
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.ord_id as orderid, ");
		hqlBuffer.append("t1.orderitm_id as orderitmid, ");
		hqlBuffer.append("t1.inc_id as hopincid, ");
		hqlBuffer.append("t2.inc_name as incname, ");
		hqlBuffer.append("t2.inc_code as inccode, ");
		hqlBuffer.append("t1.reqqty as qty , ");
		hqlBuffer.append("t1.rp as rp, ");
		hqlBuffer.append("t1.DELIVERQTY as delqty, ");
		hqlBuffer.append("t1.uom as uom, ");
		hqlBuffer.append("t3.name as manf ");
		hqlBuffer.append("from t_ord_order t4 ");
		hqlBuffer.append("left join t_ord_orderitm t1 on t1.ord_id=t4.ORDER_ID ");
		hqlBuffer.append("left join t_hop_inc t2 on t2.inc_id=t1.inc_id ");
		hqlBuffer.append("left join t_hop_manf t3 on t2.inc_manfid=t3.id ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if(dto.getCmpFlag()!=null){
			hqlBuffer.append("and t1.flag=:cmpflag ");
			hqlParamMap.put("cmpflag", dto.getCmpFlag());
		}
		if(dto.getPageModel()==null){
			PagerModel pagerModel=new PagerModel();
			pagerModel.setPageNo(1);
			pagerModel.setPageSize(10);
			dto.setPageModel(pagerModel);
		}
		if(dto.getExeState()!=null){
			if (dto.getExeState().getOrdId()!=null){
				hqlBuffer.append("and t1.ord_id=:ord ");
				hqlParamMap.put("ord", dto.getExeState().getOrdId());
			}
		}
		if(dto.getOrder()!=null){
			if (!StringUtils.isNullOrEmpty(dto.getOrder().getOrderSerial())){
				hqlBuffer.append("and t4.ORDER_SERIAL=:serial ");
				hqlParamMap.put("serial", dto.getOrder().getOrderSerial());
			}
		}
		if((dto.getOrder()==null)&&(dto.getExeState()==null)){
			return;
		}
		dto.getPageModel().setQueryHql(hqlBuffer.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		jdbcTemplateWrapper.fillPagerModelData(dto.getPageModel(), OrderItmVo.class, "orderitm_id");
	}
	
	
	
	
	/**
	 * 
	* @Title: OrderStateDao.java
	* @Description: TODO(查询咋状态列表)
	* @return
	* @return:List<State> 
	* @author zhouxin  
	* @date 2014年5月29日 下午2:40:53
	* @version V1.0
	 */
	public List<State> getComboList(){
		return (List<State>)super.getAll(State.class, "stateSeq",true);
	}
		
	@SuppressWarnings("unchecked")
	public List<OrderItm> getOrderItms(Long orderId){
		StringBuffer hql = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		hql.append(" from ");
		hql.append(" OrderItm t ");
		hql.append(" where 1=1 ");
		hql.append(" and t.ordId = :key ");
		paramMap.put("key", orderId);	
		return (List<OrderItm>) this.findByHqlWithValuesMap(hql.toString(),paramMap,false);
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrderByNo(String no){
		StringBuffer hql = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		hql.append(" from ");
		hql.append(" Order t ");
		hql.append(" where 1=1 ");
		hql.append(" and t.orderNo = :key ");
		paramMap.put("key", no);
		List<Order> orders=(List<Order>) this.findByHqlWithValuesMap(hql.toString(),paramMap,false);
		List<Order> orders2=new ArrayList<Order>();
		Long loginVendor=WebContextHolder.getContext().getVisit().getUserInfo().getVendorIdLong();
		for(Order tmpOrder:orders){
			HopVendor hopVendor=super.get(HopVendor.class, tmpOrder.getVendorId());
			
			if((hopVendor.getHopVenId()!=null)&&(hopVendor.getHopVenId().toString().equals(loginVendor.toString()))){
				orders2.add(tmpOrder);
			}
		}
		return orders2;

	}
	
	
	/**
	 * 
	* @Title: OrderStateDao.java
	* @Description: TODO( webservice 查询医院订单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月2日 上午11:37:44
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public void listOrderWS(OrderStateDto dto) {

		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.order_no orderno, ");
		hqlBuffer.append("t1.REMARK remark, ");
		hqlBuffer.append("t1.EMFLAG emflag, ");
		hqlBuffer.append("t1.order_id orderid, ");
		hqlBuffer.append("t4.ctloc_name as recloc, ");
		hqlBuffer.append("t5.ctloc_name as purloc, ");
		hqlBuffer.append("t6.ctlocdes_destination as desction, ");
		hqlBuffer.append("t1.DELIVERYDATE as deliverdate, ");
		hqlBuffer.append("t10.HOSPITAL_NAME as hopname ");
		hqlBuffer.append("from t_ord_order t1  ");
		hqlBuffer.append("left join t_ord_exestate t2 on t1.exestate_id=t2.exestate_id  ");
		hqlBuffer.append("left join t_sys_ctloc t4 on t4.ctloc_id=t1.recloc ");
		hqlBuffer.append("left join t_sys_ctloc t5 on t5.ctloc_id=t1.purloc ");
		hqlBuffer.append("left join t_sys_ctloc_destination t6 on t6.ctlocdes_id=t1.recdestination ");
		hqlBuffer.append("left join T_HOP_VENDOR t9 on t9.H_VENID=t1.vendor_id ");
		hqlBuffer.append("left join T_SYS_HOSPITAL t10 on t10.HOSPITAL_ID=t1.HOP_ID ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
	

		hqlBuffer.append("and t2.state_id=:State ");
		hqlParamMap.put("State", 1);
		if(dto.getSendFlag().equals("1")){
			hqlBuffer.append("and t1.sendflag=:sendflag ");
			hqlParamMap.put("sendflag", dto.getSendFlag());
		}else{
			hqlBuffer.append("and t1.sendflag is null ");
		}
		
		hqlBuffer.append("and t9.H_VENDORID=:Vendor ");
		hqlParamMap.put("Vendor", dto.getVendor());

		dto.setOrderWSVos(jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), OrderWebVo.class, hqlParamMap));
	}
	

	/**
	 * 
	* @Title: OrderStateDao.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月3日 下午2:28:32
	* @version V1.0
	 */
    @SuppressWarnings("unchecked")
	public void listOrderItmWS(OrderWebVo wsVo){
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.ord_id as orderid, ");
		hqlBuffer.append("t2.INC_SPEC as spec, ");
		hqlBuffer.append("t7.NAME as manf, ");
		hqlBuffer.append("t1.orderitm_id as orderitmid, ");
		hqlBuffer.append("t1.inc_id as hopincid, ");
		hqlBuffer.append("t3.VEN_INC_NAME as venincname, ");
		hqlBuffer.append("t3.VEN_INC_CODE as veninccode, ");
		hqlBuffer.append("t1.reqqty as hisqty , ");
		hqlBuffer.append("t1.rp as hisrp, ");
		hqlBuffer.append("t6.VEN_FAC/t6.HOP_FAC as fac, ");
		hqlBuffer.append("t3.VEN_INC_UOMNAME as venuom, ");
		hqlBuffer.append("t1.rp/t6.VEN_FAC/t6.HOP_FAC as venrp, ");
		hqlBuffer.append("t1.reqqty/t6.VEN_FAC/t6.HOP_FAC as venqty, ");
		hqlBuffer.append("t2.INC_NAME as hopincname, ");
		hqlBuffer.append("t1.uom as hisuom ");
		hqlBuffer.append("from t_ord_orderitm t1 ");
		hqlBuffer.append("left join t_hop_inc t2 on t2.inc_id=t1.inc_id ");
		hqlBuffer.append("left join t_ven_hop_inc t6 on t1.inc_id=t6.hop_inc_id ");
		hqlBuffer.append("left join t_ven_inc t3 on t3.ven_inc_rowid=t6.ven_inc_id ");
		hqlBuffer.append("left join T_HOP_MANF t7 on t7.ID=t2.INC_MANFID ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();

		hqlBuffer.append("and t1.ord_id=:ordrowid ");
		hqlParamMap.put("ordrowid", wsVo.getOrderid());

		wsVo.setOrderItmWSVos(jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), OrderItmWebVo.class, hqlParamMap));
	}
    
    

}
