var now = new Date(); // 当前日期
var nowDayOfWeek = now.getDay(); // 今天本周的第几天
var nowDay = now.getDate(); // 当前日
var nowMonth = now.getMonth(); // 当前月
var nowYear = now.getYear(); // 当前年
nowYear += (nowYear < 2000) ? 1900 : 0; //  

/**
 * 将字符串dateStr转化成 Date 
 * 例如：dateStr ：yyyy-MM-dd 或 yyyy-MM-dd HH：mm：ss
 * 
 * AHTHOR:liangyangsheng 
 * TIME:2013-06-04
 */
function strToDate(dateStr) {
	dateStr = dateStr + "";
	dateStr = dateStr.replace(/:/g, "-");
	dateStr = dateStr.replace(/\s/g, "-");
	var dateArr = dateStr.split("-");
	//月份是从0开始的所以nowArr[1]-1
	if (dateArr.length == 3) {
		return new Date(dateArr[0], dateArr[1]-1,
				dateArr[2]);
	}
	if (dateArr.length == 6) {
		return new Date(dateArr[0], dateArr[1]-1,
				dateArr[2], dateArr[3],
				dateArr[4], dateArr[5]);
	}
}

/**
 * 获取baseDate日期 距离n天的日期（n可以取正或负整数） 
 * 例如：baseDate ：2013-05-31；n:5
 * 
 * baseDate：date 
 * n：int 
 * AHTHOR:liangyangsheng 
 * TIME:2013-06-04
 */
function getNDate(baseDate, n) {
	var newdate = baseDate;
	var newtimems = newdate.getTime() + (n * 24 * 60 * 60 * 1000);
	newdate.setTime(newtimems);
	var str = formatDate(newdate);
	return str;
}

/**
 * 计算两个日期距离天数 
 * 例如：date1 ：2013-05-31；date2:2013-05-30
 * 
 * date1：date
 * date2：date 
 * AHTHOR:liangyangsheng 
 * TIME:2013-06-04
 */
function dateDistanceDayCount(date1, date2) {
	var date1 = getInterceptDate(date1);
	var valLong = date1.getTime();
	var date2 = getInterceptDate(date2);
	var nowLong = date2.getTime();

	var tempLong = valLong - nowLong;

	return tempLong / (24 * 60 * 60 * 1000);

}

/**
 * 截取date后的时间 只保留日期 
 * 例如：1990-11-20 15:30:44.400 --> 1990-11-20
 * 
 * AHTHOR:liangyangsheng 
 * TIME:2013-06-04
 */
function getInterceptDate(date) {
	var nowStr = formatDate(date);
	var nowArr = nowStr.split("-");
	//月份是从0开始的所以nowArr[1]-1
	var nowDate = new Date(nowArr[0], nowArr[1]-1,
			nowArr[2]);
	return nowDate;
}

// 格式化日期：yyyy-MM-dd
function formatDate(date) {
	var myyear = date.getFullYear();
	var mymonth = date.getMonth() + 1;
	var myweekday = date.getDate();

	if (mymonth < 10) {
		mymonth = "0" + mymonth;
	}
	if (myweekday < 10) {
		myweekday = "0" + myweekday;
	}
	return (myyear + "-" + mymonth + "-" + myweekday);
}

// 格式化日期：yyyy-MM-dd HH:mm:ss
function formatDateTime(date) {
	var myyear = date.getFullYear();
	var mymonth = date.getMonth() + 1;
	var myweekday = date.getDate();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var seconds = date.getSeconds();

	if (mymonth < 10) {
		mymonth = "0" + mymonth;
	}
	if (myweekday < 10) {
		myweekday = "0" + myweekday;
	}
	if (hours < 10) {
		hours = "0" + hours;
	}
	if (minutes < 10) {
		minutes = "0" + minutes;
	}
	if (seconds < 10) {
		seconds = "0" + seconds;
	}

	return (myyear + "-" + mymonth + "-" + myweekday + " " + hours + ":"
			+ minutes + ":" + seconds);
}

// 格式化日期：yyyy-MM-dd,专为周日期定制，以周一而不是以周日作为一周起始日期
function formatDate4Week(date) {
	var myyear = date.getFullYear();
	var mymonth = date.getMonth() + 1;
	var myweekday = date.getDate() + 1;

	if (mymonth < 10) {
		mymonth = "0" + mymonth;
	}
	if (myweekday < 10) {
		myweekday = "0" + myweekday;
	}
	return (myyear + "-" + mymonth + "-" + myweekday);
}

// 获得某月的天数
function getMonthDays(myMonth) {
	var monthStartDate = new Date(nowYear, myMonth, 1);
	var monthEndDate = new Date(nowYear, myMonth + 1, 1);
	var days = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
	return days;
}

// 获得本季度的开始月份
function getQuarterStartMonth() {
	var quarterStartMonth = 0;
	if (nowMonth < 3) {
		quarterStartMonth = 0;
	}
	if (2 < nowMonth && nowMonth < 6) {
		quarterStartMonth = 3;
	}
	if (5 < nowMonth && nowMonth < 9) {
		quarterStartMonth = 6;
	}
	if (nowMonth > 8) {
		quarterStartMonth = 9;
	}
	return quarterStartMonth;
}

// 获得本周的开始日期
function getWeekStartDate() {
	var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);
	return formatDate4Week(weekStartDate);
}

// 获得本周的结束日期
function getWeekEndDate() {
	var weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek));
	return formatDate4Week(weekEndDate);
}

// 获得本月的开始日期
function getMonthStartDate() {
	var monthStartDate = new Date(nowYear, nowMonth, 1);
	return formatDate(monthStartDate);
}

// 获得本月的结束日期
function getMonthEndDate() {
	var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowMonth));
	return formatDate(monthEndDate);
}

// 获得本季度的开始日期
function getQuarterStartDate() {
	var quarterStartDate = new Date(nowYear, getQuarterStartMonth(), 1);
	return formatDate(quarterStartDate);
}

// 或的本季度的结束日期
function getQuarterEndDate() {
	var quarterEndMonth = getQuarterStartMonth() + 2;
	var quarterStartDate = new Date(nowYear, quarterEndMonth,
			getMonthDays(quarterEndMonth));
	return formatDate(quarterStartDate);
}

function getFirstDayOfYear() {
	var firstdate = nowYear + '-01-01';
	return firstdate;
}

function getLastDayOfYear() {
	var day = new Date(nowYear, 12, 0);
	var lastdate = nowYear + '-' + 12 + '-' + day.getDate();// 获取当月最后一天日期
	return lastdate;
}

function getCurrentDate() {
	var now = new Date();
	var year = now.getFullYear(); // 年
	var month = now.getMonth() + 1; // 月
	var day = now.getDate(); // 日
	var currentDateStr = "";
	if (month < 10) {
		currentDateStr = year + "-" + "0" + month;
	} else {
		currentDateStr = year + "-" + month;
	}
	if (day < 10) {
		currentDateStr = currentDateStr + "-" + "0" + day;
	} else {
		currentDateStr = currentDateStr + "-" + day;
	}
	return currentDateStr;
}

// 获得本周的结束日期
function getDay(daysThreshold) {
	var theDay = new Date(nowYear, nowMonth, nowDay + daysThreshold);
	return formatDate4Week(theDay);
}

function AddOrRemimusDays(obj1, obj2, lx) { // obj1：原始时间 obj2：天数 lx:类型1加，2减
	DaysToAdd = obj2;
	var sjrqq = obj1;
	var tmp = sjrqq.split('-');
	var sjrqqtmp = tmp[0] + "/" + tmp[1] + "/" + tmp[2];
	var newdate = new Date(sjrqqtmp); // yyyy-MM-dd转化为日期格式
	if (lx == "1") { // 加
		var newtimems = newdate.getTime() + (DaysToAdd * 24 * 60 * 60 * 1000);
	} else { // 减
		var newtimems = newdate.getTime() - (DaysToAdd * 24 * 60 * 60 * 1000);
	}

	newdate.setTime(newtimems);
	var date = formatDate(newdate);

	return date
}

// 日期格式转化为yyyy-MM-dd
function formatDateYYYYMMDD(_date) {
	var day = _date;

	var Year = 0;
	var Month = 0;
	var Day = 0;
	var CurrentDate = "";
	// 初始化时间
	// Year = day.getYear();//有火狐下2008年显示108的bug
	Year = day.getFullYear();// ie火狐下都可以
	Month = day.getMonth() + 1;
	Day = day.getDate();

	CurrentDate += Year + "-";

	if (Month >= 10) {
		CurrentDate += Month + "-";
	} else {
		CurrentDate += "0" + Month + "-";
	}
	if (Day >= 10) {
		CurrentDate += Day;
	} else {
		CurrentDate += "0" + Day;
	}
	return CurrentDate;
}
