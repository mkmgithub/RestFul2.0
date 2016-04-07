package com.yonyou.h.domain.person;

import org.dom4j.Document;

import com.yonyou.h.util.BuildXml;

public class FmailyHistoryDisInfo {
	
	private String grandparents;
	private String fathercd;
	private String mothercd;
	private String brothercd;
	private String childcd;
	private String othermembercd;

	public Document buildFmailyHistoryDisInfoDocument() {
		return BuildXml.buildDocument(this, FmailyHistoryDisInfo.class);
	}
	
	public String getGrandparents() {
		return grandparents;
	}

	public void setGrandparents(String grandparents) {
		this.grandparents = grandparents;
	}

	public String getFathercd() {
		return fathercd;
	}

	public void setFathercd(String fathercd) {
		this.fathercd = fathercd;
	}

	public String getMothercd() {
		return mothercd;
	}

	public void setMothercd(String mothercd) {
		this.mothercd = mothercd;
	}

	public String getBrothercd() {
		return brothercd;
	}

	public void setBrothercd(String brothercd) {
		this.brothercd = brothercd;
	}

	public String getChildcd() {
		return childcd;
	}

	public void setChildcd(String childcd) {
		this.childcd = childcd;
	}

	public String getOthermembercd() {
		return othermembercd;
	}

	public void setOthermembercd(String othermembercd) {
		this.othermembercd = othermembercd;
	}

}
