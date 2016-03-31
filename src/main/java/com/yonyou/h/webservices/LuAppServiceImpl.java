package com.yonyou.h.webservices;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.yonyou.h.services.LuPhs4AppIntf;


@WebService(endpointInterface = "com.yonyou.h.webservices.LuAppService")
public class LuAppServiceImpl implements LuAppService {

	private LuPhs4AppIntf luphs4AppIntf;
	
	

	public LuPhs4AppIntf getLuphs4AppIntf() {
		return luphs4AppIntf;
	}

	@Autowired
	public void setLuphs4AppIntf(LuPhs4AppIntf luphs4AppIntf) {
		this.luphs4AppIntf = luphs4AppIntf;
	}

	public String GetHealthPADBus(String param) {		
		return luphs4AppIntf.getPHS(param);
	}

	public String SetHealth4PADBus(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	public String SetHealthPADBus(String param) {
		// TODO Auto-generated method stub
		return null;
	}		
	
}
