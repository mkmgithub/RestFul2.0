package com.yonyou.h.domain.person;

import org.dom4j.Document;

import com.yonyou.h.util.BuildXml;

public class HistoryDisease {
	private String hdtype;
	private String hdtypename;
	private String dissn;
	private String disopertype;
	private String icd10;
	private String disease;
	private String diagnosedate;
	private String happendate;
	private String hdreason;
	private String resultcd;
	private String resultname;
	private String curedes;
	private String curehos;

	public Document buildHistoryDisease() {
		return BuildXml.buildDocument(this, HistoryDisease.class);
	}
	
	public String getHdtype() {
		return hdtype;
	}

	public void setHdtype(String hdtype) {
		this.hdtype = hdtype;
	}

	public String getHdtypename() {
		return hdtypename;
	}

	public void setHdtypename(String hdtypename) {
		this.hdtypename = hdtypename;
	}

	public String getDissn() {
		return dissn;
	}

	public void setDissn(String dissn) {
		this.dissn = dissn;
	}

	public String getDisopertype() {
		return disopertype;
	}

	public void setDisopertype(String disopertype) {
		this.disopertype = disopertype;
	}

	public String getIcd10() {
		return icd10;
	}

	public void setIcd10(String icd10) {
		this.icd10 = icd10;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getDiagnosedate() {
		return diagnosedate;
	}

	public void setDiagnosedate(String diagnosedate) {
		this.diagnosedate = diagnosedate;
	}

	public String getHappendate() {
		return happendate;
	}

	public void setHappendate(String happendate) {
		this.happendate = happendate;
	}

	public String getHdreason() {
		return hdreason;
	}

	public void setHdreason(String hdreason) {
		this.hdreason = hdreason;
	}

	public String getResultcd() {
		return resultcd;
	}

	public void setResultcd(String resultcd) {
		this.resultcd = resultcd;
	}

	public String getResultname() {
		return resultname;
	}

	public void setResultname(String resultname) {
		this.resultname = resultname;
	}

	public String getCuredes() {
		return curedes;
	}

	public void setCuredes(String curedes) {
		this.curedes = curedes;
	}

	public String getCurehos() {
		return curehos;
	}

	public void setCurehos(String curehos) {
		this.curehos = curehos;
	}

}
