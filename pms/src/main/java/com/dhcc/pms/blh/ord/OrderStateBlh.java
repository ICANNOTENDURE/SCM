/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.ord.OrderStateDto;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.ord.OrderItm;
import com.dhcc.pms.entity.userManage.NormalAccount;
import com.dhcc.pms.entity.ven.VenDeliveritm;
import com.dhcc.pms.entity.vo.ws.OperateResult;
import com.dhcc.pms.service.ord.OrderStateService;
import com.dhcc.pms.service.userManage.NormalAccountService;
import com.dhcc.pms.service.ven.VenDeliverService;


@Component
public class OrderStateBlh extends AbstractBaseBlh {


	@Resource
	private OrderStateService ordertateService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private NormalAccountService normalAccountService;
	
	@Resource
	private VenDeliverService venDeliverService;
	
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
	/*
	@BlhParameters(dtoCls=OrderStateDto.class,parameter={
		@BlhParameter(parameterExpress="emflag",testvalues={("emflag=1")},desc="是否加急")
	})
	@Descript("查询订单")
	@OutPut(ognlExpress="dto.pageModel")
	*/
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
		dto.setOpFlg("1");
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
	/*
	@BlhParameters(dtoCls=OrderStateDto.class,parameter={
		@BlhParameter(parameterExpress="exeState.ordId",testvalues={("exeState.ordId=1000")},desc="订单ID"),
		@BlhParameter(parameterExpress="pageModel.pageSize",testvalues={("pageModel.pageSize=10")},desc="每页条数"),
		@BlhParameter(parameterExpress="pageModel.pageNo",testvalues={("pageModel.pageNo=1")},desc="当前页数")
	})
	@Descript("订单明细")
	@OutPut(ognlExpress="dto.pageModel")
	*/
	public void listOrdItm(BusinessRequest res){
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);
		ordertateService.listOrderItm(dto);
		dto.setOpFlg("1");
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
		WebContextHolder.getContext().getResponse().setContentType("text/html;charset=UTF-8");
		WebContextHolder.getContext().getResponse().getWriter()
		.write("{\"total\":"
				+ dto.getPageModel().getTotals()
				+ ",\"rows\":"
				+ JsonUtils.toJson(dto.getPageModel().getPageData())
				+ "}");
		WebContextHolder.getContext().getResponse().getWriter().flush();
	}
	
	
	
	
	
	
	//////////////////////////////////////////////////////webservice gener///////////////////////////////////////////////////
	/**
	 * 
	* @Title: OrderStateBlh.java
	* @Description: TODO(给供应商查询订单接口)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月2日 上午11:09:16
	* @version V1.0
	 */
	/*
	@BlhParameters(dtoCls=OrderStateDto.class,parameter={
		//@BlhParameter(parameterExpress="dto.sendFlag",testvalues={("dto.sendFlag=1")},desc="是否发送过,1，未发送过,2，发送过,必填"),
		//@BlhParameter(parameterExpress="dto.reqStDate",testvalues={("dto.reqStDate=2014-01-01")},desc="开始日期,可为空"),
		//@BlhParameter(parameterExpress="dto.reqEdDate",testvalues={("dto.reqEdDate=2014-09-01")},desc="结束日期,可为空"),
		@BlhParameter(parameterExpress="dto.userName",testvalues={("dto.userName=1000")},desc="用户名,必填"),
		@BlhParameter(parameterExpress="dto.passWord",testvalues={("dto.passWord=1000")},desc="密码,必填")
	})
	@Descript("查询订单")
	@OutPut(ognlExpress="dto.orderWSVos")
	*/
	public void listOrderWS(BusinessRequest res) {
	
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);
		if(StringUtils.isNullOrEmpty(dto.getUserName())){
			return;
		}
		if(StringUtils.isNullOrEmpty(dto.getPassWord())){
			return;
		}
		NormalAccount normalAccount=normalAccountService.getNormalAccountByAccount(dto.getUserName());
		if(normalAccount==null){
			return;
		}
		if(!normalAccount.getPassword().equals(dto.getPassWord())){
			return;
		}
		if(!normalAccount.getNormalUser().getType().toString().equals("2")){
			return;
		}
		dto.setVendor(normalAccount.getNormalUser().getVendorId());
		dto.setSendFlag("0");
		//调用对应的service方法
		ordertateService.listOrderWS(dto);
	}
	
	
	
	/**
	 * 
	* @Title: OrderStateBlh.java
	* @Description: TODO(供应商确认收到订单信息)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月9日 上午9:30:28
	* @version V1.0
	 */
	/*
	@BlhParameters(dtoCls=OrderStateDto.class,parameter={
		@BlhParameter(parameterExpress="order.orderId",testvalues={("order.orderId=1000")},desc="订单ID")
	})
	@Descript("供应商确认收到订单")
	@OutPut(ognlExpress="dto.operateResult")
	*/
	public void recievedMsg(BusinessRequest res){
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);
        OperateResult operateResult=new OperateResult();
        operateResult.setResultCode("-1");
        operateResult.setResultContent("falie");
        dto.setOperateResult(operateResult);
		if (StringUtils.isNullOrEmpty(dto.getOrderIdStr())){
			dto.getOperateResult().setResultCode("-1");
			dto.getOperateResult().setResultContent("入参为空");
			return;
		}
		String[] strs=dto.getOrderIdStr().split(BaseConstants.COMMA);
		for(String id:strs){
			Order order=commonService.get(Order.class, Long.valueOf(id));
			if (order==null){
				dto.getOperateResult().setResultCode("-2");
				dto.getOperateResult().setResultContent("入参无效");
				return;
			}
			order.setSendFlag(1l);
			commonService.saveOrUpdate(order);
		}
		
		dto.getOperateResult().setResultCode("0");
		dto.getOperateResult().setResultContent("success");
	}
	
	
	/**
	 * 
	* @Title: OrderStateBlh.java
	* @Description: TODO(供应商发货调用)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月9日 上午9:36:29
	* @version V1.0
	 */
	/*
	@BlhParameters(dtoCls=OrderStateDto.class,parameter={
		@BlhParameter(parameterExpress="deliveritms",testvalues={("deliveritms=1000")},desc="订单明细")
	})
	@Descript("供应商确认收到订单")
	@OutPut(ognlExpress="dto.operateResult")
	*/
	public void deliver(BusinessRequest res){
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);
		if(dto.getDeliveritms()==null){
			dto.getOperateResult().setResultCode("-1");
			dto.getOperateResult().setResultContent("入参为空");
			return;
		}
		if(dto.getDeliveritms().size()==0){
			dto.getOperateResult().setResultCode("-1");
			dto.getOperateResult().setResultContent("入参为空");
			return;
		}
		
		Map<String, List<VenDeliveritm>> DelMap=new HashMap<String,List<VenDeliveritm>>();
		//按订单拆分发货单 
		for(VenDeliveritm tmpVenDeliveritm:dto.getDeliveritms()){
			if(tmpVenDeliveritm.getDeliveritmOrderitmid()==null){
				dto.getOperateResult().setResultCode("-2");
				dto.getOperateResult().setResultContent(tmpVenDeliveritm.getDeliveritmInvnoe()+",没有订单明细ID");
				break;
			}
			
			OrderItm orderItm=commonService.get(OrderItm.class, tmpVenDeliveritm.getDeliveritmOrderitmid());
			if(orderItm.getOrdId()==null){
				dto.getOperateResult().setResultCode("-3");
				dto.getOperateResult().setResultContent(tmpVenDeliveritm.getDeliveritmInvnoe()+",订单明细ID错误");
				break;
			}
			String orderId=orderItm.getOrdId().toString();
			
			tmpVenDeliveritm.setDeliveritmHopincid(orderItm.getIncId());
			if(DelMap.containsKey(orderId)){
				DelMap.get(orderId).add(tmpVenDeliveritm);
			}else{
				List<VenDeliveritm> deliveritms=new ArrayList<VenDeliveritm>();
				deliveritms.add(tmpVenDeliveritm);
				DelMap.put(orderId, deliveritms);
			}
		}
		
		venDeliverService.deliver(DelMap);
		dto.getOperateResult().setResultCode("0");
		dto.getOperateResult().setResultContent("success");
		
	}
	

	/**
	 * 
	 * @param res
	 * @throws IOException
	 * @author penzi
	 * @description 获取当前状态的订单以及其之前所有已经操作的状态订单信息
	 */
	public void OrderDetailAndroid(BusinessRequest res) throws IOException{
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);
		//List<OrderStateAndroidVo> androidVos=ordertateService.listOrderStateAndroid(dto);
		//调用对应的service方法
		//ordertateService.listOrderState(dto);
		//调用对应的service方法
//		WebContextHolder.getContext().getResponse().setContentType("text/html;charset=UTF-8");
//		WebContextHolder.getContext().getResponse().getWriter()
//		.write("{\"total\":"
//				+ dto.getPageModel().getTotals()
//				+",\"rows\":"
//				+ JsonUtils.toJson(androidVos)
//				+ "}");
//		WebContextHolder.getContext().getResponse().getWriter().flush();
	}
	
	/**
	 * 
	 * @param res
	 * @throws IOException
	 * @author penzi
	 * @description:PDA调用，查询各订单的执行状态
	 */
	public void OrderStateAndroid(BusinessRequest res) throws IOException{
		OrderStateDto dto = super.getDto(OrderStateDto.class, res);
		
		//调用对应的service方法
		WebContextHolder.getContext().getResponse().setContentType("text/html;charset=UTF-8");
		WebContextHolder.getContext().getResponse().getWriter()
		.write("{\"orderId\":"
				+dto.getExeState().getOrdId()
				+ ",\"rows\":"
				+ JsonUtils.toJson(ordertateService.listOrderExeState(dto))
				+ "}");
		WebContextHolder.getContext().getResponse().getWriter().flush();
	}
}
