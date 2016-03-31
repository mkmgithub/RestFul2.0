package com.yonyou.h.domain.person;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.yonyou.h.util.BuildXml;

public class FamilyInfo {

	private String familytelephone;
	private String familytypecd;
	private String dwelltype;
	private String housingproperty;
	private String economics;
	private String grossincome;
	private String grosscharge;
	private String incomecd;
	private String healthcarecontractflag;
	private String householdcd;
	private String area;
	private String avgarea;
	private String population;
	private String populationnow;
	private String housingrooms;
	private String floortypecd;
	private String kitchenusecd;
	private String housinglighting;
	private String housingventilation;
	private String housingwarm;
	private String airhumidity;
	private String healthstatus;
	private String waterstatus;
	private String sewagetreatment;
	private String stylisticdevices;
	private String smokeremoval;
	private String kitchenfancd;
	private String watercd;
	private String fuelcd;
	private String fuelname;
	private String santoiletcd;
	private String notsantoiletcd;
	private String animalplacecd;
	private String garbagedealcd;
	private String appliancecd;
	private String transport;

	private List<Member> familymember;

	public Document buildFamilyInfoDocument() {
		Document doc = BuildXml.buildDocument(this, FamilyInfo.class);
		Element mEle = (Element) doc.selectSingleNode("//"+FamilyInfo.class.getSimpleName().toLowerCase());
		mEle = mEle.addElement("familymember");
		for (Member mber : this.familymember) {
			mEle.appendContent(mber.buildMemberDocument().getRootElement());
		}
		return doc;
	}

	public List<Member> getFamilymember() {
		return familymember;
	}

	public void setFamilymember(List<Member> familymember) {
		this.familymember = familymember;
	}

	public String getFamilytelephone() {
		return familytelephone;
	}

	public void setFamilytelephone(String familytelephone) {
		this.familytelephone = familytelephone;
	}

	public String getFamilytypecd() {
		return familytypecd;
	}

	public void setFamilytypecd(String familytypecd) {
		this.familytypecd = familytypecd;
	}

	public String getDwelltype() {
		return dwelltype;
	}

	public void setDwelltype(String dwelltype) {
		this.dwelltype = dwelltype;
	}

	public String getHousingproperty() {
		return housingproperty;
	}

	public void setHousingproperty(String housingproperty) {
		this.housingproperty = housingproperty;
	}

	public String getEconomics() {
		return economics;
	}

	public void setEconomics(String economics) {
		this.economics = economics;
	}

	public String getGrossincome() {
		return grossincome;
	}

	public void setGrossincome(String grossincome) {
		this.grossincome = grossincome;
	}

	public String getGrosscharge() {
		return grosscharge;
	}

	public void setGrosscharge(String grosscharge) {
		this.grosscharge = grosscharge;
	}

	public String getIncomecd() {
		return incomecd;
	}

	public void setIncomecd(String incomecd) {
		this.incomecd = incomecd;
	}

	public String getHealthcarecontractflag() {
		return healthcarecontractflag;
	}

	public void setHealthcarecontractflag(String healthcarecontractflag) {
		this.healthcarecontractflag = healthcarecontractflag;
	}

	public String getHouseholdcd() {
		return householdcd;
	}

	public void setHouseholdcd(String householdcd) {
		this.householdcd = householdcd;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAvgarea() {
		return avgarea;
	}

	public void setAvgarea(String avgarea) {
		this.avgarea = avgarea;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getPopulationnow() {
		return populationnow;
	}

	public void setPopulationnow(String populationnow) {
		this.populationnow = populationnow;
	}

	public String getHousingrooms() {
		return housingrooms;
	}

	public void setHousingrooms(String housingrooms) {
		this.housingrooms = housingrooms;
	}

	public String getFloortypecd() {
		return floortypecd;
	}

	public void setFloortypecd(String floortypecd) {
		this.floortypecd = floortypecd;
	}

	public String getKitchenusecd() {
		return kitchenusecd;
	}

	public void setKitchenusecd(String kitchenusecd) {
		this.kitchenusecd = kitchenusecd;
	}

	public String getHousinglighting() {
		return housinglighting;
	}

	public void setHousinglighting(String housinglighting) {
		this.housinglighting = housinglighting;
	}

	public String getHousingventilation() {
		return housingventilation;
	}

	public void setHousingventilation(String housingventilation) {
		this.housingventilation = housingventilation;
	}

	public String getHousingwarm() {
		return housingwarm;
	}

	public void setHousingwarm(String housingwarm) {
		this.housingwarm = housingwarm;
	}

	public String getAirhumidity() {
		return airhumidity;
	}

	public void setAirhumidity(String airhumidity) {
		this.airhumidity = airhumidity;
	}

	public String getHealthstatus() {
		return healthstatus;
	}

	public void setHealthstatus(String healthstatus) {
		this.healthstatus = healthstatus;
	}

	public String getWaterstatus() {
		return waterstatus;
	}

	public void setWaterstatus(String waterstatus) {
		this.waterstatus = waterstatus;
	}

	public String getSewagetreatment() {
		return sewagetreatment;
	}

	public void setSewagetreatment(String sewagetreatment) {
		this.sewagetreatment = sewagetreatment;
	}

	public String getStylisticdevices() {
		return stylisticdevices;
	}

	public void setStylisticdevices(String stylisticdevices) {
		this.stylisticdevices = stylisticdevices;
	}

	public String getSmokeremoval() {
		return smokeremoval;
	}

	public void setSmokeremoval(String smokeremoval) {
		this.smokeremoval = smokeremoval;
	}

	public String getKitchenfancd() {
		return kitchenfancd;
	}

	public void setKitchenfancd(String kitchenfancd) {
		this.kitchenfancd = kitchenfancd;
	}

	public String getWatercd() {
		return watercd;
	}

	public void setWatercd(String watercd) {
		this.watercd = watercd;
	}

	public String getFuelcd() {
		return fuelcd;
	}

	public void setFuelcd(String fuelcd) {
		this.fuelcd = fuelcd;
	}

	public String getFuelname() {
		return fuelname;
	}

	public void setFuelname(String fuelname) {
		this.fuelname = fuelname;
	}

	public String getSantoiletcd() {
		return santoiletcd;
	}

	public void setSantoiletcd(String santoiletcd) {
		this.santoiletcd = santoiletcd;
	}

	public String getNotsantoiletcd() {
		return notsantoiletcd;
	}

	public void setNotsantoiletcd(String notsantoiletcd) {
		this.notsantoiletcd = notsantoiletcd;
	}

	public String getAnimalplacecd() {
		return animalplacecd;
	}

	public void setAnimalplacecd(String animalplacecd) {
		this.animalplacecd = animalplacecd;
	}

	public String getGarbagedealcd() {
		return garbagedealcd;
	}

	public void setGarbagedealcd(String garbagedealcd) {
		this.garbagedealcd = garbagedealcd;
	}

	public String getAppliancecd() {
		return appliancecd;
	}

	public void setAppliancecd(String appliancecd) {
		this.appliancecd = appliancecd;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

}
