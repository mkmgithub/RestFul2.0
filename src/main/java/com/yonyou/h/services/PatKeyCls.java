package com.yonyou.h.services;


public class PatKeyCls {

	private Integer keyType;
	private String keyWhere;
	private String keyConvertString;
	private String keyAlis;

	
	public String getKeyAlis() {
		return keyAlis;
	}

	public void setKeyAlis(String keyAlis) {
		this.keyAlis = keyAlis;
	}

	public String getKeyWhere() {
		return keyWhere;
	}

	public void setKeyWhere(String keyWhere) {
		this.keyWhere = keyWhere;
	}

	public Integer getKeyType() {
		return keyType;
	}

	public void setKeyType(Integer keyType) {
		this.keyType = keyType;
	}

	public String getKeyConvertString() {
		return keyConvertString;
	}

	public void setKeyConvertString(String keyConvertString) {
		this.keyConvertString = keyConvertString;
	}	

	public PatKeyCls(Integer iType, String cvKey, String keyWhere) {
		setKeyType(iType);
		setKeyConvertString(cvKey);
		setKeyAlis(cvKey);
		setKeyWhere(keyWhere);
	}
	
	public PatKeyCls(Integer iType, String cvKey, String keyWhere, String Alis) {
		this(iType, cvKey, keyWhere);
		setKeyAlis(Alis);
	}
	
}
