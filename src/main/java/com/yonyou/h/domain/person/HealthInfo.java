package com.yonyou.h.domain.person;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.yonyou.h.util.BuildXml;

public class HealthInfo {

	private String deformitycd;
	private String deformitycardno;
	private String deformitylevel;
	private String hereditycd;
	private String heredityname;
	private String exposurecd;
	
	private List<HistoryHyper> historyhyper;

	public Document buildHealInfo() {
		Document doc= BuildXml.buildDocument(this, HealthInfo.class);
		Element mEle = (Element) doc.selectSingleNode("//"+HealthInfo.class.getSimpleName().toLowerCase());
		mEle = mEle.addElement("historyhyper");
		for (HistoryHyper hyper : this.historyhyper) {
			mEle.appendContent(hyper.buildHistoryHyper().getRootElement());
		}
		return doc;
	}

	
	public List<HistoryHyper> getHistoryhyper() {
		return historyhyper;
	}


	public void setHistoryhyper(List<HistoryHyper> historyhyper) {
		this.historyhyper = historyhyper;
	}


	public String getDeformitycd() {
		return deformitycd;
	}

	public void setDeformitycd(String deformitycd) {
		this.deformitycd = deformitycd;
	}

	public String getDeformitycardno() {
		return deformitycardno;
	}

	public void setDeformitycardno(String deformitycardno) {
		this.deformitycardno = deformitycardno;
	}

	public String getDeformitylevel() {
		return deformitylevel;
	}

	public void setDeformitylevel(String deformitylevel) {
		this.deformitylevel = deformitylevel;
	}

	public String getHereditycd() {
		return hereditycd;
	}

	public void setHereditycd(String hereditycd) {
		this.hereditycd = hereditycd;
	}

	public String getHeredityname() {
		return heredityname;
	}

	public void setHeredityname(String heredityname) {
		this.heredityname = heredityname;
	}

	public String getExposurecd() {
		return exposurecd;
	}

	public void setExposurecd(String exposurecd) {
		this.exposurecd = exposurecd;
	}

}
