package com.yonyou.h.services;

import java.util.Date;

import com.ufida.hap.util.DBAgent;

/**
 *	公共数据查询接口
 */
public interface CommonIntf {

	/**
	 * 取数据库时间
	 * */
	public Date getDbDate(DBAgent db) ;
		
	
}
