/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ven;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContext;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.ven.VenIncDto;
import com.dhcc.pms.entity.ven.VenInc;
import com.dhcc.pms.service.ven.VenIncService;


@Component
public class VenIncBlh extends AbstractBaseBlh {


	@Resource
	private VenIncService venIncService;
	
	@Resource
	private CommonService commonService;
	
	public VenIncBlh() {
		
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
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}
		//调用对应的service方法
		venIncService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		if(dto.getVenInc().getVenIncId()==null||
				(dto.getVenInc().getVenIncId()).equals("")){
			dto.getVenInc().setVenIncId(null);						
			venIncService.save(dto);		
		}else {
				
			venIncService.update(dto);
		}	
		
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		
		//调用对应的service方法
		venIncService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		
		//调用对应的service方法
		venIncService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		
		//调用对应的service方法
		venIncService.findById(dto);
		
	}
	
	public void getIncInfo(BusinessRequest res) throws Exception {
		
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		
		List<VenInc> venIncs=new ArrayList<VenInc>();
		venIncs=venIncService.getIncInfo(dto);
		WebContext webContext = WebContextHolder.getContext();
		webContext.getResponse().getWriter().write(JsonUtils.toJson(venIncs));
			
	}
		
	//显示VenIncVo信息，即包括科室表中的指向医院Id对应的医院描述
	public void listInfo(BusinessRequest res)  {			
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}

		VenInc venInc=new VenInc();
		venInc.setVenIncName(dto.getComgridparam());
		dto.setVenInc(venInc);

		venIncService.getListInfo(dto);
		//List<VenIncVo> venIncVos=new ArrayList<VenIncVo>();		
		//venIncVos=venIncService.getListInfo(dto);
		//WebContext webContext = WebContextHolder.getContext();
		//webContext.getResponse().getWriter().write(JsonUtils.toJson(venIncVos));
				
	}
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(药品对照)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月30日 上午9:03:53
	* @version V1.0
	 */
	public void listContrantInc(BusinessRequest res){
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		venIncService.listContrantInc(dto);
	}
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(保存药品关联)
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月30日 下午5:23:46
	* @version V1.0
	 */
	public void saveContranst(BusinessRequest res){
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		venIncService.saveContranst(dto);
	}
}
