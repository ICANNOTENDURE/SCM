/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ord;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.ord.OrdDto;
import com.dhcc.pms.entity.ord.OrderItm;
import com.dhcc.pms.entity.ven.VenDeliveritm;
import com.dhcc.pms.service.ord.OrdService;
import com.dhcc.pms.service.ven.VenIncService;


@Component
public class OrdBlh extends AbstractBaseBlh {


	@Resource
	private OrdService ordService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private VenIncService venIncService;
	
	public OrdBlh() {
		
	}
	

	public void list(BusinessRequest res) {
	
		OrdDto dto = super.getDto(OrdDto.class, res);
		
		//调用对应的service方法
		ordService.list(dto);
	}
	
	public void listItm(BusinessRequest res) throws IOException {
		
		OrdDto dto = super.getDto(OrdDto.class, res);
		
		//调用对应的service方法
		ordService.listOrdr(dto);
		WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto.getOrdVos()));
	}
	
	
	/**
	 * 
	* @Title: OrdBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param res
	* @throws IOException
	* @return:void 
	* @author zhouxin  
	* @date 2014年8月28日 下午3:35:14
	* @version V1.0
	 */
	public void deleteItm(BusinessRequest res) throws IOException {
		
		OrdDto dto = super.getDto(OrdDto.class, res);
		dto.setOpFlg("0");
		try{
		//调用对应的service方法
		ordService.deleteItm(dto);
		dto.setOpFlg("1");
		}catch(Exception e){
			e.printStackTrace();
			dto.setMsg(e.getMessage());
		}finally{
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
		}
		
	}
	
	/**
	 * 
	* @Title: OrdBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @return:void 
	* @author zhouxin  
	* @date 2014年8月29日 上午9:34:16
	* @version V1.0
	 * @throws IOException 
	 */
	public void saveOrditm(BusinessRequest res) throws IOException{
		OrdDto dto = super.getDto(OrdDto.class, res);
		try{
			OrderItm orderItm=commonService.get(OrderItm.class,dto.getOrderItmId());
			orderItm.setReqqty(dto.getQty());
			orderItm.setRp(dto.getRp());
			orderItm.setIncId(dto.getIncId());
			orderItm.setUom(dto.getUom());
			commonService.saveOrUpdate(orderItm);
			dto.setOpFlg("1");
		}catch(Exception e){
			dto.setOpFlg("-1");
			dto.setMsg(e.getMessage());
		}finally{
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
		}
		
	}
	
	public void listDelV(BusinessRequest res) {
		
		OrdDto dto = super.getDto(OrdDto.class, res);
		
		//调用对应的service方法
		ordService.listDelv(dto);
	}
	
	
	/**
	 * 
	* @Title: OrdBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年8月29日 下午4:37:39
	* @version V1.0
	 * @throws IOException 
	 */
	public void listDelVItm(BusinessRequest res) throws IOException {
		
		OrdDto dto = super.getDto(OrdDto.class, res);
		
		//调用对应的service方法
		ordService.listDeliverItm(dto);
		WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto.getDelvVos()));
	}
	
	
	/**\
	 * 
	* @Title: OrdBlh.java
	* @Description: TODO(删除明细)
	* @param res
	* @throws IOException
	* @return:void 
	* @author zhouxin  
	* @date 2014年8月29日 下午4:54:34
	* @version V1.0
	 */
	public void deleteDelVItm(BusinessRequest res) throws IOException {
		
		OrdDto dto = super.getDto(OrdDto.class, res);
		dto.setOpFlg("0");
		try{
		//调用对应的service方法
		ordService.deleteDelvItm(dto);
		dto.setOpFlg("1");
		}catch(Exception e){
			e.printStackTrace();
			dto.setMsg(e.getMessage());
		}finally{
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
		}
	}
	
	public void saveDelVItm(BusinessRequest res) throws IOException {
		
		OrdDto dto = super.getDto(OrdDto.class, res);
		try{
			VenDeliveritm deliveritm=commonService.get(VenDeliveritm.class,dto.getDeliverItmid());
			deliveritm.setDeliveritmBatno(dto.getBatno());
			deliveritm.setDeliveritmExpdate(dto.getExpdate());
			deliveritm.setDeliveritmInvnoe(dto.getInvno());
			deliveritm.setDeliveritmQty(dto.getQty());
			deliveritm.setDeliveritmRp(dto.getRp());
			
			Float fac=deliveritm.getDeliveritmFac();
			if(fac==null){
				fac=venIncService.getFacByhopInc(deliveritm.getDeliveritmHopincid(), WebContextHolder.getContext().getVisit().getUserInfo().getVendorIdLong());
				deliveritm.setDeliveritmFac(fac);
			}
			if(dto.getQty()!=null){
				deliveritm.setDeliveritmHisQty(dto.getQty().floatValue()*fac);
			}
			if(dto.getRp()!=null){
				deliveritm.setDeliveritmHisRp(dto.getRp().floatValue()*fac);
			}
			commonService.saveOrUpdate(deliveritm);
			dto.setOpFlg("1");
		}catch(Exception e){
			dto.setOpFlg("-1");
			dto.setMsg(e.getMessage());
			e.printStackTrace();
		}finally{
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
		}
	}
}
