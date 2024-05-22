package com.kh.wehub.approval.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kh.wehub.approval.model.service.ApprovalService;
import com.kh.wehub.approval.model.vo.Approval;
import com.kh.wehub.common.util.PageInfo;
import com.kh.wehub.member.model.service.MemberService;
import com.kh.wehub.member.model.vo.Member;
import com.kh.wehub.message.model.vo.Message;
import com.kh.wehub.project.model.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/approval")
@SessionAttributes("loginMember")
public class ApprovalController {
	@Autowired
	private ApprovalService service;

	@Autowired
	private MemberService service2;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private ProjectService projectService;

	/** 메인화면 */

	@RequestMapping(value = "/approvalMain", method = { RequestMethod.GET })
	public ModelAndView approvalMain(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			ModelAndView model) {

		int approvalCount_YET = service.approvalCount_YET(loginMember);
		int approvalCount_UNDER = service.approvalCount_UNDER(loginMember);
		int approvalCount_DONE = service.approvalCount_DONE(loginMember);

		List<Approval> mainList = null; // 내 결재 목록

		List<Approval> mainList1 = null; // 내가 작성한 결재
		
		List<Approval> mainList2 = null; // 결재 수신목록

		System.out.println(approvalCount_YET);
		System.out.println(approvalCount_UNDER);
		System.out.println(approvalCount_DONE);

		mainList = service.getRecentList(loginMember);

		mainList1 = service.getRecentList1(loginMember);
		
		mainList2 = service.getRecentList2(loginMember);

		System.out.println("mainList : " + mainList);

		model.addObject("mainList", mainList);
		model.addObject("mainList1", mainList1);
		model.addObject("mainList2", mainList2);
		model.addObject("countYet", approvalCount_YET);
		model.addObject("countUnder", approvalCount_UNDER);
		model.addObject("countDone", approvalCount_DONE);
		model.setViewName("approval/approvalMain");

		return model;
	}

	/** 결재리스트 */

	@RequestMapping(value = "/approvalList", method = { RequestMethod.GET })
	public ModelAndView approvalList(ModelAndView model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "listLimit", required = false, defaultValue = "10") int listLimit,
			@RequestParam(value = "notice_search", required = false) String searchText) {

		List<Approval> mainList2 = null;

		int listCount = service.getListCount(searchText);

		PageInfo pageInfo = new PageInfo(page, 10, listCount, listLimit);

		mainList2 = service.getApprovalList(pageInfo, searchText);

		System.out.println("mainList2 : " + mainList2);

		if (searchText != null) {
			model.addObject("notice_search", searchText);
		}

		model.addObject("mainList", mainList2);
		model.addObject("pageInfo", pageInfo);

		model.setViewName("approval/approvalList");

		return model;
	}

	/** 수신참조자 모달 내 멤버 리스트 불러오기 (leaveApplication) */

	/*
	 * @RequestMapping(value = "/leaveApplication", method = { RequestMethod.GET })
	 * public ModelAndView leaveApplication(@SessionAttribute(name = "loginMember",
	 * required = false) Member loginMember, ModelAndView model, Member member) {
	 * 
	 * List<Member> memberList = null;
	 * 
	 * memberList = service2.selectMemberAll(loginMember.getUser_id());
	 * 
	 * model.addObject("memberList", memberList);
	 * model.setViewName("approval/leaveApplication");
	 * 
	 * return model; }
	 */

	/** 수신참조자 모달 내 멤버 리스트 불러오기 (letterOfApproval) */

	@RequestMapping(value = "/letterOfApproval", method = { RequestMethod.GET })
	public ModelAndView letterOfApproval(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			ModelAndView model, Member member) {

		System.out.println("loginMember : " + loginMember);

		Date date = new Date();
		// DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
		// DateFormat.SHORT, locale);

		// String formattedDate = dateFormat.format(date);

		SimpleDateFormat format = new SimpleDateFormat("yyyy 년 MM 월 dd 일");

		String today = format.format(date);

		model.addObject("serverTime", today);

		List<Member> memberList = null;

		memberList = service2.selectMemberAllForApproval(loginMember.getUser_id());

		System.out.println("memberList : " + memberList);

		model.addObject("memberList", memberList);
		model.setViewName("approval/letterOfApproval");

		return model;
	}

	@RequestMapping(value = "/letterOfApproval", method = { RequestMethod.POST })
	public ModelAndView letterOfApprovalWrite(Approval approval, ModelAndView model,  HttpServletRequest request,
			@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@RequestParam("appLoaFileUpload") MultipartFile upfile) {
		
		int result = 0;
		int result2 = 0;
		int result3 = 0;

		approval.setAppWriterNo(loginMember.getUser_no());

		// 품의서 파일업로드 // 살려야한다...
		if(upfile != null && !upfile.isEmpty()) {

			String renameFileName = saveAppFile(upfile, request);
			
			System.out.println("renameFileName : " + renameFileName);
			
			if(renameFileName != null) {
				approval.setAppOriginalFileName(upfile.getOriginalFilename());
				approval.setAppRenameFileName(renameFileName);
				
				System.out.println("imgOriname : " + approval.getAppOriginalFileName() + " / imgRename : " + approval.getAppRenameFileName());
			}
		}
		
		result = service.saveLetterOfApproval(approval);
		
		approval.setLoaAppNo(approval.getAppNo());
		
		result2 = service.saveLetterOfApproval2(approval);
		
		approval.setReceiveRefAppNo(approval.getAppNo());
		
		result3 = service.saveLetterOfApproval3(approval);
		
		if (result > 0 && result2 > 0 && result3 > 0) {
			model.addObject("msg", "품의서가 정상적으로 등록되었습니다.");
			model.addObject("location", "/approval/approvalList");
		} else {
			model.addObject("msg", "품의서 등록에 실패하였습니다.");
			model.addObject("location", "/");
		}
		model.setViewName("common/msg");
		return model;
	}

	@RequestMapping(value = "/letterOfApprovalView", method = { RequestMethod.GET })
	public ModelAndView letterOfApprovalView(@RequestParam("appNo") int appNo, ModelAndView model) {
		Approval approval = service.findListByNo(appNo);

		System.out.println(approval);

		model.addObject("approval", approval);
		model.setViewName("/approval/letterOfApprovalView");

		return model;
	}

	/** 수신참조자 모달 내 검색 */

	@ResponseBody
	@RequestMapping(value = "/searchMemberInModal", method = { RequestMethod.GET })
	public List<Member> searchMemberInModal(
			@SessionAttribute(name = "loginMember", required = false) Member loginMember, Member member,
			@RequestParam(value = "searchData") String searchData) {

		System.out.println("searchData : " + searchData);

		List<Member> memberList = null;

		System.out.println("loginMember.getUser_id() : " + loginMember.getUser_id());

		memberList = service2.selectSearchedMemberForApproval(searchData, loginMember.getUser_id());

		return memberList;
	}

	@ResponseBody
	@RequestMapping(value = "/letterOfApprovalUpdate", method = { RequestMethod.POST })
	public int letterOfApprovalUpdate(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			Approval approval, @RequestParam(value = "rejectReasonText") String rejectReasonText,
			@RequestParam(value = "appNo") int appNo) {
		int result = 0;

		approval.setAppNo(appNo);
		approval.setAppReason(rejectReasonText);

		System.out.println("appNo : " + appNo);
		System.out.println("rejectReasonText : " + rejectReasonText);
		System.out.println("approval.getAppNo() : " + approval.getAppNo());
		System.out.println("approval.getAppReason() : " + approval.getAppReason());

		if (rejectReasonText != null) {
			if (approval.getAppNo() != 0) {
				result = service.rejectUpdate(approval);
				System.out.println("result: " + result);
			}
		} else {

		}

		return result;
	}

	// 자동완성
	@ResponseBody
	@RequestMapping(value = "/search/json", method = { RequestMethod.GET })
	public String searchJson(@RequestParam(value = "userName") String userName) {
		System.out.println(userName);
		List<Member> memSearch = service2.getSearchMember(userName);

		JsonArray array = new JsonArray();
		for(int i=0; i < memSearch.size(); i++) {
	         array.add(memSearch.get(i).getUser_name() + "_" + memSearch.get(i).getRank() + "_" +memSearch.get(i).getDept_name());
	      }

		System.out.println(array);

		Gson gson = new Gson();

		return gson.toJson(array);
	}

	@ResponseBody
	@RequestMapping(value = "/loaApproved1", method = { RequestMethod.POST })
	public int loaApproved1(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@RequestParam(value = "appNo") int appNo) {
		int result = 0;
		result = service.approved1(appNo);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/loaApproved2", method = { RequestMethod.POST })
	public int loaApproved2(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@RequestParam(value = "appNo") int appNo) {
		int result = 0;
		result = service.approved2(appNo);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/loaApproved3", method = { RequestMethod.POST })
	public int loaApproved3(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@RequestParam(value = "appNo") int appNo, Message msg) {
		int result = 0;
		result = service.approved3(appNo);
		
		// 쪽지보내기 기능
		Approval approval = service.findByAppNoMsg(appNo);

		if(approval.getAppPresent().equals("D")) {

			String[] arr = approval.getReferList().split(", ");

			for(int num1=0; num1<arr.length; num1++ ) {
				System.out.println(arr[num1]);
			}

			int msgResult = 0;
			List<Message> message = new ArrayList<Message>();

			String msgContent = approval.getAppKinds() + "가 결재완료되었습니다.";

			for(int i=0; i <arr.length; i++) {
				Message forMsg = new Message();

				String[] name = arr[i].split("_");

				Member member = projectService.findReceiver(name[0], name[1], name[2]);

				forMsg.setMsgFrom(loginMember.getUser_no());
				forMsg.setMsgTo(member.getUser_no());
				forMsg.setMsgContent(msgContent);
				forMsg.setRank(name[1]);
				forMsg.setDeptName(name[2]);

				message.add(i,forMsg);

			}

			Map<String, Object> map = new HashMap<>();
			map.put("list", message);

			msgResult = projectService.sendProjectMsg(map);

			if(msgResult > 0) {
				System.out.println("전송완료");
			} else {
				System.out.println("전송실패");
			}
		}

		return result;
	}

	// 휴가신청서
	@RequestMapping("/leaveApplication")
	public String leaveApplication(@SessionAttribute(name = "loginMember", required = false) Member loginMember) {

		return "/approval/leaveApplication";
	}

	@RequestMapping(value = "/updateLeave", method = { RequestMethod.POST })
	public ModelAndView insertLeave(ModelAndView model, HttpServletRequest request,
			@SessionAttribute(name = "loginMember", required = false) Member loginMember, Approval approval) {

		log.info("휴가 신청서 작성 컨트롤러 : " + approval);
		int result = 0;
		int result2 = 0;
		int result3 = 0;

		approval.setAppWriterNo(loginMember.getUser_no());

		System.out.println(approval.getAppWriterNo() + "\n" + approval + "\n" + approval);
		if (loginMember.getUser_no() == approval.getAppWriterNo()) {
			System.out.println(loginMember.getUser_no() + " ,\n" + approval.getAppWriterNo());
			result = service.insertApproval(approval);

			approval.setLeaveAppNo(approval.getAppNo());
			approval.setReceiveRefAppNo(approval.getAppNo());

			System.out.println("approval.getReceiveRefAppNo() : " + approval.getReceiveRefAppNo());

			result2 = service.insertLeave(approval);
			result3 = service.insertReceive(approval);

//			System.out.println("97번줄 : " + appLeave.getLeaveAppNo());
//			System.out.println("101 result : " + result + "\nresult2 : " + result2);

			if (result > 0 && result2 > 0 && result3 > 0) {
				model.addObject("msg", "휴가신청서가 정상적으로 등록되었습니다.");
				model.addObject("location", "/approval/approvalList");
			} else {
				model.addObject("msg", "결재서류 등록을 실패하였습니다.");
				model.addObject("location", "/approval/leaveApplication");
			}
		}

		model.setViewName("common/msg");

		return model;
	}

	/** 수신참조자 모달 내 멤버 리스트 불러오기 (leaveApplication) */

	@RequestMapping(value = "/leaveApplication", method = { RequestMethod.GET })
	public ModelAndView leaveApplication(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			ModelAndView model, Member member) {

		System.out.println("loginMember : " + loginMember);

		List<Member> memberList = null;

		memberList = service2.selectMemberAllForApproval(loginMember.getUser_id());

		System.out.println("memberList : " + memberList);

		model.addObject("memberList", memberList);
		model.setViewName("approval/leaveApplication");

		return model;
	}

	/** 수신참조자 모달 내 멤버 리스트 불러오기 (expenseReport) */

	@RequestMapping(value = "/expenseReport", method = { RequestMethod.GET })
	public ModelAndView expenseReport(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			ModelAndView model, Member member) {

		System.out.println("loginMember : " + loginMember);

		List<Member> memberList = null;

		memberList = service2.selectMemberAllForApproval(loginMember.getUser_id());

		System.out.println("memberList : " + memberList);

		model.addObject("memberList", memberList);
		model.setViewName("approval/expenseReport");

		return model;
	}

	@RequestMapping(value = "/expenseReport", method = { RequestMethod.POST })
	public ModelAndView expenseReportWrite(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			Approval approval, ModelAndView model) {
		int result = 0;
		int result2 = 0;
		int result3 = 0;

		approval.setAppWriterNo(loginMember.getUser_no());

		result = service.saveExpenseReport(approval);

		approval.setErAppNo(approval.getAppNo());

		result2 = service.saveExpenseReport2(approval);

		approval.setReceiveRefAppNo(approval.getAppNo());

		result3 = service.saveExpenseReport3(approval);

		if (result > 0 && result2 > 0 && result3 > 0) {
			model.addObject("msg", "지출결의서가 정상적으로 등록되었습니다.");
			model.addObject("location", "/approval/approvalList");
		} else {
			model.addObject("msg", "지출결의서 등록에 실패하였습니다.");
			model.addObject("location", "/");
		}

		model.setViewName("common/msg");

		return model;
	}

	@RequestMapping(value = "/expenseReportView", method = { RequestMethod.GET })
	public ModelAndView expenseReportView(@RequestParam("appNo") int appNo, ModelAndView model) {

		Approval approval = service.findExpenseReportListByNo(appNo);
		System.out.println("expenseReportView : " + approval);

		
//	    String erDetailStr = approval.getErDetail();
//	    System.out.println("erDetailStr : " + erDetailStr);
//	  
//	    String[] erDetailStrArray = erDetailStr.split(",");
//	    
//	    List<String> erDetailStrList = new ArrayList<>();
//	    for (int i = 0; i < erDetailStrArray.length; i++) {
//	    	erDetailStrList.add(erDetailStrArray[i]);
//	    }
//	    
//	    String erReferenceStr = approval.getErReference();
//	    System.out.println("erReferenceStr : " + erReferenceStr);
//	  
//	    String[] erReferenceStrArray = erReferenceStr.split(",");
//	    
//	    List<String> erReferenceStrList = new ArrayList<>();
//	    for (int i = 0; i < erReferenceStrArray.length; i++) {
//	    	erReferenceStrList.add(erReferenceStrArray[i]);
//	    }
//	    
//	    String erAmountStr = approval.getErAmount();
//	    System.out.println("erAmountStr : " + erAmountStr);
//	  
//	    String[] erAmountStrArray = erAmountStr.split(",");
//	    
//	    List<String> erAmountStrList = new ArrayList<>();
//	    for (int i = 0; i < erAmountStrArray.length; i++) {
//	    	erAmountStrList.add(erAmountStrArray[i]);
//	    }
//	    
//	    Map<Object, List<String>> map = new HashMap<Object, List<String>>();
//	    
//	    map.put("erDetailStrList", erDetailStrList);
//	    map.put("erReferenceStrList", erReferenceStrList);
//	    map.put("erAmountStrList", erAmountStrList);
	    
//	    model.addObject("map", map);
		model.addObject("approval", approval);
		model.setViewName("/approval/expenseReportView");

		return model;
	}

	@RequestMapping(value = "/leaveApplicationView", method = { RequestMethod.GET })
    public ModelAndView leaveApplicationView(@RequestParam("appNo") int appNo, ModelAndView model) {
      
       Approval approval = service.findListByLeaveNo(appNo);

//	       System.out.println("507번줄 : " + approval);

       model.addObject("approval", approval);
       model.setViewName("/approval/leaveApplicationView");

       return model;
    }
	
	// 파일첨부 관련
	private String saveAppFile(MultipartFile file, HttpServletRequest request) {
		String renamePath = null; 
		String originalFileName = null;
		String renameFileName = null;
		String rootPath = request.getSession().getServletContext().getRealPath("resources");
		String savePath = rootPath + "/upload/approvalFile";				
		
		log.info("Root Path : " + rootPath);
		log.info("Save Path : " + savePath);
		
		File folder = new File(savePath); // Save Path가 실제로 존재하지 않으면 폴더를 생성하는 로직
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		originalFileName = file.getOriginalFilename();
		renameFileName = 
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS")) + 
				originalFileName.substring(originalFileName.lastIndexOf("."));
		renamePath = savePath + "/" + renameFileName;
		
		try {
			file.transferTo(new File(renamePath)); // 업로드 한 파일 데이터를 지정한 파일에 저장한다.
		}catch (IOException e) {
			System.out.println("파일 전송 에러 : " + e.getMessage());
			e.printStackTrace();
		}
		
		return renameFileName;
	}
	
	@RequestMapping(value="/appFileDown", method = { RequestMethod.GET })
	public ResponseEntity<Resource> fileDown(
		@RequestParam("oriname") String oriname, @RequestParam("rename") String rename,
		@RequestHeader(name="user-agent") String header) {
		
		try {
			Resource resource = resourceLoader.getResource("resources/upload/approvalFile/" + rename);
			File file = resource.getFile();
			boolean isMSIE = header.indexOf("Trident")!=-1||header.indexOf("MSIE")!=-1;
			String encodeRename = "";
			
			if(isMSIE) {
				encodeRename = URLEncoder.encode(oriname, "UTF-8");
				encodeRename = encodeRename.replaceAll("\\+", "%20");
			}else {
				encodeRename = new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
			}
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + encodeRename + "\"")
					.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())
					.body(resource);
		} catch (IOException e) {
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}