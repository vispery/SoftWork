package dto;

import java.io.Serializable;

/**
 * @author ztang
 * generated at : Mar 9, 2015 - 2:36:02 PM
 */

public class Drug implements Serializable{

	private static final long serialVersionUID = 1L;
	public Drug() {
		super();
	}

	private String drugId;
	public String getDrugId() {	return drugId;	}
	public void setDrugId(String drugId) {	this.drugId = drugId;	}
	
	private String fdbGcnSeqNum;
	public String getFdbGcnSeqNum() {	return fdbGcnSeqNum;	}
	public void setFdbGcnSeqNum(String fdbGcnSeqNum) {	this.fdbGcnSeqNum = fdbGcnSeqNum;	}
		
	private String ndcId;
	public String getNdcId() {	return ndcId;	}
	public void setNdcId(String ndcId) {	this.ndcId = ndcId;	}
	
	private String brandName;
	public String getBrandName() {	return brandName;	}
	public void setBrandName(String brandName) {	this.brandName = brandName;	}
	
	private String route;
	public String getRoute() {		return route;	}
	public void setRoute(String route) {	this.route = route;	}

	private String genericName;
	public String getGenericName() {return genericName;	}
	public void setGenericName(String genericName) {	this.genericName = genericName;	}	
	
	private int strength;
	public int getStrength() {	return strength;	}
	public void setStrength(int strength) {	this.strength = strength;	}

	

}