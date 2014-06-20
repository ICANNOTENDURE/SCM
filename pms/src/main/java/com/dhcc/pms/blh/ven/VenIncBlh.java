/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ven;

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
import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.exception.DataBaseException;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.util.PingYinUtil;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContext;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.sys.SysImpModelDto;
import com.dhcc.pms.dto.ven.VenIncDto;
import com.dhcc.pms.entity.manf.HopManf;
import com.dhcc.pms.entity.sys.ImpModel;
import com.dhcc.pms.entity.ven.VenHopInc;
import com.dhcc.pms.entity.ven.VenInc;
import com.dhcc.pms.entity.ven.Vendor;
import com.dhcc.pms.service.manf.HopManfService;
import com.dhcc.pms.service.sys.SysImpModelService;
import com.dhcc.pms.service.ven.VenIncService;
import com.dhcc.pms.service.ven.VendorService;


@Component
public class VenIncBlh extends AbstractBaseBlh {


	@Resource
	private VenIncService venIncService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private SysImpModelService sysImpModelService;
	
	@Resource
	private HopManfService hopManfService;
	
	@Resource
	private VendorService vendorService;


	
	public VenIncBlh() {
		
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
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}
		//调用对应的service方法
		venIncService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		if(StringUtils.isNullOrEmpty(dto.getVenInc().getVenIncAlias())){
			dto.getVenInc().setVenIncAlias(PingYinUtil.getFirstSpell(dto.getVenInc().getVenIncName()));
		}
		if(dto.getVenInc().getVenIncId()==null||
				(dto.getVenInc().getVenIncId()).equals("")){
			dto.getVenInc().setVenIncId(null);						
			venIncService.save(dto);		
		}else {
				
			venIncService.update(dto);
		}	
		
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		
		//调用对应的service方法
		venIncService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		
		//调用对应的service方法
		venIncService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		
		//调用对应的service方法
		venIncService.findById(dto);
		
	}
	
	public void getIncInfo(BusinessRequest res) throws Exception {
		
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		
		List<VenInc> venIncs=new ArrayList<VenInc>();
		venIncs=venIncService.getIncInfo(dto);
		WebContext webContext = WebContextHolder.getContext();
		webContext.getResponse().getWriter().write(JsonUtils.toJson(venIncs));
			
	}
		
	//显示VenIncVo信息，即包括科室表中的指向医院Id对应的医院描述
	public void listInfo(BusinessRequest res)  {			
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}
		if(dto.getVenInc()==null){
			dto.setVenInc(new VenInc());
		}
		dto.getVenInc().setVenIncName(dto.getComgridparam());

		venIncService.getListInfo(dto);

				
	}
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(药品对照)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月30日 上午9:03:53
	* @version V1.0
	 */
	public void listContrantInc(BusinessRequest res){
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		venIncService.listContrantInc(dto);
	}
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(保存药品关联)
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月30日 下午5:23:46
	* @version V1.0
	 */
	public void saveContranst(BusinessRequest res){
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		venIncService.saveContranst(dto);
	}
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(列出供应商对照药品)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 下午2:44:25
	* @version V1.0
	 */
	public void listVenContranst(BusinessRequest res){
		
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		venIncService.listVenContranst(dto);
	}
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 下午4:36:33
	* @version V1.0
	 */
	public void saveContranstInc(BusinessRequest res){
		
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		commonService.saveOrUpdate(dto.getVenHopInc());
		dto.setOpFlg("1");
	}
	
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(删除对照)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 下午4:49:07
	* @version V1.0
	 */
	public void deleteContranstInc(BusinessRequest res){
		
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		commonService.delete(VenHopInc.class, dto.getVenHopInc().getVenHopIncId());
		dto.setOpFlg("1");
	}
	
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(修改系数)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月11日 下午6:02:45
	* @version V1.0
	 */
	public void updateContranstInc(BusinessRequest res){
		
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		VenHopInc venHopInc=commonService.get(VenHopInc.class, dto.getVenHopInc().getVenHopIncId());
		venHopInc.setVenIncFac(dto.getVenHopInc().getVenIncFac());
		commonService.saveOrUpdate(venHopInc);
		dto.setOpFlg("1");
	}
	
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(导入供应商药品)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月12日 下午1:53:30
	* @version V1.0
	 */
	public void upload(BusinessRequest res){
		
		VenIncDto dto = super.getDto(VenIncDto.class, res);

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
        SysImpModelDto.getImpModel().setType("VENINC");
        List<ImpModel> listImpModels=sysImpModelService.getModelList(SysImpModelDto);
        Map<Integer,String> modelMap=new HashMap<Integer,String>();
        for(int i=0;i<listImpModels.size();i++){
        	modelMap.put(Integer.valueOf(listImpModels.get(i).getSeq().toString()), listImpModels.get(i).getName());
        }
        Map<String,String> manfMap = new HashMap<String,String>();
        Map<String,String> venMap = new HashMap<String,String>();
        String manfId="";
        String venId="";
        //读取excel
        try {
			List<VenInc> venIncs = new ArrayList<VenInc>();
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
				
				VenInc venInc = new VenInc();
				for (int numCells = 0; numCells <= row.getLastCellNum(); numCells++) {
					cell = row.getCell(numCells);
					String colNameString=modelMap.get(numCells);
					if(StringUtils.isNullOrEmpty(colNameString)) {colNameString=" ";};
					switch (colNameString) {
						case "产品ID":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venInc.setVenIncCode(cell.toString());
							}
							break;
						case "药品名称":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venInc.setVenIncName(cell.toString());
								venInc.setVenIncAlias(PingYinUtil.getFirstSpell(cell.toString()));
							}
							break;
						case "规格":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venInc.setVenIncSpec(cell.toString());
							}
							break;
						case "生产厂家":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								
								manfId=manfMap.get(cell.toString());
								if(StringUtils.isNullOrEmpty(manfId)){
									Long tmpmanfid = hopManfService.getIdByName(cell.toString());
									if(tmpmanfid==null){
										HopManf manf=new HopManf();
										manf.setManfName(cell.toString());
										manf.setManfHisid(WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
										commonService.saveOrUpdate(manf);
										venInc.setVenIncManfid(manf.getHopManfId());
										manfMap.put(cell.toString(),manf.getHopManfId().toString());
									}else{
										venInc.setVenIncManfid(tmpmanfid);
										manfMap.put(cell.toString(),tmpmanfid.toString());
									}
									tmpmanfid=null;
								}else{
									venInc.setVenIncManfid(Long.valueOf(manfId));
								}
								manfId="";
							}
							break;
						case "供应商":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venId=venMap.get(cell.toString());
								if(StringUtils.isNullOrEmpty(venId)){
									Long vendorIdLong=vendorService.findVendorIdByName(cell.toString());
									if(vendorIdLong==null){
										Vendor hopVendor=new Vendor();
										hopVendor.setName(cell.toString());
										commonService.saveOrUpdate(hopVendor);
										venInc.setVenIncVenid(hopVendor.getVendorId());
										venMap.put(cell.toString(), hopVendor.getVendorId().toString());
									}else{
										venInc.setVenIncVenid(vendorIdLong);
										venMap.put(cell.toString(), vendorIdLong.toString());
									}
								}else{
									venInc.setVenIncVenid(Long.valueOf(venId));
								}
								venId="";
							}
							break;
						
					}	
				}
				venIncs.add(venInc);
			}
			
			dto.setVenIncs(venIncs);
			venIncService.exportVenInc(dto);
			
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
			
			dto.setVenIncs(null);
			dto.setOpFlg("1");
			manfMap=null;
			venMap=null;
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage(), e);
		}

	}
}
