/**  
* @Title: PrimaryGenerater.java
* @Package com.dhcc.framework.util
* @Description: TODO(用一句话描述该文件做什么)
* @author zhouxin  
* @date 2014 2014年8月27日 下午7:19:38
* @version V1.0  
*/
package com.dhcc.framework.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhouxin
 *
 */
public class PrimaryGenerater {
	
	 private static final String SERIAL_NUMBER = "XXXX"; // 流水号格式
	
	 private static PrimaryGenerater primaryGenerater = null;

	     private PrimaryGenerater() {
	
	     }

	     /**
	 14
	      * 取得PrimaryGenerater的单例实现
	 15
	      *
	 16
	      * @return
	 17
	      */
	
	     public static PrimaryGenerater getInstance() {
	
	         if (primaryGenerater == null) {

	             synchronized (PrimaryGenerater.class) {

	                 if (primaryGenerater == null) {
	
	                     primaryGenerater = new PrimaryGenerater();

	                 }
	
	             }
	
	         }
	
	         return primaryGenerater;

	     }

	     /**
	 30
	      * 生成下一个编号
	 31
	      */
	
	     public synchronized String geneterNextNumber(String sno) {
	
	         String id = null;
	
	         Date date = new Date();
	
	         SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	
	         if (sno == null) {
	
	             id = formatter.format(date) + "0001";
	 
	         } else {
	 
	             int count = SERIAL_NUMBER.length();
	 
	             StringBuilder sb = new StringBuilder();
	 
	             for (int i = 0; i < count; i++) {
	 
	                 sb.append("0");
	 
	             }
	 
	             DecimalFormat df = new DecimalFormat("0000");
	 
	             id = formatter.format(date)
	 
	             + df.format(1 + Integer.parseInt(sno.substring(8, 12)));
	 
	         }
	 
	         return id;
	 
	     }
	 
}
