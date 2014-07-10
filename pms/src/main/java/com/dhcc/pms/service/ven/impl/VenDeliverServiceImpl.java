/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.ven.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.pms.dao.ven.VenDeliverDao;
import com.dhcc.pms.dto.ven.VenDeliverDto;
import com.dhcc.pms.entity.ven.VenDeliveritm;
import com.dhcc.pms.service.ven.VenDeliverService;

@Service("venDeliverService")
public class VenDeliverServiceImpl implements VenDeliverService {

	@Resource
	private VenDeliverDao venDeliverDao;

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#AccectOrder(java.lang.Long)
	 */
	@Override
	public void AccectOrder(VenDeliverDto dto) {
		// TODO Auto-generated method stub
		venDeliverDao.AccectOrder(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#listDeliver(com.dhcc.pms.dto.ven.VenDeliverDto)
	 */
	@Override
	public void listDeliver(VenDeliverDto dto) {
		// TODO Auto-generated method stub
		venDeliverDao.listDeliver(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#listDeliverItm(com.dhcc.pms.dto.ven.VenDeliverDto)
	 */
	@Override
	public void listDeliverItm(VenDeliverDto dto) {
		// TODO Auto-generated method stub
		venDeliverDao.listDeliverItm(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#saveDeliverItm(com.dhcc.pms.dto.ven.VenDeliverDto)
	 */
	@Override
	public void saveDeliverItm(VenDeliverDto dto) {
		// TODO Auto-generated method stub
		venDeliverDao.saveDeliverItm(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#impInv(com.dhcc.pms.dto.ven.VenDeliverDto)
	 */
	@Override
	public void impInv(VenDeliverDto dto) {
		// TODO Auto-generated method stub
		venDeliverDao.impInv(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#impByOrder(com.dhcc.pms.dto.ven.VenDeliverDto)
	 */
	@Override
	public void impByOrder(VenDeliverDto dto) {
		// TODO Auto-generated method stub
		venDeliverDao.impByOrder(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#deliver(com.dhcc.pms.dto.ven.VenDeliverDto)
	 */
	@Override
	public void deliver(VenDeliverDto dto) {
		// TODO Auto-generated method stub
		venDeliverDao.deliver(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#delete(com.dhcc.pms.dto.ven.VenDeliverDto)
	 */
	@Override
	public void delete(VenDeliverDto dto) {
		// TODO Auto-generated method stub
		venDeliverDao.delete(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#cancelComplete(com.dhcc.pms.dto.ven.VenDeliverDto)
	 */
	@Override
	public void cancelComplete(VenDeliverDto dto) {
		// TODO Auto-generated method stub
		venDeliverDao.cancelComplete(dto);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#getDeliveritms(com.dhcc.pms.dto.ven.VenDeliverDto)
	 */
	@Override
	public List<VenDeliveritm> getDeliveritms(Long deliverId) {
		// TODO Auto-generated method stub
		return venDeliverDao.getDeliveritms(deliverId);
	}

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#deliver(java.util.Map)
	 */
	@Override
	public void deliver(Map<String, List<VenDeliveritm>> map) {
		// TODO Auto-generated method stub
		venDeliverDao.Deliver(map);
	}
	

}
