package com.dhcc.pms.tool.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dhcc.framework.exception.DataBaseException;

/**
 * <p>标题: PerformanceTest.java</p>
 * <p>业务描述:性能测试时获取时间</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2013</p>
 * @author 于鸿
 * @date 2013年10月10日
 * @version V1.0 
 */
public class PerformanceTest {

	/**
	 * 
	* 方法名:          getCurrentTime
	* 方法功能描述:    获取当前时间
	* @param:         无
	* @return:        当前时间，精确到毫秒
	* @Author:        于鸿
	* @Create Date:   2013年10月10日 下午3:56:01
	 */
	public static String getCurrentTime(){
		String dateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS") .format(new Date());
		return dateTime;
	}
	
	/**
	 * 
	* 方法名:          getIntervalTime
	* 方法功能描述:    获取时间间隔
	* @param:         终止时间字符串，起始时间字符串
	* @return:        时间间隔(秒)
	* @Author:        于鸿
	* @Create Date:   2013年10月10日 下午3:56:32
	 */
	public static double getIntervalTime(String endTime,String startTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		double ss=0;
		try {
			Date endDate = sdf.parse(endTime);
			Date startDate = sdf.parse(startTime);
			long mi=endDate.getTime()-startDate.getTime();
			ss=mi/1000.0;
		} catch (Exception e) {
			throw new DataBaseException(e.getMessage(), e);
		}
		return ss;
	}
	
	/**
	 * 
	* 方法名:          getIntervalTime
	* 方法功能描述:    获取时间间隔
	* @param:         终止时间，起始时间
	* @return:        时间间隔(秒)
	* @Author:        于鸿
	* @Create Date:   2013年10月10日 下午3:57:21
	 */
	public static double getIntervalTime(Date endDate,Date startDate) {
		long mi=endDate.getTime()-startDate.getTime();
		double ss=mi/1000.0;
		return ss;
	}
	
	/**
	 * 
	* 方法名:          getIntervalTime
	* 方法功能描述:    获取时间间隔
	* @param:         终止时间，起始时间
	* @return:        时间间隔(秒)
	* @Author:        于鸿
	* @Create Date:   2013年10月10日 下午4:02:18
	 */
	public static double getIntervalTime(String endTime,Date startDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		double ss=0;
		try {
			Date endDate = sdf.parse(endTime);
			long mi=endDate.getTime()-startDate.getTime();
			ss=mi/1000.0;
		} catch (Exception e) {
			throw new DataBaseException(e.getMessage(), e);
		}
		return ss;
	}

	/**
	 * 
	* 方法名:          getIntervalTime
	* 方法功能描述:    获取时间间隔
	* @param:         终止时间字符串，起始时间字符串
	* @return:        时间间隔(秒)
	* @Author:        于鸿
	* @Create Date:   2013年10月10日 下午4:03:23
	 */
	public static double getIntervalTime(Date endDate,String startTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		double ss=0;
		try {
			Date startDate = sdf.parse(startTime);
			long mi=endDate.getTime()-startDate.getTime();
			ss=mi/1000.0;
		} catch (Exception e) {
			throw new DataBaseException(e.getMessage(), e);
		}
		return ss;
	}
	
}
