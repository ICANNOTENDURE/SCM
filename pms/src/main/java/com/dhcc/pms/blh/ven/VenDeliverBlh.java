/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ven;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.pms.dto.ven.VenDeliverDto;
import com.dhcc.pms.entity.ven.VenDeliver;
import com.dhcc.pms.entity.ven.VenDeliveritm;
import com.dhcc.pms.service.ven.VenDeliverService;


@Component
public class VenDeliverBlh extends AbstractBaseBlh {


	@Resource
	private VenDeliverService venDeliverService;
	
	@Resource
	private CommonService commonService;
	
	public VenDeliverBlh() {
		
	}
	
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(查询发货单)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午3:46:55
	* @version V1.0
	 */
	public void listDeliver(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.listDeliver(dto);
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(查询发货单明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午3:46:59
	* @version V1.0
	 */
	public void listDeliverItm(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.listDeliverItm(dto);
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(修改发货单明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午5:03:15
	* @version V1.0
	 */
	public void saveDeliverItm(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.saveDeliverItm(dto);
		dto.setOpFlg("1");
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(删除一条发货明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午5:28:40
	* @version V1.0
	 */
	public void delDeliverItm(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		commonService.delete(commonService.get(VenDeliveritm.class, dto.getVenDeliveritm().getDeliveritmId()));
		dto.setOpFlg("1");
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(保存发货单)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午7:06:51
	* @version V1.0
	 */
	public void saveMain(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		VenDeliver venDeliver=commonService.get(VenDeliver.class, dto.getVenDeliver().getDeliverId());
		venDeliver.setDeliverArrdate(dto.getVenDeliver().getDeliverArrdate());
		venDeliver.setDeliverRemark(dto.getVenDeliver().getDeliverRemark());
		commonService.saveOrUpdate(venDeliver);
		dto.setOpFlg("1");
	}
}
