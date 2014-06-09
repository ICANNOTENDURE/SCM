package com.dhcc.pms.ws.ord;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dhcc.pms.entity.vo.ord.OrderItmVo;


@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface OrderStateWServiceInterface {

   /**
    * 方法名:                list
    * 方法功能描述：                                          查询订单
    * @param  emflag 是否加急
    * @return com.dhcc.framework.common.PagerModel   术语类型是{@linkplain java.lang.String}  值域类型是{@linkplain java.lang.String}
    * @see java.lang.String
    * @see java.lang.String
    * @since JDK1.7
    * @Create Date:        2014-06-06 17:29:29.343
    */
    @WebMethod
    public com.dhcc.framework.common.PagerModel list(java.lang.String emflag) ;


   /**
    * 方法名:                listOrdItm
    * 方法功能描述：                                          订单明细
    * @param  exeState.ordId 订单ID  pageModel.pageSize 每页条数  pageModel.pageNo 当前页数
    * @return com.dhcc.framework.common.PagerModel   术语类型是{@linkplain int}  值域类型是{@linkplain int}
    * @see int
    * @see int
    * @since JDK1.7
    * @Create Date:        2014-06-06 17:29:29.343
    */
    @WebMethod
    public List<OrderItmVo> listOrdItm(int pageNo, int pageSize, java.lang.Long ordId) ;


}