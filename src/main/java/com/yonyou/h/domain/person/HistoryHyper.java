package com.yonyou.h.domain.person;

import org.dom4j.Document;

import com.yonyou.h.util.BuildXml;

public class HistoryHyper {

	private String hypertypecd;
	private String hypertypename;
	private String hypersn;
	private String hypersource;
	private String happendate;
	private String hyperreason;
	private String curedes;

	public Document buildHistoryHyper() {
		return BuildXml.buildDocument(this, HistoryHyper.class);
	}

	public String getHypertypecd() {
		return hypertypecd;
	}

	public void setHypertypecd(String hypertypecd) {
		this.hypertypecd = hypertypecd;
	}

	public String getHypertypename() {
		return hypertypename;
	}

	public void setHypertypename(String hypertypename) {
		this.hypertypename = hypertypename;
	}

	public String getHypersn() {
		return hypersn;
	}

	public void setHypersn(String hypersn) {
		this.hypersn = hypersn;
	}

	public String getHypersource() {
		return hypersource;
	}

	public void setHypersource(String hypersource) {
		this.hypersource = hypersource;
	}

	public String getHappendate() {
		return happendate;
	}

	public void setHappendate(String happendate) {
		this.happendate = happendate;
	}

	public String getHyperreason() {
		return hyperreason;
	}

	public void setHyperreason(String hyperreason) {
		this.hyperreason = hyperreason;
	}

	public String getCuredes() {
		return curedes;
	}

	public void setCuredes(String curedes) {
		this.curedes = curedes;
	}

}
