/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ven;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.pms.service.ven.VenDeliverService;


@Component
public class VenDeliverBlh extends AbstractBaseBlh {


	@Resource
	private VenDeliverService venDeliverService;
	
	@Resource
	private CommonService commonService;
	
	public VenDeliverBlh() {
		
	}
	
	
	
}
