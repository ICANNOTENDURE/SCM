package com.dhcc.pms;

import com.dhcc.framework.common.codegen.CodeGenUtils;



/**
 * <p>标题: PmsCodeGen.java</p>
 * <p>业务描述: 统一运维及安全管理平台</p>
 * <p>公司: 东华软件股份公司</p>
 * <p>版权: dhcc2013</p>
 * @author 于鸿
 * @date 2013年10月25日
 * @version V1.0 
 */
public class PmsCodeGen {
	public static void main(String[] args){
		CodeGenUtils.createCode("com.dhcc.pms", "SysImpModel", "ImpModel","sys");
	}
}
