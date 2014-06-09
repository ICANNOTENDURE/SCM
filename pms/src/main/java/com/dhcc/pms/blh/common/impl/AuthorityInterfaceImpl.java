package com.dhcc.pms.blh.common.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.service.AuthorityInterface;
import com.dhcc.framework.transmission.dto.Response;
import com.dhcc.pms.dto.userManage.FuncDto;
import com.dhcc.pms.entity.userManage.Func;


/**
 * <p>标题: AuthorityInterfaceImpl.java</p>
 * <p>业务描述: 统一运维及安全管理平台</p>
 * <p>公司: 东华软件股份公司</p>
 * <p>版权: dhcc2013</p>
 * @author 于鸿
 * @date 2013年11月25日
 * @version V1.0 
 */
@Component("authorityInterface")
public class AuthorityInterfaceImpl implements AuthorityInterface{
	
	private static Log logger = LogFactory.getLog(AuthorityInterfaceImpl.class);

	@Override
	public Set<String> getNeedCheckUrls(String systemType) {
		Set<String> needCheckUrls=new HashSet<String>();
		FuncDto dto = new FuncDto();
		dto.setSystemType(systemType);
		Response<Func>  response = null;
		try {
			//response =PmsApiResourceClient.funcCtrlGetFuncListBySystemType(dto);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("can not load security url ",e);
		}
		if(response!=null&&response.getPageData()!=null){
			for(Func func :response.getPageData()){
				needCheckUrls.add(func.getSecutiryUrl());
			}
			needCheckUrls.remove(null);
			needCheckUrls.remove("");
		}
		return needCheckUrls;
	}

}
