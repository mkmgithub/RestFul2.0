package com.yonyou.h.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ufida.hap.util.BeanUtils;
import com.yonyou.h.util.C;

/**
 * 方法返回工具
 */
public class FunctionRet {
	
	private static void addElement(Element itemNode, Map m){
		for(Object k: m.keySet()) {
			itemNode.addElement(String.valueOf(k)).setText(m.get(k) == null? "" : String.valueOf(m.get(k)));
		}
	}
	/**
	 * 构建成功返回xml
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String buildSuccessXml( Object obj){
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(C.RETURN_ROOT_NODE);		
				
		root.addElement(C.CODE_NODE).setText("0");
		root.addElement(C.MSG_NODE).setText("操作成功");	
		
		if(obj instanceof Map){
			Map map = (Map)obj;
			Element itemNode= root.addElement("NODE");
			addElement(itemNode, map);
		} else if(obj instanceof List) {
			List list = (List)obj;
			Element itemr = root.addElement("LISTNODE");
			for(Object object : list) {
				Element itemNode = itemr.addElement("NODE");
				if(object instanceof Map) {
					Map map = (Map)object;
					addElement(itemNode, map);
				} else {
					Map map = BeanUtils.toMap(object, new HashMap(), true);
					addElement(itemNode, map);
				}
			}
		} else {
			Map map = BeanUtils.toMap(obj, new HashMap(), true);
			addElement(root, map);
		}
		return doc.asXML();
	}
	
	/**
	 * 构建无返回值成功xml
	 * @param message
	 * @return
	 */
	public static String buildOpSuccessXml(String message){
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(C.RETURN_ROOT_NODE);
		root.addElement(C.CODE_NODE).setText("0");
		root.addElement(C.MSG_NODE).setText(message);
		return doc.asXML();
	}
	
	/**
	 * 构建出错返回xml
	 * @param message
	 * @return
	 */
	public static String buildFailXml(String message) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(C.RETURN_ROOT_NODE);
		root.addElement(C.CODE_NODE).setText("-1");
		root.addElement(C.MSG_NODE).setText(message);
		return doc.asXML();
	}
}
