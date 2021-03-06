/**
 * 通过模板生成Blh 
 * template by liuyg
 */
package com.dhcc.pms.blh.ven;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.pms.dto.ven.VenQualifTypeDto;
import com.dhcc.pms.service.ven.VenQualifTypeService;


@Component
public class VenQualifTypeBlh extends AbstractBaseBlh {


	@Resource
	private VenQualifTypeService venQualifTypeService;
	
	@Resource
	private CommonService commonService;
	
	public VenQualifTypeBlh() {
		
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
	
		VenQualifTypeDto dto = super.getDto(VenQualifTypeDto.class, res);
		
		//调用对应的service方法
		venQualifTypeService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		VenQualifTypeDto dto = super.getDto(VenQualifTypeDto.class, res);
		
		//调用对应的service方法
		//venQualifTypeService.save(dto);
		commonService.saveOrUpdate(dto.getVenQualifType());
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		VenQualifTypeDto dto = super.getDto(VenQualifTypeDto.class, res);
		
		//调用对应的service方法
		venQualifTypeService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		VenQualifTypeDto dto = super.getDto(VenQualifTypeDto.class, res);
		
		//调用对应的service方法
		venQualifTypeService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		VenQualifTypeDto dto = super.getDto(VenQualifTypeDto.class, res);
		
		//调用对应的service方法
		venQualifTypeService.findById(dto);
		
	}
	
}
