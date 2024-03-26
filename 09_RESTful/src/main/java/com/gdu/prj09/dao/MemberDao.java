package com.gdu.prj09.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj09.dto.AddressDto;
import com.gdu.prj09.dto.MemberDto;

public interface MemberDao {
  int insertMember(MemberDto member);
  int insertAddress(AddressDto address);
  int updateMember(MemberDto member);
  int deleteMember(int memberNo);
  int deleteMemberes(List<String> memberNoList);
  int getTotalMemberCount();
  List<AddressDto> getMemberList(Map<String, Object> map); // 페이징 처리 위해서 begin end 값 Map으로받는다.
  MemberDto getMemberByNo(int memberNo);
  int getTotalAddressCountByNo(int memberNo);
  List<AddressDto> getAddressListByNo(Map<String, Object> map);
}
