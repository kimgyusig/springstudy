package com.kh.wehub.schedule.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.wehub.member.model.vo.Member;
import com.kh.wehub.schedule.model.dao.ScheduleDao;
import com.kh.wehub.schedule.model.vo.DateData;
import com.kh.wehub.schedule.model.vo.DeptData;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleDao scheduleDao;

	@Override
	public List<DateData> selectCalendar(Member loginMember) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		map.put("userNo", loginMember.getUser_no());
		return scheduleDao.selectCalendar(map);
	}
	@Override
	public List<DeptData> selectDeptCalendar(Member loginMember) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		System.out.println("selectDeptCalendar");
		map.put("dept_code", loginMember.getDept_code());
		
		return scheduleDao.selectDeptCalendar(map);
	}

	@Override
	public int updateCalendar(String text, int dayNo, int year, String month, int calNo, Member member) {
		
		int result = 0;
		
		Map<Object, Object> map = new HashMap<Object, Object>();

		map.put("text", text);
		map.put("dayNo", dayNo);
		map.put("year", year);
		map.put("month", month);
		map.put("userNo", member.getUser_no());
		map.put("calNo", calNo);
		
		System.out.println("calNo : " + calNo);
				
		if(calNo != 0) {
			result = scheduleDao.updateCalendar(map);
		}else {
			result = scheduleDao.insertCalendar(map);
		}
		
		return result;
	}
	@Override
	public List<DeptData> selectAppCalendar(Member loginMember) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		System.out.println("selectAppCalendar");
		map.put("dept_code", loginMember.getDept_code());
		//System.out.println("selectAppCalendar dept_code"+loginMember.getDept_code());
		//System.out.println("selectAppCalendar????"+scheduleDao.selectAppCalendar(map));
		
		return scheduleDao.selectAppCalendar(map);
	}

	@Override
	public List<DateData> todaySchedule(Member loginMember, String[] arr) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		map.put("userNo", loginMember.getUser_no());
		map.put("year", arr[0]);
		map.put("month", arr[1]);
		map.put("today", arr[2]);
		
		return scheduleDao.todaySchedule(map);
	}

}
