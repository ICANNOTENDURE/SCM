package com.dhcc.framework.common;

/**
 * 常量类
 * @author liuyg
 */
public abstract class BaseConstants {

	/**
	 * session中存放当前用户ID的key
	 */
	public static String SESSION_KEY_USER_ID = "SESSION_USER_ID";
	
	/**
	 * session过期后返回给ajax请求的错误信息
	 */
	public static String SESSION_TIME_OUT_AJAX_ERR_MSG = "TIME_OUT";
	
	/**
	 * request中存放消息的key
	 */
	public static final String REQUEST_MESSAGE_KEY = "REQ_MESSAGE";
	
	/**
	 * session中存放用户的变量
	 */
	public final static String USER_IN_SESSION = "sessionUser";



	/**
	 * 存放session中visitor对像的变量
	 */
	public final static String VISITOR = "currentUser";

	/**
	 * request中存放页面提示的key
	 */
	public static final String PAGE_TIP_KEY = "PAGE_TIP";
	
	/**
	 * request中存放对话框类型key
	 */
	public static final String REQUEST_MESSAGE_DIALOG_TYPE_KEY = "MESSAGE_DIALOG_TYPE";
	
	/**
	 * 逗号分隔符
	 */
	public static final String  COMMA = ",";


	
	/**
	 * memcache,缺省对像缓存周期
	 */
	public static final int MEM_MAX_AGE = 28 * 60 * 60;
	
	
	/**
	 * 前端调用时URL参数中指明方法调用的参数名
	 */
	public static final String INVOKE_BLH_METHOD="BLHMI";
}
