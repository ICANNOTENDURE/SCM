/**
 * 通过模板生成Action 
 * template by zxx
 */
package com.dhcc.pms.web.ord.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.dhcc.framework.util.FileUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.entity.vo.ord.ExportOrderVo;
import com.dhcc.pms.service.ord.OrderService;
import com.opensymphony.xwork2.ActionSupport;


@Namespace(value = "/ord")
@Scope("prototype")
@Action(value = "downLoadOrderCtrl", results = {
		@Result(name = "downLoadOrder",type = "stream", 
		params = { "contentType","application/octet-stream;charset=UTF-8", 
		           "inputName", "inputStream",
	               "contentDisposition", "attachment;filename=\"${downloadFileName}\""
	             }
		)})
@InterceptorRefs(value = { @InterceptorRef("fileUploadStack") })


public class DownLoadOrderAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private OrderService orderService;
	
	private InputStream inputStream;
	
	private String downloadFileName;
	
	
	
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the downloadFileName
	 */
	public String getDownloadFileName() {
		return downloadFileName;
	}

	/**
	 * @param downloadFileName the downloadFileName to set
	 */
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public String execute(){  
		return SUCCESS;  
    }
	
	/**
	 * 
	* @Title: DownLoadOrderAction.java
	* @Description: TODO(下载订单)
	* @return
	* @return:String 
	* @author zhouxin  
	* @date 2014年6月18日 上午10:54:41
	* @version V1.0
	 */
	public String downLoadOrder(){
		
		downloadFileName=UUID.randomUUID().toString()+".xls";
		String outputFile = ServletActionContext.getServletContext().getRealPath("/downloads")+File.separator+downloadFileName;

       
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();  
		
		sheet.protectSheet("123");
		HSSFCell cell=null;
		HSSFRow row=null;
		
		row = sheet.createRow(0);        //创建第一行
		
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("订单号");
		
		cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("入库科室");
		
		cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("收货科室");
		
		cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("供应商药品标识");
		
		cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("药品名称");
		
		cell = row.createCell(5, HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue("数量");
		
		cell = row.createCell(6, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("单位");
		
		cell = row.createCell(7, HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue("进价");
		
		cell = row.createCell(8, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("医院标识");
		
		cell = row.createCell(9, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("医院药品名称");
		List<ExportOrderVo> exportOrderVos=orderService.ExportOrder(Long.valueOf(WebContextHolder.getContext().getRequest().getParameter("orderId")));
		int i=1;
		for(ExportOrderVo tmpExportOrderVo:exportOrderVos){
			row = sheet.createRow(i);        //创建第一行
			
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tmpExportOrderVo.getNo());
			
			cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tmpExportOrderVo.getPurloc());
			
			cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tmpExportOrderVo.getRecloc());
			
			cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tmpExportOrderVo.getVenincid());
			
			cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tmpExportOrderVo.getVenincname());
			
			cell = row.createCell(5, HSSFCell.CELL_TYPE_NUMERIC);
			if(tmpExportOrderVo.getQty()!=null){
				cell.setCellValue(tmpExportOrderVo.getQty());
			}
			cell = row.createCell(6, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tmpExportOrderVo.getUom());
			
			cell = row.createCell(7, HSSFCell.CELL_TYPE_NUMERIC);
			if(tmpExportOrderVo.getRp()!=null){
				cell.setCellValue(tmpExportOrderVo.getRp());
			}
			cell = row.createCell(8, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tmpExportOrderVo.getHopincid());
			
			cell = row.createCell(9, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tmpExportOrderVo.getHopincname());
			++i;
		}
		
		try {
			FileOutputStream fOut = new FileOutputStream(outputFile);
			// 把相应的Excel 工作簿存盘
			workbook.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
			
			inputStream = new FileInputStream(new File(outputFile));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			FileUtils.deleteFile(outputFile);
		}
		return "downLoadOrder";
	}
	

	
}
