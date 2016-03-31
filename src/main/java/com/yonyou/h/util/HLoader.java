package com.yonyou.h.util;

import javax.servlet.ServletContext;

import com.ufida.hap.context.loader.ext.SystemLoader;
import com.ufida.hap.core.exception.FrameworkException;
import com.ufida.hap.util.ConvertUtils;

public class HLoader implements SystemLoader {

	private String hisDaoBeanName;
	private Boolean usePayFee;

	public Boolean getUsePayFee() {
		return usePayFee;
	}

	public void setUsePayFee(Boolean usePayFee) {
		this.usePayFee = usePayFee;
	}

	public void init(ServletContext sc) throws FrameworkException {
		/*ConversionService hcs =  ConvertUtils.getConversionService();
		
		ConvertUtils.addConverter(new DateToIntegerConverter(hcs));
		ConvertUtils.addConverter(new IntegerToDateConverter(hcs));*/
	}

	public void destroy() throws FrameworkException {
		// TODO Auto-generated method stub

	}

	public String getHisDaoBeanName() {
		return hisDaoBeanName;
	}

	public void setHisDaoBeanName(String hisDaoBeanName) {
		this.hisDaoBeanName = hisDaoBeanName;
	}

}
