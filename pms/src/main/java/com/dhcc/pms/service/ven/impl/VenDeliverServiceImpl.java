/**
 * 通过模板生成Dto 
 * template by zxx
 */
package com.dhcc.pms.service.ven.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.pms.dao.ven.VenDeliverDao;
import com.dhcc.pms.service.ven.VenDeliverService;

@Service("venDeliverService")
public class VenDeliverServiceImpl implements VenDeliverService {

	@Resource
	private VenDeliverDao venDeliverDao;

	/* (non-Javadoc)
	 * @see com.dhcc.pms.service.ven.VenDeliverService#AccectOrder(java.lang.Long)
	 */
	@Override
	public void AccectOrder(Long orderId) {
		// TODO Auto-generated method stub
		venDeliverDao.AccectOrder(orderId);
	}
	

}
