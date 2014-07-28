package com.dhcc.pms.ws.ven;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhcc.framework.annotation.Descript;
import com.dhcc.framework.common.WsInfoHolder;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.pms.blh.ord.OrderStateBlh;
import com.dhcc.pms.blh.ven.VenIncBlh;
import com.dhcc.pms.dto.ord.OrderStateDto;
import com.dhcc.pms.dto.ven.VenIncDto;
import com.dhcc.pms.entity.vo.ws.DeliverWeb;
import com.dhcc.pms.entity.vo.ws.OperateResult;
import com.dhcc.pms.entity.vo.ws.OrderWebVo;
import com.dhcc.pms.entity.vo.ws.VenIncWeb;

/**
 * @author auto-generated by AnnoParameterParser
 * @timestamp 2014-07-10 09:36:34.205
 */
@WebService(endpointInterface = "com.dhcc.pms.ws.ven.OrderStateWServiceInterface",targetNamespace="http://ven.ws.pms.dhcc.com/",
portName="OrderStateWServiceServiceHttpSoap12Endpoint")
public class OrderStateWService implements OrderStateWServiceInterface{

    private static Log logger = LogFactory.getLog(OrderStateWServiceInterface.class);

    @Resource
    private OrderStateBlh blh;
    
    @Resource
    private VenIncBlh venIncBlh;
    
    @Descript("供应商确认收到订单")
   /**
    * 方法名:                recievedMsg
    * 方法功能描述：                                          供应商确认收到订单
    * @param  order.orderId 订单ID
    * @return com.dhcc.pms.entity.vo.ws.OperateResult   术语类型是{@linkplain java.lang.Long}  值域类型是{@linkplain java.lang.Long}
    * @see java.lang.Long
    * @see java.lang.Long
    * @since JDK1.7
    * @Create Date:        2014-07-10 09:36:34.205
    */
    @Override
    @WebMethod
    public OperateResult recievedMsg(String orderId) {

        OrderStateDto dto = new OrderStateDto();
        dto.setOrderIdStr(orderId);
        BusinessRequest request = new BusinessRequest();
        request.setDto(dto);

        try {
            blh.recievedMsg(request);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return dto.getOperateResult();

    }

    @com.dhcc.framework.annotation.Descript("供应商确认收到订单")
   /**
    * 方法名:                deliver
    * 方法功能描述：                                          供应商确认收到订单
    * @param  deliveritms 订单明细
    * @return com.dhcc.pms.entity.vo.ws.OperateResult   术语类型是{@linkplain java.util.List<com.dhcc.pms.entity.ven.VenDeliveritm>}  值域类型是{@linkplain java.util.List<com.dhcc.pms.entity.ven.VenDeliveritm>}
    * @see java.util.List<com.dhcc.pms.entity.ven.VenDeliveritm>
    * @see java.util.List<com.dhcc.pms.entity.ven.VenDeliveritm>
    * @since JDK1.7
    * @Create Date:        2014-07-10 09:36:34.206
    */
    @Override
    @WebMethod
    public OperateResult deliver(@WebParam(name="deliverWeb")DeliverWeb deliverWeb) {

        OrderStateDto dto = new OrderStateDto();
        dto.setDeliveritms(deliverWeb.getDeliveritms());
        BusinessRequest request = new BusinessRequest();
        request.setDto(dto);
        OperateResult operateResult=new OperateResult();
        operateResult.setResultCode("-1");
        dto.setOperateResult(operateResult);
        try {
            blh.deliver(request);
        } catch(Exception e) {
        	dto.getOperateResult().setResultCode("-111");
        	dto.getOperateResult().setResultContent(e.getLocalizedMessage());
            logger.error(e.getMessage(), e);
        }
        
        return dto.getOperateResult();

    }

	@com.dhcc.framework.annotation.Descript("查询订单")
   /**
    * 方法名:                listOrderWS
    * 方法功能描述：                                          查询订单
    * @param  passWord,  用户名,必填  类型是{@linkplain java.lang.String}
    * @param  passWord,  密码,必填  类型是{@linkplain java.lang.String}
    * @return java.util.List<com.dhcc.pms.entity.vo.ord.OrderWSVo>   术语类型是{@linkplain java.lang.String}  值域类型是{@linkplain java.lang.String}
    * @see java.lang.String
    * @see java.lang.String
    * @since JDK1.7
    * @Create Date:        2014-07-10 09:36:34.206
    */
    @Override
    @WebMethod
    @WebResult(name="orderWSVo")
    public List<OrderWebVo> listOrderWS(@WebParam(name="passWord")String passWord, @WebParam(name="userName")String userName) {

        OrderStateDto dto = new OrderStateDto();
        dto.setPassWord(passWord);
        dto.setUserName(userName);
        BusinessRequest request = new BusinessRequest();
        request.setDto(dto);

        try {
            blh.listOrderWS(request);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return (List<OrderWebVo>)dto.getOrderWSVos();

    }
	
	
	/* (non-Javadoc)
	 * @see com.dhcc.pms.ws.ord.OrderStateWServiceInterface#getVenInc(java.util.List)
	 */
	@Override
	public OperateResult getVenInc(VenIncWeb venIncWeb) {
		// TODO Auto-generated method stub
		VenIncDto dto = new VenIncDto();
	    dto.setVenIncWeb(venIncWeb);
	    BusinessRequest request = new BusinessRequest();
	    request.setDto(dto);
        try{
        	venIncBlh.SynchVenInc(request);
        } catch(Exception e) {
        	logger.error(e.getMessage(), e);
        }
		return dto.getOperateResult();
	}
	
	
	
    @PostConstruct
    private void preRegister() {
       WsInfoHolder.registWsInfo(OrderStateWServiceInterface.class);
    }
    
    
    
    
	
}