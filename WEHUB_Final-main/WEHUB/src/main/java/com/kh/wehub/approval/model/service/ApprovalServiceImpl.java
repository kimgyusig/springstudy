package com.kh.wehub.approval.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.wehub.approval.model.dao.ApprovalDao;
import com.kh.wehub.approval.model.vo.Approval;
import com.kh.wehub.common.util.PageInfo;
import com.kh.wehub.member.model.vo.Member;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	@Autowired
	private ApprovalDao approvalDao;

	// Count =======================================
	
	@Override
	public int approvalCount_YET(Member loginMember) {
		
		return approvalDao.approvalCount_YET(loginMember);
	}
	
	@Override
	public int approvalCount_UNDER(Member loginMember) {
		
		return approvalDao.approvalCount_UNDER(loginMember);
	}
	
	@Override
	public int approvalCount_DONE(Member loginMember) {
		
		return approvalDao.approvalCount_DONE(loginMember);
	}

	// List =======================================
	
	@Override
	public List<Approval> getRecentList(Member loginMember) {
		
		return approvalDao.selectRecentList(loginMember);
	}
	
	@Override
	public List<Approval> getRecentList1(Member loginMember) {
		
		return approvalDao.selectRecentList1(loginMember);
	}
	
	@Override
	public List<Approval> getRecentList2(Member loginMember) {
		
		return approvalDao.selectRecentList2(loginMember);
	}

	@Override
	public List<Approval> getApprovalList(PageInfo pageInfo, String searchText) {
		
		int offset = (pageInfo.getCurrentPage() - 1) * pageInfo.getListLimit();
		RowBounds rowBounds = new RowBounds(offset, pageInfo.getListLimit());
		
		return approvalDao.selectApprovalList(rowBounds,searchText);
	}

	@Override
	public int getListCount(String searchText) {
		
		return approvalDao.listCount(searchText);
	}

	@Override
	public int saveLetterOfApproval(Approval approval) {
		int result = 0;
		
		result = approvalDao.insertLetterOfApproval(approval);
		
		return result;
	}

	@Override
	public int saveLetterOfApproval2(Approval approval) {
		int result = 0;
		
		result = approvalDao.insertLetterOfApproval2(approval);
		
		return result;
	}

	@Override
	public int saveLetterOfApproval3(Approval approval) {
		int result = 0;
		
		result = approvalDao.insertLetterOfApproval3(approval);
		
		return result;
	}

	@Override
	public Approval findListByNo(int appNo) {
		
		return approvalDao.selectApprovalListDetail(appNo);
	}

	@Override
	public int rejectUpdate(Approval approval) {
		int result = 0;
		
		result = approvalDao.rejectUpdate(approval);
		
		return result;
	}

	@Override
	public int approved1(int appNo) {
		int result = 0;
		
		result = approvalDao.approved1(appNo);
		
		return result;
	}
	
	@Override
	public int approved2(int appNo) {
		int result = 0;
		
		result = approvalDao.approved2(appNo);
		
		return result;
	}

	@Override
	public int approved3(int appNo) {
		int result = 0;
		
		result = approvalDao.approved3(appNo);
		
		return result;
	}

	// 휴가신청서 등록 
	@Override
	public int insertApproval(Approval approval) {
		
		return approvalDao.insertApproval(approval);
	}

	@Override
	public int insertLeave(Approval approval) {
		
		return approvalDao.insertAppLeave(approval);
	}

	@Override
	public int insertReceive(Approval approval) {
		
		return approvalDao.insertReceiveRef(approval);
	}

	@Override
	public int saveExpenseReport(Approval approval) {
		int result = 0;
		
		result = approvalDao.insertExpenseReport(approval);
		
		return result;
	}

	@Override
	public int saveExpenseReport2(Approval approval) {
		int result = 0;
		
		result = approvalDao.insertExpenseReport2(approval);
		
		return result;
	}

	@Override
	public int saveExpenseReport3(Approval approval) {
		int result = 0;
		
		result = approvalDao.insertExpenseReport3(approval);
		
		return result;
	}

	@Override
	public Approval findExpenseReportListByNo(int appNo) {
		
		return approvalDao.selectExpenseReportListDetail(appNo);
	}
	
	// 휴가신청서 상세보기
    @Override
    public Approval findListByLeaveNo(int appNo) {
       System.out.println(appNo);
      
       return approvalDao.viewAppLeaveList(appNo);
    }
    
    // 결재 수신참조자 쪽지보내기
    @Override
    public Approval findByAppNoMsg(int appNo) {
       System.out.println("findByAppNoMsg : " + appNo);

       return approvalDao.findByAppNoMsg(appNo);
    }
}
