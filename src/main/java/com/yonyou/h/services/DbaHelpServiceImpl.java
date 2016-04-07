package com.yonyou.h.services;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ufida.hap.core.exception.InfrastructureException;
import com.ufida.hap.util.ConvertUtils;
import com.ufida.hap.util.DBAgent;
import com.ufida.hap.web.servlet.ExcludeJsonServer;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DbaHelpServiceImpl implements DbaHelpService, ExcludeJsonServer {
	private static boolean checkSeq = false;

	private DBAgent db;

	private DBAgent getDBAgent() {
		if (db == null)
			db = DBAgent.getInstance();
		return db;
	}

	private static void autoBuildSeq(String seqName, DBAgent db) {
		if (!checkSeq)
			return;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) as RS from user_sequences where sequence_name='").append(seqName.toUpperCase()).append("'");
		List<Map<String, Object>> data = db.querySQL(sql.toString(), null);
		Integer v = ConvertUtils.toInteger(data.get(0).get("RS"));// 除非db出错不然不能没有数据
		if (0 == v) {
			sql = new StringBuffer();
			sql.append("create sequence ").append(seqName).append(" minvalue 1 maxvalue 2147483647 start with 1 increment by 1 cache 300");
			db.executeSQL(sql.toString(), null);
		}
	}

	public Long selectSysID(long lx) {
		return selectSysID(lx, getDBAgent());
	}

	/**
	 * @param lx
	 * @param db
	 * @return
	 */
	public static Long selectSysID(long lx, DBAgent db) {
		if (db == null) {
			throw new InfrastructureException("数据工具dbagent不能为空");
		}
		StringBuffer seq = new StringBuffer();
		seq.append("SEQ_ZD_").append(lx);
		autoBuildSeq(seq.toString(), db);// 检查是否建 立索引
		seq.insert(0, "select ").append(".nextval AS RS from dual");
		List<Map<String, Object>> data = db.querySQL(seq.toString(), null);
		if (data.size() == 0)
			throw new InfrastructureException("不存在序列为" + lx);
		Long v = ConvertUtils.toLong(data.get(0).get("RS"));
		if (v == null)
			throw new InfrastructureException("不能取得序例" + lx + "的值");
		return v;
	}

	public Long[] selectSysIDWithTime(long lx, String RestRq, String sqbm) {
		if (true)
			throw new InfrastructureException("不再支持按日期生成id");
		return null;
		// return selectSysIDWithTime(lx, RestRq, sqbm, getDBAgent());
	}

	// public static Long[] selectSysIDWithTime(long lx, String RestRq, String
	// sqbm, DBAgent db) {
	// String sql = "{call APKG_PACKAGE.ASP_GetID_DAY(?,?,?,?)}";
	//
	// List con = new ArrayList();
	// con.add(sqbm);
	// con.add(RestRq);
	// con.add(lx);
	// List<List<Map>> ret = getProcValue(sql, con, db);
	// if (ret.size() > 0) {
	// Map rs = ret.get(0).get(0);
	// if (rs.size() > 0) {
	// LongDataTime ldt = new LongDataTime();
	// Long[] rt = new Long[] { Long.parseLong(rs.get("ID").toString()), new
	// Long(ldt.TimeToInt(rs.get("T").toString())) };
	//
	// return rt;
	// }
	// }
	// return null;
	// }
	public Long[] selectSysIDs(long lx, Integer count) {
		return selectSysIDs(lx, count, getDBAgent());
	}

	/**
	 * 可独立事务
	 * 
	 * @param lx
	 * @param count
	 * @param db
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Long[] selectSysIDs(long lx, Integer count, DBAgent db) {
		if (db == null) {
			throw new InfrastructureException("数据工具dbagent不能为空");
		}
		Long[] ids = new Long[count];
		StringBuffer seq = new StringBuffer();
		seq.append("SEQ_ZD_").append(lx);
		autoBuildSeq(seq.toString(), db);// 检查是否建 立索引
		seq.insert(0, "select ").append(".nextval AS RS from dual");
		for (int i = 0; i < count; i++) {
			List<Map<String, Object>> data = db.querySQL(seq.toString(), null);
			if (data.size() == 0)
				throw new InfrastructureException("不存在序列为" + lx);
			Long v = ConvertUtils.toLong(data.get(0).get("RS"));
			if (v == null)
				throw new InfrastructureException("不能取得序例" + lx + "的值");
			ids[i] = v;
		}
		return ids;
	}

	public Boolean getCheckSeq() {
		return checkSeq;
	}

	public void setCheckSeq(Boolean checkSeq) {
		DbaHelpServiceImpl.checkSeq = checkSeq;
	}
}
