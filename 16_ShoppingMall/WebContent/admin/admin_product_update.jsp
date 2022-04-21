<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<hr width="80%" color="gray">
			<h1>상품 수정</h1>
		<hr width="80%" color="gray"> <br>
		
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/admin_product_update_ok.do">
			
			<c:set var="dto" value="${productCont }" />
			
			<input type="hidden" name="p_num" value="${dto.getPnum() }">
			<table border="1" cellspacing="0" width="600">
				<tr>
					<th>카테고리 코드</th>
					<td><input name="p_category" value="${dto.getPcategory_fk() }" readonly></td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input name="p_name" value="${dto.getPname() }" readonly></td>
				</tr>
				<tr>
					<th>제조사</th>
					<td><input name="p_company" value="${dto.getPcompany() }" readonly></td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
						<img src="<%=request.getContextPath() %>/upload/${dto.getPimage() }"
							width="100" height="80">
						<input type="file" name="p_image_new">
						<%-- 새로운 이미지를 사용하지 않을 경우 기존 이미지 사용 --%>
						<input type="hidden" name="p_image_old" value="${dto.getPimage() }">
					</td>
				</tr>
				<tr>
					<th>수 량</th>
					<td><input type="number" name="p_qty" min="1" max="100" value="${dto.getPqty() }"></td>
				</tr>
				<tr>
					<th>가 격</th>
					<td><input name="p_price" maxlength="8" value="${dto.getPrice() }"></td>
				</tr>
				<tr>
					<th>사 양</th>
					<td>
						<select name="p_spec">
							<option value="none"
								<c:if test="${dto.getPspec() == 'none' }"> selected </c:if>>일반</option>
							<option value="hit"
								<c:if test="${dto.getPspec() == 'hit' }"> selected </c:if>>인기</option>
							<option value="new"
								<c:if test="${dto.getPspec() == 'new' }"> selected </c:if>>최신</option>
							<option value="recommand"
								<c:if test="${dto.getPspec() == 'recommand' }"> selected </c:if>>추천</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>소 개</th>
					<td>
						<textarea rows="7" cols="30" name="p_content">${dto.getPcontents() }</textarea>
					</td>
				</tr>
				<tr>
					<th>포인트</th>
					<td><input name="p_point" value="${dto.getPoint() }"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="상품수정">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="초기화">&nbsp;&nbsp;&nbsp;
						<input type="button" value="취소" onclick="history.back()">					
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>