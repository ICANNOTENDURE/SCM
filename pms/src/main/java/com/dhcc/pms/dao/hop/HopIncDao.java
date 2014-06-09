/**
 *  
 * template by zxx
 */
package com.dhcc.pms.dao.hop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import  com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.hibernate.dao.CommonDao;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.pms.entity.hop.HopInc;
import com.dhcc.pms.entity.vo.hop.HopIncVo;
import com.dhcc.pms.entity.vo.hop.ShowHopIncVo;
import com.dhcc.pms.dto.hop.HopIncDto;

@Repository
public class HopIncDao extends HibernatePersistentObjectDAO<HopInc> {
	
	@Resource
	private CommonDao commonDao;
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	public void buildPagerModelQuery(PagerModel pagerModel,BaseDto dto) {
	
		HopIncDto hopIncDto = (HopIncDto) dto;
		HopInc hopInc = hopIncDto.getHopInc();

		pagerModel.setCountProName(super.getIdName(HopInc.class));
		StringBuilder hqlStr = new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		
		buildQuery(hqlParamMap, hopInc, hqlStr);
		pagerModel.setQueryHql(hqlStr.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
	}

	/** 
	 * 拼接查询条件的方法  
	 * @param hql
	 * @return
	 */
	private void buildQuery(Map<String, Object> hqlParamMap,HopInc hopInc,StringBuilder hqlStr){
		//拼接查询条件
		hqlStr.append(" from HopInc h ");
		//拼接查询条件		
		if (hopInc!=null) {
			hqlStr.append(" where 1=1 ");
			String codeStr =hopInc.getIncCode();
			String nameStr =hopInc.getIncName();	
			BigDecimal manfDr=hopInc.getIncManfid();
			Long hospDr=hopInc.getIncHospid();
			Long hissysDr=hopInc.getIncHissysid();
			if(!StringUtils.isNullOrEmpty(codeStr)){
				hqlStr.append(" AND h.incCode like:codeStr ");
				hqlParamMap.put("codeStr","%"+codeStr+"%");
			}
			if(!StringUtils.isNullOrEmpty(nameStr)){
				hqlStr.append(" AND h.incName like:nameStr ");
				hqlParamMap.put("nameStr","%"+nameStr+"%");
			}
			if(manfDr!=null){
				hqlStr.append(" AND h.incManfid =:manfDr ");
				hqlParamMap.put("manfDr",manfDr);
			}
			if(hospDr!=null){
				hqlStr.append(" AND h.incHospid =:hospDr ");
				hqlParamMap.put("hospDr",hospDr);
			}
			if(hissysDr!=null){
				hqlStr.append(" AND h.incHissysid =:hissysDr ");
				hqlParamMap.put("hissysDr",hissysDr);
			}
			
		}
	}
		
	public void save(HopInc hopInc){
	
		super.save(hopInc);
	}
	
	public void delete(HopInc hopInc){
		
		super.delete(hopInc);
	}
	
	public void update(HopInc hopInc){
	
		super.update(hopInc);
	}
	
	public HopInc findById(HopInc hopInc){

		return super.findById(hopInc.getIncId());

	}

	/**
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HopIncVo> getIncInfo(List<HopIncVo> list) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select new com.dhcc.pms.entity.vo.hop.HopIncVo(");
		hqlBuffer.append(" h.incId, ");
		hqlBuffer.append(" h.incCode, ");
		hqlBuffer.append(" h.incName, ");		
		hqlBuffer.append(" h.incUomname, ");		
		hqlBuffer.append(" h.incBuomname, ");		
		hqlBuffer.append(" hs.hospitalName, ");		
		hqlBuffer.append(" hm.manfName, ");
		hqlBuffer.append(" h.incHissysid) ");
		hqlBuffer.append(" from HopInc h , Hospital hs ,HopManf hm ");		
		hqlBuffer.append(" where h.incHospid=hs.hospitalId and h.incManfid=hm.hopManfId");
		
		return (List<HopIncVo>)this.findByHql(hqlBuffer.toString());
	}

	/**
	 * @author pengzhikun
	 * @param hopIncVos
	 * @param hopInc
	 * @return 新组成的药品图片显示信息，其中包含父表HopInc的所有信息，以及子表HopIncPic与父表关联的信息
	 */
	public void getListInfo(HopIncDto dto){
				
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select ");
		hqlBuffer.append("t1.INC_CODE as inccode, ");
		hqlBuffer.append("t1.INC_NAME as incname, ");
		hqlBuffer.append("t1.INC_UOMCODE as incuomcode, ");
		hqlBuffer.append("t1.INC_UOMNAME as incuomname, ");
		hqlBuffer.append("t1.INC_BUOMCODE as incbuomcode, ");
		hqlBuffer.append("t1.INC_BUOMNAME as incbuomname, ");
		hqlBuffer.append("t1.INC_FAC as incfac, ");
		hqlBuffer.append("t1.INC_MANFID as incmanfid, ");
		hqlBuffer.append("t1.INC_ID as incid, ");
		hqlBuffer.append("t1.INC_HISSYSID as inchissysid, ");
		hqlBuffer.append("t1.INC_HOSPID as inchospid, ");
		hqlBuffer.append("t1.INC_RP as incrp, ");
		hqlBuffer.append("t2.HOSPITAL_NAME as hospitalname, ");
		hqlBuffer.append("t3.NAME as manfname, ");
		hqlBuffer.append("t4.INC_PIC_ID as hopincpicid, ");
		hqlBuffer.append("t4.INC_PIC_PATH as incpicpath, ");
		hqlBuffer.append("t4.INC_PIC_SEQ as incpicseq ");
		hqlBuffer.append("from ");
		hqlBuffer.append("t_hop_inc t1 left join t_sys_hospital t2 on t1.inc_hospid=t2.hospital_id ");
		hqlBuffer.append("left join t_hop_manf t3 on t1.inc_manfid=t3.id ");
		hqlBuffer.append("left join t_hop_inc_pic t4 on t4.inc_pic_incid=t1.inc_id ");
		hqlBuffer.append(" where 1=1");
		/*
		hqlBuffer.append(" select new com.dhcc.pms.entity.vo.hop.HopIncVo(");
		hqlBuffer.append(" h.incId, ");
		hqlBuffer.append(" h.incCode, ");
		hqlBuffer.append(" h.incName, ");
		hqlBuffer.append(" h.incUomcode, ");
		hqlBuffer.append(" h.incUomname, ");
		hqlBuffer.append(" h.incBuomcode, ");
		hqlBuffer.append(" h.incBuomname, ");
		hqlBuffer.append(" h.incFac, ");
		hqlBuffer.append(" h.incRp, ");
		hqlBuffer.append(" h.incHospid, ");
		hqlBuffer.append(" hs.hospitalName, ");
		hqlBuffer.append(" h.incManfid, ");
		hqlBuffer.append(" hm.manfName, ");
		hqlBuffer.append(" h.incHissysid, ");
		hqlBuffer.append(" h.) ");
		hqlBuffer.append(" from HopInc h , Hospital hs ,HopManf hm ");		
		hqlBuffer.append(" where h.incHospid=hs.hospitalId and h.incManfid=hm.hopManfId");
		*/
		HopInc hopInc=dto.getHopInc();
		if (hopInc!=null) {			
			String codeStr =hopInc.getIncCode();
			String nameStr =hopInc.getIncName();	
			BigDecimal manfDr=hopInc.getIncManfid();
			Long hospDr=hopInc.getIncHospid();
			Long hissysDr=hopInc.getIncHissysid();
			if(!StringUtils.isNullOrEmpty(codeStr)){
				hqlBuffer.append(" AND t1.INC_CODE like:codeStr ");
				hqlParamMap.put("codeStr","%"+codeStr+"%");
			}
			if(!StringUtils.isNullOrEmpty(nameStr)){
				hqlBuffer.append(" AND t1.INC_NAME like:nameStr ");
				hqlParamMap.put("nameStr","%"+nameStr+"%");
			}
			if(manfDr!=null){
				hqlBuffer.append(" AND t1.INC_MANFID =:manfDr ");
				hqlParamMap.put("manfDr",manfDr);
			}
			if(hospDr!=null){
				hqlBuffer.append(" AND t1.INC_HOSPID =:hospDr ");
				hqlParamMap.put("hospDr",hospDr);
			}
			if(hissysDr!=null){
				hqlBuffer.append(" AND t1.INC_HISSYSID =:hissysDr ");
				hqlParamMap.put("hissysDr",hissysDr);
			}
			
		}
		if(!StringUtils.isNullOrEmpty(dto.getComgridparam())){
			hqlBuffer.append(" AND t1.INC_NAME like:nameStr ");
			hqlParamMap.put("nameStr","%"+dto.getComgridparam()+"%");
		}
		dto.getPageModel().setQueryHql(hqlBuffer.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		jdbcTemplateWrapper.fillPagerModelData(dto.getPageModel(), ShowHopIncVo.class, "INC_ID");
		
		//获取总页数
		/*
		pagerModel.setCountProName("INC_ID");
		pagerModel.setQueryHql(hqlBuffer.toString());
		int totalRows = pagerModel.getTotals();
		if (totalRows == 0) {
			totalRows = commonDao.getResultCountWithValuesMap(
					pagerModel.getQueryHql(), pagerModel.getHqlParamMap(),
					pagerModel.getCountProName(), false).intValue();
		}
		if (totalRows == 0) {
			pagerModel.setPageData(new ArrayList<Object>(1));
			return null;
		}
		pagerModel.setTotals(totalRows);
		*/
		//药品列表信息
		//List<HopIncVo> hopIncList=(List<HopIncVo>)findByHqlWithValuesMap(hqlBuffer.toString(),pagerModel.getPageNo(),pagerModel.getPageSize(),hqlParamMap,true);
		/*		
		StringBuffer hqlBufferPic = new StringBuffer();		
		hqlBufferPic.append(" select new com.dhcc.pms.entity.vo.hop.HopIncPicVo(");
		hqlBufferPic.append(" hp.hopIncPicId, ");
		hqlBufferPic.append(" hp.incPicIncid, ");
		hqlBufferPic.append(" h.incName, ");
		hqlBufferPic.append(" hp.incPicPath, ");
		hqlBufferPic.append(" hp.incPicSeq) ");
		hqlBufferPic.append(" from HopIncPic hp , HopInc  h ");
		hqlBufferPic.append(" where hp.incPicIncid=h.incId ");
		
		//药品图片列表信息
		List<HopIncPicVo> hopIncPicList=(List<HopIncPicVo>)findByHql(hqlBufferPic.toString());
		
		//return (List<HopIncVo>)findByHqlWithValuesMap(hqlBuffer.toString(), hqlParamMap, true);
		
		for(int i=0;i<hopIncList.size();i++){
			HopIncPicVo e=new HopIncPicVo();
			List<HopIncPicVo> list=hopIncList.get(i).getHopIncPicVos();
			
			//flag为[],只是用来作List比较的flag。
			List<Object> flag=new ArrayList<Object>();
			
			for(int j=0;j<hopIncPicList.size();j++){
				if(hopIncPicList.get(j).getIncPicIncid().equals(BigDecimal.valueOf(hopIncList.get(i).getIncId()))){					
					try{
						e=hopIncPicList.get(j);
						list.add(e);
					}catch(Exception ex){
						ex.printStackTrace();
					}
					
				}
			}
			//判判断list中有值，则将其赋值给HopIncVo的List<HopIncPic>，而当list为[]，即无子表数据
			if(!list.equals(flag)){
				hopIncList.get(i).setHopIncPicVos(list);
			}
			
		}
		*/
		//return hopIncList;
				
		//return showHopIncVos;
		
	} 
}
