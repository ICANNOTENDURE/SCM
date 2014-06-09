package com.dhcc.pms.blh.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.pms.dto.demo.DemoDto;
import com.dhcc.pms.entity.demo.Demo;
import com.dhcc.pms.service.demo.DemoService;

/**
 * <p>标题：</p>
 * <p>业务描述：</p>
 * <p>公司：东华软件股份公司</p>
 * <p>版权：dhcc2013</p>
 * @author 代超
 * @date 2013年12月9日
 * @version 
 */
@Component
public class DemoBlh extends AbstractBaseBlh {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(DemoBlh.class);
	
	@Resource
	private DemoService demoService;
	
	@Resource
	CommonService commonService;

	  /**
	 * 
	 * 方法名: list 方法功能描述: 列表查询
	 * @param:  res
	 * @Author: 代超
	 * @Create Date: 2013年12月9日 上午10:38:50
	 */
	public void list(BusinessRequest res){
		
		DemoDto dto=super.getDto(DemoDto.class, res);
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}
		demoService.list(dto);
		
		Map<String, String>map = new HashMap<String, String>(1);
		map.put("demoGender", "gender");
		commonService.dictionaryConvert(dto.getPageModel().getPageData(), map);
	}
	
	public void save(BusinessRequest res) {
		
		DemoDto dto=super.getDto(DemoDto.class, res);
		
		if("".equals(dto.getDemo().getDemoId())){
			dto.getDemo().setDemoId(null);
		}
		
		//调用对应的service方法
		demoService.save(dto);
	}

	public void update(BusinessRequest res) {
		
		DemoDto dto=super.getDto(DemoDto.class, res);
		
		//调用对应的service方法
		demoService.update(dto);
	}

	public void delete(BusinessRequest res) {
		
		DemoDto dto=super.getDto(DemoDto.class, res);
		
		//调用对应的service方法
		demoService.delete(dto);
	}
	
	public void findById(BusinessRequest res) {
		
		DemoDto dto=super.getDto(DemoDto.class, res);
		
		//调用对应的service方法
		Demo demo = demoService.findById(dto);
		dto.setDemo(demo);
	}
}
	
	
