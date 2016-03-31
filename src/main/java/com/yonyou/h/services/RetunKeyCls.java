package com.yonyou.h.services;

public class RetunKeyCls {

	/**
	 * 1 对应转换方式  RegNo 直接转为 EMPI
	 * 2 固定值  AdmType --> 值门诊
	 * 3 直接结果转换      1=男,2=女
	 * 4 使用对应的参数转换
	 * 
	 * 9 暂无对照，不处理
	 */
	public enum ReturnKind  {rkAll, rkLis, rkPACS};
	
	
	private Integer returnType;
	private String originalKey;
	private String convertKey;
	private ReturnKind rtKind;
		
	public ReturnKind getRtKind() {
		return rtKind;
	}

	public void setRtKind(ReturnKind rtKind) {
		this.rtKind = rtKind;
	}

	public Integer getReturnType() {
		return returnType;
	}

	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	public RetunKeyCls(){
		setReturnType(9);
		setRtKind( ReturnKind.rkAll);
	}
	
	public RetunKeyCls(String okey){
		this();
		setOriginalKey(okey);
		setReturnType(1);		
	}
	
	public RetunKeyCls(ReturnKind rKind){
		this();
		setRtKind(rKind);
	}
	
	public RetunKeyCls(Integer returnType, String okey,String convKey){
		this();
		setReturnType(returnType);
		setOriginalKey(okey);
		setConvertKey(convKey);		
	}
	
	public RetunKeyCls(Integer returnType, String okey,String convKey, ReturnKind rKind){
		this(returnType, okey, convKey);
		setRtKind( rKind);		
	}		
	
	public String getOriginalKey() {
		return originalKey;
	}

	public void setOriginalKey(String originalKey) {
		this.originalKey = originalKey;
	}

	public String getConvertKey() {
		return convertKey;
	}

	public void setConvertKey(String convertKey) {
		this.convertKey = convertKey;
	}
	
	
}
