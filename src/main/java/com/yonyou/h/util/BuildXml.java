package com.yonyou.h.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.springframework.util.ReflectionUtils;

import com.ufida.hap.core.logging.HapAppLogFactory;
import com.ufida.hap.util.ConvertUtils;
import com.ufida.hap.util.DateUtils;
import com.yonyou.h.domain.person.FamilyInfo;

public class BuildXml {

	private static final Logger LOGGER = HapAppLogFactory.getLogger(BuildXml.class);

	private static void addElement(Element element, Map<String,Object> map){
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey().toString();
			Object val = entry.getValue();
			if (val instanceof List) {
				List<Map<String,Object>> lvalue = (List<Map<String,Object>>)val;
				Element childEle = element.addElement( key);
				for(Map<String,Object> vmap : lvalue){
					addElement(childEle, vmap);
				}
			} else {				
				String value = ConvertUtils.toString(entry.getValue(), "");
				element.addElement(key).addText(value);
			}
		}
	}
	
	public static Document buildDocument(Object obj, Class clazz){
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(C.NODEITEM);
		String name = clazz.getSimpleName().toLowerCase();
		Element bussis = root.addElement(name);
		HashMap<String, Object> map = new HashMap<String, Object>();
		BuildXml.toMap(obj, map, true, 0);
		addElement(bussis, map);

		return doc;
	}
	/**
	 * 将bean中get方法取得的属性名字和值设置到map当中
	 * 
	 * @param beanObj
	 *            bean对象
	 * @param map
	 *            map对象
	 * @param processNull
	 *            是否处理bean返回的空值
	 * @param datePattern
	 *            日期格式化模式
	 * @param scale
	 *            浮点数值保留小数位数
	 * @param forceUpperCase
	 *            是否强制KEY值大写。
	 * @return 设置好值的map对象
	 */

	public static HashMap toMap(final Object beanObj, final HashMap map, final boolean processNull, final int scale) {
		HashMap result = map;
		Method[] methods = ReflectionUtils.getAllDeclaredMethods(beanObj.getClass());
		Method method;
		for (int i = 0; i < methods.length; i++) {
			method = methods[i];
			if (method.getModifiers() == Modifier.PUBLIC)
				fillFieldToMap(method, beanObj, processNull, DateUtils.YEAR_MONTH_DAY_PATTERN, scale, result, false);
		}
		return result;
	}

	private static void fillFieldToMap(final Method m, final Object obj, final boolean processNull,
			final String datePattern, final int scale, final HashMap result, boolean forceUpperCase) {
		String methodName = m.getName();
		try {
			if (methodName.startsWith("get") && methodName.length() > 3 && m.getParameterTypes().length == 0) {
				String key = String.valueOf(Character.toLowerCase(methodName.charAt(3)));
				if (methodName.length() > 4)
					key += methodName.substring(4);
				Object valueObj = m.invoke(obj, new Object[0]);

				if (!(valueObj instanceof List)) {
					if (valueObj != null) {
						// 处理一般属性值
						String value = null;
						if (valueObj instanceof Date) {
							value = DateUtils.format((Date) valueObj, datePattern);
						} else if (valueObj instanceof Timestamp) {
							value = DateUtils.format((Timestamp) valueObj, datePattern);
						} else if (valueObj instanceof BigDecimal && scale > 0) {
							BigDecimal bd = (BigDecimal) valueObj;
							bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
							value = bd.toString();
						} else {
							value = valueObj.toString();
						}
						if (processNull || value != null)
							result.put((forceUpperCase ? key.toUpperCase() : key), value);
					} else if (processNull) {
						result.put((forceUpperCase ? key.toUpperCase() : key), null);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
