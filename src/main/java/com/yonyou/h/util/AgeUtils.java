package com.yonyou.h.util;

import java.util.Calendar;

import com.ufida.hap.util.DateUtils;

public class AgeUtils {
	public Integer getAge(String idcard) {
		return null;
	}

	public String getBrithDay() {
		return null;
	}
	
	/**
	 * 根据出生日期计算年龄
	 * @param csrq
	 * @return
	 */
	public static Integer getAgeFromCsrq(String csrq) {
		if(CommUtil.checkNull(csrq)) {
			return null;
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		String[] strs = csrq.split("-");
		cal1.set(Integer.parseInt(strs[0]),
				Integer.parseInt(strs[1]),
				Integer.parseInt(strs[2]));
		return getYearDiff(today, cal1);
	}
	
	/**
	 * 根据出生日期计算年龄
	 * @param csrq
	 * @return
	 */
	public static Integer getAgeFromCsrq(Integer csrq) {
		String date = DateUtils.format(DateUtils.integerToDate(csrq), "yyyy-MM-dd");
		return getAgeFromCsrq(date);
	}
	
	/**
	 * 计算日期年份差
	 * @param cal
	 * @param cal1
	 * @return
	 */
	public static int getYearDiff(Calendar cal, Calendar cal1) {
		int m = (cal.get(Calendar.MONTH)) - (cal1.get(Calendar.MONTH));
		int y = (cal.get(Calendar.YEAR)) - (cal1.get(Calendar.YEAR));
		return (y * 12 + m) / 12;
	}
}
