/**  
* @Title: FileUtils.java
* @Package com.dhcc.framework.util
* @Description: TODO(用一句话描述该文件做什么)
* @author zhouxin  
* @date 2014 2014年6月3日 上午11:12:10
* @version V1.0  
*/
package com.dhcc.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.dhcc.framework.exception.DataBaseException;

/**
 * @author Administrator
 *
 */
public class FileUtils {
	
	/**
	 * 
	* 方法名:          getFileExp
	* 方法功能描述:     获取文件的后缀名
	* @param:         
	* @return:        
	* @Author:        周鑫
	 */
	public static String getFileExp(String fileName) throws Exception{
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	/**
	 * 
	* 方法名:          copyFile
	* 方法功能描述:    拷贝源文件到指定位置
	* @param:         
	* @return:        
	* @Author:        周鑫
	 */
	public static void copyFile(File srcFile,File dstFile,int BUFFER_SIZE){
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			try{
				inputStream = new BufferedInputStream(new FileInputStream(srcFile),BUFFER_SIZE);
				outputStream = new BufferedOutputStream(new FileOutputStream(dstFile), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while(inputStream.read(buffer)>0){
					outputStream.write(buffer);
				}
			}finally{
				if(null!=inputStream){
					inputStream.close();
				}
				if(null!=outputStream){
					outputStream.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage(), e);
		}
	}
	
	
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static void deleteFile(String sPath) {
  
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }
}
