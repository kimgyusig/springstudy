<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head> <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WEHUB</title>
    <script src="${path}/js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="${path}/css/SignUpForm.css">
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>
<body>
    <div class="wrapper">
        <div class="container">
            <div id="title">
                <h1><a href="file:///C:/Users/User/Desktop/Coding/FinalProjectHTML/LogInForm.html">Create an account</a></h1>
		        <h2><a href="file:///C:/Users/User/Desktop/Coding/FinalProjectHTML/LogInForm.html">Welcome</a></h2>
            </div>
            
            <form class="form" action="${path}/member/signUpForm" method="post" enctype="multipart/form-data" style="margin-top: -187px;">
			
                <input type="button" onclick="findNewMem();" class="openBtn" value="사번입력 click!" style="background:#B7A4EE; border-radius:20px; font-size: 13px;"/>

                <input type="file" name="user_img" id="user_imgOri" style="padding: 6px 35px; font-size: 13px;" accept=".gif, .jpg, .png">
                
                <input type="hidden" id="newUserNo" name="newUserNo">
                <!-- <input type="text" name="user_companyname"  placeholder="킆릭하여 프로필 이미지 선택하기" style="font-size: 13px;" readonly="readonly"> -->
                <!-- <input type="button" name="user_companyname_btn"  value="회사 확인" onclick="" style="font-size: 13px;"> -->
                <input type="text" id="user_name"  name="user_name" placeholder="이름  UserName" style="font-size: 13px;" readonly>
                <input type="text" id="rank" name="rank" placeholder="직급  Rank" style="font-size: 13px;" readonly>
                <!-- <input type="text" name="employeeNum" placeholder="사번  employee Number" style="font-size: 13px;"> -->
                <input type="text" id="dept_code"  name="dept_code" placeholder="부서이름  DepartmentName" style="font-size: 13px;" readonly>
                <input type="email" name="email" placeholder="이메일  E-Mail" style="font-size: 13px;">
                <br><br>
                <input type="text" name="user_id" placeholder="아이디  UserId" style="font-size: 13px;" id="user_id" required>
                <input type="button" name="user_id_btn" value="아이디 중복 확인" style="font-size: 13px;" id="id_Check_Btn">
                <input type="password" name="user_pwd"  placeholder="패스워드  Password" style="font-size: 13px;" id="pass1" required>
                <input type="password" placeholder="패스워드 확인  PasswordCheck" id="pass2"  style="font-size: 13px;">
                <br><br>
                <input type="button" name="addressBtn"  value="주소 찾기" onclick="javascript:sample6_execDaumPostcode();" style="font-size: 13px;">
                <input type="text" name="address"  placeholder="우편번호 Zip/Postal Code" id="kakao_postcode" style="font-size: 13px;" readonly="readonly">
                <input type="text" name="address"  placeholder="주소  State/Province/Region/City" id="kakao_address" style="font-size: 13px;"  readonly="readonly">
                <input type="text" name="address"  placeholder="상세주소 Street Address" id="kakao_detailAddress" style="font-size: 13px;">
                <input type="hidden" name="address" placeholder="참고항목" id="kakao_extraAddress" style="font-size: 13px;">
                <input type="tel" name="comcall" placeholder="내선번호 ExtensionNumber" id="comcall" style="font-size: 13px;">
                <input type="tel" name="phone"  placeholder="휴대번호  Phone" id="phone" style="font-size: 13px;">
                
                <button type="button" id="login-button">가입하기</button> <!-- Create  onclick="javascript:dataCheck();"-->
                <!-- <button type="button" id="login-button" onclick="javascript:dataCheck();">기능확인</button> -->
                <p id="findPwdAndSignUp">
                    <!-- <a href="file:///C:/Users/User/Desktop/Coding/FinalProjectHTML/LogInForm.html" id="fpas">Already have an account?</a>
                    -->
                    <a href="${path}/" id="fpas" style="font-size: 12px;">벌써 회원이신가요?</a>
                </p>
                
            </form>
      </div>
  </div>
</body>

<script>
	/* 사번으로 검색하는 모달 */
	function findNewMem(){
		const { value: userNo } = Swal.fire({
			  title: '사번을 입력해 주세요',
			  input: 'text',
			  inputLabel: '사번을 입력해 주세요 :)',
			  showCancelButton: true,
			  inputValidator: (value) => {
				  console.log(value);
				  
				  $.ajax({
						type: "post",
						url: "${path}/member/findNewMem",
						dataType: "json",
						data: {
							value: value
						},
						success: function(data) {
							if(data.member != 0 && data.member != 1){
								$("#user_name").attr('class','newMemInput');
								$("#rank").attr('class','newMemInput');
								$("#dept_code").attr('class','newMemInput');
								
								document.getElementById('newUserNo').value = data.member.new_no;
								document.getElementById('user_name').value = data.member.new_name;
								document.getElementById('rank').value = data.member.new_rank;
								document.getElementById('dept_code').value = data.member.new_dept;
								
							} else if(data.member == 0){
								document.getElementById('newUserNo').value = "";
								document.getElementById('user_name').value = "";
								document.getElementById('rank').value = "";
								document.getElementById('dept_code').value = "";
								
								Swal.fire({
									  icon: 'error',
									  text: "유효하지 않은 사번입니다.!",
									})
							} else if(data.member == 1){
								document.getElementById('newUserNo').value = "";
								document.getElementById('user_name').value = "";
								document.getElementById('rank').value = "";
								document.getElementById('dept_code').value = "";
								
								Swal.fire({
									  icon: 'error',
									  text: "이미 가입된 사번입니다.!",
									})
							}
						},
						error: function(e) {
							 
						}				
					});
				  
			    if (!value) {
			      return 'You need to write something!'
			    }
			  }
			});
	}
	
	/* function dataCheck() {
		var pass1 = document.getElementById('pass1').value;
		var pass2 = document.getElementById('pass2').value;
		
		if(pass1.length < 4) {
			alert("비밀번호를 4글자 이상 입력하세요.");
			
			return false;
		}
		
		if(pass1 != pass2) {
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다. \n비밀번호 확인을 정확하게 입력하세요.");
			
			return false;
		}
	} */
	
	$(document).on("keyup", "#comcall, #phone", function() { 
		$(this).val( $(this).val()
				.replace(/[^0-9]/g, "")
				.replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3")
				.replace("--", "-") ); 
	});
	
	$(document).ready(() => {
		$("#id_Check_Btn").on("click", () => {
			let user_id = $("#user_id").val().trim();
			var idReg = /^[a-z][a-z0-9]{3,10}$/g;
			
			if(!idReg.test(user_id)) {
				alert("아이디는 영문 소문자와 숫자를 4~12자리로 입력하세요.");
				
				return;
			}
			
			$.ajax({
				type: "post",
				url: "${path}/member/idCheck",
				dataType: "json",
				data: {
					user_id: user_id // 파라미터_키값: value값
				},
				success: function(data) {
					console.log(data);
					
					if(data.validate !== true) {
						alert("사용 가능한 아이디 입니다.");
					} else {
						alert("이미 사용중인 아이디 입니다. 다시 입력하여 주십시오.");						
					}
				},
				error: function(e) {
					console.log(e);
				}				
			});
		});
	});
	
	$(document).ready(() => {
		$("#login-button").on("click", () => {
			let user_id = $("#user_id").val().trim();
			var idReg = /^[a-z][a-z0-9]{3,10}$/g;
			
			if(!idReg.test(user_id)) {
				alert("아이디는 영문 소문자와 숫자를 4~12자리로 입력하세요.");
				
				return;
			}
			
			$.ajax({
				type: "post",
				url: "${path}/member/idCheck",
				dataType: "json",
				data: {
					user_id: user_id // 파라미터_키값: value값
				},
				success: function(data) {
					console.log(data);
					
					if(data.validate !== true) {
						$('.form').submit();
						/* location.href="${path}/member/signUpForm"; */
					} else {
						alert("이미 사용중인 아이디 입니다. 다시 입력하여 주십시오.");		
						return;
					}
				},
				error: function(e) {
					console.log(e);
				}				
			});
		});
	});
	
	// kakao 주소찾기 api
	function sample6_execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var addr = ''; // 주소 변수
	            var extraAddr = ''; // 참고항목 변수
	
	            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                addr = data.roadAddress;
	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                addr = data.jibunAddress;
	            }
	
	            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	            if(data.userSelectedType === 'R'){
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                // 조합된 참고항목을 해당 필드에 넣는다.
	                document.getElementById("kakao_extraAddress").value = extraAddr;
	            
	            } else {
	                document.getElementById("kakao_extraAddress").value = '';
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('kakao_postcode').value = data.zonecode;
	            document.getElementById("kakao_address").value = addr;
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById("kakao_detailAddress").focus();
	        }
	    }).open();
	}
	
	$("input[id=user_imgOri]").change(function(){
		 
		if($(this).val() != ""){
			// 확장자 체크
			var ext = $(this).val().split(".").pop().toLowerCase();
		
			if($.inArray(ext, ["gif","jpg","jpeg","png"]) == -1){
				/* alert("gif, jpg, jpeg, png 파일만 업로드 해주세요."); */
				Swal.fire({
					  icon: 'error',
					  title: '이미지 확장자 틀림!',
					  text: 'gif, jpg, jpeg, png 파일만 업로드 해주세요.'
					})
			    $(this).val("");
			    return;
			}
	          
			// 용량 체크
			for (var i=0; i<this.files.length; i++) {
				var fileSize = this.files[i].size;
				var fSMB = (fileSize / (1024 * 1024)).toFixed(2);
				var maxSize = 1024 * 1024 * 5;
				var mSMB = (maxSize / (1024 * 1024));
				
				if(fileSize > maxSize){
					alert(this.files[i].name + "(이)가 용량 5MB을 초과했습니다.\n\n<font color='red'>" + fSMB + "MB</font> / " + mSMB + "MB");
					
					$(this).val("");    
				}
			}
 		}
	});
</script>

</html>