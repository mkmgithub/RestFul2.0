package com.ufida.test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.mortbay.jetty.servlet.PathMap.Entry;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ReflectionUtils;

import com.ufida.hap.test.webservice.AbstractHapCXFServiceTests;
import com.ufida.hap.util.ConvertUtils;
import com.ufida.hap.util.DateUtils;
import com.ufida.hap.util.ParamUtil;
import com.yonyou.h.domain.person.FamilyInfo;
import com.yonyou.h.domain.person.Member;
import com.yonyou.h.util.BuildXml;
import com.yonyou.h.util.C;
import com.yonyou.h.util.ParamsUtil;

@ContextConfiguration(locations = { "classpath:spring-default-cxftest-junit.xml" })
public class LoginTest extends AbstractHapCXFServiceTests {
	public void testLogin() {
		
		FamilyInfo family = new FamilyInfo(){{
			setArea("jiangmen");
			setFamilytelephone("13800138000");
			}};
		
		List<Member> mbers = new ArrayList<Member>();
		Member mber = new Member(){{
			setAddress("测试地址");
			setBirthday("2010-1-4");
			setIndustry("fffff");
		}};
		mbers.add( mber);
		family.setFamilymember( mbers);

	   //System.out.println( Member.class.getSimpleName());
		
		
		
				
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		//Element hinfo = root.addElement("healthinfo");
				
		
		//Element ele= (Element) doc1.selectSingleNode("//healthinfochild");
		Document famdoc = family.buildFamilyInfoDocument();
		System.out.println( famdoc.asXML());
		System.out.println( "-----------------");
		root.appendContent( famdoc.getRootElement());
	
		//System.out.println( doc.asXML());
		System.out.println( doc.asXML());
	/*	
		 * String requestXml = "test/LoginTestRequest.xml"; request(requestXml);
		 
		Date dryrq = DateUtils.integerToDate(43250);
		Date dcyrq = DateUtils.integerToDate(43345);
		System.out.println(dryrq);
		System.out.println(dcyrq);
		//将出院、入院改到某年、月的1号进行比较
		dryrq = DateUtils.addDay(dryrq, -DateUtils.getDay( dryrq)+1);
		dcyrq = DateUtils.addDay(dcyrq, -DateUtils.getDay( dcyrq)+1);
		System.out.println(dryrq);
		System.out.println(dcyrq);
		Date nowrq = dryrq;

		do {    			
			nowrq = DateUtils.addMonth(nowrq, 1);
			System.out.println( nowrq);
		} while ( DateUtils.beforeDays(nowrq, dcyrq) >=0);*/
/*
		StringBuffer sb = new StringBuffer("<Request>");
		sb.append("<TradeCode>交易码(见交易代码表)</TradeCode>");
		sb.append("<TradeSerialNumber >终端编号+ yyyyMMddHHmmssSSS</TradeSerialNumber>");
		sb.append("<TermNo>ZT001</TermNo>");
		sb.append("<ResultData>");
		sb.append("<Name>李小本</Name>");
		sb.append("<Sex>男</Sex>");
		sb.append("<BirthDay>2012-09-25</BirthDay>");
		sb.append("<Address>广东阳江市</Address>");
		sb.append("<Nation>汉族</Nation>");
		sb.append("<IDCard>440682192508455230</IDCard>");
		sb.append("<Phone>13568242000</Phone>");
		sb.append("<SocialCardNo>440682192508455230</SocialCardNo>");
		sb.append("<PassWord>******* </PassWord>");
		sb.append("</ResultData>");
		sb.append("</Request>");
		Map map = ParamsUtil.toMap(sb.toString());
		for (Object key:  map.keySet()) {
			System.out.println( key.toString());
			Object val = map.get(key);
			if (val instanceof List){
				List list = (List)val;
				for (Object o :list){
					if(o instanceof Map){
						for (Object k : ((Map)o).keySet()){
							System.out.println( k.toString());
							System.out.println( ((Map)o).get(k).toString());
						}
					}
				}
			}else{
				System.out.println( val.toString());
			}
		}*/
	}
}
