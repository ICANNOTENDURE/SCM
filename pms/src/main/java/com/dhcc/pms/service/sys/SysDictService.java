package com.dhcc.pms.service.sys;

import com.dhcc.pms.dto.sys.SysDictDto;
import com.dhcc.pms.entity.sys.SysDict;

/**
 * <p>标题：</p>
 * <p>业务描述：</p>
 * <p>公司：东华软件股份公司</p>
 * <p>版权：dhcc2013</p>
 * @author 吴杰
 * @date 2013年12月9日
 * @version 
 */
public interface SysDictService {
	public void list(SysDictDto dto);
	public void save(SysDictDto dto);
	public void delete(SysDictDto dto);
	public void update(SysDictDto dto);
	public SysDict findById(SysDictDto dto);
}
