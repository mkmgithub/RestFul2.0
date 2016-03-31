package com.yonyou.h.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ufida.hap.util.DBAgent;
import com.ufida.hap.util.DateUtils;

public class CommonIntfImpl implements CommonIntf {

	private Logger LOG = LoggerFactory.getLogger(CommonIntfImpl.class);

	/**
	 * 获取数据库时间
	 */
	public Date getDbDate(DBAgent db) {
		try{			
			List<Map<String, Object>> r = db.querySQL("select to_char(sysdate,'yyyy-mm-dd') RS from dual", null);
			Object r0 = r.get(0).get("RS");
			String sdate = r0.toString();
			String sformater = "yyyy-mm-dd";
			if (sdate.indexOf("/") != 0){
				sformater = sformater.replaceAll("-", "/");
			}
			Date d = DateUtils.parse( sdate, sformater);
			//System.out.println(d);
			return d;
		}catch(Throwable thr){
			thr.printStackTrace();
			LOG.error( thr.getMessage());
		}
		return null;
	}
	
}
