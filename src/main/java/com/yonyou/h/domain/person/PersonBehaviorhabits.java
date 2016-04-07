package com.yonyou.h.domain.person;

import org.dom4j.Document;

import com.yonyou.h.util.BuildXml;

public class PersonBehaviorhabits {

	private String smokecd;
	private String quitsmokingflag;
	private String smokeage;
	private String nosmokeage;
	private String smokeday;
	private String smokedaypast;
	private String drinkcd;
	private String drinktypecd;
	private String drinkamount;
	private String nodrinkcd;
	private String nodrinkage;
	private String pastdrinknum;
	private String pastdrinkamount;
	private String pastdrinktypecd;
	private String foodcd;
	private String brushteethcd;
	private String sportratecd;
	private String sporttypecd;
	private String sporttypeelse;
	private String sporttime;
	private String primaryeventcd;

	public Document buildPersonBehaviorhabits() {
		return BuildXml.buildDocument(this, PersonBehaviorhabits.class);
	}

	public String getSmokecd() {
		return smokecd;
	}

	public void setSmokecd(String smokecd) {
		this.smokecd = smokecd;
	}

	public String getQuitsmokingflag() {
		return quitsmokingflag;
	}

	public void setQuitsmokingflag(String quitsmokingflag) {
		this.quitsmokingflag = quitsmokingflag;
	}

	public String getSmokeage() {
		return smokeage;
	}

	public void setSmokeage(String smokeage) {
		this.smokeage = smokeage;
	}

	public String getNosmokeage() {
		return nosmokeage;
	}

	public void setNosmokeage(String nosmokeage) {
		this.nosmokeage = nosmokeage;
	}

	public String getSmokeday() {
		return smokeday;
	}

	public void setSmokeday(String smokeday) {
		this.smokeday = smokeday;
	}

	public String getSmokedaypast() {
		return smokedaypast;
	}

	public void setSmokedaypast(String smokedaypast) {
		this.smokedaypast = smokedaypast;
	}

	public String getDrinkcd() {
		return drinkcd;
	}

	public void setDrinkcd(String drinkcd) {
		this.drinkcd = drinkcd;
	}

	public String getDrinktypecd() {
		return drinktypecd;
	}

	public void setDrinktypecd(String drinktypecd) {
		this.drinktypecd = drinktypecd;
	}

	public String getDrinkamount() {
		return drinkamount;
	}

	public void setDrinkamount(String drinkamount) {
		this.drinkamount = drinkamount;
	}

	public String getNodrinkcd() {
		return nodrinkcd;
	}

	public void setNodrinkcd(String nodrinkcd) {
		this.nodrinkcd = nodrinkcd;
	}

	public String getNodrinkage() {
		return nodrinkage;
	}

	public void setNodrinkage(String nodrinkage) {
		this.nodrinkage = nodrinkage;
	}

	public String getPastdrinknum() {
		return pastdrinknum;
	}

	public void setPastdrinknum(String pastdrinknum) {
		this.pastdrinknum = pastdrinknum;
	}

	public String getPastdrinkamount() {
		return pastdrinkamount;
	}

	public void setPastdrinkamount(String pastdrinkamount) {
		this.pastdrinkamount = pastdrinkamount;
	}

	public String getPastdrinktypecd() {
		return pastdrinktypecd;
	}

	public void setPastdrinktypecd(String pastdrinktypecd) {
		this.pastdrinktypecd = pastdrinktypecd;
	}

	public String getFoodcd() {
		return foodcd;
	}

	public void setFoodcd(String foodcd) {
		this.foodcd = foodcd;
	}

	public String getBrushteethcd() {
		return brushteethcd;
	}

	public void setBrushteethcd(String brushteethcd) {
		this.brushteethcd = brushteethcd;
	}

	public String getSportratecd() {
		return sportratecd;
	}

	public void setSportratecd(String sportratecd) {
		this.sportratecd = sportratecd;
	}

	public String getSporttypecd() {
		return sporttypecd;
	}

	public void setSporttypecd(String sporttypecd) {
		this.sporttypecd = sporttypecd;
	}

	public String getSporttypeelse() {
		return sporttypeelse;
	}

	public void setSporttypeelse(String sporttypeelse) {
		this.sporttypeelse = sporttypeelse;
	}

	public String getSporttime() {
		return sporttime;
	}

	public void setSporttime(String sporttime) {
		this.sporttime = sporttime;
	}

	public String getPrimaryeventcd() {
		return primaryeventcd;
	}

	public void setPrimaryeventcd(String primaryeventcd) {
		this.primaryeventcd = primaryeventcd;
	}

}
