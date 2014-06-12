/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.hop;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

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
import com.dhcc.framework.util.PingYinUtil;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.hop.HopVendorDto;
import com.dhcc.pms.dto.sys.SysImpModelDto;
import com.dhcc.pms.entity.hop.HopVendor;
import com.dhcc.pms.entity.sys.ImpModel;
import com.dhcc.pms.service.hop.HopVendorService;
import com.dhcc.pms.service.sys.SysImpModelService;


@Component
public class HopVendorBlh extends AbstractBaseBlh {


	@Resource
	private HopVendorService hopVendorService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private SysImpModelService sysImpModelService;
	
	public HopVendorBlh() {
		
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
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		//hopVendorService.save(dto);
		if(StringUtils.isNullOrEmpty(dto.getHopVendor().getHopAlias())){
			dto.getHopVendor().setHopAlias(PingYinUtil.getFirstSpell(dto.getHopVendor().getHopName()));
		}
		hopVendorService.save(dto);
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.findById(dto);
		
	}
	/**
	 * 
	* @Title: HopVendorBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 上午10:08:12
	* @version V1.0
	 */
	public void listHopCon(BusinessRequest res){
		
		
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		
		//调用对应的service方法
		hopVendorService.listHopCon(dto);
	}
	
	/**
	 * 
	* @Title: HopVendorBlh.java
	* @Description: TODO(对照或者删除对照关系)
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 上午10:55:26
	* @version V1.0
	 */
	public void contranstVendor(BusinessRequest res){
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		HopVendor hopVendor=new HopVendor();
		if(dto.getHopVendor().getHopVendorId()!=null){
			hopVendor=commonService.get(HopVendor.class, dto.getHopVendor().getHopVendorId());
		}
		if(dto.getHopVendor().getHopVenId()==null){
			hopVendor.setHopVenId(null);;
		}else{
			hopVendor.setHopVenId(dto.getHopVendor().getHopVenId());
		}	
		commonService.saveOrUpdate(hopVendor);
		dto.setOpFlg("1");
	}
	
	
	/**
	 * 
	* @Title: HopVendorBlh.java
	* @Description: TODO(导入医院供应商)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月12日 上午11:48:03
	* @version V1.0
	 */
	public void upload(BusinessRequest res){
		
		HopVendorDto dto = super.getDto(HopVendorDto.class, res);
		dto.setExportFlag("1");
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
        SysImpModelDto.getImpModel().setType("HOPVENDOR");
        List<ImpModel> listImpModels=sysImpModelService.getModelList(SysImpModelDto);
        Map<Integer,String> modelMap=new HashMap<Integer,String>();
        for(int i=0;i<listImpModels.size();i++){
        	modelMap.put(Integer.valueOf(listImpModels.get(i).getSeq().toString()), listImpModels.get(i).getName());
        }
        //读取excel
        try {
			List<HopVendor> hopVendors = new ArrayList<HopVendor>();
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
				
				HopVendor hopVendor = new HopVendor();
				for (int numCells = 0; numCells <= row.getLastCellNum(); numCells++) {
					cell = row.getCell(numCells);
					String colNameString=modelMap.get(numCells);
					if(StringUtils.isNullOrEmpty(colNameString)) {colNameString=" ";};
					switch (colNameString) {
						case "代码":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								hopVendor.setHopCode(cell.toString());
							}
							break;
						case "名称":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								hopVendor.setHopName(cell.toString());
							}
							break;
						case "类别":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								hopVendor.setHopType(cell.toString());
							}
							break;
						case "别名":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								hopVendor.setHopAlias(cell.toString());
							}
							break;
						case "地址":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								hopVendor.setHopAddress(cell.toString());
							}
							break;
						case "电话":
							if(cell!=null){
								hopVendor.setHopTel(cell.toString());
							}
							break;
						case "邮箱":
							if(cell!=null){
								hopVendor.setHopEmail(cell.toString());
							}
							break;
						case "发货地点":
							if(cell!=null){
								hopVendor.setHopSend(cell.toString());
							}
							break;	
						case "联系人":
							if(cell!=null){
								hopVendor.setHopContact(cell.toString());
							}
							break;	
					}	
				}
				hopVendor.setHopHopId(WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
				hopVendors.add(hopVendor);
			}
			
			dto.setHopVendors(hopVendors);
			hopVendorService.exportVendor(dto);
			
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
			WebContextHolder.getContext().getResponse().getWriter().write("1");;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage(), e);
		}

	}
}
