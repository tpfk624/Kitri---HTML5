<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 2단계내용 형식지정 + 3단계 내용 function -->












<% 
Product p = (Product)request.getAttribute("product");
%>
<div>
<img src="images/<%=p.getProd_no()%>.jpg"><br />
상품명: <%=p.getProd_name() %><br />
상품설명: <%= p.getProd_detail() %> <br />
상품번호: <%= p.getProd_no() %><br />
상품가격:<%=p.getProd_price() %><br />
상품 수량: <input type="number" name="quantity"><br />
<button> 장바구니 넣기</button>
</div>   
    
