package com.yonyou.h.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.yonyou.h.domain.person.PersonBaseInfo;
import com.yonyou.h.domain.person.Resident;
import com.yonyou.h.util.FunctionRet;
import com.yonyou.h.util.ParamsUtil;
import com.ufida.ehr.empi.model.Person;
import com.ufida.g3.domain.da.PoDaDaGrda0;
import com.ufida.g3.domain.da.PoDaDaGrda1;
import com.ufida.g3.domain.da.PoDaDaGrda2;
import com.ufida.g3.domain.dic.ZdOptions;
import com.ufida.hap.core.exception.AppException;
import com.ufida.hap.util.BeanUtils;
import com.ufida.hap.util.ConvertUtils;
import com.ufida.hap.util.DBAgent;
import com.ufida.hap.util.DateUtils;
import com.ufida.hap.util.ParamUtil;

public class LuPhs4AppIntfImpl implements LuPhs4AppIntf {
	
	private static final String[] YlfdNames = {"城镇职工基本医疗保险","城镇居民基本医疗保险","新型农村合作医疗","贫困救助","商业医疗保险",
		    "综合医疗保险","住院医疗保险","农民工医疗保险","少儿住院及门诊医保","全自费","全公费","其他"};

	private Map<String,Object> zdoptMaps = null; 
	
	private DBAgent getDBAgent() {
		return DBAgent.getInstance();
	}

	private String getZdOptions(String bm, int lx){
				if (zdoptMaps ==null){
					zdoptMaps = new HashMap<String, Object>();
					List<ZdOptions> optList= getDBAgent().find(ZdOptions.class,null, null);
					for (ZdOptions opt : optList){
						zdoptMaps.put(String.format("%d@%s", opt.getLx(), opt.getBm()), opt.getMc());
					}
				}
				String key = String.format("%d@%s",lx,bm);
				if (zdoptMaps.containsKey( key)){
					return zdoptMaps.get( key).toString();
				}
				return "";
	}	
	
	public String getPHS(String xml) {
		// 系统参数
		Map<String, Object> input = ParamsUtil.toMap(xml);
		try {
			// 根据条件查询对应的人员信息
			/* ResidentName; Age; PaperNum; CardID; */
			String xm = ConvertUtils.toString(input.get("residentname"));
			Map<String, Object> condi = new HashMap<String, Object>();
			if (input.containsKey("papernum")) {
				condi.put("sfzh[=]", ConvertUtils.toString(input.get("papernum")));
			}
			if (condi.size() == 0 && input.containsKey("cardid")) {
				condi.put("sbbh[=]", ConvertUtils.toString(input.get("cardid")));
			}
			if (condi.size() == 0) {
				condi.put("xm", xm);
				if (!input.containsKey("age"))
					throw AppException.error("没有身份证和电脑号时必须有年龄！", null);
				int age = ConvertUtils.toInteger(input.get("age"), 0);
				Long bdate = 0L;
				Long edate = 0L;
				if (age == 0) {
					bdate = DateUtils.dateToLongDonly(DateUtils.firstDateForYear(DateUtils.currentDate()));
					edate = DateUtils.dateToLongDonly(DateUtils.currentDate());
				} else {
					bdate = DateUtils.dateToLongDonly(DateUtils.addYear(DateUtils.currentDate(), 0 - age - 1));
					bdate = DateUtils.dateToLongDonly(DateUtils.addYear(DateUtils.currentDate(), 0 - age + 1));
				}
				condi.put("csrq[>=]", bdate);
				condi.put("csrq[<=]", edate);
			}
			condi.put("state[=]", "1");
			condi.put("sqbm[like]", "440303%");

			Resident resident = new Resident(); // 返回xml对象
			PersonBaseInfo person = new PersonBaseInfo();
			List<PoDaDaGrda0> da0List = getDBAgent().find(PoDaDaGrda0.class, condi, null);
			for (PoDaDaGrda0 da0 : da0List) {
				if (da0.getXm().equalsIgnoreCase(xm)) {
					person.setResidename(da0.getXm());
					person.setEmpi(da0.getEmpi().toString());
					person.setCardid(ConvertUtils.toString(da0.getSbbh()));
					person.setFamilyid(ConvertUtils.toString(da0.getJtdaid()));
					person.setResidentid(ConvertUtils.toString(da0.getDah()));
					String xb = ConvertUtils.toString(da0.getXb());
					person.setSexcd(xb);
					person.setSexname( getZdOptions(xb, 3));					
					person.setCredentials("1");
					person.setFilestatuscd("0");
					person.setFilestatusname("正常");
					person.setNowcountry("中国");
					
					person.setBirthday(da0.getCsrqstr());					
					person.setPapernum(ConvertUtils.toString(da0.getSfzh()));
					person.setManageorg(ConvertUtils.toString(da0.getSqbm()));					
					person.setStation(ConvertUtils.toString(da0.getSqbm()));
					person.setBuildorg(ConvertUtils.toString(da0.getJdsq()));
					person.setBuilder(ConvertUtils.toString(da0.getJdrymc()));
					person.setBuilddate(ConvertUtils.toString(da0.getJdrqstr()));
					person.setDutydoctor(ConvertUtils.toString(da0.getZrys()));

					PoDaDaGrda1 da1 = getDBAgent().findByPK(PoDaDaGrda1.class, da0.getEmpi());
					
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
					person.setBloodcd( xx);
					person.setBloodname( getZdOptions(xx, 5));
					person.setBloodrh(ConvertUtils.toString(da1.getRh()));
					person.setEducationcd(ConvertUtils.toString(da1.getXl()));
					person.setVocationcd(ConvertUtils.toString(da1.getZylb()));
					person.setMarriagecd(ConvertUtils.toString(da1.getHyzk()));
					// DONE: YLFD --> 按位得出其对应的编码
					Integer fd = ConvertUtils.toInteger( da1.getYlfd());
					String fds = Integer.toBinaryString( fd);
					char[] fdc = fds.toCharArray();
					String codes="";
					String names="";
					for (int i=0; i<fdc.length; i++){						
						if(fdc[i]=='1'){
							String s = String.format( "0%d",i);
							codes += codes.equals("")?"":",";
							names += names.equals("")?"":",";
							codes +=  s.substring(s.length()-2, s.length());
							names += YlfdNames[i];
						}
					}
					person.setInsurancecd( codes);
					person.setInsurancenum(ConvertUtils.toString(da0.getSbkh()));
					person.setSigncontract(ConvertUtils.toString(da1.getIsqy()));
					if (da1.getQyrq() != null && da1.getQyrq() > 0)
						person.setSigndate(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.longToDate(da1.getQyrq())));
					person.setRelation(ConvertUtils.toString(da1.getHzgx()));
					person.setRelaname(ConvertUtils.toString(da1.getLxrxm()));
					person.setRelaphone(ConvertUtils.toString(da1.getLxrdh()));
					person.setResidecd(ConvertUtils.toString(da0.getHjlb()));
					person.setResidestatuscd(ConvertUtils.toString(da0.getCzlx()));

					PoDaDaGrda2 da2 = getDBAgent().findByPK(PoDaDaGrda2.class, da0.getEmpi());
					if (da2 != null) {
						Double sg = da2.getSg();
						person.setHeight(ConvertUtils.toString(sg));
						person.setWeight(ConvertUtils.toString(da2.getTz()));
						if (sg != null && sg > 0)
							person.setBmi(ConvertUtils.toString(new BigDecimal(da2.getTz() / (sg * sg / 10000))
									.setScale(2, BigDecimal.ROUND_HALF_UP)));
						person.setBust(ConvertUtils.toString(da2.getXw()));
						person.setHip(ConvertUtils.toString(da2.getTw()));
						person.setWaist(ConvertUtils.toString(da2.getYw()));
					}
					resident.setPersonbaseinfo(person);
					if (person.getResidename().equals("")) {
						return FunctionRet.buildOpSuccessXml("未查到对满足条件的个人档案数据！");
					} else {
						return resident.buildResidentXml().asXML();
					}
				}
			}
			return FunctionRet.buildOpSuccessXml("未查到对满足条件的个人档案数据！");
		} catch (Exception ex) {
			return FunctionRet.buildFailXml(ex.getMessage());
		}
	}
}
