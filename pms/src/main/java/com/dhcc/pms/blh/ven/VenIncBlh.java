/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
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
import com.dhcc.pms.entity.userManage.NormalAccount;
import com.dhcc.pms.entity.ven.VenHopInc;
import com.dhcc.pms.entity.ven.VenInc;
import com.dhcc.pms.entity.ven.VenIncPic;
import com.dhcc.pms.entity.vo.ws.VenIncItmWeb;
import com.dhcc.pms.service.hop.HopIncService;
import com.dhcc.pms.service.hop.HopVendorService;
import com.dhcc.pms.service.manf.HopManfService;
import com.dhcc.pms.service.sys.SysImpModelService;
import com.dhcc.pms.service.userManage.NormalAccountService;
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

	@Resource
	private NormalAccountService normalAccountService;
	
	@Resource
	private HopIncService hopIncService;

	@Resource
	private HopVendorService hopVendorService;
	
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
		if((dto.getVenInc().getVenIncVenid()==null)||(dto.getVenInc().getVenIncVenid().toString().trim().equals(""))){
			dto.getVenInc().setVenIncVenid(WebContextHolder.getContext().getVisit().getUserInfo().getVendorIdLong());
		}
		commonService.saveOrUpdate(dto.getVenInc());
		dto.setOpFlg("1");
		dto.setMsg("success");
		
	}
	
	//删除
	public void delete(BusinessRequest res) throws IOException {
	
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		VenInc venInc=commonService.get(VenInc.class, dto.getVenInc().getVenIncId());
		List<VenIncPic> incPics=commonService.findByProperty(VenIncPic.class, "venIncPicVenincid", dto.getVenInc().getVenIncId());
		for(VenIncPic venIncPic:incPics){
			commonService.delete(venIncPic);
			File file=new File(ServletActionContext.getServletContext().getRealPath("/uploadPic"),venIncPic.getVenIncPicPath());
			if(file.exists()){
				FileUtils.forceDelete(file);
			}
		}
		commonService.delete(venInc);
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
			dto.getVenInc().setVenIncName(dto.getComgridparam());
		}
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
		if(!StringUtils.isNullOrEmpty(dto.getComgridparam())){
			if(dto.getVenInc()!=null){
				dto.getVenInc().setVenIncAlias(dto.getComgridparam());
			}else{
				dto.setVenInc(new VenInc());
				dto.getVenInc().setVenIncAlias(dto.getComgridparam());
			}
		}
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
		venHopInc.setVenFac(dto.getVenHopInc().getVenFac());
		venHopInc.setHopFac(dto.getVenHopInc().getHopFac());
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
	 * @throws IOException 
	 */
	public void upload(BusinessRequest res) throws IOException{
		
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		dto.setMsg("<br>");
		Long vendorId=WebContextHolder.getContext().getVisit().getUserInfo().getVendorIdLong();
		int flag=1;
		int successNum=0;
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
        String manfId="";
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
						case "药品代码":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venInc.setVenIncCode(cell.toString());
							}
							break;
						case "药品名称":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venInc.setVenIncName(cell.toString());
							}
							break;
						case "规格":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venInc.setVenIncSpec(cell.toString());
							}
							break;
						case "进价":
							if(cell!=null){
								//cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								venInc.setVenIncPrice((float)cell.getNumericCellValue());
							}
							break;
						case "单位描述":
							if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venInc.setVenIncUomname(cell.toString());
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
						
					}	
				}
				if(!StringUtils.isNullOrEmpty(venInc.getVenIncCode())){
					if(venIncService.getVenIncByCode(venInc.getVenIncCode(),vendorId)==null){
						venInc.setVenIncVenid(vendorId);
						venIncs.add(venInc);
						
						successNum=successNum+1;
					}else{
						flag=0;
						dto.setOpFlg("-2");
						dto.setMsg(dto.getMsg()+"<br>第"+numRows+"药品代和系统重复");
					};
				}else{
					flag=0;
					dto.setOpFlg("-2");
					dto.setMsg(dto.getMsg()+"<br>第"+numRows+"药品代码不能为空");
				}
				
				
			}
			workbook=null;
			manfMap=null;
			if(flag==0){
				WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
				return;
			}
			if(venIncs.size()>0){
				dto.setVenIncs(venIncs);
				venIncService.exportVenInc(dto);
				dto.setMsg(dto.getMsg()+"<br>成功导入"+successNum+"条。");
			}
			dto.setVenIncs(null);
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
		} catch (Exception e) {
			e.printStackTrace();
			//throw new DataBaseException(e.getMessage(), e);
			dto.setOpFlg("-1");
			dto.setMsg(dto.getMsg()+"<br>程序异常"+e.getMessage());
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
		}finally{
			FileUtils.forceDelete(dstFile);
		}

	}
	
	
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(同步供应商药品)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月11日 下午4:11:19
	* @version V1.0
	 */
	public void SynchVenInc(BusinessRequest res){
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		if(StringUtils.isNullOrEmpty(dto.getVenIncWeb().getPassWord())){
			dto.getOperateResult().setResultCode("-2");
			dto.getOperateResult().setResultContent("密码不能为空");
			return;
		}
		if(StringUtils.isNullOrEmpty(dto.getVenIncWeb().getUserName())){
			dto.getOperateResult().setResultCode("-2");
			dto.getOperateResult().setResultContent("用户名不能为空");
			return;
		}
		NormalAccount normalAccount=normalAccountService.getNormalAccountByAccount(dto.getVenIncWeb().getUserName());
		if(normalAccount==null){
			dto.getOperateResult().setResultCode("-3");
			dto.getOperateResult().setResultContent("没有该用户");
			return;
		}
		if(!normalAccount.getPassword().equals(dto.getVenIncWeb().getPassWord())){
			dto.getOperateResult().setResultCode("-4");
			dto.getOperateResult().setResultContent("密码不对");
			return;
		}
		if(!normalAccount.getNormalUser().getType().toString().equals("2")){
			dto.getOperateResult().setResultCode("-5");
			dto.getOperateResult().setResultContent("用户类型不对");
			return;
		}
		Map<String,String> manfMap = new HashMap<String,String>();
		for(VenIncItmWeb venIncItmWeb:dto.getVenIncWeb().getIncItmWebs()){
			
			if(venIncService.getVenIncByCode(venIncItmWeb.getVenIncCode(),normalAccount.getNormalUser().getVendorId().longValue())==null){
				VenInc venInc=new VenInc();
				venInc.setVenIncAlias(PingYinUtil.getFirstSpell(venIncItmWeb.getVenIncName()));
				venInc.setVenIncCat(venIncItmWeb.getVenIncCat());
				venInc.setVenIncName(venIncItmWeb.getVenIncName());
				venInc.setVenIncPrice(venIncItmWeb.getVenIncPrice());
				venInc.setVenIncSpec(venIncItmWeb.getVenIncSpec());
				venInc.setVenIncCode(venIncItmWeb.getVenIncCode());
				venInc.setVenIncUomname(venIncItmWeb.getVenIncUom());
				
				if(!StringUtils.isNullOrEmpty(venIncItmWeb.getVenIncManf())){
					if(!manfMap.containsKey(venIncItmWeb.getVenIncManf())){
						Long tmpmanfid = hopManfService.getIdByName(venIncItmWeb.getVenIncManf());
						if(tmpmanfid==null){
							HopManf manf=new HopManf();
							manf.setManfName(venIncItmWeb.getVenIncManf());
							manf.setManfHisid(WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
							commonService.saveOrUpdate(manf);
							venInc.setVenIncManfid(manf.getHopManfId());
							manfMap.put(venIncItmWeb.getVenIncManf(),manf.getHopManfId().toString());
						}else{
							venInc.setVenIncManfid(tmpmanfid);
							manfMap.put(venIncItmWeb.getVenIncManf(),tmpmanfid.toString());
						}
					}else{
						venInc.setVenIncManfid(Long.valueOf(manfMap.get(venIncItmWeb.getVenIncManf())));
					}
				}
				venInc.setVenIncVenid(normalAccount.getNormalUser().getVendorId());
				commonService.saveOrUpdate(venInc);
			}
		}
		dto.getOperateResult().setResultCode("0");
		dto.getOperateResult().setResultContent("操作成功");
	}
	
	
	/**
	 * 
	* @Title: VenIncBlh.java
	* @Description: TODO(上传医院药品和供应商药品对照关系)
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月29日 下午7:48:09
	* @version V1.0
	 * @throws IOException 
	 */
	public void uploadConAndroid(BusinessRequest res) throws IOException{
		
		VenIncDto dto = super.getDto(VenIncDto.class, res);
		dto.setOpFlg("0");
		Long hopId=WebContextHolder.getContext().getVisit().getUserInfo().getHopId();
		dto.setMsg("<br>");
		
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
        
    
        //读取excel
        try {
			List<VenHopInc> venHopIncs = new ArrayList<VenHopInc>();
			//读取Excel文件
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			
			workbook = new HSSFWorkbook(new FileInputStream(storageFileName + File.separator + newFileName));
			sheet = workbook.getSheetAt(0);
			
			//明细
			a:
			for (int numRows = 1; numRows <= sheet.getLastRowNum(); numRows++) {
				
				row = sheet.getRow(numRows);
				VenHopInc venHopInc = new VenHopInc();
				String hopCode="";
				String venCode="";
				String venName="";
				float venFac=0l;
				float hopFac=0l;
				Long vendorId=null;
				
				for (int numCells = 0; numCells <= row.getLastCellNum(); numCells++) {
					cell = row.getCell(numCells);
					switch (numCells) {
					case 0:
						if(cell!=null){
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							hopCode=cell.getStringCellValue();
						}
						break;
					case 1:
						if(cell!=null){
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							venCode=cell.getStringCellValue();
						}
						break;
					case 2:
						if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								venName=cell.getStringCellValue();
						}
						break;
					case 3:
						if(cell!=null){
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								venFac=(float)cell.getNumericCellValue();
						}
						break;
					case 4:
						if(cell!=null){
							    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								hopFac=(float)cell.getNumericCellValue();
						}
						break;	
					}
					
				}
				
				
				if(StringUtils.isNullOrEmpty(hopCode)){
					dto.setOpFlg("-2");
					dto.setMsg(dto.getMsg()+"<BR>第"+numRows+"行医院代码为空.");
					continue;
				}else{
					Long hopIncId=hopIncService.getHopIncByCode(hopCode, hopId);
					if(hopIncId==null){
						dto.setOpFlg("-2");
						dto.setMsg(dto.getMsg()+"<BR>第"+numRows+"行医院代码:"+hopCode+"在平台中没有。");
						continue;
					}else{
						venHopInc.setHopIncId(hopIncId);
					}
				}
				
				if(StringUtils.isNullOrEmpty(venName)){
					dto.setOpFlg("-2");
					dto.setMsg(dto.getMsg()+"<BR>第"+numRows+"行供应商名称为空.");
					continue;
				}else{
					vendorId=hopVendorService.findVendorByCode(venName, hopId).getHopVenId();
					if(vendorId==null){
						dto.setOpFlg("-2");
						dto.setMsg(dto.getMsg()+"<BR>第"+numRows+"行供应商:"+venName+"在平台中没有。");
						continue;
					}
				}
				//供应商药品代码
				if(StringUtils.isNullOrEmpty(venCode)){
					dto.setOpFlg("-2");
					dto.setMsg(dto.getMsg()+"<BR>第"+numRows+"行供应商药品代码空.");
					continue;
				}else{
					Long venIncId=venIncService.getVenIncByCode(venCode, vendorId);
					if(venIncId==null){
						dto.setOpFlg("-2");
						dto.setMsg(dto.getMsg()+"<BR>第"+numRows+"行供应商药品代码:"+venCode+"在平台中没有。");
						continue;
					}else{
						venHopInc.setVenIncId(venIncId);
					}
				}
				
				if(venFac==0f){
					dto.setOpFlg("-2");
					dto.setMsg(dto.getMsg()+"<BR>第"+numRows+"行分子不能为空");
					continue;
				}else{
					venHopInc.setVenFac(venFac);
				}
				
				if(hopFac==0f){
					dto.setOpFlg("-2");
					dto.setMsg(dto.getMsg()+"<BR>第"+numRows+"行分母不能为空");
					continue;
				}else{
					venHopInc.setHopFac(hopFac);
				}
				venHopIncs.add(venHopInc);
			}
			if(dto.getOpFlg().equals("0")){
				venIncService.saveVenHopIncList(venHopIncs);
				dto.setMsg(dto.getMsg()+"<br>成功导入"+venHopIncs.size()+"条。");
			}
			WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
        }catch(Exception e){
        	e.printStackTrace();
        	dto.setOpFlg("-1");
        	dto.setMsg(dto.getMsg()+"<br>"+e.getMessage());
        	WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(dto));
        }

		
	}
	
	
}
