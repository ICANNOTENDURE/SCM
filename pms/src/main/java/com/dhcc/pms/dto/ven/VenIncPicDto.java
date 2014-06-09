/**
 * Dto 
 * template by zxx
 */
package com.dhcc.pms.dto.ven;

import java.io.File;
import java.util.List;

import com.dhcc.pms.entity.ven.VenInc;
import com.dhcc.pms.entity.ven.VenIncPic;
import com.dhcc.pms.entity.vo.ven.VenIncPicVo;
import com.dhcc.framework.transmission.dto.BaseDto;

public class VenIncPicDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private VenIncPic venIncPic;
	private List<VenInc> venIncs;
	private File upload;
	// 封装上传文件类型的属性  
	private String contentType;  
	// 封装上传文件名的属性  
	private String uploadFileName;  
	// 封装上传文件名的属性  
	private String storgeFileName;
	
	private List<VenIncPicVo> venIncPicVos;
	
	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getStorgeFileName() {
		return storgeFileName;
	}


	public void setStorgeFileName(String storgeFileName) {
		this.storgeFileName = storgeFileName;
	}


	public VenIncPic getVenIncPic() {
		return venIncPic;
	}

	
	public void setVenIncPic(VenIncPic venIncPic) {
		this.venIncPic = venIncPic;
	}


	/**
	 * @return the venIncs
	 */
	public List<VenInc> getVenIncs() {
		return venIncs;
	}


	/**
	 * @param venIncs the venIncs to set
	 */
	public void setVenIncs(List<VenInc> venIncs) {
		this.venIncs = venIncs;
	}


	/**
	 * @return the venIncPicVos
	 */
	public List<VenIncPicVo> getVenIncPicVos() {
		return venIncPicVos;
	}


	/**
	 * @param venIncPicVos the venIncPicVos to set
	 */
	public void setVenIncPicVos(List<VenIncPicVo> venIncPicVos) {
		this.venIncPicVos = venIncPicVos;
	}
	
}
