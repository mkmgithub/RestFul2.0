package com.yonyou.h.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yonyou.h.services.Lu4AppJobIntf;

public class QuartzJob {

	private static boolean bdoing = false;
	private Lu4AppJobIntf lu4AppJobIntf;

	// 实现数据的上传
	public Lu4AppJobIntf getLu4AppJobIntf() {
		return lu4AppJobIntf;
	}

	@Autowired
	public void setLu4AppJobIntf(Lu4AppJobIntf lu4AppJobIntf) {
		this.lu4AppJobIntf = lu4AppJobIntf;
	}

	public void work() {
		System.out.println("quartz run work!");
	}

	public void dojob() {
		if (!bdoing) {
			// 任务未完成时再触发的处理，暂时用标识记录
			System.out.println("quartz dojob!");
			/*
			 * SimpleDateFormat df = new
			 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			 * System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			 */
			bdoing = true;
			try {
				lu4AppJobIntf.transJob();
			} finally {
				bdoing = false;
			}
		} else {
			System.out.println("quartz doing");
		}
	}
}
