package com.yonyou.h.domain.person;

import org.dom4j.Document;

import com.yonyou.h.util.BuildXml;

public class PersonBaseInfo {
	private String empi;
	private String cardid;
	private String familyid;
	private String residentid;
	private String residentname;
	private String sexcd;
	private String sexname;
	private String birthday;
	private String credentials;
	private String papernum;
	private String addresstype;
	private String addresstypename;
	private String registeraddressflag;
	private String nowcountry;
	private String nowprovince;
	private String nowcity;
	private String nowdistrict;
	private String nowstreet;
	private String nowzone;
	private String nowroadcd;
	private String nowroad;
	private String nown;
	private String nowh;
	private String nows;
	private String nowother;
	private String regpostcode;
	private String regcountry;
	private String regprovince;
	private String regcity;
	private String regdistrict;
	private String regstreet;
	private String regzone;
	private String regroad;
	private String regn;
	private String regh;
	private String regs;
	private String regother;
	private String regdetail;
	private String residestatuscd;
	private String residestatusname;
	private String regtypecd;
	private String regtypename;
	private String regaddress;
	private String workunit;
	private String workdate;
	private String selfphone;
	private String mobilephone;
	private String relaname;
	private String relaphone;
	private String residecd;
	private String residename;
	private String folkcd;
	private String bloodcd;
	private String bloodname;
	private String bloodrh;
	private String educationcd;
	private String educationname;
	private String vocationcd;
	private String marriagecd;
	private String marriagename;
	private String insurancecd;
	private String insurancename;
	private String insurancenum;
	private String signcontract;
	private String signdate;
	private String aidcd;
	private String aidname;
	private String medicalfeepay;
	private String nationalitycd;
	private String relation;
	private String zip;
	private String email;
	private String qq;
	private String msn;
	private String wechat;
	private String manualnm;
	private String allergyflag;
	private String operationflag;
	private String traumaflag;
	private String bloodtransfusionflag;
	private String dutydoctor;
	private String manageorg;
	private String station;
	private String builddate;
	private String builder;
	private String buildorg;
	private String height;
	private String weight;
	private String bmi;
	private String bust;
	private String hip;
	private String waist;
	private String filestatuscd;
	private String filestatusname;
	private String familypaperarchives;
	private String landlordname;
	private String landlordphone;
	private String familytelephone;
	private String familytypecd;
	private String familytypename;
	private String dwelltype;
	private String housingproperty;
	private String economics;
	private String grossincome;
	private String grosscharge;
	private String incomecd;
	private String incomename;
	private String healthcarecontractflag;
	private String householdcd;
	private String householdname;
	private String area;
	private String avgarea;
	private String population;
	private String populationnow;
	private String housingrooms;
	private String floortypecd;
	private String floortypename;
	private String kitchenusecd;
	private String kitchenusename;
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
	private String kitchenfanname;
	private String watercd;
	private String watername;
	private String fuelcd;
	private String fuelname;
	private String santoiletcd;
	private String santoiletname;
	private String notsantoiletcd;
	private String notsantoiletname;
	private String animalplacecd;
	private String animalplacename;
	private String garbagedealcd;
	private String garbagedealname;
	private String appliancecd;
	private String appliancename;
	private String transport;
	private String transportname;

	public Document buildPersonBaseInfo() {
		return BuildXml.buildDocument(this, PersonBaseInfo.class);
	}
	
	public String getEmpi() {
		return empi;
	}

	public void setEmpi(String empi) {
		this.empi = empi;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getFamilyid() {
		return familyid;
	}

	public void setFamilyid(String familyid) {
		this.familyid = familyid;
	}

	public String getResidentid() {
		return residentid;
	}

	public void setResidentid(String residentid) {
		this.residentid = residentid;
	}

	public String getResidentname() {
		return residentname;
	}

	public void setResidentname(String residentname) {
		this.residentname = residentname;
	}

	public String getSexcd() {
		return sexcd;
	}

	public void setSexcd(String sexcd) {
		this.sexcd = sexcd;
	}

	public String getSexname() {
		return sexname;
	}

	public void setSexname(String sexname) {
		this.sexname = sexname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getPapernum() {
		return papernum;
	}

	public void setPapernum(String papernum) {
		this.papernum = papernum;
	}

	public String getAddresstype() {
		return addresstype;
	}

	public void setAddresstype(String addresstype) {
		this.addresstype = addresstype;
	}

	public String getAddresstypename() {
		return addresstypename;
	}

	public void setAddresstypename(String addresstypename) {
		this.addresstypename = addresstypename;
	}

	public String getRegisteraddressflag() {
		return registeraddressflag;
	}

	public void setRegisteraddressflag(String registeraddressflag) {
		this.registeraddressflag = registeraddressflag;
	}

	public String getNowcountry() {
		return nowcountry;
	}

	public void setNowcountry(String nowcountry) {
		this.nowcountry = nowcountry;
	}

	public String getNowprovince() {
		return nowprovince;
	}

	public void setNowprovince(String nowprovince) {
		this.nowprovince = nowprovince;
	}

	public String getNowcity() {
		return nowcity;
	}

	public void setNowcity(String nowcity) {
		this.nowcity = nowcity;
	}

	public String getNowdistrict() {
		return nowdistrict;
	}

	public void setNowdistrict(String nowdistrict) {
		this.nowdistrict = nowdistrict;
	}

	public String getNowstreet() {
		return nowstreet;
	}

	public void setNowstreet(String nowstreet) {
		this.nowstreet = nowstreet;
	}

	public String getNowzone() {
		return nowzone;
	}

	public void setNowzone(String nowzone) {
		this.nowzone = nowzone;
	}

	public String getNowroadcd() {
		return nowroadcd;
	}

	public void setNowroadcd(String nowroadcd) {
		this.nowroadcd = nowroadcd;
	}

	public String getNowroad() {
		return nowroad;
	}

	public void setNowroad(String nowroad) {
		this.nowroad = nowroad;
	}

	public String getNown() {
		return nown;
	}

	public void setNown(String nown) {
		this.nown = nown;
	}

	public String getNowh() {
		return nowh;
	}

	public void setNowh(String nowh) {
		this.nowh = nowh;
	}

	public String getNows() {
		return nows;
	}

	public void setNows(String nows) {
		this.nows = nows;
	}

	public String getNowother() {
		return nowother;
	}

	public void setNowother(String nowother) {
		this.nowother = nowother;
	}

	public String getRegpostcode() {
		return regpostcode;
	}

	public void setRegpostcode(String regpostcode) {
		this.regpostcode = regpostcode;
	}

	public String getRegcountry() {
		return regcountry;
	}

	public void setRegcountry(String regcountry) {
		this.regcountry = regcountry;
	}

	public String getRegprovince() {
		return regprovince;
	}

	public void setRegprovince(String regprovince) {
		this.regprovince = regprovince;
	}

	public String getRegcity() {
		return regcity;
	}

	public void setRegcity(String regcity) {
		this.regcity = regcity;
	}

	public String getRegdistrict() {
		return regdistrict;
	}

	public void setRegdistrict(String regdistrict) {
		this.regdistrict = regdistrict;
	}

	public String getRegstreet() {
		return regstreet;
	}

	public void setRegstreet(String regstreet) {
		this.regstreet = regstreet;
	}

	public String getRegzone() {
		return regzone;
	}

	public void setRegzone(String regzone) {
		this.regzone = regzone;
	}

	public String getRegroad() {
		return regroad;
	}

	public void setRegroad(String regroad) {
		this.regroad = regroad;
	}

	public String getRegn() {
		return regn;
	}

	public void setRegn(String regn) {
		this.regn = regn;
	}

	public String getRegh() {
		return regh;
	}

	public void setRegh(String regh) {
		this.regh = regh;
	}

	public String getRegs() {
		return regs;
	}

	public void setRegs(String regs) {
		this.regs = regs;
	}

	public String getRegother() {
		return regother;
	}

	public void setRegother(String regother) {
		this.regother = regother;
	}

	public String getRegdetail() {
		return regdetail;
	}

	public void setRegdetail(String regdetail) {
		this.regdetail = regdetail;
	}

	public String getResidestatuscd() {
		return residestatuscd;
	}

	public void setResidestatuscd(String residestatuscd) {
		this.residestatuscd = residestatuscd;
	}

	public String getResidestatusname() {
		return residestatusname;
	}

	public void setResidestatusname(String residestatusname) {
		this.residestatusname = residestatusname;
	}

	public String getRegtypecd() {
		return regtypecd;
	}

	public void setRegtypecd(String regtypecd) {
		this.regtypecd = regtypecd;
	}

	public String getRegtypename() {
		return regtypename;
	}

	public void setRegtypename(String regtypename) {
		this.regtypename = regtypename;
	}

	public String getRegaddress() {
		return regaddress;
	}

	public void setRegaddress(String regaddress) {
		this.regaddress = regaddress;
	}

	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public String getWorkdate() {
		return workdate;
	}

	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}

	public String getSelfphone() {
		return selfphone;
	}

	public void setSelfphone(String selfphone) {
		this.selfphone = selfphone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getRelaname() {
		return relaname;
	}

	public void setRelaname(String relaname) {
		this.relaname = relaname;
	}

	public String getRelaphone() {
		return relaphone;
	}

	public void setRelaphone(String relaphone) {
		this.relaphone = relaphone;
	}

	public String getResidecd() {
		return residecd;
	}

	public void setResidecd(String residecd) {
		this.residecd = residecd;
	}

	public String getResidename() {
		return residename;
	}

	public void setResidename(String residename) {
		this.residename = residename;
	}

	public String getFolkcd() {
		return folkcd;
	}

	public void setFolkcd(String folkcd) {
		this.folkcd = folkcd;
	}

	public String getBloodcd() {
		return bloodcd;
	}

	public void setBloodcd(String bloodcd) {
		this.bloodcd = bloodcd;
	}

	public String getBloodname() {
		return bloodname;
	}

	public void setBloodname(String bloodname) {
		this.bloodname = bloodname;
	}

	public String getBloodrh() {
		return bloodrh;
	}

	public void setBloodrh(String bloodrh) {
		this.bloodrh = bloodrh;
	}

	public String getEducationcd() {
		return educationcd;
	}

	public void setEducationcd(String educationcd) {
		this.educationcd = educationcd;
	}

	public String getEducationname() {
		return educationname;
	}

	public void setEducationname(String educationname) {
		this.educationname = educationname;
	}

	public String getVocationcd() {
		return vocationcd;
	}

	public void setVocationcd(String vocationcd) {
		this.vocationcd = vocationcd;
	}

	public String getMarriagecd() {
		return marriagecd;
	}

	public void setMarriagecd(String marriagecd) {
		this.marriagecd = marriagecd;
	}

	public String getMarriagename() {
		return marriagename;
	}

	public void setMarriagename(String marriagename) {
		this.marriagename = marriagename;
	}

	public String getInsurancecd() {
		return insurancecd;
	}

	public void setInsurancecd(String insurancecd) {
		this.insurancecd = insurancecd;
	}

	public String getInsurancename() {
		return insurancename;
	}

	public void setInsurancename(String insurancename) {
		this.insurancename = insurancename;
	}

	public String getInsurancenum() {
		return insurancenum;
	}

	public void setInsurancenum(String insurancenum) {
		this.insurancenum = insurancenum;
	}

	public String getSigncontract() {
		return signcontract;
	}

	public void setSigncontract(String signcontract) {
		this.signcontract = signcontract;
	}

	public String getSigndate() {
		return signdate;
	}

	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}

	public String getAidcd() {
		return aidcd;
	}

	public void setAidcd(String aidcd) {
		this.aidcd = aidcd;
	}

	public String getAidname() {
		return aidname;
	}

	public void setAidname(String aidname) {
		this.aidname = aidname;
	}

	public String getMedicalfeepay() {
		return medicalfeepay;
	}

	public void setMedicalfeepay(String medicalfeepay) {
		this.medicalfeepay = medicalfeepay;
	}

	public String getNationalitycd() {
		return nationalitycd;
	}

	public void setNationalitycd(String nationalitycd) {
		this.nationalitycd = nationalitycd;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getManualnm() {
		return manualnm;
	}

	public void setManualnm(String manualnm) {
		this.manualnm = manualnm;
	}

	public String getAllergyflag() {
		return allergyflag;
	}

	public void setAllergyflag(String allergyflag) {
		this.allergyflag = allergyflag;
	}

	public String getOperationflag() {
		return operationflag;
	}

	public void setOperationflag(String operationflag) {
		this.operationflag = operationflag;
	}

	public String getTraumaflag() {
		return traumaflag;
	}

	public void setTraumaflag(String traumaflag) {
		this.traumaflag = traumaflag;
	}

	public String getBloodtransfusionflag() {
		return bloodtransfusionflag;
	}

	public void setBloodtransfusionflag(String bloodtransfusionflag) {
		this.bloodtransfusionflag = bloodtransfusionflag;
	}

	public String getDutydoctor() {
		return dutydoctor;
	}

	public void setDutydoctor(String dutydoctor) {
		this.dutydoctor = dutydoctor;
	}

	public String getManageorg() {
		return manageorg;
	}

	public void setManageorg(String manageorg) {
		this.manageorg = manageorg;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getBuilddate() {
		return builddate;
	}

	public void setBuilddate(String builddate) {
		this.builddate = builddate;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getBuildorg() {
		return buildorg;
	}

	public void setBuildorg(String buildorg) {
		this.buildorg = buildorg;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getBust() {
		return bust;
	}

	public void setBust(String bust) {
		this.bust = bust;
	}

	public String getHip() {
		return hip;
	}

	public void setHip(String hip) {
		this.hip = hip;
	}

	public String getWaist() {
		return waist;
	}

	public void setWaist(String waist) {
		this.waist = waist;
	}

	public String getFilestatuscd() {
		return filestatuscd;
	}

	public void setFilestatuscd(String filestatuscd) {
		this.filestatuscd = filestatuscd;
	}

	public String getFilestatusname() {
		return filestatusname;
	}

	public void setFilestatusname(String filestatusname) {
		this.filestatusname = filestatusname;
	}

	public String getFamilypaperarchives() {
		return familypaperarchives;
	}

	public void setFamilypaperarchives(String familypaperarchives) {
		this.familypaperarchives = familypaperarchives;
	}

	public String getLandlordname() {
		return landlordname;
	}

	public void setLandlordname(String landlordname) {
		this.landlordname = landlordname;
	}

	public String getLandlordphone() {
		return landlordphone;
	}

	public void setLandlordphone(String landlordphone) {
		this.landlordphone = landlordphone;
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

	public String getFamilytypename() {
		return familytypename;
	}

	public void setFamilytypename(String familytypename) {
		this.familytypename = familytypename;
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

	public String getIncomename() {
		return incomename;
	}

	public void setIncomename(String incomename) {
		this.incomename = incomename;
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

	public String getHouseholdname() {
		return householdname;
	}

	public void setHouseholdname(String householdname) {
		this.householdname = householdname;
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

	public String getFloortypename() {
		return floortypename;
	}

	public void setFloortypename(String floortypename) {
		this.floortypename = floortypename;
	}

	public String getKitchenusecd() {
		return kitchenusecd;
	}

	public void setKitchenusecd(String kitchenusecd) {
		this.kitchenusecd = kitchenusecd;
	}

	public String getKitchenusename() {
		return kitchenusename;
	}

	public void setKitchenusename(String kitchenusename) {
		this.kitchenusename = kitchenusename;
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

	public String getKitchenfanname() {
		return kitchenfanname;
	}

	public void setKitchenfanname(String kitchenfanname) {
		this.kitchenfanname = kitchenfanname;
	}

	public String getWatercd() {
		return watercd;
	}

	public void setWatercd(String watercd) {
		this.watercd = watercd;
	}

	public String getWatername() {
		return watername;
	}

	public void setWatername(String watername) {
		this.watername = watername;
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

	public String getSantoiletname() {
		return santoiletname;
	}

	public void setSantoiletname(String santoiletname) {
		this.santoiletname = santoiletname;
	}

	public String getNotsantoiletcd() {
		return notsantoiletcd;
	}

	public void setNotsantoiletcd(String notsantoiletcd) {
		this.notsantoiletcd = notsantoiletcd;
	}

	public String getNotsantoiletname() {
		return notsantoiletname;
	}

	public void setNotsantoiletname(String notsantoiletname) {
		this.notsantoiletname = notsantoiletname;
	}

	public String getAnimalplacecd() {
		return animalplacecd;
	}

	public void setAnimalplacecd(String animalplacecd) {
		this.animalplacecd = animalplacecd;
	}

	public String getAnimalplacename() {
		return animalplacename;
	}

	public void setAnimalplacename(String animalplacename) {
		this.animalplacename = animalplacename;
	}

	public String getGarbagedealcd() {
		return garbagedealcd;
	}

	public void setGarbagedealcd(String garbagedealcd) {
		this.garbagedealcd = garbagedealcd;
	}

	public String getGarbagedealname() {
		return garbagedealname;
	}

	public void setGarbagedealname(String garbagedealname) {
		this.garbagedealname = garbagedealname;
	}

	public String getAppliancecd() {
		return appliancecd;
	}

	public void setAppliancecd(String appliancecd) {
		this.appliancecd = appliancecd;
	}

	public String getAppliancename() {
		return appliancename;
	}

	public void setAppliancename(String appliancename) {
		this.appliancename = appliancename;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getTransportname() {
		return transportname;
	}

	public void setTransportname(String transportname) {
		this.transportname = transportname;
	}

}
