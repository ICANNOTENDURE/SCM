/**
 * 
 */
package com.dhcc.pms.entity.vo.hop;

/**
 * @author pengzhikun
 * function:定制收货信息显示实体类
 *
 */
public class HopDestinationVo {
	//科室收货信息表ID
	private Long hopCtlocDestinationId;
	//收货地点
	private String destination;
	//收货联系人
	private String desContact;
	//收货联系人电话
	private String desTel;
	//收货信息对应的科室ID
	private String  desCtlocDr;
	//收货信息对应的科室描述
	private String desCtlocName;
	
	public HopDestinationVo(){		
	}
		
	public HopDestinationVo(Long hopCtlocDestinationId, String destination,String desContact, String desTel,
			String desCtlocDr, String desCtlocName) {
		super();
		this.hopCtlocDestinationId = hopCtlocDestinationId;
		this.destination=destination;
		this.desContact = desContact;
		this.desTel = desTel;
		this.desCtlocDr = desCtlocDr;
		this.desCtlocName = desCtlocName;
	}

	public Long getHopCtlocDestinationId() {
		return hopCtlocDestinationId;
	}
	public void setHopCtlocDestinationId(Long hopCtlocDestinationId) {
		this.hopCtlocDestinationId = hopCtlocDestinationId;
	}
	public String getDesContact() {
		return desContact;
	}
	public void setDesContact(String desContact) {
		this.desContact = desContact;
	}
	public String getDesTel() {
		return desTel;
	}
	public void setDesTel(String desTel) {
		this.desTel = desTel;
	}
	public String getDesCtlocDr() {
		return desCtlocDr;
	}
	public void setDesCtlocDr(String desCtlocDr) {
		this.desCtlocDr = desCtlocDr;
	}
	public String getDesCtlocName() {
		return desCtlocName;
	}
	public void setDesCtlocName(String desCtlocName) {
		this.desCtlocName = desCtlocName;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	
}
