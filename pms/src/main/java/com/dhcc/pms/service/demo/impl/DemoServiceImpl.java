package com.dhcc.pms.service.demo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.PagerModel;
import com.dhcc.pms.dao.demo.DemoDao;
import com.dhcc.pms.dto.demo.DemoDto;
import com.dhcc.pms.entity.demo.Demo;
import com.dhcc.pms.service.demo.DemoService;
/**
 * <p>标题：</p>
 * <p>业务描述：</p>
 * <p>公司：东华软件股份公司</p>
 * <p>版权：dhcc2013</p>
 * @author 吴杰
 * @date 2013年12月9日
 * @version 
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService  {

	@Resource
	private CommonService commonService;
	
	@Resource
	private DemoDao demoDao;

	@Override
	public void list(DemoDto dto) {
		PagerModel pagerModel=dto.getPageModel();
		//调用DAO拼接查询条件
		demoDao.buildPagerModelQuery(pagerModel, dto);
		//调用分页查询方法
		commonService.fillPagerModelData(pagerModel);
	}

	@Override
	public void save(DemoDto dto) {
		demoDao.save(dto.getDemo());
	}

	@Override
	public void delete(DemoDto dto) {
		demoDao.deleteById(Demo.class, dto.getDemo().getDemoId());
	}

	@Override
	public void update(DemoDto dto) {
		demoDao.update(dto.getDemo());
	}

	@Override
	public Demo findById(DemoDto dto) {
		return demoDao.findById(dto.getDemo());
	}

}
