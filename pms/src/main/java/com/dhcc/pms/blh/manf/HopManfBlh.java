/**
 * 通过模板生成Blh 
 * template by liuyg
 */
package com.dhcc.pms.blh.manf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.web.context.WebContext;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.manf.HopManfDto;
import com.dhcc.pms.entity.manf.HopManf;
import com.dhcc.pms.service.manf.HopManfService;


@Component
public class HopManfBlh extends AbstractBaseBlh {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(HopManfBlh.class);

	@Resource
	private HopManfService hopManfService;

	public HopManfBlh() {
		
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
	
		HopManfDto dto = super.getDto(HopManfDto.class, res);
		
		//调用对应的service方法
		hopManfService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		HopManfDto dto = super.getDto(HopManfDto.class, res);
		if(dto.getHopManf().getHopManfId()==null||
				(dto.getHopManf().getHopManfId()).equals("")){
			dto.getHopManf().setHopManfId(null);
			//调用对应的service方法
			hopManfService.save(dto);
		}else{
			hopManfService.update(dto);
		}
		
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		HopManfDto dto = super.getDto(HopManfDto.class, res);
		
		//调用对应的service方法
		hopManfService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		HopManfDto dto = super.getDto(HopManfDto.class, res);
		
		//调用对应的service方法
		hopManfService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		HopManfDto dto = super.getDto(HopManfDto.class, res);
		
		//调用对应的service方法
		hopManfService.findById(dto);
		
	}
	/**
	 * 获取hopManf的id和描述
	 * @param res
	 * @throws Exception
	 */
	public void getManfInfo(BusinessRequest res) throws Exception{
		HopManfDto dto = super.getDto(HopManfDto.class, res);
		
		List<HopManf> hopManfs=new ArrayList<HopManf>();
		hopManfs=hopManfService.getManfInfo(dto);
		WebContext webContext = WebContextHolder.getContext();
		webContext.getResponse().getWriter().write(JsonUtils.toJson(hopManfs));
	
	}
	
}
