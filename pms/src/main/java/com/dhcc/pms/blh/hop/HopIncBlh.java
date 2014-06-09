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
import com.dhcc.pms.dto.hop.HopIncDto;
import com.dhcc.pms.entity.vo.hop.HopIncVo;
import com.dhcc.pms.service.hop.HopIncService;


@Component
public class HopIncBlh extends AbstractBaseBlh {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(HopIncBlh.class);

	@Resource
	private HopIncService hopIncService;

	public HopIncBlh() {
		
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
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}
		//调用对应的service方法
		hopIncService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		if(dto.getHopInc().getIncId()==null||
				(dto.getHopInc().getIncId()).equals("")){
			dto.getHopInc().setIncId(null);			
			hopIncService.save(dto);		
		}else {
				
			hopIncService.update(dto);
		}	
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		
		//调用对应的service方法
		hopIncService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		
		//调用对应的service方法
		hopIncService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		
		//调用对应的service方法
		hopIncService.findById(dto);
		
	}
	
	public void getIncInfo(BusinessRequest res) throws Exception {
		
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		
		List<HopIncVo> hopIncVos=new ArrayList<HopIncVo>();	
		hopIncVos=hopIncService.getIncInfo(dto);
		WebContext webContext = WebContextHolder.getContext();
		webContext.getResponse().getWriter().write(JsonUtils.toJson(hopIncVos));
			
	}
	
	//显示HopIncVo信息，即包括科室表中的指向医院Id对应的医院描述
	public void listInfo(BusinessRequest res) throws Exception {
			
		HopIncDto dto = super.getDto(HopIncDto.class, res);		
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}
		//调用对应的service方法
		hopIncService.getListInfo(dto);
		
//		List<ShowHopIncVo> hopIncVos=new ArrayList<ShowHopIncVo>();		
//		hopIncVos=hopIncService.getListInfo(dto);
//		WebContext webContext = WebContextHolder.getContext();
//		webContext.getResponse().getWriter().write(
//				"{\"total\":"
//						+ dto.getPageModel()
//								.getTotals()
//						+ ",\"rows\":"
//						+ JsonUtils.toJson(hopIncVos)
//						+ "}");
//				
	}
	
}
