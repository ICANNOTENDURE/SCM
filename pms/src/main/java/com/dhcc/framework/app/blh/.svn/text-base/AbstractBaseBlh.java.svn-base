package com.dhcc.framework.app.blh;

import java.io.IOException;

import com.dhcc.framework.exception.BaseException;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.transmission.event.BusinessResponse;
import com.dhcc.framework.transmission.event.RequestEvent;
import com.dhcc.framework.transmission.event.ResponseEvent;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.web.context.WebContextHolder;


/**
 * blh 抽像类 所有BLH都要继承它
 * @author liuyg
 *
 */
public abstract class AbstractBaseBlh implements BusinessLogicHandler {

	public AbstractBaseBlh() {

	}

	public ResponseEvent performTask(RequestEvent requestEvent) throws BaseException {
		
		BusinessRequest ReqEvent = (BusinessRequest) requestEvent;
		ResponseEvent response = this.invokeHander(ReqEvent);
		return response;
	}

	public ResponseEvent invokeHander(BusinessRequest request) throws BaseException {

		return BlhInvoker.invokeBlh(request, this, this.getClass());
	}

	/**
	 * 用可变长参数就是为了不强制传入参数，没参数时 直接调用getView，虽然是可变长参数，
	 * 但只取第一个参数作为forward,第二个参数是提信信息或提示信息代码
	 * 
	 * @author liuyg 功能：
	 * @param result
	 * @return
	 */

	public BusinessResponse getResponse(String... resultMsg) {
		BusinessResponse response = new BusinessResponse();
		if (resultMsg != null&& resultMsg.length >= 1 ) {
			response.displayData("forward", resultMsg[0]);
			if (resultMsg.length >= 2 && resultMsg[1] != null) {
				response.displayData("message", resultMsg[1]);
			}
		}
		return response;
	}

	protected void writeResult(String resStr) {
		WebContextHolder.getContext().writeResponse(resStr);
	}
	
	/**
	 * 写入JSON数据
	 * @param obj 数据对象（转换json前的对象）
	 * @throws IOException
	 */
	public void writeJSON(Object obj){
		
		writeResult(JsonUtils.toJson(obj));
	}


	@SuppressWarnings("unchecked")
	protected <T extends BaseDto> T getDto(Class<T> clasz, BusinessRequest req) {
		return (T) req.getDto();
	}

	protected BusinessResponse globalAjax() {

		return getResponse("globalAjaxRest");
	}
	
	/**
	 * 获取request中参数值
	 * @param name
	 * @return
	 * 后面的版本中将禁用这些方法
	 */
	@Deprecated 
	public String getParameter(String name){
		return WebContextHolder.getContext().getRequest().getParameter(name);
	}

	/**
	 * 获取request中参数值数组
	 * @param name
	 * @return
	 * 后面的版本中将禁用这些方法
	 */
	@Deprecated 
	public String[] getParameterValues(String name){
		return WebContextHolder.getContext().getRequest().getParameterValues(name);
	}
}
