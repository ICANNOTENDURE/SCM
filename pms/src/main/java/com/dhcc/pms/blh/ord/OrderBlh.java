/**
 * 通过模板生成Blh 
 * template by zxx
 */
package com.dhcc.pms.blh.ord;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.app.service.CommonService;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.util.JsonUtils;
import com.dhcc.framework.util.StringUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.ord.OrderDto;
import com.dhcc.pms.dto.sys.SysImpModelDto;
import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.hop.HopCtlocDestination;
import com.dhcc.pms.entity.hop.HopInc;
import com.dhcc.pms.entity.hop.HopVendor;
import com.dhcc.pms.entity.hop.Hospital;
import com.dhcc.pms.entity.ord.Order;
import com.dhcc.pms.entity.ord.OrderItm;
import com.dhcc.pms.entity.sys.ImpModel;
import com.dhcc.pms.entity.vo.ws.HisInvInfoItmWeb;
import com.dhcc.pms.entity.vo.ws.HisInvInfoWeb;
import com.dhcc.pms.entity.vo.ws.HisOrderItmWebVo;
import com.dhcc.pms.entity.vo.ws.HisOrderWebVo;
import com.dhcc.pms.entity.vo.ws.OperateResult;
import com.dhcc.pms.service.hop.HopCtlocService;
import com.dhcc.pms.service.hop.HopIncService;
import com.dhcc.pms.service.hop.HopVendorService;
import com.dhcc.pms.service.hop.HospitalService;
import com.dhcc.pms.service.ord.OrderService;
import com.dhcc.pms.service.sys.SysImpModelService;
import com.dhcc.pms.service.ven.VenDeliverService;
import com.dhcc.pms.service.ven.VendorService;


@Component
public class OrderBlh extends AbstractBaseBlh {


	@Resource
	private OrderService orderService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private SysImpModelService sysImpModelService;
	
	@Resource
	private HopCtlocService hopCtlocService;
	
	@Resource
	private HopIncService hopIncService;
	
	@Resource
	private HopVendorService hopVendorService;
	
	@Resource
	private HospitalService hospitalService;
	
	@Resource
	private VendorService vendorService;

	@Resource
	private VenDeliverService venDeliverService;
	
	public OrderBlh() {
		
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
	
		OrderDto dto = super.getDto(OrderDto.class, res);
		
		//调用对应的service方法
		orderService.list(dto);
	}
	
	//保存订单
	public void save(BusinessRequest res) {
	
		OrderDto dto = super.getDto(OrderDto.class, res);
		orderService.saveOrUpdate(dto);
		dto.setOpFlg("1");
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		OrderDto dto = super.getDto(OrderDto.class, res);
		
		//调用对应的service方法
		orderService.delete(dto);
		dto.setOpFlg("1");
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		OrderDto dto = super.getDto(OrderDto.class, res);
		
		//调用对应的service方法
		orderService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		OrderDto dto = super.getDto(OrderDto.class, res);
		
		//调用对应的service方法
		orderService.findById(dto);
		
	}
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(保存购物车)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月21日 下午7:09:27
	* @version V1.0
	 */
	public void saveShopCart(BusinessRequest res) {
	
		OrderDto dto = super.getDto(OrderDto.class, res);
		
		//调用对应的service方法
		orderService.saveShopCart(dto);
		dto.setMsg("添加成功");
		dto.setOpFlg("1");
	}
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(查询登录人的购物车)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月22日 下午2:31:51
	* @version V1.0
	 */
	public void listShopCart(BusinessRequest res) {
		
		OrderDto dto = super.getDto(OrderDto.class, res);
		dto.setShopCartVoList(orderService.listShopCart(dto));
		
	}
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(删除购物车的一个药 )
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月23日 上午11:36:08
	* @version V1.0
	 */
	public void deleteShopCart(BusinessRequest res) {
		OrderDto dto = super.getDto(OrderDto.class, res);
		orderService.deleteShopCart(dto);
		dto.setOpFlg("1");
		
	}
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(确认购物车药品)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月27日 上午10:11:12
	* @version V1.0
	 */
	public void saveOrdInfo(BusinessRequest res) {
		OrderDto dto = super.getDto(OrderDto.class, res);
		
		orderService.saveOrdInfo(dto);
		dto.setOpFlg("1");
		
	}
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(返回收货地址combo)
	* @param res
	* @throws IOException
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月27日 下午3:15:12
	* @version V1.0
	 */
	public void findLocDesctionComboList(BusinessRequest res) throws IOException {
		OrderDto dto = super.getDto(OrderDto.class, res);
		List<HopCtlocDestination> destinationList=orderService.findLocDesctionComboList(dto);
		WebContextHolder.getContext().getResponse().getWriter().write(JsonUtils.toJson(destinationList));
	}
	
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(导入订单)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年5月31日 下午3:27:12
	* @version V1.0
	 * @throws IOException 
	 */
	public void upload(BusinessRequest res) throws IOException{
		
		OrderDto dto = super.getDto(OrderDto.class, res);

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
        SysImpModelDto.getImpModel().setType("ORDER");
        List<ImpModel> listImpModels=sysImpModelService.getModelList(SysImpModelDto);
        Map<Integer,String> modelMap=new HashMap<Integer,String>();
        for(int i=0;i<listImpModels.size();i++){
        	modelMap.put(Integer.valueOf(listImpModels.get(i).getSeq().toString()), listImpModels.get(i).getName());
        }
        //读取excel
        try {

        	Order order=new Order();
			List<OrderItm> orderItms = new ArrayList<OrderItm>();
			//读取Excel文件
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			
			workbook = new HSSFWorkbook(new FileInputStream(storageFileName + File.separator + newFileName));
			sheet = workbook.getSheetAt(0);
			
			row = sheet.getRow(1);
			dto.setOpFlg("1");
			for (int h = 0; h <= row.getLastCellNum(); h++) {
				cell = row.getCell(h);
				String mainColNameString=modelMap.get(h);
				if(StringUtils.isNullOrEmpty(mainColNameString)) {mainColNameString=" ";};
				switch (mainColNameString) {
					case "订单号":
						if(cell!=null){
							order.setOrderNo(cell.toString());
						}
						break;
//					case "医院ID":
//						if(cell!=null){
//							order.setHopId(Math.round(cell.getNumericCellValue()));;
//						}
//						break;	
					case "请求科室ID":
						if(cell!=null){
							order.setRecLoc(hopCtlocService.getLocIdByName(cell.getStringCellValue()));
						}
						break;
					case "入库科室ID":
						if(cell!=null){
							order.setPurLoc(hopCtlocService.getLocIdByName(cell.getStringCellValue()));
						}
						break;	
					case "是否加急":
						if(cell!=null){
							order.setEmFlag(cell.toString());
						}
						break;
					case "收货地址":
						if(cell!=null){
							//order.set
						}
						break;
					case "要求送货时间":
						if(cell!=null){
							order.setDeliveryDate(cell.getDateCellValue());
						}
						break;
					case "供应商ID":
						if(cell!=null){
							Long vendorIdLong=hopVendorService.findVendorIdByName(cell.getStringCellValue());
							if(vendorIdLong==null){
								 dto.setOpFlg("6");
							     dto.setMsg(cell.getStringCellValue()+":供应商在平台没有维护");
							}else{
								order.setVendorId(hopVendorService.findVendorIdByName(cell.getStringCellValue()));
							}	
						}
						break;	
					}
			}
			
			if(!dto.getOpFlg().equals("1")){
				return;
			}
			order.setHopId(WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
			if(WebContextHolder.getContext().getVisit().getUserInfo().getHopId()==null){
				dto.setOpFlg("-2");
				dto.setMsg("登录超时,请重新登录");
				return;
			}
			dto.setOrder(order);
			//明细
			for (int numRows = 1; numRows <= sheet.getLastRowNum(); numRows++) {
				
				row = sheet.getRow(numRows);
				
				OrderItm orderItm = new OrderItm();
				
				HopInc hopInc=new HopInc();
				String undefineName="";
				for (int numCells = 0; numCells <= row.getLastCellNum(); numCells++) {
					cell = row.getCell(numCells);
					String colNameString=modelMap.get(numCells);
					if(StringUtils.isNullOrEmpty(colNameString)) {colNameString=" ";};
					if (colNameString.equals("HIS药品标示")) {
							hopInc=hopIncService.getIncIdByName(cell.getStringCellValue());
							undefineName=cell.getStringCellValue();
					}
				}
				if(hopInc==null){
					 if(StringUtils.isNullOrEmpty(dto.getMsg())){
						   dto.setMsg(undefineName+":在平台里有没");
					   }else{
						   dto.setMsg(dto.getMsg()+"."+undefineName+":在平台里有没");
						   dto.setOpFlg("4");
					   }
					continue;
				}
				orderItm.setUom(hopInc.getIncUomname());
				orderItm.setIncId(hopInc.getIncId());
				orderItm.setRp((float)hopInc.getIncRp());
				
				for (int numCells = 0; numCells <= row.getLastCellNum(); numCells++) {
					cell = row.getCell(numCells);
					
					String colNameString=modelMap.get(numCells);
					if(StringUtils.isNullOrEmpty(colNameString)) {colNameString=" ";};
					
					
					switch (colNameString) {
//						case "HIS药品标示":
//							orderItm.setIncId(hopInc.getIncId());
//							break;
						case "单位":
							if(cell!=null){
								orderItm.setUom(cell.getStringCellValue());
							}
							break;
						case "数量":
							if(cell!=null){
								orderItm.setReqqty((float)cell.getNumericCellValue());
							}
							break;
						case "进价":
							if(cell!=null){
								orderItm.setRp((float)cell.getNumericCellValue());
							}
							break;
					}
				}
				
				orderItms.add(orderItm);
			}
			dto.setOrderItms(orderItms);
			
			
			orderService.impOrder(dto);
			
			

			dto.setOrderItms(null);
			WebContextHolder
			.getContext()
			.getResponse()
			.getWriter()
			.write(JsonUtils.toJson(dto));

		
		} catch (Exception e) {
			e.printStackTrace();
			//throw new DataBaseException(e.getMessage(), e);
			dto.setOpFlg("-11");
			dto.setMsg(dto.getMsg()+"<br>程序异常:"+e.getMessage());
			WebContextHolder
			.getContext()
			.getResponse()
			.getWriter()
			.write(JsonUtils.toJson(dto));
		}finally{
			FileUtils.forceDelete(dstFile);
		}
	}
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(修改订单明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月5日 上午9:41:40
	* @version V1.0
	 */
	public void saveOrditm(BusinessRequest res){
		OrderDto dto = super.getDto(OrderDto.class, res);
		orderService.saveOrditm(dto);
	}
	
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(删除订单明细)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月5日 上午9:42:39
	* @version V1.0
	 */
	public void deleteOrditm(BusinessRequest res){
		OrderDto dto = super.getDto(OrderDto.class, res);
		orderService.deleteOrditm(dto);
	}
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(医院确定订单完成)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月5日 下午8:42:41
	* @version V1.0
	 */
	public void complete(BusinessRequest res){
		OrderDto dto = super.getDto(OrderDto.class, res);
		orderService.complete(dto);
	}
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(取消完成状态)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月17日 下午2:00:55
	* @version V1.0
	 */
	public void cancleComplete(BusinessRequest res){
		OrderDto dto = super.getDto(OrderDto.class, res);
		orderService.cancleComplete(dto);
		dto.setOpFlg("1");
	}
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @param res
	* @return:void 
	* @author zhouxin  
	* @date 2014年6月17日 上午9:53:27
	* @version V1.0
	 */
	public void saveMain(BusinessRequest res) {
	
		OrderDto dto = super.getDto(OrderDto.class, res);
		if(dto.getOrder().getHopId()==null){
			dto.getOrder().setHopId(WebContextHolder.getContext().getVisit().getUserInfo().getHopId());
		}
		commonService.saveOrUpdate(dto.getOrder());
		dto.setOpFlg("1");
	}
	
	public void exeOrder(BusinessRequest res) {
		
		OrderDto dto = super.getDto(OrderDto.class, res);
		orderService.exeOrder(dto);
		dto.setOpFlg("1");
	}
	
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(用一句话描述该文件做什么)
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月28日 下午5:52:24
	* @version V1.0
	 */
	@SuppressWarnings("unchecked")
	public void importOrderByWS(HisOrderWebVo hisOrderWebVo,OperateResult operateResult){

		if(hisOrderWebVo.getHisOrderItmWebVos().size()==0){
			operateResult.setResultCode("-1");
			operateResult.setResultContent("订单明细不能为空");
			return ;
		}
		Order order=new Order();
		
		if(StringUtils.isNullOrEmpty(hisOrderWebVo.getHopname())){
			operateResult.setResultCode("-2");
			operateResult.setResultContent("医院名称不能为空");
			return ;
		}else{
			DetachedCriteria criteria = DetachedCriteria.forClass(Hospital.class);
			criteria.add(Restrictions.eq("hospitalName", hisOrderWebVo.getHopname()));
			List<Hospital> hospitals=commonService.findByDetachedCriteria(criteria);
			if(hospitals.size()==0){
				operateResult.setResultCode("-6");
				operateResult.setResultContent("医院名称错误");
				return ;
			}
			order.setHopId(hospitals.get(0).getHospitalId());
		}
		
		if(StringUtils.isNullOrEmpty(hisOrderWebVo.getVendorname())){
			operateResult.setResultCode("-3");
			operateResult.setResultContent("供应商名称名称不能为空");
			return ;
		}else{
			DetachedCriteria criteriaVendor = DetachedCriteria.forClass(HopVendor.class);
			criteriaVendor.add(Restrictions.eq("hopName", hisOrderWebVo.getVendorname()));
			criteriaVendor.add(Restrictions.eq("hopHopId", order.getHopId()));
			List<HopVendor> hopVendors=commonService.findByDetachedCriteria(criteriaVendor);
			if(hopVendors.size()==0){
				operateResult.setResultCode("-7");
				operateResult.setResultContent("供应商名称错误");
				return ;
			}
			order.setVendorId(hopVendors.get(0).getHopVendorId());
		}
		
		if(StringUtils.isNullOrEmpty(hisOrderWebVo.getOrderno())){
			operateResult.setResultCode("-4");
			operateResult.setResultContent("医院订单号不能为空");
			return ;
		}else{
			DetachedCriteria criteriaOrderNo = DetachedCriteria.forClass(Order.class);
			criteriaOrderNo.add(Restrictions.eq("orderNo", hisOrderWebVo.getOrderno()));
			criteriaOrderNo.add(Restrictions.eq("hopId", order.getHopId()));
			List<Order> orders=commonService.findByDetachedCriteria(criteriaOrderNo);
			if(orders.size()>0){
				operateResult.setResultCode("-10");
				operateResult.setResultContent("该单号已经上传");
				return ;
			}
			order.setOrderNo(hisOrderWebVo.getOrderno());
		}
		
		
		if(StringUtils.isNullOrEmpty(hisOrderWebVo.getPurloc())){
			operateResult.setResultCode("-5");
			operateResult.setResultContent("入库科室不能不能为空");
			return ;
		}else{
			DetachedCriteria criteriaPurLoc = DetachedCriteria.forClass(HopCtloc.class);
			criteriaPurLoc.add(Restrictions.eq("name", hisOrderWebVo.getPurloc()));
			criteriaPurLoc.add(Restrictions.eq("hospid", order.getHopId()));
			List<HopCtloc> hopCtlocs=commonService.findByDetachedCriteria(criteriaPurLoc);
			if(hopCtlocs.size()==0){
				operateResult.setResultCode("-8");
				operateResult.setResultContent("采购科室名称错误");
				return ;
			}
			order.setPurLoc(hopCtlocs.get(0).getHopCtlocId());
		}
		

		if(hisOrderWebVo.getRemark()!=null){
			order.setRemark(hisOrderWebVo.getRemark());
		}
		if(!StringUtils.isNullOrEmpty(hisOrderWebVo.getPlanDate().toString())){
			order.setPlanDate(hisOrderWebVo.getPlanDate());
		}else{
			order.setPlanDate(new Date());
		}
		order.setDeliveryDate(new Date());

		if(hisOrderWebVo.getRecloc()!=null){
			DetachedCriteria criteriaRecLoc = DetachedCriteria.forClass(HopCtloc.class);
			criteriaRecLoc.add(Restrictions.eq("name", hisOrderWebVo.getRecloc()));
			criteriaRecLoc.add(Restrictions.eq("hospid", order.getHopId()));
			List<HopCtloc> hopCtlocs2=commonService.findByDetachedCriteria(criteriaRecLoc);
			if(hopCtlocs2.size()==0){
				operateResult.setResultCode("-9");
				operateResult.setResultContent("收货室名称错误");
				return ;
			}
			order.setRecLoc(hopCtlocs2.get(0).getHopCtlocId());
		}
		
		
		List<OrderItm> orderItms=new ArrayList<OrderItm>();
		for(HisOrderItmWebVo hisOrderItmWebVo:hisOrderWebVo.getHisOrderItmWebVos()){
			if(StringUtils.isNullOrEmpty(hisOrderItmWebVo.getHopIncCode())){
				operateResult.setResultCode("-1");
				operateResult.setResultContent(operateResult.getResultContent()+";药品代码为空");
				continue;
			}
			
			DetachedCriteria criteriaInc=null;
			criteriaInc= DetachedCriteria.forClass(HopInc.class);
			criteriaInc.add(Restrictions.eq("incCode", hisOrderItmWebVo.getHopIncCode()));
			criteriaInc.add(Restrictions.eq("incHospid", order.getHopId()));
			List<HopInc> hopIncs=null;
			hopIncs=commonService.findByDetachedCriteria(criteriaInc);
			if(hopIncs.size()==0){
				operateResult.setResultCode("-1");
				operateResult.setResultContent(operateResult.getResultContent()+";药品代码:"+hisOrderItmWebVo.getHopIncCode()+"平台没有,请同步药品基础数据");
				continue;
			}
			OrderItm orderItm=new OrderItm();
			orderItm.setIncId(hopIncs.get(0).getIncId());
			orderItm.setReqqty(hisOrderItmWebVo.getQty());
			orderItm.setRp(hisOrderItmWebVo.getRp());
			orderItm.setUom(hopIncs.get(0).getIncUomname());
			
			orderItms.add(orderItm);
		}
		
		if(orderItms.size()!=hisOrderWebVo.getHisOrderItmWebVos().size()){
			return ;
		}

		orderService.ImportOrderByWS(order, orderItms);
		operateResult.setResultCode("0");
		operateResult.setResultContent("success");
	}
	
	
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(his调用，入库带出明细使用)
	* @param invNo
	* @param hopName
	* @param venName
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月30日 上午10:36:09
	* @version V1.0
	 */
	public void getRecItmByInvWS(String invNo, String hopName,String venName,HisInvInfoWeb hisInvInfoWeb){
		
		Long hopId=null;
		Long vendorId=null;
		if(StringUtils.isNullOrEmpty(invNo)){
			hisInvInfoWeb.setResultCode("1");
			hisInvInfoWeb.setResultContent("发票号为空");
			return;
		}
		if(StringUtils.isNullOrEmpty(hopName)){
			hisInvInfoWeb.setResultCode("1");
			hisInvInfoWeb.setResultContent("医院名称为空");
			return;
		}else{
			Hospital hospital=hospitalService.getHospitalByName(hopName);
			if(hospital==null){
				hisInvInfoWeb.setResultCode("1");
				hisInvInfoWeb.setResultContent("医院名称错误");
				return;
			}else{
				hopId=hospital.getHospitalId();
			}
		}
		if(StringUtils.isNullOrEmpty(venName)){
			hisInvInfoWeb.setResultCode("1");
			hisInvInfoWeb.setResultContent("供应商名称为空");
			return;
		}else{
			vendorId=vendorService.findVendorIdByName(venName);
			if(vendorId==null){
				hisInvInfoWeb.setResultCode("1");
				hisInvInfoWeb.setResultContent("供应商名称错误");
				return;
			}
		}
		
		List<HisInvInfoItmWeb> hisInvInfoItmWebs=venDeliverService.getRecItmByInv(hopId, vendorId, invNo);
		if(hisInvInfoItmWebs.size()>0){
			hisInvInfoWeb.setHisInvInfoItmWebs(hisInvInfoItmWebs);
			hisInvInfoWeb.setResultCode("0");
			hisInvInfoWeb.setResultContent("sucess");
			return;
		}else{
			hisInvInfoWeb.setResultCode("1");
			hisInvInfoWeb.setResultContent("发票号码无效,错误");
		}
	}	
	
	
	/**
	 * 
	* @Title: OrderBlh.java
	* @Description: TODO(同步HIS药品)
	* @return:void 
	* @author zhouxin  
	* @date 2014年7月30日 下午5:36:32
	* @version V1.0
	 */
	public void saveHisIncWS(HopInc hopInc,OperateResult operateResult,String hopName){
		
		if(hopInc==null){
			operateResult.setResultCode("1");
			operateResult.setResultContent("入参为空");
			return;
		}
		
		if(StringUtils.isNullOrEmpty(hopName)){
			operateResult.setResultCode("1");
			operateResult.setResultContent("医院名称为空");
			return;
		}else{
			Hospital hospital=hospitalService.getHospitalByName(hopName);
			if(hospital!=null){
				hopInc.setIncHospid(hospital.getHospitalId());
			}else{
				operateResult.setResultCode("1");
				operateResult.setResultContent("医院名称错误");
				return;
			}
			
		}
		
		DetachedCriteria criteria = DetachedCriteria.forClass(HopInc.class);
		criteria.add(Restrictions.eq("incCode", hopInc.getIncCode()));
		criteria.add(Restrictions.eq("incHospid", hopInc.getIncHospid()));
		@SuppressWarnings("unchecked")
		List<HopInc> hopIncs=commonService.findByDetachedCriteria(criteria);
		if(hopIncs.size()>0){
			hopInc.setIncId(hopIncs.get(0).getIncId());
		}
		commonService.saveOrUpdate(hopInc);
		operateResult.setResultCode("0");
		operateResult.setResultContent("success");
	}
}
