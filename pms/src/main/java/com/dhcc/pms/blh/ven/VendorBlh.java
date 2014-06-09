/**
 * 通过模板生成Blh 
 * template by liuyg
 */
package com.dhcc.pms.blh.ven;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.exception.DataBaseException;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.web.context.WebContext;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.ven.VendorDto;
import com.dhcc.pms.entity.ven.VenQualifPic;
import com.dhcc.pms.entity.ven.VenQualifType;
import com.dhcc.pms.entity.ven.VenQualification;
import com.dhcc.pms.entity.vo.ven.VenQualifTypeVO;
import com.dhcc.pms.service.ven.VendorService;
import com.fasterxml.jackson.core.type.TypeReference;


@Component
public class VendorBlh extends AbstractBaseBlh {


	@Resource
	private VendorService vendorService;
	
	@Resource
	private CommonService commonService;
	
	//最大缓存空间
	private static final int BUFFER_SIZE = 16 * 1024; 
	

		
	public VendorBlh() {
		
	}
	
	/**
	 * 进入某个列表的入口方法
	 * 列表方法，也就是查询方法，调用的时候不需要xxxCtrl!list
	 * 框架 在不调Ctrl时，不指定方法，就默认为它list，在action中通过
	 * json注解，所dto中的pageModel to json
	 * @param: res
	 *  
	 */
	public void list(BusinessRequest res) {
	
		VendorDto dto = super.getDto(VendorDto.class, res);
		
		//调用对应的service方法
		vendorService.list(dto);
	}
	
	/**
	 * 
	* @Title: VendorBlh.java
	* @Description: TODO(保存供应商)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月9日 上午9:36:31
	* @version V1.0
	 */
	public void save(BusinessRequest res) {
	
		VendorDto dto = super.getDto(VendorDto.class, res);
		

		List<VenQualification> venQualificationList=JsonUtils.toObject(dto.getVenQualificationList(), new TypeReference<List<VenQualification>>() { });

		dto.getVendor().setVenQualificationList(venQualificationList);
		vendorService.saveOrUpdate(dto);
		dto.setMessage("保存成功");
		dto.setSuccess(true);
	}
	
	
	/**
	 * 
	* @Title: VendorBlh.java
	* @Description: TODO(返回供应商列表下拉列表)
	* @param res
	* @throws IOException
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月9日 上午9:35:54
	* @version V1.0
	 */
	public void getVenCombox(BusinessRequest res) throws IOException {
		VendorDto dto = super.getDto(VendorDto.class, res);
		if(dto.getVendor()!=null){
			dto.getVendor().setName(new String(dto.getVendor().getName().getBytes("ISO8859-1"), "UTF-8").trim());
		}
		WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(vendorService.findVenComboList(dto)));
	}
	
	/**
	* 
	* @Title: VendorBlh.java
	* @Description: TODO(删除供应商)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月9日 上午9:35:22
	* @version V1.0
	 */
	public void delete(BusinessRequest res) {
	
		VendorDto dto = super.getDto(VendorDto.class, res);
		//调用对应的service方法
		vendorService.delete(dto);
	}
	
	/**
	 * 
	* @Title: VendorBlh.java
	* @Description: TODO(进入增加页面)
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月9日 上午9:32:09
	* @version V1.0
	 */
	public void listMain(BusinessRequest res) {
		
		VendorDto dto = super.getDto(VendorDto.class, res);
		List<VenQualifType> venQualifTypeList=commonService.getAll(VenQualifType.class,"seq",true);
		List<VenQualifTypeVO> venQualifTypeVOList=new ArrayList<>();
		for(int i=0;i<venQualifTypeList.size();i++){
			VenQualifTypeVO venQualifTypeVO =new VenQualifTypeVO();
			venQualifTypeVO.setName(venQualifTypeList.get(i).getName());
			venQualifTypeVO.setType(venQualifTypeList.get(i).getVenQualifTypeId());
			venQualifTypeVOList.add(venQualifTypeVO);
		}
		dto.setVenQualifTypeVOList(venQualifTypeVOList);
		
	}
	
	/**
	 * 
	* 方法名:          delUpload
	* 方法功能描述:      删除文件
	* @param:         
	* @return:        
	* @Author:        周鑫
	* @Create Date:   2014年04月30日
	 */
	public void deleteUpload(BusinessRequest res) {
		VendorDto dto = super.getDto(VendorDto.class, res);
		//删除upload文件夹下的所有文件
		VenQualifPic venQualifPic=commonService.get(VenQualifPic.class,Long.valueOf(dto.getVenQualifPicId()));
		String storePathString=ServletActionContext.getServletContext().getRealPath("/uploads")+"\\"+venQualifPic.getPath();
		File tempFile = new File(storePathString);
		if(tempFile.isFile() || tempFile.list().length ==0)  {  
			tempFile.delete();       
		}else{      
			File[] tempFiles = tempFile.listFiles();  
			for (int i = 0; i < tempFiles.length; i++) {  
				tempFiles[i].delete();      
			}
		}
		VenQualifPic VenQualifPic=new VenQualifPic();
		VenQualifPic.setId(Long.valueOf(dto.getVenQualifPicId()));
		commonService.delete(VenQualifPic);
		dto.setMessage("保存成功");
		dto.setSuccess(true);
	}
	/**
	 * 
	* 方法名:          upload
	* 方法功能描述:    上传文件到upload文件夹
	* @param:         
	* @return:        
	* @Author:        周鑫
	* @Create Date:   2014年04月15日
	 */
	public void upload(BusinessRequest res) throws Exception{
		VendorDto dto = super.getDto(VendorDto.class, res);
		Date date = new Date();
		SimpleDateFormat smDateFormat=new SimpleDateFormat("yyyy-MM-dd+HH-mm-ss");

		//生成随机文件名
		String newFileName =smDateFormat.format(date) +UUID.randomUUID()+ this.getFileExp(dto.getUploadFileName());
		//获取文件存储路径
		String storageFileName = ServletActionContext.getServletContext().getRealPath("/uploads");
		//判断文件存储路径是否存在，若不存在则自动新建
		File document = new File(storageFileName);
		if (!document.exists()) {
			document.mkdir();
		}
		dto.setStorgeFileName(newFileName);
		File dstFile = new File(storageFileName,newFileName); 
        this.copyFile(dto.getUpload(), dstFile);
        
        vendorService.saveOrUpdatePic(dto);
        WebContext context=WebContextHolder.getContext();
        
        context.getResponse().getWriter().write(dto.getVendor().getVendorId().toString());
	}
	/**
	 * 
	* 方法名:          getFileExp
	* 方法功能描述:     获取文件的后缀名
	* @param:         
	* @return:        
	* @Author:        周鑫
	 */
	private String getFileExp(String fileName) throws Exception{
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
	private void copyFile(File srcFile,File dstFile){
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
	
	public void findById(BusinessRequest res) throws IOException {
		
		VendorDto dto = super.getDto(VendorDto.class, res);
		
		//调用对应的service方法
		commonService.get(VendorBlh.class, dto.getVendor().getVendorId());
		WebContextHolder.getContext().getResponse().getWriter().write(dto.getVendor().getName());
	}
}
