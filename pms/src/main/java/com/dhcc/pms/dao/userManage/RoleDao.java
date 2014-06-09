package com.dhcc.pms.dao.userManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.StatelessSession;
import org.springframework.stereotype.Repository;

import com.dhcc.framework.common.PagerModel;
import com.dhcc.framework.exception.DataBaseException;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.pms.dto.userManage.RoleDto;
import com.dhcc.pms.entity.userManage.NormalAccount;
import com.dhcc.pms.entity.userManage.Role;
import com.dhcc.pms.entity.userManage.RoleFunc;

/**
 * 标题: RoleDao.java
 * 业务描述：安全运维管理平台
 * 公司:东华软件股份公司
 * 版权:dhcc2013
 * @author 聂文来
 * @date 2013年9月3日
 * @version V1.0 
 */
@Repository
public class RoleDao extends HibernatePersistentObjectDAO<Role>{

	/**
	 * 
	* 方法名:          buildPagerModelQuery
	* 方法功能描述:     分页获取角色信息
	* @param:         
	* @return:        
	* @Author:        聂文来
	* @Create Date:   2013年8月30日 下午6:05:47
	 */
	@Override
	public void buildPagerModelQuery(PagerModel pagerModel, BaseDto dto) {
		RoleDto roleDto = (RoleDto)dto;
		pagerModel.setCountProName("roleId");
		StringBuffer hqlBuffer = new StringBuffer();
		Map<String, Object> hqlParamMap= new HashMap<String, Object>();
		
		this.buildQuery(roleDto,hqlBuffer,hqlParamMap);
		pagerModel.setQueryHql(hqlBuffer.toString());
		pagerModel.setHqlParamMap(hqlParamMap);
	}
	/**
	 * 
	* 方法名:          buildQuery
	* 方法功能描述:     拼装分页查询语句
	* @param:         
	* @return:        
	* @Author:        聂文来
	* @Create Date:   2013年10月30日 下午5:35:47
	 */
	private void buildQuery(RoleDto roleDto,StringBuffer hqlBuffer,Map<String, Object> hqlParamMap){
		hqlBuffer.append(" from Role ");
		hqlBuffer.append(" where 1=1 ");
		if(roleDto.getColumnName().length()!=0&&roleDto.getColumnValue().length()!=0){
			hqlBuffer.append(" and "+roleDto.getColumnName()+" like :columnValue ");
			hqlParamMap.put("columnValue", "%"+roleDto.getColumnValue()+"%");
		}
		hqlBuffer.append(" and useState = :useState ");
		hqlParamMap.put("useState", roleDto.getRole().getUseState());
    }
	
	/**
	 * 
	* 方法名:          getFuncsByRoleId
	* 方法功能描述:    获取角色权限
	* @param:         
	* @return:        
	* @Author:        聂文来
	* @Create Date:   2013年9月29日 下午2:04:26
	 */
	@SuppressWarnings("unchecked")
	public void getRoleFuncs(RoleDto roleDto) throws Exception{
		StringBuffer hql = new StringBuffer();
		hql.append(" from RoleFunc t ");
		hql.append(" where t.roleId = ? ");
		List<RoleFunc> roleFuncs = (List<RoleFunc>)this.findByHql(hql.toString(),Long.valueOf(roleDto.getRoleId()));
		roleDto.setRoleFuncs(roleFuncs);
	}
	
	/**
	 * 
	* 方法名:          removeRoleFunc
	* 方法功能描述:    移除角色权限
	* @param:         
	* @return:        
	* @Author:        聂文来
	* @Create Date:   2013年9月29日 下午4:35:43
	 */
	public void removeRoleFunc(RoleDto roleDto) throws Exception{
		StringBuffer hql = new StringBuffer();
		hql.append(" delete ");
		hql.append(" from RoleFunc t ");
		hql.append(" where ");
		hql.append(" t.roleId = ? ");
		
		this.updateByHqlWithFreeParam(hql.toString(), roleDto.getRoleId());
	}
	
	/**
	 * 
	* 方法名:          saveRoleFuncs
	* 方法功能描述:    批量保存角色权限
	* @param:         
	* @return:        
	* @Author:        聂文来
	* @Create Date:   2013年11月11日 下午4:55:57
	 */
	public void saveRoleFuncs(List<RoleFunc> roleFuncs) throws Exception{
		StatelessSession session = null;
		try {
			session = this.getSessionFactory().openStatelessSession();
			session.beginTransaction();
			for(int i=0;i<roleFuncs.size();i++){
				session.insert(roleFuncs.get(i));
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new DataBaseException(e.getMessage(), e);
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
	}
	
	
	
	/**
	 * 
	* 方法名:          queryNormalAccountByPageModel
	* 方法功能描述:    获取拥有该角色的普通用户账户
	* @param:         
	* @return:        
	* @Author:        聂文来
	* @Create Date:   2013年10月31日 上午11:31:29
	 */
	public void queryNormalAccountByPageModel(RoleDto roleDto) throws Exception{
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select ");
		hqlBuffer.append(" new NormalAccount(t2.accountName,t2.accountAlias,t2.onlineState,t2.useState) ");
		hqlBuffer.append(" from ");
		hqlBuffer.append(" NormalAccountRole t1,NormalAccount t2,Role t3 ");
		hqlBuffer.append(" where ");
		hqlBuffer.append(" t1.accountId = t2.accountId ");
		hqlBuffer.append(" and t1.roleId = t3.roleId ");
		hqlBuffer.append(" and t3.roleId = '"+roleDto.getRoleId()+"' ");
		if(roleDto.getColumnName().length()!=0&&roleDto.getColumnValue().length()!=0){
			hqlBuffer.append(" and t2."+roleDto.getColumnName()+" like '%"+roleDto.getColumnValue()+"%' ");
		}
		
		PagerModel pagerModel = roleDto.getPageModel();
		pagerModel.setQueryHql(hqlBuffer.toString());
		pagerModel.setHqlParamMap(null);

		int totalRows = this.getResultCountWithValuesMap(
				pagerModel.getQueryHql(), pagerModel.getHqlParamMap(), "*", false).intValue();
		if (totalRows == 0) {
			pagerModel.setPageData(new ArrayList<NormalAccount>());
		}
		pagerModel.setTotals(totalRows);
		
		pagerModel.setPageData(this.findByHqlWithValuesMap(
				pagerModel.getQueryHql(), pagerModel.getPageNo(), pagerModel.getPageSize(),
				pagerModel.getHqlParamMap(), false));
		
	}
	
	/**
	 * 
	* 方法名:          findRole
	* 方法功能描述:    获取某个角色信息
	* @param:         
	* @return:        
	* @Author:        聂文来
	* @Create Date:   2013年11月25日 下午4:15:39
	 */
	@SuppressWarnings("unchecked")
	public List<Role> findRole(RoleDto roleDto) throws Exception{
		StringBuffer hql = new StringBuffer();
		hql.append(" from ");
		hql.append(" Role t ");
		hql.append(" where t.systemType = ? ");
		hql.append(" and t.roleCode = ? ");
		return (List<Role>)this.findByHql(hql.toString(), roleDto.getSystemType(),roleDto.getRole().getRoleCode());
	}
	
}
