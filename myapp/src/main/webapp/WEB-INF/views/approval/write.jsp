<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>

<jsp:include page="../layout/header.jsp"/>

<style>
 
</style>

  <form method="POST"
        action="${contextPath}/approval/write.do">
    <div>
      <h1>연차신청서</h1>
      <input type="hidden" name="sort" value="1">
      <div>
        <label for="departName">부서명</label>
        <input type="text" name="departName" id="departName">
         <label for="name">성명</label>
        <input type="text" name="name" id="name">
      </div>
      <div>
        <label for="rankTitle">직책</label>
        <input type="text" name="rankTitle" id="rankTitle">
        <label for="leaveType">연차종류</label>
        <input type="text" name="leaveType" id="leaveType">
      </div>
      <div>
        <label for="reason">사유</label>
        <input type="text" name="reason" id="reason">
      </div>
      <div>
        <label for="startDate">시작일</label>
        <input type="text" name="startDate" id="endDate">
        <label for="endDate">종료일</label>
        <input type="text" name="endDate" id="endDate">
      </div>
      <input type="hidden" name="status" value="0">
      <input type="hidden" name="employeeNo" value="1">
    </div>
    <button type="submit" id="frm-register">기안</button>
  </form>





<script>

const fnRegisterPost = (evt) => {
    if(document.getElementById('name').value == ''){
      alert('제목 입력은 필수입니다.');
      evt.preventDefault();
      return;
    }
  }
  
  document.getElementById('frm-register').addEventListener('submit', (evt) => {
    fnRegisterPost(evt);
  })
  

</script>


<%@ include file="../layout/footer.jsp" %>