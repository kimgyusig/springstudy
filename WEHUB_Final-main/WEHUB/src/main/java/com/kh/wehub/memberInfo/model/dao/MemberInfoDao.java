package com.kh.wehub.memberInfo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.wehub.member.model.vo.Member;

@Mapper
public interface MemberInfoDao {

	public Integer infoCount();

	public List<Member> SearchList(RowBounds rowBounds, Map<String, Object> map);

	public int SearchCount(Map<String, Object> map);

	public int insertMember(@Param("newName") String newName, @Param("newRank") String newRank,@Param("newDept") String newDept);
}
