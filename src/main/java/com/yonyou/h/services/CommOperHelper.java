/**
 * $Author: liyjb $
 * $Rev: 3058 $
 * $Date:: 2010-04-22 18:18:22 +#$:
 *
 * Copyright (c) 2010 UFIDA, Inc. All rights reserved.
 *
 * This software is the proprietary information of UFIDA, Inc.
 * Use is subject to license terms.
 */
package com.yonyou.h.services;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.ufida.g3.domain.common.PersonIdx;
import com.ufida.g3.domain.da.DaDazt;
import com.ufida.g3.domain.da.PoDaDaGrda0;
import com.ufida.g3.domain.dic.DaSysforms;
import com.ufida.g3.domain.region.WsxxBa;
import com.ufida.g3.domain.vo.VoGrJkDaTree;
import com.ufida.hap.webservice.util.WebServiceUtils;
import com.ufida.hap.core.exception.AppException;
import com.ufida.hap.hibernate.SQLCriterionFix;
import com.ufida.hap.util.BeanUtils;
import com.ufida.hap.util.ConvertUtils;
import com.ufida.hap.util.DBAgent;
import com.ufida.hap.util.StringUtils;

public class CommOperHelper {
	private static String[] DAMark = { "NONE", "YES", "NO" };
	public static final String linkFlag = "_";
	// 对应DELPHI和数据库的FORMID常量
	public static final String flagETDA = "ETDA"; // ok
	public static final String flagETTJ = "ETTJ"; // ok
	// public static final String flagFNDA = "FNDA"; //已不使用
	public static final String flagYCFDA = "YCFDA"; // ok
	public static final String flagLNRDA = "LNRDA"; // ok
	public static final String flagTNBDA = "TNBDA"; // ok
	public static final String flagGXYDA = "GXYDA"; // ok
	public static final String flagMBSFGL = "MBSFGL";
	public static final String flagZLDA = "ZLDA"; // OK
	public static final String flagCJRDA = "CJRDA"; // OK
	public static final String flagJSBDA = "JSBDA"; // OK
	public static final String flagETTRDA = "ETTRDA"; // ok
	public static final String flagETSWJL = "ETSWJL"; // ok
	public static final String flagXSESWJL = "XSESWJL"; // ok
	public static final String flagYCFSWJL = "YCFSWJL"; // ok
	public static final String flagYCFCQSJ = "YCFDYCCQSF";
	public static final String flagYCFCQFC = "YCF2D5CCQSFLB";
	public static final String flagYCFFMJL = "YCFFNBJFMJL";
	public static final String flagCHFS = "YCFSF";
	public static final String flagCH42FS = "YCFHC42TJCJL";

	// --以下列表方式的档案业务
	public static final String flagET3M = "ET3M"; // ok
	public static final String flagET6M = "ET6M"; // ok
	public static final String flagET9M = "ET9M"; // ok
	public static final String flagET1Y = "ET1Y"; // ok
	public static final String flagET1Y6M = "ET1Y6M"; // ok
	public static final String flagET2Y = "ET2Y"; // ok
	public static final String flagET3Y = "ET3Y"; // ok
	public static final String flagET2Y6M = "ET2Y6M"; // ok
	public static final String flagET1M = "ET1M"; // ok
	public static final String flagGWRQDA = "GWRQDA";
	public static final String flagFRBGK = "FRBGK";
	public static final String flagETCHFS = "XSECHFSLB"; // ok

	public static final String[] personIdxTypes = new String[] { "sfzh", "sbh", "sbkh", "jkkh" };
	public static final int pidxT_sfzh = 0;
	public static final int pidxT_sbh = 1;
	public static final int pidxT_sbkh = 2;
	public static final int pidxT_jkkh = 3;
	public static final String PTT_WSXXBA = "WSXX_BA";

	public static final String PTT_DAGRDA0 = "DA_GRDA0";

	private static final Map<String, Fields[]> flagIndexSearchKeys = initFlagIndexSearchKeys(); // 引用唯一主索引的字段

	public static final String[] moreBsKey = new String[] { flagYCFCQSJ, flagYCFCQFC, flagYCFFMJL, flagCHFS, flagYCFDA }; // 两个以上的状态业务字段的FORMID，基本只有孕产妇档案

	public static final Map<String, Integer> bsIndex = new Hashtable<String, Integer>();
	public static final List<bsInfo> bsData = init(); // 专项档案表代码关系对照,(对应FORM的ID不一在存在于DA_SYSFORM中)

	public static final String SPACE10 = "          ";

	private static class Fields {
		public Fields(int field, Type fieldType) {
			this(field, fieldType, null);
		}

		public Fields(int field, Type fieldType, String fieldName) {
			if (fieldName != null)
				this.fieldName = fieldName;
			else
				this.fieldName = personIdxTypes[field];
			this.fieldType = fieldType;
			this.fieldCode = field;
		}

		public String fieldName;
		public Type fieldType;
		public int fieldCode;

	}

	private static final Map<String, Fields[]> initFlagIndexSearchKeys() {
		Map<String, Fields[]> rt = new HashMap<String, Fields[]>();
		rt.put(PTT_DAGRDA0, new Fields[] { new Fields(pidxT_sfzh, Hibernate.STRING),
				new Fields(pidxT_sbh, Hibernate.STRING, "sbbh"), new Fields(pidxT_sbkh, Hibernate.STRING),
				new Fields(pidxT_jkkh, Hibernate.STRING) });
		rt.put(PTT_WSXXBA, new Fields[] { new Fields(pidxT_sfzh, Hibernate.STRING),
				new Fields(pidxT_sbh, Hibernate.STRING), new Fields(pidxT_sbkh, Hibernate.STRING),
				new Fields(pidxT_jkkh, Hibernate.STRING) });
		return rt;
	}

	public static bsInfo getBsInfo(String key) {
		if (bsIndex.containsKey(key))
			return bsData.get(bsIndex.get(key));
		return null;
	}

	/**
	 * 判断这个FORMID是否为除了EMPI和FORMID之外还有YWID为业务主键的
	 * 
	 * @param formid
	 * @return
	 */
	public static boolean isMoreKey(String formid) {
		for (String item : moreBsKey)
			if (item.equalsIgnoreCase(formid))
				return true;
		return false;
	}

	private static List<bsInfo> init() {
		List<bsInfo> rt = new ArrayList<bsInfo>();
		rt.add(new bsInfo(flagETDA, "DA_ETDA", "EMPI", "JDRQ"));
		// rt.add(new bsInfo(flagFNDA, "DA_FNBJDA", "EMPI", "DJRQ"));
		rt.add(new bsInfo(flagYCFDA, "DA_YCFDA", "DAID", "JDRQ"));
		rt.add(new bsInfo(flagLNRDA, "DA_LNRDA", "EMPI", "DJRQ"));
		rt.add(new bsInfo(flagTNBDA, "DA_TNBDA", "EMPI", "JDRQ"));
		rt.add(new bsInfo(flagGXYDA, "DA_GXYDA", "EMPI", "JDRQ"));
		rt.add(new bsInfo(flagZLDA, "DA_ZLDA", "EMPI", "JDRQ"));
		rt.add(new bsInfo(flagCJRDA, "DA_CJRDA", "EMPI", "JDRQ"));
		rt.add(new bsInfo(flagJSBDA, "DA_JSBDA", "EMPI", "JDRQ"));
		rt.add(new bsInfo(flagETTRDA, "ET_TRETDA", "TRID", "JDRQ"));
		rt.add(new bsInfo(flagETSWJL, "ET_SWJL", "EMPI", "DJRQ"));
		rt.add(new bsInfo(flagXSESWJL, "YCF_XSESWJL", "EMPI", "DJRQ"));
		rt.add(new bsInfo(flagYCFSWJL, "YCF_SWJL", "EMPI", "DJRQ"));
		rt.add(new bsInfo(flagYCFCQSJ, "YCF_DYCCQSF", "DAID", "SFRQ"));
		rt.add(new bsInfo(flagYCFCQFC, "YCF_2D5CCQSF", "DAID", "SFRQ"));
		rt.add(new bsInfo(flagYCFFMJL, "YCF_FMJL", "DAID", "JDRQ"));
		rt.add(new bsInfo(flagETTJ, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagCHFS, "YCF_CHFSJL", "DAID", "FSRQ"));
		rt.add(new bsInfo(flagCH42FS, "YCF_CH42TJCJL", "DAID", "SFRQ"));
		rt.add(new bsInfo(flagGWRQDA, "DA_GWRQDA", "EMPI", "JDRQ"));
		rt.add(new bsInfo(flagFRBGK, "SQ_FRBGK", "FWID", "BGRQ"));

		rt.add(new bsInfo(flagET3M, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagET6M, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagET9M, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagET1Y, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagET1Y6M, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagET2Y, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagET3Y, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagET2Y6M, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagET1M, "ET_TJJL", "ID", "TJRQ"));
		rt.add(new bsInfo(flagETCHFS, "YCF_XSECHFS", "DAID", "FSRQ"));
		rt.add(new bsInfo(flagMBSFGL, "DA_GXYDA", "EMPI", "JDRQ"));

		for (int i = 0; i < rt.size(); i++) {
			bsIndex.put(rt.get(i).getFormid(), i);
		}
		return rt;
	}

	/**
	 * 业务档案类
	 * 
	 * @author J9
	 *
	 */
	public static class bsInfo {
		private String table; // 对应业务表
		private String ywidfield; // 业务主键FIELD
		private String jdrqfield; // 建档日期FIELD
		private String formid;

		public bsInfo(String formid, String table, String ywidfield, String jdrqfield) {
			this.formid = formid;
			this.jdrqfield = jdrqfield;
			this.table = table;
			this.ywidfield = ywidfield;
		}

		public String getTable() {
			return table;
		}

		public void setTable(String table) {
			this.table = table;
		}

		public String getYwidfield() {
			return ywidfield;
		}

		public void setYwidfield(String ywidfield) {
			this.ywidfield = ywidfield;
		}

		public String getJdrqfield() {
			return jdrqfield;
		}

		public void setJdrqfield(String jdrqfield) {
			this.jdrqfield = jdrqfield;
		}

		public String getFormid() {
			return formid;
		}

		public void setFormid(String formid) {
			this.formid = formid;
		}

	}

	private static final List<DaSysforms> sysForms = new ArrayList<DaSysforms>();

	

	/**
	 * 从业务编码转换回对应的表
	 * 
	 * @param bm
	 * @return
	 */
	public static String getTableFromBM(String bm) {
		if (bm == null || (bm.equals("")))
			return null;
		bsInfo o = getBsInfo(bm);
		if (o != null) {
			return o.getTable();
		}
		return null;
	}

	public static void AddChildNodes(List<VoGrJkDaTree> datrees, List<VoGrJkDaTree> childtrees, String bm) {
		for (VoGrJkDaTree tree : datrees) {
			if (tree.getSjbm().equalsIgnoreCase(bm)) {
				AddChildNodes(datrees, childtrees, tree.getBm());
			}
			if (tree.getBm().equalsIgnoreCase(bm)) {
				childtrees.add(tree);
			}
		}
	}

	public static boolean checkNL(Integer[] grnl, String checknl, Integer flag) {
		int iy = 0;
		int im = 0;
		if (checknl.indexOf("Y") > 0) {
			String[] nl1 = checknl.split("Y");
			iy = Integer.parseInt(nl1[0]);
			if (nl1.length > 1) {
				String[] nl2 = nl1[1].split("M");
				im = Integer.parseInt(nl2[0]);
			}
		} else {
			String[] nl1 = checknl.split("M");
			im = Integer.parseInt(nl1[0]);
		}
		if (flag == 1) { // 判断下限
			if (iy < grnl[0]) {
				return true;
			} else if ((iy == grnl[0]) && (im <= grnl[1])) {
				return true;
			} else {
				return false;
			}
		} else if (flag == 2) {// 判断上限
			if (iy > grnl[0]) {
				return true;
			} else if ((iy == grnl[0]) && (im >= grnl[1])) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	private synchronized static void getInitSysForms(DBAgent dba) {
		String sql = "SELECT * FROM DA_SYSFORMS WHERE FORMID IN (";
		// Map nullMap = new HashMap();
		for (int i = 0; i < bsData.size(); i++) {
			if (i != 0) {
				sql += ",";
			}
			sql += "'" + bsData.get(i).getFormid() + "'";
		}
		sql += ")";

		// List sysformMap = ;

		sysForms.addAll(dba.querySQL(sql, null, DaSysforms.class));
	}

	public static List<DaSysforms> getSysForms(DBAgent dba) {
		if (sysForms.size() == 0) {
			getInitSysForms(dba);
		}
		return sysForms;
	}

	/**
	 * 取得系统窗口FORM配置
	 * 
	 * @param bm
	 * @param dba
	 * @return
	 */
	public static DaSysforms getDaForms(String bm, DBAgent dba) {
		List<DaSysforms> forms = getSysForms(dba);
		for (int i = 0; i < forms.size(); i++) {
			DaSysforms form = forms.get(i);
			if (form.getFormid().equalsIgnoreCase(bm)) {
				return form;
			}
		}

		return null;
	}


	/**
	 * 处理业务档案状态-非通用(从业务逻辑中重复的动作提取出来的)
	 * 
	 * @param data
	 *            webservices原生取出来的DATASET数据。带FLAG标记的
	 * @param adddata
	 *            在前端传进来的ADD标记数据.在处理完保存时会自动填补上一些业务主键,所以要单独处理
	 * @param formid
	 * @param flag
	 *            档案状态
	 * @param mode
	 *            是否允许多条YWID,只有在DaDazt的ID为空时才生效
	 * @param dba
	 * @throws AppException
	 */
	public static void bsMarkDaStates(List[] data, List adddata, String formid, int flag, boolean mode, DBAgent dba)
			throws AppException {
		CommOperHelper.markDaStatesWithFlag(data, formid, WebServiceUtils.rowOp_add, flag, mode, dba);// 处理除了新增之外的数据
		CommOperHelper.markDaStates(adddata, formid, flag, mode, dba);// 新增要单独理理。因为新增的要保存了才有业务主键
	}

	/**
	 * 删除状态
	 * 
	 * @param formid
	 * @param data
	 * @param dba
	 */
	private static void removeDaStates(String formid, Object data, DBAgent dba) {
		Map condi = new HashMap();
		bsInfo o = getBsInfo(formid);
		Long ywid = ConvertUtils.toLong(BeanUtils.getPropertyIgnoreCase(data, o.getYwidfield()));
		Long empi = ConvertUtils.toLong(BeanUtils.getPropertyIgnoreCase(data, "EMPI"));
		condi.put("formid", formid);
		condi.put("empi", empi);
		if (ywid != null)
			condi.put("ywid", ywid);
		dba.executeHQL("delete from DaDazt where empi=:empi and formid=:formid", condi);

	}

	/**
	 * 记录业务档案的状态 (通用方法)(处理带标记的数据组)
	 * 注意：如果是删除标记。只支持业务档案单条删除。如老年人档案这些。如果是儿童体检之类的一个档案多条记录的。
	 * 则不会更新YWID为最后的记录。这时就需要把DELETE方法排除了。之后再自行在业务逻辑里处理。 另外。DATA里面一定要带EMPI
	 * 
	 * @param datas
	 * @param formid
	 * @param exclude
	 *            排除不使用通用方法处理的标记如ADD,DELETE这样。可以支持“,”号分隔
	 * @param flag
	 * @param mode
	 * @param dba
	 * @throws AppException
	 */
	public static void markDaStatesWithFlag(List[] datas, String formid, String exclude, Integer flag, boolean mode,
			DBAgent dba) throws AppException {
		List<String> flaglist = datas[1];
		List datalist = datas[0];
		String[] excludes = StringUtils.checkNullOrEmpty(exclude) ? new String[] {} : exclude.split(",");
		out: for (int i = 0; i < datalist.size(); i++) {
			String rowflag = flaglist.get(i);
			Object rowdata = datalist.get(i);
			for (String item : excludes)
				if (item.equalsIgnoreCase(rowflag))
					continue out;

			if (WebServiceUtils.rowOp_add.equalsIgnoreCase(rowflag)
					|| WebServiceUtils.rowOp_update.equalsIgnoreCase(rowflag)
					|| WebServiceUtils.rowOp_updatesimple.equalsIgnoreCase(rowflag)) {
				markDaState(rowdata, formid, flag, mode, dba);
			} else if (WebServiceUtils.rowOp_delete.equalsIgnoreCase(rowflag)) {
				// db.delete(null, data);
			}

		}

	}

	/**
	 * 记录业务档案的状态多数据处理 (通用方法)
	 * 
	 * @param data
	 * @param formid
	 * @param flag
	 * @param mode
	 * @param dba
	 * @throws AppException
	 */
	public static void markDaStates(List data, String formid, Integer flag, boolean mode, DBAgent dba)
			throws AppException {

		for (int i = 0; i < data.size(); i++) {
			Object item = data.get(i);
			markDaState(item, formid, flag, mode, dba);
		}

	}

	/**
	 * 记录业务档案的状态(通用方法)(不用自己组装DaDazt)
	 * 
	 * @param data
	 * @param empi
	 *            如果当数据里有EMPI的话这里可以不传EMPI参数
	 * @param formid
	 * @param flag
	 * @param mode
	 * @param dba
	 * @throws AppException
	 */
	public static void markDaState(Object data, String formid, Integer flag, boolean mode, DBAgent dba)
			throws AppException {
		bsInfo o = getBsInfo(formid);
		Long ywid = ConvertUtils.toLong(BeanUtils.getPropertyIgnoreCase(data, o.getYwidfield()));
		Integer jdrq = ConvertUtils.toInteger(BeanUtils.getPropertyIgnoreCase(data, o.getJdrqfield()));

		Long empi = ConvertUtils.toLong(BeanUtils.getPropertyIgnoreCase(data, "EMPI"));
		DaDazt t = new DaDazt();
		t.setFormid(formid);
		t.setEmpi(empi);
		t.setFlag(flag);
		t.setYwid(ywid);
		t.setJdrq(jdrq);
		markDaState(t, mode, dba);
	}

	/**
	 * 结案专用
	 * 
	 * @param data
	 * @param formid
	 * @param flag
	 * @param jarqFieldN
	 *            //结案日期字段名
	 * @param jaidFieldN
	 *            //结案ID字段名
	 * @param mode
	 * @param dba
	 * @throws AppException
	 */
	public static void markDaStateEnd(Object data, String formid, Integer flag, String jarqFieldN, String jaidFieldN,
			boolean mode, DBAgent dba) throws AppException {
		bsInfo o = getBsInfo(formid);
		Long ywid = ConvertUtils.toLong(BeanUtils.getPropertyIgnoreCase(data, o.getYwidfield()));
		Integer jdrq = ConvertUtils.toInteger(BeanUtils.getPropertyIgnoreCase(data, o.getJdrqfield()));

		Long empi = ConvertUtils.toLong(BeanUtils.getPropertyIgnoreCase(data, "EMPI"));
		Integer jarq = ConvertUtils.toInteger(BeanUtils.getPropertyIgnoreCase(data, jarqFieldN));
		Long jaid = ConvertUtils.toLong(BeanUtils.getPropertyIgnoreCase(data, "EMPI"));
		DaDazt t = new DaDazt();
		t.setFormid(formid);
		t.setEmpi(empi);
		t.setFlag(flag);
		t.setYwid(ywid);
		t.setJdrq(jdrq);
		t.setJarq(jarq);
		t.setJaid(jaid);

		markDaState(t, mode, dba);
	}

	/**
	 * 记录业务档案的状态(通用方法)
	 * 
	 * @param data
	 * @param mode
	 *            //是否允许多条YWID,只有在DaDazt的ID为空时才生效
	 * @param dba
	 * @throws AppException
	 */
	public static void markDaState(DaDazt data, boolean mode, DBAgent dba) throws AppException {
		if (data.getEmpi() == null)
			throw new AppException("处理档案状态数据时出错。没有EMPI值");
		if (data.getId() != null) {// 当ID不为空时强行进入更新模式
			dba.update(null, data);
			return;
		}
		Map condi = new HashMap();
		condi.put("empi", data.getEmpi());
		condi.put("formid", data.getFormid());
		if (mode)
			condi.put("ywid", data.getYwid());
		List<DaDazt> zts = dba.find(DaDazt.class, condi, null);
		dba.evicts(zts);// 游离连接
		if (zts.size() == 0) {// 新增
			data.setId(DbaHelpServiceImpl.selectSysID(10000L, dba));
			dba.save(null, data);
		} else {// 更新
			DaDazt t = zts.get(0);
			t.setFlag(data.getFlag());
			t.setEmpi(data.getEmpi());
			t.setFormid(data.getFormid());
			t.setJaid(data.getJaid());
			t.setJarq(data.getJarq());
			if (data.getJdrq() != null)
				t.setJdrq(data.getJdrq());
			if (data.getYwid() != null)// 结案时可能会为空所以在UPDATE时有空值就不处理
				t.setYwid(data.getYwid());
			dba.update(null, t);
		}
	}

	public static void main(String[] args) {
		applyPersonIdxCondi(new HashMap(), "", "", "");
	}

	/**
	 * 把索引加进对应的条件中。
	 * 
	 * @param condi
	 * @param tableFlag
	 *            定位到处理生成具体的表,如要替换SQL请在对应的SQL语句加上对应的标记如:{t:WSXX_BA},如PO类则不用
	 * @param aliasFlag
	 *            如果出现有表的另名时请写这东东
	 * @param empiKey
	 *            主索引字段名
	 */
	public static void applyPersonIdxCondi(Map condi, String tableFlag, String aliasFlag, String empiKey) {
		StringBuffer tsb = new StringBuffer();
		// String sql = "exists (select * from da_grda0)";
		List<Type> types = new ArrayList<Type>();
		List<Object> datas = new ArrayList<Object>();
		// Type[] types = new Type[] {};
		// Object[] datas = new Object[] {};
		Fields[] keyFields = flagIndexSearchKeys.get(tableFlag);
		Map<String, Object> rmdata = new HashMap<String, Object>();
		if (keyFields != null)
			for (Fields keyField : keyFields) {
				Object value = condi.get(keyField.fieldName);
				if (value == null
						|| (value != null && value instanceof String && StringUtils.checkNullOrEmpty((String) value)))
					continue;
				rmdata.put(keyField.fieldName, value);
				datas.add(value);
				types.add(keyField.fieldType);
				if (tsb.length() > 0)
					tsb.append(" or ");
				tsb.append("{s:PERSON_IDX.IDX_TYPE,h:PersonIdx.idxType,p:PERSON_IDX.IDX_TYPE}=" + keyField.fieldCode
						+ " and  {s:PERSON_IDX.IDX_ID,h:PersonIdx.idxId,p:PERSON_IDX.IDX_ID}= :" + keyField.fieldName
						+ " and {s:PERSON_IDX.Flag,h:PersonIdx.flag,p:PERSON_IDX.Flag}=1");
			}

		if (datas.size() == 0)
			return;// 所有唯一索引都不存在.不必继续
		// 增加PO处理时可能要暂时移除的条件
		if (rmdata.size() > 0)
			condi.put(DBAgent.poCondiRemoveKey, rmdata);
		tsb.insert(0, empiKey + " in "
		// + " exists "
				+ " ({s:select empi,h:select empi,p:select empi} from {s:PERSON_IDX,h:PersonIdx,p:PERSON_IDX} where "
		// + "{s:PERSON_IDX,h:PersonIdx,p:PERSON_IDX}.empi={alias"
		// + (aliasFlag == null ? "" : ":" + aliasFlag)
		// + "}." + empiKey + " and ("

		)// .append(")")
				.append(")");

		condi.put("{sqlc:" + tableFlag + "}",
				new SQLCriterionFix(tsb.toString(), datas.toArray(), types.toArray(new Type[] {})));
		// System.out.println(c.toString());
		// new SQLCriterion(sql, datas, types);

		// flagIndexSearchKeys.get(tableFlag);

	}

	private static void savePersonIdx(DBAgent dba, DbaHelpService dhs, List<PersonIdx> pis, Fields[] f, Object data, Long empi){
		boolean[] updateFlag = new boolean[f.length];
		for (int i = 0; i < updateFlag.length; i++)
			updateFlag[i] = true;
		// 更新无效标记
		for (PersonIdx pi : pis) {
			// pidxT_sfzh = 0;
			// pidxT_sbh = 1;
			// pidxT_sbkh = 2;
			// pidxT_jkkh = 3;
			Integer idxType = pi.getIdxType();
			if (idxType != null && (idxType >= pidxT_sfzh || idxType <= pidxT_jkkh)) {
				Object val = getFieldValueByName(f[pi.getIdxType()].fieldName, data);
				if (!pi.getIdxId().equals( ConvertUtils.toString( val))) {
					pi.setFlag(0);
				} else
					updateFlag[pi.getIdxType()] = false;
			}
		}
		// 保存与原来不相同的索引数据
		for (int i = 0; i < updateFlag.length; i++) {
			if (updateFlag[i]) {
				PersonIdx t = null;
				Object val = getFieldValueByName(f[i].fieldName, data);
				if (val != null){
					t = toPersonIdx(empi, ConvertUtils.toString(val), i, dhs);
				}
				if (t != null)
					dba.save(null, t);
			}

		}
		
	}
	public static void updatePersonIdx(WsxxBa data, DBAgent dba, DbaHelpService dhs) {
		Map<String, Object> condi = new HashMap<String, Object>();
		Long empi = ConvertUtils.toLong(data.getEmpi());
		condi.put("empi", empi);
		condi.put("flag", 1);
		List<PersonIdx> pis = dba.find(PersonIdx.class, condi, null);
		Fields[] f = flagIndexSearchKeys.get(PTT_WSXXBA);
		savePersonIdx(dba, dhs, pis, f, data, empi);
	}

	/**
	 * 使用反射根据属性名称获取属性值
	 * 
	 * @param fieldName
	 *            属性名称
	 * @param o
	 *            操作对象
	 * @return Object 属性值
	 */

	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("Field name is not exists");
			return null;
		}
	}

	/**
	 * 
	 * @param data
	 *            处理的数据(GRDA0)
	 * @param dba
	 * @param dhs
	 *            序列服务
	 */
	public static void updatePersonIdx(PoDaDaGrda0 data, DBAgent dba, DbaHelpService dhs) {
		Map<String, Object> condi = new HashMap<String, Object>();
		condi.put("empi", data.getEmpi());
		condi.put("flag", 1);
		List<PersonIdx> pis = dba.find(PersonIdx.class, condi, null);
		Fields[] f = flagIndexSearchKeys.get(PTT_DAGRDA0);
		
		savePersonIdx(dba,dhs, pis, f, data, data.getEmpi());
	}

	private static PersonIdx toPersonIdx(Long empi, String idxId, int idxType, DbaHelpService dhs) {
		PersonIdx rt = new PersonIdx();
		rt.setEmpi(empi);
		rt.setFlag(1);
		rt.setId(dhs.selectSysID(40000L));
		rt.setIdxId(idxId);
		rt.setIdxType(idxType);
		return rt;
	}

	/**
	 * 移除系统加进去的SQL增加方法
	 * 
	 * @param condi
	 */
	public static void removePersonIdxCondi(Map<Object, Object> condi) {
		List<String> rm = new ArrayList<String>();
		condi.remove(DBAgent.poCondiRemoveKey);

		for (Map.Entry<Object, Object> item : condi.entrySet()) {
			if (item.getKey() instanceof String) {
				String key = (String) item.getKey();
				if (key.indexOf("{sqlc:") > -1)
					rm.add(key);
			}
		}
		for (String key : rm) {
			condi.remove(key);
		}
	}

}
