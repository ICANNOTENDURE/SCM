/**
 * 通过模板生成Blh 
 * template by liuyg
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.exception.DataBaseException;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContext;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.hop.HopIncDto;
import com.dhcc.pms.dto.sys.SysImpModelDto;
import com.dhcc.pms.entity.hop.HopInc;
import com.dhcc.pms.entity.manf.HopManf;
import com.dhcc.pms.entity.sys.ImpModel;
import com.dhcc.pms.entity.vo.hop.HopIncVo;
import com.dhcc.pms.service.hop.HopIncService;
import com.dhcc.pms.service.manf.HopManfService;
import com.dhcc.pms.service.sys.SysImpModelService;


@Component
public class HopIncBlh extends AbstractBaseBlh {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(HopIncBlh.class);

	@Resource
	private HopIncService hopIncService;
	
	@Resource
	private SysImpModelService sysImpModelService;
	
	@Resource
	private HopManfService hopManfService;
	
	@Resource
	private CommonService commonService;

	
	public HopIncBlh() {
		
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
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}
		//调用对应的service方法
		hopIncService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		if(dto.getHopInc().getIncId()==null||
				(dto.getHopInc().getIncId()).equals("")){
			dto.getHopInc().setIncId(null);			
			hopIncService.save(dto);		
		}else {
				
			hopIncService.update(dto);
		}	
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		
		//调用对应的service方法
		hopIncService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		
		//调用对应的service方法
		hopIncService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		
		//调用对应的service方法
		hopIncService.findById(dto);
		
	}
	
	public void getIncInfo(BusinessRequest res) throws Exception {
		
		HopIncDto dto = super.getDto(HopIncDto.class, res);
		
		List<HopIncVo> hopIncVos=new ArrayList<HopIncVo>();	
		hopIncVos=hopIncService.getIncInfo(dto);
		WebContext webContext = WebContextHolder.getContext();
		webContext.getResponse().getWriter().write(JsonUtils.toJson(hopIncVos));
			
	}
	
	//显示HopIncVo信息，即包括科室表中的指向医院Id对应的医院描述
	public void listInfo(BusinessRequest res) throws Exception {
			
		HopIncDto dto = super.getDto(HopIncDto.class, res);		
		if(dto.getPageModel() == null){
			dto.setPageModel(new PagerModel());
		}
		//调用对应的service方法
		hopIncService.getListInfo(dto);
		
//		List<ShowHopIncVo> hopIncVos=new ArrayList<ShowHopIncVo>();		
//		hopIncVos=hopIncService.getListInfo(dto);
//		WebContext webContext = WebContextHolder.getContext();
//		webContext.getResponse().getWriter().write(
//				"{\"total\":"
//						+ dto.getPageModel()
//								.getTotals()
//						+ ",\"rows\":"
//						+ JsonUtils.toJson(hopIncVos)
//						+ "}");
//				
	}
	
	
	
	/**
	 * 
	* @Title: HopIncBlh.java
	* @Description: TODO(导入药品)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月10日 下午2:37:46
	* @version V1.0
	 */
	public void upload(BusinessRequest res){
		
		HopIncDto dto = super.getDto(HopIncDto.class, res);

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
        SysImpModelDto.getImpModel().setType("INC");
        List<ImpModel> listImpModels=sysImpModelService.getModelList(SysImpModelDto);
        Map<Integer,String> modelMap=new HashMap<Integer,String>();
        for(int i=0;i<listImpModels.size();i++){
        	modelMap.put(Integer.valueOf(listImpModels.get(i).getSeq().toString()), listImpModels.get(i).getName());
        }
        //读取excel
        try {
			List<HopInc> hopIncs = new ArrayList<HopInc>();
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
				
				HopInc hopInc = new HopInc();
				for (int numCells = 0; numCells <= row.getLastCellNum(); numCells++) {
					cell = row.getCell(numCells);
					String colNameString=modelMap.get(numCells);
					if(StringUtils.isNullOrEmpty(colNameString)) {colNameString=" ";};
					switch (colNameString) {
						case "代码":
							if(cell!=null){
								hopInc.setIncCode(cell.toString());
							}
							break;
						case "名称":
							if(cell!=null){
								hopInc.setIncName(cell.toString());
							}
							break;
						case "规格":
							if(cell!=null){
								hopInc.setIncSpec(cell.toString());
							}
							break;
						case "入库单位":
							if(cell!=null){
								hopInc.setIncUomname(cell.toString());
							}
							break;
						case "进价":
							if(cell!=null){
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								hopInc.setIncRp(Math.round(cell.getNumericCellValue()));
							}
							break;
						case "产地":
							if(cell!=null){
								if(hopManfService.getIdByName(cell.toString())==null){
									HopManf manf=new HopManf();
									manf.setManfName(cell.toString());
									manf.setManfHisid(WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
									commonService.saveOrUpdate(manf);
									hopInc.setIncManfid(manf.getHopManfId());
								}else{
									hopInc.setIncManfid(hopManfService.getIdByName(cell.toString()));
								}
							}
							break;
						case "库存分类":
							if(cell!=null){
								hopInc.setIncCat(cell.toString());
							}
							break;
						case "别名":
							if(cell!=null){
								hopInc.setIncAliaS(cell.toString());
							}
							break;
						case "最小单位系数":
							if(cell!=null){
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);	
								hopInc.setIncFac(Math.round(cell.getNumericCellValue()));
							}
							break;
						case "售价":
							if(cell!=null){
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								hopInc.setIncSp(Math.round(cell.getNumericCellValue()));
							}
							break;	
					}	
				}
				hopIncs.add(hopInc);
			}
			
			dto.setHopIncs(hopIncs);
			hopIncService.saveInc(dto);

			
			//删除upload文件夹下的所有文件
			if(dstFile.isFile() || dstFile.list().length ==0)  {  
				dstFile.delete();       
			}else{      
				File[] tempFiles = dstFile.listFiles();  
				for (int i = 0; i < tempFiles.length; i++) {  
					tempFiles[i].delete();      
				}
			}

		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage(), e);
		}

	}
	
}
