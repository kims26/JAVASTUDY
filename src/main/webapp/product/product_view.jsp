<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<script type="text/javascript">
  
  function add_cart(){

	  
	  //로그인여부(안된경우)
	  if("${ empty user }" =="true"){
		  
		  if(confirm("장바구니 담기는 로그인후에 가능합니다\n로그인 하시겠습니까?")==false)return;
		  
		  //로그인창 이동 : location.href <=현재경로
		  location.href="../member/login_form.do?url=" + 
				         encodeURIComponent(location.href) ;
		  return;
	  }
	  
      //장바구니 담기	  
	  var p_idx   = '${ vo.p_idx }';
	  var mem_idx = '${ user.mem_idx }';
	  
	  //Ajax이용 장바구니 담기
	  
	  $.ajax({
		  url	:	"cart_insert.do",
		  data	:	{"p_idx": p_idx, "mem_idx": mem_idx },
		  dataType: "json",
		  success	: function(res_data){
			  // res_data = {"result" : "exist" }
			  // res_data = {"result" : "success" }
			  // res_data = {"result" : "fail" }
			  
			  if(res_data.result == "exist"){
				  alert("이미 등록된 상품입니다");
				  return;
			  }
			  
			  if(res_data.result == "fail"){
				  alert("장바구니 담기 실패");
				  return;
			  }
			  
			  if(confirm("해당상품을 장바구니에 담았습니다\n장바구니 보기로 이동하시겠습니까?")==false)return;
			  
			  location.href="cart_list.do";
			  
			  
		  },
		  error		: function(err){
			  alert(err.responseText);
		  }
	  });
	  
	  
	  
  }


</script>


</head>
<body>

<jsp:include page="index.jsp"/>

<table align="center" width="600" border="1"
 style="border-collapse:collapse;font-size:8pt"
 bordercolor="navy" cellpadding="4" cellspacing="0">
		<tr>
			<td width="40%">제품분류</td>
			<td width="60%">${ vo.category }</td>
		</tr>
		<tr>
			<td width="40%">제품번호</td>
			<td width="60%">${ vo.p_num }</td>
		</tr>
		<tr>
			<td width="40%">제품명</td>
			<td width="60%">${ vo.p_name }</td>
		</tr>
		<tr>
			<td width="40%">제조사</td>
			<td width="60%">${ vo.p_company }</td>
		</tr>
		<tr>
			<td width="40%">제품가격</td>
			<td width="60%">
				<fmt:formatNumber value="${ vo.p_price }"/>원
				(할인가:<fmt:formatNumber value="${ vo.p_saleprice }"/>원)
			</td>
		</tr>
		<tr>
			<td colspan="2">제품설명</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
		        <img src="../images/${ vo.p_image_l }"  width="500">
			</td>
		</tr>
		<tr>
			<td colspan="2">${ vo.p_content }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button"  value="장바구니 담기"  
				       onclick="add_cart();"/>
				<input type="button"  value="장바구니 보기"  
				       onclick="location.href='cart_list.do'"/>
			</td>
		</tr>
	</table>
</body>
</html>