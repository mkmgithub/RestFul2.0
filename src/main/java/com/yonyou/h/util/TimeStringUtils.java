package com.yonyou.h.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeStringUtils {
	 public static final String getTimeString() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String strTime = formatter.format(calendar.getTime());
		return strTime;
	}
}
