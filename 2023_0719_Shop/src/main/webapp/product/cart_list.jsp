<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

		<tr align="center">
			<td>p111</td>
			<td>PC</td>
			<td>
				단가:500<br>
				<font color="red">
				세일가격:<b>200</b>
				</font>
			</td>
			<td>
				<!-- 수량 조정 폼 -->
				<form action="editQuant.jsp"
						method="post">
					<input type="hidden" name="p_num" value="">
					<input name="q" size="4"  align="center" value="">
					<input type="submit" value="수정">
				</form>
			</td>
			<td>1000</td>
			<td>
				<input type="button" value="삭제"
 style="border:1 solid black;cursor:hand"
 onclick="location.href='delProduct.jsp?p_num='">
			</td>
		</tr>

		<tr>
			<td colspan="6" align="center">
				<b>장바구니가 비었습니다.</b>
			</td>
		</tr>

		<tr>
			<td colspan="5" align="right">
				총 결재액 :
			</td>
			<td>1000</td>
		</tr>
	</table>
</body>
</html>






