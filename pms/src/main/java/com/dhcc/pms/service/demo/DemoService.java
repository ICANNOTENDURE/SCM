package com.dhcc.pms.service.demo;

import com.dhcc.pms.dto.demo.DemoDto;
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
public interface DemoService {
	public void list(DemoDto dto);
	public void save(DemoDto dto);
	public void delete(DemoDto dto);
	public void update(DemoDto dto);
	public Demo findById(DemoDto dto);
}
