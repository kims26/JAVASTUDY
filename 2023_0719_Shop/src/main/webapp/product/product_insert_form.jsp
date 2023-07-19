<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

const regular_number = /^[0-9]{1,10}$/;

function send(f)
{
   let category 	= f.category.value;
   let p_num 		= f.p_num.value;
   let p_name 		= f.p_name.value;
   let p_company 	= f.p_company.value;
   let p_price 		= f.p_price.value;
   let p_saleprice 	= f.p_saleprice.value;
   let p_content 	= f.p_content.value;
   let photo_s 		= f.photo_s.value;
   let photo_l 		= f.photo_l.value;
   
   if(category==''){
	   alert('카테고리를 선택하세요');
	   return;
   }
   
   if(p_num==''){
	   alert('상품모델번호를 입력하세요');
	   f.p_num.value='';
	   f.p_num.focus();
	   return;
   }
   
   if(p_name==''){
	   alert('상품명을 입력하세요');
	   f.p_name.value='';
	   f.p_name.focus();
	   return;
   }
   
   if(p_company==''){
	   alert('제조사명을 입력하세요');
	   f.p_company.value='';
	   f.p_company.focus();
	   return;
   }
   
   if(regular_number.test(p_price)==false){
	   alert('금액(정가)은 숫자만 입력하세요');
	   f.p_price.value='';
	   f.p_price.focus();
	   return;
   }
   
   if(regular_number.test(p_saleprice)==false){
	   alert('금액(할인가)은 숫자만 입력하세요');
	   f.p_saleprice.value='';
	   f.p_saleprice.focus();
	   return;
   }
   
   if(p_content==''){
	   alert('상품설명을 입력하세요');
	   f.p_content.value='';
	   f.p_content.focus();
	   return;
   }
   
   if(photo_s==''){
	   alert('상품이미지(소)를 선택하세요');
	   return;
   }
   
   if(photo_l==''){
	   alert('상품이미지(대)를 선택하세요');
	   return;
   }
   
   f.action = "insert.do"; // /product/insert.do
   
   
   f.submit();
   
}
</script>
</head>
<body>

<!-- title -->
<jsp:include page="index.jsp"/>

<form name="f" method="post" action="insert.do" 
      enctype="multipart/form-data"> 
<table align="center" width="600" border="1" 
    style="border-collapse:collapse;font-size:8pt" bordercolor="navy"
    cellpadding="2" cellspacing="0">
    <tr>
        <td>제품Category</td>
        <td><select name="category">
        		<option value="">카테고리 선택</option>
        		<option value="com001">컴퓨터</option>
        		<option value="ele002">가전제품</option>
        		<option value="sp003">스포츠</option>
        </select></td>
    </tr>
    <tr>
        <td>제품번호</td>
        <td><input name="p_num" type="text" ></td>
    </tr>
    <tr>
        <td>제품이름</td>
        <td><input name="p_name" type="text" ></td>
    </tr>
    <tr>
        <td>제품 판매사</td>
        <td><input name="p_company" type="text" ></td>
    </tr>
    <tr>
        <td>제품가격</td>
        <td><input name="p_price" type="text" ></td>
    </tr>
    <tr>
        <td>제품할인가격</td>
        <td><input name="p_saleprice" type="text" ></td>
    </tr>    
    <tr>
        <td>제품설명</td>
        <td><TEXTAREA name="p_content" rows="5" cols="50"></TEXTAREA></td>
    </tr>
    <tr>
        <td>제품사진(작은사진)</td>
        <td><input type="file" name="photo_s">
    </tr>
    <tr>
        <td>제품사진(큰사진)</td>
        <td><input type="file" name="photo_l">
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="button" value="등록" 
                   onclick="send(this.form);" >
            <input type="reset" value="Clear" >
        </td>
    </tr>    
</table>
</form>
</body>
</html>