package com.yonyou.h.domain.person;


import org.dom4j.Document;
import com.yonyou.h.util.BuildXml;

public class Member {
	private String memberid;
	private String residentid;
	private String membername;
	private String sex;
	private String birthday;
	private String credentials;
	private String credentialsno;
	private String education;
	private String maritalstatus;
	private String telephone;
	private String mobilephone;
	private String householder;
	private String relationtohouseholder;
	private String address;
	private String industry;
	private String occupation;
	private String archivesstatus;
	private String remark;

	public Document buildMemberDocument() {
		return BuildXml.buildDocument(this, Member.class);		
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getResidentid() {
		return residentid;
	}

	public void setResidentid(String residentid) {
		this.residentid = residentid;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getCredentialsno() {
		return credentialsno;
	}

	public void setCredentialsno(String credentialsno) {
		this.credentialsno = credentialsno;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getHouseholder() {
		return householder;
	}

	public void setHouseholder(String householder) {
		this.householder = householder;
	}

	public String getRelationtohouseholder() {
		return relationtohouseholder;
	}

	public void setRelationtohouseholder(String relationtohouseholder) {
		this.relationtohouseholder = relationtohouseholder;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getArchivesstatus() {
		return archivesstatus;
	}

	public void setArchivesstatus(String archivesstatus) {
		this.archivesstatus = archivesstatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
