<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param value="${blog.blogNo}번 블로그" name="title"/>
</jsp:include>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


  <h1>블로그 상세화면</h1>

  <div>
    <span>조회수</span>
    <span>${blog.hit}</span>
  </div>

  <div>
    <span>작성자</span>
    <span>${blog.user.email}</span>
  </div>
  
  <div>
    <span>제목</span>
    <span>${blog.title}</span>
  </div>
  
  <div>
    <span>내용</span>
    <span>${blog.contents}</span>
  </div>
  <div>
    <c:if test="${blog.user.userNo == sessionScope.user.userNo}">             
    <button type="button" id="btn-delete">블로그삭제</button>
              <input type="hidden" value="${blog.blogNo}">
            </c:if>
  </div>
  
  <hr>
      

<!-- 댓글 작성 창 -->
<form id="frm-comment">
  <textarea id="contents" name="contents"></textarea>
  <input type="hidden"  name="blogNo" value="${blog.blogNo}">
  <c:if test="${not empty sessionScope.user}" >  
    <input type="hidden"  name="userNo" value="${sessionScope.user.userNo}">
  </c:if>
  <button id="btn-comment-register" type="button">댓글등록</button>
</form>

<hr>

<!-- 댓글 목록 -->
<div id="comment-list"></div>
<div id="paging"></div>

<script>

const fndelete = ()=> {
	$('#btn-delete').on('click', (evt)=>{
	 $.ajax({
		 type:'GET',
		 url: '${contextPath}/blog/remove.do?blogNo=' + ${blog.blogNo},
		 dataType: 'json',
		 success: (resData)=>{
			 if(resData.deleteCount === 1){
				 alert('블로그삭제')
			 } else{
				 alert('실패');
			 }
		 }
	 })
	
	})
	
}
fndelete();

const fnCheckSignin = () => {
    if('${sessionScope.user}' === '') {
      if(confirm('Sign In 이 필요한 기능입니다. Sign In 할까요?')) {
        location.href = '${contextPath}/user/signin.page';
      }
    }
  }

const fnRegisterComment = ()=> {
	$('#btn-comment-register').on('click', (evt)=>{
	  fnCheckSignin();
			$.ajax({
				// 요청
				type: 'POST',
				url: '${contextPath}/blog/registerComment.do',
				data: $('#frm-comment').serialize(),  // <form> 내부의 모든 입력을 파라미터 형식으로 보낼 때 사용, 입력 요소들은 name 속성을 가지고 있어야 함
				// 응답
				dataType: 'json',
				success: (resData)=> {  // {"insertCount": 1}
					if(resData.insertCount === 1) {
						alert('댓글이 등록되었습니다');
						$('#contents').val('');  // 초기화
						// 댓글 목록 보여주는 함수
						fnCommentList();
					} else {
						alert('댓글 등록이 실패했습니다.');
					}
				},
				error: (jqXHR)=> {
					alert(jqXHR.status + '(' + jqXHR.status + ')');
				}
			})
		
	})
}

// 전역 변수
var page =1;

const fnCommentList = ()=> {
	$.ajax({
		type: 'GET',
		url: '${contextPath}/blog/comment/list.do',
		data: 'blogNo=${blog.blogNo}&page=' + page,  // 보내는 파라미터
		dataType: 'json',
		success: (resData)=> {  // resData = {"commentList": [], "paging": "<1 2 3 4 5>"}
			let commentList = $('#comment-list');
		  let paging = $('#paging');
		  commentList.empty();
		  paging.empty();
		  if(resData.commentList.length === 0 ) {
			  commentList.append('<div> 첫 번째 댓글의 주인공이 되어 보세요 </div>');
			  paging.empty();
			  return;
		  } 
		  $.each(resData.commentList, (i, comment) => {
			  let str = '';
			  // 댓글은 들여쓰기 (댓글 여는 <div>)
			  if(comment.depth === 0) {  // 댓글이 없으면
				  str += '<div>';
			  } else {
				  str += '<div style="padding-left: 32px;">';
			  }
			  // 댓글 내용 표시
			  str += '<span>' + comment.user.email;
			  str += '('+  moment(comment.createDt).format('YYYY.MM.DD.') +')';
			  str += '</span>';
			  str += '<div>' + comment.contents + '</div>';
			  // 답글 버튼 (원글에만 답글 버튼이 생성됨)
			  if(comment.depth === 0) {
				  str += '<button type="button" class="btn btn-success btn-reply">답글</button>';
			  }
			  // 삭제 버튼 (내가 작성한 댓글에만 삭제 버튼이 생성됨)
			  if(Number('${sessionScope.user.userNo}') === comment.user.userNo) {
				  str += '<button type="button" class="btn btn-danger btn-remove data-comment-no="'+comment.commentNo +'">삭제</button>';
			  }
			  /***************************    답글입력 화면   *********************/
   			  if(comment.depth === 0){
			    str += '<div>';
			    str += ' <form class="frm-reply">';
			    str += '   <input type="hidden" name="groupNo" value="'+comment.groupNo+'">';
			    str += '   <input type="hidden" name="blogNo" value="${blog.blogNo}">';
			    str += '   <input type="hidden" name="userNo" value="${sessionScope.user.userNo}">';
			    str += '   <textarea name="contents" placeholder="답글 입력"></textarea>';
			    str += '   <button type="button" class="btn btn-warning btn-register-reply">작성완료</button>';
			    str += ' </form>';
			    str += '</div>';
   				  
   			  } 
			  /***************************       *******************/
			  // 댓글 닫는 <div>
			  str += '</div>'
			  // 목록에 댓글 추가
			  commentList.append(str);
		  })
		  // 페이징 표시
		  paging.append(resData.paging);
		},
		error: (jqXHR)=> {
			alert(jqXHR.statusText + '(' + jqXHR.status + ')');
		}
	})
}

const fnCommentRemove = ()=> {
	  $(document).on('click', '.btn-remove', (evt)=>{
		 
		 
		  // location '${contextPath}/blog/comment/remove.do',
		  
	  })
}



const fnPaging = (p)=> {
	page = p;
	fnCommentList();
}

const fnRegisterReply = () => {
	  $(document).on('click', '.btn-register-reply', (evt) => {
	    fnCheckSignin();
	    $.ajax({
	      type: 'POST',
	      url: '${contextPath}/blog/comment/registerReply.do',
	      data: $(evt.target).closest('.frm-reply').serialize(),
	      dataType: 'json',
	      success: (resData) => {
	        if(resData.insertReplyCount === 1) {
	          alert('답글이 등록되었습니다.');
	          $(evt.target).prev().val('');
	          fnCommentList();
	        } else {
	          alert('답글 등록이 실패했습니다.');
	        }
	      },
	      error: (jqXHR) => {
	        alert(jqXHR.statusText + '(' + jqXHR.status + ')');
	      }
	    })
	  })
	}


$('#contents').on('click', fnCheckSignin);
fnRegisterComment();
fnCommentList();
fnRegisterReply();
fnCommentRemove();
</script>

<%@ include file="../layout/footer.jsp" %>