
package com.dhcc.pms.ws.his.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetVendorResult" type="{http://www.dhcc.com}HisVendor"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getVendorResult"
})
@XmlRootElement(name = "GetVendorResponse")
public class GetVendorResponse {

    @XmlElement(name = "GetVendorResult", required = true)
    protected HisVendor getVendorResult;

    /**
     * 获取getVendorResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HisVendor }
     *     
     */
    public HisVendor getGetVendorResult() {
        return getVendorResult;
    }

    /**
     * 设置getVendorResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HisVendor }
     *     
     */
    public void setGetVendorResult(HisVendor value) {
        this.getVendorResult = value;
    }

}
