package com.ufida.test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import com.yonyou.h.util.Md5Utils;
import com.yonyou.h.util.ParamsUtil;

@ContextConfiguration(locations = { "classpath:spring-default-cxftest-junit.xml" })
public class LoginTest extends AbstractHapCXFServiceTests {
	public void testLogin() {
		/*try {
			System.out.println(DateUtils.currentDateString(DateUtils.YEAR_MONTH_DAY_PATTERN));
			System.out.println( Md5Utils.MD5("lhyy"+DateUtils.currentDateString(DateUtils.YEAR_MONTH_DAY_PATTERN)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		QName qname=new QName("soap:Header");   
        Document doc=DOMUtils.createDocument();   
        //自定义节点
        Element myheader=doc.createElementNS("","MySoapHeader");   
        myheader.setAttribute("xmlns","http://tempuri.org/");   
        //自定义节点        
        Element spPass=doc.createElement("Password");          
		try {
			String md5 = Md5Utils.MD5("lhyy"+DateUtils.currentDateString(DateUtils.YEAR_MONTH_DAY_PATTERN));        
			spPass.setTextContent(md5); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
        myheader.appendChild( spPass); 
         
        /*Element root=doc.createElementNS("", "soap:Header");   
        root.appendChild( myheader);*/
           
        SoapHeader head=new SoapHeader(qname, myheader);
        
        System.out.println( head);
		
		/*FamilyInfo family = new FamilyInfo(){{
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
		family.setFamilymember( mbers);*/

	   //System.out.println( Member.class.getSimpleName());
		
		
		
				
		/*Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		//Element hinfo = root.addElement("healthinfo");
				
		
		//Element ele= (Element) doc1.selectSingleNode("//healthinfochild");
		Document famdoc = family.buildFamilyInfoDocument();
		System.out.println( famdoc.asXML());
		System.out.println( "-----------------");
		root.appendContent( famdoc.getRootElement());
	
		//System.out.println( doc.asXML());
		System.out.println( doc.asXML());*/
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
