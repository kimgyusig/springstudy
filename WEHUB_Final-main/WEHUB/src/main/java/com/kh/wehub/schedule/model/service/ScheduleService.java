package com.kh.wehub.schedule.model.service;

import java.util.List;

import com.kh.wehub.member.model.vo.Member;
import com.kh.wehub.schedule.model.vo.DateData;
import com.kh.wehub.schedule.model.vo.DeptData;

public interface ScheduleService {

	List<DateData> selectCalendar(Member loginMember);
	List<DeptData> selectDeptCalendar(Member loginMember);
	List<DeptData> selectAppCalendar(Member loginMember);

	int updateCalendar(String text, int dayNo, int year, String month, int calNo, Member member);

	List<DateData> todaySchedule(Member loginMember, String[] arr);

}
