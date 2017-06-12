package com.huaao.sunejwapi.api.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.huaao.sunejwapi.common.util.DateTool;

public class ResidenceAppointmentDateUtil {

	private static List<Calendar> holidayList;
	private static boolean holidayFlag;

	/**
	 * 计算工作日 具体节日包含哪些,可以在HolidayMap中修改
	 * 
	 * @param src
	 *            日期(源)
	 * @param adddays
	 *            要加的天数
	 */
	public static Calendar addDateByWorkDay(Calendar src, int adddays) {
		// Calendar result = null;
		holidayFlag = false;
		for (int i = 0; i < adddays; i++) {
			// 把源日期加一天
			src.add(Calendar.DAY_OF_MONTH, 1);
			holidayFlag = checkHoliday(src);
			if (holidayFlag) {
				i--;
			}
		}
		return src;
	}

	/**
	 * 校验指定的日期是否在节日列表中 具体节日包含哪些,可以在HolidayMap中修改
	 */
	public static boolean checkHoliday(Calendar src) {
		boolean result = false;
		if (holidayList == null) {
			initHolidayList();
		}
		// 先检查是否是周六周日(有些国家是周五周六)
		if (src.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || src.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		for (Calendar c : holidayList) {
			if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH) && src.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 初始化节日List,如果需要加入新的节日,请在这里添加 加的时候请尽量使用Calendar自带的常量而不是魔鬼数字
	 * 注:年份可以随便写,因为比的时候只比月份和天
	 */
	private static void initHolidayList() {
		holidayList = new ArrayList();
		// 五一劳动节
		Calendar may1 = Calendar.getInstance();
		may1.set(Calendar.MONTH, Calendar.MAY);
		may1.set(Calendar.DAY_OF_MONTH, 1);
//		holidayList.add(may1);
	}

	// 获取上周日
	public static Date getLastWeekSunday() {
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		date.add(Calendar.WEEK_OF_MONTH, -1);// 周数减一，即上周
		date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 日子设为星期天
		return date.getTime();
	}

	public static String display(String dateFirst, String dateSecond) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dates = "";
		try {
			Date dateOne = dateFormat.parse(dateFirst);
			Date dateTwo = dateFormat.parse(dateSecond);

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(dateOne);

			while (calendar.getTime().before(dateTwo)) {
				String da = dateFormat.format(calendar.getTime());
				dates = dates.equals("") ? da : dates + "," + da;
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dates;
	}

	public static String[] arrContrast(String[] arr1, String[] arr2) {
		List<String> list = new LinkedList<String>();
		for (String str : arr1) {
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (String str : arr2) {
			if (list.contains(str)) {
				list.remove(str);
			}
		}
		String[] result = {};
		return list.toArray(result);
	}

	public static void main(String[] args) {
		try {
			String dateSunday = DateTool.dateToNormalString(getLastWeekSunday());

			String sund = DateTool.dateToNormalString(DateTool.addDays(DateTool.parseDate(dateSunday), 21));
			System.out.println("--" + dateSunday);
			System.out.println(sund);
			System.out.println("--------------");

			System.out.println(display(dateSunday, sund));
			String dates = "";
			for (int i = 1; i < 8; i++) {
				Calendar one = Calendar.getInstance();
				String date = DateTool.dateToNormalString(ResidenceAppointmentDateUtil.addDateByWorkDay(one, i).getTime());
				System.out.println(date);
				dates = dates.equals("") ? date : dates + "," + date;

			}
			System.out.println("dates"+dates);
			String a[] = arrContrast(display(dateSunday, sund).split(","), dates.split(","));
			for (int i = 0; i < a.length; i++) {
				System.out.println(a[i]);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
