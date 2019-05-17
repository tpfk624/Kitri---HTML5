<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%!Product product; %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(function(){
		$("input").click(function() {
			alert("ajax호출")
			$.ajax({
				url:"productInfo",
				data: "no="+ $(this).attr("id"),
				method: "get",
				success : function(result){
					//location.href="/myjquery/productdetail.jsp";
					$("section").html(result);
				},
				error : function(){
					alert("에러남");
				} 	
			});
		});
		
	});
</script>

</head>
<%!
int i;
%>
 <%
 	
 List<Product> list= (List<Product>)request.getAttribute("result");
%>
<% 
for (i =0; i<list.size(); i++){ //향상된 for문으로 바꿔보기
	product = new Product();
	 product = list.get(i);
	 %>

	<%-- <img src="/myjquery/images/img<%=i+1%>.PNG" align="center"><br/> <!-- alt : alternate의 약자 : 어떤 브라우저에서든지 결과를 똑같이 보여주게 하는 것(웹표준화)--> --%>
	<img src="images/<%=product.getProd_no()%>.jpg"><br />
	카테고리: <%= product.getProductCategory().getCate_no()%><br/>
	 상품번호 : <%=product.getProd_no()%><br/>
	 상품명 : <%=product.getProd_name()%><br/>
	 가격 : <%=product.getProd_price()%><br/>
	 상세정보 : <%=product.getProd_detail()%>
	 
<input type="button" value="상세정보" id="<%=product.getProd_no()%>" name="<%=product.getProd_no()%>">	 
<hr/>
	 
<% 
} 
%>