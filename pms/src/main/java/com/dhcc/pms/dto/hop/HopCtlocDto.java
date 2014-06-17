/**
 * Dto 
 * template by zxx
 */
package com.dhcc.pms.dto.hop;

import java.util.List;

import com.dhcc.pms.entity.hop.HopCtloc;
import com.dhcc.pms.entity.vo.hop.HopCtlocVo;
import com.dhcc.framework.transmission.dto.BaseDto;

public class HopCtlocDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private HopCtloc hopCtloc;
	private Long hospitalDr;
	private String comgridparam;
	private String type;
	//要显示的科室信息
	private List<HopCtlocVo> hopCtlocVos;
	
	private Long userId;
	
	
	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the comgridparam
	 */
	public String getComgridparam() {
		return comgridparam;
	}


	/**
	 * @param comgridparam the comgridparam to set
	 */
	public void setComgridparam(String comgridparam) {
		this.comgridparam = comgridparam;
	}


	public Long getHospitalDr() {
		return hospitalDr;
	}


	public void setHospitalDr(Long hospitalDr) {
		this.hospitalDr = hospitalDr;
	}


	public HopCtloc getHopCtloc() {
		return hopCtloc;
	}

	
	public void setHopCtloc(HopCtloc hopCtloc) {
		this.hopCtloc = hopCtloc;
	}


	/**
	 * @return the hopCtlocVos
	 */
	public List<HopCtlocVo> getHopCtlocVos() {
		return hopCtlocVos;
	}


	/**
	 * @param hopCtlocVos the hopCtlocVos to set
	 */
	public void setHopCtlocVos(List<HopCtlocVo> hopCtlocVos) {
		this.hopCtlocVos = hopCtlocVos;
	}


	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
