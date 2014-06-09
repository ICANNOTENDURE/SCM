package com.dhcc.pms.dto.demo;

import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.pms.entity.demo.Demo;
/**
 * <p>标题：</p>
 * <p>业务描述：</p>
 * <p>公司：东华软件股份公司</p>
 * <p>版权：dhcc2013</p>
 * @author 吴杰
 * @date 2013年12月9日
 * @version 
 */

public class DemoDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private Demo demo;
	public Demo getDemo() {
		return demo;
	}
	public void setDemo(Demo demo) {
		this.demo = demo;
	}
	
}

