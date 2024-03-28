<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <h1>신규학생등록 화면</h1>
  <div>
    <form id="frm-register" method="post" action="${contextPath}/student/register.do">
      <div>
        <label for="name">이름</label>
        <input type="text" id="name" name="name">
      </div>
      <div>
        <label for="kor">국어</label>
        <input type="text" id="kor" name="kor">
      </div>
      <div>
        <label for="eng">영어</label>
        <input type="text" id="eng" name="eng">
      </div>
      <div>
        <label for="math">수학</label>
        <input type="text" id="math" name="math">
      </div>
      <hr>
      <div>
        <button type="submit">작성완료</button>
        <button type="reset">다시작성</button>
        <button type="button" id="btn-list">목록보기</button>
      </div>
    </form>
  </div>

  <script>
    
    function fnStudentRegister(){
      document.getElementById('frm-register').addEventListener('submit', (evt)=>{
        const kor = document.getElementById('kor');
        const eng = document.getElementById('eng');
        const math = document.getElementById('math');
        if(kor.value === '' || isNaN(kor.value) || kor.value < 0 || kor.value > 100){
          alert('국어 점수를 확인하세요.');
          kor.focus();
          evt.preventDefault();
          return;
        }
        else if(eng.value === '' || isNaN(eng.value) || eng.value < 0 || eng.value > 100){
          alert('영어 점수를 확인하세요.');
          eng.focus();
          evt.preventDefault();
          return;
        }
        else if(math.value === '' || isNaN(math.value) || math.value < 0 || math.value > 100){
          alert('수학 점수를 확인하세요.');
          math.focus();
          evt.preventDefault();
          return;
        }
      });
    }
    
    function fnStudentList(){
      document.getElementById('btn-list').addEventListener('click', (evt)=>{
        location.href = '${contextPath}/student/list.do';
      });
    }
  
    fnStudentRegister();
    fnStudentList();
    
  </script>

</body>
</html>