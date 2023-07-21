<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<script type="text/javascript">

   var regular_number = /^[0-9]{1,4}$/;// 0 ~9999
   
   function modify(c_idx){
	   
	   var c_cnt = $("#c_cnt_" + c_idx).val();
	   
	   
	   if(regular_number.test(c_cnt)==false){
		   alert('0~9999까지의 숫자만 입력하세요');
		   $("#c_cnt_" + c_idx).val("");
		   $("#c_cnt_" + c_idx).focus();
		   return;
	   }
	   
	   //수정
	   location.href = "cart_update.do?c_idx=" + c_idx + "&c_cnt=" + c_cnt; 
	   
   }
   
   
   //장바구니 삭제
   function del(c_idx){
	   
	   
	   if(confirm("정말 삭제하시겠습니까?")==false)return;
	   
	   //
	   location.href="cart_delete.do?c_idx=" + c_idx
	   
   }
   
   //선택삭제
   function del_select(f){
	   
	   if($("input[name='c_idx']:checked").length == 0 ){
		   
		   alert("삭제할 상품을 선택하세요!!");
		   return;
	   }
	   
	   
	   f.action="cart_delete_select.do";
	   f.submit();
	   
   }   
   

</script>


<script type="text/javascript">

  $(document).ready(function(){
	  
	  //전체버튼을 클릭시
	  $("#check_all").click(function(){
		  
		  var checked = $(this).is(":checked");
		  
		  //체크all상태에 따라서 아래측 체크박스 체크여부 결정
		  $("input[name='c_idx']").prop("checked", checked);
	  });
	  
	  
	  //각항목의 체크박스 클릭시
	  $("input[name='c_idx']").click(function(){
		 
		  //전체체크박스갯수 구하기
		  var total_length       = $("input[name='c_idx']").length;
		  
		  //체크된 체크박스갯수 구하기
		  var total_check_length = $("input[name='c_idx']:checked").length;
		  
		  console.log(total_length,total_check_length);
		  
		  $("#check_all").prop("checked", (total_length==total_check_length) );
		  
		  
	  });
	  
	  
  });


</script>




</head>
<jsp:include page="index.jsp"/>
<body>

<form>
    
	<table align="center" width="600" border="1"
 style="border-collapse:collapse;font-size:8pt"
 bordercolor="navy" cellpadding="4" cellspacing="0">
        <tr>
			<td colspan="6">
			
			  <input type="checkbox" value="전체" id="check_all">전체 &nbsp;&nbsp;
			  <input type="button"   value="선택삭제" onclick="del_select(this.form);">
			
			</td>
		</tr>
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
				<td align="left">
				   <input type="checkbox" name="c_idx" value="${ vo.c_idx }"> 
				   ${ vo.p_num }
				</td>
				<td>${ vo.p_name }</td>
				<td>
					단가:<fmt:formatNumber value="${ vo.p_price }" />원<br>
					<font color="red">
					세일가격:<b><fmt:formatNumber value="${ vo.p_saleprice }" />원</b>
					</font>
				</td>
				<td>
					<!-- 수량 조정 폼 -->
					<input id="c_cnt_${ vo.c_idx }" size="4"  align="center" value="${ vo.c_cnt }">
					<input type="button" value="수정" onclick="modify('${ vo.c_idx }');">
					
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
</form>
	
</body>
</html>






