package com.dhcc.pms.ws.ord;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface OrderStateWServiceInterface {

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
    @WebMethod
    public com.dhcc.pms.entity.vo.ws.OperateResult recievedMsg(java.lang.Long orderId) ;


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
    @WebMethod
    public com.dhcc.pms.entity.vo.ws.OperateResult deliver(java.util.List<com.dhcc.pms.entity.ven.VenDeliveritm> deliveritms) ;


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
    @WebMethod
    public java.util.List<com.dhcc.pms.entity.vo.ws.OrderWebVo> listOrderWS(java.lang.String passWord, java.lang.String userName) ;


}