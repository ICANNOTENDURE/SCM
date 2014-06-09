package com.dhcc.pms.entity.ven;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.dhcc.pms.entity.userManage.NormalAccountRoleId;



@Embeddable
public class VenHopIncId implements Serializable {

	/**  
	* 字段:      序列化id
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;
	
	private Long hopIncId;
	private Long venIncId;

	
	
	/**
	 * @return the hopIncId
	 */
	public Long getHopIncId() {
		return hopIncId;
	}
	/**
	 * @param hopIncId the hopIncId to set
	 */
	public void setHopIncId(Long hopIncId) {
		this.hopIncId = hopIncId;
	}
	/**
	 * @return the venIncId
	 */
	public Long getVenIncId() {
		return venIncId;
	}
	/**
	 * @param venIncId the venIncId to set
	 */
	public void setVenIncId(Long venIncId) {
		this.venIncId = venIncId;
	}
	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NormalAccountRoleId))
			return false;
		VenHopIncId castOther = (VenHopIncId) other;

		return ((this.getVenIncId().equals(castOther.getVenIncId())) || (this
				.getVenIncId() != null
				&& castOther.getVenIncId() != null && this.getVenIncId().equals(
				castOther.getVenIncId())))
				&& ((this.getHopIncId().equals(castOther.getHopIncId())) || (this
						.getHopIncId() != null
						&& castOther.getHopIncId() != null && this
						.getHopIncId().equals(castOther.getHopIncId())));
	}
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result
				+ (this.getVenIncId() == null ? 0 : this.getVenIncId().hashCode());
		result = 37 * result
				+ (this.getHopIncId() == null ? 0 : this.getHopIncId().hashCode());
		return result;
	}
}