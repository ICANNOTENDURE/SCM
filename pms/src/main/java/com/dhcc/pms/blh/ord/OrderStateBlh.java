/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ord;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.annotation.BlhParameter;
import com.dhcc.framework.annotation.BlhParameters;
import com.dhcc.framework.annotation.Descript;
import com.dhcc.framework.annotation.OutPut;
import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.ord.OrderStateDto;
import com.dhcc.pms.service.ord.OrderStateService;


@Component
public class OrderStateBlh extends AbstractBaseBlh {


	@Resource
	private OrderStateService ordertateService;
	
	@Resource
	private CommonService commonService;
	
	public OrderStateBlh() {
		
	}
	
	/**
	 * 进入某个列表的入口方法
	 * 列表方法，也就是查询方法，调用的时候不需要xxxCtrl!list
	 * 框架 在不调Ctrl时，不指定方法，就默认为它list，在action中通过
	 * json注解，所dto中的pageModel to json
	 * @param: res
	 *  
	 */
	@BlhParameters(dtoCls=OrderStateDto.class,parameter={
		@BlhParameter(parameterExpress="emflag",testvalues={("emflag=1")},desc="是否加急")
	})
	@Descript("查询订单")
	@OutPut(ognlExpress="dto.pageModel")
	public void list(BusinessRequest res) {
	
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);
		
		//调用对应的service方法
		ordertateService.listOrderState(dto);
	}

	/**
	 * 
	* @Title: OrderStateBlh.java
	* @Description: TODO(查询一个订单的执行历史)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月28日 下午5:34:11
	* @version V1.0
	 * @throws IOException 
	 */
	public void listExeState(BusinessRequest res) throws IOException{
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);
		WebContextHolder
		.getContext()
		.getResponse()
		.getWriter()
		.write(JsonUtils.toJson(ordertateService.listOrderExeState(dto)));
	}
	
	/**
	 * 
	* @Title: OrderStateBlh.java
	* @Description: TODO(查询订单明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月29日 上午9:21:21
	* @version V1.0
	 */
	@BlhParameters(dtoCls=OrderStateDto.class,parameter={
		@BlhParameter(parameterExpress="exeState.ordId",testvalues={("exeState.ordId=1000")},desc="订单ID"),
		@BlhParameter(parameterExpress="pageModel.pageSize",testvalues={("pageModel.pageSize=10")},desc="每页条数"),
		@BlhParameter(parameterExpress="pageModel.pageNo",testvalues={("pageModel.pageNo=1")},desc="当前页数")
	})
	@Descript("订单明细")
	@OutPut(ognlExpress="dto.pageModel")
	public void listOrdItm(BusinessRequest res){
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);
		ordertateService.listOrderItm(dto);
	}
	
	/**
	 * 
	* @Title: OrderStateBlh.java
	* @Description: TODO(查询订单状态列表)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月29日 下午2:43:23
	* @version V1.0
	 * @throws IOException 
	 */
	public void getComboList(BusinessRequest res) throws IOException{
		WebContextHolder
		.getContext()
		.getResponse()
		.getWriter()
		.write(JsonUtils.toJson(ordertateService.getComboList()));
	}
	/**
	 * 
	 * @param res
	 * @throws IOException
	 * 1024
	 */
	public void OrderAndroid(BusinessRequest res) throws IOException{
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);

		//调用对应的service方法
		ordertateService.listOrderState(dto);
		//调用对应的service方法
		WebContextHolder
		.getContext()
		.getResponse()
		.getWriter()
		.write(JsonUtils.toJson(dto.getPageModel().getPageData()));
	}
}
