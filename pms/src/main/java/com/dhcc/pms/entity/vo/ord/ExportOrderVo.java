package com.dhcc.pms.entity.vo.ord;





public class ExportOrderVo implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String no;
	
	private String purloc;
	
	private String recloc;
	
	private String venincid;
	
	private String venincname;
	
	private Long qty;
	
	private String uom;
	
	private Long rp;
	
	private String hopincname;
	
	private Long hopincid;
	
	
	
	/**
	 * @return the hopincname
	 */
	public String getHopincname() {
		return hopincname;
	}

	/**
	 * @param hopincname the hopincname to set
	 */
	public void setHopincname(String hopincname) {
		this.hopincname = hopincname;
	}

	/**
	 * @return the hopincid
	 */
	public Long getHopincid() {
		return hopincid;
	}

	/**
	 * @param hopincid the hopincid to set
	 */
	public void setHopincid(Long hopincid) {
		this.hopincid = hopincid;
	}

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return the purloc
	 */
	public String getPurloc() {
		return purloc;
	}

	/**
	 * @param purloc the purloc to set
	 */
	public void setPurloc(String purloc) {
		this.purloc = purloc;
	}

	/**
	 * @return the recloc
	 */
	public String getRecloc() {
		return recloc;
	}

	/**
	 * @param recloc the recloc to set
	 */
	public void setRecloc(String recloc) {
		this.recloc = recloc;
	}

	/**
	 * @return the venincid
	 */
	public String getVenincid() {
		return venincid;
	}

	/**
	 * @param venincid the venincid to set
	 */
	public void setVenincid(String venincid) {
		this.venincid = venincid;
	}

	

	/**
	 * @return the venincname
	 */
	public String getVenincname() {
		return venincname;
	}

	/**
	 * @param venincname the venincname to set
	 */
	public void setVenincname(String venincname) {
		this.venincname = venincname;
	}

	/**
	 * @return the qty
	 */
	public Long getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(Long qty) {
		this.qty = qty;
	}

	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}

	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}

	/**
	 * @return the rp
	 */
	public Long getRp() {
		return rp;
	}

	/**
	 * @param rp the rp to set
	 */
	public void setRp(Long rp) {
		this.rp = rp;
	}

	
	
	
}
