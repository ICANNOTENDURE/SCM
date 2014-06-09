/**
 * 通过模板生成Blh 
 * template by liuyg
 */
package com.dhcc.pms.blh.hop;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.web.context.WebContext;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.hop.HopCtlocDto;
import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.vo.hop.HopCtlocVo;
import com.dhcc.pms.service.hop.HopCtlocService;


@Component
public class HopCtlocBlh extends AbstractBaseBlh {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(HopCtlocBlh.class);

	@Resource
	private HopCtlocService hopCtlocService;

	public HopCtlocBlh() {
		
	}
	
	/**
	 * 进入某个列表的入口方法
	 * 列表方法，也就是查询方法，调用的时候不需要xxxCtrl!list
	 * 框架 在不调Ctrl时，不指定方法，就默认为它list，在action中通过
	 * json注解，所dto中的pageModel to json
	 * @param: res
	 *  
	 */
	public void list(BusinessRequest res) {
	
		HopCtlocDto dto = super.getDto(HopCtlocDto.class, res);

		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}	
		//调用对应的service方法
		hopCtlocService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		HopCtlocDto dto = super.getDto(HopCtlocDto.class, res);
		if(dto.getHopCtloc().getHopCtlocId()==null||
				(dto.getHopCtloc().getHopCtlocId()).equals("")){
			dto.getHopCtloc().setHopCtlocId(null);
			//dto.getHopCtloc().setHospid(BigDecimal.valueOf(dto.getHospitalDr()));
			hopCtlocService.save(dto);		
		}else {
			hopCtlocService.update(dto);
		}	
			
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		HopCtlocDto dto = super.getDto(HopCtlocDto.class, res);
		
		//调用对应的service方法
		hopCtlocService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		HopCtlocDto dto = super.getDto(HopCtlocDto.class, res);
		
		//调用对应的service方法
		hopCtlocService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		HopCtlocDto dto = super.getDto(HopCtlocDto.class, res);
		
		//调用对应的service方法
		hopCtlocService.findById(dto);
		
	}
	
	//显示科室的部分信息，以json传给浏览器
	public void getCtlocList(BusinessRequest res) throws Exception {
		
		HopCtlocDto dto = super.getDto(HopCtlocDto.class, res);
		
		List<HopCtloc> hopCtlocs=new ArrayList<HopCtloc>();
		hopCtlocs=hopCtlocService.getCtlocInfo(dto);
		WebContext webContext = WebContextHolder.getContext();
		webContext.getResponse().getWriter().write(JsonUtils.toJson(hopCtlocs));
			
	}
	
	//显示HopCtlocVo信息，即包括科室表中的指向医院Id对应的医院描述
	public void listInfo(BusinessRequest res) throws Exception {
		
		HopCtlocDto dto = super.getDto(HopCtlocDto.class, res);		
		List<HopCtlocVo> hopCtlocVos=new ArrayList<HopCtlocVo>();		
		hopCtlocVos=hopCtlocService.getListInfo(dto);
		WebContext webContext = WebContextHolder.getContext();
		//webContext.getResponse().getWriter().write(JsonUtils.toJson(hopCtlocVos));
		webContext.getResponse().getWriter().write(
				"{\"total\":"
						+ dto.getPageModel()
								.getTotals()
						+ ",\"rows\":"
						+ JsonUtils.toJson(hopCtlocVos)
						+ "}");
			
	}
	
}
