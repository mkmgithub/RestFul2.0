package com.yonyou.h.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctc.wstx.util.DataUtil;
import com.misyshealthcare.connect.util.DateUtil;
import com.ufida.g3.domain.da.PoDaDaGrda0;
import com.ufida.g3.domain.da.PoDaDaGrda1;
import com.ufida.g3.domain.da.PoDaDaGrda2;
import com.ufida.g3.domain.dic.ZdOptions;
import com.ufida.g3.domain.region.WsxxBa;
import com.ufida.hap.core.exception.AppException;
import com.ufida.hap.util.ConvertUtils;
import com.ufida.hap.util.DBAgent;
import com.ufida.hap.util.DateUtils;
import com.ufida.hap.util.ParamUtil;
import com.yonyou.h.domain.person.PersonBaseInfo;
import com.yonyou.h.domain.person.Resident;
import com.yonyou.h.util.FunctionRet;
import com.yonyou.h.util.ParamsUtil;

public class LuPhs4AppIntfImpl implements LuPhs4AppIntf {

	private static final String[] YlfdNames = { "����ְ������ҽ�Ʊ���", "����������ҽ�Ʊ���",
			"����ũ�����ҽ��", "ƶ������", "��ҵҽ�Ʊ���", "�ۺ�ҽ�Ʊ���", "סԺҽ�Ʊ���", "ũ��ҽ�Ʊ���",
			"�ٶ�סԺ������ҽ��", "ȫ�Է�", "ȫ����", "����" };

	private Map<String, Object> zdoptMaps = null;

	private DBAgent getDBAgent() {
		return DBAgent.getInstance();
	}

	private DbaHelpService dhs;

	public DbaHelpService getDhs() {
		return dhs;
	}

	@Autowired
	public void setDhs(DbaHelpService dhs) {
		this.dhs = dhs;
	}

	private String getZdOptions(String bm, int lx) {
		if (zdoptMaps == null) {
			zdoptMaps = new HashMap<String, Object>();
			List<ZdOptions> optList = getDBAgent().find(ZdOptions.class, null,
					null);
			for (ZdOptions opt : optList) {
				zdoptMaps.put(String.format("%d@%s", opt.getLx(), opt.getBm()),
						opt.getMc());
			}
		}
		String key = String.format("%d@%s", lx, bm);
		if (zdoptMaps.containsKey(key)) {
			return zdoptMaps.get(key).toString();
		}
		return "";
	}

	public String getPHS(String xml) {
		// ϵͳ����
		Map<String, Object> input = ParamsUtil.toMap(xml);
		try {
			// ����������ѯ��Ӧ����Ա��Ϣ
			/* ResidentName; Age; PaperNum; CardID; */
			String xm = ConvertUtils.toString(input.get("residentname"));
			Map<String, Object> condi = new HashMap<String, Object>();
			if (input.containsKey("empi")) {
				condi.put("empi[=]", ConvertUtils.toLong(input.get("empi")));
			}
			if (condi.size() == 0 && input.containsKey("papernum")) {
				condi.put("sfzh[=]",
						ConvertUtils.toString(input.get("papernum")));
			}
			if (condi.size() == 0 && input.containsKey("cardid")) {
				condi.put("sbbh[=]", ConvertUtils.toString(input.get("cardid")));
			}
			if (condi.size() == 0) {
				condi.put("xm", xm);
				if (!input.containsKey("age"))
					throw AppException.error("û�����֤�͵��Ժ�ʱ���������䣡", null);
				int age = ConvertUtils.toInteger(input.get("age"), 0);
				Long bdate = 0L;
				Long edate = 0L;
				if (age == 0) {
					bdate = DateUtils.dateToLongDonly(DateUtils
							.firstDateForYear(DateUtils.currentDate()));
					edate = DateUtils.dateToLongDonly(DateUtils.currentDate());
				} else {
					bdate = DateUtils.dateToLongDonly(DateUtils.addYear(
							DateUtils.currentDate(), 0 - age - 1));
					bdate = DateUtils.dateToLongDonly(DateUtils.addYear(
							DateUtils.currentDate(), 0 - age + 1));
				}
				condi.put("csrq[>=]", bdate);
				condi.put("csrq[<=]", edate);
			}
			condi.put("state[=]", "1");
			condi.put("sqbm[like]", "440303%");

			Resident resident = new Resident(); // ����xml����
			PersonBaseInfo person = new PersonBaseInfo();
			List<PoDaDaGrda0> da0List = getDBAgent().find(PoDaDaGrda0.class,
					condi, null);
			for (PoDaDaGrda0 da0 : da0List) {
				if (xm == null || da0.getXm().equalsIgnoreCase(xm)) {
					person.setResidename(da0.getXm());
					//person.setResidentname(da0.getXm());
					person.setEmpi(da0.getEmpi().toString());
					person.setCardid(ConvertUtils.toString(da0.getSbbh()));
					person.setFamilyid(ConvertUtils.toString(da0.getJtdaid()));
					person.setResidentid(ConvertUtils.toString(da0.getDah()));
					String xb = ConvertUtils.toString(da0.getXb());
					person.setSexcd(xb);
					person.setSexname(getZdOptions(xb, 3));
					person.setCredentials("1");
					person.setFilestatuscd("0");
					person.setFilestatusname("����");
					person.setNowcountry("�й�");

					person.setBirthday(da0.getCsrqstr());
					person.setPapernum(ConvertUtils.toString(da0.getSfzh()));
					person.setManageorg(ConvertUtils.toString(da0.getSqbm()));
					person.setStation(ConvertUtils.toString(da0.getSqbm()));
					person.setBuildorg(ConvertUtils.toString(da0.getJdsq()));
					person.setBuilder(ConvertUtils.toString(da0.getJdrymc()));
					person.setBuilddate(ConvertUtils.toString(da0.getJdrqstr()));
					person.setDutydoctor(ConvertUtils.toString(da0.getZrys()));

					PoDaDaGrda1 da1 = getDBAgent().findByPK(PoDaDaGrda1.class,
							da0.getEmpi());

					person.setNowprovince(ConvertUtils.toString(da1.getJtdz1()));
					person.setNowcity(ConvertUtils.toString(da1.getJtdz2()));
					person.setNowdistrict(ConvertUtils.toString(da1.getJtdz3()));
					person.setNowstreet(ConvertUtils.toString(da1.getJtdz4()));
					person.setNowzone(ConvertUtils.toString(da1.getJtdz5()));
					person.setNowother(ConvertUtils.toString(da1.getJtdz7()));
					person.setRegdetail(ConvertUtils.toString(da1.getHjdz()));
					person.setWorkunit(ConvertUtils.toString(da1.getGzdw()));
					person.setSelfphone(ConvertUtils.toString(da1.getLxdh()));
					person.setMobilephone(ConvertUtils.toString(da1.getLxdh()));
					person.setFolkcd(ConvertUtils.toString(da1.getMz()));
					String xx = ConvertUtils.toString(da1.getXx());
					person.setBloodcd(xx);
					person.setBloodname(getZdOptions(xx, 5));
					person.setBloodrh(ConvertUtils.toString(da1.getRh()));
					person.setEducationcd(ConvertUtils.toString(da1.getXl()));
					person.setVocationcd(ConvertUtils.toString(da1.getZylb()));
					person.setMarriagecd(ConvertUtils.toString(da1.getHyzk()));
					// DONE: YLFD --> ��λ�ó����Ӧ�ı���
					Integer fd = ConvertUtils.toInteger(da1.getYlfd());
					String fds = Integer.toBinaryString(fd);
					char[] fdc = fds.toCharArray();
					String codes = "";
					String names = "";
					for (int i = 0; i < fdc.length; i++) {
						if (fdc[i] == '1') {
							String s = String.format("0%d", i);
							codes += codes.equals("") ? "" : ",";
							names += names.equals("") ? "" : ",";
							codes += s.substring(s.length() - 2, s.length());
							names += YlfdNames[i];
						}
					}
					person.setInsurancecd(codes);
					person.setInsurancenum(ConvertUtils.toString(da0.getSbkh()));
					person.setSigncontract(ConvertUtils.toString(da1.getIsqy()));
					if (da1.getQyrq() != null && da1.getQyrq() > 0)
						person.setSigndate(new SimpleDateFormat("yyyy-MM-dd")
								.format(DateUtils.longToDate(da1.getQyrq())));
					person.setRelation(ConvertUtils.toString(da1.getHzgx()));
					person.setRelaname(ConvertUtils.toString(da1.getLxrxm()));
					person.setRelaphone(ConvertUtils.toString(da1.getLxrdh()));
					person.setResidecd(ConvertUtils.toString(da0.getHjlb()));
					person.setResidestatuscd(ConvertUtils.toString(da0
							.getCzlx()));

					PoDaDaGrda2 da2 = getDBAgent().findByPK(PoDaDaGrda2.class,
							da0.getEmpi());
					if (da2 != null) {
						Double sg = da2.getSg();
						person.setHeight(ConvertUtils.toString(sg));
						person.setWeight(ConvertUtils.toString(da2.getTz()));
						if (sg != null && sg > 0)
							person.setBmi(ConvertUtils.toString(new BigDecimal(
									da2.getTz() / (sg * sg / 10000)).setScale(
									2, BigDecimal.ROUND_HALF_UP)));
						person.setBust(ConvertUtils.toString(da2.getXw()));
						person.setHip(ConvertUtils.toString(da2.getTw()));
						person.setWaist(ConvertUtils.toString(da2.getYw()));
					}
					resident.setPersonbaseinfo(person);
					if (person.getResidename().equals("")) {
						return FunctionRet.buildOpSuccessXml("δ�鵽���������ĸ��˵������ݣ�");
					} else {
						return resident.buildResidentXml().asXML();
					}
				}
			}
			return FunctionRet.buildOpSuccessXml("δ�鵽���������ĸ��˵������ݣ�");
		} catch (Exception ex) {
			return FunctionRet.buildFailXml(ex.getMessage());
		}
	}

	private void saveGrda(DBAgent dba, PoDaDaGrda0 grda0, PoDaDaGrda1 grda1,
			PoDaDaGrda2 grda2) {
		WsxxBa wsxxBa = null;
		grda0.setWszb(1l); // δ����

		if (grda0.getEmpi() == null) {
			List<WsxxBa> wsxxList = ArchivemgrHelper.getWsxxDa(grda0, dba);// �鲡����ҳ
			if (wsxxList.size() >= 1) {
				wsxxBa = wsxxList.get(0);
				grda0.setEmpi(wsxxBa.getEmpi());
			} else {
				Long empi = dhs.selectSysID(1006l);
				grda0.setEmpi(empi);
			}
			grda1.setEmpi(grda0.getEmpi());
			grda2.setEmpi(grda0.getEmpi());
		}
		wsxxBa = ArchivemgrHelper.PoDaDaGrda0ToWsxxBa(wsxxBa, grda0,
				grda1.getHklb());// ת��������Ϣ
		if (grda1 != null && "0".equals(grda1.getHzgx())
				&& (grda0.getJtdaid() == null)) { // �����ǰ��Աѡ��Ϊ����ʱ������ͥ����
			ArchivemgrHelper.saveJTDA(grda0, grda1, dba, dhs);
		}
		dba.save(null, grda0);
		dba.save(null, grda1);
		dba.save(null, grda2);
		// �жϸ���������
		dba.saveOrUpdate(null, wsxxBa);
		CommOperHelper.updatePersonIdx(grda0, dba, dhs);
	}

	public String save2Phs(String xml) {
		try {
			Document doc = DocumentHelper.parseText(xml);
			// ���������Ϣ
			Element rootElement=doc.getRootElement();
			Element ment = (Element) rootElement.selectSingleNode("//resident//personbaseinfo");//doc.selectSingleNode("personbaseinfo");
			Map<String, Object> omap = new HashMap<String, Object>();
			List<Element> childments = ment.elements();
			for (int k = 0; k < childments.size(); k++) {
				Element ele = childments.get(k);
				omap.put(ele.getName(), ele.getTextTrim());
			}
			PersonBaseInfo person = (PersonBaseInfo) ParamUtil.mapToBean(omap,
					PersonBaseInfo.class, true);
			// ����ǩԼ��Ϣ�������ݿ��е���Ϣ --���Ȳ��ҵ���Ӧ����Ա
			Map<String, Object> condi = new HashMap<String, Object>();
			condi.put("sfzh", person.getPapernum());
			condi.put("state", "1");
			condi.put("sqbm[like]", "440303%");
			List<PoDaDaGrda0> da0List = getDBAgent().find(PoDaDaGrda0.class,
					condi, null);
			if (da0List == null || da0List.size() == 0) {
				// ������޺��Ҳ����������������еĵ�������
				condi.clear();
				condi.put("sfzh", person.getPapernum());
				da0List = getDBAgent().find(PoDaDaGrda0.class, condi, null);
			}
			if (da0List.size() == 0) {
				// �����Ĵ��� --> ������תΪ da0,da1,da2
				// PoDaDaGrda0 da0 = new PoDaDaGrda0();
				// PoDaDaGrda1 da1 = new PoDaDaGrda1();
				// PoDaDaGrda2 da2 = new PoDaDaGrda2();
				// saveGrda(getDBAgent(), da0, da1, da2);
				save2phs(person);
			} else {
				PoDaDaGrda0 da0;
				if (da0List.size() == 1) {
					da0 = da0List.get(0);
				} else {
					PoDaDaGrda0 da = null;
					for (PoDaDaGrda0 d0 : da0List) {
						if (d0.getState().equals("1"))
							da = d0;
					}
					if (da == null)
						da = da0List.get(0);
					da0 = da;
				}
				// ���µ���Ӧ��ǩԼ��Ϣ

				StringBuilder sb = new StringBuilder();
				sb.append("UPDATE DA_GRDA1 SET ISQY=:ISQY,QYFS=2,QYRQ=:QYRQ,QYYSMC=:QYYSMC,QYSQBM=:QYSQBM");
				sb.append(" WHERE EMPI=:EMPI");
				condi.clear();

				condi.put("EMPI", da0.getEmpi());
				condi.put("ISQY", ConvertUtils.toLong(person.getSigncontract()));
				condi.put("QYRQ", DateUtils.dateToLongDonly(DateUtils
						.parse(person.getSigndate())));
				condi.put("QYYSMC", person.getDutydoctor());
				condi.put("QYSQBM", person.getManageorg());
				getDBAgent().executeSQL(sb.toString(), condi);
				if (!da0.getState().equals("1")) {
					getDBAgent().executeSQL(
							"UPDATE DA_GRDA0 SET STATE=1 WHERE EMPI=:EMPI",
							condi);
				}
			}
			return FunctionRet.buildOpSuccessXml("�������ǩԼ��Ա��Ϣ�ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return FunctionRet.buildFailXml(e.getMessage());
		}

	}

	private void save2phs(PersonBaseInfo personInfo) { 
		PoDaDaGrda0 da0 = new PoDaDaGrda0();
		PoDaDaGrda1 da1 = new PoDaDaGrda1();
		PoDaDaGrda2 da2 = new PoDaDaGrda2();
 
		da0.setCsrq(DateUtils.dateToLongDonly(DateUtils.parse(personInfo.getBirthday()))); 
		da0.setCzlx(personInfo.getResidestatuscd());
		da0.setDah(personInfo.getResidentid());
		da0.setEmpi(ConvertUtils.toLong(personInfo.getEmpi())); 
		//��ס���  ��ȷ���Ƿ���Ҫת��
		da0.setHjlb(personInfo.getResidecd());
		da0.setJdrq(DateUtils.dateToLong(DateUtils.currentDate()));
		da0.setJdrqstr(personInfo.getBuilddate());
		da0.setJdry(personInfo.getBuilder());
		da0.setJdrymc(personInfo.getBuilder());
		//��������
		da0.setJdsq(personInfo.getBuildorg()); 
		da0.setJtdaid(ConvertUtils.toLong(personInfo.getFamilyid()));
        

		da0.setSbbh(personInfo.getCardid());
		da0.setSbkh(personInfo.getCardid());
		da0.setSfzh(personInfo.getPapernum());
		da0.setSqbm(personInfo.getManageorg()); 
		da0.setState("1");
		String xb=personInfo.getSexcd();
		if(xb.equals("��"))
		{
			xb="1";
		}
		else if(xb.equals("Ů"))
		{
			xb="2";
		}
		else 
		{
			xb="9";
		}
		da0.setXb(xb);
		da0.setXm(personInfo.getResidentname());
		da0.setZrys(personInfo.getDutydoctor());

		da1.setEmpi(da0.getEmpi());
		da1.setGzdw(personInfo.getWorkunit());
		da1.setHjdz(personInfo.getRegdetail());
		//�Ƿ���Ҫת��  ��Ҫȷ��
		da1.setHyzk(personInfo.getMarriagecd());
		//��Ҫȷ���Ƿ�ת��
		da1.setHzgx(personInfo.getRelation());
		da1.setIsqy(ConvertUtils.toLong(personInfo.getSigncontract()));
		da1.setJtdz(personInfo.getRegdetail());
		da1.setJtdz1(personInfo.getNowprovince());
		da1.setJtdz2(personInfo.getNowcity());
		da1.setJtdz3(personInfo.getNowdistrict());
		da1.setJtdz4(personInfo.getNowstreet());
		da1.setJtdz5(personInfo.getNowzone());
		da1.setJtdz7(personInfo.getNowother());
		da1.setLxdh(personInfo.getSelfphone());
		da1.setLxrdh(personInfo.getRelaphone());
		da1.setLxrxm(personInfo.getRelaname());
		da1.setQyrq(DateUtils.dateToLong(DateUtils.parse(personInfo.getSigndate())));
		da1.setRh(personInfo.getBloodrh());
		da1.setSqbm(personInfo.getManageorg());
		da1.setXl(personInfo.getEducationcd());
		da1.setXx(personInfo.getBloodcd());
        da1.setQyysmc(personInfo.getDutydoctor());
        da1.setQyfs(2L);
        da1.setIsqy(1L);
        da1.setQysqbm(personInfo.getManageorg());
        //�������
        da1.setHklb(personInfo.getRegtypecd());
		// ��Ҫ�޸�
		da1.setYlfd(null);

        
		da2.setEmpi(da0.getEmpi());
		da2.setSg(ConvertUtils.toDouble(personInfo.getHeight()));
		da2.setTw(ConvertUtils.toDouble(personInfo.getHip()));
		da2.setTz(ConvertUtils.toDouble(personInfo.getWeight()));
		da2.setXw(ConvertUtils.toDouble(personInfo.getBust()));
		da2.setYw(ConvertUtils.toDouble(personInfo.getWaist()));
		saveGrda(getDBAgent(), da0, da1, da2); 
	}

	public String select4Phs(String xml) {
		return null;
	}
}
