package com.dhcc.pms;

import org.junit.Test;

import com.dhcc.framework.test.AbstractBaseTransactionalSpringContextTest;

/**
 * 标题: PmsRestCodeGen.java
 * 业务描述：安全运维管理平台
 * 公司:东华软件股份公司
 * 版权:dhcc2013
 * @author 聂文来
 * @date 2013年10月18日
 * @version V1.0 
 */
public class PmsRestCodeGen extends AbstractBaseTransactionalSpringContextTest{
     

	public void codeGen(){
		super.exportService();
	}
	
}
