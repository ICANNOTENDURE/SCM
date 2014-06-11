/**
 * 
 */
package com.dhcc.pms.entity.vo.hop;


public class HopVendorVo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//表id
	private Long hopvenid;
	
	private String hopvenname;
	
	private String hopname;

	private String venname;

	private Long venid;

	/**
	 * @return the hopvenid
	 */
	public Long getHopvenid() {
		return hopvenid;
	}

	/**
	 * @param hopvenid the hopvenid to set
	 */
	public void setHopvenid(Long hopvenid) {
		this.hopvenid = hopvenid;
	}

	/**
	 * @return the hopvenname
	 */
	public String getHopvenname() {
		return hopvenname;
	}

	/**
	 * @param hopvenname the hopvenname to set
	 */
	public void setHopvenname(String hopvenname) {
		this.hopvenname = hopvenname;
	}

	/**
	 * @return the hopname
	 */
	public String getHopname() {
		return hopname;
	}

	/**
	 * @param hopname the hopname to set
	 */
	public void setHopname(String hopname) {
		this.hopname = hopname;
	}

	/**
	 * @return the venname
	 */
	public String getVenname() {
		return venname;
	}

	/**
	 * @param venname the venname to set
	 */
	public void setVenname(String venname) {
		this.venname = venname;
	}

	/**
	 * @return the venid
	 */
	public Long getVenid() {
		return venid;
	}

	/**
	 * @param venid the venid to set
	 */
	public void setVenid(Long venid) {
		this.venid = venid;
	}
	
	
}
