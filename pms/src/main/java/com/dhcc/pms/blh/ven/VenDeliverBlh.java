/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.mail.EmailException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.exception.DataBaseException;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.util.SendMailUtil;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.sys.SysImpModelDto;
import com.dhcc.pms.dto.ven.VenDeliverDto;
import com.dhcc.pms.entity.hop.HopCtlocDestination;
import com.dhcc.pms.entity.ord.ExeState;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.sys.ImpModel;
import com.dhcc.pms.entity.userManage.NormalUser;
import com.dhcc.pms.entity.ven.VenDeliver;
import com.dhcc.pms.entity.ven.VenDeliveritm;
import com.dhcc.pms.entity.ven.Vendor;
import com.dhcc.pms.service.sys.SysImpModelService;
import com.dhcc.pms.service.ven.VenDeliverService;
import com.dhcc.pms.service.ven.VenIncService;


@Component
public class VenDeliverBlh extends AbstractBaseBlh {


	@Resource
	private VenDeliverService venDeliverService;
	
	@Resource
	private VenIncService venIncService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private SysImpModelService sysImpModelService;
	
	public VenDeliverBlh() {
		
	}
	
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(查询发货单)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午3:46:55
	* @version V1.0
	 */
	public void listDeliver(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.listDeliver(dto);
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(查询发货单明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午3:46:59
	* @version V1.0
	 */
	public void listDeliverItm(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.listDeliverItm(dto);
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(修改发货单明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午5:03:15
	* @version V1.0
	 */
	public void saveDeliverItm(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.saveDeliverItm(dto);
		dto.setOpFlg("1");
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(删除一条发货明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午5:28:40
	* @version V1.0
	 */
	public void delDeliverItm(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		
		VenDeliveritm venDeliveritm=commonService.get(VenDeliveritm.class, dto.getVenDeliveritm().getDeliveritmId());
		VenDeliver venDeliver=commonService.get(VenDeliver.class, venDeliveritm.getDeliveritmParentid());
		if(commonService.get(ExeState.class,venDeliver.getDeliverExestateid()).getStateId().toString().equals("6")){
			dto.setOpFlg("2");
			return;
		}
		commonService.delete(venDeliveritm);
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(保存发货单)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月19日 下午7:06:51
	* @version V1.0
	 */
	public void saveMain(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		VenDeliver venDeliver=commonService.get(VenDeliver.class, dto.getVenDeliver().getDeliverId());
		venDeliver.setDeliverArrdate(dto.getVenDeliver().getDeliverArrdate());
		venDeliver.setDeliverRemark(dto.getVenDeliver().getDeliverRemark());
		commonService.saveOrUpdate(venDeliver);
		dto.setOpFlg("1");
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(导入发票)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月20日 下午1:58:28
	* @version V1.0
	 */
	public void uploadAndroid(BusinessRequest res){
		
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);

		//生成随机文件名
		String newFileName =UUID.randomUUID().toString();
		//获取文件存储路径
		String storageFileName = ServletActionContext.getServletContext().getRealPath("/uploadtmps");
		//判断文件存储路径是否存在，若不存在则自动新建
		File document = new File(storageFileName);
		if (!document.exists()) {
			document.mkdir();
		}

		File dstFile = new File(storageFileName,newFileName); 
        com.dhcc.framework.util.FileUtils.copyFile(dto.getUpload(), dstFile,BaseConstants.BUFFER_SIZE);
        
        //
        SysImpModelDto SysImpModelDto=new SysImpModelDto();
        SysImpModelDto.setImpModel(new ImpModel());
        SysImpModelDto.getImpModel().setType("VENINVBYORDER");
        List<ImpModel> listImpModels=sysImpModelService.getModelList(SysImpModelDto);
        Map<Integer,String> modelMap=new HashMap<Integer,String>();
        for(int i=0;i<listImpModels.size();i++){
        	modelMap.put(Integer.valueOf(listImpModels.get(i).getSeq().toString()), listImpModels.get(i).getName());
        }

        //读取excel
        try {
        	dto.setOpFlg("1");
			//读取Excel文件
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			
			workbook = new HSSFWorkbook(new FileInputStream(storageFileName + File.separator + newFileName));
			sheet = workbook.getSheetAt(0);
			
			//明细
			quit:
			for (int numRows = 1; numRows <= sheet.getLastRowNum(); numRows++) {
				
				row = sheet.getRow(numRows);
				
				VenDeliveritm venDeliveritm = new VenDeliveritm();
				String orderNo="";
				for (int numCells = 0; numCells <= row.getLastCellNum(); numCells++) {
					cell = row.getCell(numCells);
					String colNameString=modelMap.get(numCells);
					if(StringUtils.isNullOrEmpty(colNameString)) {colNameString=" ";};
					switch (colNameString) {
						case "订单号":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								orderNo=cell.getStringCellValue();
							}
							break ;
						case "供应商药品代码":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								
								Long hopincid=venIncService.getHopIncByVenIncCode(cell.getStringCellValue());
								if(hopincid==null){
									dto.setOpFlg("2");
									if(StringUtils.isNullOrEmpty(dto.getMsg())){
										dto.setMsg(cell.getStringCellValue());
									}else{
										dto.setMsg(dto.getMsg()+","+cell.getStringCellValue());
									}
									break quit;
								}else{
									venDeliveritm.setDeliveritmHopincid(hopincid);
								}
							}
							break;
						case "发票":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venDeliveritm.setDeliveritmInvnoe(cell.getStringCellValue());
							}
							break;
						case "数量":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								venDeliveritm.setDeliveritmQty((float)cell.getNumericCellValue());
							}
							break;
						case "批号":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venDeliveritm.setDeliveritmBatno(cell.getStringCellValue());
							}
							break;
						case "效期":
							if(cell!=null){
								venDeliveritm.setDeliveritmExpdate(cell.getDateCellValue());
							}
							break;
						case "进价":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								venDeliveritm.setDeliveritmRp((float)cell.getNumericCellValue());
							}
							break;
					}	
				}
				if(orderNo.equals("")){
					break quit;
				}
				if(dto.getOrderMap()!=null&&dto.getOrderMap().containsKey(orderNo)){
					dto.getOrderMap().get(orderNo).add(venDeliveritm);
				}else{
					List<VenDeliveritm> venDeliveritms2=new ArrayList<VenDeliveritm>();
					venDeliveritms2.add(venDeliveritm);
					dto.getOrderMap().put(orderNo,venDeliveritms2);
				}
			}
			if(!dto.getOpFlg().equals("1")){
				WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));;
				return;
			}
			venDeliverService.impByOrder(dto);
			workbook=null;
			//删除upload文件夹下的所有文件
			if(dstFile.isFile() || dstFile.list().length ==0)  {  
				dstFile.delete();       
			}else{      
				File[] tempFiles = dstFile.listFiles();  
				for (int i = 0; i < tempFiles.length; i++) {  
					tempFiles[i].delete();      
				}
			}
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage(), e);
		}

	}
	
	
public void upload(BusinessRequest res){
		
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);

		//生成随机文件名
		String newFileName =UUID.randomUUID().toString();
		//获取文件存储路径
		String storageFileName = ServletActionContext.getServletContext().getRealPath("/uploadtmps");
		//判断文件存储路径是否存在，若不存在则自动新建
		File document = new File(storageFileName);
		if (!document.exists()) {
			document.mkdir();
		}

		File dstFile = new File(storageFileName,newFileName); 
        com.dhcc.framework.util.FileUtils.copyFile(dto.getUpload(), dstFile,BaseConstants.BUFFER_SIZE);
        
        //
        SysImpModelDto SysImpModelDto=new SysImpModelDto();
        SysImpModelDto.setImpModel(new ImpModel());
        SysImpModelDto.getImpModel().setType("VENINV");
        List<ImpModel> listImpModels=sysImpModelService.getModelList(SysImpModelDto);
        Map<Integer,String> modelMap=new HashMap<Integer,String>();
        for(int i=0;i<listImpModels.size();i++){
        	modelMap.put(Integer.valueOf(listImpModels.get(i).getSeq().toString()), listImpModels.get(i).getName());
        }

        //读取excel
        try {
        	dto.setOpFlg("1");
			List<VenDeliveritm> venDeliveritms = new ArrayList<VenDeliveritm>();
			//读取Excel文件
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			
			workbook = new HSSFWorkbook(new FileInputStream(storageFileName + File.separator + newFileName));
			sheet = workbook.getSheetAt(0);
			
			//明细
			for (int numRows = 1; numRows <= sheet.getLastRowNum(); numRows++) {
				
				row = sheet.getRow(numRows);
				
				VenDeliveritm venDeliveritm = new VenDeliveritm();
				for (int numCells = 0; numCells <= row.getLastCellNum(); numCells++) {
					cell = row.getCell(numCells);
					String colNameString=modelMap.get(numCells);
					if(StringUtils.isNullOrEmpty(colNameString)) {colNameString=" ";};
					switch (colNameString) {
						case "供应商药品代码":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								
								Long hopincid=venIncService.getHopIncByVenIncCode(cell.getStringCellValue());
								if(hopincid==null){
									dto.setOpFlg("2");
									if(StringUtils.isNullOrEmpty(dto.getMsg())){
										dto.setMsg(cell.getStringCellValue());
									}else{
										dto.setMsg(dto.getMsg()+","+cell.getStringCellValue());
									}
								}else{
									venDeliveritm.setDeliveritmHopincid(hopincid);
								}
							}
							break;
						case "发票":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venDeliveritm.setDeliveritmInvnoe(cell.getStringCellValue());
							}
							break;
						case "数量":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								venDeliveritm.setDeliveritmQty((float)cell.getNumericCellValue());
							}
							break;
						case "批号":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venDeliveritm.setDeliveritmBatno(cell.getStringCellValue());
							}
							break;
						case "效期":
							if(cell!=null){
								venDeliveritm.setDeliveritmExpdate(cell.getDateCellValue());
							}
							break;
						case "进价":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								venDeliveritm.setDeliveritmRp((float)cell.getNumericCellValue());
							}
							break;
					}	
				}
				venDeliveritms.add(venDeliveritm);
			}
			if(!dto.getOpFlg().equals("1")){
				WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));;
				return;
			}
			dto.setVenDeliveritms(venDeliveritms);
			venDeliverService.impInv(dto);
			workbook=null;
			//删除upload文件夹下的所有文件
			if(dstFile.isFile() || dstFile.list().length ==0)  {  
				dstFile.delete();       
			}else{      
				File[] tempFiles = dstFile.listFiles();  
				for (int i = 0; i < tempFiles.length; i++) {  
					tempFiles[i].delete();      
				}
			}
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage(), e);
		}

	}

	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(发货)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月24日 上午11:44:37
	* @version V1.0
	 */
	public void sendDeliver(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.deliver(dto);
		//发送邮件
		String sub="发货通知";
		StringBuffer msg = new StringBuffer();
		String address="";
		VenDeliver VenDeliver=commonService.get(VenDeliver.class,dto.getVenDeliver().getDeliverId());
		HopCtlocDestination ctlocDestination=commonService.get(HopCtlocDestination.class,VenDeliver.getDeliverDestinationid());
		NormalUser normalUser=commonService.get(NormalUser.class, Long.valueOf(ctlocDestination.getContact()));
		Order order=commonService.get(Order.class, VenDeliver.getDeliverOrderid());
		Vendor vendor=commonService.get(Vendor.class, VenDeliver.getDeliverVendorid());
		if(normalUser!=null){
			address=normalUser.getEmail();
		}
		if(!StringUtils.isNullOrEmpty(address)){
			address=address+BaseConstants.COMMA+ctlocDestination.getMail();
		}else{
			address=ctlocDestination.getMail();
		}
		msg.append("您的订单:<h1>"+order.getOrderNo()+"</h1>已经发货。");
		if(VenDeliver.getDeliverArrdate()!=null){
			msg.append("<br>预计送达时间:"+new SimpleDateFormat("yyyy-mm-dd").format(VenDeliver.getDeliverArrdate()));
		}
		msg.append("<br>收货地址:"+ctlocDestination.getDestination());
		msg.append("<br>请注意收货.");
		msg.append("<br><br><br><br><br><div 'float:right'>供应商:"+vendor.getName()+"</div>");
		msg.append("<br>地址:"+vendor.getAddress());
		msg.append("<br>传真:"+vendor.getFax());
		msg.append("<br>电话:"+vendor.getTel());
		try {
			if(!StringUtils.isNullOrEmpty(address)){
				SendMailUtil.sendEmail(sub, msg.toString(),address,60 * 1000);
			}
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(删除发货单)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月24日 下午4:57:24
	* @version V1.0
	 */
	public void delete(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.delete(dto);
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(取消发货状态)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月24日 下午4:57:21
	* @version V1.0
	 */
	public void cancelComplete(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.cancelComplete(dto);
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月26日 下午2:22:48
	* @version V1.0
	 */
	public void createDelByOrder(BusinessRequest res){
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		venDeliverService.AccectOrder(dto);
	}
	
	/**
	 * 
	* @Title: VenDeliverBlh.java
	* @Description: TODO(查询所有订单明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月27日 下午2:42:23
	* @version V1.0
	 * @throws IOException 
	 */
	public void getDeliveritms(BusinessRequest res) throws IOException{
		VenDeliverDto dto = super.getDto(VenDeliverDto.class, res);
		WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(venDeliverService.getDeliveritms(dto.getVenDeliver().getDeliverId())));
	}
}
