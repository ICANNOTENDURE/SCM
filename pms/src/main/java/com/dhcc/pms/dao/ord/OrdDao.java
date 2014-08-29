/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.ord;

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
import com.dhcc.pms.dto.ord.OrdDto;
import com.dhcc.pms.entity.ord.Ord;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.ord.OrderItm;
import com.dhcc.pms.entity.ven.VenDel;
import com.dhcc.pms.entity.ven.VenDeliver;
import com.dhcc.pms.entity.ven.VenDeliveritm;
import com.dhcc.pms.entity.vo.ord.OrdVo;
import com.dhcc.pms.entity.vo.ven.DelvVo;



/**
 * 
 * @author Administrator
 *
 */
@Repository
public class OrdDao extends HibernatePersistentObjectDAO<Ord> {
	
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	/* (non-Javadoc)
	 * @see com.dhcc.framework.hibernate.dao.PersistentObjectDAO#buildPagerModelQuery(com.dhcc.framework.common.PagerModel, com.dhcc.framework.transmission.dto.BaseDto)
	 */
	@Override
	public void buildPagerModelQuery(PagerModel pagerModel,BaseDto dto) {
		
		OrdDto ordDto = (OrdDto) dto;

		pagerModel.setCountProName(super.getIdName(Ord.class));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		
		buildQuery(hqlParamMap, ordDto, hqlStr);
		pagerModel.setQueryHql(hqlStr.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
	}

	/** 
	 * 拼接查询条件的方法  
	 * @param hql
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void buildQuery(Map hqlParamMap,OrdDto ordDto,StringBuilder hqlStr){
		//拼接查询条件
		hqlStr.append(" from Ord where 1=1 ");
		//接下来拼接其他查询条件 如下示例代码所示
		hqlStr.append("and ordHopId=:year ");
		hqlParamMap.put("year", WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
		hqlStr.append("AND ordLocId=:month ");
		hqlParamMap.put("month", WebContextHolder.getContext().getVisit().getUserInfo().getLocId());
		if(ordDto!=null){
			if(ordDto.getStDate()!=null){
				hqlStr.append("AND ordDate>=:st ");
				hqlParamMap.put("st", ordDto.getStDate());
			}
			if(ordDto.getEdDate()!=null){
				hqlStr.append("AND ordDate<=:ed ");
				hqlParamMap.put("ed", ordDto.getEdDate());
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void listOrd(OrdDto dto){
		
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.order_id as orderid,  ");
		hqlBuffer.append("t2.orderitm_id as orderitmid,  ");
		hqlBuffer.append("t3.inc_code as inccode,  ");
		hqlBuffer.append("t3.inc_name as incname,  ");
		hqlBuffer.append("t2.reqqty as qty,  ");
		hqlBuffer.append("t2.uom as uom,  ");
		hqlBuffer.append("t4.name as manf,  ");
		hqlBuffer.append("t2.rp as rp,  ");
		hqlBuffer.append("t3.inc_spec as spec,  ");
		hqlBuffer.append("t5.h_name as vendor,  ");
		hqlBuffer.append("t6.state_id as state  ");
		
		hqlBuffer.append(" from t_ord_order t1 ");
		hqlBuffer.append("left join t_ord_orderitm t2 on t1.order_id=t2.ord_id ");
		hqlBuffer.append("left join t_hop_inc t3 on t3.inc_id=t2.inc_id	");
		hqlBuffer.append("left join t_hop_manf t4 on t3.inc_manfid=t4.id ");
		hqlBuffer.append("left join t_hop_vendor t5 on t5.h_venid=t1.vendor_id ");
		hqlBuffer.append("left join t_ord_exestate t6 on t6.exestate_id=t1.exestate_id ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        
		hqlBuffer.append("and t1.order_serial=:ss ");
		hqlParamMap.put("ss", dto.getOrdSerial());
		
		
		if(!StringUtils.isNullOrEmpty(dto.getSort())){

			hqlBuffer.append(" order by "+dto.getSort()+"	"+dto.getSortOrder());
			
		}

		dto.setOrdVos(jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), OrdVo.class, hqlParamMap));
	}
	
	
	/**
	 * 
	* @Title: OrdDao.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年8月28日 下午3:36:48
	* @version V1.0
	 */
	public void deleteItm(OrdDto dto){
		
		OrderItm orderItm=super.get(OrderItm.class, dto.getOrderItmId());
		super.delete(orderItm);
		List<OrderItm> orderItms=super.findByProperty(OrderItm.class, "ordId", orderItm.getOrdId());
		if(orderItms.size()==0){
			super.deleteById(Order.class, orderItm.getOrdId());
		}
	}
	
	
	
	
	/**
	 * 
	* @Title: OrdDao.java
	* @Description: TODO(查询发货单上传流水)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年8月29日 下午3:27:04
	* @version V1.0
	 */
	public void listDelv(OrdDto dto){
		dto.getPageModel().setCountProName(super.getIdName(VenDel.class));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		
		hqlStr.append(" from VenDel where 1=1 ");
		//接下来拼接其他查询条件 如下示例代码所示
		hqlStr.append("and delvVendorid=:year ");
		hqlParamMap.put("year", WebContextHolder.getContext().getVisit().getUserInfo().getVendorIdLong());
		if(dto!=null){
			if(dto.getStDate()!=null){
				hqlStr.append("AND delvDate>=:st ");
				hqlParamMap.put("st", dto.getStDate());
			}
			if(dto.getEdDate()!=null){
				hqlStr.append("AND delvDate<=:ed ");
				hqlParamMap.put("ed", dto.getEdDate());
			}
		}
		dto.getPageModel().setQueryHql(hqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
	}
	
	
	
	/**
	 * 
	* @Title: OrdDao.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年8月29日 下午4:16:04
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public void listDeliverItm(OrdDto dto){
		
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t2.deliveritm_id as deliveritmid,  ");
		hqlBuffer.append("t3.ven_inc_code as venincncode,  ");
		hqlBuffer.append("t3.ven_inc_name as venincname,  ");
		hqlBuffer.append("t2.deliveritm_qty as deliverqty,  ");
		hqlBuffer.append("t3.ven_inc_uomname as uom,  ");
		hqlBuffer.append("t4.name as manf,  ");
		hqlBuffer.append("t2.deliveritm_rp as rp,  ");
		hqlBuffer.append("t3.ven_inc_spec as spec,  ");
		hqlBuffer.append("t6.hospital_name as hopname,  ");
		hqlBuffer.append("t2.deliveritm_batno as batno,  ");
		hqlBuffer.append("t2.deliveritm_expdate as expdate,  ");
		hqlBuffer.append("t2.deliveritm_invnoe as invno,  ");
		hqlBuffer.append("t2.deliveritm_recflag as state  ");
		
		hqlBuffer.append("  from t_ven_deliver t1 ");
		hqlBuffer.append("left join t_ven_deliveritm t2 on t2.deliveritm_parentid=t1.deliver_rowid ");
		hqlBuffer.append("left join t_ven_inc t3 on t3.ven_inc_rowid=t2.deliveritm_venincid ");
		hqlBuffer.append("left join t_hop_manf t4 on t4.id=t3.ven_inc_manfid ");
		hqlBuffer.append("left join t_sys_hospital t6 on t1.deliver_hopid=t6.hospital_id ");
		hqlBuffer.append("left join t_ord_orderitm t7 on t7.orderitm_id=t2.deliveritm_orderitmid ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        
		hqlBuffer.append("and t1.deliver_no=:ss ");
		hqlParamMap.put("ss", dto.getOrdSerial());
		
		
		if(!StringUtils.isNullOrEmpty(dto.getSort())){

			hqlBuffer.append(" order by "+dto.getSort()+"	"+dto.getSortOrder());
			
		}

		dto.setDelvVos(jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), DelvVo.class, hqlParamMap));
	}
	
	
	/**
	 * 
	* @Title: OrdDao.java
	* @Description: TODO(删除发货单)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年8月29日 下午4:59:35
	* @version V1.0
	 */
	public void deleteDelvItm(OrdDto dto){
		
		VenDeliveritm venDeliveritm=super.get(VenDeliveritm.class, dto.getDeliverItmid());
		super.delete(venDeliveritm);
		List<VenDeliveritm> deliveritms=super.findByProperty(VenDeliveritm.class, "deliveritmParentid", venDeliveritm.getDeliveritmParentid());
		if(deliveritms.size()==0){
			super.deleteById(VenDeliver.class, venDeliveritm.getDeliveritmParentid());
		}
	}
}
