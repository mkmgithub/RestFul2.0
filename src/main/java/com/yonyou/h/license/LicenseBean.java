package com.yonyou.h.license;

import com.ufida.hap.webservice.ServerLicense;

public class LicenseBean implements ServerLicense {

	public boolean verification() {
		return true;
	}

}
