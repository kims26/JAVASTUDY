<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript">

   var regular_number = /^[0-9]{1,4}$/;// 0 ~9999
   
   function modify(f){
	   
	   var c_cnt = f.c_cnt.value;
	   if(regular_number.test(c_cnt)==false){
		   alert('0~9999까지의 숫자만 입력하세요');
		   f.c_cnt.value='';
		   f.c_cnt.focus();
		   return;
	   }
	   
	   f.action = "cart_update.do";
	   f.submit();
   }
   
   
   //장바구니 삭제
   function del(c_idx){
	   
	   
	   if(confirm("정말 삭제하시겠습니까?")==false)return;
	   
	   //
	   location.href="cart_delete.do?c_idx=" + c_idx
	   
   }
   
   

</script>




</head>
<jsp:include page="index.jsp"/>
<body>
	<table align="center" width="600" border="1"
 style="border-collapse:collapse;font-size:8pt"
 bordercolor="navy" cellpadding="4" cellspacing="0">
		<tr>
			<td colspan="6">:: 장바구니 내용</td>
		</tr>
		<tr bgcolor="#dedede">
			<th>제품번호</th>
			<th width="25%">제품명</th>
			<th>단가</th>
			<th>수량</th>
			<th>금액</th>
			<th>삭제</th>
		</tr>

        <!-- for(CartVo vo : list)   -->   
        <c:forEach var="vo"  items="${ list }">

			<tr align="center">
				<td>${ vo.p_num }</td>
				<td>${ vo.p_name }</td>
				<td>
					단가:<fmt:formatNumber value="${ vo.p_price }" />원<br>
					<font color="red">
					세일가격:<b><fmt:formatNumber value="${ vo.p_saleprice }" />원</b>
					</font>
				</td>
				<td>
					<!-- 수량 조정 폼 -->
					<form action="">
						<input type="hidden" name="c_idx" value="${ vo.c_idx }">
						<input name="c_cnt" size="4"  align="center" value="${ vo.c_cnt }">
						<input type="button" value="수정" onclick="modify(this.form);">
					</form>
				</td>
				<td><fmt:formatNumber value="${ vo.amount }" />원</td>
				<td>
					<input type="button" value="삭제" style="border:1 solid black;cursor:hand"
 	                       onclick="del('${ vo.c_idx }');">
				</td>
			</tr>
		
		</c:forEach>



        <!-- 장바구니가 비어있는경우 -->  
        <c:if test="${ empty list }">
			<tr>
				<td colspan="6" align="center">
					<b>장바구니가 비었습니다.</b>
				</td>
			</tr>
		</c:if>

		<tr>
			<td colspan="5" align="right">
				총 결재액 :
			</td>
			<td><fmt:formatNumber value="${ total_amount }"/>원</td>
		</tr>
	</table>
</body>
</html>






