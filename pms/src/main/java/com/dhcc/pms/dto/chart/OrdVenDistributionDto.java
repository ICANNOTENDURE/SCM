/**
 * Dto 
 * template by zxx
 */
package com.dhcc.pms.dto.chart;

import java.util.ArrayList;
import java.util.List;

import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.pms.entity.vo.chart.ChartVO;

public class OrdVenDistributionDto extends BaseDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ChartVO> chartVOs;
	
	private List<String> names= new ArrayList<String>();
	
	private List<String> values=new ArrayList<String>();

	private List<String> value1s=new ArrayList<String>();
	
	private String MaxQty;
	
	private String maxQtyMonth;
	
	private String minQty;
	
	private String minQtyMonth;
	
	
	
	/**
	 * @return the maxQty
	 */
	public String getMaxQty() {
		return MaxQty;
	}

	/**
	 * @param maxQty the maxQty to set
	 */
	public void setMaxQty(String maxQty) {
		MaxQty = maxQty;
	}

	/**
	 * @return the maxQtyMonth
	 */
	public String getMaxQtyMonth() {
		return maxQtyMonth;
	}

	/**
	 * @param maxQtyMonth the maxQtyMonth to set
	 */
	public void setMaxQtyMonth(String maxQtyMonth) {
		this.maxQtyMonth = maxQtyMonth;
	}

	/**
	 * @return the minQty
	 */
	public String getMinQty() {
		return minQty;
	}

	/**
	 * @param minQty the minQty to set
	 */
	public void setMinQty(String minQty) {
		this.minQty = minQty;
	}

	/**
	 * @return the minQtyMonth
	 */
	public String getMinQtyMonth() {
		return minQtyMonth;
	}

	/**
	 * @param minQtyMonth the minQtyMonth to set
	 */
	public void setMinQtyMonth(String minQtyMonth) {
		this.minQtyMonth = minQtyMonth;
	}

	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}

	/**
	 * @return the value1s
	 */
	public List<String> getValue1s() {
		return value1s;
	}

	/**
	 * @param value1s the value1s to set
	 */
	public void setValue1s(List<String> value1s) {
		this.value1s = value1s;
	}

	/**
	 * @return the names
	 */
	public List<String> getNames() {
		return names;
	}

	/**
	 * @param names the names to set
	 */
	public void setNames(List<String> names) {
		this.names = names;
	}

	/**
	 * @return the chartVOs
	 */
	public List<ChartVO> getChartVOs() {
		return chartVOs;
	}

	/**
	 * @param chartVOs the chartVOs to set
	 */
	public void setChartVOs(List<ChartVO> chartVOs) {
		this.chartVOs = chartVOs;
	}


	
	
	
	
}
