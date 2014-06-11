/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.hop;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.PingYinUtil;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.pms.dto.hop.HopVendorDto;
import com.dhcc.pms.entity.hop.HopVendor;
import com.dhcc.pms.service.hop.HopVendorService;


@Component
public class HopVendorBlh extends AbstractBaseBlh {


	@Resource
	private HopVendorService hopVendorService;
	
	@Resource
	private CommonService commonService;
	
	public HopVendorBlh() {
		
	}
	
	/**
	 * 进入某个列表的入口方法
	 * 列表方法，也就是查询方法，调用的时候不需要xxxCtrl!list
	 * 框架 在不调Ctrl时，不指定方法，就默认为它list，在action中通过
	 * json注解，所dto中的pageModel to json
	 * @param: res
	 *  
	 */
	public void list(BusinessRequest res) {
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		//hopVendorService.save(dto);
		if(StringUtils.isNullOrEmpty(dto.getHopVendor().getHopAlias())){
			dto.getHopVendor().setHopAlias(PingYinUtil.getFirstSpell(dto.getHopVendor().getHopName()));
		}
		hopVendorService.save(dto);
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.findById(dto);
		
	}
	/**
	 * 
	* @Title: HopVendorBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 上午10:08:12
	* @version V1.0
	 */
	public void listHopCon(BusinessRequest res){
		
		
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.listHopCon(dto);
	}
	
	/**
	 * 
	* @Title: HopVendorBlh.java
	* @Description: TODO(对照或者删除对照关系)
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 上午10:55:26
	* @version V1.0
	 */
	public void contranstVendor(BusinessRequest res){
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		HopVendor hopVendor=new HopVendor();
		if(dto.getHopVendor().getHopVendorId()!=null){
			hopVendor=commonService.get(HopVendor.class, dto.getHopVendor().getHopVendorId());
		}
		if(dto.getHopVendor().getHopVenId()==null){
			hopVendor.setHopVenId(null);;
		}else{
			hopVendor.setHopVenId(dto.getHopVendor().getHopVenId());
		}	
		commonService.saveOrUpdate(hopVendor);
		dto.setOpFlg("1");
	}
}
