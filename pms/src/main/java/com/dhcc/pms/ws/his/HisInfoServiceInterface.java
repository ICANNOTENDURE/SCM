package com.dhcc.pms.ws.his;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.dhcc.pms.entity.vo.ws.HisCmpRecWeb;
import com.dhcc.pms.entity.vo.ws.HisIncItmWeb;
import com.dhcc.pms.entity.vo.ws.HisIncWeb;
import com.dhcc.pms.entity.vo.ws.HisInvInfoWeb;
import com.dhcc.pms.entity.vo.ws.HisOrderWebVo;
import com.dhcc.pms.entity.vo.ws.OperateResult;


@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface HisInfoServiceInterface {
		
	
		/**
		 * 
		* @Title: HisInfoServiceInterface.java
		* @Description: TODO(同步医院药品基本信息)
		* @param venIncWeb
		* @return
		* @return:OperateResult 
		* @author zhouxin  
		* @date 2014年7月24日 上午10:00:12
		* @version V1.0
		 */
	 	@WebMethod
	    @WebResult(name="operateResult")
	    public OperateResult getHopInc(@WebParam(name="hisIncWeb")HisIncWeb hisIncWeb);
		
	    /**
	     * 
	    * @Title: HisInfoServiceInterface.java
	    * @Description: TODO(供医院订单调用，上传订单)
	    * @param venIncWeb
	    * @return
	    * @return:OperateResult 
	    * @author zhouxin  
	    * @date 2014年7月24日 上午9:59:04
	    * @version V1.0
	     */
	    @WebMethod
	    @WebResult(name="operateResult")
	    public OperateResult getHisOrder(@WebParam(name="hisOrderWebVo")HisOrderWebVo hisOrderWebVo);
	    
	    
	    

	    
	    
	    /**
	     * 
	    * @Title: HisInfoServiceInterface.java
	    * @Description: TODO(his通过发票号返回入库明细)
	    * @param invNo
	    * @param hopName
	    * @param venName
	    * @return
	    * @return:OperateResult 
	    * @author zhouxin  
	    * @date 2014年7月30日 上午10:28:07
	    * @version V1.0
	     */
	    @WebMethod
	    @WebResult(name="hisInvInfoWeb")
	    public HisInvInfoWeb getRecItmByInv(@WebParam(name="invNo")String invNo,@WebParam(name="hopName")String hopName,@WebParam(name="venName")String venName);
	    
	    
	    
	    /**
	     * 
	    * @Title: HisInfoServiceInterface.java
	    * @Description: TODO(和his同步药品信息)
	    * @param hopInc
	    * @param hopname
	    * @return
	    * @return:OperateResult 
	    * @author zhouxin  
	    * @date 2014年7月31日 上午9:23:36
	    * @version V1.0
	     */
	    @WebMethod
	    @WebResult(name="operateResult")
	    public OperateResult saveHisInc(@WebParam(name="hisIncItmWeb")HisIncItmWeb hisIncItmWeb,@WebParam(name="hopname")String hopname);
	    
	    
	    /**
	     * 
	    * @Title: HisInfoServiceInterface.java
	    * @Description: TODO(确认发以入库)
	    * @param hisCmpRecWeb
	    * @return
	    * @return:OperateResult 
	    * @author zhouxin  
	    * @date 2014年8月1日 下午2:45:53
	    * @version V1.0
	     */
	    @WebMethod
	    @WebResult(name="operateResult")
	    public OperateResult cmpRec(@WebParam(name="hisCmpRecWeb")HisCmpRecWeb hisCmpRecWeb);
	    
	    
	    
	    
	    
}