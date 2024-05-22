package com.kh.wehub.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.wehub.member.model.vo.Member;
import com.kh.wehub.memberInfo.model.vo.InsertNewMember;

@Mapper
public interface MemberDao {

	Member selectMember(@Param("user_id") String user_id) ;

	String findID(Member member);

	String findPWD(Member member);

	int insertMember(Member member);

	int updateMember(Member member);
	
	int updateMemberPass(Member member);

	int deleteMember(@Param("user_id")String userId);

	int GetKey(String user_id, String user_key); // 유저 인증키 생성 메서드

	int searchPassword(@Param("user_id")String user_id, @Param("email")String email,@Param("key") String key);// 회원 임시 비밀번호 변경 메서드
	
	InsertNewMember getNewMember(String userNo); //임시멤버 찾아옴

	Member checkNewMem(String userNo); //임시멤버 멤버에 있는지 찾아옴

	
	public abstract Member selectMemberForFreeBoard(String userId);

	public List<Member> selectMemberAllForApproval(String userId);

	public List<Member> selectSearchedMemberForApproval(@Param("searchData") String searchData, @Param("userId") String userId);

	public List<Member> getSearchMemberForApproval(String user_name);
	
	List<Member> getSearchMemberApp(String user_name); // Approval 자동완성

	Member findUserInfo(String userId); // 회원수정 사번 갖고올때
}

