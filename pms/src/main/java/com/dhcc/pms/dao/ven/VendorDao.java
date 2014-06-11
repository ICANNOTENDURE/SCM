/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.ven;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.util.PingYinUtil;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.pms.dto.ven.VendorDto;
import com.dhcc.pms.entity.ven.VenQualifPic;
import com.dhcc.pms.entity.ven.VenQualification;
import com.dhcc.pms.entity.ven.Vendor;
import com.dhcc.pms.entity.vo.ven.VenQualifTypeVO;

@Repository
public class VendorDao extends HibernatePersistentObjectDAO<Vendor> {

	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	public void buildPagerModelQuery(PagerModel pagerModel, BaseDto dto) {

		VendorDto vendorDto = (VendorDto) dto;
		Vendor vendor = vendorDto.getVendor();

		pagerModel.setCountProName(super.getIdName(Vendor.class));
		StringBuilder hqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();

		buildQuery(hqlParamMap, vendor, hqlStr);
		pagerModel.setQueryHql(hqlStr.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
	}

	/**
	 * 拼接查询条件的方法
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void buildQuery(Map hqlParamMap, Vendor vendor, StringBuilder hqlStr) {
		// 拼接查询条件
		hqlStr.append(" from Vendor where 1=1 ");
		if (vendor!=null){
			if (vendor.getCode()!=null){
				 hqlStr.append(" and code like :code ");
				 hqlParamMap.put("code", "%"+vendor.getCode()+"%");
			}
			if (vendor.getName()!=null){
				 hqlStr.append(" and name like :name ");
				 hqlParamMap.put("name", "%"+vendor.getName()+"%");
			}
			if (!StringUtils.isNullOrEmpty(vendor.getAlias())){
				 hqlStr.append(" and alias like :alias ");
				 hqlParamMap.put("alias", "%"+vendor.getAlias().toLowerCase()+"%");
			}
		}
	}

	public void save(Vendor vendor) {

		super.save(vendor);
	}

	public void delete(Vendor vendor) {

		super.delete(vendor);
	}

	public void update(Vendor vendor) {

		super.update(vendor);
	}

	public Vendor findById(Vendor vendor) {

		return super.findById(vendor.getVendorId());

	}

	public void saveOrUpdate(VendorDto dto) {

		if(StringUtils.isNullOrEmpty(dto.getVendor().getAlias())){
			dto.getVendor().setAlias(PingYinUtil.getFirstSpell(dto.getVendor().getName()));
		}
		super.saveOrUpdate(dto.getVendor());
		for (int i = 0; i < dto.getVendor().getVenQualificationList().size(); i++) {
			dto.getVendor().getVenQualificationList().get(i)
					.setVendorid(dto.getVendor().getVendorId());
		}
		super.batchSaveOrUpdate(dto.getVendor().getVenQualificationList());

	}

	@SuppressWarnings("unchecked")
	public List<VenQualifTypeVO> queryQualifyType(VendorDto dto) {

		Vendor vendor = dto.getVendor();

		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select ");
		hqlBuffer.append("t1.venqualiftype_id as type,t1.name as name ,t2.EXPDATE as expdate ,t2.QUALIFICATION_ID as   qualif ");// ,t2.venQualificationId,t2.expdate,t2.vendorid)																														// ");
		hqlBuffer.append(" from ");
		hqlBuffer.append(" T_VEN_QUALIF_TYPE t1   left  join T_VEN_QUALIFICATION t2 ");
		hqlBuffer.append(" on t1.VENQUALIFTYPE_ID=t2.QUALIFY_TYPE_ID");
		//hqlBuffer.append(" where 1=1 ");
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (vendor != null) {
			if ((vendor.getVendorId() != null)) {
				hqlBuffer.append(" AND t2.VENDOR_ID=:vendorid ");
				hqlParamMap.put("vendorid", vendor.getVendorId());

			}
		}
		hqlBuffer.append(" order by t1.seq ");
		List<VenQualifTypeVO> venQualifTypeVOList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(hqlBuffer.toString(), VenQualifTypeVO.class, hqlParamMap);
		for(int i=0;i<venQualifTypeVOList.size();i++){
			if(venQualifTypeVOList.get(i).getQualif()!=null){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				StringBuffer hql = new StringBuffer();
				hql.append(" from ");
				hql.append(" VenQualifPic t ");
				hql.append(" where t.qualifyid = :qualifyid ");
				paramMap.put("qualifyid",venQualifTypeVOList.get(i).getQualif());
				List<VenQualifPic> VenQualifPics=this.findByHqlWithValuesMap(hql.toString(),paramMap,false);
				venQualifTypeVOList.get(i).setVenQualifPics(VenQualifPics);
			}
		}
		return venQualifTypeVOList;
	}
	
	public void saveOrUpdatePic(VendorDto dto) {

		if (dto.getVendor()==null){
			Vendor vendor=new Vendor();
			super.saveOrUpdate(vendor);
			dto.setVendor(vendor);
		}
		if (dto.getVenQualifTypeVO().getQualif()==null){
			
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();
			hql.append(" from ");
			hql.append(" VenQualification t ");
			hql.append(" where t.vendorid = :vendorid ");
			hql.append(" and t.venQualifTypeId = :venQualifTypeId ");
			paramMap.put("vendorid",dto.getVendor().getVendorId());
			paramMap.put("venQualifTypeId",dto.getVenQualifTypeVO().getType());
			@SuppressWarnings("unchecked")
			List<VenQualification> VenQualificationList=this.findByHqlWithValuesMap(hql.toString(),paramMap,false);
			if(VenQualificationList.size()>0){
				dto.getVenQualifTypeVO().setQualif(VenQualificationList.get(0).getVenQualificationId());
			}
		}
		
		if (dto.getVenQualifTypeVO().getQualif()==null){
			VenQualification venQualification=new VenQualification();
			venQualification.setVendorid(dto.getVendor().getVendorId());
			venQualification.setVenQualifTypeId(dto.getVenQualifTypeVO().getType());
			super.saveOrUpdate(venQualification);
			dto.getVenQualifTypeVO().setQualif(venQualification.getVenQualificationId());
		}
		VenQualifPic venQualifPic=new VenQualifPic();
		venQualifPic.setPath(dto.getStorgeFileName());
		venQualifPic.setName(dto.getUploadFileName());
		venQualifPic.setQualifyid(dto.getVenQualifTypeVO().getQualif());
		super.saveOrUpdate(venQualifPic);
	}
	
	@SuppressWarnings("unchecked")
	public List<Vendor> findVenComboList(VendorDto dto){
		StringBuffer hqlBuffer = new StringBuffer();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		hqlBuffer.append(" select new Vendor(");
		hqlBuffer.append(" t.vendorId, ");
		hqlBuffer.append(" t.name ) ");
		hqlBuffer.append(" from Vendor t ");
		hqlBuffer.append(" where 1=1 ");
		if(dto.getVendor()!=null){
			if(dto.getVendor().getName()!=null){
				hqlBuffer.append(" and t.name like :name");
				hqlParamMap.put("name", dto.getVendor().getName()+"%");
			}
		}
		if(!StringUtils.isNullOrEmpty(dto.getComgridparam())){
				hqlBuffer.append(" and t.name like :name");
				hqlParamMap.put("name", dto.getComgridparam()+"%");
			
		}
		return (List<Vendor>)super.findByHqlWithValuesMap(hqlBuffer.toString(),1,20,hqlParamMap,true);
		
	}
}
