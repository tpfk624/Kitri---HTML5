<%@page import="java.util.ArrayList"%>
<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<script>
$(function(){
	var aDiv = $('div');
		
	$(aDiv).click(function(){
			$.ajax({
				url : "/myjquery/productinfo",
				method : 'get',
				data : 'no='+ $(this).attr("id"),
				success : function(result) {
					$("section").html(result); //div에 결과를 html로 작성하라
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("에러남");
				}
			});
			return false;
		});
});
</script>



<%!List<Product> list = new ArrayList();%>
<%
	list = (List<Product>) request.getAttribute("productlist");
%>
<%
/*미래쓰 방법!! 
for (int i = 0; i < list.size(); i++) {
	Product p = new Product();
	p = list.get(i); */

List<Product> list = 
         (List)request.getAttribute("productlist");
   for(Product p: list){    
%>
<div id="<%=p.getProd_no()%>">
<img src="images/<%=p.getProd_no()%>.jpg"><br />
카테고리:<%=p.getProductCategory().getCate_name()%><br />
상품번호:<%=p.getProd_no()%><br />
상품명:<%=p.getProd_name()%><br />
가격:<%=p.getProd_price()%><hr />
</div>
<%
}
%>


