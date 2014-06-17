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
import com.dhcc.framework.exception.DataBaseException;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.web.context.WebContext;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.hop.HopCtlocDestinationDto;
import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.vo.hop.HopDestinationVo;
import com.dhcc.pms.service.hop.HopCtlocDestinationService;


@Component
public class HopCtlocDestinationBlh extends AbstractBaseBlh {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(HopCtlocDestinationBlh.class);

	@Resource
	private HopCtlocDestinationService hopCtlocDestinationService;

	public HopCtlocDestinationBlh() {
		
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
	
		HopCtlocDestinationDto dto = super.getDto(HopCtlocDestinationDto.class, res);
		
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}			
		//调用对应的service方法
		hopCtlocDestinationService.list(dto);
	}
	
	
	//保存
	public void save(BusinessRequest res) {
	
		HopCtlocDestinationDto dto = super.getDto(HopCtlocDestinationDto.class, res);
		
		if(dto.getHopCtlocDestination().getHopCtlocDestinationId()==null||
				(dto.getHopCtlocDestination().getHopCtlocDestinationId()).equals("")){
			dto.getHopCtlocDestination().setHopCtlocDestinationId(null);
			//dto.getHopCtlocDestination().setCtlocDr(dto.getHopCtlocDr().toString());
			hopCtlocDestinationService.save(dto);			
		}else {
			hopCtlocDestinationService.update(dto);
		}	
		dto.setOpFlg("1");
		
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		HopCtlocDestinationDto dto = super.getDto(HopCtlocDestinationDto.class, res);
		
		//调用对应的service方法
		hopCtlocDestinationService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		HopCtlocDestinationDto dto = super.getDto(HopCtlocDestinationDto.class, res);
		
		//调用对应的service方法
		hopCtlocDestinationService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		HopCtlocDestinationDto dto = super.getDto(HopCtlocDestinationDto.class, res);
		
		//调用对应的service方法
		hopCtlocDestinationService.findById(dto);
		
	}
	
	/**
	 * 
	* 方法名:          configOnly
	* 方法功能描述:    通过hopCtlocDestination 的hopCtlocDr获取HopCtloc
	* @param:         
	* @return:        
	* @Author:        
	* @Create Date:   
	 */
	public HopCtloc getHopCtloc(BusinessRequest res){
		try {
			HopCtlocDestinationDto dto = super.getDto(HopCtlocDestinationDto.class, res);
			HopCtloc hopCtloc=this.hopCtlocDestinationService.getCtloc(dto);	
			return hopCtloc;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage(), e);
		}
	}
	
	//显示HopDestinationVo信息，即包括收货信息表中科室的id和描述
	public void listInfo(BusinessRequest res) throws Exception {
			
		HopCtlocDestinationDto dto = super.getDto(HopCtlocDestinationDto.class, res);		
		List<HopDestinationVo> hopDestinationVos=new ArrayList<HopDestinationVo>();		
		hopDestinationVos=hopCtlocDestinationService.getListInfo(dto);
		WebContext webContext = WebContextHolder.getContext();
		//webContext.getResponse().getWriter().write(JsonUtils.toJson(hopDestinationVos));
		webContext.getResponse().getWriter().write(
				"{\"total\":"
						+ dto.getPageModel()
								.getTotals()
						+ ",\"rows\":"
						+ JsonUtils.toJson(hopDestinationVos)
						+ "}");
			
	}
	
	
	public void getDefaultDes(BusinessRequest res){
		
		
	}
	
	
}
