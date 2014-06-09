package com.dhcc.pms.dto.userManage;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.pms.entity.platformManage.SystemVersion;
import com.dhcc.pms.entity.userManage.Func;
import com.dhcc.pms.entity.userManage.Role;
import com.dhcc.pms.entity.userManage.RoleFunc;

/**
 * 标题: RoleDto.java
 * 业务描述：安全运维管理平台
 * 公司:东华软件股份公司
 * 版权:dhcc2013
 * @author 聂文来
 * @date 2013年9月3日
 * @version V1.0 
 */
public class RoleDto extends BaseDto{
	
	/**  
	* 字段:      字段名称
	* @Fields serialVersionUID : 序列化id
	*/
	private static final long serialVersionUID = 1L;
	
	private Role role;
	
	private String systemType;
	private String columnName = "";
	private String columnValue = "";
	
    
    private List<Func> funcs;
    private List<RoleFunc> roleFuncs;
    private Long roleId;
    private String parentId;
    
    private String funcIds;
    
    private String message;
    private boolean success;
    
    
	/**  
	 * @return message 
	 */
	public String getMessage() {
		return message;
	}

	/**  
	 * @param message message 
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**  
	 * @return success 
	 */
	public boolean isSuccess() {
		return success;
	}

	/**  
	 * @param success success 
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**  
	 * @return funcIds 
	 */
	public String getFuncIds() {
		return funcIds;
	}

	/**  
	 * @param funcIds funcIds 
	 */
	public void setFuncIds(String funcIds) {
		this.funcIds = funcIds;
	}

	/**  
	 * @return roleId 
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**  
	 * @param roleId roleId 
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**  
	 * @return parentId 
	 */
	public String getParentId() {
		return parentId;
	}

	/**  
	 * @param parentId parentId 
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**  
	 * @return funcs 
	 */
	public List<Func> getFuncs() {
		return funcs;
	}

	/**  
	 * @param funcs funcs 
	 */
	public void setFuncs(List<Func> funcs) {
		this.funcs = funcs;
	}

	/**  
	 * @return systemType 
	 */
	public String getSystemType() {
		return systemType;
	}

	/**  
	 * @param systemType systemType 
	 */
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	/**  
	 * @return columnName 
	 */
	public String getColumnName() {
		return columnName;
	}

	/**  
	 * @param columnName columnName 
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**  
	 * @return columnValue 
	 */
	public String getColumnValue() {
		return columnValue;
	}

	/**  
	 * @param columnValue columnValue 
	 */
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	/**  
	 * @return role 
	 */
	public Role getRole() {
		return role;
	}

	/**  
	 * @param role role 
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**  
	 * @return roleFuncs 
	 */
	public List<RoleFunc> getRoleFuncs() {
		return roleFuncs;
	}

	/**  
	 * @param roleFuncs roleFuncs 
	 */
	public void setRoleFuncs(List<RoleFunc> roleFuncs) {
		this.roleFuncs = roleFuncs;
	}
	
	//系统类型
	private List<SystemVersion> systemVersions;

	/**  
	 * @return systemVersions 
	 */
	public List<SystemVersion> getSystemVersions() {
		return systemVersions;
	}
	/**  
	 * @param systemVersions systemVersions 
	 */
	public void setSystemVersions(List<SystemVersion> systemVersions) {
		this.systemVersions = systemVersions;
	}
	
	
}
