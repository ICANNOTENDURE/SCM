/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.hop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.util.PingYinUtil;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.hop.HopVendorDto;
import com.dhcc.pms.entity.hop.HopVendor;
import com.dhcc.pms.entity.ven.Vendor;
import com.dhcc.pms.entity.vo.combo.ComboxVo;
import com.dhcc.pms.entity.vo.hop.HopVendorDetailVo;
import com.dhcc.pms.entity.vo.hop.HopVendorVo;

@Repository
public class HopVendorDao extends HibernatePersistentObjectDAO<HopVendor> {
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	public void buildPagerModelQuery(PagerModel pagerModel,BaseDto dto) {
	
		HopVendorDto hopVendorDto = (HopVendorDto) dto;
		HopVendor hopVendor = hopVendorDto.getHopVendor();

		pagerModel.setCountProName(super.getIdName(HopVendor.class));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		
		buildQuery(hqlParamMap, hopVendor, hqlStr);
		pagerModel.setQueryHql(hqlStr.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
	}

	/** 
	 * 拼接查询条件的方法  
	 * @param hql
	 * @return
	 */
	private void buildQuery(Map<String, Object> hqlParamMap,HopVendor hopVendor,StringBuilder hqlStr){
		//拼接查询条件
		hqlStr.append(" from HopVendor where 1=1 ");
		
		if(hopVendor!=null){
			if(!StringUtils.isNullOrEmpty(hopVendor.getHopType())){
				hqlStr.append("and hopType=:type ");
				hqlParamMap.put("type", hopVendor.getHopType());
			}
			if(!StringUtils.isNullOrEmpty(hopVendor.getHopName())){
				hqlStr.append("and hopName like :name ");
				hqlParamMap.put("name", "%"+hopVendor.getHopName()+"%");
			}
			if(!StringUtils.isNullOrEmpty(hopVendor.getHopCode())){
				hqlStr.append("and hopCode like :code ");
				hqlParamMap.put("code", "%"+hopVendor.getHopCode()+"%");
			}
		}
		
		//接下来拼接其他查询条件 如下示例代码所示
		//hqlStr.append("WHERE YEAR=:year ");
		//hqlParamMap.put("year", year);
		//hqlStr.append("AND MONTH=:month ");
		//hqlParamMap.put("month", month);
		//hqlStr.append("AND DAY=:day ");
		//hqlParamMap.put("day", day);
	}
		
	public void save(HopVendor hopVendor){
		hopVendor.setHopHopId(WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
		super.saveOrUpdate(hopVendor);
	}
	
	public void delete(HopVendor hopVendor){
		
		super.delete(hopVendor);
	}
	
	public void update(HopVendor hopVendor){
	
		super.update(hopVendor);
	}
	
	public HopVendor findById(HopVendor hopVendor){

		return super.findById(hopVendor.getHopVendorId());

	}
	
	/**
	 * 
	* @Title: HopVendorDao.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 上午9:59:48
	* @version V1.0
	 */
	public void listHopCon(HopVendorDto dto){
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.H_VENID as hopvenid, ");
		hqlBuffer.append("t1.H_NAME as hopvenname, ");
		hqlBuffer.append("t3.hospital_name as hopname, ");
		hqlBuffer.append("t1.H_code as hopvencode, ");
		hqlBuffer.append("t2.name as venname, ");
		hqlBuffer.append("t1.h_vendorid as venid ");
		
		hqlBuffer.append("from t_hop_vendor t1 ");
		hqlBuffer.append("left join t_ven_vendor t2 on t1.h_vendorid=t2.ven_id ");
		hqlBuffer.append("left join t_sys_hospital t3 on t3.hospital_id=t1.h_hopid ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();

		if(dto.getHopVendor()!=null){
			if (dto.getHopVendor().getHopHopId()!=null){
				hqlBuffer.append("and t3.hospital_id=:hopid ");
				hqlParamMap.put("hopid", dto.getHopVendor().getHopHopId());
			}
			if (!StringUtils.isNullOrEmpty(dto.getHopVendor().getHopAlias())){
				hqlBuffer.append("and t1.h_alias like :hvalias ");
				hqlParamMap.put("hvalias", "%"+dto.getHopVendor().getHopAlias()+"%");
			}
			if (!StringUtils.isNullOrEmpty(dto.getHopVendor().getHopCode())){
				hqlBuffer.append("and t1.h_code like :hvcode ");
				hqlParamMap.put("hvcode", "%"+dto.getHopVendor().getHopCode()+"%");
			}
			if (!StringUtils.isNullOrEmpty(dto.getHopVendor().getHopName())){
				hqlBuffer.append("and t1.h_name like :hvname ");
				hqlParamMap.put("hvname", "%"+dto.getHopVendor().getHopName()+"%");
			}
			if (dto.getHopVendor().getHopVenId()!=null){
				hqlBuffer.append("and t1.H_VENDORID =:hvendorid ");
				hqlParamMap.put("hvendorid", dto.getHopVendor().getHopVenId());
			}
		
		}
		if(dto.getFlag()!=null){
			if (dto.getFlag().equals("2")){
				hqlBuffer.append("and t1.h_vendorid is null ");
			}
			if (dto.getFlag().equals("1")){
				hqlBuffer.append("and t1.h_vendorid is not null ");
			}
		}
		dto.getPageModel().setQueryHql(hqlBuffer.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		jdbcTemplateWrapper.fillPagerModelData(dto.getPageModel(), HopVendorVo.class, "h_venid");
	}
	
	/**
	 * 
	* @Title: HopVendorDao.java
	* @Description: TODO(导入保存供应商)
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月12日 上午11:55:22
	* @version V1.0
	 */
	public void exportVendor(HopVendorDto dto){
		
		if(dto.getExportFlag().equals("1")){
			if(dto.getHopVendors().size()>0){
				for(int i=0;i<dto.getHopVendors().size();i++){
					HopVendor hopVendor=dto.getHopVendors().get(i);
					if(StringUtils.isNullOrEmpty(hopVendor.getHopAlias())){
						hopVendor.setHopAlias(PingYinUtil.getFirstSpell(hopVendor.getHopName()));
					}
					Vendor vendor=new Vendor();
					vendor.setAccount(hopVendor.getHopAccount());
					vendor.setAddress(hopVendor.getHopAddress());
					vendor.setAlias(hopVendor.getHopAlias());
					vendor.setCode(hopVendor.getHopCode());
					vendor.setContact(hopVendor.getHopContact());
					vendor.setFax(hopVendor.getHopFax());
					vendor.setName(hopVendor.getHopName());
					vendor.setTel(hopVendor.getHopTel());
					Long vendorId=(Long)super.saveEntity(vendor);
					hopVendor.setHopVenId(vendorId);
					super.saveEntity(hopVendor);
				}
			}
		}else{
			super.batchSaveOrUpdate(dto.getHopVendors());
		}

	}
	
	
	/**
	 * 
	* @Title: VendorDao.java
	* @Description: TODO(供应商名称找ID)
	* @param name
	* @return
	* @return:Long 
	* @author zhouxin  
	* @date 2014年6月12日 下午2:05:08
	* @version V1.0
	 */
	public Long findVendorIdByName(String name){
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from ");
		hql.append(" HopVendor t ");
		hql.append(" where 1=1 ");
		hql.append(" and t.hopName = :hopName ");
		paramMap.put("hopName", name);
		Long hopId=WebContextHolder.getContext().getVisit().getUserInfo().getHopId();
		hql.append(" and t.hopHopId = :hopId ");
		paramMap.put("hopId",hopId);
		@SuppressWarnings("unchecked")
		List<HopVendor> hopVendors=(List<HopVendor>) this.findByHqlWithValuesMap(hql.toString(),paramMap,false);
		if(hopVendors.size()==1){
			return hopVendors.get(0).getHopVendorId();
		}
		return null;
	}
	
	
	/**
	 * 
	* @Title: HopVendorDao.java
	* @Description: TODO(更具权限和条件查询供应商列表combox)
	* @param input
	* @return
	* @return:List<ComboxVo> 
	* @author zhouxin  
	* @date 2014年6月13日 上午10:39:45
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public List<ComboxVo> findHopVenComboxVos(String input){
		
		Long type=WebContextHolder.getContext().getVisit().getUserInfo().getUserType();
		Long userId=Long.valueOf(WebContextHolder.getContext().getVisit().getUserInfo().getId());
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		//1是医院，2是供应商，0是平台维护人员
		if(type==1){
			StringBuffer hqlBuffer = new StringBuffer();
			hqlBuffer.append("select ");
			hqlBuffer.append("t3.H_NAME as name, ");
			hqlBuffer.append("t3.h_venid as id ");			
			hqlBuffer.append("from t_hop_vendor t3 ");
			hqlBuffer.append("where t3.h_venid in ");
			hqlBuffer.append("(select t2.sys_ven_id from t_sys_normalaccount_role t1,t_sys_ven_role t2 where t2.sys_role_id=t1.role_id and t1.account_id=:userId) ");
			hqlParamMap.put("userId", userId);
			
			if(!StringUtils.isNullOrEmpty(input)){
				hqlBuffer.append("and t3.h_alias like :alias ");
				hqlParamMap.put("alias", input+"%");
			}
			if(!StringUtils.isNullOrEmpty(WebContextHolder.getContext().getRequest().getParameter("q"))){
				hqlBuffer.append("and t3.h_alias like :xxx ");
				hqlParamMap.put("xxx", WebContextHolder.getContext().getRequest().getParameter("q")+"%");
			}
			if(!StringUtils.isNullOrEmpty(WebContextHolder.getContext().getRequest().getParameter("venid"))){
				hqlBuffer.append("and t3.H_VENDORID =:yyy ");
				hqlParamMap.put("yyy", WebContextHolder.getContext().getRequest().getParameter("venid"));
			}
			return (List<ComboxVo>)jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), ComboxVo.class, hqlParamMap, 1,BaseConstants.COMBOX_PAGE_SIZE, "sys_ven_id");
		}
		return null;
	}
	
	
	/**
	 * 
	* @Title: HopVendorDao.java
	* @Description: TODO(查找医院供应商)
	* @param code
	* @param hopId
	* @return
	* @return:HopVendor 
	* @author zhouxin  
	* @date 2014年8月5日 下午7:05:43
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public HopVendor findVendorByCode(String code,Long hopId){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(HopVendor.class);
		criteria.add(Restrictions.eq("hopCode", code));
		criteria.add(Restrictions.eq("hopHopId", hopId));
		
		List<HopVendor> hopVendors=super.findByCriteria(criteria);
		if(hopVendors.size()==0){
			return null;
		}
		return hopVendors.get(0);
	}
	
	
	/**
	 * 
	* @Title: HopVendorDao.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param dto
	* @return:void 
	* @author zhouxin  
	* @date 2014年8月12日 下午2:24:28
	* @version V1.0
	 */
	public void listVenDetail(HopVendorDto dto){
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.H_VENID as hopvenid, ");
		hqlBuffer.append("t1.H_NAME as name, ");
		hqlBuffer.append("t1.H_code as code, ");
		hqlBuffer.append("t2.address as address, ");
		hqlBuffer.append("t5.name as type, ");
		hqlBuffer.append("t4.expdate as expdate, ");
		hqlBuffer.append("t6.path as path, ");
		hqlBuffer.append("t1.h_vendorid as vendorid ");
		
		hqlBuffer.append("from t_hop_vendor t1 ");
		hqlBuffer.append("left join t_ven_vendor t2 on t1.h_vendorid=t2.ven_id ");
		hqlBuffer.append("left join t_ven_qualification t4 on t4.vendor_id=t1.h_vendorid ");
		hqlBuffer.append("left join t_ven_qualif_type t5 on t5.VENQUALIFTYPE_ID=t4.QUALIFY_TYPE_ID ");
		hqlBuffer.append("left join t_ven_qualif_pic t6 on t6.qualify_id=t4.qualification_id ");
		hqlBuffer.append("where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();

		if(dto.getHopVendor()!=null){
			if (!StringUtils.isNullOrEmpty(dto.getHopVendor().getHopAlias())){
				hqlBuffer.append("and t1.h_alias like :hvalias ");
				hqlParamMap.put("hvalias", "%"+dto.getHopVendor().getHopAlias()+"%");
			}
			if (!StringUtils.isNullOrEmpty(dto.getHopVendor().getHopCode())){
				hqlBuffer.append("and t1.h_code like :hvcode ");
				hqlParamMap.put("hvcode", "%"+dto.getHopVendor().getHopCode()+"%");
			}
			if (!StringUtils.isNullOrEmpty(dto.getHopVendor().getHopName())){
				hqlBuffer.append("and t1.h_name like :hvname ");
				hqlParamMap.put("hvname", "%"+dto.getHopVendor().getHopName()+"%");
			}
		}
		Long type=WebContextHolder.getContext().getVisit().getUserInfo().getUserType();
		Long hopId=WebContextHolder.getContext().getVisit().getUserInfo().getHopId();
		Long vendorId=WebContextHolder.getContext().getVisit().getUserInfo().getVendorIdLong();
		if(type.toString().equals("1")){
			hqlBuffer.append("and t1.h_hopid = :hopid ");
			hqlParamMap.put("hopid", hopId);
		}
		if(type.toString().equals("2")){
			hqlBuffer.append("and t1.h_venid = :venid ");
			hqlParamMap.put("venid", vendorId);
		}
		if(!StringUtils.isNullOrEmpty(dto.getSort())){
			if(dto.getSort().equals("name")){
				hqlBuffer.append(" order by  t1.H_VENID,t1.h_code "+dto.getSortOrder());
			}
			if(dto.getSort().equals("code")){
				hqlBuffer.append(" order by  t1.H_VENID,t1.h_name "+dto.getSortOrder());
			}
			
		}else{
			hqlBuffer.append(" order by  t1.H_VENID desc ");
		}
		dto.getPageModel().setQueryHql(hqlBuffer.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		jdbcTemplateWrapper.fillPagerModelData(dto.getPageModel(), HopVendorDetailVo.class, "h_venid");
	}
	
}
