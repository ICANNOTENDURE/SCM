/**
 * 通过模板生成Service
 * template by zxx
 */
package com.dhcc.pms.service.hop;

import java.util.List;

import com.dhcc.pms.dto.hop.HospitalDto;
import com.dhcc.pms.entity.hop.Hospital;

public interface HospitalService {

	public void list(HospitalDto dto);
	
	public void save(HospitalDto dto);
	
	public void delete(HospitalDto dto);
	
	public void update(HospitalDto dto);
	
	public Hospital findById(HospitalDto dto);
	
	public List<Hospital> getHospInfo(HospitalDto dto);
	

}
