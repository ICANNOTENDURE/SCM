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
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.pms.dto.ord.OrderStateDto;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.ord.State;
import com.dhcc.pms.entity.vo.ord.OrderExeStateVo;
import com.dhcc.pms.entity.vo.ord.OrderItmVo;
import com.dhcc.pms.entity.vo.ord.OrderStateVo;

@Repository
public class OrderStateDao extends HibernatePersistentObjectDAO<Order> {

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
		hqlBuffer.append("t8.realname as realname, ");
		hqlBuffer.append("t1.deliverydate as deliverydate, ");
		hqlBuffer.append("t2.exedate as exedate, ");
		hqlBuffer.append("t9.hopName as vendor ");
		hqlBuffer.append("from t_ord_order t1  ");
		hqlBuffer.append("left join t_ord_exestate t2 on t1.exestate_id=t2.exestate_id  ");
		hqlBuffer.append("left join t_ord_state t3 on t2.state_id=t3.state_seq ");
		hqlBuffer.append("left join t_sys_ctloc t4 on t4.ctloc_id=t1.recloc ");
		hqlBuffer.append("left join t_sys_ctloc t5 on t5.ctloc_id=t1.purloc ");
		hqlBuffer.append("left join t_sys_ctloc_destination t6 on t6.ctlocdes_id=t1.recdestination ");
		hqlBuffer.append("left join t_sys_normal_account t7 on  t7.account_id=t1.createuser ");
		hqlBuffer.append("left join t_sys_normal_user t8 on  t8.user_id=t7.user_id ");
		hqlBuffer.append("left join T_HOP_VENDOR t9 on t9.H_VENID=t1.vendor_id ");
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
		hqlBuffer.append("t3.name as exeuser ");
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
		}
		
		
		hqlBuffer.append("order by t1.exestate_id desc ");
		

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
		hqlBuffer.append("t1.uom as uom, ");
		hqlBuffer.append("t3.name as manf ");
		hqlBuffer.append("from t_ord_orderitm t1 ");
		hqlBuffer.append("left join t_hop_inc t2 on t2.inc_id=t1.inc_id ");
		hqlBuffer.append("left join t_hop_manf t3 on t2.inc_manfid=t3.id ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
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
		else{
			dto.getPageModel().setTotals(0);
			dto.getPageModel().setPageData(new ArrayList<OrderItmVo>());
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
}
