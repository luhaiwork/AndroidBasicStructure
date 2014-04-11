/**
 * 
 */
package com.example.mainproject.models;

/**
 * </br>
 * 
 * @author luh </br>
 * @time 2014年3月12日 下午2:28:32 </br>
 * 
 */
public class Province {
	private String provinceCode;
	private String provinceName;
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
//		return super.toString();
		return provinceName+"-"+provinceCode;
	}
	
}
