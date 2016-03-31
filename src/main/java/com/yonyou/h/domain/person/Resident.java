package com.yonyou.h.domain.person;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.yonyou.h.util.C;

public class Resident {

	private PersonBaseInfo personbaseinfo;
	private HealthInfo healthinfo;
	private PersonBehaviorhabits personbehaviorhabits;
	private List<HistoryDisease> historyinfo;
	private FmailyHistoryDisInfo fmailyhistorydisinfo;
	private FamilyInfo familyinfo;

	public Document buildResidentXml() {
		Document persondoc = DocumentHelper.createDocument();
		Element root = persondoc.addElement(C.RETURN_ROOT_NODE);
		root.addElement(C.CODE_NODE).addText("1");
		root = root.addElement(Resident.class.getSimpleName().toLowerCase());
		if (personbaseinfo != null)
			root.appendContent(personbaseinfo.buildPersonBaseInfo().getRootElement());
		if (healthinfo != null)
			root.appendContent(healthinfo.buildHealInfo().getRootElement());
		if (personbehaviorhabits != null)
			root.appendContent(personbehaviorhabits.buildPersonBehaviorhabits().getRootElement());
		if (historyinfo != null) {
			Element hiselement = root.addElement("historyinfo");
			for (HistoryDisease historydis : historyinfo) {
				hiselement.appendContent(historydis.buildHistoryDisease().getRootElement());
			}
		}
		if (fmailyhistorydisinfo != null)
			root.appendContent(fmailyhistorydisinfo.buildFmailyHistoryDisInfoDocument().getRootElement());
		if (familyinfo != null)
			root.appendContent(familyinfo.buildFamilyInfoDocument().getRootElement());
		return persondoc;
	}

	public PersonBaseInfo getPersonbaseinfo() {
		return personbaseinfo;
	}

	public void setPersonbaseinfo(PersonBaseInfo personbaseinfo) {
		this.personbaseinfo = personbaseinfo;
	}

	public HealthInfo getHealthinfo() {
		return healthinfo;
	}

	public void setHealthinfo(HealthInfo healthinfo) {
		this.healthinfo = healthinfo;
	}

	public PersonBehaviorhabits getPersonbehaviorhabits() {
		return personbehaviorhabits;
	}

	public void setPersonbehaviorhabits(PersonBehaviorhabits personbehaviorhabits) {
		this.personbehaviorhabits = personbehaviorhabits;
	}

	public List<HistoryDisease> getHistoryinfo() {
		return historyinfo;
	}

	public void setHistoryinfo(List<HistoryDisease> historyinfo) {
		this.historyinfo = historyinfo;
	}

	public FmailyHistoryDisInfo getFmailyhistorydisinfo() {
		return fmailyhistorydisinfo;
	}

	public void setFmailyhistorydisinfo(FmailyHistoryDisInfo fmailyhistorydisinfo) {
		this.fmailyhistorydisinfo = fmailyhistorydisinfo;
	}

	public FamilyInfo getFamilyinfo() {
		return familyinfo;
	}

	public void setFamilyinfo(FamilyInfo familyinfo) {
		this.familyinfo = familyinfo;
	}

}
