package com.yonyou.h.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * ÂÞºþAPP½Ó¿Ú
 */
@WebService(targetNamespace = "http://service.luapp.h.yonyou.com")
public interface LuAppService {
	

		@WebMethod
		public String GetHealthPADBus(@WebParam(name="data") String param);
		
		@WebMethod
		public String SetHealth4PADBus(@WebParam(name="data") String param);
		
		@WebMethod
		public String SetHealthPADBus(@WebParam(name="data") String param);
		
 
}
